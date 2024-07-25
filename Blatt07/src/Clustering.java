import java.util.*;
import java.awt.Color;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.HashMap;

/**
 * This class solves a clustering problem with the Prim algorithm.
 */
public class Clustering {
	EdgeWeightedGraph G;
	List <List<Integer>>clusters; 
	List <List<Integer>>labeled; 
	
	/**
	 * Constructor for the Clustering class, for a given EdgeWeightedGraph and no labels.
	 * @param G a given graph representing a clustering problem
	 */
	public Clustering(EdgeWeightedGraph G) {
            this.G=G;
	    clusters= new LinkedList <List<Integer>>();
	}
	
    /**
	 * Constructor for the Clustering class, for a given data set with labels
	 * @param in input file for a clustering data set with labels
	 */
	public Clustering(In in) {
            int V = in.readInt();
            int dim= in.readInt();
            G= new EdgeWeightedGraph(V);
            labeled=new LinkedList <List<Integer>>();
            LinkedList labels= new LinkedList();
            double[][] coord = new double [V][dim];
            for (int v = 0;v<V; v++ ) {
                for(int j=0; j<dim; j++) {
                	coord[v][j]=in.readDouble();
                }
                String label= in.readString();
                    if(labels.contains(label)) {
                    	labeled.get(labels.indexOf(label)).add(v);
                    }
                    else {
                    	labels.add(label);
                    	List <Integer> l= new LinkedList <Integer>();
                    	labeled.add(l);
                    	labeled.get(labels.indexOf(label)).add(v);
                    	System.out.println(label);
                    }                
            }
             
            G.setCoordinates(coord);
            for (int w = 0; w < V; w++) {
                for (int v = 0;v<V; v++ ) {
                	if(v!=w) {
                	double weight=0;
                    for(int j=0; j<dim; j++) {
                    	weight= weight+Math.pow(G.getCoordinates()[v][j]-G.getCoordinates()[w][j],2);
                    }
                    weight=Math.sqrt(weight);
                    Edge e = new Edge(v, w, weight);
                    G.addEdge(e);
                	}
                }
            }
	    clusters= new LinkedList <List<Integer>>();
	}

	private void connectedComponents(Iterable<Edge> mst_edges) {
		// connecting
		UF clusterUF = new UF(G.V());
		mst_edges.forEach(mst_edge -> {
			clusterUF.union(mst_edge.either(), mst_edge.other(mst_edge.either()));
		});
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		// put the element in the same group of the elements of the same parent
		for (int i = 0; i < G.V(); i++) {
			int parentGroup = clusterUF.find(i);

		if (map.containsKey(parentGroup)) {
			if (!map.get(parentGroup).contains(i)) {
				map.get(parentGroup).add(i);
			}
		} else {
			List<Integer> new_list = new ArrayList<>();
			new_list.add(i);
			map.put(parentGroup, new_list);
		}
		}
		this.clusters.clear();
		map.forEach((group,vertices) -> {
			this.clusters.add(new ArrayList<>(vertices));
		});
		// sort
		for (List<Integer> cluster : this.clusters) {
			Collections.sort(cluster);
		}
		}


    /**
	 * This method finds a specified number of clusters based on a MST.
	 *
	 * It is based in the idea that removing edges from a MST will create a
	 * partition into several connected components, which are the clusters.
	 * @param numberOfClusters number of expected clusters
	 */
	public void findClusters(int numberOfClusters){

		// edge cases for 0
		if (numberOfClusters == 0) return;

		PrimMST mst = new PrimMST(this.G);
		Iterable<Edge> mst_edges = mst.edges();
		List<Edge> edgesList = new ArrayList<>();
		mst_edges.forEach(edgesList::add);

		// sort the list in descending order
		edgesList.sort((e1, e2) -> Double.compare(e2.weight(), e1.weight()));
		// remove the largest numberOfClusters - 1 edges
		for (int i = 0; i < numberOfClusters - 1; i++) {
			edgesList.remove(0); // Removes the largest edge.
		}
		// helper function for clustering using Union Find
		connectedComponents(edgesList);
	}
	
