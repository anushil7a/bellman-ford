Bellman-Ford and Dijkstra Algorithm Simulator
Overview
This project is a Java-based simulator for visualizing and analyzing the Bellman-Ford and Dijkstra algorithms on directed graphs. The simulator uses the JUNG library for real-time graph visualization, allowing users to interactively add and remove nodes and paths. The simulator supports step-by-step execution, helping users understand how shortest path algorithms work, particularly in graphs with negative edge weights.

Features
Bellman-Ford and Dijkstra Algorithms: Simulate and analyze shortest path calculations.
Real-Time Visualization: Visualize graph changes dynamically as nodes and edges are added, removed, or modified.
Step-by-Step Execution: Observe the algorithms' operation step by step to understand the process of pathfinding.
Interactive Graph Editing: Add or remove nodes and edges interactively.
Responsive Design: The simulator is designed with a user-friendly interface.
Prerequisites
Java 8 or higher
JUNG Library (310libs.jar)
Project Structure
FlexGraph.java: Implements the graph structure and supports operations like adding/removing nodes and edges.
BellmanFord.java: Contains the Bellman-Ford algorithm implementation.
SimGUI.java: The graphical user interface for the simulator.
310libs.jar: External library required for graph visualization (JUNG).
Getting Started
Installation
Clone the Repository:

bash
Copy code
git clone https://github.com/yourusername/graph-algorithm-simulator.git
cd graph-algorithm-simulator
Add the Required Libraries: Ensure 310libs.jar is in the parent directory of your source files or adjust the classpath accordingly.

Compile the Java Files:

On Windows:

bash
Copy code
javac -cp .;../310libs.jar *.java
On Linux/MacOS:

bash
Copy code
javac -cp .:../310libs.jar *.java
Running the Simulator
Execute the Program:

On Windows:

bash
Copy code
java -cp .;../310libs.jar SimGUI
On Linux/MacOS:

bash
Copy code
java -cp .:../310libs.jar SimGUI
Running with Parameters: You can start the simulator with custom parameters like the number of nodes, connection probability, and random seed:

bash
Copy code
java -cp .;../310libs.jar SimGUI <numNodes> <connectionProbability> <randomSeed>
Example:

bash
Copy code
java -cp .;../310libs.jar SimGUI 6 0.2 20
Using the Simulator
Graph Generation: The simulator can generate random graphs based on the input parameters.
Adding/Removing Nodes and Edges: Use the GUI controls to add or remove nodes and edges interactively.
Step-by-Step Simulation: Follow the algorithm's execution step by step to see how paths are calculated and updated.
