### Le composant `FilterDescription`

Il s'agit d'un composant ayant pour but d'afficher une description d'un filtre.

Ce composant est plus léger que les autres car il a seulement les attributs: `id`, `componentType` et `label` du composant de base. Il dispose en revanche d'un attribut supplémentaire :

`management:true/false`

Il définit si le composant doit être affiché ou non, par défaut, il est à `false`.
Sa structure est la suivante :

```json
{
  "id": "j6p6my1d",
  "componentType": "FilterDescription",
  "management": false,
  "label": "If you are not ready, please go to the end of the questionnaire"
}
```
