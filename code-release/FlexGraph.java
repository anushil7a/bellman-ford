//TODO:
//  (1) Implement the graph!
//  (2) Update this code to meet the style and JavaDoc requirements.
//			Why? So that you get experience with the code for a graph!
//			Also, this happens a lot in industry (updating old code
//			to meet your new standards).

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.Hypergraph;
import edu.uci.ics.jung.graph.DirectedGraph;

import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.graph.util.EdgeType;

import org.apache.commons.collections15.Factory;

//import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
//import java.util.List;
import java.util.ListIterator;
//import java.util.Set;

/**
 * A class that implements a graph with nodes and edges. It is a directed graph.
 * The graph is implemented using an adjacency list. Its index is the node's id. 
 * The values are a list of connections. The first connection is the node itself.
 * The rest of the connections are the edges that connect the node to other nodes.
 */
class FlexGraph implements Graph<GraphNode,GraphEdge>, DirectedGraph<GraphNode,GraphEdge> {
	//HINTS:
	//1 -- Learn about what methods are available in Java's LinkedList
	//	 class before trying this, a lot of them will come in handy...
	//2 -- You may want to become friendly with the ListIterator as well. 
	//	 This iterator support things beyond Iterator, e.g. removal...

	
	//you may not have any other class variables, only this one
	//if you make more class variables your graph class will receive a 0,
	//no matter how well it works
	/**
	 * The maximum number of nodes in the graph.
	 */
	private static final int MAX_NUMBER_OF_NODES = 200;

	//you may not have any other instance variables, only this one
	//if you make more instance variables your graph class will receive a 0,
	//no matter how well it works
	/**
	 * The adjacency list of the graph. Holds the connections between nodes.
	 */
	private LinkedList<Connection>[] adjList = null;
	
	
	//a (destination,edge) to store in the adjacency list
	//note: source is indicated by the first node of each list
	//you may not edit this inner private class	
	/**
	 * A class to store the connection between a node and an edge. 
	 * It is used to store the connections in the adjacency list.
	 * It is a private inner class of FlexGraph.
	 */
	private class Connection {
		/**
		 * The node that is connected to the edge.
		 */
		GraphNode node;
		/**
		 * The edge that connects the node to another node.
		 */
		GraphEdge edge;
		/**
		 * The only constructor for the Connection class.
		 * @param n the node that is connected to the edge
		 * @param e the edge that connects the node to another node
		 */
		Connection(GraphNode n, GraphEdge e) { this.node = n; this.edge = e; }
		
	}
	
	//this is the only allowed constructor
	/**
	 * This constructor creates a new FlexGraph object.
	 * It initializes the adjacency list. Each index of the list is a node. I used a LinkedList to store the connections.
	 */
	@SuppressWarnings("unchecked")
	public FlexGraph() {
		//reminder: you can NOT do this: ClassWithGeneric<T>[] items = (ClassWithGeneric<T>[]) new Object[10];
		//you must use this format instead: ClassWithGeneric<T>[] items = (ClassWithGeneric<T>[]) new ClassWithGeneric[10];
		this.adjList = (LinkedList<Connection>[]) new LinkedList[MAX_NUMBER_OF_NODES];
		for(int i=0; i<MAX_NUMBER_OF_NODES; i++) {
			this.adjList[i] = new LinkedList<Connection>();
		}

	}	


	/**
	 * Returns a view of all edges in this graph. In general, this
	 * obeys the Collection contract, and therefore makes no guarantees 
	 * about the ordering of the edges within the set.
	 * @return a Collection view of all edges in this graph
	 */
	public Collection<GraphEdge> getEdges() {
		//Hint: this method returns a Collection, look at the imports for
		//what collections you could return here.

		//O(n+e) where e is the number of edges in the graph and 
		//n is the max number of nodes in the graph
		
		//
		//return 'PP';


		Collection<GraphEdge> edges = new LinkedList<GraphEdge>();

		for(int i = 0; i < MAX_NUMBER_OF_NODES; i++){
			if(adjList[i] != null && adjList[i].size() > 0){
				for(Connection c : adjList[i]){
					if(c.edge != null){
						edges.add(c.edge);
					}
				}
			}
		}
		//System.out.println(edges);	
		return edges; //default return, remove or change as needed
	}
	
	/**
	 * Returns a view of all vertices in this graph. In general, this
	 * obeys the Collection contract, and therefore makes no guarantees 
	 * about the ordering of the vertices within the set.
	 * @return a Collection view of all vertices in this graph
	 */
	public Collection<GraphNode> getVertices() {
		//Hint: this method returns a Collection, look at the imports for
		//what collections you could return here.
		//O(n) where n is the max number of nodes.


		//Set<GraphNode> vertices = new HashSet<GraphNode>();
		//List<GraphNode> vertices = new ArrayList<GraphNode>();
		Collection<GraphNode> vertices = new LinkedList<GraphNode>();

		for(int i = 0; i < MAX_NUMBER_OF_NODES; i++){
			if(adjList[i] != null && adjList[i].size() > 0){
				//System.out.println(adjList[i].get(0).node.getId());
				vertices.add(adjList[i].get(0).node);
			}
		}		
		return vertices; //default return, remove or change as needed
		
	}
	
	/**
	 * Returns the number of edges in this graph.
	 * @return the number of edges in this graph
	 */
	public int getEdgeCount() {
		//O(n) where n is the max number of nodes in the graph
		//note: this is NOT O(n+e), just O(n)
		
		//Note: this runtime is not a mistake, think about how
		//you could find out the number of edges *without*
		//looking at each one
		int count = 0;

		for(int i = 0; i < MAX_NUMBER_OF_NODES; i++){
			if(adjList[i] != null && adjList[i].size() > 0){
				count += adjList[i].size()-1;
			}
		}
		return count; //default return, remove or change as needed
	}

	/**
	 * Returns the number of vertices in this graph.
	 * @return the number of vertices in this graph
	 */
	public int getVertexCount() {
		//O(n) where n is the max number of nodes in the graph
		int count = this.getVertices().size();
		return count; //default return, remove or change as needed
	}


