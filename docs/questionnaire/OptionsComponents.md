## Les composants à options

Il existe 3 composants à options :

- le composant `Radio`
- le composant `Dropdown`
- le composant `CheckboxOne`

Les 3 sont identiques en terme de structure JSON, ils sont donc présentés ensemble. Seul diffère le comportement du composant Lunatic.
Ils peuvent tous les 3 collecter une unique réponse, celle séléctionnée.

Structure des composants à options:

```json
{
  "id" : "j6qdfhvw",
  "componentType" : "Radio / Dropdown / CheckboxOne",
  "mandatory" : false,
  "label": "\"➡ 2. Who is the Simpsons city mayor?\"",
  "declarations" : [... ],
  "conditionFilter" : "if ((not(cast(READY,integer) <>  1) )) then \"normal\" else \"hidden\"",
  "bindingDependencies": ["MAYOR"],
  "options": [
          { "value": "1", "label": "\"Constance Harm\"" },
  
          { "value": "2", "label": "\"Timothy Lovejoy\"" },
  
          { "value": "3", "label": "\"Joe Quimby\"" },
  
          { "value": "4", "label": "\"Poochie\"" }
  ],
  "response": { "name": "MAYOR" }
}
```

### Le composant `Radio`

Il correspond à un enchainement de champ HTML `<input type="radio">`

---

### Le composant `Dropdown`

Il correspond à un `<select>` en HTML.

---

### Le composant `CheckboxOne`

Il correspond à un enchainement de champ HTML `<input type="checkbox">`

---

Pour plus de visuel et pour mieux comprendre : [Storybook de Lunatic](https://inseefr.github.io/Lunatic/storybook/)
