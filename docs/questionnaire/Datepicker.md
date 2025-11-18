### Le composant `Datepicker`

Les dates ne sont pas encore bien prise en compte. (en attente de besoin métier)

Il s'agit d'un composant ayant pour but de collecte une date .
Il dispose de tous les attributs du composant de base avec un attribut `dateFormat` en plus qui définit le format de la date à collecter.

Sa structure est la suivante :

```json
{
      "id": "j334cyqb",
      "componentType": "Datepicker",
      "mandatory": false,
      "min": "1900-01-01",
      "max": "format-date(current-date(),'[Y0001]-[M01]-[D01]')", // temporaire (xpath)
      "label": "\"➡ 5. When was the first episode of the Simpsons?\"",
      "declarations": [...],
      "conditionFilter": "if ((not(cast(READY,integer) <>  1) )) then \"normal\" else \"hidden\"",
      "bindingDependencies": ["LAST_BROADCAST", "DATEFIRST"],
      "dateFormat": "YYYY-MM-DD",
      "response": { "name": "DATEFIRST" }
    }
```
