package hw5;

import static org.junit.Assert.*;
import hw5.COMP232LinkedBinaryTree.BTNode;

import org.junit.Test;

public class No11Tests {
	/*
	 * Helper method that traverses the tree and checks that every node is
	 * linked back to its proper parent.
	 */
	private void checkTreeStructure(BTNode<Integer, String> node) {

		if (node.left != null) {
			assertEquals("left child of " + node.key
					+ " has incorrect parent pointer", node, node.left.parent);
			checkTreeStructure(node.left);
		}

		if (node.right != null) {
			assertEquals("right child of " + node.key
					+ " has incorrect parent pointer", node, node.right.parent);
			checkTreeStructure(node.right);
		}
	}

	private void checkTreeValuesInOrder(BTNode<Integer, String> node,
			String expKeys) {
		/*
		 * Check the keys in the nodes of the tree against those in keys using
		 * an in-order traversal.
		 */
		String treeKeys = getValuesInOrder(node);
		assertEquals("Keys are not in proper locations in inorder traversal",
				expKeys, treeKeys);
	}

	private String getValuesInOrder(BTNode<Integer, String> root) {
		if (root != null) {
			String keys = getValuesInOrder(root.left);
			keys = keys + root.value;
			keys = keys + getValuesInOrder(root.right);
			return keys.trim();
		} else {
			return "";
		}
	}
	
	@Test
	public void testRemoveFromEmptyTree() {
		COMP232LinkedBinarySearchTree<Integer, String> bst = new COMP232LinkedBinarySearchTree<Integer, String>();
		String val = bst.remove(5);

		assertNull("There was nothing to remove.", val);
		assertEquals("Incorrect size", 0, bst.size());
	}

	@Test
	public void testRemoveFromOneNodeTree() {
		COMP232LinkedBinarySearchTree<Integer, String> bst = new COMP232LinkedBinarySearchTree<Integer, String>(
				30, "D");

		String val = bst.remove(30);

		assertEquals("Incorrect value returned", "D", val);
		assertEquals("Incorrect size", 0, bst.size());
		assertNull("Tree root should be null", bst.root);
	}

	@Test
	public void testRemoveLeftSubChildLeaf() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		COMP232LinkedBinarySearchTree<Integer, String> bst = new COMP232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		String val = bst.remove(10);

		assertEquals("Incorrect value returned", "A", val);
		assertEquals("Incorrect size", 6, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "BCDEFG");

		val = bst.remove(40);

		assertEquals("Incorrect value returned", "E", val);
		assertEquals("Incorrect size", 5, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "BCDFG");
	}

	@Test
	public void testRemoveRightSubChildLeaf() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		COMP232LinkedBinarySearchTree<Integer, String> bst = new COMP232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		String val = bst.remove(20);

		assertEquals("Incorrect value returned", "C", val);
		assertEquals("Incorrect size", 6, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "ABDEFG");

		val = bst.remove(50);

		assertEquals("Incorrect value returned", "G", val);
		assertEquals("Incorrect size", 5, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "ABDEF");
	}

	@Test
	public void testRemoveNodeWithLeftSubTreeOnly() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40 };
		String[] vals = { "D", "B", "F", "A", "C", "E" };

		COMP232LinkedBinarySearchTree<Integer, String> bst = new COMP232LinkedBinarySearchTree<Integer, String>(
				keys, vals);
		
		checkTreeValuesInOrder(bst.root, "ABCDEF");

		String val = bst.remove(45);

		assertEquals("Incorrect value returned", "F", val);
		assertEquals("Incorrect size", 5, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "ABCDE");

		// remove right child of B so it has only a left child.
		bst.root.left.right = null;
		bst.size--;

		val = bst.remove(15);

		assertEquals("Incorrect value returned", "B", val);
		assertEquals("Incorrect size", 3, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "ADE");
	}

	@Test
	public void testRemoveNodeWithRightSubTreeOnly() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		COMP232LinkedBinarySearchTree<Integer, String> bst = new COMP232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		// Create some nodes with only right subtrees.
		bst.root.left.left = null; // remove A from tree.
		bst.size--;
		bst.root.right.left = null; // remove E from tree.
		bst.size--;
		
		String val = bst.remove(15);

		assertEquals("Incorrect value returned", "B", val);
		assertEquals("Incorrect size", 4, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "CDFG");
		
		val = bst.remove(45);

		assertEquals("Incorrect value returned", "F", val);
		assertEquals("Incorrect size", 3, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "CDG");
	}

	@Test
	public void testRemoveNodeWithTwoChildren() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		COMP232LinkedBinarySearchTree<Integer, String> bst = new COMP232LinkedBinarySearchTree<Integer, String>(
				keys, vals);
		
		String val = bst.remove(15);

		assertEquals("Incorrect value returned", "B", val);
		assertEquals("Incorrect size", 6, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "ACDEFG");
		
		val = bst.remove(45);

		assertEquals("Incorrect value returned", "F", val);
		assertEquals("Incorrect size", 5, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "ACDEG");
	}
	
	@Test
	public void testRemoveRootWithTwoChildren() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		COMP232LinkedBinarySearchTree<Integer, String> bst = new COMP232LinkedBinarySearchTree<Integer, String>(
				keys, vals);
		
		String val = bst.remove(30);

		assertEquals("Incorrect value returned", "D", val);
		assertEquals("Incorrect size", 6, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "ABCEFG");
	}
}