	/**
	 * Returns true if this graph's vertex collection contains vertex.
	 * Equivalent to getVertices().contains(vertex).
	 * @param vertex the vertex whose presence is being queried
	 * @return true iff this graph contains a vertex vertex
	 */
	public boolean containsVertex(GraphNode vertex) {
		//O(1) -- NOT O(n)!
		
		//Note: this runtime is not a mistake, look at
		//the storage overview in the project description for ideas
		
		if(vertex == null){
			return false;
		}
		if(vertex.getId() < 0 || vertex.getId() >= MAX_NUMBER_OF_NODES){
			return false;
		}
		if(adjList[vertex.getId()] != null && adjList[vertex.getId()].size() > 0){
			return true;
		}
		return false; //default return, remove or change as needed
		
	}
	 
	
	/**
	 * Returns a Collection view of the incoming edges incident to vertex
	 * in this graph.
	 * @param vertex	the vertex whose incoming edges are to be returned
	 * @return  a Collection view of the incoming edges incident 
	 to vertex in this graph
	 */
	public Collection<GraphEdge> getInEdges(GraphNode vertex) {
		//if vertex not present in graph, return null

		//O(n+e) where e is the number of edges in the graph and 
		//n is the max number of nodes in the graph
		if(this.containsVertex(vertex) == false){
			return null;
		}
		//List<GraphEdge> arr = new ArrayList<GraphEdge>();
		Collection<GraphEdge> arr = new LinkedList<GraphEdge>();

		for(int i = 0; i < MAX_NUMBER_OF_NODES; i++){
			if(adjList[i] != null && adjList[i].size() > 0){
				for(Connection c : adjList[i]){
					if(c.node.equals(vertex) && c.edge != null){
						arr.add(c.edge);
					}
				}
			}
		}
		return arr; //default return, remove or change as needed
		
	}
	
	/**
	 * Returns a Collection view of the outgoing edges incident to vertex
	 * in this graph.
	 * @param vertex	the vertex whose outgoing edges are to be returned
	 * @return  a Collection view of the outgoing edges incident to vertex in this graph
	 */
	public Collection<GraphEdge> getOutEdges(GraphNode vertex) {
		//if vertex not present in graph, return null
		if(this.containsVertex(vertex) == false){
			return null;
		}
		//List<GraphEdge> arr = new ArrayList<GraphEdge>();
		Collection<GraphEdge> arr = new LinkedList<GraphEdge>();

		for(Connection c : adjList[vertex.getId()]){
			if(c.edge != null){
				arr.add(c.edge);
			}
		}

		//O(e) where e is the number of edges in the graph
		return arr; //default return, remove or change as needed

	}

	/**
	 * Returns the number of incoming edges incident to vertex.
	 * Equivalent to getInEdges(vertex).size().
	 * @param vertex	the vertex whose indegree is to be calculated
	 * @return  the number of incoming edges incident to vertex
	 */
	public int inDegree(GraphNode vertex) {
		
		//if vertex not present in graph, return -1
		
		//O(n+e) where e is the number of edges in the graph and 
		//n is the max number of nodes in the graph
		
		if(this.containsVertex(vertex) == false){
			return -1;
		}
		int count = this.getInEdges(vertex).size();
		return count; //default return, remove or change as needed
	}
	
	/**
	 * Returns the number of outgoing edges incident to vertex.
	 * Equivalent to getOutEdges(vertex).size().
	 * @param vertex	the vertex whose outdegree is to be calculated
	 * @return  the number of outgoing edges incident to vertex
	 */
	public int outDegree(GraphNode vertex) {
		//if vertex not present in graph, return -1
		//O(1) 
		if(this.containsVertex(vertex) == false){
			return -1;
		}
		int count = this.getOutEdges(vertex).size();
		return count; //default return, remove or change as needed
	}


	/**
	 * Returns a Collection view of the predecessors of vertex 
	 * in this graph.  A predecessor of vertex is defined as a vertex v 
	 * which is connected to 
	 * vertex by an edge e, where e is an outgoing edge of 
	 * v and an incoming edge of vertex.
	 * @param vertex	the vertex whose predecessors are to be returned
	 * @return  a Collection view of the predecessors of vertex in this graph
	 */
	public Collection<GraphNode> getPredecessors(GraphNode vertex) {
		//if vertex not present in graph, return null
		if(this.containsVertex(vertex) == false){
			return null;
		}
		else{

			//List<GraphNode> arr = new ArrayList<GraphNode>();
			Collection<GraphNode> arr = new LinkedList<GraphNode>();
			for(int i = 0; i < MAX_NUMBER_OF_NODES; i++){
				if(adjList[i] != null && adjList[i].size() > 0){
					for(Connection c : adjList[i]){
						if(c.node.equals(vertex) && c.edge != null){
							arr.add(adjList[i].get(0).node);
						}
					}
				}
			}
		
			return arr; //default return, remove or change as needed
		 //O(n+e) where e is the number of edges in the graph and n is the max number of nodes in the graph
		}
	}
	
	/**
	 * Returns a Collection view of the successors of vertex 
	 * in this graph.  A successor of vertex is defined as a vertex v 
	 * which is connected to 
	 * vertex by an edge e, where e is an incoming edge of 
	 * v and an outgoing edge of vertex.
	 * @param vertex	the vertex whose predecessors are to be returned
	 * @return  a Collection view of the successors of vertex in this graph
	 */
	public Collection<GraphNode> getSuccessors(GraphNode vertex) {
		//if vertex not present in graph, return null
		//O(e) where e is the number of edges in the graph
		if(this.containsVertex(vertex) == false){
			return null;
		}
		else{
			//List<GraphNode> arr = new ArrayList<GraphNode>();
			Collection<GraphNode> arr = new LinkedList<GraphNode>();
		
			for(Connection c : adjList[vertex.getId()]){
				if(c.edge != null){
					arr.add(c.node);
				}
			}
			return arr; //default return, remove or change as needed
		}
	}
	
	/**
	 * Returns true if v1 is a predecessor of v2 in this graph.
	 * @param v1 the first vertex to be queried
	 * @param v2 the second vertex to be queried
	 * @return true if v1 is a predecessor of v2, and false otherwise.
	 */
	public boolean isPredecessor(GraphNode v1, GraphNode v2) {
		//O(e) where e is the number of edges in the graph
		if(this.containsVertex(v1) == false || this.containsVertex(v2) == false){
			return false;
		}
		else{
			Collection<GraphNode> arr = this.getPredecessors(v2);
			if(arr.contains(v1) == true){
				return true;
			}
		}
		return false; //default return, remove or change as needed	
	}
	
