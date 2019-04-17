package typecheck;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ParserTest {
	
	private static final List<String> allowedTypes = new ArrayList<String>();
	static {
	allowedTypes.add("Integer");
	allowedTypes.add("Long");
	allowedTypes.add("Float");
	allowedTypes.add("function function(Primitive + Long)");
	}
	
	private static final Rules ruleset = new Rules("rules1",allowedTypes);
	
	static {
		ruleset.parseRules();
	}
	static String inputString = "Integer + Float + function function(Primitive + Long)";
	static Parser parse = new Parser(inputString, ruleset);
	static {
		System.out.println(parse.replaceRules(inputString));
	}
	private static final Rules rules = null;
	private static final String tree = null;
	private static final Parser.TestHook standardHook = new Parser(tree, rules).new TestHook();

	@Test
	public void testParser() {
	
	}

	@Test
	public void testReplaceRules() {
	
	}
	
	//@Test
	//public void removeParenthesesTest {
	//	assertEquals(standardHook.removeParenthesisTest("((Integer + Float))"), "Integer + Float");
	//}

}
