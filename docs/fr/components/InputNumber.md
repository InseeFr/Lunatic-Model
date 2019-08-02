### Le composant InputNumber

Il s'agit d'un composant ayant pour but de collecte une réponse de type nombre dans un input.

Il dispose de tous les attributs du composant de base avec comme attributs supplémentaires :

- `min` : qui définit la valeur maximale que l'utilisateur peut saisir
- `max` : qui définit la valeur minimale que l'utilisateur peut saisir
- `decimals` : qui définit le nombre décimales du nombre
- `unit` : qui définit l'unité du nombre collecté (optionnel)

Sa structure est la suivante :

```json
{
  "id" : "j3343qhx",
  "componentType" : "InputNumber",
  "mandatory" : false,
  "min" : 0,
  "max" : 10,
  "decimals" : 0,
  "unit" : "€"
  "label" : "label de la question",
  "declarations" : [...],
  "conditionFilter" : "#if((!( $READY != '1')))normal#{else}hidden#end",
  "response" : {...}
}
```
