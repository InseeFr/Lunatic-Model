<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:xs="http://www.w3.org/2001/XMLSchema" 
	xmlns:fn="http://www.w3.org/2005/xpath-functions" 
	xmlns:xd="http://www.oxygenxml.com/ns/doc/xsl"
	xmlns="http://xml.insee.fr/schema/applis/lunatic"
	
	exclude-result-prefixes="xs fn xd" version="2.0">	
	
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
	
	<xsl:template match="Questionnaire">
		<Questionnaire 
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://xml.insee.fr/schema/applis/lunatic file:/src/main/resources/xsd/Questionnaire.xsd"
			id="{@id}">
			<xsl:apply-templates select="label"/>
			<xsl:apply-templates select="component"/>
			<xsl:apply-templates select="descendant::codeLists"/>
			<xsl:apply-templates select="variable"/>
		</Questionnaire>
	</xsl:template>
	
	<xsl:template match="component[@xsi:type='Sequence']">
		<components xsi:type="{@xsi:type}" id="{@id}" page="{@page}">
			<xsl:apply-templates select="label"/>
			<xsl:apply-templates select="declaration"/>
			<xsl:apply-templates select="conditionFilter"/>
		</components>
		<xsl:apply-templates select="component"/>
	</xsl:template>
	
	<xsl:template match="component[@xsi:type='Subsequence']">
		<components xsi:type="{@xsi:type}" id="{@id}" page="{@page}">
			<xsl:apply-templates select="label"/>
			<xsl:apply-templates select="declaration"/>
			<xsl:apply-templates select="conditionFilter"/>
		</components>
		<xsl:apply-templates select="component"/>
	</xsl:template>
	
	<xsl:template match="component">
		<components>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</components>
	</xsl:template>
	
	<xsl:template match="label">
		<label><xsl:value-of select="."/></label>
	</xsl:template>
	
	<xsl:template match="value">
		<value><xsl:value-of select="."/></value>
	</xsl:template>
	
	<xsl:template match="unit">
		<unit><xsl:value-of select="."/></unit>
	</xsl:template>
	
	<xsl:template match="dateFormat">
		<dateFormat><xsl:value-of select="."/></dateFormat>
	</xsl:template>
	
	<xsl:template match="conditionFilter">
		<conditionFilter><xsl:value-of select="normalize-space(.)"/></conditionFilter>
	</xsl:template>
	
	<xsl:template match="declaration">
		<declaration declarationType="{@declarationType}" id="{@id}" position="{@position}">
			<xsl:apply-templates select="label"/>
		</declaration>
	</xsl:template>
	
	<xsl:template match="component[@xsi:type='Radio'] | component[@xsi:type='Dropdown'] | component[@xsi:type='CheckboxOne']">
		<components xsi:type="{@xsi:type}" id="{@id}" page="{@page}">
			<xsl:apply-templates select="label"/>
			<xsl:apply-templates select="declaration"/>
			<xsl:apply-templates select="response"/>
			<xsl:apply-templates select="conditionFilter"/>
			<codeListReference>
				<xsl:value-of select="codeLists/@id"/>
			</codeListReference>
		</components>
	</xsl:template>
	
	<xsl:template match="component[@xsi:type='InputNumber']">
		<components>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates select="label"/>
			<xsl:apply-templates select="declaration"/>
			<xsl:apply-templates select="response"/>
			<xsl:apply-templates select="conditionFilter"/>
			<xsl:apply-templates select="unit"/>
		</components>
	</xsl:template>
	
	<xsl:template match="response">
		<response name="{@name}">
			<xsl:apply-templates  select="valueState"/>
		</response>
	</xsl:template>
	
	<xsl:template match="valueState">
		<valueState type="{@type}">
			<xsl:apply-templates  select="value"/>
		</valueState>
	</xsl:template>
	
	<xsl:template match="codeLists">
		<codeLists id="{@id}">
			<xsl:apply-templates/>
		</codeLists>
	</xsl:template>	
	
	<xsl:template match="code">
		<code>
			<parent><xsl:value-of select="parent"/></parent>
			<xsl:apply-templates  select="value"/>
			<xsl:apply-templates select="label"/>
		</code>
	</xsl:template>	
	
	<xsl:template match="variable">
		<xsl:variable name="value" select="value"/>
		<xsl:variable name="responseRef" select="responseRef"/>		
		<variables>
			<name><xsl:value-of select="name"/></name>
			<xsl:choose>
				<xsl:when test="$value!=''">
					<xsl:apply-templates  select="$value"/>
				</xsl:when>
				<xsl:when test="$responseRef!=''">
					<responseRef><xsl:value-of select="$responseRef"/></responseRef>
				</xsl:when>
			</xsl:choose>
			<xsl:apply-templates select="label"/>
		</variables>
	</xsl:template>
</xsl:stylesheet>