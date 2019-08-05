# Comment utiliser Lunatic-Model

## Installation

Il s'agit d'un librairie java utilisant Maven.
Ce projet n'est pour l'instant pas publié sur les `repository` de Maven.

Pour l'utiliser/installer veuillez suivre les étapes suivantes :

- cloner le dépôt : `git clone https://github.com/InseeFr/Lunatic-Model.git`
- lancer l'installation : - se placer dans le dossier Lunatic-Model - `mvn clean install -DskipTests`
- intégrer la dépendances dans votre projet maven

```xml
<dependency>
  <groupId>fr.insee.lunatic</groupId>
  <artifactId>lunatic-model</artifactId>
  <version>1.0.0</version>
</dependency>
```

---

## Transformations

Il existe 2 transformations possibles :

1. XMLLunatic (sortie d'Eno ddi2js) vers JSONLunatic (sortie hiérarchique)
2. XMLLunatic (sortie d'Eno ddi2js) vers JSONLunaticFlat (sortie à plat)

Pour utiliser ces transformations, il faut utiliser dans un ordre précis les transformations unitaires:

---

### Transformation 1 (XMLLunatic vers JSONLunatic hiérarchique)

En entrée de la transformation, il possible d'avoir:

- un fichier xml `java.io.File`
- un `String` répresentant le xml
- un type `StreamSource` (`javax.xml.transform.stream.StreamSource`)

Le fichier en entrée doit être issue du générateur de questionnaire Eno (ddi2js).

En sortie : un json au format `String`.

Transformation :

```java
XMLLunaticToJSONLunaticTranslator translator = new XMLLunaticToJSONLunaticTranslator();
JSONCleaner jsonCleaner = new JSONCleaner();
String jsonQuestionnaire = jsonCleaner.clean(translator.translate(in));
```

---

### Transformation 2 (XMLLunatic vers JSONLunaticFlat à plat)

En entrée de la transformation, il possible d'avoir:

- un fichier xml (`java.io.File`)
- un `String` répresentant le xml
- un type `InputStream` (`java.io.InputStream`)

Le fichier en entrée doit être issue du générateur de questionnaire Eno (ddi2js).

En sortie : un json au format `String`.

Transformation :

```java
XMLLunaticToXMLLunaticFlatTranslator translator = new XMLLunaticToXMLLunaticFlatTranslator();
XMLLunaticFlatToJSONLunaticFlatTranslator translator2 = new XMLLunaticFlatToJSONLunaticFlatTranslator();
JSONCleaner jsonCleaner = new JSONCleaner();
String jsonQuestionnaire = jsonCleaner.clean(translator2.translate(translator.generate(in)));
```
