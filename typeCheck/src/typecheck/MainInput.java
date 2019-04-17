package typecheck;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class MainInput {
	
	private String rulesFilename;
	private List<Object> parseTree;
	private List<String> types;
	
	public void runParser(List<Object> userTree, String filename, List<String> validTypes){
		parseTree = userTree;
		rulesFilename = filename;
		types = validTypes;
		TreeBarricade validTreeChecker = new TreeBarricade();
		if(!validTreeChecker.isValid(parseTree)) {
			System.out.print("Tree entered is invalid:\nIssues could be an invalid starting item, multiple operators in a row,"
					+ "\nor mismatched parentheses.");
			System.exit(0);
		}
		TypeBarricade validTreeTypes = new TypeBarricade();
		if(!validTreeTypes.isValid(parseTree, types)) {
			System.out.print("Tree types are invalid:\nEither the tree contains invalid types\nor more types need to be specified as valid.");
			System.exit(0);
		}
		InputFormatter treeStringMaker = new InputFormatter(parseTree);
		String tree = treeStringMaker.makeTreeString();
		Rules ruleset = new Rules(rulesFilename, types);
		ruleset.parseRules();
		
		Parser parser = new Parser(tree, ruleset);
		System.out.print("The tree resolves to the type " + parser.replaceRules(tree, true) + " with the given types and rules");
	}
}
