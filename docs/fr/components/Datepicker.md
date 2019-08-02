### Le composant Datepicker

Il s'agit d'un composant ayant pour but de collecte une date .
Il dispose de tous les attributs du composant de base avec un attribut `dateFormat` en plus qui définit le format de la date à collecter.

Sa structure est la suivante :

```json
{
  "id" : "j3343qhx",
  "componentType" : "Datepicker",
  "mandatory" : false,
  "dateFormat" : "jj/mm/aaaa",
  "label" : "label de la question",
  "declarations" : [...],
  "conditionFilter" : "#if((!( $READY != '1')))normal#{else}hidden#end",
  "response" : {...}
}
```
