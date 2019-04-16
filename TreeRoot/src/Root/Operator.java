package Root;

import java.util.HashMap;

public enum Operator implements Connector {
	PLUS,
	MINUS,
	TIMES,
	DIVIDE,
	EXPONENTIAL;
	
	private HashMap<Operator, String> stringMap;
	
	public String toString() {
		stringMap.put(PLUS, "+");
		stringMap.put(MINUS, "-");
		stringMap.put(TIMES, "*");
		stringMap.put(DIVIDE, "/");
		stringMap.put(EXPONENTIAL, "^");
		return stringMap.get(this);
	}
}
