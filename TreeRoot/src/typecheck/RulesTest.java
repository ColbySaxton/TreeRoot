package typecheck;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class RulesTest {

	@Test
	public void testParseRulesBasicRule() {
		List<String> allowedTypes = new ArrayList<String>();
		allowedTypes.add("Integer");
		allowedTypes.add("Double");
		allowedTypes.add("Float");
		allowedTypes.add("Complex");
		
		Rules ruleset = new Rules("test/basicRule",allowedTypes);
		
		ruleset.parseRules();
		assertTrue(ruleset.getMap().containsKey("Double+Integer"));
	}
	
	@Test
	public void testParseRulesBasicFunctionRule() {
		List<String> allowedTypes = new ArrayList<String>();
		allowedTypes.add("Integer");
		allowedTypes.add("Double");
		allowedTypes.add("Float");
		allowedTypes.add("Complex");
		
		Rules ruleset = new Rules("test/basicFunctionRule", allowedTypes);
		
		ruleset.parseRules();
		assertTrue(ruleset.getMap().containsKey("functionF(Double,Complex)"));
	}
	
	@Test
	public void testParseRulesMultipleRule() {
		List<String> allowedTypes = new ArrayList<String>();
		allowedTypes.add("Integer");
		allowedTypes.add("Double");
		allowedTypes.add("Float");
		allowedTypes.add("Complex");
		
		Rules ruleset = new Rules("test/multipleRule", allowedTypes);
		
		ruleset.parseRules();
		assertTrue(ruleset.getMap().containsKey("Double+Integer"));
		assertTrue(ruleset.getMap().containsKey("functionF(Double,Complex)"));
	}
	
	@Test
	public void testParseRulesNullFile() {
		List<String> allowedTypes = new ArrayList<String>();
		allowedTypes.add("Integer");
		allowedTypes.add("Double");
		allowedTypes.add("Float");
		allowedTypes.add("Complex");
		
		Rules ruleset = new Rules("test/nullFile", allowedTypes);
		
		ruleset.parseRules();
		assertTrue(ruleset.getMap().isEmpty());
	}
	
	@Test
	public void testParseRulesInvalidNormalRule() {
		List<String> allowedTypes = new ArrayList<String>();
		allowedTypes.add("Integer");
		allowedTypes.add("Double");
		allowedTypes.add("Float");
		allowedTypes.add("Complex");
		
		Rules ruleset = new Rules("test/invalidNormalRule", allowedTypes);
		
		ruleset.parseRules();
		assertTrue(ruleset.getMap().isEmpty());
	}
	
	@Test
	public void testParseRulesInvalidFunctionRule() {
		List<String> allowedTypes = new ArrayList<String>();
		allowedTypes.add("Integer");
		allowedTypes.add("Double");
		allowedTypes.add("Float");
		allowedTypes.add("Complex");
		
		Rules ruleset = new Rules("test/invalidFunctionRule", allowedTypes);
		
		ruleset.parseRules();
		assertTrue(ruleset.getMap().isEmpty());
	}
	
	@Test
	public void testParseRulesMixedInvalidRules() {
		List<String> allowedTypes = new ArrayList<String>();
		allowedTypes.add("Integer");
		allowedTypes.add("Double");
		allowedTypes.add("Float");
		allowedTypes.add("Complex");
		
		Rules ruleset = new Rules("test/mixedInvalidRules", allowedTypes);
		
		ruleset.parseRules();
		assertTrue(ruleset.getMap().containsKey("Double+Integer"));
		assertFalse(ruleset.getMap().containsKey("functionF(Double,Complex)"));
		assertFalse(ruleset.getMap().containsKey("functionG(Double,Float)"));
		assertTrue(ruleset.getMap().containsKey("functionfunk(Integer,Integer)"));
		assertFalse(ruleset.getMap().containsKey("Integer+Integer"));
		assertTrue(ruleset.getMap().containsKey("Complex+Complex"));
	}

	@Test
	public void testGetMap() {
		Rules ruleset = new Rules("",null);
		assertTrue(ruleset.getMap().isEmpty());
	}

}
