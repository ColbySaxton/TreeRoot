package typecheck;

import java.util.HashMap;

/* an enum of bridgers which are parentheses around expressions */
public enum Bridger implements Connector {
	OPEN,
	CLOSE,
	FUNCTION;
	
	private HashMap<Bridger, String> stringMap = new HashMap<Bridger, String>();
	
	/* returns the type of the current bridger */
	public Bridger getType() {
		return this;
	}
	
	/* returns the string of the current bridger */
	public String toString() {
		stringMap.put(OPEN, "(");
		stringMap.put(CLOSE, ")");
		stringMap.put(FUNCTION, "function");
		return stringMap.get(this);
	}
	
}
