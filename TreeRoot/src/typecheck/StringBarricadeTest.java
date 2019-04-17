package typecheck;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StringBarricadeTest {
	
	private StringBarricade.TestHook hook = new StringBarricade(null).new TestHook();
	private List<String> types = new ArrayList<String>();
	
	@Test
	public void testIsValid() {
		types.add("Integer");
		types.add("Double");
		types.add("Long");
		types.add("Complex");
		StringBarricade tester = new StringBarricade(types);
		
		assertFalse(tester.isValid(""));
		assertFalse(tester.isValid("a"));
		assertFalse(tester.isValid("==="));
		assertFalse(tester.isValid("Integer = Double"));
		assertFalse(tester.isValid("Double + Integer = Float"));
		assertTrue(tester.isValid("Integer + Double = Long"));
		assertTrue(tester.isValid("function F(Double, Complex) = Integer"));
	}

	@Test
	public void testIsValidSplit() {
		assertTrue(hook.isValidSplitTest(new String[]{"a", "b"}, ""));
		assertFalse(hook.isValidSplitTest(new String[]{}, ""));
		assertFalse(hook.isValidSplitTest(new String[]{"a", "b", "c"}, ""));
	}

	@Test
	public void testIsEmpty() {
		StringBarricade tester = new StringBarricade(null);
		tester.isValid("");
		assertTrue(tester.isEmpty());
		tester.isValid("av?xa");
		assertFalse(tester.isEmpty());
		tester.isValid(".");
		assertFalse(tester.isEmpty());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testSplitRuleAndReturn(){
		String[] testRule = new String[] {"Integer+Double", "Double"};
		assertEquals(testRule ,hook.splitRuleAndReturnTest("Integer + Double = Double"));
		testRule = new String[] {"Integer+Float+Double", "Double", "Float"};
		assertEquals(testRule ,hook.splitRuleAndReturnTest("Integer + Float + Double = Double = Float"));
		testRule = new String[] {"functionF(Integer,Integer)", "Long"};
		assertEquals(testRule ,hook.splitRuleAndReturnTest("function F(Integer, Integer) = Long"));
	}

	@Test
	public void testIsValidRule(){
		assertTrue(hook.isValidRuleTest("Integer+Double", "Integer + Double = Double"));
		assertTrue(hook.isValidRuleTest("Integer+Float+Double", "Integer + Float + Double = Double"));
		assertTrue(hook.isValidRuleTest("functionF(Integer,Integer)", "function F(Integer, Integer) = Long"));
		assertTrue(hook.isValidRuleTest("functionF(Double,Float)*functionZ(Long,Integer)", ""));
		assertFalse(hook.isValidRuleTest("a", "a"));
		assertFalse(hook.isValidRuleTest("aaFunctionS(Double,Float)", ""));
	}
}
