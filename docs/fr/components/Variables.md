### Les variables

Les variables sont toutes externalisées dans un tableau `variables`.
Elles sont de trois types :

- `EXTERNAL` : variable externe à valoriser
- `COLLECTED` : référence à une variable collectée (issue des `response` dans les composants)
- `CALCULATED` : une formule qui définie la valeur de la variable

Strucure du tableau `variables` :

```json
[
  {
    "variableType": "EXTERNAL",
    "name": "LAST_BROADCAST",
    "label": "label"
  },
  {
    "variableType": "COLLECTED",
    "name": "FAVOURITE_CHARACTERS",
    "componentRef": "FAVOURITE_CHARACTERS_COMPONENT",
    "valueState" :
          [
            { "valueType" : "PREVIOUS", "value" : null },
            { "valueType" : "COLLECTED", "value" : null },
            { "valueType" : "FORCED", "value" : null },
            { "valueType" : "EDITED", "value" : null },
            { "valueType" : "INPUTED", "value" : null }
          ]
      },
  {
    "variableType": "CALCULATED",
    "name": "SUM_EXPENSES",
    "expression": "$PERCENTAGE_EXPENSES_1 + $PERCENTAGE_EXPENSES_2"
  }
]
```
