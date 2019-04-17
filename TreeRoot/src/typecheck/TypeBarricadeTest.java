package typecheck;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TypeBarricadeTest {
	
	private static final TypeBarricade.TestHook standardHook = new TypeBarricade().new TestHook();
	private static final TypeBarricade barricade = new TypeBarricade();
	private static final List<Object> goodTree = new ArrayList<Object>();
	private static final List<Object> badTree = new ArrayList<Object>();
	private static final List<String> goodTypes = new ArrayList<String>();
	private static final List<String> emptyTypes = new ArrayList<String>();
	
	static {
		int i = 1;
		Operator plus = Operator.PLUS;
		Operator minus = Operator.MINUS;
		Operator exp = Operator.EXPONENTIAL;
		Bridger open = Bridger.OPEN;
		Bridger close = Bridger.CLOSE;
		double j = 1.0;
		String foo = "function(Integer + Double)";
		String integer = "Integer";
		String duoble = "Double";
		String add = "+";
		goodTree.add(i);
		goodTree.add(plus);
		goodTree.add(j);
		goodTypes.add(integer);
		goodTypes.add(foo);
		goodTypes.add(duoble);
		goodTypes.add(add);
		badTree.add(i);
		badTree.add(plus);
		badTree.add(j);
		badTree.add(open);
	}

	/** objectToString tests **/
	/* objectToString integer */
	@Test
	public void ObjectToStringTest() {
		assertEquals(standardHook.objectToStringTest(0, goodTree, goodTypes), "Integer");
		assertEquals(standardHook.objectToStringTest(1, goodTree, goodTypes), "Operator");
		assertEquals(standardHook.objectToStringTest(2, goodTree, goodTypes), "Double");
	}
	
	/** isEmpty tests **/
	/* not empty */
	@Test
	public void notIsEmptyTest() {
		assertFalse(standardHook.isEmptyTest(goodTree, goodTypes));
	}
	
	/* is empty */
	@Test
	public void isEmptyTest() {
		assertTrue(standardHook.isEmptyTest(goodTree, emptyTypes));
	}
	
	/** isObjectinList tests **/
	/* isInList */
	@Test
	public void objectisinListTest() {
		assertTrue(standardHook.isObjectInListTest("Integer", goodTree, goodTypes));
		assertTrue(standardHook.isObjectInListTest("Double", goodTree, goodTypes));
		assertTrue(standardHook.isObjectInListTest("function(Integer + Double)", goodTree, goodTypes));
	}
	
	/** isValid tests **/
	/* isValid true */
	@Test
	public void isValidTrueTest() {
		assertTrue(barricade.isValid(goodTree, goodTypes));
	}
	
	/* is empty */
	@Test
	public void isValidEmptyTest() {
		assertFalse(barricade.isValid(goodTree, emptyTypes));
	}
	
	/* does not match */
	@Test
	public void isNotValidTest() {
		assertFalse(barricade.isValid(badTree, emptyTypes));
	}

}
