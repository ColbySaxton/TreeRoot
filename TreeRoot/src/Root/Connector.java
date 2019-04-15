package Root;
import java.util.HashMap;

public enum Connector {
	PLUS,
	MINUS,
	TIMES,
	DIVIDE,
	OPEN,
	CLOSE,
	EXPONENTIAL,
	FUNCTION;
	
	private HashMap<Connector, String> stringMap;
	
	public String toString() {
		stringMap.put(PLUS, "+");
		stringMap.put(MINUS, "-");
		stringMap.put(TIMES, "*");
		stringMap.put(DIVIDE, "/");
		stringMap.put(OPEN, "(");
		stringMap.put(CLOSE, ")");
		stringMap.put(EXPONENTIAL, "^");
		stringMap.put(FUNCTION, "function");
		return stringMap.get(this);
	}
	
}
