package diameter_computation;

import java.util.HashMap;

public class Graph {
	
	private HashMap<String, Node> graph;

	public Graph(HashMap<String, Node> graph) {
		this.graph = graph;
	}

	public Graph(String file_name) {
		Parser data = new Parser(file_name) ; ;
		this.graph = data.buildAdjacences() ;
	}

	public HashMap<String, Node> getGraph() {
		return graph;
	}

	public void setGraph(HashMap<String, Node> graph) {
		this.graph = graph;
	}
	
	public void addNode(Node node) {
        this.graph.put(node.getId(),node);
    }
	
}
