package Root;

import java.util.HashMap;

public enum Bridger implements Connector {
	OPEN,
	CLOSE,
	FUNCTION;
	
	private HashMap<Bridger, String> stringMap;
	
	public String toString() {
		stringMap.put(OPEN, "(");
		stringMap.put(CLOSE, ")");
		stringMap.put(FUNCTION, "function");
		return stringMap.get(this);
	}
	
}
