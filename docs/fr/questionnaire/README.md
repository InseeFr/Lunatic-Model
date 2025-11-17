# Le questionnaire Lunatic

## Structure du questionnaire

La structure d'un questionnaire Lunatic est la suivante : 

```json
{
  "id": "idQuestionnaire",
  "modele": "SIMPSONS",
  "enoCoreVersion": "...",
  "lunaticModelVersion": "x.y.z",
  "label": "Nom du questionnaire",
  "components": [...],
  "variables":[...]
}
```

- des métadonnées générales (identifiant du questionnaire, etc.)
- les composants
- les variables
- différents blocs d'optimisation (_cleaning_, _resizing_, etc.)

---

## Les composants

L'objet principal du modèle Lunatic est le _composant_. Le questionnaire lui-même est un composant.

La librairie Lunatic-Model implémente les différents composants [Lunatic](https://github.com/InseeFr/Lunatic).

Il existe de nombreux types de composants :

- le questionnaire
- les composants de structure (séquence et sous-séquence)
- le composant question
- les différents composants de réponse
- les composants d'itérations (boucle, rond-point)
- encore d'autres types de composant...

### Structure générale d'un composant

Tous les composants (excepté Sequence et Subsequence) ont au moins ces attributs :

```json
{
  "id": "id_du_composant",
  "componentType": "type_du_composant",
  "label": "label",
  "declarations": [...],
  "conditionFilter": {...}
}
```

- `id` : identifiant unique du composant
- `componentType` : définit le type du composant
- `label` : label de la question associée au composant (expression VTL)
- `declarations` : liste des déclarations éventuelles associées au composant.
- `conditionFilter` : filtre écrit dans le langage VTL pour savoir s'il faut afficher ou non le questionnaire.
- `isMandatory` : booléen qui définit si la réponse est obligatoire ou non

### Les déclarations

Il s'agit d'un tableau de déclarations

```json
{
  "declarations": [
    {
      "id": "id1",
      "declarationType": "STATEMENT",
      "position": "BEFORE_QUESTION_TEXT",
      "label": "\"Label de la declaration\""
    },
    {
      "id": "id2",
      "declarationType": "HELP",
      "position": "AFTER_QUESTION_TEXT",
      "label": "\"Label de la declaration\""
    }
  ]
}
```

- `declarationType` : "INSTRUCTION", "COMMENT", "HELP", "WARNING" ou "STATEMENT"
- `position` : "BEFORE_QUESTION_TEXT" ou "AFTER_QUESTION_TEXT"
- `label` : label de la déclarations

### Les réponses

Les composants qui ont pour but de collecter des réponses ont un attribut "response" ayant pour structure :

```json
{
  "response": {
    "name": "VARIABLE_NAME"
  }
}
```

L'attribut `name` faisant référence à l'attribut `name` d'une variable dans le tableau `variables` en fin de questionnaire.

### Documentation détaillée des composants

- [Sequence](./Sequence.md)
- [Subsequence](./Subsequence.md)
- [Input](./Input.md)
- [Textarea](./Textarea.md)
- [InputNumber](./InputNumber.md)
- [CheckboxBoolean](./CheckboxBoolean.md)
- [Datepicker](./Datepicker.md)
- [Les composants à options : Radio, Dropdown, CheckboxOne](./OptionsComponents.md)
- [CheckboxGroup](./CheckboxGroup.md)
- [FilterDescription](./FilterDescription.md)
- [Table](./Table.md)

---

## Les variables

[Les variables](./Variables.md)
