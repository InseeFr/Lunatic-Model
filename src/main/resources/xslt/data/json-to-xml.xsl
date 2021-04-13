<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:j="http://www.w3.org/2013/XSL/json"
    exclude-result-prefixes="j"
    version="3.0">
    
    <xsl:output indent="yes"/>
    
    <xsl:variable name="rootName" select="'Data'"/>
    
    <xsl:template match="/">
        <xsl:apply-templates select="json-to-xml(.)" mode="prepare"/>
    </xsl:template>
    
    <xsl:template match="*" mode="prepare">
        <xsl:variable name="name">
            <xsl:choose>
                <xsl:when test="@key!=''"><xsl:value-of select="@key"/></xsl:when>
                <xsl:otherwise>
                    <xsl:variable name="arrayName" select="ancestor::*[local-name(.)='array'][@key!='']/@key"/>
                    <xsl:value-of select="if($arrayName) then $arrayName else $rootName"/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:variable>
        <xsl:element name="{$name}">
            <xsl:choose>
                <xsl:when test="local-name(.)=('map','array')">
                    <xsl:apply-templates mode="prepare"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="type" select="local-name(.)"/>
                    <xsl:value-of select="."/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>