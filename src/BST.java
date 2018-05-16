import java.util.Iterator;


public class BST<E extends Comparable<E>> implements Set<E> 
{
	private int size;
	private Node<E> root;
	
	public BST()
	{
		size = 0;
		root = null;
	}

	/**
	 * Find the node that contains v if it exits. If it doesn't exist
	 * then return the node that would have been the parent of a node
	 * containing v.
	 * @param r root of the subtree
	 * @param v value we are searching for
	 * @return Node containing v or node that would have a parent of v
	 */
	private Node<E> find(Node<E> r, E v)
	{
		//Case 1: Tree is empty
		if(r == null)
		{
			return null;
		}
		
		//Case 2: r contains v OR r is a leaf 
		if(r.value.equals(v))
		{
			return r;
		}
		
		//Case 3: r contains a value that is less than v
		if(r.value.compareTo(v) < 0 && r.right != null)
		{
			return find(r.right, v);
		}
		
		//Case 4: r contains a value that is greater than v
		if(r.value.compareTo(v) > 0 && r.left != null)
		{
			return find(r.left, v);
		}
		
		
		//All other cases: I didn't find the value
		return r;
		
	}
	
	/**
	 * Adds a node with the value k if it doesn't exist
	 * @param k value to be inserted
	 * @return true if value was inserted successfully
	 */
	public boolean add(E k) 
	{
		Node<E> n = find(root, k);
		
		//Case 1: tree is empty
		if(n == null)
		{
			root = new Node<>(k);
		}
		
		//Case 2: value exists already
		else if(n.value.equals(k))
		{
			return false;
		}
		
		else if(n.value.compareTo(k) < 0)
		{
			n.right = new Node<>(k);
		}
		else
		{
			n.left = new Node<>(k);
		}
		size++;
		return true;
	}

	/**
	 * Remove the node with value v and return the root of the subtree with the node removed
	 * @param r root of the subtree 
	 * @param v value to remove
	 * @return root of the new subtree
	 */
	private Node<E> remove(Node<E> r, E v)
	{
		//Case 1: tree is empty
		if(r == null)
		{
			return null;
		}
		if(r.value.compareTo(v) < 0)
		{
			r.right = remove(r.right, v);
		}
		else if(r.value.compareTo(v) > 0)
		{
			r.left = remove(r.left, v);
		}
		else
		{
			size--;
			//Case 1: leaf
			if(r.right == null && r.left == null)
			{
				return null;
			}
			//Case 2: single child
			if(r.right == null)
			{
				return r.left;
			}
			if(r.left == null)
			{
				return r.right;
			}
			//Case 3: two children;
			Node<E> pred = r.left;
			while(pred.right != null)
			{
				pred = pred.right;
			}
			r.value = pred.value;
			r.left = remove(r.left, pred.value);
		}
		
		return r;
		
	}
	
	public boolean remove(E k) 
	{
		int oldSize = size;
		root = remove(root, k);
		return oldSize > size;
	}

	
	private void toString(Node<E> r, StringBuilder sb, int level)
	{
		if(r != null)
		{
			//Print the root
			for(int i=0; i < 2 * level; i++)
			{
				sb.append(" ");
			}
			
			//Recursively print the left and right children
			sb.append(r.value + "\n");
			
			toString(r.left, sb, level+1);
			toString(r.right, sb, level+1);
		}
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		toString(root, sb, 0);
		return sb.toString();
	}

	
	public boolean contains(E k)
	{
		Node<E> n = find(root, k);
		return n != null && n.value.equals(k);
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAll(Set<E> other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retainAll(Set<E> other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAll(Set<E> other) {
		// TODO Auto-generated method stub
		
	}

	private static class Node<T extends Comparable<T>>
	{
		private T value;
		private Node<T> left;
		private Node<T> right;
		
		public Node(T v)
		{
			value = v;
			left = null;
			right = null;
		}
		
		public String toString()
		{
			return value.toString();
		}
	}
}
