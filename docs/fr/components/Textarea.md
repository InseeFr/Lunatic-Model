### Le composant `Textarea`

Il s'agit d'un composant ayant pour but de collecter une réponse de type texte dans une zone de texte `<textarea>` (HTML).
Il dispose de tous les attributs du composant de base avec un attribut `maxLength` en plus qui définit la longueur maximale de la zone de texte.

Sa structure est la suivante :

```json
{
  "id" : "j3343qhx",
  "componentType" : "Textarea",
  "mandatory" : false,
  "maxLength" : 250,
  "label" : "\"label de la question\"",
  "declarations" : [...],
  "conditionFilter" : "if ((not(cast(READY,integer) <>  1) )) then \"normal\" else \"hidden\"",
  "response" : {...}
}
```
