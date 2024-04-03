# Les données

## Données au format JSON

```json
{
  "COLLECTED": {
    "variable1": {
      "PREVIOUS": "Previous",
      "COLLECTED": "Collected",
      "FORCED": null,
      "EDITED": 34,
      "INPUTTED": "Inputted"
    },
    "variable2": {
      "PREVIOUS": ["previous individu1", "previous individu2"],
      "COLLECTED": ["individu1", "individu2"]
    },
    "variable3": {
      "PREVIOUS": [
        ["previous individu1", "previous individu2"],
        ["previous individu3", "previous individu4"]
      ],
      "COLLECTED": [
        ["individu1", "individu2"],
        ["individu3", "individu4"]
      ]
    },
    "variable4": {
      "COLLECTED": [
        [
          [true, true],
          ["individu3", "individu4"]
        ],
        [
          ["individu5", "individu6"],
          ["individu7", "individu8"]
        ]
      ]
    }
  },
  "EXTERNAL": {
    "variableExt1": ["External var", "External var2"],
    "variableExt2": "External var2"
  }
}
```

## Donnéee équivalente au format XML

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Data>
   <COLLECTED>
      <variable1>
         <PREVIOUS type="string">Previous</PREVIOUS>
         <COLLECTED type="string">Collected</COLLECTED>
         <FORCED type="null"/>
         <EDITED type="number">34</EDITED>
         <INPUTTED type="string">Inputted</INPUTTED>
      </variable1>
      <variable2>
         <PREVIOUS>
            <PREVIOUS type="string">previous individu1</PREVIOUS>
            <PREVIOUS type="string">previous individu2</PREVIOUS>
         </PREVIOUS>
         <COLLECTED>
            <COLLECTED type="string">individu1</COLLECTED>
            <COLLECTED type="string">individu2</COLLECTED>
         </COLLECTED>
      </variable2>
      <variable3>
         <PREVIOUS>
            <PREVIOUS>
               <PREVIOUS type="string">previous individu1</PREVIOUS>
               <PREVIOUS type="string">previous individu2</PREVIOUS>
            </PREVIOUS>
            <PREVIOUS>
               <PREVIOUS type="string">previous individu3</PREVIOUS>
               <PREVIOUS type="string">previous individu4</PREVIOUS>
            </PREVIOUS>
         </PREVIOUS>
         <COLLECTED>
            <COLLECTED>
               <COLLECTED type="string">individu1</COLLECTED>
               <COLLECTED type="string">individu2</COLLECTED>
            </COLLECTED>
            <COLLECTED>
               <COLLECTED type="string">individu3</COLLECTED>
               <COLLECTED type="string">individu4</COLLECTED>
            </COLLECTED>
         </COLLECTED>
      </variable3>
      <variable4>
         <COLLECTED>
            <COLLECTED>
               <COLLECTED>
                  <COLLECTED type="boolean">true</COLLECTED>
                  <COLLECTED type="boolean">true</COLLECTED>
               </COLLECTED>
               <COLLECTED>
                  <COLLECTED type="string">individu3</COLLECTED>
                  <COLLECTED type="string">individu4</COLLECTED>
               </COLLECTED>
            </COLLECTED>
            <COLLECTED>
               <COLLECTED>
                  <COLLECTED type="string">individu5</COLLECTED>
                  <COLLECTED type="string">individu6</COLLECTED>
               </COLLECTED>
               <COLLECTED>
                  <COLLECTED type="string">individu7</COLLECTED>
                  <COLLECTED type="string">individu8</COLLECTED>
               </COLLECTED>
            </COLLECTED>
         </COLLECTED>
      </variable4>
   </COLLECTED>
   <EXTERNAL>
      <variableExt1>
         <variableExt1 type="string">External var</variableExt1>
         <variableExt1 type="string">External var2</variableExt1>
      </variableExt1>
      <variableExt2 type="string">External var2</variableExt2>
   </EXTERNAL>
</Data>

```

## Transformations JSON <-> XML

```java
File in = new File("/in/data.json");
JSONLunaticDataToXML translator = new JSONLunaticDataToXML();
File xmlOut = translator.transform(in);
```

## Transformations XML <-> JSON

```java
File in = new File("/in/data.xml");
XMLLunaticDataToJSON translator = new XMLLunaticDataToJSON();
File jsonData = translator.transform(in);
```