	/**
	 * Returns true if v1 is a successor of v2 in this graph.
	 * @param v1 the first vertex to be queried
	 * @param v2 the second vertex to be queried
	 * @return true if v1 is a successor of v2, and false otherwise.
	 */
	public boolean isSuccessor(GraphNode v1, GraphNode v2) {
		//O(e) where e is the number of edges in the graph
		if(this.containsVertex(v1) == false || this.containsVertex(v2) == false){
			return false;
		}
		else{
			Collection<GraphNode> arr = this.getSuccessors(v2);
			if(arr.contains(v1) == true){
				return true;
			}
		}
		return false; //default return, remove or change as needed
	}
	
	/**
	 * Returns the collection of vertices which are connected to vertex
	 * via any edges in this graph.
	 * If vertex is connected to itself with a self-loop, then 
	 * it will be included in the collection returned.
	 * 
	 * @param vertex the vertex whose neighbors are to be returned
	 * @return  the collection of vertices which are connected to vertex, or null if vertex is not present
	 */
	public Collection<GraphNode> getNeighbors(GraphNode vertex) {
		//O(n^2) where n is the max number of vertices in the graph
		//NOTE: there should be no duplicates in the neighbor list.
		if(vertex == null || this.containsVertex(vertex) == false){
			return null;
		}
		else{
			//List<GraphNode> arr = new ArrayList<GraphNode>();
			Collection<GraphNode> arr = new LinkedList<GraphNode>();

			Collection<GraphNode> pre = this.getPredecessors(vertex);
			Collection<GraphNode> suc = this.getSuccessors(vertex);


			if(pre != null){
				for(GraphNode i : pre){
					if(arr.contains(i) == false){
						arr.add(i);
					}
				}
			}
			if(suc != null){
				for(GraphNode i : suc){
					if(arr.contains(i) == false){
						arr.add(i);
					}
				}
			}
		 //the self loop checker (check if it does this)
			return arr; //default return, remove or change as needed
		}
	}


	/**
	 * If directedEdge is a directed edge in this graph, returns the source; 
	 * otherwise returns null. 
	 * The source of a directed edge d is defined to be the vertex for which  
	 * d is an outgoing edge.
	 * directed_edge is guaranteed to be a directed edge if 
	 * its EdgeType is DIRECTED. 
	 * @param directedEdge the edge to get the source of
	 * @return  the source of directed_edge if it is a directed edge in this graph, or null otherwise
	 */
	public GraphNode getSource(GraphEdge directedEdge) {
		//O(n+e) where n is the max number of vertices in the graph
		// and e is the number of edges in the graph
		if(directedEdge == null){
			return null;
		}
		for(int i = 0; i < MAX_NUMBER_OF_NODES; i++){
			if(adjList[i] != null && adjList[i].size() > 0){
				for(Connection c : adjList[i]){
					if(c.edge != null && c.edge.equals(directedEdge)){
						return adjList[i].get(0).node;
					}
				}
			}
		}
		
		return null; //default return, remove or change as needed
	}

	/**
	 * If directedEdge is a directed edge in this graph, returns the destination; 
	 * otherwise returns null. 
	 * The destination of a directed edge d is defined to be the vertex 
	 * incident to d for which  
	 * d is an incoming edge.
	 * directed_edge is guaranteed to be a directed edge if 
	 * its EdgeType is DIRECTED. 
	 * @param directedEdge the edge to get the destination of
	 * @return  the destination of directed_edge if it is a directed edge in this graph, or null otherwise
	 */
	public GraphNode getDest(GraphEdge directedEdge) {
		//O(n+e) where n is the max number of vertices in the graph
		// and e is the number of edges in the graph
		if(directedEdge == null){
			return null;
		}

		for(int i = 0; i < MAX_NUMBER_OF_NODES; i++){
			if(adjList[i] != null && adjList[i].size() > 0){
				for(Connection c : adjList[i]){
					if(c.edge != null && c.edge.equals(directedEdge)){//might not need the c.edge != null
						return c.node;
					}
				}
			}
		}
		
		return null; //default return, remove or change as needed   	
	}
  
	
	/**
	 * Returns an edge that connects v1 to v2.
	 * If this edge is not uniquely
	 * defined (that is, if the graph contains more than one edge connecting 
	 * v1 to v2), any of these edges 
	 * may be returned.  findEdgeSet(v1, v2) may be 
	 * used to return all such edges.
	 * Returns null if either of the following is true:
	 * <ul>
	 * <li/>v1 is not connected to v2
	 * <li/>either v1 or v2 are not present in this graph
	 * </ul> 
	 * <b>Note</b>: for purposes of this method, v1 is only considered to be connected to
	 * v2 via a given <i>directed</i> edge e if
	 * v1 == e.getSource() && v2 == e.getDest() evaluates to true.
	 * (v1 and v2 are connected by an undirected edge u if 
	 * u is incident to both v1 and v2.)
	 * @param v1 the first vertex of the edge to get
	 * @param v2 the second vertex of the edge to get
	 * @return  an edge that connects v1 to v2, or null if no such edge exists (or either vertex is not present)
	 * @see Hypergraph#findEdgeSet(Object, Object) 
	 */
	public GraphEdge findEdge(GraphNode v1, GraphNode v2) {
		//O(e) where e is the number of edges in the graph
		if(this.containsVertex(v1) == false || this.containsVertex(v2) == false){
			return null;
		}

		for(Connection c : adjList[v1.getId()]){
			if(c.node.equals(v2) && c.edge != null){
				return c.edge;
			}
		}
		return null; //default return, remove or change as needed   	
		
	}

	/**
	 * Returns true if vertex and edge 
	 * are incident to each other.
	 * Equivalent to getIncidentEdges(vertex).contains(edge) and to
	 * getIncidentVertices(edge).contains(vertex).
	 * @param vertex the vertex to test
	 * @param edge the edge to test
	 * @return true if vertex and edge are incident to each other
	 */
	public boolean isIncident(GraphNode vertex, GraphEdge edge) {
		//O(n+e) where n is the max number of vertices in the graph
		// and e is the number of edges in the graph
		if(this.containsVertex(vertex) == false || this.containsEdge(edge) == false){
			return false;
		}

		if(this.getIncidentEdges(vertex).contains(edge) == true && this.getIncidentVertices(edge).contains(vertex) == true){
			return true;
		}
		return false; //default return, remove or change as needed
		
	}



