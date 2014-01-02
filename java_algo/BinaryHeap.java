package JavaAlgo;

import java.util.ArrayList;
// Name: Lucas Ou-Yang     ID: 27404511

public class BinaryHeap {
    private int size;
    private ArrayList<HeapNode> mainList;

    public BinaryHeap() {
        this.mainList = new ArrayList<HeapNode>();
        this.mainList.add(null);
        this.size = 0;
    }

    // A special inner class which encapsulates the node within a heap.
    private static class HeapNode {
        private double key;
        private Vertex value;

        public HeapNode(Vertex value, double key) {
            this.key = key;
            this.value = value;
        }
    }

    public void insert(Vertex vertex, double key) {
        mainList.add(new HeapNode(vertex, key));
        int followIndex = mainList.size() - 1;

        while (followIndex > 1) {
            // An integer for the parent index.
            int parent = (int) Math.floor(followIndex / 2);
            // If the node we just inserted is smaller than the parent, the heap
            // is broken and we need to perform swaps.
            if (mainList.get(followIndex).key < mainList.get(parent).key) {
                HeapNode temp = mainList.get(parent);
                mainList.set(parent, mainList.get(followIndex));
                mainList.set(followIndex, temp);
                followIndex = parent;

            }
            // If the parent is smaller, we are done and we exit out.
            else {
                followIndex = 0;
            }
        }
        size++;
    }

    public Vertex removeMin() {
        int followIndex = 1;
        // Set a temporary value to the smallest value so we don't get it lost.
        HeapNode nodeToBeReturned = mainList.get(1);
        // Set the root of the tree to be the very last node of the complete
        // heap.
        mainList.set(1, mainList.get(mainList.size() - 1));
        // Remove that node so that we don't have duplicates.
        mainList.remove(mainList.size() - 1);

        // Perform the necessary swaps to maintain the heap order.
        // While our root pointer has not hit our leaf pointer.
        while (followIndex < mainList.size() - 1) {
            int left = 2 * followIndex;
            int right = (2 * followIndex) + 1;

            // If both children don't exist, we are done!! Leave the loop.
            if (mainList.size() <= left) // Size inflated by 1 because
                                         // deadweight, so we use <= and not <
            {
                followIndex = mainList.size() - 1;
            }

            // If the right child doesn't exist and the left is smaller, swap
            // left to root.
            else if (mainList.size() <= right) {
                if (mainList.get(left).key < mainList.get(followIndex).key) {
                    HeapNode temp = mainList.get(followIndex);
                    mainList.set(followIndex, mainList.get(left));
                    mainList.set(left, temp);
                    // Set the index properly so we can move along the heap.
                    followIndex = left;
                }

                // If the only child isn't smaller, we are done.
                else {
                    followIndex = mainList.size() - 1;
                }
            }

            // If the right/left children actually exist, replace with the
            // smaller.
            else {
                // If the left child is smaller than the right child. (Or equal)
                if ((mainList.get(left).key < mainList.get(right).key || mainList
                        .get(left).key == mainList.get(right).key)
                        && mainList.get(left).key < mainList.get(followIndex).key) {
                    HeapNode temp = mainList.get(followIndex);
                    mainList.set(followIndex, mainList.get(left));
                    mainList.set((left), temp);
                    // Set the index properly so we can move along the heap.
                    followIndex = left;
                }
                // If the right child is smaller than the left child
                else if (mainList.get(right).key < mainList.get(left).key
                        && mainList.get(right).key < mainList.get(followIndex).key) {
                    HeapNode temp = mainList.get(followIndex);
                    mainList.set(followIndex, mainList.get(right));
                    mainList.set(right, temp);
                    // Set the index properly so we can move along the heap.
                    followIndex = right;
                }
                // If the two children are greater than the current, were good.
                else {
                    followIndex = mainList.size() - 1;
                }
            }
        }
        size--;
        return nodeToBeReturned.value;
    }

    public int size() {
        return size;
    }
}

class Edge {
    private int startVertex;
    private double distance;
    private double speed;
    private int adjacentVertex;
    // A special field designed so I can identify out bound and in bound edges
    // for each
    // vertex.
    private boolean isSuccessor;

    public Edge(int startVertex, int adjacentVertex, double distance,
            double speed) {
        this.startVertex = startVertex;
        this.adjacentVertex = adjacentVertex;
        this.distance = distance;
        this.speed = speed;
    }

    public int getStartVertex() {
        return startVertex;
    }

    public int getAdjacentVertex() {
        return adjacentVertex;
    }

    public double getDistance() {
        return distance;
    }

    public boolean isSuccessor() {
        return isSuccessor;
    }

    // This method is more beefy because we need to compute the actual time
    // taken
    // to traverse a road segment, using the miles per hour and distance.
    public double getTimeTaken() {
        double time = distance / speed;
        return time;
    }

    public double getSpeed() {
        return speed;
    }

    public void setIsSuccessor(boolean status) {
        this.isSuccessor = status;
    }
}

class Vertex {
    // kv is a boolean flag that indicates whether the shortest path to vertex
    // v is known. Initially, kv is false for all vertices.
    private boolean k;
    // dv is the length of the shortest known path from the start vertex to
    // vertex
    // v. When the algorithm begins, no shortest paths are known. Initially, dv
    // is INFINITY for all vertices, except the start vertex, for which dv = 0.
    private double d;
    // pv is the predecessor of the vertex v on the shortest known path from the
    // start vertex to v. Initially, pv is unknown for all vertices, except for
    // the start vertex, for which pv is none.
    private Vertex p;

    private int number;
    private String name;
    private ArrayList<Edge> inAndOutEdges;

    public Vertex(int number, String name) {
        this.number = number;
        this.name = name;
        inAndOutEdges = new ArrayList<Edge>();
    }

    // Getters for the field variables.
    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Edge> getEdges() {
        return inAndOutEdges;
    }

    public boolean getK() {
        return k;
    }

    public double getD() {
        return d;
    }

    public Vertex getP() {
        return p;
    }

    // Method for adding edges on a vertex (Mainly for the adjacency list).
    public void addToEdgeList(Edge inEdge) {
        inAndOutEdges.add(inEdge);
    }

    // Setters for Dijkstra?s algorithm.
    public void setK(boolean value) {
        this.k = value;
    }

    public void setD(double value) {
        this.d = value;
    }

    public void setP(Vertex value) {
        this.p = value;
    }
}
