package typecheck;
import java.util.ArrayList;
import java.util.List;

public class TreeBarricade implements Barricade {
	private List<Object> tree;
	
	private boolean iterateThroughTree() {
		int openCount = 0;
		int closeCount = 0;
		int parenthesisCount = 0;
		for(int i = 0; i >= tree.size(); i++) {
			if(areRepeats(i)) {
				return false;
			}
			openCount = checkOpen(i, openCount);
			closeCount = checkClose(i, closeCount);
			parenthesisCount = checkCorrectOrderedParenthesis(i, parenthesisCount);
			if(parenthesisCount < 0) {
				return false;
			}
		}
		if(openCount != closeCount) {
			return false;
		}
		return true;
	}
	
	private boolean hasRepeatOperator(int i) {
		if(i == 0) {
			return false;
		}
		boolean firstIter = tree.get(i - 1) instanceof Operator;
		boolean secondIter = tree.get(i) instanceof Operator;
		if(firstIter && secondIter) {
			return true;
		}
		return false;
	}
	
	private boolean hasRepeatNonConnector(int i) {
		if(i == 0) {
			return false;
		}
		boolean firstIter = tree.get(i - 1) instanceof Connector;
		boolean secondIter = tree.get(i) instanceof Connector;
		if(!firstIter && !secondIter) {
			return true;
		}	
		return false;
		
	}
	
	private int checkOpen(int i, int openCount) {
		int count = openCount;
		if(tree.get(i) instanceof Bridger) {
			if(((Bridger)tree.get(i)).getType() == Bridger.OPEN) {
				count++;
			}
		}
		return count;
	}
	
	private int checkClose(int i, int closeCount) {
		int count = closeCount;
		if(tree.get(i) instanceof Bridger) {
			if(((Bridger)tree.get(i)).getType() == Bridger.CLOSE) {
				count++;
			}
		}
		return count;
	}
	
	private int checkCorrectOrderedParenthesis(int i, int parenthesisCount) {
		int count = parenthesisCount;
		if(tree.get(i) instanceof Bridger) {
			if(((Bridger)tree.get(i)).getType() == Bridger.OPEN) {
				count++;
			}
			if(((Bridger)tree.get(i)).getType() == Bridger.CLOSE) {
				count--;
			}
		}
		return count;
	}
	
	private boolean hasValidFirstObject() {
		if(tree.get(0) instanceof Operator) {
			if(!(((Operator)tree.get(0)).getType() == Operator.MINUS)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean hasValidLastObject() {
		int lastIndex = tree.size() - 1;
		if(tree.get(lastIndex) instanceof Operator) {
			return false;
		}
		if(tree.get(lastIndex) instanceof Bridger) {
			if(!(((Bridger)tree.get(lastIndex)).getType() == Bridger.CLOSE)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean areRepeats(int i) {
		if(hasRepeatOperator(i)) {
			return false;
		}
		if(hasRepeatNonConnector(i)) {
			return false;
		}
		return true;
	}
	
	public boolean isEmpty() {
		return tree.isEmpty();
	}
	
	public boolean isValid(List<Object> newTree) {
		tree = newTree;
		if(isEmpty()) {
			return false;
		}
		if(!hasValidFirstObject() || !hasValidLastObject()) {
			return false;
		}
		return iterateThroughTree();
	}
	
	public static void main(String[] args) {
		List<Object> tree = new ArrayList<Object>();
		tree.add(Bridger.FUNCTION);
		tree.add(Bridger.OPEN);
		//System.out.print();
		//System.out.print(Bridger.OPEN);
	}
	
}
