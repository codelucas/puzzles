package graphalgorithms;

import java.util.*;
import java.io.*;

/**
 * Graph application for performing graph algorithms on a graph that well
 * construct from an input data file that defines the edges and vertices in the
 * graph and the relationship between each vertex
 *
 * @author James C. Porcelli
 */
public class Connected_Components {

    //INDICATES THE SET OF DISCOVERED AND PROCESSED VERTICES 
    static boolean discovered[], processed[];
    //INDICATES THE PARENT RELATION OF EACH VERTICE 
    static int parent[];

    /**
     * Main routine from which we will control the program flow
     * for solving problems by building graphs and running graph 
     * algorithms on those graphs
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //GET THE INPUT DATA FILE NAME 
        System.out.print("Enter file name: ");
        //WELL USE THIS SCANNER TO GET KEYBOARD INPUT
        Scanner console = new Scanner(System.in);
        //READ IN THE INPUT FILE NAME FROM THE USER
        String fileName = console.next();
        File input = new File(fileName);
        Scanner in;
        try {
            //ATTEMPT TO OPEN THE SPECIFIED FILE
            in = new Scanner(input);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            //EXIT THE APPLICATION IF WE COULDNT FIND THE FILE 
            return;
        }

        //NUMBER OF VERTICES AND EDGES IN OUR GRAPH
        int numVertices, numEdges;
        //READ IN THE VALUE OF EACH FROM THE FILE 
        numEdges = in.nextInt();
        numVertices = in.nextInt();

        //INIT THE UTILITIE AND RELATIONSHIP ARRAYS
        discovered = new boolean[numVertices];
        processed = new boolean[numVertices];
        parent = new int[numVertices];

        //OUR GRAPH WILL RESIDE IN AN ADJACENCY LIST DATA STRUCTURE 
        LinkedList<Integer>[] graph = new LinkedList[numVertices];

        //READ IN EACH EDGE AND ADD IT TO THE ADJACENCY LIST
        for (int i = 0; i < numEdges; i++) {
            //VERTICES AT EACH END OF AN EDGE
            int xVertex, yVertex;
            xVertex = in.nextInt();
            yVertex = in.nextInt();

            //THIS ENABLES US TO KEEP UNUSED ELEMENTS OF THE ARRAY
            //INITIALIZED TO NULL SO WE CAN EASILY IDENTIFY THEM 
            //FROM ELEMENTS OF THE ADJACENCY STRUCTURE THAT ARE BEING
            //USED
            if (graph[xVertex - 1] == null) {
                graph[xVertex - 1] = new LinkedList<Integer>();
            }
            //DO THIS FOR EACH VERTEX OF AN EDGE FOR AN UNDIRECTED GRAPH
            if (graph[yVertex - 1] == null) {
                graph[yVertex - 1] = new LinkedList<Integer>();
            }

            //IF WE WANT TO PRINT THE VERTICES OF AN EDGE AS WE READ THEM IN
            //System.out.println("x=" + xVertex + ", y=" + yVertex);

            //FOR UNDIRECTED GRAPHS WE MUST ADD Y TO X'S ADJACENCY LIST, AND
            //X TO Y'S ADJACENCY LIST
            graph[xVertex - 1].add(yVertex);
            graph[yVertex - 1].add(xVertex);
        }

        //THE GRAPH ALGORITHM WELL BE PERFORMING ON THE GRAPH
        connected_components(graph);
    }

    /**
     * The processed, discovered, and parent, state and relationship arrays 
     * are primarily used in the different graph algorithms well be performing 
     * on the graph to determine specific relationships of a particular edge or 
     * vertex. So before we run any graph algorithm we will almost always init 
     * those arrays so we can build them up as the algorithm progresses
     *
     * @param graph The graph we store our data in and perform different graph
     * algorithms on. Our graph is represented by an adjacency list data
     * structure using an array of LinkedLists<E> where currently the type of
     * the graph elements is Integer but we can easily adopt our data structure
     * to handle data of any type which makes the graph data structure an
     * extremely capable data structure for representing data and the
     * relationships that exist between the separate nodes of that data
     */
    public static void initialize_search(LinkedList<Integer>[] graph) {
        //GO THROUGH EACH VERTEX IN THE LIST
        for (int i = 0; i < graph.length; i++) {
            //AND SET THE PROCESSED, AND DISCOVERED STATES OF EVERY VERTEX TO
            //NOT PROCESSED AND NOT DISCOVERED
            processed[i] = discovered[i] = false;
            //AND SET THE PARENT RELATIONSHIP OF EACH VERTICE TO UNDEFINED
            parent[i] = -1;
        }
    }

