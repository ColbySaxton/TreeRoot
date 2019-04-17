package typecheck;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputFormatter {
	private String parseString;
	private List<Object> restOfTree;
	
	public InputFormatter(List<Object> tree) {
		restOfTree = tree;
		parseString = "";
	}
	
	public String makeTreeString() {
		for(Object currentNode: restOfTree) {		
				String typename = this.checkCurrentType(currentNode).replaceAll("\\s+", "");
				typename = this.findCurrentType(typename);
				this.insertIntoTree(typename);
		}
		return parseString;
	}
	
	private String checkCurrentType(Object currentItem) {
		if (currentItem instanceof String) {
			return ((String) currentItem).startsWith("function") ? (String) currentItem : "String";
		}else {
			return currentItem instanceof Connector ? currentItem.toString() : currentItem.getClass().getName();
		}
	}
	
	private String findCurrentType(String currentItem) {
		List<String> wordParts = Stream.of(currentItem.split("[.]"))
									.map (elem -> new String(elem))
									.collect(Collectors.toList());
		return wordParts.get(wordParts.size() - 1);
	}
	
	private void insertIntoTree(String currentItem) {
		parseString = parseString + currentItem;
	}
	
	class TestHook{
		public String testFindCurrentType(String testItem) {
			return findCurrentType(testItem);
		}
	
		public String testCheckCurrentType(Object testItem) {
			return checkCurrentType(testItem);
		}
	}

}
