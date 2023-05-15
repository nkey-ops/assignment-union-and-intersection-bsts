import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
	protected Node<T> root;

	public void insert(T value) {
		if (root == null) {
			root = new Node<>(value);
		} else {
			root = insert(root, value);
		}
	}

	protected Node<T> insert(Node<T> n, T v) {
		if (n == null)
			return new Node<>(v);

		if (v.compareTo(n.data) < 0)
			n.left = insert(n.left, v);
		else if (v.compareTo(n.data) > 0)
			n.right = insert(n.right, v);
		else
			return null;

		return n;
	}

	public List<T> toList() {
		return toList(root, new ArrayList<T>());
	}

	private List<T> toList(Node<T> node, List<T> list) {
		if (node == null)
			return list;

		toList(node.left, list);
		list.add(node.data);
		toList(node.right, list);

		return list;
	}

	@Override
	public String toString() {
		if (root == null)
			return "";
		return root.toString(new StringBuilder(), true, new StringBuilder()).toString();
	}

	public static class Node<T extends Comparable<T>> {
		public T data;
		public Node<T> left;
		public Node<T> right;

		public long height;

		public Node(T value) {
			this.data = value;
		}

		@Override
		public String toString() {
			return data.toString();
		}

		public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
			if (right != null) {
				right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
			}
			sb.append(prefix).append(isTail ? "└── " : "┌── ").append(data.toString()).append("\n");
			if (left != null) {
				left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
			}
			return sb;
		}
	}

}
