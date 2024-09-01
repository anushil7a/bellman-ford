# Bellman-Ford and Dijkstra Algorithm Simulator


https://github.com/user-attachments/assets/f41d54ac-7a47-470c-9dc9-cb268af32bee


## Project Description
This project is a Java-based simulator for visualizing and analyzing the Bellman-Ford and Dijkstra algorithms on directed graphs. The simulator uses the JUNG library for real-time graph visualization, allowing users to interactively add and remove nodes and paths. The simulator supports step-by-step execution, helping users understand how shortest path algorithms work, particularly in graphs with negative edge weights.

## Features
- Bellman-Ford and Dijkstra Algorithms: Simulate and analyze shortest path calculations.
- Real-Time Visualization: Visualize graph changes dynamically as nodes and edges are added, removed, or modified.
- Step-by-Step Execution: Observe the algorithms' operation step by step to understand the process of pathfinding.
- Interactive Graph Editing: Add or remove nodes and edges interactively.
- Responsive Design: The simulator is designed with a user-friendly interface.

## Prerequisites
- Java 8 or higher
- JUNG Library (libb.jar)

## Project Structure
- FlexGraph.java: Implements the graph structure and supports operations like adding/removing nodes and edges.
- BellmanFord.java: Contains the Bellman-Ford algorithm implementation.
- SimGUI.java: The graphical user interface for the simulator.
- libb.jar: External library required for graph visualization (JUNG).

## Installation
1. Clone the Repository
2. Add the Required Libraries: Ensure libb.jar is in the parent directory of your source files or adjust the classpath accordingly.
3. Linux: javac -cp .:../libb.jar *.java
4. Execute the Program: java -cp .:../libb.jar SimGUI
- you can also run with parameters: java -cp .;../libb.jar SimGUI <numNodes> <connectionProbability> <randomSeed>

Ex: java -cp .;../310libs.jar SimGUI 6 0.2 20

## Using the Simulator
- Graph Generation: The simulator can generate random graphs based on the input parameters.
- Adding/Removing Nodes and Edges: Use the GUI controls to add or remove nodes and edges interactively.
- Step-by-Step Simulation: Follow the algorithm's execution step by step to see how paths are calculated and updated.
