# Les composants

La librairie Lunatic-Model définie un ensemble de composants [Lunatic](https://inseefr.github.io/Lunatic/storybook) au format JSON.

La différence entre le modèle hiérarchique et le modèle à plat est très faible :

Dans le modèle hiérarchique, les composants Sequence et Subsequence contiennent des également des composants.

Alors que dans le modèle à plat, les composants sont les uns à la suite des autres.

Dans cette documentation, seul le modèle à plat est présenté.

---

## Structure du questionnaire

La structure du questionnaire attendue est la suivante

```json
{
  "id":"idQuestionnaire",
  "label":"Nom du questionnaire",
  "components":[...],
  "variables":[...]
}
```

---

## Structure générale d'un composant

Tous les composants (excepté Sequence et Subsequence) ont au moins ces attributs :

```json
{
  "id":"id_du_composant",
  "componentType":"type_du_composant",
  "label":"label",
  "declarations":[...],
  "conditionFilter":"...",
  "mandatory":true
}
```

- `id` : identifiant unique du composant
- `componentType` : définit le type du composant
- `label` : label de la question associée au composant
- `declarations` : liste des déclarations éventuelles associées au composant.
- `conditionFilter` : filtre écrit dans le langage VTL pour savoir s'il faut afficher ou non le questionnaire.
- `mandatory` : booléen qui définit si la réponse est obligatoire ou non

---

### Les déclarations

Il s'agit d'un tableau de déclarations

```json
"declarations" :
  [
    {
      "id" : "id1",
      "declarationType" : "STATEMENT",
      "position" : "BEFORE_QUESTION_TEXT",
      "label" : "Label de la declaration"
    },
    {
      "id" : "id2",
      "declarationType" : "HELP",
      "position" : "AFTER_QUESTION_TEXT",
      "label" : "Label de la declaration"
    }
  ]
```

- `declarationType` : "INSTRUCTION", "COMMENT", "HELP", "WARNING" ou "STATEMENT"
- `position` : "BEFORE_QUESTION_TEXT" ou "AFTER_QUESTION_TEXT"
- `label` : label de la déclarations

---

### Les réponses

Les composants qui ont pour but de collecter des réponses ont un attribut "response" ayant pour structure :

```json
"response" :
  {
    "name" : "idVariable",
    "valueState" :
      [
        { "valueType" : "PREVIOUS", "value" : null },
        { "valueType" : "COLLECTED", "value" : null },
        { "valueType" : "FORCED", "value" : null },
        { "valueType" : "EDITED", "value" : null },
        { "valueType" : "INPUTED", "value" : null },
      ]
  }
```

L'attribut `name` faisant référence à l'attribut `responseRef` d'une variable dans le tableau `variables` en fin de questionnaire.
