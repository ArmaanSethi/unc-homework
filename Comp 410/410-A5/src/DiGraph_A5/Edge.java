package DiGraph_A5;

public class Edge {
	long id;
	String label;
	String source;
	String destination;
	long weight = 1;

	public Edge(long id, String sLabel, String dLabel, long weight, String eLabel) {
		this.id = id;
		source = sLabel;
		destination = dLabel;
		label = eLabel;
	}

}
