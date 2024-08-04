This repository is the showcase from Data Structures and Algorithms class. The objectives were to learn OOP paradigms as well as advanced algorithms and ADS. The repository includes submission versions.

#### This brief descriptions were translated from German using LLM.

## Data Structures and Algorithms class curriculum
#### 1. Introduction to Java: object-oriented programming
#### 2. Introduction to Java: inheritance, generics, debugging; growth orders
#### 3. Introduction to Java: exception handling, unit tests, precedence queues 
#### 4. Backtracking, P and NP, greedy algorithms
#### 5. Branch-and-bound, alpha-beta search, randomized algorithms
#### 6. Dynamic programming
#### 7. Graph algorithms: Depth-first and breadth-first search
#### 8. Minimum spanning trees
#### 9. Shortest paths, topological sorting
#### 10. Heuristic algorithms, approximate algorithms 
#### 11. Hashing


## Homework Assignment 1 (Java basic functionality and OOP Concepts: Inheritance, Encapsulation and Polymorphism)

This assignment involves implementing generic data structures and understanding inheritance in Java, specifically applied to geometric shapes and utility classes.

### Key Topics

#### 1. **Generics**
   - **Task:** Implement a `Pair` class that can store and manage two elements of the same type, providing methods for setting, getting, and swapping these elements.

#### 2. **Inheritance and Interfaces**
   - **Task:** Develop a hierarchy of geometric shapes defined by interfaces and abstract classes, including specific implementations for:
     - Convex polygons
     - Triangles
     - Quadrilaterals
     - Regular polygons

#### 3. **Utility Methods**
   - **Purpose:** Facilitate calculations of area, management of vertices, and generate custom string outputs for debugging and display.

### Implementation Notes
- Utilize IntelliJ IDEA’s automated feature for generating constructors, getters, setters, and methods like `equals()` and `toString()`.
- Ensure correct functionality by adhering to provided specifications and testing with provided methods in the main class.

### Testing and Debugging
- Test classes using provided `main` methods and ensure compliance with output format requirements, especially for the `toString()` method.
- Pay attention to the correct implementation of methods to ensure the semantic equality and accurate geometrical calculations.

## Homework Assignment 2 (Queues and Stacks) 

This assignment is divided into several tasks focusing on implementing classes, understanding data structures like queues and stacks, and simulating a card game, all in Java.

### Key Tasks

#### Implementation of the "Bettelmann" Card Game
   - **Game Simulation:**
     - Simulate the card game "Bettelmann" round by round, using Deques to manage cards and implement game logic, considering the unique rules of the game where cards are won, lost, and shuffled between players based on card values.

### Implementation Notes
- Utilize Java’s collection framework extensively, particularly for managing data structures like stacks, queues, and deques.
- Pay attention to class inheritance and method overrides to maintain clean and efficient code architecture.
- Ensure all implementations handle edge cases, such as low ink levels in printers or end-game scenarios in card games, as specified in the task descriptions.


## Homework Assignment 3 (JUnit Testing for Backtracking)

This assignment focuses on developing JUnit tests for classes that use backtracking to find derangements of a number sequence. A derangement is a permutation where no element appears in its original position.

### Key Tasks

#### 1. **JUnit Tests for Backtracking**
   - **Objective:** Write JUnit tests using JUnit5 to validate classes that compute fixpoint-free permutations (derangements) of number sequences.
   - **Classes to Test:**
     - Two given subclasses of `PermutationVariation` that are supposed to generate all derangements. The classes differ; one functions correctly, and the other does not.

#### 2. **Testing Tasks**
   - **Constructor Test (`testPermutation()`):**
     - Tests the constructor of permutation classes ensuring the `original` array is initialized correctly without duplicates and `allDerangements` starts as an empty list.
   - **Derangement Generation Test (`testDerangements()`):**
     - Validates the `derangments()` method by checking the count and properties of the generated derangements to ensure each truly leaves no element in its original place.
   - **Element Integrity Test (`testsameElements()`):**
     - Checks if the outputs from the `derangments()` method are actual permutations of the original array, failing the test if any non-permutation is found.

#### 3. **Implementation Guidelines**
   - **Flexible Testing:**
     - Apply the tests to any subclasses of `PermutationVariation` as well as the specifically provided class instances `p1` and `p2`.
   - **Code Efficiency:**
     - Avoid code duplication by creating helper methods without the `@Test` annotation to support test initialization and fix potential constructor issues.
   - **Understanding of Underlying Concepts:**
     - Gain a deeper understanding of how greedy algorithms make decisions, the use of recursion in backtracking, and the structure of solution trees in such algorithms.

