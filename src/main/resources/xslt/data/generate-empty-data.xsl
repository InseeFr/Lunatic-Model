<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:h="http://xml.insee.fr/schema/applis/lunatic-h"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    exclude-result-prefixes="xs xsi h xsl"
    version="2.0">
    
    <xsl:output indent="yes"/>
    
    <xsl:template match="/">
        <Data>
            <COLLECTED>                
                <xsl:apply-templates select="//h:variables[@variableType='COLLECTED']"/>
            </COLLECTED>
            <EXTERNAL>                
                <xsl:apply-templates select="//h:variables[@variableType='EXTERNAL']"/>
            </EXTERNAL>
        </Data>
    </xsl:template>
    
    <xsl:template match="h:variables[@variableType='COLLECTED']">
        <xsl:variable name="name" select="h:name"/>
        <xsl:element name="{$name}">
            <xsl:apply-templates select="h:values/*" mode="clean"/>            
        </xsl:element>
    </xsl:template>
    
    
    <xsl:template match="h:variables[@variableType='EXTERNAL']">
        <xsl:variable name="name" select="h:name"/>
        <xsl:apply-templates select="h:value">
            <xsl:with-param name="name" select="$name" tunnel="yes"/>
        </xsl:apply-templates>
    </xsl:template>
    
    <xsl:template match="h:value">
        <xsl:param name="name" tunnel="yes"/>
        <xsl:element name="{$name}">
            <xsl:apply-templates select="@*" mode="clean"/>
            <xsl:apply-templates select="node()"/>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="@xsi:type" mode="clean"/>
    <xsl:template match="@xsi:nil" mode="clean">
        <xsl:attribute name="type" select="'null'"/>
    </xsl:template>
    
    <xsl:template match="h:*" mode="clean">
        <xsl:element name="{local-name(.)}">
            <xsl:apply-templates select="@*|node()" mode="clean"/>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>