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
    "values": {
            "PREVIOUS": null,
            "COLLECTED": null,
            "FORCED": null,
            "EDITED": null,
            "INPUTED": null
    }
  },
  {
    "variableType": "CALCULATED",
    "name": "SUM_EXPENSES",
    "expression": "cast(PERCENTAGE_EXPENSES11,number) + cast(PERCENTAGE_EXPENSES21,number)" // expression VTL
    "bindingDependencies": ["PERCENTAGE_EXPENSES11", "PERCENTAGE_EXPENSES21"]
  }
]
```

- `bindingDependencies` : liste des variables impliquées dans le calcul d'une variable calculée.
