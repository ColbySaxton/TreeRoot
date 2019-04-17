package typecheck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Rules {
	private HashMap<String, String> rulesMap;
	private StringBarricade barricade;
	private String readRulesFile;
	
	public Rules(String readRules) {
		readRulesFile = readRules;
	}
	
	public void parseRules(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(readRulesFile));
			String line = reader.readLine();
			if(barricade.isValid(line)) {
				List<String> ruleParts = Stream.of(line.replaceAll("\\s+", "").split("="))
						.map (elem -> new String(elem))
						.collect(Collectors.toList());
				addRule(ruleParts.get(0), ruleParts.get(1));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String, String> getMap(){
		return new HashMap<String, String>(rulesMap);
	}
	
	private void addRule(String rule, String resultType) {
		rulesMap.put(rule, resultType);
	}
}
