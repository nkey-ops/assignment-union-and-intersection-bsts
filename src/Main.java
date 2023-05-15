import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		int l = args.length == 1 ? Integer.parseInt(args[0]) : 10;
		
	 	BinarySearchTree<Book> bs1 = new BinarySearchTree<Book>();
	 	BinarySearchTree<Book> bs2 = new BinarySearchTree<Book>();

	 	//Generate Books
	 	for (int i = 0; i < l; i++) {
	 		if( i % 2 == 0) bs1.insert(new Book("T " + i, i));
	 		else bs2.insert(new Book("T " + i, i));
		}


	 	System.out.println("MERGING");
	 	System.out.println("BST 1:\n" + bs1);
	 	System.out.println("BST 2:\n" + bs2);

	 	System.out.println("Merged:\n" + merge(bs1, bs2));




	 	BinarySearchTree<Book> bs3 = new BinarySearchTree<Book>();
	 	BinarySearchTree<Book> bs4 = new BinarySearchTree<Book>();

	 	//Generate Books
	 	for (int i = 0; i < l; i++) {
	 		Book b =  new Book("T " + i, i);

	 		if(Math.random() > 0.5) {
	 			bs3.insert(b); bs4.insert(b);

	 		} else if( Math.random() > 0.5) bs3.insert(b);
	 		else bs4.insert(b);
		}

	 	System.out.println("INTERSECTION");
	 	System.out.println("BST 1:\n"+ bs3);
	 	System.out.println("BST 2:\n" + bs4);
	 	System.out.println("Intersected\n" + intersection(bs3, bs4));
		

	}


	
	/**
	 * Merge these two BST‘s into one height balanced BST with the lowest possible height. 
	 * 
	 * Hint: In-order traverse two initial BSTs and store all
	 * 	values in two sorted lists (ArrayList or LinkedList). 
	 * 
	 * Merge these two lists into one sorted list (one for loop may be used). 
	 * 
	 * Then construct a height-balanced BST from the merged sorted list. 
	 * The idea is to take middle element from the list and add it to final BST. 
	 * Then take the middle element from the left side of the list and add it to final BST. 
	 * Take the middle element from the right side of the list and add it to final BST. 
	 * 
	 * Repeat recursively. 
	 * 
	 * Print all trees and lists.
	 * 
	 */
	public static <T extends Comparable<T>>  BinarySearchTree<T>
					merge(BinarySearchTree<T> bs1,  BinarySearchTree<T> bs2 ) {

//		In-order traversing two BSTs and stores all 	values in two sorted lists.
		List<T> l1 = bs1.toList();
		List<T> l2 = bs2.toList();
		List<T> mergedSortedList = new ArrayList<T>();
		

//		Merging two lists into one sorted list. 
		for (int i = 0, j = 0; i < l1.size() || j < l2.size();) {
			if(i >= l1.size())
				mergedSortedList.add(l2.get(j++));
			else if (j >= l2.size())
				mergedSortedList.add(l1.get(i++));
			else if(l1.get(i).compareTo(l2.get(j)) > 0) 
				mergedSortedList.add(l2.get(j++));
			else 
				mergedSortedList.add(l1.get(i++));
			
		}

// 		Constructing height-balanced BST from merged sorted list	
		return toHBBST(mergedSortedList);
	}


	private static <T extends Comparable<T>> BinarySearchTree<T> 
								toHBBST(List<T> src) {

		return toHBBST(src, new BinarySearchTree<T>(), 0, src.size() - 1);
	}

	private static <T extends Comparable<T>> BinarySearchTree<T> 
								toHBBST(
										List<T> src,
										BinarySearchTree<T> dest,
										int low, int max) {
		
		if(low > max) return null;
			
		if(low < 0 || max >= src.size()) 
			throw new IllegalArgumentException();

		int mid = low + (max - low) / 2;

		dest.insert(src.get(mid));
		toHBBST(src, dest, low, mid - 1);
		toHBBST(src, dest, mid + 1, max);
		
		return dest;
	}


	
	
	/**
	 * Intersect these two BST‘s (i.e. find those elements which are in bothBSTs) 
	 * 	and create height balanced BST 
	 * 	with the lowest possible height from elements of intersection . 
	 * 
	 * Hint: In-order traverse two initial BSTs and store
	 * 	all values in two sorted lists (ArrayList or LinkedList). 
	 * 
	 * Intersect these two lists into one sorted list (one for loop may be used). 
	 * 
	 * Then construct a height-balanced BST from the intersected sorted list. 
	 * The idea is to take middle element from the list and add it to final BST. 
	 * Then take the middle element from the left side of the list and add it to final BST. 
	 * Take the middle element from the right side of the list and add it to final BST.
	 * 
	 * Repeat recursively. Print all trees and lists.
	 */
	public static <T extends Comparable<T>>  BinarySearchTree<T>
					intersection(BinarySearchTree<T> bs1,  BinarySearchTree<T> bs2 ) {

		// In-order traversing two BSTs and stores all 	values in two sorted lists.
		List<T> l1 = bs1.toList();
		List<T> l2 = bs2.toList();
		List<T> intersectedSortedList = new ArrayList<>();
	
		// Intersecting two lists into one sorted list (one for loop may be used). 
		for (int i = 0, j = 0; i < l1.size() && j < l2.size();) {

			if(l1.get(i).compareTo(l2.get(j)) > 0) { 
				if(l1.contains(l2.get(j++)))
				  intersectedSortedList.add(l2.get(j - 1));
				
			} else if (l1.get(i).compareTo(l2.get(j)) < 0) { 
				if( l2.contains(l1.get(i++))) 
				 intersectedSortedList.add(l1.get(i -  1));
			
			} else if(l1.get(i).compareTo(l2.get(j)) == 0) {
				intersectedSortedList.add(l1.get(i++));
				j++;
			}	
		}	

		// Constructing height-balanced BST from an intersected sorted list	
		return toHBBST(intersectedSortedList);
	}



	private static class Book  implements Comparable<Book>{
		private String title;
		private int id;

		public Book(String title, int id) {
			this.title = title;
			this.id = id;
		}


		@Override
		public int compareTo(Main.Book o) {
			return  Integer.compare(this.id, o.id);
		}


		@Override
		public String toString() {
			return "[" + title + "]";
		}
		
		
		
	}

}
