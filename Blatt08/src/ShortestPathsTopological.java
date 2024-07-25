import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class ShortestPathsTopological {
    private int[] parent;
    private int s;
    private double[] dist;


    public ShortestPathsTopological(WeightedDigraph G, int s) {
        this.s = s;
        if (s < 0 || s >= G.V()) {
            throw new IllegalArgumentException("Invalid start vertex: " + s);
        }
        double positiveInfinity = Double.POSITIVE_INFINITY;
        // Be careful: Methods does not check, whether edge e exists already.
        // Do not add an edge more than once


        parent = new int[G.V()];  // init parent array with size of vertices amount
        dist = new double[G.V()]; // init distances from starting vertex to chosen vertex v
        Arrays.fill(dist, positiveInfinity);
        dist[s] = 0;
        TopologicalWD topSorted = new TopologicalWD(G); // creates an object for topological sorting
        if (topSorted.hasCycle()) {
            throw new IllegalArgumentException("Graph is not a DAG");
        }
        topSorted.dfs(s); // determines the reverse-postorder for a DAG

        Stack<Integer> revPostOrderSorted = topSorted.order(); // returns reversed ordering

        while(!revPostOrderSorted.isEmpty()) {
           Integer vertex = revPostOrderSorted.pop(); // pops and returns element
            // check on parent
            if (dist[vertex] < positiveInfinity) { // distance
                Iterable<DirectedEdge> incidentEdges  = G.incident(vertex); // retrieve incident edges
                incidentEdges.forEach(edge -> {
                    relax(edge);             // relax if needed
                });

            }

        }


    }

    public void relax(DirectedEdge e) {
        int v = e.from(); // source vertex
        int w = e.to(); // destination vertex
        double weight = e.weight(); // edge weight
        if (v < 0 || v >= dist.length || w < 0 || w >= dist.length) {
            throw new IllegalArgumentException("Vertex index out of valid range");
        }
        if (dist[v] + weight < dist[w]) { // if the dist shorter than existing dist
            dist[w] = dist[v] + weight; // calculate distance from start
            parent[w] = v; // set parent
        }
    }

    public boolean hasPathTo(int v) {
        return parent[v] >= 0;
    }

    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int w = v; w != s; w = parent[w]) {
            path.push(w);

        }
        path.push(s);
        return path;
    }
}
