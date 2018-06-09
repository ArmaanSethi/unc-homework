package A6_Dijkstra;

import java.util.HashMap;

public class Vertex {
	String label;
	long id;
	HashMap<String, Edge> inEdge = new HashMap<String, Edge>();
	HashMap<String, Edge> outEdge = new HashMap<String, Edge>();
	long topoDegree;

	public Vertex(long id, String label) {
		this.id = id;
		this.label = label;
	}

	void addInEdge(Edge in) {
		inEdge.put(in.source, in);
	}

	void addOutEdge(Edge out) {
		outEdge.put(out.destination, out);
	}
}
