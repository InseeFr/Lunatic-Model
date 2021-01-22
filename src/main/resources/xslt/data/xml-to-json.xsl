<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:j="http://www.w3.org/2013/XSL/json"
    xmlns:xf="http://www.w3.org/2005/xpath-functions"
    exclude-result-prefixes="xs"
    expand-text="yes"
    version="3.0">
    
    <xsl:output method="text" media-type="text/json" encoding="utf-8" omit-xml-declaration="no" indent="yes"/>
    <xsl:param name="javaCall" select="false()" as="xs:boolean"/>
    
    <xsl:variable name="namespace">
        <xsl:choose>
            <xsl:when test="$javaCall">
                <xsl:value-of select="'xf:'"/>
            </xsl:when>
            <xsl:otherwise><xsl:value-of select="'j:'"/></xsl:otherwise>
        </xsl:choose>
    </xsl:variable>
    
    <xsl:variable name="rootName" select="'Data'"/>
    
    
    <xsl:template match="/">
        <xsl:variable name="xmlJSON">
            <xsl:apply-templates mode="prepare"/>
        </xsl:variable>
        <xsl:copy-of select="xml-to-json($xmlJSON, map{'indent':true()})"/>
    </xsl:template>
    
    <xsl:template match="*" mode="prepare">
        <xsl:param name="displayKey" select="true()" as="xs:boolean" tunnel="yes"/>
        <xsl:variable name="nodeName" select="name(.)"/>
        <xsl:variable name="nbChildren">
            <xsl:variable name="children" select="child::*[name(.)=$nodeName]" as="node()*"/>
            <xsl:value-of select="count($children)"/>
        </xsl:variable>        
        <xsl:variable name="elementName">
            <xsl:choose>
                <xsl:when test="@type!=''"><xsl:value-of select="concat($namespace,@type)"/></xsl:when>
                <xsl:when test="$nbChildren>0"><xsl:value-of select="concat($namespace,'array')"/></xsl:when>
                <xsl:otherwise><xsl:value-of select="concat($namespace,'map')"/></xsl:otherwise>
            </xsl:choose>
        </xsl:variable>
        
        <xsl:element name="{$elementName}">
            <xsl:if test="$displayKey and $nodeName!=$rootName">
                <xsl:attribute name="key" select="$nodeName"/>
            </xsl:if>
            <xsl:choose>
                <xsl:when test="@type!=''"><xsl:value-of select="."/></xsl:when>
                <xsl:otherwise>
                    <xsl:apply-templates mode="prepare">
                        <xsl:with-param name="displayKey" select="$nbChildren=0" as="xs:boolean" tunnel="yes"/>
                    </xsl:apply-templates>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>