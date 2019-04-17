package typecheck;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class InputFormatterTest {

	static List<Object> tree = new ArrayList<Object>();

	@Test
	public void testMakeTreeString() {
		List<Object> tree = new ArrayList<Object>(this.tree);
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
		InputFormatter tester = new InputFormatter(null);
		
		assertEquals("java.lang.Integer", tester.tCheckCurrentType(new Integer(2)));
		assertEquals("java.lang.Double", tester.tCheckCurrentType(new Double(6.0)));
		assertEquals("String", tester.tCheckCurrentType("hello world"));
		assertEquals("function world", tester.tCheckCurrentType("function world"));
		assertEquals("+", tester.tCheckCurrentType(Operator.PLUS));
	}
	
	@Test
	public void testFindCurrentType() {
		InputFormatter tester = new InputFormatter(null);
		
		assertEquals("Integer", tester.tFindCurrentType("java.lang.Integer"));
		assertEquals("Double", tester.tFindCurrentType("java.lang.Double"));
		assertEquals("", tester.tFindCurrentType(""));
		assertEquals("functionf(Double,Double)", tester.tFindCurrentType("functionf(Double,Double)"));
	}
}
