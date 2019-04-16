package Root;
import java.util.HashMap;

public interface Connector {
	public static final HashMap<Connector, String> stringMap = new HashMap<Connector, String>();
	public String toString();
	public Connector getType();
}
