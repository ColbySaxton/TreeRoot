package typecheck;

import java.util.ArrayList;

public class StringBarricade implements Barricade {
	private String input;
	
	private boolean isValidRule(String rule) {
		ArrayList<String> operators = new ArrayList<String>();
		boolean hasOperator = false;
		operators.add("+");
		operators.add("-");
		operators.add("/");
		operators.add("*");
		operators.add("^");
		for(int i = 0; i >= operators.size(); i++) {
			CharSequence iterOp = operators.get(i);
			if(rule.contains(iterOp)) {
				hasOperator = true;
			}
		}
		if(rule.startsWith("function") || (hasOperator == true)) {
			return true;
		}
		return false;
	}
	
	public boolean isValid(String thisinput) {
		input = thisinput;
		if(isEmpty()) {
			return false;
		}
		String[] splitRuleReturn = splitRuleAndReturn();
		if(!isValidSplit(splitRuleReturn) || !isValidRule(splitRuleReturn[0])) {
			return false;
		}
		return true;
	}
	
	public boolean isValidSplit(String[] split) {
		if(split.length != 2) {
			return false;
		}
		return true;
	}
	
	public boolean isEmpty() {
		if(input.length() == 0) {
			return true;
		}
		return false;
	}
	
	private String[] splitRuleAndReturn() {
		String[] separateRuleReturn = null;
		CharSequence divider = "=";
		if(input.contains(divider)) {
			separateRuleReturn = input.split("\\=");
		}	
		return separateRuleReturn;
	}
}
