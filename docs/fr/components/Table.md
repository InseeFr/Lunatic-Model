## Le composant `Table`

Il s'agit du composants le plus complexe de Lunatic.
Comme tous les composants, il a les attributs communs des composants. Il dispose en plus d'un attribut `positioning` pouvant être égale à "HORIZONTAL" ou "VERTICAL".

Il y a deux types de composants `Table` Lunatic :

- les `Table` classique qui représente un tableau avec un nombre de cellules fixes
- les `Table` dynamique qui représente un tableau dynamique avec un nombre de ligne affiché de façon dynamique.

Dans le dernier cas, il y a un attribut `lines` qui définit le minimum et le maximum de lignes.

```json
"lines": { "min":1, "max":10 }
```

Le JSON fournit dans ce cas contient toutes les lignes possibles du tableau. C'est au composant Lunatic d'afficher dynamiquement les lignes, en affichant à l'état initial du composant autant de lignes que l'attribut min définit le minimum de ligne.
Dans tous les cas, le composant Table contient un attribut `cells` qui est une matrice défiisant les cellules du tableau à afficher.

---

### Les `cells`

Une cellule peut être soit:

- un composant
- une cellule simple avec un label

Dans le dernier cas :

- si la cellule est une entête de colonne, il y a un attribut `headerCell`, exemple :`"headerCell":true`
- si il y a une fusion de colonne/ligne, il y a un attribut `colspan` / `rowspan`, exemple :`"colspan":2`

```json
{
  "id" : "j4nwc63q",
  "componentType" : "Table",
  "mandatory" : false,
  "positioning" : "HORIZONTAL",
  "label" : "label de la question",
  "declarations": [...],
  "conditionFilter" : "#if((!( $READY != '1')))normal#{else}hidden#end",
  "cells" :
    [
      [
        { "headerCell" : true, "colspan" : 2, "label" : ""},
        { "headerCell" : true, "label" : "Percentage"}
      ],

      [
        { "rowspan" : 2, "label" : "Frozen products", "value" : "A"},
        { "label" : "Ice creams", "value" : "A1"},
        { "componentType" : "InputNumber", ...}
      ],

      [
        { "label" : "Jasper Beardly", "value" : "A2" },
        { "componentType" : "InputNumber", ...}
      ],

      [
        { "rowspan" : 3, "label" : "Meat", "value" : "B"},
        { "label" : "Bacon", "value" : "B1"},
        { "componentType" : "InputNumber", ...}
      ],

      [
        { "label" : "Pork chop", "value" : "B2"},
        { "componentType" : "InputNumber", ... }
      ],

      [
        { "label" : "Chicken", "value" : "B3"},
        { "componentType" : "InputNumber", ...}
      ],

      [
        { "label" : "Compote", "value" : "C"},
        { "label" : "Powersauce", "value" : "C1"},
        { "componentType" : "InputNumber", ...}
      ],

      [
        { "colspan" : 2, "label" : "Other", "value" : "D"},
        { "componentType" : "InputNumber", ...}
      ]
    ]
}
```

Résultat en composant Lunatic du `Table` classique :

**label de la question**

<table>
	<thead>
		<tr>
			<th colspan=2></th>
			<th>Percentage</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td rowspan=2>Frozen products</td>
			<td>Ice creams</td>
			<td>InputNumber</td>
		</tr>
		<tr>
			<td>Jasper Beardly</td>
			<td>InputNumber</td>
		</tr>
		<tr>
			<td rowspan=3>Meat</td>
			<td>Bacon</td>
			<td>InputNumber</td>
		</tr>
		<tr>
			<td>Pork chop</td>
			<td>InputNumber</td>
		</tr>
		<tr>
			<td>Chicken</td>
			<td>InputNumber</td>
		</tr>
		<tr>
			<td>Compote</td>
			<td>Powersauce</td>
			<td>InputNumber</td>
		</tr>
		<tr>
			<td colspan=2>Other</td>
			<td>InputNumber</td>
		</tr>
	</tbody>
</table>
