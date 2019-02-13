# Lunatic Model

## Structure du questionnaire au format json
```json
    {
      "id" : "id_du_questionnaire",
      "label" : "label(=titre) du questionnaire"
      "components": [...], /*liste_des_composants*/
      "codeLists" : [...], /*liste_des_codeLists*/
      "variables" : [...]  /*liste_des_variables_collectes/externes/calculées*/
   }
```


### Les codeLists
```json
  {
      "id" : "j334iumu",
      "label" : "TOWN",
      "code" : [ {
         "parent" : "",
         "value" : "00001",
         "label" : "Springfield"
      }, {
         "parent" : "",
         "value" : "00002",
         "label" : "Shelbyville"
      }, {
         "parent" : "",
         "value" : "00003",
         "label" : "Seinfeld"
      } ]
   }
```
### Les variables
Il y a plusieurs types de variables : externes, calculées ou collectées
```json
  {
      "name" : "LAST_BROADCAST",
      "label" : "LAST_BROADCAST label" /*label=variable_externe*/
   }, 
   {
      "name" : "COMMENT",
      "responseRef" : "COMMENT" /*responseRef=variable_collectées, 
                                  ref_au_'name'_de_l'objet_'reponse'_dans_un_component*/
   },
   {
      "name" : "SUM_EXPENSES",
      "value" : "$PERCENTAGE_EXPENSES11$ + $PERCENTAGE_EXPENSES21$ + $PERCENTAGE_EXPENSES31$ + $PERCENTAGE_EXPENSES41$ + $PERCENTAGE_EXPENSES51$ + $PERCENTAGE_EXPENSES61$ + $PERCENTAGE_EXPENSES71$ + $PERCENTAGE_EXPENSES81$ + $PERCENTAGE_EXPENSES91$ + $PERCENTAGE_EXPENSES101$"
   } /*value=variable_calculées,_on_peut_changer/supprimer_le_symbole_'$'*/
```

## Listes des Component

* Component ("interface"):
Tous les composant ont au moins ces attributs
```json
    {
      "id" : "id_du_composant",
      "page" : 1, /*numero_de_la_page_où_afficher_le_composant*/
      "type" : "type_du_composant",
      "label" : "label du composant",
      "response" : {...}, /*pour_les_composants_de_type_question_(pas_module_et_sous-module)*/
      "declaration" : [...]
      "conditionFilter" : "une_lambda_expression_au_format_string, ex :(READY) =>(!( READY !== '1')) ? 'normal' : 'hidden'"
   }
```

* response : 
```json       
      "response" : {
         "name" : "READY",
         "valueState" : [ {
            "type" : "PREVIOUS",
            "value" : ""
         }, {
            "type" : "COLLECTED",
            "value" : ""
         }, {
            "type" : "FORCED",
            "value" : ""
         }, {
            "type" : "EDITED",
            "value" : ""
         }, {
            "type" : "INPUTED",
            "value" : ""
         } ]
      }
```
* declaration :
``` json
    "declaration" : [ 
        {
          "id" : "id_declaration_1",
          "position" : "AFTER_QUESTION_TEXT", /*ou "BEFORE_QUESTION_TEXT"*/
          "label" : "label_de_la_declaration_1"
        },
        {
          "id" : "id_declaration_2",
          "position" : "AFTER_QUESTION_TEXT", /*ou "BEFORE_QUESTION_TEXT"*/
          "label" : "label_de_la_declaration_2"
        } 
      ]
```

* CheckboxBoolean 
 ```json 
    {
      "id" : "idCheckboxBoolean",
      "page" : 1,
      "type" : "CheckboxBoolean",
      "label" : "label de la question",
      "response" : {...},
      "declaration" : [...],
      "conditionFilter" : "..."
   }
```
* Dropdown / CheckboxOne / Radio :
``` json
    {
      "id" : "id_du_composant",
      "page" : 2,
      "type" : "Dropdown / CheckboxOne /Radio",
      "label" : "label de la question",
      "response" : {...},
      "declaration" : [...],
      "conditionFilter" : "...",
      "codeListReference" : "reference_a_la_liste_de_code_(codeLists)"
   }
```

* Input :
``` json
    {
      "id" : "id_du_composant",
      "page" : 2,
      "maxLength" : 30 /*longueur_max_du_champ_input*/
      "type" : "Input",
      "label" : "label de la question",
      "response" : {...},
      "declaration" : [...],
      "conditionFilter" : "..."
   }
```

* InputNumber :
``` json
    {
      "id" : "j6q9h8tj",
      "page" : 1,
      "min" : 0.0, /*minimum_autorise*/
      "max" : 99.0, /*maximume_autorise*/
      "decimals" : 0, /*nombre de decimales*/
      "type" : "InputNumber",
      "label" : "label de la question",
      "response" : {...},
      "declaration" : [...],
      "conditionFilter" : "...",
      "unit" : "unite_du_nombre" /*vaut_""_si_nombre sans_d'unité*/
   }
```


* Sequence / Subsequence :
``` json
  {
      "id" : "id_sequence",
      "page" : 2,
      "type" : "Sequence / Subseqeunce",
      "label" : "label de la sequence",
      "declaration" : [...],
      "conditionFilter" : "..."
   }
```

* Textarea
``` json
  {
      "id" : "id_textarea",
      "page" : 6,
      "maxLength" : 255,
      "type" : "Textarea",
      "label" : "label de la question",
      "response" : {...},
      "declaration" : [...],
      "conditionFilter" : "..."
   }
```
* Datepicker
``` json
  {
      "id" : "id_Datepicker",
      "page" : 1,
      "type" : "Datepicker",
      "label" : "label de la question",
      "response" : {...},
      "declaration" : [...],
      "conditionFilter" : "...",
      "dateFormat" : "jj/mm/aaaa" /*format_de_la_date_a_collecter*/
   }
  ```