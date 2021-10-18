package diameter_computation;

import java.util.HashMap;

public class TestMain {

	public static void main(String[] args) {

		Parser parser = new Parser("tvshow_edges.csv") ;
		HashMap<String,Node> myList = parser.buildAdjacences();
		for(int i=0; i<myList.keySet().size();i++) {
			String s = (String) myList.keySet().toArray()[i] ;
			System.out.println("-------------"+s);
			for(Node adj : myList.get(s).getAdjacent().values()) {
				System.out.println(adj.getId());
			}
		}	
	}
}
