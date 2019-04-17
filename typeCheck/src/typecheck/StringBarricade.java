package typecheck;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringBarricade implements Barricade {
	private String input;
	private List<String> allowedTypes;
	
	/**Initializes the barricade for Rules
	 * 
	 * @param types the types allowed in the tree
	 */
	public StringBarricade(List<String> types) {
		input = "";
		allowedTypes = types;
	}
	
	/**Checks whether the rule contains an operator
	 * or is a function
	 * @param rule = the expression of the rule such as "Integer+Double"
	 * @return whether the rule is valid or not
	 */
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
	
	/**Checks whether the input is valid
	 * If it is not empty
	 * is in the proper format with one "=" sign
	 * is either a function or has an operator on the left half
	 * has resolving type that is allowed
	 * @param thisinput = the string line to be resolved
	 * @return whether the line is a valid rule
	 */
	public boolean isValid(String thisinput) {
		input = thisinput;
		return !isEmpty() && isValidSplit(splitRuleAndReturn()) && isValidRule(splitRuleAndReturn()[0]) && allowedTypes.contains(splitRuleAndReturn()[1]);
	}
	
	/**The rule line split by the "=" signs
	 * if it is a valid rule it should be 2 parts
	 * @param split = the number of parts to the rule
	 * @return whether the line could be a rule
	 */
	private boolean isValidSplit(String[] split) {
		return split.length == 2;
	}
	
	/**Whether the line is empty
	 * 
	 */
	public boolean isEmpty() {
		return input.isEmpty();
	}
	
	/**Splits the line into separate portions using "=" as a marker
	 * also removes all whitespaces
	 * @return the individual items in an array
	 */
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