	/**
	 * Adds edge e to this graph such that it connects 
	 * vertex v1 to v2.
	 * Equivalent to {@code addEdge(e, new Pair<GraphNode, GraphNode>(v1, v2))}.
	 * If this graph does not contain v1, v2, 
	 * or both, implementations may choose to either silently add 
	 * the vertices to the graph or throw an IllegalArgumentException.
	 * If this graph assigns edge types to its edges, the edge type of
	 * e will be the default for this graph.
	 * See Hypergraph.addEdge() for a listing of possible reasons
	 * for failure.
	 * @param e the edge to be added
	 * @param v1 the first vertex to be connected
	 * @param v2 the second vertex to be connected
	 * @return true if the add is successful, false otherwise
	 * @see Hypergraph#addEdge(Object, Collection)
	 * @see #addEdge(Object, Object, Object, EdgeType)
	 */
	public boolean addEdge(GraphEdge e, GraphNode v1, GraphNode v2) {
		//remember we do not allow self-loops nor parallel edges
		if(e == null || v1 == null || v2 == null){
			return false;
		}
		if(this.containsVertex(v1) == false || this.containsVertex(v2) == false){
			throw new IllegalArgumentException("Vertex is not in the graph");
		}
		if(v1.equals(v2)){
			return false;
		}
		if(this.findEdge(v1, v2) != null){
			return false;
		}
		
		//O(n+e) where n is the max number of vertices in the graph
		// and e is the number of edges in the graph
		adjList[v1.getId()].add(new Connection(v2,e));


		return true; //default return, remove or change as needed

	}
	
	/**
	 * Adds vertex to this graph.
	 * Fails if vertex is null or already in the graph.
	 * 
	 * @param vertex	the vertex to add
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException if vertex is null
	 */
	public boolean addVertex(GraphNode vertex) {
		if(vertex == null){
			throw new IllegalArgumentException("Vertex is null");
		}
		if(this.containsVertex(vertex) == true){
			return false;
		}
		if(vertex.getId() < 0 || vertex.getId() >= MAX_NUMBER_OF_NODES){
			return false;
		}

		this.adjList[vertex.getId()] = new LinkedList<Connection>();
		this.adjList[vertex.getId()].add(new Connection(vertex,null));
		return true; //default return, remove or change as needed
	}

	/**
	 * Removes edge from this graph.
	 * Fails if edge is null, or is otherwise not an element of this graph.
	 * 
	 * @param edge the edge to remove
	 * @return true if the removal is successful, false otherwise
	 */
	public boolean removeEdge(GraphEdge edge) {
		//O(n+e) where n is the max number of vertices in the graph
		// and e is the number of edges in the graph
		if(edge == null){
			return false;
		}

		for(int i = 0; i < MAX_NUMBER_OF_NODES; i++){
			if(adjList[i] != null && adjList[i].size() > 0){
				ListIterator<Connection> iter = adjList[i].listIterator();//have to use while because we are removing
				while(iter.hasNext()){
					Connection c = iter.next();
					if(c.edge != null && c.edge.equals(edge)){
						iter.remove();
						return true;
					}
				}
			}
		}
		return false; //default return, remove or change as needed
	}
	
	/**
	 * Removes vertex from this graph.
	 * As a side effect, removes any edges e incident to vertex if the 
	 * removal of vertex would cause e to be incident to an illegal
	 * number of vertices.  (Thus, for example, incident hyperedges are not removed, but 
	 * incident edges--which must be connected to a vertex at both endpoints--are removed.) 
	 * 
	 * <p>Fails under the following circumstances:
	 * <ul>
	 * <li/>vertex is not an element of this graph
	 * <li/>vertex is null
	 * </ul>
	 * 
	 * @param vertex the vertex to remove
	 * @return true if the removal is successful, false otherwise
	 */
	public boolean removeVertex(GraphNode vertex) {
		//O(n+e) where n is the max number of vertices in the graph
		// and e is the number of edges in the graph
		if(vertex == null){
			return false;
		}
		if(this.containsVertex(vertex) == false){
			return false;
		}

		//List<GraphEdge> out = new ArrayList<>(getOutEdges(vertex));
		Collection<GraphEdge> out = this.getOutEdges(vertex);
		//List<GraphEdge> in = new ArrayList<>(getInEdges(vertex));
		Collection<GraphEdge> in = this.getInEdges(vertex);

		for(GraphEdge e : out){
			this.removeEdge(e);
		}
		for(GraphEdge e : in){
			this.removeEdge(e);
		}
		adjList[vertex.getId()] = null;
		return true; //default return, remove or change as needed
				
	}
	
	/**
	 *  Returns a string of the depth first traversal of the graph from the given vertex. 
	 *  If vertex is null or not present in graph, return an empty string.
	 *  Otherwise, return the string with node IDs in the depth first traversal order, 
	 *  separated by a single space.  
	 *  When there are multiple outgoing paths to continue for a node, follow the storage
	 *  order to decide which one to pick, i.e. you should process the connections in the 
	 *  adjacency list from start to end.
	 *  Check the example in main() below.
	 *
	 *  @param vertex the starting vertex of the depth first traversal, may be null
	 *  @return a string representation of the depth first traversal, or an empty string if vertex is null or not present
	 */

