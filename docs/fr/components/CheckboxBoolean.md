### Le composant CheckboxBoolean

Il s'agit d'un composant ayant pour but de collecte une réponse de type booléen dans un input de type checkbox.
(case coché ou pas)

Il dispose de tous les attributs du composant de base avec aucun attribut supplémentaire.

Sa structure est la suivante :

```json=
{ 
  "id" : "j3343qhx",
  "componentType" : "CheckboxBoolean",
  "mandatory" : false,
  "label" : "label de la question",
  "declarations" : [...],
  "conditionFilter" : "#if((!( $READY != '1')))normal#{else}hidden#end",
  "response" : {...}
}
```