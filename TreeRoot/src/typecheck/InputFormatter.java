package typecheck;

import java.util.List;

public class InputFormatter {
	String parseString;
	List<Object> restOfTree;
	
	public InputFormatter(List<Object> tree) {
		restOfTree = tree;
	}
	
	public String makeTreeString() {
		for(Object currentNode: restOfTree) {
			String typename = this.findCurrentType(currentNode);
			typename = this.checkCurrentType(typename);
			this.insertIntoTree(typename);
		}
		return parseString;
	}
	
	private String findCurrentType(Object currentItem) {
		return Connector.stringMap.containsKey(currentItem) ? currentItem.toString() : currentItem.getClass().getName();
	}
	
	private String checkCurrentType(String currentItem) {
		int lastClass = currentItem.lastIndexOf(".");
		return lastClass != -1 ? currentItem.substring(lastClass + 1) : currentItem;
	}
	
	private void insertIntoTree(String currentItem) {
		parseString = parseString + " " + currentItem;
	}
}