	public String depthFirstTraversal(GraphNode vertex){//can work on later(not needed for Bellman-Ford)
	
		//Hint: feel free to define private helper method
		//Use StringBuilder!
		
		//O(n+e) where n is the max number of vertices in the graph
		// and e is the number of edges in the graph

		if(vertex == null || this.containsVertex(vertex) == false){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		boolean[] visited = new boolean[MAX_NUMBER_OF_NODES];
		helperForDFS(vertex,sb, visited);

		return sb.toString();
	}

	/**
	 * Helper method for depthFirstTraversal. 
	 * We use this method to traverse the graph in a depth first manner.
	 * If the vertex is null or not present in the graph, return an empty string.
	 * Otherwise, return the string with node IDs in the depth first traversal order,
	 * separated by a single space. Then loop through the connections of the vertex
	 * and recursively call the helper method on the connections.
	 * @param vertex the starting vertex of the depth first traversal
	 * @param sb the StringBuilder object to store the traversal
	 * @param visited the boolean array to keep track of visited nodes
	 */
	private void helperForDFS(GraphNode vertex, StringBuilder sb, boolean[] visited){
		if(vertex != null && visited[vertex.getId()] == false){
			visited[vertex.getId()] = true;
			sb.append(vertex.getId() + " ");
			
			//System.out.println("Visited " + vertex.getId());
			for(Connection c : adjList[vertex.getId()]){
				if(this.containsVertex(vertex) == false){
					return;
				}
				if(c.node != null && visited[c.node.getId()] == false && c.node != vertex){
					helperForDFS(c.node,sb,visited);
				}
				//System.out.println("Visited " + vertex.getId());
			}
		}
	}


	/**
	 *  Counts the number of nodes that are reachable from the given vertex, (did not complete fully)
	 *  and the number of nodes that can reach the given vertex in graph.
	 *  Returns a pair of the two integer counters as the answer.
	 *  If vertex is null or not present, return null.
	 *  Check the example in main() below.
	 *
	 *  @param vertex the node we check for reachable feature
	 *  @return a pair of integer counters
	 */

	public IntPair countReachable(GraphNode vertex){//can work on later(not needed for Bellman-Ford)
		//O(n+e) where n is the max number of vertices in the graph
		// and e is the number of edges in the graph
		
		//Note: big-O is not O(n(n+e))
		//Re-check zyBookCh14 for graph traversals if you need more hints ...
		if(vertex == null || this.containsVertex(vertex) == false){
			return null;
		}
		IntPair pair = new IntPair(0,0);

		return pair;		
	}
	
		
	
	//********************************************************************************
	//   testing code goes here... edit this as much as you want!
	//********************************************************************************
	
	/**
	 * A main method to test the FlexGraph class. We test every method in the class. I commented out the ones that 
	 * took a lot of spaces.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		//create a set of nodes and edges to test with
		GraphNode[] nodes = {
			new GraphNode(0), 
			new GraphNode(1), 
			new GraphNode(2), 
			new GraphNode(3), 
			new GraphNode(4), 
			new GraphNode(5), 
			new GraphNode(6), 
			new GraphNode(7), 
			new GraphNode(8), 
			new GraphNode(9)
		};
		
		GraphEdge[] edges = {
			new GraphEdge(0), 
			new GraphEdge(1), 
			new GraphEdge(2),
			new GraphEdge(3), 
			new GraphEdge(4), 
			new GraphEdge(5),
			new GraphEdge(6),
			new GraphEdge(7)
		};
		
		//constructs a graph
		FlexGraph graph = new FlexGraph();
		for(GraphNode n : nodes) {
			//System.out.println(n.getId());
			graph.addVertex(n);
		}
		
		graph.addEdge(edges[0],nodes[0],nodes[1]);
		graph.addEdge(edges[1],nodes[1],nodes[2]); //1-->2
		graph.addEdge(edges[2],nodes[3],nodes[6]);
		graph.addEdge(edges[3],nodes[6],nodes[7]);
		graph.addEdge(edges[4],nodes[8],nodes[9]);
		graph.addEdge(edges[5],nodes[9],nodes[0]);
		graph.addEdge(edges[6],nodes[2],nodes[7]);
		graph.addEdge(edges[7],nodes[1],nodes[8]); //1-->8

		//testing the basic methods
		System.out.println("Vertices: " + graph.getVertices());
		System.out.println("Vertices: " + graph.getVertexCount());
		System.out.println("Edges: " + graph.getEdges());
		System.out.println("Edges: " + graph.getEdgeCount());
		//check containsVertex using loop
		for(GraphNode n : nodes) {
			System.out.println("Contains vertex " + n + "? " + graph.containsVertex(n));
		}
		//check containsVertex using vertex not in graph
		System.out.println("Contains vertex " + new GraphNode(10) + "? " + graph.containsVertex(new GraphNode(10)));
		//check containsVertex using edge cases
		System.out.println("Contains vertex null? " + graph.containsVertex(null));
		//empty space
		System.out.println();

		//check getInEdges and getOutEdges
		for(GraphNode n : nodes) {
			System.out.println("In edges of " + n + ": " + graph.getInEdges(n));
			System.out.println("Out edges of " + n + ": " + graph.getOutEdges(n));
		}
		System.out.println();
		//check getInEdges and getOutEdges using vertex not in graph
		System.out.println("In edges of " + new GraphNode(10) + ": " + graph.getInEdges(new GraphNode(10)));
		System.out.println("Out edges of " + new GraphNode(10) + ": " + graph.getOutEdges(new GraphNode(10)));
		//check getInEdges and getOutEdges using edge cases
		System.out.println("In edges of null: " + graph.getInEdges(null));//check piazza and make sure we return null
		System.out.println("Out edges of null: " + graph.getOutEdges(null));
		//empty space
		System.out.println();

		//check inDegree and outDegree
		for(GraphNode n : nodes) {
			System.out.println("In degree of " + n + ": " + graph.inDegree(n));
			System.out.println("Out degree of " + n + ": " + graph.outDegree(n));
		}
		System.out.println();
		//check inDegree and outDegree using vertex not in graph
		System.out.println("In degree of " + new GraphNode(10) + ": " + graph.inDegree(new GraphNode(10)));
		System.out.println("Out degree of " + new GraphNode(10) + ": " + graph.outDegree(new GraphNode(10)));
		//check inDegree and outDegree using edge cases
		System.out.println("In degree of null: " + graph.inDegree(null));
		System.out.println("Out degree of null: " + graph.outDegree(null));
		//empty space
		System.out.println();

		//check getPredecessors and getSuccessors
		for(GraphNode n : nodes) {
			System.out.println("Predecessors of " + n + ": " + graph.getPredecessors(n));
			System.out.println("Successors of " + n + ": " + graph.getSuccessors(n));
		}
		System.out.println();
		//check getPredecessors and getSuccessors using vertex not in graph
		System.out.println("Predecessors of " + new GraphNode(10) + ": " + graph.getPredecessors(new GraphNode(10)));
		System.out.println("Successors of " + new GraphNode(10) + ": " + graph.getSuccessors(new GraphNode(10)));
		//check getPredecessors and getSuccessors using edge cases
		System.out.println("Predecessors of null: " + graph.getPredecessors(null));//check piazza and make sure we return null
		System.out.println("Successors of null: " + graph.getSuccessors(null));
		//empty space
		System.out.println();

		//check isPredecessor and isSuccessor
		/*for(GraphNode n1 : nodes) {
			for(GraphNode n2 : nodes) {
				System.out.println(n1 + " is predecessor of " + n2 + "? " + graph.isPredecessor(n1,n2));
				System.out.println(n1 + " is successor of " + n2 + "? " + graph.isSuccessor(n1,n2));
			}
		}
		System.out.println();
		//check isPredecessor and isSuccessor using vertex not in graph
		System.out.println(new GraphNode(10) + " is predecessor of " + new GraphNode(10) + "? " + graph.isPredecessor(new GraphNode(10),new GraphNode(10)));
		System.out.println(new GraphNode(10) + " is successor of " + new GraphNode(10) + "? " + graph.isSuccessor(new GraphNode(10),new GraphNode(10)));
		//check isPredecessor and isSuccessor using edge cases
		System.out.println("null is predecessor of null? " + graph.isPredecessor(null,null));
		System.out.println("null is successor of null? " + graph.isSuccessor(null,null));
		//empty space
		System.out.println();*/

		//check getNeighbors
		for(GraphNode n : nodes) {
			System.out.println("Neighbors of " + n + ": " + graph.getNeighbors(n));
		}
		System.out.println();
		//check getNeighbors using vertex not in graph
		System.out.println("Neighbors of " + new GraphNode(10) + ": " + graph.getNeighbors(new GraphNode(10)));
		//check getNeighbors using edge cases
		System.out.println("Neighbors of null: " + graph.getNeighbors(null));
		//empty space
		System.out.println();

		//check getSource and getDest
		for(GraphEdge e : edges) {
			System.out.println("Source of " + e + ": " + graph.getSource(e));
			System.out.println("Dest of " + e + ": " + graph.getDest(e));
		}
		System.out.println();
		//check getSource and getDest using edge not in graph
		System.out.println("Source of " + new GraphEdge(10) + ": " + graph.getSource(new GraphEdge(10)));
		System.out.println("Dest of " + new GraphEdge(10) + ": " + graph.getDest(new GraphEdge(10)));
		//check getSource and getDest using edge cases
		System.out.println("Source of null: " + graph.getSource(null));
		System.out.println("Dest of null: " + graph.getDest(null));
		//empty space
		System.out.println();

		//check findEdge
		/*for(GraphNode n1 : nodes) {
			for(GraphNode n2 : nodes) {
				System.out.println("Edge between " + n1 + " and " + n2 + ": " + graph.findEdge(n1,n2));
			}
		}
		System.out.println();
		//check findEdge using vertex not in graph
		System.out.println("Edge between " + new GraphNode(10) + " and " + new GraphNode(10) + ": " + graph.findEdge(new GraphNode(10),new GraphNode(10)));
		//check findEdge using edge cases
		System.out.println("Edge between null and null: " + graph.findEdge(null,null));
		//empty space
		System.out.println();*/

		//check isIncident
		/*for(GraphNode n : nodes) {
			for(GraphEdge e : edges) {
				System.out.println(n + " is incident to " + e + "? " + graph.isIncident(n,e));
			}
		}
		System.out.println();
		//check isIncident using vertex not in graph
		System.out.println(new GraphNode(10) + " is incident to " + new GraphEdge(10) + "? " + graph.isIncident(new GraphNode(10),new GraphEdge(10)));
		//check isIncident using edge not in graph
		System.out.println(new GraphNode(10) + " is incident to " + new GraphEdge(10) + "? " + graph.isIncident(new GraphNode(10),new GraphEdge(10)));
		//check isIncident using edge cases
		System.out.println("null is incident to null? " + graph.isIncident(null,null));
		//empty space
		System.out.println();*/



		//check removeEdge
		/*for(GraphEdge e : edges) {
			System.out.println("Remove edge " + e + "? " + graph.removeEdge(e));
			//System.out.println("Vertices: " + graph.getVertices());
			System.out.println("Edges: " + graph.getEdges());
		}
		System.out.println();
		//check removeEdge using edge not in graph
		System.out.println("Remove edge " + new GraphEdge(10) + "? " + graph.removeEdge(new GraphEdge(10)));
		//check removeEdge using edge cases
		System.out.println("Remove edge null? " + graph.removeEdge(null));
		//empty space
		System.out.println();
		
		//check removeVertex
		System.out.println("Vertices: " + graph.getVertices());
		System.out.println("Edges: " + graph.getEdges());
		for(GraphNode n : nodes) {
			System.out.println("Remove vertex " + n + "? " + graph.removeVertex(n));
			System.out.println("Vertices: " + graph.getVertices());
			System.out.println("Edges: " + graph.getEdges());
		}
		System.out.println();
		//check removeVertex using vertex not in graph
		System.out.println("Remove vertex " + new GraphNode(10) + "? " + graph.removeVertex(new GraphNode(10)));
		//check removeVertex using edge cases
		System.out.println("Remove vertex null? " + graph.removeVertex(null));
		//empty space
		System.out.println();*/

		
		if(graph.getVertexCount() == 10 && graph.getEdgeCount() == 8) {
			System.out.println("Yay 1");
		}
		
		if(graph.inDegree(nodes[0]) == 1 && graph.outDegree(nodes[1]) == 2) {
			System.out.println("Yay 2");
		}
		
		
		//lots more testing here...
		//If your graph "looks funny" you probably want to check:
		//getVertexCount(), getVertices(), getInEdges(vertex),
		//and getIncidentVertices(incomingEdge) first. These are
		//used by the layout class.


		//some testing for the advanced graph operations:
		for(GraphNode n : nodes) {
			System.out.println("Depth first traversal from " + n + ": " + graph.depthFirstTraversal(n).trim());
		}
		System.out.println();
		//check depthFirstTraversal using vertex not in graph
		System.out.println("Depth first traversal from " + new GraphNode(10) + ": " + graph.depthFirstTraversal(new GraphNode(10)).trim());
		
		if(graph.depthFirstTraversal(nodes[9]).trim().equals("9 0 1 2 7 8")) {
			System.out.println("Yay 3");
		}
		//NOTE: in traversal, after node 1, we visited node 2 before node 8
		//	  since edge 1-->2 was added into graph before edge 1-->8
		
		//System.out.println(graph.depthFirstTraversal(nodes[9]));

		//IntPair counts = graph.countReachable(nodes[1]);
		//if (counts.getFirst() == 5 && counts.getSecond() == 3){
		//	System.out.println("Yay 4");
		//}
		//System.out.println(graph.countReachable(nodes[0]));
		
		//again, many more testing by yourself...
		
	}
	

