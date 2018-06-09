package A6_Dijkstra;

public class EntryPair {
	  public String value;
	  public long priority;

	  public EntryPair(String aValue, long aPriority) {
	    value = aValue;
	    priority = aPriority;
	  }

	  public String getValue() { return value; }
	  public long getPriority() { return priority; }
	}
