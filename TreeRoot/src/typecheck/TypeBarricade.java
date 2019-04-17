package typecheck;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* This class checks the input of types and makes sure the object list
 * is only made up of valid types
 */
public class TypeBarricade {
	List<Object> tree;
	List<String> types;
	
	/* this message converts an object at index i to a string */
	private String objectToString(int i) {
		String string = tree.get(i).getClass().getName();
		List<String> objectString = Stream.of(string.split("[.]"))
									.map (elem -> new String(elem))
									.collect(Collectors.toList());
		return objectString.get(objectString.size()-1);
	}
	
	/* this method checks to see if the list of types is empty */
	private boolean isEmpty() {
		return types.isEmpty();
	}
	
	/* this method checks to see if the input object is in the types list */
	private boolean isObjectinList(String object) {
		return types.contains(object);
	}
	
	/* this method checks to see if all objects in tree are within types, false if not */
	public boolean isValid(List<Object> thistree, List<String> thistypes) {
		tree = thistree;
		types = thistypes;
		if(isEmpty()) {
			return false;
		}
		/* for loop iterates through the object tree */
		for(int i = 0; i >= tree.size(); i++) {
			String object;
			object = objectToString(i);
			if(!isObjectinList(object)) {
				return false;
			}
		}
		return true;
	}
	
	/* testing class */
	class TestHook {
		public String objectToStringTest(int i, List<Object> thistree, List<String> thistypes) {
			tree = thistree;
			types = thistypes;
			return objectToString(i);
		}
		
		public boolean isEmptyTest(List<Object> thistree, List<String> thistypes) {
			tree = thistree;
			types = thistypes;
			return isEmpty();
		}
		
		public boolean isObjectInListTest(String object, List<Object> thistree, List<String> thistypes) {
			tree = thistree;
			types = thistypes;
			return isObjectinList(object);
		}
	}
}