#### 4. **Learning Outcomes**
   - By the end of this assignment, you should be proficient in writing JUnit tests, understanding the types of errors they check for, and applying backtracking techniques effectively in problem-solving.

### Additional Notes
- Use `Math.pow(a, b)` for calculations involving exponentiation, as `^` in Java is a bitwise operator.
- Explore the structure and definitions of the `Cases` class to better understand the initialization of test cases and the context of the data used in tests.


## Homework Assignment 4 (Tic-Tac-Toe game  using Minimax with Alpha-Beta-pruning)

This assignment involves implementing a program that evaluates various positions in a Tic-Tac-Toe game, accounting for winning or losing scenarios based on optimal play from both players.

### Key Tasks

#### 1. **Class Implementation**
   - **Board Class**:
     - Implement the `Board` class for n×n Tic-Tac-Toe boards (1 ≤ n ≤ 10) with methods to handle game moves, check game status, and manage the board state.
   - **TicTacToe Class**:
     - Implement the `alphaBeta` search method to evaluate game positions, predicting the outcome based on current board states and possible moves.
     - Add a method to evaluate and display all possible moves from a given board position, showing potential outcomes for each move.

#### 2. **Features to Implement**
   - **Dynamic Board Handling**:
     - Constructor should throw `InputMismatchException` if board dimensions are invalid.
     - Methods for setting and getting board positions, performing moves, and validating moves.
   - **Game Evaluation Logic**:
     - Use alpha-beta pruning to efficiently determine the value of board positions, helping players decide the best moves.
     - Evaluate potential moves and output the results in a specific format, simulating different game scenarios.

#### 3. **Additional Functionalities**
   - **Efficiency Considerations**:
     - Optimize functions like checking for free fields or winning conditions to enhance performance.
   - **Debugging and Validation**:
     - Implement methods to assist in debugging, such as a print function for the board.
   - **Comprehensive Testing**:
     - Develop main methods for both classes to simulate game scenarios and validate the correctness of the implementations. Consider using JUnit tests to automate some of these validations.

#### 4. **Evaluation and Analysis**
   - Use the implemented methods to analyze and discuss strategies within Tic-Tac-Toe, such as identifying poor or optimal opening moves.

### Learning Objectives
- Gain a deeper understanding of advanced algorithmic strategies like alpha-beta pruning in the context of game theory.
- Develop proficiency in handling multi-dimensional arrays and simulating game logic in programming.
- Enhance debugging and testing skills through methodical implementation and validation of complex systems.

### Testing Strategy
- Start testing with simple scenarios where a player can win immediately, then progressively test more complex game states.
- Use the output of the `alphaBeta` method for a blank 2x2 board to explore and validate the base case outcomes expected.

##  Homework Assignment 5: Optimal Selection using Recursion & Dynamic Programming concepts

This assignment involves implementing a game strategy where two players alternately select from the outer bowls of a lineup to maximize the difference in the number of marbles collected.

### Key Tasks

#### 1. **Row of Bowls Game Implementation**
   - **Revisiting Recursion**:
     - Implement a simple recursive solution that calculates the maximum gain a player can achieve by selecting bowls optimally, recognizing that this method may be inefficient due to overlapping subproblems.
   - **Dynamic Programming**:
     - Develop an efficient solution using dynamic programming to optimize both time and space complexity to O(n^2) where n is the number of bowls.

#### 2. **Features to Implement**
   - **Recursive Method**:
     - `public int maxGainRecursive(int[] values)`: Calculates the initial player's score difference against an optimally playing opponent.
   - **Dynamic Programming Method**:
     - `public int maxGain(int[] values)`: Stores intermediate results in a matrix within a class variable for efficient computation and retrieval.
   - **Optimal Sequence Calculation**:
     - `public Iterable<Integer> optimalSequence()`: Returns the sequence of moves that leads to the optimal result as determined by `maxGain`.

#### 3. **Additional Functionalities**
   - **Sequence Uniqueness**:
     - Note that the optimal sequence of moves is not unique; any optimal sequence may be returned by the method.
   - **Order of Moves**:
     - The sequence should list moves in the order they are played, starting with the first move.
   - **Consistency Check**:
     - Ensure the score from the optimal sequence matches the score returned by `maxGain`.

#### 4. **Testing and Validation**
   - **Main Methods and Debugging**:
     - Implement main methods in each class to simulate game scenarios and validate the correctness of the implementations using provided values.
   - **Guideline for Implementation**:
     - While not a strict limit, a guideline suggests keeping methods under 15 lines to encourage clarity and conciseness.

