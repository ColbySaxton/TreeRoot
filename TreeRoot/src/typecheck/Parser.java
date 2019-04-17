package typecheck;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

public class Parser {
	private Rules ruleSet;
	private String tree;
	
	public Parser(String thistree, Rules thisruleSet) {
		ruleSet = thisruleSet;
		tree = thistree;
	}
	
	public String parse() {
		replaceRules(tree);
	}
	
	private void iterateThroughRules() {
		
	}
	
	private Operator operatorOfStandardOperation(String input) {
		
	}
	
	private boolean hasParenthesis(String input) {
		
	}
	
	private boolean hasExponential(String input) {
		
	}
	
	private boolean hasTimesorDivide(String input) {
		
	}
	
	private String removeParentheses(String input) {
		
	}
	
	private String replaceRules(String input) {
		HashMap<String, String> ruleMap = ruleSet.getMap();
		for(String key: ruleMap.keySet()) {
			CharSequence thisCharSeq = key;
			if(input.contains(thisCharSeq)) {
				String thisString = key;
				input = input.replaceAll(thisString, ruleMap.get(key));
			}
		}
		return input;
	}
	
	private Object returnTypeofRule(String input) {
		
	}
	
}
