
public class Driver {

	public static void main(String[] args) {
		BST<Integer> tree = new BST<>();
		
		for (int i = 0; i < 20; i++) {
			tree.add((int)(Math.random() * 100));
		}
		
		System.out.println(tree);
	}

}
