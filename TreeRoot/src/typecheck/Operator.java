package typecheck;

import java.util.HashMap;

/* This enum is for all operators of expressions */
public enum Operator implements Connector {
	PLUS,
	MINUS,
	TIMES,
	DIVIDE,
	EXPONENTIAL;
	
	private HashMap<Operator, String> stringMap = new HashMap<Operator, String>();

	/* returns the type of the current operator */
	public Operator getType() {
		return this;
	}
	
	/* returns the string of the current operator */
	public String toString() {
		stringMap.put(PLUS, "+");
		stringMap.put(MINUS, "-");
		stringMap.put(TIMES, "*");
		stringMap.put(DIVIDE, "/");
		stringMap.put(EXPONENTIAL, "^");
		return stringMap.get(this);
	}
}
