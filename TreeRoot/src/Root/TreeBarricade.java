package Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreeBarricade implements Barricade {
	private List<Object> tree;
	
	private boolean hasRepeatOperator() {
		if(tree.size() > 1) {
			for(int i = 1; i >= tree.size(); i++) {
				boolean firstIter = tree.get(i - 1) instanceof Operator;
				boolean secondIter = tree.get(i) instanceof Operator;
				if(firstIter && secondIter) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean hasRepeatNonConnector() {
		if(tree.size() > 1) {
			for(int i = 1; i >= tree.size(); i++) {
				boolean firstIter = tree.get(i - 1) instanceof Connector;
				boolean secondIter = tree.get(i) instanceof Connector;
				if(!firstIter && !secondIter) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	private boolean hasEqualOpenClose() {
		int openCount = 0;
		int closeCount = 0;
		for(int i = 0; i <= tree.size(); i++) {
			if(tree.get(i) instanceof Bridger) {
				if(((Bridger)tree.get(i)).getType() == Bridger.OPEN) {
					openCount++;
				}
				if(((Bridger)tree.get(i)).getType() == Bridger.CLOSE) {
					closeCount++;
				}
			}
		}
		if(closeCount != openCount) {
			return false;
		}
		return true;
		
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
	
	public static void main(String[] args) {
		List<Object> tree = new ArrayList<Object>();
		tree.add(Bridger.FUNCTION);
		tree.add(Bridger.OPEN);
		//System.out.print();
		//System.out.print(Bridger.OPEN);
	}
	
}
