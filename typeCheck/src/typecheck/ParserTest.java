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
	static String inputString3 = "((Integer+Float)+functionfunction(Primitive+Long))";
	static Parser parse1 = new Parser(inputString1, ruleset1);
	static Parser parse2 = new Parser(inputString2, ruleset2);
	static Parser parse3 = new Parser(inputString3, ruleset1);
	
	private static final Rules rules = null;
	private static final String tree = null;
	private static final Parser.TestHook standardHook = new Parser(tree, rules).new TestHook();
	private static boolean inputBool = true;

	/** replacerules test **/
	/* 3 replacements test rule1 */
	@Test
	public void testReplaceRules() {
		assertEquals(parse1.replaceRules(inputString1, inputBool), "Long");
	}
	
	/* mixed invalid rules */
	@Test
	public void testMixedInvalidRules() {
		assertEquals(parse2.replaceRules(inputString2, inputBool), "Float");
	}
	
	/* parenthesis in rules */
	@Test
	public void testParenthesisRules() {
		assertEquals(parse1.replaceRules(inputString3, inputBool), "Long");
	}
	
	/** works with removing parentheses **/
	/* has parentheses to remove */
	@Test
	public void trueremoveParenthesesTest() {
		assertEquals(standardHook.removeParenthesisTest("((Integer + Float))"), "Integer + Float");
	}
	
	/* does not have parentheses */
	@Test
	public void falseremoveParenthesesTest() {
		assertEquals(standardHook.removeParenthesisTest("Integer + Float"), "Integer + Float");
	}

}
