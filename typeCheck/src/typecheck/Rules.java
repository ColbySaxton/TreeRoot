package typecheck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/** The Rules for the parsing of the tree parser 
 * to follow. They all contain either an expression
 * or function that resolves into a single type
 * For example, Integer + Integer = Double is a valid rule
 * @author Justin
 *
 */
public class Rules {
	private HashMap<String, String> rulesMap;
	private StringBarricade barricade;
	private String readRulesFile;
	
	/**Creates a new instance of rules which will look
	 * in a certain file for rules and has specific
	 * allowed types
	 * @param readRules = the filename to read the rules from
	 * @param allowedTypes = the types allowed in the tree/rules
	 */
	public Rules(String readRules, List<String> allowedTypes) {
		readRulesFile = readRules;
		rulesMap = new HashMap<String, String>();
		barricade = new StringBarricade(allowedTypes);
	}
	
	/**Parses all the rules in the file
	 * makes sure the rules put in the tree all are in 
	 * valid format and resolve to allowed types through
	 * the String Barricade 
	 * Possibly throws an IO, FileNotFoundException if the file
	 * for rules does not exist.
	 */
	public void parseRules(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(readRulesFile));
			String line = reader.readLine();
			while(line != null) {
				if(barricade.isValid(line)) {
					List<String> ruleParts = Stream.of(line.replaceAll("\\s+", "").split("="))
							.map (elem -> new String(elem))
							.collect(Collectors.toList());
					addRule(ruleParts.get(0), ruleParts.get(1));
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			System.out.println("Most likely source of error, file not found");
			e.printStackTrace();
		}
	}
	
	/**Returns the map of the valid rules
	 * 
	 * @return the map of rules generated
	 */
	public HashMap<String, String> getMap(){
		return new HashMap<String, String>(rulesMap);
	}
	
	/** Method to add a rule to the existing ones
	 * 
	 * @param rule = the expression of the rule
	 * @param resultType = the resolved type of the rule
	 */
	private void addRule(String rule, String resultType) {
		rulesMap.put(rule, resultType);
	}
	
	class TestHook{
		public void addRuleTest(String rule, String resultType, HashMap<String, String> map) {
			rulesMap = map;
			addRule(rule, resultType);
		}
	}
}