	/**
	 * This method finds clusters based on a MST and a threshold for the coefficient of variation.
	 *
	 * It is based in the idea that removing edges from a MST will create a
	 * partition into several connected components, which are the clusters.
	 * The edges are removed based on the threshold given. For further explanation see the exercise sheet.
	 *
	 * @param threshold for the coefficient of variation
	 */
	public void findClusters(double threshold){
		// create array list and cast as collection
		PrimMST mst = new PrimMST(G);
		Iterable<Edge> mst_edges = mst.edges();

		LinkedList<Edge> edgesList = new LinkedList<>();
		mst_edges.forEach(edgesList::add);

		Collections.sort(edgesList);
		// remove the element over Coefficient of Variation
		while(coefficientOfVariation(edgesList) > threshold) edgesList.removeLast();

		// UF & clustering
		connectedComponents(edgesList);
	}


	/**
	 * Evaluates the clustering based on a fixed number of clusters.
	 * @return array of the number of the correctly classified data points per cluster
	 */
	public int[] validation() {
		// TODO
		int[] correctlyClassified = new int[labeled.size()];
		for (int i = 0; i < clusters.size(); i++) {
			List<Integer> innerListClustered = clusters.get(i);
			List<Integer> innerListLabeled = labeled.get(i);
			int correctCount = 0;

			for (int dataPoint : innerListLabeled) {
				if (innerListClustered.contains(dataPoint)) { // Check if the dataPoint is correctly classified
					correctCount++;
				}
			}
			correctlyClassified[i] = correctCount;
		}
		return correctlyClassified;

	}
	
	/**
	 * Calculates the coefficient of variation.
	 * For the formula see exercise sheet.
	 * @param part list of edges
	 * @return coefficient of variation
	 */
	public double coefficientOfVariation(List <Edge> part) {
		if (part.isEmpty()) return 0;
		// declare sum of weights
		double sumOfWeights = 0.0;
		// declare
		for (Edge each: part) {
			sumOfWeights += each.weight();
		}
		double mean = sumOfWeights / part.size();
		// iterate over the List of Edges
		// calculate standard deviation of all elements weights
		double standardDeviation = 0.0;
		for (Edge each: part) {
			standardDeviation += Math.pow(each.weight() - mean, 2);
		}
		standardDeviation = Math.sqrt(standardDeviation / part.size());

		return standardDeviation / mean;
	}
	
	/**
	 * Plots clusters in a two-dimensional space.
	 */
	public void plotClusters() {
		int canvas=800;
	    StdDraw.setCanvasSize(canvas, canvas);
	    StdDraw.setXscale(0, 15);
	    StdDraw.setYscale(0, 15);
	    StdDraw.clear(new Color(0,0,0));
		Color[] colors= {new Color(255, 255, 255), new Color(128, 0, 0), new Color(128, 128, 128), 
				new Color(0, 108, 173), new Color(45, 139, 48), new Color(226, 126, 38), new Color(132, 67, 172)};
	    int color=0;
		for(List <Integer> cluster: clusters) {
			if(color>colors.length-1) color=0;
		    StdDraw.setPenColor(colors[color]);
		    StdDraw.setPenRadius(0.02);
		    for(int i: cluster) {
		    	StdDraw.point(G.getCoordinates()[i][0], G.getCoordinates()[i][1]);
		    }
		    color++;
	    }
	    StdDraw.show();
	}
	

    public static void main(String[] args) {
		// FOR TESTING
		In in;
		try {
			in = new In("./graph_small.txt");
			while (!in.isEmpty()) {
				String s = in.readLine();
				System.out.println(s);
			}
			EdgeWeightedGraph graph = new EdgeWeightedGraph(in);
			Clustering c = new Clustering(graph);

			c.findClusters(2);
			c.plotClusters();
		}
		catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		System.out.println();


    }
}

