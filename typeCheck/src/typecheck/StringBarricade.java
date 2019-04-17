package typecheck;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringBarricade implements Barricade {
	private String input;
	private List<String> allowedTypes;
	
	public StringBarricade(List<String> types) {
		input = "";
		allowedTypes = types;
	}
	
	private boolean isValidRule(String rule) {
		List<CharSequence> operators = new ArrayList<CharSequence>();
		boolean hasOperator = false;
		operators.add("+");
		operators.add("-");
		operators.add("/");
		operators.add("*");
		operators.add("^");
		for(int i = 0; i < operators.size(); i++) {
			if(rule.contains(operators.get(i))) {
				hasOperator = true;
			}
		}
		return rule.startsWith("function") || hasOperator;
	}
	
	public boolean isValid(String thisinput) {
		input = thisinput;
		return !isEmpty() && isValidSplit(splitRuleAndReturn()) && isValidRule(splitRuleAndReturn()[0]) && allowedTypes.contains(splitRuleAndReturn()[1]);
	}
	
	private boolean isValidSplit(String[] split) {
		return split.length == 2;
	}
	
	public boolean isEmpty() {
		return input.isEmpty();
	}
	
	private String[] splitRuleAndReturn() {
		List<String> separateRuleReturn = Stream.of(input.replaceAll("\\s+", "").split("="))
				.map (elem -> new String(elem))
				.collect(Collectors.toList());
		return separateRuleReturn.toArray(new String[0]);
	}
	
	class TestHook {
		public boolean isValidRuleTest(String rule, String thisinput) {
			input = thisinput;
			return isValidRule(rule);
		}
		
		public boolean isValidSplitTest(String[] split, String thisinput) {
			input = thisinput;
			return isValidSplit(split);
		}
		
		public String[] splitRuleAndReturnTest(String thisinput) {
			input = thisinput;
			return splitRuleAndReturn();
		}
	}
}
