<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:h="http://xml.insee.fr/schema/applis/lunatic-h"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    version="2.0">
    
    <xsl:output indent="yes"/>
    
    <xsl:variable name="ResponseTypeEnum" select="'PREVIOUS,COLLECTED,FORCED,EDITED,INPUTED'" as="xs:string"/>
    
    <xsl:template match="/">
        <xs:schema 
            xmlns:xs="http://www.w3.org/2001/XMLSchema"
            elementFormDefault="qualified"
            attributeFormDefault="unqualified">
            <xs:element name="Data">
                <xs:complexType>
                    <xs:all>
                        <xs:element name="COLLECTED" minOccurs="0" maxOccurs="1">
                            <xs:complexType>
                                <xs:all>
                                    <xsl:apply-templates select="//h:variables[@variableType='COLLECTED']"/>
                                </xs:all>
                            </xs:complexType>
                        </xs:element>
                        <xs:element name="EXTERNAL" minOccurs="0" maxOccurs="1">
                            <xs:complexType>
                                <xs:all>
                                    <xsl:apply-templates select="//h:variables[@variableType='EXTERNAL']"/>
                                </xs:all>
                            </xs:complexType>
                        </xs:element>
                        <xs:element name="CALCULATED" minOccurs="0" maxOccurs="1">
                            <xs:complexType>
                                <xs:all>
                                    <xsl:apply-templates select="//h:variables[@variableType='CALCULATED']"/>
                                </xs:all>
                            </xs:complexType>
                        </xs:element>
                    </xs:all>
                </xs:complexType>
            </xs:element>

            <xs:complexType name="VALUE">
                <xs:simpleContent>
                    <xs:extension base="xs:string">
                        <xs:attribute type="xs:string" name="type"/>
                    </xs:extension>
                </xs:simpleContent>
            </xs:complexType>
            
            <xs:simpleType name="TypeEnum">
                <xs:restriction base="xs:token">
                    <xs:enumeration value="null"/>
                    <xs:enumeration value="string"/>
                    <xs:enumeration value="number"/>
                </xs:restriction>
            </xs:simpleType>
            
        </xs:schema>
    </xsl:template>
    
    <xsl:template match="h:variables[@variableType='COLLECTED']">
        <xsl:variable name="name" select="h:name"/>
        <xsl:variable name="valuesType" select="@xsi:type"/>
        <xsl:variable name="context" select="."/>
        <xs:element name="{$name}" minOccurs="0" maxOccurs="1">
            <xs:complexType>
                <xs:all>
                    <xsl:choose>
                        <xsl:when test="contains($valuesType,'Array')">
                            <xsl:for-each select="tokenize($ResponseTypeEnum,',')">
                                <xsl:variable name="currentType" select="."/>
                                <xs:element name="{.}" minOccurs="0" maxOccurs="1">
                                    <xs:complexType>
                                        <xs:sequence>                                            
                                            <xsl:apply-templates select="$context/h:values/h:*[local-name(.)=$currentType]/*"/>
                                        </xs:sequence>
                                    </xs:complexType>                                    
                                </xs:element>
                                
                            </xsl:for-each>                                                        
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:for-each select="tokenize($ResponseTypeEnum,',')">
                                <xs:element name="{.}" type="VALUE" minOccurs="0" maxOccurs="1"/>
                            </xsl:for-each>
                        </xsl:otherwise>
                    </xsl:choose>                    
                </xs:all>
            </xs:complexType>
            
        </xs:element>
    </xsl:template>
    
    <xsl:template match="h:*[local-name(.)=('PREVIOUS','COLLECTED','FORCED','EDITED','INPUTED')]">
        <xsl:variable name="type" select="@xsi:type"/>
        <xsl:variable name="name" select="local-name(.)"/>
        
        <xsl:choose>
            <xsl:when test="contains($type,'Array')">
                <xs:element name="{$name}" minOccurs="0" maxOccurs="unbounded">
                    <xs:complexType>
                        <xs:sequence>
                            <xsl:apply-templates select="h:*[local-name(.)=$name]"/>
                        </xs:sequence>
                    </xs:complexType>
                </xs:element>
            </xsl:when>
            <xsl:otherwise>
                <xs:element name="{$name}" type="VALUE" minOccurs="0" maxOccurs="unbounded"/>
            </xsl:otherwise>
        </xsl:choose>
        
    </xsl:template>
    
    <xsl:template match="h:variables[@variableType='EXTERNAL']">
        <xsl:variable name="name" select="h:name"/>
        <xsl:variable name="valuesType" select="@xsi:type"/>
        <xsl:choose>
            <xsl:when test="contains($valuesType,'Array')">
                <xsl:variable name="currentType" select="."/>
                <xs:element name="{$name}" minOccurs="0" maxOccurs="1">
                    <xs:complexType>
                        <xs:sequence>
                            <xsl:apply-templates select="h:value/*">
                                <xsl:with-param name="name" select="$name" tunnel="yes"/>
                            </xsl:apply-templates>
                        </xs:sequence>
                    </xs:complexType>                                    
                </xs:element>                                       
            </xsl:when>
            <xsl:otherwise>
                <xs:element name="{$name}" type="VALUE" minOccurs="0" maxOccurs="1"/>                
            </xsl:otherwise>
        </xsl:choose>
        
    </xsl:template>

    <xsl:template match="h:variables[@variableType='CALCULATED']">
        <xsl:variable name="name" select="h:name"/>
        <xsl:variable name="valuesType" select="@xsi:type"/>
        <xs:element name="{$name}" type="VALUE" minOccurs="0" maxOccurs="1"/>
    </xsl:template>
    
    <xsl:template match="h:value">
        <xsl:param name="name" tunnel="yes"/>
        <xsl:variable name="type" select="@xsi:type"/>
        <xsl:choose>
            <xsl:when test="contains($type,'Array')">
                <xs:element name="{$name}" minOccurs="0" maxOccurs="unbounded">
                    <xs:complexType>
                        <xs:sequence>
                            <xsl:apply-templates select="h:value"/>
                        </xs:sequence>
                    </xs:complexType>
                </xs:element>
            </xsl:when>
            <xsl:otherwise>
                <xs:element name="{$name}" type="VALUE" minOccurs="0" maxOccurs="unbounded"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
</xsl:stylesheet>