Union and intersection of two Binary Search Trees (BST).
Create two binary search trees (BST) which store various Books in nodes. Each book object has unique id
and title. Use id of book as a key.

a) Merge these two BST‘s into one height balanced BST with the lowest possible height.
Hint: In-order traverse two initial BSTs and store all values in two sorted lists (ArrayList or LinkedList).
Merge these two lists into one sorted list (one for loop may be used).
Then construct a height-balanced BST from the merged sorted list. The idea is to take middle element from
the list and add it to final BST. Then take the middle element from the left side of the list and add it to final
BST. Take the middle element from the right side of the list and add it to final BST. Repeat recursively.
Print all trees and lists.

b) Intersect these two BST‘s (i.e. find those elements which are in both BSTs) and create height balanced
BST with the lowest possible height from elements of intersection .
Hint: In-order traverse two initial BSTs and store all values in two sorted lists (ArrayList or LinkedList).
Intersect these two lists into one sorted list (one for loop may be used).
Then construct a height-balanced BST from the intersected sorted list. The idea is to take middle element
from the list and add it to final BST. Then take the middle element from the left side of the list and add it to
final BST. Take the middle element from the right side of the list and add it to final BST. Repeat recursively.
Print all trees and lists.