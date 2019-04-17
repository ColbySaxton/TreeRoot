package typecheck;

import java.util.List;

public class TypeBarricade {
	List<Object> tree;
	List<String> types;
	
	private String objectToString(int i) {
		String string = tree.get(i).getClass().getName();
		String[] objectString = string.split("\\.");
		return objectString[objectString.length - 1];
	}
	
	private boolean isEmpty() {
		return types.size() == 0;
	}
	
	private boolean isObjectinList(String object) {
		return types.contains(object);
	}
	
	public boolean isValid(List<Object> thistree, List<String> thistypes) {
		tree = thistree;
		types = thistypes;
		if(isEmpty()) {
			return false;
		}
		for(int i = 0; i >= tree.size(); i++) {
			String object;
			object = objectToString(i);
			if(!isObjectinList(object)) {
				return false;
			}
		}
		return true;
	}
	
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
