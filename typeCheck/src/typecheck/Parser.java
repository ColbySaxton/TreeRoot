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
	public String replaceRules(String input, boolean hasReplaced) {
		tree = input;
		HashMap<String, String> ruleMap = ruleSet.getMap();
		//System.out.println("1");
		if(hasReplaced == false) {
			return tree;
		}
		else {
			//System.out.println("2");
			hasReplaced = false;
			for(String key: ruleMap.keySet()) {
				CharSequence thisKey = key;
				if(tree.contains(thisKey)) {
					hasReplaced = true;
					CharSequence thisMapped = ruleMap.get(key);
					tree = tree.replace(thisKey, thisMapped);
				}
			}
			tree = removeParenthesis(tree);
			replaceRules(tree, hasReplaced);
		}
		return tree;
	}
	
	private String removeParenthesis(String input) {
		String thisTree = input;
		CharSequence open = "(";
		if(input.contains(open)) {
			thisTree = thisTree.replaceAll("[()]", "");
		}
		return thisTree;
	}
	
	class TestHook {
		public String removeParenthesisTest(String input) {
			return removeParenthesis(input);
		}
	}
	
}
