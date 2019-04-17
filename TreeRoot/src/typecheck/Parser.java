package typecheck;

import java.util.HashMap;

public class Parser {
	private Rules ruleSet;
	private String tree;
	
	public Parser(String thistree, Rules thisruleSet) {
		ruleSet = thisruleSet;
		tree = thistree;
	}
	
	public String replaceRules(String input) {
		tree = input;
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
	
}
