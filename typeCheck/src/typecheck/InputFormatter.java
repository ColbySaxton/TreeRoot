package typecheck;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputFormatter {
	private String parseString;
	private List<Object> restOfTree;
	
	/**Takes in the tree to turn into a string form
	 * 
	 * @param tree the valid parse tree
	 */
	public InputFormatter(List<Object> tree) {
		restOfTree = tree;
		parseString = "";
	}
	
	/**Makes the tree into a string by checking each item individually and
	 * replacing it with its type
	 * @return the tree in string form
	 */
	public String makeTreeString() {
		for(Object currentNode: restOfTree) {		
				String typename = this.checkCurrentType(currentNode).replaceAll("\\s+", "");
				typename = this.findCurrentType(typename);
				this.insertIntoTree(typename);
		}
		return parseString;
	}
	
	/**Checks what the type of each object is and
	 * in the case of a string whether it is a function
	 * or not
	 * Also checks whether it is a connector
	 * @param currentItem = the current item to be processed in the tree
	 * @return the String of the type name
	 */
	private String checkCurrentType(Object currentItem) {
		if (currentItem instanceof String) {
			return ((String) currentItem).startsWith("function") ? (String) currentItem : "String";
		}else {
			return currentItem instanceof Connector ? currentItem.toString() : currentItem.getClass().getName();
		}
	}
	
	/**Cuts the type to a single word since the checkType
	 * gets the names as java.lang.Integer and similar
	 * names so removes 
	 * @param currentItem = the type to be worked on
	 * @return the shortened name of the type
	 */
	private String findCurrentType(String currentItem) {
		List<String> wordParts = Stream.of(currentItem.split("[.]"))
									.map (elem -> new String(elem))
									.collect(Collectors.toList());
		return wordParts.get(wordParts.size() - 1);
	}
	
	/**Inserts the type into the string tree
	 * 
	 * @param currentItem = type to be inserted
	 */
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
