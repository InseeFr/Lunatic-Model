<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:j="http://www.w3.org/2013/XSL/json"
    xmlns:fn="http://www.w3.org/2005/xpath-functions"
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
    
    <!-- delete type attribute except when key is linked to the suggesters block or to a label...-->
    <xsl:template match="*[@key='type'][not(ancestor::*[@key=('suggesters')] or parent::*[@key=('label','min','max','iterations','conditionFilter','errorMessage','control','expression','xAxisIterations','yAxisIterations')])]" mode="clean"/>
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
    
        
    <!-- Delete the second level bodyLines, which we do not want to keep in the final JSON -->
    <!--1. Do not keep the map surrounding the bodyline -->
    <xsl:template match="*[child::*[@key='bodyLine']]" mode="clean">
        <xsl:apply-templates select="child::*[@key='bodyLine']" mode="clean"/>
    </xsl:template>
    <!-- Do not keep the attribute key bodyLine of array -->
    <xsl:template match="*[@key='bodyLine']" mode="clean">
        <xsl:copy>
            <xsl:apply-templates select="node()" mode="clean"/>
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
    <!-- 1. When encountering the array node, not copying it but treating its children -->
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
    
    
    <!-- Transform the array produced inside resizing block into simple map object -->
    <!-- 1. When encountering the array node, not copying it but treating its children -->
    <xsl:template match="*[parent::*/@key='resizing']" mode="clean">
        <xsl:apply-templates select="node()" mode="clean"/>
    </xsl:template>
    <!-- 2. When encountering the map child of the array, putting the attribute key of variable inside -->
    <xsl:template match="*[parent::*/parent::*/@key='resizing']" mode="clean">
        <xsl:copy>
            <xsl:attribute name="key" select="parent::*/@key"/>
            <xsl:apply-templates select="node()" mode="clean"/>
        </xsl:copy>
    </xsl:template>
    
    <!-- Transform the multiple variables inside an item of resizing block into an array -->
    <!-- 1. When encountering the first variables node, not copying it but putting it in an array and copying all other variables node -->
    <xsl:template match="*[@key='variables' and ancestor::*/@key='resizing'][1]" mode="clean">
        <fn:array key="variables">
            <xsl:for-each select="../fn:string[@key='variables']">
                <fn:string><xsl:value-of select="text()"/></fn:string>
            </xsl:for-each>
        </fn:array>
    </xsl:template>
    <!-- 2. When encountering variables node that is not the first, do nothing, the copy to array has already been done at previous step -->    
    <xsl:template match="*[@key='variables' and ancestor::*/@key='resizing' and preceding-sibling::*[@key='variables']]" mode="clean"/>

    <!-- Copy of the template for "variables" in resizing with "sizeForLinksVariables" for the pairwise component -->
    <!-- 1.  -->
    <xsl:template match="*[@key='sizeForLinksVariables' and ancestor::*/@key='resizing'][1]" mode="clean">
        <fn:array key="sizeForLinksVariables">
            <xsl:for-each select="../fn:string[@key='sizeForLinksVariables']">
                <fn:string><xsl:value-of select="text()"/></fn:string>
            </xsl:for-each>
        </fn:array>
    </xsl:template>
    <!-- 2.  -->
    <xsl:template match="*[@key='sizeForLinksVariables' and ancestor::*/@key='resizing' and preceding-sibling::*[@key='sizeForLinksVariables']]" mode="clean"/>

    <!-- Same thing for "linksVariables" (pairwise component) -->
    <!-- 1.  -->
    <xsl:template match="*[@key='linksVariables' and ancestor::*/@key='resizing'][1]" mode="clean">
        <fn:array key="linksVariables">
            <xsl:for-each select="../fn:string[@key='linksVariables']">
                <fn:string><xsl:value-of select="text()"/></fn:string>
            </xsl:for-each>
        </fn:array>
    </xsl:template>
    <!-- 2.  -->
    <xsl:template match="*[@key='linksVariables' and ancestor::*/@key='resizing' and preceding-sibling::*[@key='linksVariables']]" mode="clean"/>

    <!-- When encountering value node, do not copy (it is a line return wrongly treated as an object
    since we do not have a proper schema model for the resizing part...-->
    <xsl:template match="*[@key='value' and ancestor::*/@key='resizing']"  mode="clean"/>

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