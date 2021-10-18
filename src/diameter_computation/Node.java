package diameter_computation;

import java.util.HashMap;

public class Node {
	
	//identifier of the node
	String id ;
	
	//list of all adjacent nodes
	private HashMap<String, Node> adjacent;
	
	/**constructor*/
	public Node (String id)
	{
		this.id = id ;
		adjacent = new HashMap<String,Node>() ;
	}

	/**getters and setters*/
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HashMap<String, Node> getAdjacent() {
		return adjacent;
	}

	//add a node to my adjacent list
	public void addNodeToAdjacents(Node node)
	{
		this.adjacent.put(node.getId(), node) ;
	}

}