### Learning Objectives
- Understand and apply recursive techniques and dynamic programming in a game theory context.
- Enhance problem-solving skills by translating game strategies into algorithmic solutions.
- Develop the ability to optimize algorithms for both runtime and memory usage.


## Homework Assignment 6: Depth First Search 

This assignment involves using the Depth First Search (DFS) algorithm to create and solve a randomized maze represented by a graph, where nodes correspond to intersections and edges to pathways between these intersections.

### Key Tasks

#### 1. **Implementation of DFS**
   - **DepthFirstPaths Class Enhancements**:
     - Implement the `private void dfs(Graph G, int v)` to compute DFS traversal recursively, calculating postorder, preorder, edgeTo, and distTo during execution.
     - Implement the `public void nonrecursiveDFS(Graph G)` to perform DFS in a non-recursive manner, ensuring it computes the same traversals and results as the recursive version.

#### 2. **Pathfinding**
   - **Method for Path Retrieval**:
     - Implement the `public List<Integer> pathTo(int v)` method in the `DepthFirstPath` class to return the path from a source node to node `v`, using the edgeTo array to trace the path backwards in the correct order.

#### 3. **Maze Graph Construction**
   - **Maze Class Development**:
     - Implement the `public Graph mazegrid()` method to generate a graph where nodes are arranged in a square grid, and only adjacent nodes are connected.

#### 4. **Randomized DFS Implementation**
   - **RandomDepthFirstPaths Class Modifications**:
     - Implement `private void randomDFS(Graph G, int v)` and `public void randomNonrecursiveDFS(Graph G)` to generate a maze using randomized DFS, where the next node to visit is selected randomly among the adjacent nodes.

#### 5. **Edge Management in Maze**
   - **Edge Management Methods**:
     - Implement `public boolean hasEdge(int v, int w)` and `public void addEdge(int v, int w)` in the `Maze` class to manage edges in the maze, ensuring no reflexive edges are allowed and adding new edges to the graph.

#### 6. **Maze Construction and Pathfinding**
   - **Building and Navigating the Maze**:
     - Implement `private void buildMaze()` to construct the maze by finding all paths using randomized DFS and adding them to the maze graph.
     - Implement `public List<Integer> findWay(int v, int w)` to return a path from node `v` to node `w` in the maze, satisfying the condition of finding a feasible path though not necessarily the shortest.

### Learning Objectives
- Deepen understanding of graph theory and the DFS algorithm.
- Apply DFS for practical applications like maze generation and solving.
- Explore the implications of recursion and its non-recursive alternatives in algorithm design.

### Testing and Debugging
- Utilize simple graph examples to test the implemented DFS methods, ensuring consistency between recursive and non-recursive approaches.
- Use visualizations provided by `GridGraph` class to visually verify the correct construction of mazes and the paths found within them.

### Expected Knowledge Outcomes
- Understand the structure and representation of (directed and undirected) graphs.
- Distinguish between and apply depth-first and breadth-first search algorithms.
- Recognize and implement solutions to identify cycles, connected components, and perform topological sorting using DFS and BFS.


##  Homework Assignment 7: Clustering using Prim's MST algorithm

This assignment involves using the Prim's Minimum Spanning Tree (MST) algorithm to solve a clustering problem. Clustering involves grouping objects (in this case, points in an n-dimensional space) based on their similarities, defined by the Euclidean norm.

### Key Tasks

#### 1. **Understanding the Graph Structure**
   - **Graph Reading**:
     - Enhance the `EdgeWeightedGraph` class to read and interpret data from text files describing graphs and the n-dimensional coordinates of their nodes. The constructor should set up the graph and calculate edge weights using the Euclidean norm between points.

#### 2. **Clustering Constructors**
   - **Initial Setup**:
     - Examine and implement two constructors in the `Clustering` class:
       - `public Clustering(EdgeWeightedGraph G)` for unlabeled data.
       - `public Clustering(In in)` for labeled data, where labels help determine the accuracy of the clustering algorithm post-computation.

#### 3. **Clustering Algorithms**
   - **First Clustering Variant**:
     - Implement `public void findClusters(int numberOfClusters)` to determine the number of edges to remove from the MST based on their weights to form the desired number of clusters. Utilize the Union-Find data structure to help identify connected components.
   - **Second Clustering Variant**:
     - Implement `public void findClusters(double threshold)` to dynamically determine which edges to remove by examining the variation in edge weights. Use the coefficient of variation to decide if an edge's weight is too divergent, indicating that the linked nodes should not be in the same cluster.