	//********************************************************************************
	//   YOU MAY, BUT DON'T NEED TO EDIT THINGS IN THIS SECTION
	//   NOTE: you do need to fix JavaDoc issues if there is any in this section.
	//********************************************************************************



	/**
	 * Returns the number of edges incident to vertex.  
	 * Special cases of interest:
	 * <ul>
	 * <li/> Incident self-loops are counted once.
	 * <li> If there is only one edge that connects this vertex to
	 * each of its neighbors (and vice versa), then the value returned 
	 * will also be equal to the number of neighbors that this vertex has
	 * (that is, the output of getNeighborCount).
	 * <li> If the graph is directed, then the value returned will be 
	 * the sum of this vertex's indegree (the number of edges whose 
	 * destination is this vertex) and its outdegree (the number
	 * of edges whose source is this vertex), minus the number of
	 * incident self-loops (to avoid double-counting).
	 * </ul>
	 * 
	 * <p>Equivalent to getIncidentEdges(vertex).size().
	 * @param vertex the vertex whose degree is to be returned
	 * @return the degree of this node
	 * @see Hypergraph#getNeighborCount(Object)
	 */
	public int degree(GraphNode vertex) {
		return inDegree(vertex) + outDegree(vertex);
	}

 /**
	 * Returns true if v1 and v2 share an incident edge.
	 * Equivalent to getNeighbors(v1).contains(v2).
	 * 
	 * @param v1 the first vertex to test
	 * @param v2 the second vertex to test
	 * @return true if v1 and v2 share an incident edge
	 */
	public boolean isNeighbor(GraphNode v1, GraphNode v2) {
		return (findEdge(v1, v2) != null || findEdge(v2, v1)!=null);
	}
	
