package diameter_computation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

	private String file_name;

	String file_path = System.getProperty("user.dir") + "/Graph_Datasets/";

	public Parser(String file_name) {
		super();
		this.file_name = file_name;
	}

	public HashMap<String,Node> buildNodes() {
		HashMap<String,Node> list_nodes = new HashMap<String,Node>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file_path + file_name));
			String line;
			br.readLine();
			String[] values = br.readLine().split(",");
			Node node_1 = new Node(values[0]);
			list_nodes.put(values[0],node_1) ;
			while ((line = br.readLine()) != null) {
				values = line.split(",") ;
				if(!list_nodes.containsKey(values[0])) {
					Node node_2 = new Node(values[0]) ;
					list_nodes.put(values[0],node_2) ;
				}
				if(!list_nodes.containsKey(values[1])) {
					Node node_2 = new Node(values[1]) ;
					list_nodes.put(values[1],node_2) ;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list_nodes ;
	}
	
	public HashMap<String,Node> buildAdjacences(){
		HashMap<String,Node> list_nodes = buildNodes() ;
//		for(String s : list_nodes.keySet()) {
//			if(s.equals("0"))
//			System.out.println("fffffffffffffffffff");
//		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(file_path + file_name));
			String line;
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				//System.out.println("values0==="+values[0]+"values1==="+values[1]);
				list_nodes.get(values[0]).addNodeToAdjacents(list_nodes.get(values[1])) ;
				list_nodes.get(values[1]).addNodeToAdjacents(list_nodes.get(values[0])) ;
				}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list_nodes ;
	}
	
}
