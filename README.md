# dijkstra-prim
Interactive implementation of Dijkstra's shortest path and Prim's minimum spanning 
tree algorithms. This program provides a GUI for building visual and logical
weighted undirected graphs and for running the algorithms. Algorithm execution is
animated and the results of the animation may optionally be exported to images for
each step to enable the creation of animated GIFs or PNGs.

# Dependencies
This project was developed and tested using Java 17 making JDK 17+ a necessity
for compilation. There are no further requirements.

# Compiling and Running
To compile the project, run the following command from the project root directory:

    javac -cp "src" -d "bin" src/dsprim/DSPrim.java

Running the program is accomplished using this command:

    java -cp "bin" dsprim.DSPrim

# Usage
At the bottom of the screen may be found all of the controls available for using
the program. These controls enable a user to draw undirected weighted graphs with
arbitrary structure; to run Dijkstra's Shortest Path algorithm or Prim's Minimum
Spanning Tree algorithm on those graphs with their choice of origin vertex; to
choose between a Binary Min Heap or a Linked List for the fringe data structure
used in either algorithm; and to optionally save images of each completed step
for use in generating animations.

### Creating Graphs
To create a graph, first select the "Add Vertex" button and begin placing one or
more vertices. Once the "Add Vertex" button is selected, clicking anywhere in on
the canvas will place a new vertex. To disable the vertex-placing mode, either
click the "Add Vertex" button again or press the escape key on your keyboard to
exit the mode.

Next, add some edges to the graph. To do this, click on the first vertex that will
be part of the new edge and, without releasing the mouse, drag your mouse to the
other vertex. Once over the other vertex, release your mouse and a popup will appear
prompting for the weight of that edge. After entering an edge weight, the edge
will be added to the graph and appear in the visual model.

Continue repeating this process as many times as desired until the graph is
complete.

### Running the Algorithms
Once the desired graph is built, the algorithms may be executed upon it. To run
Dijkstra's shortest path, choose an origin and a destination vertex and click on
the "Run Dijkstra". This will cause the algorithm to run, with the first selected
vertex treated as the origin. This mode of operation requires two vertices be
selected before the program will run.

To run Prim's Minimum Spanning Tree, select at least one vertex and click on the
"Run Prim MST" button. This will run the algorithm with the first selected of
any selected vertices providing the origin vertex for the algorithm.

### Other Controls
When running either of the algorithms, the Min-Queue fringe data structure used can be controlled using the drop-down menu near the righthand side of the control
panel. The possible selections are Min-Heap or Linked List, with the Min-Heap
selection corresponding to an implementation created using a Binary Min Heap and
the linked list selection corresponding to an implementation created using a
doubly-linked list.

The "Reset" button is used to reset the state of the created graph for running
the algorithms repeatedly, with different origins, or with different fringe data
structures.

The "Clear" button destroys the graph and resets the canvas to an empty state. 
This allows for creating new graphs without having to restart the program.

The "Record Run" radio button may be used to indicate to the program that it should
save a PNG image of the graph every time it repaints the canvas. These images
will be saved to a directory called "output_frames" containing each image in a
file named "frame_XXX.png" with XXX being the number of the frame generated,
starting with frame zero.
