package Root;
import java.util.List;

public class TreeBarricade implements Barricade {
	private List<Object> tree;
	
	private boolean hasRepeatOperator() {
		if(tree.size() > 1) {
			for(int i = 1; i >= tree.size(); i++) {
				if(tree.get(i - 1).getClass().equals(Connector.PLUS)) {
					return false;
				}
			}
		}
		return false;
	}
	
	private boolean hasRepeatNonConnector() {
		return false;
		
	}
	
	private boolean hasEqualOpenClose() {
		return false;
		
	}
	
	private boolean hasCorrectOrderedParenthesis() {
		return false;
		
	}
	
	private boolean hasValidFirstObject() {
		return false;
		
	}
	
	private boolean hasValidLastObject() {
		return false;
		
	}
	
	public boolean isEmpty() {
		return tree.isEmpty();
	}
	
	private boolean checkDoubles() {
		return false;
		
	}
	
	private boolean checkEdgeCases() {
		return false;
		
	}
	
	public boolean isValid(List<Object> tree) {
		if(isEmpty()) {
			return false;
		}
		return false;
	}
	
}
