# Smart Cook

## Description

Smart Cook is a Kitchen Manager application. It is comprehensive Java application for managing grocery lists, kitchen inventory, and recipes with synchronized functionalities for enhanced user convenience.\

## Installation

1. Download the 7 files
2. Run the Start.java file in an IDE

## Usage

The <b>grocery</b> page allows the user to modify their grocery list. They can also press the "Buy Item" button which will transfer the selected item to their inventory list.

The <b>inventory</b> page allows the user to modify their current kitchen inventory. When adding an item to the inventory list, the user can set a minimum amount of that item that they would like to maintain at all times. If their stock falls below that amount, the item will automatically be added to the grocery list.

The <b>recipes</b> page allows the user to modify their recipe book. When the user selects a recipe, an alert message shows up if they do not have all the ingredients needed, and they may choose to add these ingredients to their grocery list.

## Known Issues and Bugs

| Issue 1: Editing an item in the grocery or inventory list deletes the set expiry date. |
| Status | Open|
| Details | This does not interfere with any other function |
