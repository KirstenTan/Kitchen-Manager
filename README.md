# Smart Cook

## Description

Smart Cook is a Kitchen Manager application. It is a comprehensive Java application for managing grocery lists, kitchen inventory, and recipes with synchronized functionalities for enhanced user convenience.\

## Installation

1. Download the 7 files
2. Run the Start.java file in an IDE

## Usage

The <b>grocery</b> page allows the user to modify their grocery list. They can also press the "Buy Item" button which will transfer the selected item to their inventory list.

The <b>inventory</b> page allows the user to modify their current kitchen inventory. When adding an item to the inventory list, the user can set a minimum amount of that item that they would like to maintain at all times. If their stock falls below that amount, the item will automatically be added to the grocery list.

The <b>recipes</b> page allows the user to modify their recipe book. When the user selects a recipe, an alert message shows up if they do not have all the ingredients needed, and they may choose to add these ingredients to their grocery list.

<img alt="Grocery Page" src="https://github.com/KirstenTan/Kitchen-Manager/blob/main/images/Grocery%20List.png" width="20%" align="center"> <br>
<i>Grocery List Page</i> <br><br>

<img alt="Add Item Page" src="https://github.com/KirstenTan/Kitchen-Manager/blob/main/images/Add%20Item.png" width="20%" align="center"> <br>
<i>Add Item Page</i> <br><br>

<img alt="Recipes Page" src="https://github.com/KirstenTan/Kitchen-Manager/blob/main/images/Recipe%20List" width="20%" align="center"> <br>
<i>Recipes Page</i> <br><br>

<img alt="Add Recipe Page" src="https://github.com/KirstenTan/Kitchen-Manager/blob/main/images/Add%20Recipe.png" width="20%" align="center"> <br>
<i>Add Recipes Page</i> <br><br>

## Known Issues and Bugs

<b>Issue 1</b>: Editing an item in the grocery or inventory list deletes the set expiry date.
Status: Open
Details: The expiry date values are simply reset to nothing and no other function is interfered with.

<b>Issue 2</b>: Multi-word missing recipe items cannot be properly added to the grocery list.
Status: Open
Details: The program is unable to correctly split the item's values in the recipe's ingredients list when it encounters a multi-word item name. When the item is added to the grocery list, its values are not placed under the correct category.

<b>Issue 3</b>: Deleting a recipe deletes its content but the recipe button and page remain visible.
Status: Open
Details: The program still considers this blank recipe as an existing recipe, so adding a new recipe will create a new page and a new button under the blank one.

<b>Issue 4</b>: (Optional feature) The Edit Recipe function does not work at all. 
Status: Open
Details: This was an optional feature that I tried to implement but was unable to in the given timeframe. Pressing the Edit Recipe button opens up the Add Recipe page with none of the selected recipe's information loaded in.

## Context and Limitations

This project was developed and last edited in 2023 as part of my Computer Science class as a senior high school student. It reflects my knowledge and understanding at that time. Due to other commitments, I may not be able to address the existing bugs, but they are documented for reference.


