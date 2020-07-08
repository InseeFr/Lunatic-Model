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
			<xsl:apply-templates/>
		</Questionnaire>
	</xsl:template>
	
	<xsl:template match="h:components[@xsi:type='Sequence' or @xsi:type='Subsequence']">
		<components>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates select="*[not(self::h:components)]"/>
		</components>
		<xsl:apply-templates select="h:components"/>
	</xsl:template>
	
	<xsl:template match="h:*">
		<xsl:element name="{local-name(.)}">
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>