	/**
	 * Returns the endpoints of edge as a {@code Pair<GraphNode>}.
	 * @param edge the edge whose endpoints are to be returned
	 * @return the endpoints (incident vertices) of edge
	 */
	public Pair<GraphNode> getEndpoints(GraphEdge edge) {
		if (edge==null) return null;
		
		GraphNode v1 = getSource(edge);
		if (v1==null)
			return null;
			
		GraphNode v2 = getDest(edge);
		if (v2==null)
			return null;
			
		return new Pair<GraphNode>(v1, v2);
	}


	/**
	 * Returns the collection of edges in this graph which are connected to vertex.
	 * 
	 * @param vertex the vertex whose incident edges are to be returned
	 * @return  the collection of edges which are connected to vertex or null if vertex is not present
	 */
	public Collection<GraphEdge> getIncidentEdges(GraphNode vertex) {
		LinkedList<GraphEdge> ret = new LinkedList<>();
		ret.addAll(getInEdges(vertex));
		ret.addAll(getOutEdges(vertex));
		return ret;
	}
	
	/**
	 * Returns the collection of vertices in this graph which are connected to edge.
	 * Note that for some graph types there are guarantees about the size of this collection
	 * (i.e., some graphs contain edges that have exactly two endpoints, which may or may 
	 * not be distinct).  Implementations for those graph types may provide alternate methods 
	 * that provide more convenient access to the vertices.
	 * 
	 * @param edge the edge whose incident vertices are to be returned
	 * @return  the collection of vertices which are connected to edge, or null if edge is not present
	 */
	public Collection<GraphNode> getIncidentVertices(GraphEdge edge) {
		Pair<GraphNode> p = getEndpoints(edge);
		LinkedList<GraphNode> ret = new LinkedList<>();
		ret.add(p.getFirst());
		ret.add(p.getSecond());
		return ret;
	}


	/**
	 * Returns true if this graph's edge collection contains edge.
	 * Equivalent to getEdges().contains(edge).
	 * @param edge the edge whose presence is being queried
	 * @return true iff this graph contains an edge edge
	 */
	public boolean containsEdge(GraphEdge edge) {
		return (getEndpoints(edge) != null);
	}
	
	/**
	 * Returns the collection of edges in this graph which are of type edge_type.
	 * @param edgeType the type of edges to be returned
	 * @return the collection of edges which are of type edge_type, or null if the graph does not accept edges of this type
	 * @see EdgeType
	 */
	
	public Collection<GraphEdge> getEdges(EdgeType edgeType) {
		if(edgeType == EdgeType.DIRECTED) {//edge_type is not defined
			return getEdges();
		}
		return null;
	}
	
	
	/**
	 * Returns the number of edges of type edge_type in this graph.
	 * @param edgeType the type of edge for which the count is to be returned
	 * @return the number of edges of type edge_type in this graph
	 */

	 
	public int getEdgeCount(EdgeType edgeType) {
		if(edgeType == EdgeType.DIRECTED) {//edge_type is not defined
			return getEdgeCount();
		}
		return 0;
	}
	
	/**
	 * Returns the number of predecessors that vertex has in this graph.
	 * Equivalent to vertex.getPredecessors().size().
	 * @param vertex the vertex whose predecessor count is to be returned
	 * @return  the number of predecessors that vertex has in this graph
	 */
	public int getPredecessorCount(GraphNode vertex) {
		return inDegree(vertex);
	}
	
	/**
	 * Returns the number of successors that vertex has in this graph.
	 * Equivalent to vertex.getSuccessors().size().
	 * @param vertex the vertex whose successor count is to be returned
	 * @return  the number of successors that vertex has in this graph
	 */
	public int getSuccessorCount(GraphNode vertex) {
		return outDegree(vertex);
	}
	
	/**
	 * Returns the number of vertices that are adjacent to vertex
	 * (that is, the number of vertices that are incident to edges in vertex's
	 * incident edge set).
	 * 
	 * <p>Equivalent to getNeighbors(vertex).size().
	 * @param vertex the vertex whose neighbor count is to be returned
	 * @return the number of neighboring vertices
	 */
	public int getNeighborCount(GraphNode vertex) {		
		if (!containsVertex(vertex))
			return -1;

		return getNeighbors(vertex).size();
	}

	/**
	 * Returns the vertex at the other end of edge from vertex.
	 * (That is, returns the vertex incident to edge which is not vertex.)
	 * @param vertex the vertex to be queried
	 * @param edge the edge to be queried
	 * @return the vertex at the other end of edge from vertex
	 */
	public GraphNode getOpposite(GraphNode vertex, GraphEdge edge) {
		if (getSource(edge).equals(vertex)){
			return getDest(edge);
		}
		else if (getDest(edge).equals(vertex)){
			return getSource(edge);
		}
		else
			return null;			
	}
	
	/**
	 * Returns all edges that connects v1 to v2.
	 * If this edge is not uniquely
	 * defined (that is, if the graph contains more than one edge connecting 
	 * v1 to v2), any of these edges 
	 * may be returned.  findEdgeSet(v1, v2) may be 
	 * used to return all such edges.
	 * Returns null if v1 is not connected to v2.
	 * <br/>Returns an empty collection if either v1 or v2 are not present in this graph.
	 *  
	 * <p><b>Note</b>: for purposes of this method, v1 is only considered to be connected to
	 * v2 via a given <i>directed</i> edge d if
	 * v1 == d.getSource() && v2 == d.getDest() evaluates to true.
	 * (v1 and v2 are connected by an undirected edge u if 
	 * u is incident to both v1 and v2.)
	 * @param v1 the first vertex connected by the edge
	 * @param v2 the second vertex connected by the edge
	 * 
	 * @return  a collection containing all edges that connect v1 to v2, or null if either vertex is not present
	 * @see Hypergraph#findEdge(Object, Object) 
	 */
	public Collection<GraphEdge> findEdgeSet(GraphNode v1, GraphNode v2) {
		GraphEdge edge = findEdge(v1, v2);
		if(edge == null) {
			return null;
		}
		
		LinkedList<GraphEdge> ret = new LinkedList<>();
		ret.add(edge);
		return ret;
		
	}
	
