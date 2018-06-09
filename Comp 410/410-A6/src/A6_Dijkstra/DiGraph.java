package A6_Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class DiGraph implements DiGraph_Interface {
	HashMap<String, Vertex> nodes = new HashMap<String, Vertex>();
	Set<Long> allEdgeId = new HashSet<Long>();
	Set<Long> allVertexId = new HashSet<Long>();
	// in here go all your data and methods for the graph
	// and the topo sort operation

	public DiGraph() { // default constructor
		// explicitly include this
		// we need to have the default constructor
		// if you then write others, this one will still be there
	}

	public boolean addNode(long idNum, String label) {
		if (idNum < 0 || allVertexId.contains(idNum)) {
			return false;
		}
		if (nodes.containsKey(label) || label == null) {
			return false;
		}

		Vertex add = new Vertex(idNum, label);
		nodes.put(label, add);
		allVertexId.add(idNum);

		return true;
	}

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		if (idNum < 0 || allEdgeId.contains(idNum)) {
			return false;
		}
		if (!nodes.containsKey(sLabel) || sLabel == null) {
			return false;
		}
		if (!nodes.containsKey(dLabel) || dLabel == null) {
			return false;
		}
		if (nodes.get(sLabel).outEdge.containsKey(dLabel)) {
			return false;
		}
		allEdgeId.add(idNum);
		Edge add = new Edge(idNum, sLabel, dLabel, weight, eLabel);
		nodes.get(sLabel).addOutEdge(add);
		nodes.get(dLabel).addInEdge(add);
		return true;
	}

	@Override
	public boolean delNode(String label) {
		if (!nodes.containsKey(label)) {
			return false;
		}
		Vertex removeVertex = nodes.get(label);
		Iterator<java.util.Map.Entry<String, Edge>> iterator = removeVertex.outEdge.entrySet().iterator();

		while (iterator.hasNext()) {
			Edge removeEdge = iterator.next().getValue();
			nodes.get(removeEdge.destination).inEdge.remove(label);
			removeVertex.outEdge.remove(removeEdge.destination);
			allEdgeId.remove(removeEdge.id);
		}
		nodes.remove(label);
		allVertexId.remove(removeVertex.id);
		return true;
	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		if (!nodes.containsKey(sLabel)) {
			return false;
		}
		if (!nodes.containsKey(dLabel)) {
			return false;
		}
		if (!nodes.get(sLabel).outEdge.containsKey(dLabel)) {
			return false;
		}
		Edge removeEdge = nodes.get(sLabel).outEdge.get(dLabel);
		nodes.get(sLabel).outEdge.remove(dLabel);
		nodes.get(dLabel).inEdge.remove(sLabel);
		allEdgeId.remove(removeEdge.id);
		return true;
	}

	@Override
	public long numNodes() {
		return allVertexId.size();
	}

	@Override
	public long numEdges() {
		return allEdgeId.size();
	}

	@Override
	public String[] topoSort() {
		ArrayList<Vertex> sortedVerticies = new ArrayList<Vertex>();
		Queue<Vertex> que = new LinkedList<Vertex>();
		Iterator<Entry<String, Vertex>> itr = nodes.entrySet().iterator();
		while (itr.hasNext()) {
			Vertex next = itr.next().getValue();
			next.topoDegree = next.inEdge.size();
			if (next.inEdge.size() == 0) {
				que.add(next);
			}
		}
		while (!que.isEmpty()) {
			Vertex pop = que.poll();
			sortedVerticies.add(pop);
			Iterator<Entry<String, Edge>> itr2 = pop.outEdge.entrySet().iterator();
			while (itr2.hasNext()) {
				Vertex nextVertex = nodes.get(itr2.next().getValue().destination);
				if (--(nextVertex.topoDegree) == 0) {
					que.add(nextVertex);
				}
			}
		}
		if(sortedVerticies.size() != nodes.size()){
			return null;
		}
		
		String[] s = new String[nodes.size()];
		for(int i = 0; i < sortedVerticies.size(); i++){
			s[i] = sortedVerticies.get(i).label;
		}
		return s;
	}

	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		MinBinHeap frontier = new MinBinHeap(); 
		List<ShortestPathInfo> shortestPaths = new ArrayList<ShortestPathInfo>();
		Set<String> visited = new HashSet<String>();		
		HashMap<String, Long> distances = new HashMap<String, Long>();
		
		//set our node to 0
		distances.put(label, 0L);
		frontier.insert(new EntryPair(label, 0));
		
		while (frontier.size() != 0){
			String v = frontier.getMin().getValue();
			frontier.delMin();
			
			if (visited.contains(v)) {
				continue;
			}
			
			visited.add(v);
			shortestPaths.add(new ShortestPathInfo(v, distances.get(v)));

			for(String x : nodes.get(v).outEdge.keySet()){
				Vertex v2 = nodes.get(v);
				Long edgeWeight = nodes.get(v).outEdge.get(x).weight;
				if(!visited.contains(v2)){
					if (!distances.containsKey(x)) {
						// current guess is inf
						distances.put(x, distances.get(v) + edgeWeight);
						frontier.insert(new EntryPair(x, distances.get(x)));
					}
					else {
						// check if this is better than current guess						
						if (distances.get(x) > distances.get(v) + edgeWeight) {
							distances.put(x, distances.get(v) + edgeWeight);
							frontier.insert(new EntryPair(x, distances.get(x)));
						}
					}
				}
			}
		}
		
		for (String l : nodes.keySet()) {
			if (!visited.contains(l)) {
				shortestPaths.add(new ShortestPathInfo(l, -1));
			}
		}
		
		ShortestPathInfo[] retVal = new ShortestPathInfo[shortestPaths.size()];
		shortestPaths.toArray(retVal);
//		System.out.println(Arrays.toString(retVal));
		return retVal; //(ShortestPathInfo[])shortestPaths.toArray();
	}
	
	public static void main(String[] args) {
		Scanner sin = new Scanner(System.in);
		int v = sin.nextInt();
		int e = sin.nextInt();
		
		DiGraph g = new DiGraph();
		
		for (int i = 0 ; i < v ; i++) {
			g.addNode(sin.nextInt(), sin.next());
		}
		
		for (int i = 0 ; i < e ; i++) {
			sin.nextInt();
			g.addEdge(0, sin.next(), sin.next(), sin.nextInt(), "");
		}
		
		g.shortestPath(sin.next());
	}

	// rest of your code to implement the various operations
}