#### 4. **Validation of Clustering**
   - **Accuracy Assessment**:
     - Implement `public int[] validation()` to compare the clustering results with provided labels, if available. This method should count and return the number of correctly clustered points per cluster, helping evaluate the effectiveness of the clustering algorithm.

### Learning Objectives
- Apply Prim's MST algorithm to a practical problem in clustering.
- Understand and utilize the Union-Find algorithm to detect and manage connected components within the graph.
- Develop skills in handling multidimensional data and interpreting the results of clustering algorithms.

### Expected Knowledge Outcomes
- Comprehend the basic concepts of Union Find and how it operates.
- Understand what a Minimum Spanning Tree (MST) is, its properties, and its applications.
- Learn about the cut properties and crossing edges in graph theory.
- Distinguish between Prim’s and Kruskal's algorithms for finding MSTs and understand their differences.

### Testing and Debugging
- Use provided example files like `graph_bigger.txt`, `graph_small.txt`, and `iris.txt` to test the clustering implementations.
- Utilize the `plotClusters()` method to visualize the clusters in two-dimensional space for easier analysis and verification of the clustering results. 

This assignment not only enhances understanding of graph-based algorithms but also applies these concepts to real-world data sets, providing a robust learning experience in both theoretical and practical aspects of computer science.


## Homework Assignment 8: Content-Aware Image Resizing using SSSP algorithms for DAG manipulation.

This assignment involves implementing content-aware image resizing, also known as seam carving, using algorithms that manipulate directed acyclic graphs (DAGs) to preserve important content while reducing image dimensions.

### Key Tasks

#### 1. **Understanding Existing Classes and Interfaces**
   - **Initial Setup**:
     - Familiarize yourself with the provided classes and interfaces such as `Image`, `Coordinate`, `DirectedEdge`, `WeightedDigraph`, and utilities like `Bag` for managing collections of items.

#### 2. **Implementing MatrixImage Class**
   - **Method Implementations**:
     - Implement `public double contrast(Coordinate p0, Coordinate p1)` to calculate the contrast between two adjacent pixels, defined as the absolute difference in their values.
     - Implement `public void removeVPath(int[] path)` which should adjust the image matrix by removing the specified path, effectively resizing the image.

#### 3. **Implementing Shortest Paths for Topological Sorting**
   - **Shortest Path Calculation**:
     - Implement the `public ShortestPathsTopological(WeightedDigraph G, int s)` constructor to calculate shortest paths from a source node `s` using topological sorting.
     - Implement the `public void relax(DirectedEdge e)` method to handle the relaxation process in the shortest path algorithm, ensuring it updates path lengths and predecessors efficiently.

#### 4. **Content-Aware Image Resizing Implementation**
   - **Finding Least Contrast Paths**:
     - Implement the `public int[] leastContrastImageVPath()` in the `ContentAwareImageRezising` class. This method should identify the vertical path with the least contrast that can be removed to resize the image while preserving important features.

### Learning Objectives
- Understand and apply concepts of graph theory in practical applications like image processing.
- Implement algorithms for finding shortest paths in weighted graphs, particularly using methods like topological sort and relaxation.
- Explore content-aware algorithms that adapt the structure of data (images in this case) while maintaining its integrity.

### Expected Knowledge Outcomes
- Grasp the functionality and utility of weighted graphs in algorithm design.
- Understand relaxation in the context of shortest path algorithms and its role in efficient graph processing.
- Learn how topological sorting facilitates shortest path calculations in acyclic graphs and the conditions under which this is applicable.
- Explore different shortest path algorithms including Dijkstra and Bellman-Ford, understanding their application conditions and limitations.

### Testing and Debugging
- Use provided example files and the `MatrixImage` class for initial testing to ensure the implemented methods function correctly before applying them to actual images.
- Leverage the `plotClusters()` method for visual verification of the clustering results, particularly useful in two-dimensional representations.

This assignment is designed to bridge theoretical graph algorithms with practical applications in computer science, particularly in areas like image processing where preserving content integrity during transformations is crucial.


## Homework Assignment 9: Sliding Puzzle Solution Procedure using A* search algorithm

This assignment involves solving a sliding puzzle using the A* search algorithm, where the objective is to reach a specific arrangement of tiles by sliding them into an empty space on a 4x4 board.

### Key Tasks

#### 1. **Enhancing the Board Class**
   - **Heuristic Implementation**:
     - Implement the `public int manhattan()` method in the `Board` class to calculate the Manhattan distance heuristic, which estimates the cost to reach the goal state from the current configuration of the puzzle.

