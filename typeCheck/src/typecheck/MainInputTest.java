package typecheck;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import typecheck.Parser.TestHook;

public class MainInputTest {
	
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	@Before
	public void setUpStream() {
		System.setOut(new PrintStream(outContent));
	}
	
	@After
	public void restoreStream() {
		System.setOut(originalOut);
	}
	static List<Object> list1 = new ArrayList<Object>();
	static List<Object> list2 = new ArrayList<Object>();
	
	private static final List<String> allowedTypes1 = new ArrayList<String>();
	private static final List<String> allowedTypes2 = new ArrayList<String>();
	static {
		int i = 1;
		Operator plus = Operator.PLUS;
		Operator minus = Operator.MINUS;
		Operator exp = Operator.EXPONENTIAL;
		Bridger open = Bridger.OPEN;
		Bridger close = Bridger.CLOSE;
		double j = 1.0;
		float k = (float)1.134;
		allowedTypes1.add("Integer");
		allowedTypes1.add("Long");
		allowedTypes1.add("Float");
		allowedTypes1.add("+");
		allowedTypes1.add("functionfunction(Primitive + Long)");
		allowedTypes1.add("Double");
		allowedTypes1.add("functionfunk(Integer,Integer");
		allowedTypes1.add("(");
		allowedTypes1.add(")");
		allowedTypes2.add("Integer");
		allowedTypes2.add("Double");
		allowedTypes2.add("Float");
		allowedTypes2.add("Complex");
		list1.add(i);
		list1.add(plus);
		list1.add(k);
		list2.add(i);
		list2.add(plus);
		list2.add(plus);
		list2.add(k);
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
	private static boolean inputBool = true;
	
	private static MainInput newMain = new MainInput();

	/** Test runParser **/
	/* test normal stuff */
	@Test
	public void testSimpleNominal() {
		newMain.runParser(list1, "test/rules1", allowedTypes1);
		assertEquals("The tree resolves to the type Float with the given types and rules", outContent.toString());
		System.out.flush();
	}
	
	/* test fails due to bad object list */

	//@Test
	//public void testEnterRulesFile() {
	//	fail("Not yet implemented");
	//}

	//@Test
	//public void testEnterTypes() {
	//	fail("Not yet implemented");
	//}

	//@Test
	//public void testMain() {
	//	fail("Not yet implemented");
	//}

}
