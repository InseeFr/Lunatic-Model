### Le composant Input

Il s'agit d'un composant ayant pour but de collecte une réponse de type texte dans un input.
Il dispose de tous les attributs du composant de base avec un attribut `maxLength` en plus qui définit la longueur maximale du champ input.

Sa structure est la suivante :

```json
{
  "id" : "j3343qhx",
  "componentType" : "Input",
  "mandatory" : false,
  "maxLength" : 30,
  "label" : "label de la question",
  "declarations" : [...],
  "conditionFilter" : "#if((!( $READY != '1')))normal#{else}hidden#end",
  "response" : {...}
}
```