#### 2. **Implementing the PartialSolution Class**
   - **Solution Representation**:
     - Implement the `PartialSolution` class to represent states of the puzzle, including the board configuration and the sequence of moves made. This class should be comparable based on the combined cost of backward and forward moves (defined by the A* algorithm).
   - **Move Execution**:
     - Implement methods to execute moves, check if the current state is a solution, and generate valid next moves, avoiding moves that reverse the immediate last move.

#### 3. **Developing the AStar15Puzzle Solver**
   - **A* Algorithm Implementation**:
     - Implement the `public static PartialSolution solveByAStar(Board board)` method in the `AStar15Puzzle` class. This method should use the A* algorithm with the Manhattan distance heuristic to find the shortest sequence of moves to solve the puzzle.
   - **Handling of Unsolvable States**:
     - Note that the implementation does not need to terminate for unsolvable configurations of the puzzle. However, it should efficiently find solutions for configurations that can be resolved.

### Learning Objectives
- Apply heuristic algorithms to efficiently solve complex puzzles.
- Understand and utilize the A* algorithm's principles, including the use of heuristics, to navigate large search spaces.
- Develop and use data structures suitable for tracking and comparing game states in the context of game-solving algorithms.

### Expected Knowledge Outcomes
- Learn what heuristic algorithms are and the role of heuristics in these algorithms.
- Understand the concepts of admissible and consistent heuristics and how they ensure the effectiveness of the A* algorithm.
- Explore the principles of the A* algorithm, particularly how it uses relaxation techniques to find the shortest paths in state space.
- Distinguish between different types of algorithms, including approximative algorithms and their applications in solving real-world problems.

### Testing and Debugging
- Utilize the provided sample puzzle configurations in the `board-3x3-*.txt` files to test the implementation. Corresponding expected outcomes are provided to verify the correctness of the solution sequences.
- Test the implementation's ability to handle different board sizes and configurations, assessing both efficiency and accuracy.

This assignment bridges theoretical algorithm concepts with practical applications, enhancing problem-solving skills in complex scenarios typically encountered in computational puzzles and games.


## Homework Assignment 10: Solving the Ricochet Robots Puzzle using BFS and Hashing techniques

This assignment involves developing a solver for the "Ricochet Robots" puzzle game using efficient search techniques to handle multiple robots on a grid with obstacles. The goal is to navigate robots to specific targets with the fewest moves possible using hash sets to manage state exploration efficiently.

### Key Tasks

#### 1. **Understanding Game Mechanics**
   - **Review Game Logic**:
     - Examine the classes `Position`, `Move`, `Robot`, and `Board` that encapsulate the game's logic. These classes will serve as the foundation for implementing the solution procedure.

#### 2. **Enhancing Position and Board Classes**
   - **Position Class**:
     - Implement the `public boolean equals(Object o)` and `public int hashCode()` methods in the `Position` class to ensure each position on a 20x20 board is uniquely identified by its hash code.
   - **Board Class**:
     - Implement similar methods in the `Board` class, considering only attributes that change during gameplay, to efficiently check if a board configuration has been encountered before using hash sets.

#### 3. **Implementing the Solution Logic**
   - **PartialSolution Class**:
     - Develop the `PartialSolution` class to store game states and the sequence of moves leading to them. This class should support operations necessary for the breadth-first search (BFS) with hashing, including checking if a state is the goal state and retrieving valid moves.
   - **RicochetRobots Solver**:
     - Implement the `public static PartialSolution bfsWithHashing(Board board)` method in the `RicochetRobots` class. This method should use BFS enhanced with hashing to prevent revisiting the same game state, significantly reducing the search space and computation time.

### Learning Objectives
- Apply advanced search algorithms and hashing techniques to solve complex puzzle games efficiently.
- Understand the use of hash sets to manage explored states in a search algorithm, enhancing performance by avoiding redundant state explorations.
- Develop practical solutions that can be applied to real-world problems, such as game solving and pathfinding in constrained environments.

### Expected Knowledge Outcomes
- Learn about hashing, perfect hashing, and strategies for collision resolution.
- Understand the operational complexities involved with hash tables, including insertion and deletion processes.
- Explore the impact of the load factor on the performance of hash tables and how it influences search and insertion times.

### Testing and Debugging
- Utilize provided example board configurations (`rrBoard-sample01.txt`, `rrBoard-sample02.txt`, and `rrBoard-sample03.txt`) to test the solver's effectiveness and correctness.
- Implement tests to ensure that the hashing mechanism effectively reduces the number of states revisited during the search, checking for performance improvements and correctness in solving the provided puzzles.

This assignment challenges students to integrate complex data structures and algorithms to solve a dynamic and visually intuitive puzzle, enhancing skills in both theoretical concepts and practical software development.
