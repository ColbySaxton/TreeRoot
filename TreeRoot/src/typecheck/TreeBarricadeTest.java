package typecheck;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TreeBarricadeTest {

	private static final TreeBarricade.TestHook standardHook = new TreeBarricade().new TestHook();
	private static final TreeBarricade barricade = new TreeBarricade();
	private static final List<Object> goodTree = new ArrayList<Object>();
	private static final List<Object> emptyTree = new ArrayList<Object>();
	private static final List<Object> doubleOpTree = new ArrayList<Object>();
	private static final List<Object> doubleNonTree = new ArrayList<Object>();
	private static final List<Object> wrongParenTree = new ArrayList<Object>();
	private static final List<Object> unevenParenTree = new ArrayList<Object>();
	private static final List<Object> minusTree = new ArrayList<Object>();
	private static final List<Object> expfirstTree = new ArrayList<Object>();
	private static final List<Object> plusEndTree = new ArrayList<Object>();
	private static final List<Object> openEndTree = new ArrayList<Object>();
	private static final List<Object> stringGood = new ArrayList<Object>();
	private static final List<Object> stringBad = new ArrayList<Object>();
	
	static {
		int i = 1;
		Operator plus = Operator.PLUS;
		Operator minus = Operator.MINUS;
		Operator exp = Operator.EXPONENTIAL;
		Bridger open = Bridger.OPEN;
		Bridger close = Bridger.CLOSE;
		double j = 1.0;
		String func = "Function john(Integer + Double)";
		String bad = "whats up";
		goodTree.add(i);
		goodTree.add(plus);
		goodTree.add(j);
		doubleOpTree.add(i);
		doubleOpTree.add(plus);
		doubleOpTree.add(plus);
		doubleOpTree.add(j);
		doubleNonTree.add(i);
		doubleNonTree.add(j);
		wrongParenTree.add(close);
		wrongParenTree.add(open);
		wrongParenTree.add(j);
		unevenParenTree.add(open);
		unevenParenTree.add(i);
		unevenParenTree.add(plus);
		unevenParenTree.add(j);
		unevenParenTree.add(close);
		unevenParenTree.add(close);
		minusTree.add(minus);
		minusTree.add(i);
		minusTree.add(plus);
		minusTree.add(j);
		expfirstTree.add(exp);
		expfirstTree.add(i);
		expfirstTree.add(plus);
		plusEndTree.add(i);
		plusEndTree.add(plus);
		openEndTree.add(i);
		openEndTree.add(plus);
		openEndTree.add(j);
		openEndTree.add(open);
		stringGood.add(i);
		stringGood.add(plus);
		stringGood.add(func);
		stringBad.add(i);
		stringBad.add(plus);
		stringBad.add(bad);
	}
	
	/** iterateThroughTree tests **/
	/* simple test */
	@Test
	public void simpleIterateThroughTest() {
		assertTrue(standardHook.iterateThroughTreeTest(goodTree));
	}
	
	/* empty test */
	@Test
	public void emptyIterateThroughTest() {
		//System.out.println(goodTree.size());
		assertTrue(standardHook.iterateThroughTreeTest(emptyTree));
	}
	
	/* repeat tests */
	@Test
	public void repeatIterateThroughTest() {
		//System.out.println("init" + doubleOpTree.size());
		assertFalse(standardHook.iterateThroughTreeTest(doubleOpTree));
		assertFalse(standardHook.iterateThroughTreeTest(doubleNonTree));
	}
	
	/* out of order parenthesis */
	@Test
	public void outParenIterateThroughTest() {
		assertFalse(standardHook.iterateThroughTreeTest(wrongParenTree));
	}
	
	/* uneven # of parenthesis */
	@Test
	public void unevenParenIterateThroughTest() {
		assertFalse(standardHook.iterateThroughTreeTest(unevenParenTree));
	}
	
	/* string not good */
	@Test
	public void badStringIterateThroughTest() {
		assertFalse(standardHook.iterateThroughTreeTest(stringBad));
	}
	
	/** isNotStringFunction **/
	/* is not a string */
	@Test
	public void notStringTest() {
		assertFalse(standardHook.isNotStringFunctionTest(0, stringGood));
	}
	
	/* is a string that is not a function */
	@Test
	public void stringNotFunctionTest() {
		assertTrue(standardHook.isNotStringFunctionTest(2, stringBad));
	}
	
	/* is a function string */
	@Test
	public void stringIsFunctionTest() {
		assertFalse(standardHook.isNotStringFunctionTest(2, stringGood));
	}
	
	/** hasRepeatOperator test **/
	/* index 0 test */
	@Test
	public void zeroHasRepeatOperatorTest() {
		assertFalse(standardHook.hasRepeatOperatorTest(0, goodTree));
	}
	
	/* index normal non-repeat test */
	@Test
	public void normalRepeatOperatorTest() {
		assertFalse(standardHook.hasRepeatOperatorTest(2, goodTree));
	}
	
	/* triggers repeat test */
	@Test
	public void triggerRepeatOperatorTest() {
		assertTrue(standardHook.hasRepeatOperatorTest(2, doubleOpTree));
	}
	
	/** hasRepeatNonConnector test **/
	/* index 0 test */
	@Test
	public void zeroHasRepeatNonTest() {
		assertFalse(standardHook.hasRepeatNonConnectorTest(0, goodTree));
	}
	
	/* index normal non-repeat test */
	@Test
	public void normalRepeatNonTest() {
		assertFalse(standardHook.hasRepeatNonConnectorTest(2, goodTree));
	}
	
	/* triggers repeat test */
	@Test
	public void triggerRepeatNonTest() {
		assertTrue(standardHook.hasRepeatNonConnectorTest(1, doubleNonTree));
	}
	
	/** checkOpen test **/
	/* is not open */
	@Test
	public void nonCheckOpenTest() {
		assertEquals(standardHook.checkOpenTest(1, 2, goodTree), 2);
		assertEquals(standardHook.checkOpenTest(0, 2, wrongParenTree), 2);
	}
	
	/* is open */
	@Test
	public void isCheckOpenTest() {
		assertEquals(standardHook.checkOpenTest(1, 2, wrongParenTree), 3);
	}
	
	/** checkClose test **/
	/* is not close */
	@Test
	public void nonCheckCloseTest() {
		assertEquals(standardHook.checkCloseTest(1, 2, goodTree), 2);
		assertEquals(standardHook.checkCloseTest(1, 2, wrongParenTree), 2);
	}
	
	/* is open */
	@Test
	public void isCheckCloseTest() {
		assertEquals(standardHook.checkCloseTest(0, 2, wrongParenTree), 3);
	}
	
	/** checkCorrectOrderedParentheses test **/
	/* non paren test */
	@Test
	public void noparenCheckCorrectOrderedParenTest() {
		assertEquals(standardHook.checkCorrectOrderedParenthesisTest(1, 2, goodTree), 2);
	}
	
	/* open test */
	@Test
	public void openCheckCorrectOrderedParenTest() {
		assertEquals(standardHook.checkCorrectOrderedParenthesisTest(0, 2, wrongParenTree), 1);
	}
	
	/* open test */
	@Test
	public void closeCheckCorrectOrderedParenTest() {
		assertEquals(standardHook.checkCorrectOrderedParenthesisTest(1, 2, wrongParenTree), 3);
	}
	
	/** hasValidFirstObject tests **/
	/* is valid */
	@Test
	public void validFirstObjectTest() {
		assertTrue(standardHook.hasValidFirstObjectTest(goodTree));
	}
	
	/* is minus */
	@Test
	public void minusFirstObjectTest() {
		assertTrue(standardHook.hasValidFirstObjectTest(minusTree));
	}
	
	/* is bad start */
	@Test
	public void expFirstObjectTest() {
		assertFalse(standardHook.hasValidFirstObjectTest(expfirstTree));
	}
	
	/** hasValidLastObject tests **/
	/* is valid */
	@Test
	public void validLastObjectTest() {
		assertTrue(standardHook.hasValidLastObjectTest(goodTree));
	}
	
	/* is close */
	@Test
	public void closeFirstObjectTest() {
		assertTrue(standardHook.hasValidLastObjectTest(unevenParenTree));
	}
	
	/* is bad start */
	@Test
	public void invalidFirstObjectTest() {
		assertFalse(standardHook.hasValidLastObjectTest(plusEndTree));
		assertFalse(standardHook.hasValidLastObjectTest(openEndTree));
	}
	
	/** areRepeats test **/
	/* no repeats */
	@Test
	public void noAreRepeatsTest() {
		assertFalse(standardHook.areRepeatsTest(1, goodTree));
	}
	
	/* operator repeat */
	@Test
	public void opAreRepeatsTest() {
		assertTrue(standardHook.areRepeatsTest(2, doubleOpTree));
	}
	
	/* non connector repeat */
	@Test
	public void nonConnectorAreRepeatsTest() {
		assertTrue(standardHook.areRepeatsTest(1, doubleNonTree));
	}
	
	/** isEmpty test **/
	/* not empty */
	@Test
	public void notEmptyTest() {
		assertFalse(standardHook.isEmptyTest(goodTree));
	}
	
	/* is empty */
	@Test
	public void emptyTest() {
		assertTrue(standardHook.isEmptyTest(emptyTree));
	}
	
	/** isValid test **/
	/* isValid true */
	@Test
	public void goodIsValid() {
		assertTrue(barricade.isValid(goodTree));
	}
	
	/* isValid empty */
	@Test
	public void emptyIsValid() {
		assertFalse(barricade.isValid(emptyTree));
	}
	
	/* not isValid first and last */
	@Test
	public void firstLastIsValid() {
		assertFalse(barricade.isValid(expfirstTree));
		assertFalse(barricade.isValid(plusEndTree));
	}

}
