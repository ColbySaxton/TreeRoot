package typecheck;
import java.util.ArrayList;
import java.util.List;

public class TreeBarricade implements Barricade {
	private List<Object> tree;
	
	private boolean iterateThroughTree() {
		int openCount = 0;
		int closeCount = 0;
		int parenthesisCount = 0;
		for(int i = 0; i < tree.size(); i++) {
			if(areRepeats(i)) {
				return false;
			}
			if(isNotStringFunction(i)) {
				return false;
			}
			openCount = checkOpen(i, openCount);
			closeCount = checkClose(i, closeCount);
			parenthesisCount = checkCorrectOrderedParenthesis(i, parenthesisCount);
			if(parenthesisCount < 0) {
				return false;
			}
		}
		return openCount == closeCount;
	}
	
	private boolean isNotStringFunction(int i) {
		Object object = tree.get(i);
		if(object instanceof String) {
			return !((String) object).startsWith("Function");
		}
		return false;
	}
	
	private boolean hasRepeatOperator(int i) {
		if(i == 0) {
			return false;
		}
		boolean firstIter = tree.get(i - 1) instanceof Operator;
		boolean secondIter = tree.get(i) instanceof Operator;
		return firstIter && secondIter;
	}
	
	private boolean hasRepeatNonConnector(int i) {
		if(i == 0) {
			return false;
		}
		boolean firstIter = tree.get(i - 1) instanceof Connector;
		boolean secondIter = tree.get(i) instanceof Connector;
		return !firstIter && !secondIter;
		
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
		return (tree.get(0) instanceof Operator) ? ((Operator)tree.get(0)).getType() == Operator.MINUS : true;
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
		return hasRepeatOperator(i) || hasRepeatNonConnector(i);
	}
	
	public boolean isEmpty() {
		return tree.isEmpty();
	}
	
	public boolean isValid(List<Object> newTree) {
		tree = newTree;
		return !isEmpty() && hasValidFirstObject() && hasValidLastObject() && iterateThroughTree();
	}
	
	class TestHook {
		
		public boolean iterateThroughTreeTest(List<Object> thistree) {
			tree = thistree;
			return iterateThroughTree();
		}
		
		public boolean isNotStringFunctionTest(int i, List<Object> thistree) {
			tree = thistree;
			return isNotStringFunction(i);
		}
		
		public boolean hasRepeatOperatorTest(int i, List<Object> thistree) {
			tree = thistree;
			return hasRepeatOperator(i);
		}
		
		public boolean hasRepeatNonConnectorTest(int i, List<Object> thistree) {
			tree = thistree;
			return hasRepeatNonConnector(i);
		}
		
		public int checkOpenTest(int i, int openCount, List<Object> thistree) {
			tree = thistree;
			return checkOpen(i, openCount);
		}

		public int checkCloseTest(int i, int closeCount, List<Object> thistree) {
			tree = thistree;
			return checkClose(i, closeCount);
		}

		public int checkCorrectOrderedParenthesisTest(int i, int parenthesisCount, List<Object> thistree) {
			tree = thistree;
			return checkCorrectOrderedParenthesis(i, parenthesisCount);
		}

		public boolean hasValidFirstObjectTest(List<Object> thistree) {
			tree = thistree;
			return hasValidFirstObject();
		}

		public boolean hasValidLastObjectTest(List<Object> thistree) {
			tree = thistree;
			return hasValidLastObject();
		}

		public boolean areRepeatsTest(int i, List<Object> thistree) {
			tree = thistree;
			return areRepeats(i);
		}

		public boolean isEmptyTest(List<Object> thistree) {
			tree = thistree;
			return isEmpty();
		}
	}
	
}
