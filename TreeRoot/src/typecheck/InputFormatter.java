package typecheck;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputFormatter {
	String parseString;
	List<Object> restOfTree;
	
	public InputFormatter(List<Object> tree) {
		restOfTree = tree;
	}
	
	public String makeTreeString() {
		for(Object currentNode: restOfTree) {		
				String typename = this.findCurrentType(currentNode).replaceAll("\\s+", "");
				typename = this.checkCurrentType(typename);
				this.insertIntoTree(typename);
		}
		return parseString;
	}
	
	private String findCurrentType(Object currentItem) {
		if (currentItem instanceof String) {
			return ((String) currentItem).startsWith("function") ? (String) currentItem : "String";
		}else {
			return Connector.stringMap.containsKey(currentItem) ? currentItem.toString() : currentItem.getClass().getName();
		}
	}
	
	private String checkCurrentType(String currentItem) {
		List<String> wordParts = Stream.of(currentItem.split("[.]"))
									.map (elem -> new String(elem))
									.collect(Collectors.toList());
		return wordParts.get(wordParts.size() - 1);
	}
	
	private void insertIntoTree(String currentItem) {
		parseString = parseString + currentItem;
	}
}
