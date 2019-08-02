### Les composants à options

Il existe 3 composants à options :

- le composant Radio
- le composant Dropdown
- le composant CheckboxOne

Les 3 sont identiques en terme de structure JSON, ils sont donc présentés ensemble. Seule diffère le comportement du composant Lunatic.
Ils peuvent tous les 3 collectés une unique réponse, celle séléctionnée.

Structure des composants à options:

```json
{
  "id" : "j6qdfhvw",
  "componentType" : "Radio / Dropdown / CheckboxOne",
  "mandatory" : false,
  "label" : "label de la question",
  "declarations" : [... ],
  "conditionFilter" : "#if((!( $READY != '1')))normal#{else}hidden#end",
  "response" : { ... },
  "options" :
    [
      { "value" : "1", "label" : "Constance Harm" },
      { "value" : "2", "label" : "Timothy Lovejoy" },
      { "value" : "3", "label" : "Joe Quimby" },
      { "value" : "4", "label" : "Poochie" }
    ]
}
```

#### Le composant Radio

Il correspond à un enchainement de champ html `<input type="radio">`

#### Le composant Dropdown

Il correspond à un `<select>` en html.

#### Le composant CheckboxOne

Il correspond à un enchainement de champ html `<input type="checkbox">`

Pour plus de visuel : [Storybook de Lunatic](https://inseefr.github.io/Lunatic/storybook/)
