# Java classes for the Lunatic Model

Java classes for the Lunatic questionnaire model.

This library offers serialization and deserialization for Lunatic json questionnaires.

Available on maven central.

```xml
<dependency>
  <groupId>fr.insee.lunatic</groupId>
  <artifactId>lunatic-model</artifactId>
  <version>x.y.z</version>
</dependency>
```

```kotlin
implementation("fr.insee.lunatic:lunatic-model:x.y.z")
```

## Implementation

The model is described by the Java classes. [Jackson](https://github.com/FasterXML/jackson) is used for serialization/deserialization.

## The Lunatic Model

Note that this project does not define the Lunatic model (although its name suggests it): 
[Lunatic](https://github.com/InseeFr/) does. Changes in the Lunatic questionnaire model are brought in 
Lunatic-Model in a second stage.

In Lunatic, classes are defined in the 
[type source typescript file](https://github.com/InseeFr/Lunatic/blob/3.0/src/type.source.ts).

## Documentation

Documentation can be found in the [docs](https://github.com/InseeFr/Lunatic-Model/tree/master/docs) folder and browsed [online](https://inseefr.github.io/Lunatic-Model).
