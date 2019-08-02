### Le composant CheckboxGroup

Il s'agit d'un composant ayant pour but de collecte plusieurs réponses de type booléen par l'intermédiare de plusieurs champ input de type checkbox.
(case coché ou pas)

Les différentes options sont définies grâce au tableau `responses` qui est un tableau d'objet `response` défini précédemment.

Sa structure est la suivante :

```json
{ 
  "id" : "j3343qhx",
  "componentType" : "CheckboxGroup",
  "mandatory" : false,
  "label" : "label de la question",
  "declarations" : [...],
  "conditionFilter" : "#if((!( $READY != '1')))normal#{else}hidden#end",
  "responses" : [...]
}
```