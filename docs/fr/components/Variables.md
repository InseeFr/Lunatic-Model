### Les variables

Les variables sont toutes externalisées dans un tableau `variables`.
Elles sont de trois types :

- `EXTERNAL` : variable externe à valoriser
- `COLLECTED` : référence à une variable collectée (issue des `response` dans les composants)
- `CALCULATED` : une formule qui définie la valeur de la variables

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
    "responseRef": "FAVOURITE_CHARACTERS"
  },
  {
    "variableType": "CALCULATED",
    "name": "SUM_EXPENSES",
    "value": "$PERCENTAGE_EXPENSES_1 + $PERCENTAGE_EXPENSES_2"
  }
]
```
