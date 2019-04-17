package typecheck;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class InputFormatterTest {

	InputFormatter.TestHook tester = new InputFormatter(null).new TestHook();
	
	@Test
	public void testMakeTreeString() {
		List<Object> tree = new ArrayList<Object>();
		tree.add(Bridger.OPEN);
		tree.add(2);
		tree.add(Operator.PLUS);
		tree.add(6.0);
		tree.add(Bridger.CLOSE);
		tree.add(Operator.MINUS);
		tree.add(new Float(66));
		tree.add(Operator.TIMES);
		tree.add(new Long(1000000));
		
		InputFormatter formatter = new InputFormatter(tree);
		assertEquals("(Integer+Double)-Float*Long",formatter.makeTreeString());
		
	}
	
	@Test
	public void testCheckCurrentType() {
		assertEquals("java.lang.Integer", tester.testCheckCurrentType(new Integer(2)));
		assertEquals("java.lang.Double", tester.testCheckCurrentType(new Double(6.0)));
		assertEquals("String", tester.testCheckCurrentType("hello world"));
		assertEquals("function world", tester.testCheckCurrentType("function world"));
		assertEquals("+", tester.testCheckCurrentType(Operator.PLUS));
	}
	
	@Test
	public void testFindCurrentType() {
		assertEquals("Integer", tester.testFindCurrentType("java.lang.Integer"));
		assertEquals("Double", tester.testFindCurrentType("java.lang.Double"));
		assertEquals("", tester.testFindCurrentType(""));
		assertEquals("functionf(Double,Double)", tester.testFindCurrentType("functionf(Double,Double)"));
	}
}
