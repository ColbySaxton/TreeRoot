package typecheck;
import java.util.List;


/* This class is the barricade which all input from the object tree must pass through */
public class TreeBarricade implements Barricade {
	private List<Object> tree;
	
	/* This method iterates through the tree list and parses it */
	private boolean iterateThroughTree() {
		int openCount = 0;
		int closeCount = 0;
		int parenthesisCount = 0;
		
		/* for loop iterates through tree */
		for(int i = 0; i < tree.size(); i++) {
			if(areRepeats(i)) {
				System.out.println("ERROR: input has repeated object types.");
				return false;
			}
			if(isNotStringFunction(i)) {
				System.out.println("ERROR: input a string that is not a function.");
				return false;
			}
			openCount = checkOpen(i, openCount);
			closeCount = checkClose(i, closeCount);
			parenthesisCount = checkCorrectOrderedParenthesis(i, parenthesisCount);
			if(parenthesisCount < 0) {
				System.out.println("ERROR: order of parentheses are incorrect.");
				return false;
			}
		}
		return openCount == closeCount;
	}
	
	/* This method checks to see if the object at i is a String that does not start with Function */
	private boolean isNotStringFunction(int i) {
		Object object = tree.get(i);
		if(object instanceof String) {
			return !((String) object).startsWith("Function");
		}
		return false;
	}
	
	/* Checks to see if there are any repeated operators at i */
	private boolean hasRepeatOperator(int i) {
		if(i == 0) {
			return false;
		}
		boolean firstIter = tree.get(i - 1) instanceof Operator;
		boolean secondIter = tree.get(i) instanceof Operator;
		return firstIter && secondIter;
	}
	
	/* Checks to see if there are any repeated not connector objects next to each other at i */
	private boolean hasRepeatNonConnector(int i) {
		if(i == 0) {
			return false;
		}
		boolean firstIter = tree.get(i - 1) instanceof Connector;
		boolean secondIter = tree.get(i) instanceof Connector;
		return !firstIter && !secondIter;
		
	}
	
	/* checks to see if at i if there is an open paren. Iterates openCount if so */
	private int checkOpen(int i, int openCount) {
		int count = openCount;
		if(tree.get(i) instanceof Bridger) {
			if(((Bridger)tree.get(i)).getType() == Bridger.OPEN) {
				count++;
			}
		}
		return count;
	}
	
	/* checks to see if at i if there is a close paren. Iterates closeCount if so */
	private int checkClose(int i, int closeCount) {
		int count = closeCount;
		if(tree.get(i) instanceof Bridger) {
			if(((Bridger)tree.get(i)).getType() == Bridger.CLOSE) {
				count++;
			}
		}
		return count;
	}
	
	/* keeps track of the order of parenthesis, adds one if open, subtracts one if closed */
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
	
	/* checks if the first object is valid (if it isnt an operator without a minus) */
	private boolean hasValidFirstObject() {
		return (tree.get(0) instanceof Operator) ? ((Operator)tree.get(0)).getType() == Operator.MINUS : true;
	}
	
	/* checks to see if the last object is valid 
	 * if it doesnt have an operator, and if it doesnt have an open parenthesis
	 */
	private boolean hasValidLastObject() {
		int lastIndex = tree.size() - 1;
		if(tree.get(lastIndex) instanceof Operator) {
			System.out.println("ERROR: has object cannot be an operator.");
			return false;
		}
		if(tree.get(lastIndex) instanceof Bridger) {
			if(!(((Bridger)tree.get(lastIndex)).getType() == Bridger.CLOSE)) {
				System.out.println("ERROR: cannot end with an open parenthesis.");
				return false;
			}
		}
		return true;
	}
	
	/* checks to see if there are any repeats at index i */
	private boolean areRepeats(int i) {
		return hasRepeatOperator(i) || hasRepeatNonConnector(i);
	}
	
	/* checks to see if the input object tree is empty */
	public boolean isEmpty() {
		if(tree.isEmpty()) {
			System.out.println("ERROR: the list of objects is empty.");
			return true;
		}
		return false;
	}
	
	/* checks to see if the tree is overall valid */
	public boolean isValid(List<Object> newTree) {
		tree = newTree;
		if(!hasValidFirstObject()) {
			System.out.println("ERROR: has invalid first object.");
		}
		return !isEmpty() && hasValidFirstObject() && hasValidLastObject() && iterateThroughTree();
	}
	
	/* testhook class */
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
