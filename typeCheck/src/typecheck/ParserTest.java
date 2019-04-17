package typecheck;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ParserTest {
	
	private static final List<String> allowedTypes1 = new ArrayList<String>();
	private static final List<String> allowedTypes2 = new ArrayList<String>();
	static {
	allowedTypes1.add("Integer");
	allowedTypes1.add("Long");
	allowedTypes1.add("Float");
	allowedTypes1.add("functionfunction(Primitive + Long)");
	allowedTypes1.add("Double");
	allowedTypes1.add("functionfunk(Integer,Integer");
	allowedTypes1.add("(");
	allowedTypes1.add(")");
	allowedTypes2.add("Integer");
	allowedTypes2.add("Double");
	allowedTypes2.add("Float");
	allowedTypes2.add("Complex");
	}
	
	private static final Rules ruleset1 = new Rules("test/rules1",allowedTypes1);
	private static final Rules ruleset2 = new Rules("test/mixedInvalidRules", allowedTypes2);
	
	static {
		ruleset1.parseRules();
		ruleset2.parseRules();
	}
	static String inputString1 = "Integer+Float+functionfunction(Primitive+Long)";
	static String inputString2 = "Double+Integer+Double+functionfunk(Integer,Integer)";
	static Parser parse1 = new Parser(inputString1, ruleset1);
	static Parser parse2 = new Parser(inputString2, ruleset2);
	
	private static final Rules rules = null;
	private static final String tree = null;
	private static final Parser.TestHook standardHook = new Parser(tree, rules).new TestHook();

	@Test
	public void testParser() {
	
	}

	/** replacerules test **/
	/* 3 replacements test rule1 */
	@Test
	public void testReplaceRules() {
		boolean input1 = true;
		assertEquals(parse1.replaceRules(inputString1, input1), "Long");
	}
	
	/* mixed invalid rules */
	@Test
	public void testMixedInvalidRules() {
		boolean input = true;
		assertEquals(parse2.replaceRules(inputString2, input), "Float");
	}
	
	/* works with removing parentheses */
	
	//@Test
	//public void removeParenthesesTest {
	//	assertEquals(standardHook.removeParenthesisTest("((Integer + Float))"), "Integer + Float");
	//}

}
