### Le composant `CheckboxGroup`

Il s'agit d'un composant ayant pour but de collecter plusieurs réponses de type booléen par l'intermédiare de plusieurs champ input de type checkbox.

Les différentes options sont définies grâce au tableau `responses`.

Sa structure est la suivante :

```json
{
      "id": "j334akov",
      "componentType": "CheckboxGroup",
      "label": "\"➡ 1. What are the pet names that the Simpsons family had?\"",
      "declarations": [
        {
          "id": "j334akov-d5",
          "declarationType": "INSTRUCTION",
          "position": "AFTER_QUESTION_TEXT",
          "label": "\"Several possible answers\""
        }
      ],
      "conditionFilter": "if ((not(cast(READY,integer) <>  1) )) then \"normal\" else \"hidden\"",
      "bindingDependencies": ["PET1", "PET2", "PET3", "PET4"],
      "responses": [
        {
          "id": "j334akov-QOP-jbgd8qmn",
          "label": "\"Santa's Little Helper\"",
          "response": { "name": "PET1" }
        },

        {
          "id": "j334akov-QOP-jbgd60vl",
          "label": "\"Snowball I\"",
          "response": { "name": "PET2" }
        },

        {
          "id": "j334akov-QOP-jbgda2jo",
          "label": "\"Coltrane\"",
          "response": { "name": "PET3" }
        },

        {
          "id": "j334akov-QOP-jbgdfssy",
          "label": "\"Mojo the Helper Monkey\"",
          "response": { "name": "PET4" }
        }
      ]
    }
```
