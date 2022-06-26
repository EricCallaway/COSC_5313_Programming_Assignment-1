## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
# COSC_5313_Programming_Assignment-1

MapBuilder.java
This file creates all twenty vertices in the form of node objects. The objects are placed in a list and then sorted. The map is a 20x20 grid, each index is associated with a cities ID (e.g Oradea's ID is 0 so the first column/row represents the neighbors of Oradea, Iasi's ID is 18 so row/column #18 represents the neighbors of Iasi). Each index of the map is either a one or a zero. If an element has a one, then the cities represented by that column and row are neighbors. If an element has a zero, then the cities represented by that column and row are not neighbors. A grid is printed out in the console showing the relationship of the cities.

Node.java
This file creates a node object for use in the MapBuilder.java file. This file creates a node object with three attributes:
1. Name of the City
2. ID of the City
3. The cities neighbors

How to Run:
1. Compile node.java (javac node.java)
2. Compile MapBuilder.java (javac MapBuilder.java)
3. Run MapBuilder.java for this is where the main method is. (java MapBuilder)
The output is the 20x20 grid displaying the neighbors of each city.
