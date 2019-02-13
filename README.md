# Lunatic Model

Classes and converters for the Lunatic questionnaire model.

## Introduction

The Lunatic questionnaire model is specified as an XML Schema in the `Questionnaire.xsd` file.

The schema is transformed into Java classes with the JAXB compiler. The JAXB classes are then packaged with other components to produce a REST web service and an XQuery module ready to be installed in eXist.

The JAXB compilation and the packaging tasks are executed by Maven.

The "Lunatic model" project contains the schema, the support classes for creating the web service and the XQuery module, and other useful classes like conversion tools and classes for generating mock questionnaires.

## Changing the model (short story)

Changing the model is done in two steps, that can also be realized in an IDE like Eclipse:
* modification of the schema
  * edit `src/main/resources/xsd/Questionnaire.xsd` to make the desired changes,
  * save the file.
* production of the output artifacts
  * open a command line at the project root and run `mvn package -DskipTests`
  * the rest service war file and XQuery module jar file should be produced in the `target` folder.

## Changing the model (longer story)

Most of the times, the simple procedure described above will result in a Maven build failure at the compilation step. This is because the changes on the model have to be reflected in the tools that use the generated JAXB classes corresponding to the model. For example, the mock classes use the setters of the JAXB objects, and the names of those setter methods are generated from the names of the elements and attributes in the schema.

Under Eclipse or equivalent, running the `generate-sources` Maven goal will show the compilation errors that are then easy to correct. If you just have the command line, you will have to run `mvn compile` and correct the errors reported one by one.

So the slightly longer story for changing the model is:

* modification of the schema
  * edit `src/main/resources/xsd/Questionnaire.xsd` to make the desired changes,
  * change the impacted Java classes accordingly
  * save the files.
* production of the output artifacts
  * open a command line at the project root and run `mvn package -DskipTests`
  * the rest service war file and XQuery module jar file should be produced in the `target` folder.

## Structure of the questionnaire in json format
List of attributes :
* id : id of the questionnaire,
* label : label (title) of the questionnaire
* components : list of components
* codeLists : list of codeLists
* variables : list of variables
```json
    {
      "id" : "id_of_questionnaire",
      "label" : "label of questionnaire",
      "components": [...], 
      "codeLists" : [...], 
      "variables" : [...]  
   }
```


### The codeLists
```json
  {
      "id" : "id",
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
### The variables
There are several types of variables: external, calculated or collected.
* external variable : there is a 'label' attribute
* calculated variable : there is a 'responseRef' attribute, refer to 'name' attribute in 'response' object in a component
* collected variable : there is a 'value' attribute
```json
  {
      "name" : "EXTERNAL_VAR",
      "label" : "EXTERNAL_VAR label"
   }, 
   {
      "name" : "COLLECTED_VAR",
      "responseRef" : "COLLECTED_VAR"
   },
   {
      "name" : "CALCULATED_VAR",
      "value" : "$PERCENTAGE_EXPENSES11$ + $PERCENTAGE_EXPENSES21$ + $PERCENTAGE_EXPENSES31$ + $PERCENTAGE_EXPENSES41$ + $PERCENTAGE_EXPENSES51$ + $PERCENTAGE_EXPENSES61$ + $PERCENTAGE_EXPENSES71$ + $PERCENTAGE_EXPENSES81$ + $PERCENTAGE_EXPENSES91$ + $PERCENTAGE_EXPENSES101$"
   }
```

## List of Components

* Component ("interface"):
All components have at least these attributes :
    * id : id of the component
    * page : number of the page where to display the component
    * type : component type
    * label : label
    * response : for question-type components only (not Sequence and Subsequence)
    * declaration
    * conditionFilter : an lambda expression in string format executable in JavaScript, ex : `(READY) =>(!( READY !== '1')) ? 'normal' : 'hidden'`
    
```json
    {
      "id" : "id_of_component",
      "page" : 1,
      "type" : "type_du_composant",
      "label" : "label",
      "response" : {...},
      "declaration" : [...],
      "conditionFilter" : "expression"
   }
```

* response :
It contains all the name of the collected response and refer to the name of variable in list of variables.
It has has several possible state : PREVIOUS, COLLECTED, FORCED or EDITED.
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
    * it's a table with declartion elements with an id, a position (compare to the label of the question) and its label.
``` json
    "declaration" : [ 
        {
          "id" : "id_1",
          "position" : "AFTER_QUESTION_TEXT",
          "label" : "label_1"
        },
        {
          "id" : "id_2",
          "position" : "AFTER_QUESTION_TEXT",
          "label" : "label_2"
        } 
      ]
```

* CheckboxBoolean 
 ```json 
    {
      "id" : "id",
      "page" : 1,
      "type" : "CheckboxBoolean",
      "label" : "question label",
      "response" : {...},
      "declaration" : [...],
      "conditionFilter" : "..."
   }
```
* Dropdown / CheckboxOne / Radio :
    * codeListReference : reference to the code list (codeLists)
``` json
    {
      "id" : "id",
      "page" : 2,
      "type" : "Dropdown / CheckboxOne /Radio",
      "label" : "question label",
      "response" : {...},
      "declaration" : [...],
      "conditionFilter" : "...",
      "codeListReference" : "idCodeLists"
   }
```

* Input/Textarea :
    * maxLength : maximum number of characters allowed
``` json
    {
      "id" : "id",
      "page" : 2,
      "maxLength" : 30,
      "type" : "Input / Textarea",
      "label" : "question label",
      "response" : {...},
      "declaration" : [...],
      "conditionFilter" : "..."
   }
```

* InputNumber :
    * min : minimum allowed
    * max : maximum allowed
    * decimals : number of decimals for the number
    * unit : the unit of the number
``` json
    {
      "id" : "id",
      "page" : 1,
      "min" : 0.0,
      "max" : 99.0,
      "decimals" : 1,
      "type" : "InputNumber",
      "label" : "question label",
      "response" : {...},
      "declaration" : [...],
      "conditionFilter" : "...",
      "unit" : "cm"
   }
```


* Sequence / Subsequence :
``` json
  {
      "id" : "id",
      "page" : 2,
      "type" : "Sequence / Subseqeunce",
      "label" : "label de la sequence",
      "declaration" : [...],
      "conditionFilter" : "..."
   }
```

* Datepicker
    * dateFormat : the format of the date to be collected
``` json
  {
      "id" : "id",
      "page" : 1,
      "type" : "Datepicker",
      "label" : "question label",
      "response" : {...},
      "declaration" : [...],
      "conditionFilter" : "...",
      "dateFormat" : "jj/mm/aaaa"
   }
  ```
