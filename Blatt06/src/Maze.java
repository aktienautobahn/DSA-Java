import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
/**
 * Class that represents a maze with N*N junctions.
 * 
 * @author Vera Röhr
 */
public class Maze{
    private final int N; // number of vertices
    private Graph M;    //Maze
    public int startnode;
        
	public Maze(int N, int startnode) {

        if (N < 0) throw new IllegalArgumentException("Number of vertices in a row must be nonnegative");
        this.N = N;
        this.M= new Graph(N*N); // instantiation of Graph class with the N^2 size
        this.startnode= startnode;
        buildMaze();
	}
	
    public Maze (In in) {
    	this.M = new Graph(in);
    	this.N= (int) Math.sqrt(M.V());
    	this.startnode=0;
    }

	
    /**
     * Adds the undirected edge v-w to the graph M.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {
        this.M.addEdge(v, w);
    }
    
    /**
     * Returns true if there is an edge between 'v' and 'w'
     * @param v one vertex
     * @param w another vertex
     * @return true or false
     */
    public boolean hasEdge( int v, int w){
        if (v >= this.M.V() || w >= this.M.V() || v < 0 || w < 0) return false; // invalid vertex for 10, 4
        if (v == w) return true; // doesn't make sense in hasEdge method, but okay: Lassen Sie keine reflexiven Kanten zu, also Kanten die von einem Knoten zu sich selbst gehen. Geben Sie dafür bei der Methode hasEdge für diese Kanten true zurück.
        return M.adj(v).contains(w);
    }	
    
    /**
     * Builds a grid as a graph.
     * @return Graph G -- Basic grid on which the Maze is built
     */
    public Graph mazegrid() {
        int n = N;
        Graph M = new Graph(N*N); // instantiation of Graph class with the N^2 size
        // edge case with N = 1
        if (N == 1) return M;
        // corner cases: two edges
            // top left: 0 (edges to: 1 / n)
            M.addEdge(0, 1);
            M.addEdge(0, n);
            // top right: n - 1 (edge to: n*2-1)
            M.addEdge(n-1, n*2 - 1);
            // bottom left: n * n - n + 1(edges to: -n / + 1
            M.addEdge(n*n - n, n*n - n + 1);
            // bottom right n * n - 1
        // edge cases: three edges
            for (int i = 1; i < n - 1; i++) {
            // top vertices  (edges to: + 1 / +n)
                M.addEdge(i, i+1);
                M.addEdge(i, i+n);
            // left: i*n (edges to: + n/ + 1)
                M.addEdge(i*n, i*n + n);
                M.addEdge(i*n, i*n + 1);
            // right:  i * n + n - 1 (edges to: + n)
                M.addEdge(i * n + n - 1, i * n + n - 1 + n);
            // bottom n * n - n + i  (edges to:  - 1 / + 1)
                M.addEdge(n*n - n + i,n*n - n + i + 1);
            }
        // inner cases: four edges
        for (int x = 1; x < n - 1; x++ ) {
            for (int y = 1; y < n - 1; y++) {
                // right edge
                M.addEdge(y*n+x, y*n+x+1);
                // bottom edge
                M.addEdge(y*n+x, y*n+x+n);
            }
        }
        return M;

    }
    
    /**
     * Builds a random maze as a graph.
     * The maze is build with a randomized DFS as the Graph M.
     */
    private void buildMaze() {
		// TODO
        // mazegrid
        Graph g = mazegrid();
        // apply random dps on Graph G
        RandomDepthFirstPaths dfs = new RandomDepthFirstPaths(g, startnode);
        //
        dfs.randomNonrecursiveDFS(g);

        int index = 0;
        for (int parent: dfs.edge()) {
            if (index != 0) {
            this.M.addEdge(index++, parent);
            } else {
                index++;
            }
        }

    }

    /**
     * Find a path from node v to w
     * @param v start node
     * @param w end node
     * @return List<Integer> -- a list of nodes on the path from v to w (both included) in the right order.
     */
    public List<Integer> findWay(int v, int w){
		// TODO
        RandomDepthFirstPaths dfs = new RandomDepthFirstPaths(this.M, w);
        //
        dfs.randomNonrecursiveDFS(this.M);
        return dfs.pathTo(v);
    }
    
    /**
     * @return Graph M
     */
    public Graph M() {
    	return M;
    }

    public static void main(String[] args) {
		// FOR TESTING
        int n = 300;
        Maze m = new Maze(n, 0);
//        GridGraph vis= new GridGraph(m.M);
        GridGraph vis= new GridGraph(m.M, m.findWay(0, m.M().E()-1));
    }


}

