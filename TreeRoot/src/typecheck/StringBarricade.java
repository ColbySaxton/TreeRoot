package typecheck;

public class StringBarricade implements Barricade {
	private String input;
	
	private Object getType(String input) {
		
	}
	
	private String[] splitInput() {
		
	}
	
	private boolean isValidType() {
		
	}
	
	public boolean isValid(String input) {
		
	}
	
	public boolean isValidRuleOperation() {
		
	}
	
	public boolean isEmpty() {
		
	}
	
	private String[] splitRuleAndReturn() {
		String[] separateRuleReturn = null;
		CharSequence divider = ":";
		if(input.contains(divider)) {
			separateRuleReturn = input.split("\\:");
		}	
		return separateRuleReturn;
	}
	
	private boolean isValidReturn() {
		
	}
}
