<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:j="http://www.w3.org/2013/XSL/json"
    exclude-result-prefixes="xs"
    expand-text="yes"
    version="3.0">
    
    <xsl:output method="text" media-type="text/json" encoding="utf-8" omit-xml-declaration="no" indent="yes"/>
<!--    <xsl:output indent="yes"/>-->
    
    <xsl:template match="/">
        <xsl:apply-templates/>
    </xsl:template>
    
    <xsl:template match="Questionnaire">
        <xsl:variable name="output-xml">
            <xsl:apply-templates select="json-to-xml(.)" mode="clean"/>
        </xsl:variable>
<!--        <xsl:copy-of select="$output-xml"/>-->
        <xsl:copy-of select="xml-to-json($output-xml, map{'indent':true()})"/>
    </xsl:template>
    
    <!-- delete type attribute except when key is linked to the suggesters block-->
    <xsl:template match="*[@key='type'][not(ancestor::*[@key=('suggesters')])]" mode="clean"/>
    <!-- delete map useless inside array -->
    <xsl:template match="*[local-name(.)='map'][parent::*[@key=('PREVIOUS','COLLECTED','FORCED','EDITED','INPUTED') and local-name(.)='array'] | parent::*[@key=('values')]]" mode="clean">
        <xsl:apply-templates mode="clean"/>
    </xsl:template>
    <xsl:template match="*[local-name(.)='map'][parent::*[@key='value' and local-name(.)='array'] or (self::*[@key='value'] and preceding-sibling::*[@key='variableType'])]" mode="clean">
        <xsl:apply-templates mode="clean"/>
    </xsl:template>
    <!-- delete key attribute for array inside array -->
    <xsl:template match="*[local-name(.)='array' and @key=('PREVIOUS','COLLECTED','FORCED','EDITED','INPUTED','value')][ancestor::*[local-name(.)='array' and @key=('PREVIOUS','COLLECTED','FORCED','EDITED','INPUTED','value')]]" mode="clean">
        <xsl:copy>
            <xsl:apply-templates mode="clean"/>
        </xsl:copy>
    </xsl:template>
    
        
    <!-- delete responses attribute in table responses for Table component -->
    <xsl:template match="*[@key='cells' and preceding-sibling::*[@key='componentType']]" mode="clean">
        <xsl:copy>
            <xsl:copy-of select="@*"/>
            <xsl:for-each select="descendant::*[@key='cells']">
                <xsl:copy>
                    <xsl:apply-templates mode="clean"/>
                </xsl:copy>
            </xsl:for-each>
        </xsl:copy>
    </xsl:template>
    
    <!-- rename the rulesA key of suggesters to rules -->
    <xsl:template match="*[@key='rulesA']" mode="clean">
        <xsl:copy>
        	<xsl:attribute name="key" select="'rules'"/>
            <xsl:apply-templates select="node()" mode="clean"/>
        </xsl:copy>
    </xsl:template>
    
    <!-- Transform the array produced inside cleaning block into simple map object -->
    <!-- 1. When encountering the array ndoe, not copying it but treating its children -->
    <xsl:template match="*[parent::*/@key='cleaning']" mode="clean">
        <xsl:apply-templates select="node()" mode="clean"/>
    </xsl:template>
    <!-- 2. When encountering the map child of the array, putting the attribute key of variable inside -->
    <xsl:template match="*[parent::*/parent::*/@key='cleaning']" mode="clean">
        <xsl:copy>
            <xsl:attribute name="key" select="parent::*/@key"/>
            <xsl:apply-templates select="node()" mode="clean"/>
        </xsl:copy>
    </xsl:template>
    <!-- 3. When encountering value node, do not copy (it is a line return wrongly treated as an object 
    since we do not have a proper schema model for the cleaning part...-->
    <xsl:template match="*[@key='value' and ancestor::*/@key='cleaning']"  mode="clean"/>
    
    <xsl:template match="@*|node()" mode="clean">
        <xsl:choose>
            <xsl:when test="self::text()">
                <xsl:value-of select="." />
            </xsl:when>
            <xsl:otherwise>
                <xsl:copy>
                    <xsl:apply-templates select="@*|node()" mode="clean"/>
                </xsl:copy>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
</xsl:stylesheet>