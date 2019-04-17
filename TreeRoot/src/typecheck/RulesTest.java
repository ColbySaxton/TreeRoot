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
		assertTrue(ruleset.getMap().containsKey("functionF(Double,Complex)"));
	}

	@Test
	public void testGetMap() {
		Rules ruleset = new Rules("",null);
		assertTrue(ruleset.getMap().isEmpty());
	}

}
