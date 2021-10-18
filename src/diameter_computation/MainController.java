package diameter_computation;

import java.util.ArrayList;
import java.util.HashMap;

public class MainController {
	
	private ArrayList<Node> upCert; //upper certificate
	private ArrayList<Node> packing; //packing
	private ArrayList<Node> L ; // Lower certificate
	private HashMap<String, Integer> eccentricity; // eU(u)
	private HashMap<String, Integer> eccentricityPacking; // eP(u)
	private HashMap<String, Integer> eccentricityLower;
	private Graph graph ;
	final int MAX_VALUE = 9999;
	private String file_name = "tvshow_edges.csv" ;

	
	
	
	public MainController() {
		super();
		graph = new Graph(this.file_name);
		upCert= new ArrayList<>();
		packing = new ArrayList<>();
		eccentricity = new HashMap<>();// eU(u)
		eccentricityPacking = new HashMap<>();// eP(u)
		
	}

	
	public static void main(String[] args) {
		
		MainController m = new MainController() ;
		
		 for(String n : m.graph.getGraph().keySet()) {
			 HashMap<String, Integer> Bellman = m.getDistFrom(m.graph.getGraph().get(n));
			 System.out.println("node= "+n);
			 for (String idNode : Bellman.keySet()) {		
					System.out.println(" To destination : " + idNode + " with distance: " + Bellman.get(idNode));	
				}
			 System.out.println("***************************");
		 }
		 
			System.out.println("--------------------------------Diam(G) = " +m.Diameter(m.graph));
		 
	}
	
	//Initialize eccentricity
		public void initializeEcce()
		{
			for (String key : graph.getGraph().keySet()) {
				this.eccentricity.put(key, MAX_VALUE);
			}
		}

		// return the maximal node!
		public String maxNode(HashMap<String, Integer> eccentricityEntry)
		{ 
			int ecc = 0;
			String idnode = null;
			for (String key : eccentricityEntry.keySet()) {
				if (ecc <= eccentricityEntry.get(key)) {
					ecc = eccentricityEntry.get(key);
					idnode = key;
					}
			}
			return idnode;
		}
		
		//MaxDistances, return eccentricity!
		public int max(HashMap<String, Integer> distances)
		{ 
			int eccentricity = 0;
			for (String key : distances.keySet()) {
				if (eccentricity <= distances.get(key)) {
					eccentricity = distances.get(key);}
			}
			return eccentricity;
		}
		

		//BellmanFord algorithm
		public HashMap<String, Integer> getDistFrom(Node node) {

			HashMap<String, Integer> dist = new HashMap<String, Integer>();
			for (String id : graph.getGraph().keySet()) {
				dist.put(id, MAX_VALUE);
			}
			

			dist.put(node.getId(), 0); // source=0

			int graphSize = graph.getGraph().size();
			
			for (int nod = 1; nod <= graphSize - 1; nod++) {
				
				for (String idSource : graph.getGraph().keySet()) {

					for (String idDestination : graph.getGraph().keySet()) {

						if (graph.getGraph().get(idSource).getAdjacent().containsKey(idDestination)) {

							if (dist.get(idDestination) > dist.get(idSource) + 1) // here 1 because we don't have any														// weight.
							{
								dist.put(idDestination, dist.get(idSource) + 1);
							}
						}

					}

				}
			}
			return dist;
		}
		
		
		public void updateEccentricity(HashMap<String, Integer> distances) { // x is predefined because we give distances
			// according to a node
			int distEccen = 0;
			
			for (String key : graph.getGraph().keySet()) 
			{ // for v in V
				distEccen = distances.get(key) + this.max(distances); // Dx(v)+e(x)
				if (this.eccentricity.get(key) > distEccen) {
					this.eccentricity.put(key, distEccen);
				}
			}

		}
		
		//-----------------------------  Diameter Algorithm --------------------------------
		public int Diameter(Graph G){

			this.initializeEcce();
			
			do {
				
				String id_u = this.maxNode(this.eccentricity);
				Node u = G.getGraph().get(id_u);
				
				System.out.println("u.getId() :"+u.getId());
				
				HashMap<String, Integer> D_u = this.getDistFrom(u);
				
				
				this.packing.add(u);
				
				Node x = u;
				
				HashMap<String, Integer> D_x = this.getDistFrom(x);
				
				
				this.upCert.add(x);		
				
				this.updateEccentricity(D_x);	
				
				for(String key:graph.getGraph().keySet()) {
					System.out.println("eU(u) = " + this.eccentricity.get(key));
				}
				
				this.eccentricityPacking.put(u.getId(),this.eccentricity.get(u.getId()));
				
				for(String key: eccentricityPacking.keySet()) {
					System.out.println("\neP(u) = " + this.eccentricityPacking.get(key));
				}
				
				System.out.println("this.max(this.eccentricityPacking) : " + this.max(this.eccentricityPacking));
				System.out.println("this.max(this.eccentricity) : " +this.max(this.eccentricity));
			
			}while( this.max(this.eccentricityPacking) < this.max(this.eccentricity));
			
			return this.max(this.eccentricityPacking);
			
		}
		


}