    /**
     * A connected components graph algorithm. A connected component of a graph
     * is a set of vertices such that there is a path between all vertices in
     * the component. Finding the connected components of a graph can be very
     * useful in different applications
     *
     * @param graph The graph data structure represented by an array of
     * LinkedList<E> that we will perform this graph algorithm on
     */
    public static void connected_components(LinkedList<Integer>[] graph) {
        //THE NUMBER OF COMPONENTS IN THE GRAPH
        int components = 0;

        //INIT THE UTILITIE ARRAYS THAT THE GRAPH ALGORITHMS DEPEND ON
        initialize_search(graph);
        
        //GO THROUGH EACH VERTEX IN THE GRAPH
        for (int i = 0; i < graph.length; i++) {
            //IF THAT VERTEX IS IN AN UNDISCOVERED STATE
            if (discovered[i] == false && graph[i] != null) {
                //INCREMENT THE NUMBER OF COMPONENTS BECAUSE WE HAVE FOUND
                //ANOTHER SEPERATE COMPONENT OF THE GRAPH
                components++;
                //PRINT OUT THE CURRENT NUMBER OF COMPONENTS
                System.out.println("Component " + components);
                //PERFORM A BFS FROM THAT VERTEX
                bfs(graph, i);
            }
        }
    }

    /**
     * A breadth first search graph algorithm. The BFS of an undirected graph
     * defines the shortest path spanning tree between the root vertex and every
     * other vertex in the tree. BFS has several other useful application such
     * as determining if a given graph is connected
     *
     * @param graph The graph data structure we are performing this graph
     * algorithm on. The graph is represented by an array of LinekedList<E>
     *
     * @param start The starting vertex for BFS that will become the root of the
     * spanning tree defined by BFS
     */
    public static void bfs(LinkedList<Integer>[] graph, int start) {
        //A QUEUE DATA STRUCUTURE USED IN BFS
        LinkedList<Integer> queue = new LinkedList();

        //THE VERTICES ON EIGHTER END OF A PARTICULAR EDGE
        int v, y;
        //THE ADJACENCY LIST FOR THE VERTEX p
        LinkedList<Integer> p;

        //ENQUEUE THE START VERTEX 
        queue.addFirst(start + 1);
        //NOW SET THE START VERTEX TO DISCOVERED STATE
        discovered[start] = true;

        //WHILE THE QUEUE IS NOT EMPTY
        while (!queue.isEmpty()) {
            //DEQUEUE A VERTEX FROM THE QUEUE
            v = queue.getFirst();
            queue.removeFirst();

            //PROCESS VERTEX EARLY USED TO PRINT OUT THE CURRENT VERTEX BEING PROCESSED
            //System.out.println("processed vertex " + v);

            //SET V TO PROCESSED STATE
            processed[v - 1] = true;

            //VERTEX v's ADJACENCY LIST
            p = graph[v - 1];
            
            //GET AN ITERATOR TO v's ADJACENCY LIST
            ListIterator<Integer> adj = p.listIterator(0);
            
            //PROCESS EVERY VERTEX ADJACENT TO v
            while (adj.hasNext()) {
                //GET THE NEXT VERTEX IN v's ADJACENCY LIST
                y = adj.next();
                
                //IF THAT VERTEX IS NOT PROCESSED YET, PROCESS VERTEX LATE
                if (processed[y - 1] == false) {
                    //USED TO PRINT OUT THE CURRENT VERTEX BEING PROCESSED
                    System.out.println("processed edge " + v + " " + y);
                }
                //IF THE CURRENT VERTEX BEING LOOKED AT HAS NOT BEEN DISCOVERED YET
                if (discovered[y - 1] == false) {
                    //ENQUEUE THAT VERTEX 
                    queue.addLast(y);
                    //AND SET THAT VERTICES DISCOVERED STATE TO TRUE
                    discovered[y - 1] = true;
                    //THE CURRENT VERTICES PARENT IS v SO SET THAT RELATIONSHIP IN
                    //THE PARENT ARRAY
                    parent[y - 1] = v;
                }

            }
        }
    }
}