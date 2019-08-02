# Fonctionnement de Lunatic-Model

Le principe de cette librairie java est de transformer un questionnaire au format XML (en sortie d'ENO ddi2js) en un questionnaire au format JSON.


## Schéma xsd

Il existe deux modèles de questionnaire :
- Lunatic (LunaticModel.xsd)
- LunaticFlat (LunaticModelFlat.xsd)

Les modèles sont définis par des schémas XML dans les fichiers XSD. 
Ces schémas sont situés dans le dossier : `src/main/resources/xsd`.
Ils valident et assurent la bonne structure du fichier XML attendu en entrée des transformations.

Ces schémas sont transformés en classes Java par le compilateur JAXB.

Ces classes permettent ensuite de serialiser en JSON le questionnaire préalablement défini au format XML.

## Pilotage par maven
Une phase `generate-sources` définie dans le fichier pom.xml permet de générer les classes JAXB en s'appuyant sur les schémas XSD.

## Transformations
Lunatic-Model fonctionne avec les transformations unitaires suivantes :
- `XMLLunaticToJSONLunaticTranslator`
- `XMLLunaticFlatToJSONLunaticFlatTranslator`
- `XMLLunaticToXMLLunaticFlatTranslator`
- `JSONCleaner`

Ces quatre transformations sont nécessaires pour obtenir les deux types de sorties possibles :
- le questionnaire hiérarchique au format JSON
- le questionnaire à plat au format JSON

### Transformations XSLT
Les transformations `XMLLunaticToXMLLunaticFlatTranslator` et `JSONCleaner` sont des transformations de type XSLT assurées par Saxon.
Elles s'appuient respectivement sur les fichiers `src/main/resources/xslt/flattener.xsl` et `src/main/resources/xslt/json-cleaner.xsl`

### Transformations JAXB
Les transformations `XMLLunaticToJSONLunaticTranslator` et `XMLLunaticFlatToJSONLunaticFlatTranslator` sont assurées par JAXB. Il s'agit d'un sérialisation d'un fichier XML au format JSON en utilisant un schéma XSD.
Elles s'appuient sur des `properties` définies dans le dossier `src/main/resources/jaxb`.