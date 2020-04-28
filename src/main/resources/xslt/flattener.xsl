<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:xs="http://www.w3.org/2001/XMLSchema" 
	xmlns:fn="http://www.w3.org/2005/xpath-functions" 
	xmlns:xd="http://www.oxygenxml.com/ns/doc/xsl"
	xmlns:h="http://xml.insee.fr/schema/applis/lunatic-h"
	xmlns="http://xml.insee.fr/schema/applis/lunatic"
	
	exclude-result-prefixes="xs fn xd h" version="2.0">	
	
	<xsl:output indent="yes"/>
	
	<xd:doc scope="stylesheet">
		<xd:desc>
			<xd:p>An xslt stylesheet who transforms an input into js through generic driver templates.</xd:p>
			<xd:p>The real input is mapped with the drivers.</xd:p>
		</xd:desc>
	</xd:doc>
	
	<xd:doc>
		<xd:desc>
			<xd:p>Match on Form driver.</xd:p>
			<xd:p>It writes the root of the document with the main title.</xd:p>
		</xd:desc>
	</xd:doc>
	
	<xsl:template match="h:Questionnaire">
		<Questionnaire>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates select="h:label"/>
			<xsl:apply-templates select="h:components"/>
			<xsl:apply-templates select="h:variables"/>
		</Questionnaire>
	</xsl:template>
	
	<xsl:template match="h:components[@xsi:type='Sequence' or @xsi:type='Subsequence']">
		<components>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates select="h:label"/>
			<xsl:apply-templates select="h:declarations"/>
			<xsl:apply-templates select="h:conditionFilter"/>
		</components>
		<xsl:apply-templates select="h:components"/>
	</xsl:template>
	
	<xsl:template match="h:components">
		<components>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</components>
	</xsl:template>
	
	<xsl:template match="h:label">
		<label><xsl:value-of select="."/></label>
	</xsl:template>
	
	<xsl:template match="h:value">
		<value>
			<xsl:copy-of select="@*"/>
			<xsl:value-of select="."/>
		</value>
	</xsl:template>
	
	<xsl:template match="h:unit">
		<unit><xsl:value-of select="."/></unit>
	</xsl:template>
	
	<xsl:template match="h:lines">
		<lines><xsl:copy-of select="@*"/></lines>
	</xsl:template>	
		
	<xsl:template match="h:dateFormat">
		<dateFormat><xsl:value-of select="."/></dateFormat>
	</xsl:template>
	
	<xsl:template match="h:conditionFilter">
		<conditionFilter><xsl:value-of select="normalize-space(.)"/></conditionFilter>
	</xsl:template>
	
	<xsl:template match="h:declarations">
		<declarations>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates select="h:label"/>
		</declarations>
	</xsl:template>
	
	<xsl:template match="h:components[@xsi:type='Radio'] | h:components[@xsi:type='Dropdown'] | h:components[@xsi:type='CheckboxOne']">
		<components>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates />
		</components>
	</xsl:template>
	
	<xsl:template match="h:components[@xsi:type='Table']">
		<components>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</components>
	</xsl:template>
	
	<xsl:template match="h:columns">
		<columns>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</columns>
	</xsl:template>
	
	<xsl:template match="h:components[@xsi:type='CheckboxGroup']">
		<components>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</components>
	</xsl:template>
	
	<xsl:template match="h:components[@xsi:type='InputNumber']">
		<components>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</components>
	</xsl:template>
	
	<xsl:template match="h:response">
		<response>
			<xsl:copy-of select="@*"/>
		</response>
	</xsl:template>
	
	<xsl:template match="h:cells">
		<cells>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates />
		</cells>
	</xsl:template>
	
	
	<xsl:template match="h:valueState">
		<valueState>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</valueState>
	</xsl:template>
	
	<xsl:template match="h:codeLists">
		<codeLists>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</codeLists>
	</xsl:template>	
	
	<xsl:template match="h:options">
		<options>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</options>
	</xsl:template>
	
	<xsl:template match="h:responses">
		<responses>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</responses>
	</xsl:template>
	
	<xsl:template match="h:codes">
		<codes>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</codes>
	</xsl:template>	
	
	<xsl:template match="h:variables">
		<xsl:variable name="responseRef" select="h:responseRef"/>
		<xsl:variable name="expression" select="h:expression"/>
		<variables>
			<xsl:copy-of select="@*"/>
			<name><xsl:value-of select="h:name"/></name>
			<xsl:choose>
				<xsl:when test="$responseRef!=''">
					<responseRef><xsl:value-of select="$responseRef"/></responseRef>
				</xsl:when>
				<xsl:when test="$expression!=''">
					<expression><xsl:value-of select="normalize-space($expression)"/></expression>
				</xsl:when>
			</xsl:choose>
			<xsl:apply-templates select="h:value"/>
			<xsl:apply-templates select="h:valueState"/>
		</variables>
	</xsl:template>
</xsl:stylesheet>