	/**
	 * Returns true if vertex is the source of edge.
	 * Equivalent to getSource(edge).equals(vertex).
	 * @param vertex the vertex to be queried
	 * @param edge the edge to be queried
	 * @return true iff vertex is the source of edge
	 */
	public boolean isSource(GraphNode vertex, GraphEdge edge) {
		return getSource(edge).equals(vertex);
	}
	
	/**
	 * Returns true if vertex is the destination of edge.
	 * Equivalent to getDest(edge).equals(vertex).
	 * @param vertex the vertex to be queried
	 * @param edge the edge to be queried
	 * @return true iff vertex is the destination of edge
	 */
	public boolean isDest(GraphNode vertex, GraphEdge edge) {
		return getDest(edge).equals(vertex);
	}
	
	/**
	 * Adds edge e to this graph such that it connects 
	 * vertex v1 to v2.
	 * Equivalent to addEdge(e, new {@code Pair<GraphNode>(v1, v2)}).
	 * If this graph does not contain v1, v2, 
	 * or both, implementations may choose to either silently add 
	 * the vertices to the graph or throw an IllegalArgumentException.
	 * If edgeType is not legal for this graph, this method will
	 * throw IllegalArgumentException.
	 * See Hypergraph.addEdge() for a listing of possible reasons
	 * for failure.
	 * @param e the edge to be added
	 * @param v1 the first vertex to be connected
	 * @param v2 the second vertex to be connected
	 * @param edgeType the type to be assigned to the edge
	 * @return true if the add is successful, false otherwise
	 * @see Hypergraph#addEdge(Object, Collection)
	 * @see #addEdge(Object, Object, Object)
	 */
	public boolean addEdge(GraphEdge e, GraphNode v1, GraphNode v2, EdgeType edgeType) {
		//NOTE: Only directed edges allowed
		
		if(edgeType == EdgeType.UNDIRECTED) {
			throw new IllegalArgumentException();
		}
		
		return addEdge(e, v1, v2);
	}
	
	/**
	 * Adds edge to this graph.
	 * Fails under the following circumstances:
	 * <ul>
	 * <li/>edge is already an element of the graph 
	 * <li/>either edge or vertices is null
	 * <li/>vertices has the wrong number of vertices for the graph type
	 * <li/>vertices are already connected by another edge in this graph,
	 * and this graph does not accept parallel edges
	 * </ul>
	 * 
	 * @param edge the edge to be added
	 * @param vertices the vertices to be connected
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException if edge or vertices is null, 
	 * 	    or if a different vertex set in this graph is already connected by edge, 
	 * 	    or if vertices are not a legal vertex set for edge 
	 */
	@SuppressWarnings("unchecked")
	public boolean addEdge(GraphEdge edge, Collection<? extends GraphNode> vertices) {
		if(edge == null || vertices == null || vertices.size() != 2) {
			return false;
		}
		
		GraphNode[] vs = (GraphNode[])vertices.toArray();
		return addEdge(edge, vs[0], vs[1]);
	}

	/**
	 * Adds edge to this graph with type edge_type.
	 * Fails under the following circumstances:
	 * <ul>
	 * <li/>edge is already an element of the graph 
	 * <li/>either edge or vertices is null
	 * <li/>vertices has the wrong number of vertices for the graph type
	 * <li/>vertices are already connected by another edge in this graph,
	 * and this graph does not accept parallel edges
	 * <li/>edge_type is not legal for this graph
	 * </ul>
	 * 
	 * @param edge the edge to be added
	 * @param vertices the vertices to be connected
	 * @param edgeType the type of the edge
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException if edge or vertices is null, 
	 *     or if a different vertex set in this graph is already connected by edge, 
	 *     or if vertices are not a legal vertex set for edge 
	 */
	@SuppressWarnings("unchecked")
	public boolean addEdge(GraphEdge edge, Collection<? extends GraphNode> vertices, EdgeType edgeType) {
		if(edge == null || vertices == null || vertices.size() != 2) {
			return false;
		}
		
		GraphNode[] vs = (GraphNode[])vertices.toArray();
		return addEdge(edge, vs[0], vs[1], edgeType);//edge_type
	}
	
	//********************************************************************************
	//   DO NOT EDIT ANYTHING BELOW THIS LINE EXCEPT FOR FIXING JAVADOC
	//********************************************************************************
	
	/**
	 * Returns a {@code Factory} that creates an instance of this graph type.
	 * @param <V> this is the vertex
	 * @param <E> this is the edge
	 * @return a {@code Factory} that creates an instance of this graph type
	 */
	 
	public static <V,E> Factory<Graph<GraphNode,GraphEdge>> getFactory() { 
		return new Factory<Graph<GraphNode,GraphEdge>> () {
			@SuppressWarnings("unchecked")
			public Graph<GraphNode,GraphEdge> create() {
				return (Graph<GraphNode,GraphEdge>) new FlexGraph();
			}
		};
	}
	


	/**
	 * Returns the edge type of edge in this graph.
	 * @param edge returns directed edge for all
	 * @return the EdgeType of edge, or null if edge has no defined type
	 */
	public EdgeType getEdgeType(GraphEdge edge) {
		return EdgeType.DIRECTED;
	}
	
	/**
	 * Returns the default edge type for this graph.
	 * 
	 * @return the default edge type for this graph
	 */
	public EdgeType getDefaultEdgeType() {
		return EdgeType.DIRECTED;
	}
	
	/**
	 * Returns the number of vertices that are incident to edge.
	 * For hyperedges, this can be any nonnegative integer; for edges this
	 * must be 2 (or 1 if self-loops are permitted). 
	 * 
	 * <p>Equivalent to getIncidentVertices(edge).size().
	 * @param edge the edge whose incident vertex count is to be returned
	 * @return the number of vertices that are incident to edge.
	 */
	public int getIncidentCount(GraphEdge edge) {
		return 2;
	}
	


}
