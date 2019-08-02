### Le composant Textarea

Il s'agit d'un composant ayant pour but de collecte une réponse de type texte dans une zone de texte `textarea` (html).
Il dispose de tous les attributs du composant de base avec un attribut `maxLength` en plus qui définit la longueur maximale du de la zone de texte.

Sa structure est la suivante :

```json=
{ 
  "id" : "j3343qhx",
  "componentType" : "Textarea",
  "mandatory" : false,
  "maxLength" : 250,
  "label" : "label de la question",
  "declarations" : [...],
  "conditionFilter" : "#if((!( $READY != '1')))normal#{else}hidden#end",
  "response" : {...}
}
```