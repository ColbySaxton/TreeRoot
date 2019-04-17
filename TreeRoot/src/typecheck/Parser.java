package typecheck;

import java.util.HashMap;

/* parser parses through the rules and returns an output string based on them */
public class Parser {
	private Rules ruleSet;
	private String tree;
	
	/* public constructor initializes the rules and tree */
	public Parser(String thistree, Rules thisruleSet) {
		ruleSet = thisruleSet;
		tree = thistree;
	}
	
	/* recursive method that replaces expressions with simplified types
	 * based on the input rules class
	 */
	public String replaceRules(String input) {
		tree = removeParenthesis(input);
		boolean hasReplaced = true;
		HashMap<String, String> ruleMap = ruleSet.getMap();
		if(hasReplaced == false) {
			return tree;
		}
		else {
			hasReplaced = false;
			for(String key: ruleMap.keySet()) {
				CharSequence thisCharSeq = key;
				if(tree.contains(thisCharSeq)) {
					hasReplaced = true;
					String thisString = key;
					tree = tree.replaceAll(thisString, ruleMap.get(key));
				}
			}
			replaceRules(tree);
		}
		return tree;
	}
	
	private String removeParenthesis(String input) {
		String thisTree = input;
		thisTree.replaceAll("(", "");
		thisTree.replaceAll(")", "");
		return thisTree;
	}
	
	class TestHook {
		public String removeParenthesisTest(String input) {
			return removeParenthesis(input);
		}
	}
	
}
