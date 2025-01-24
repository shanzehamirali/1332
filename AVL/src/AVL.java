import java.util.Collection;
import java.util.Set;
import java.util.NoSuchElementException;
import java.util.HashSet;

/**
 * Your implementation of an AVL.
 *
 * @author Shanzeh Amirali
 * @version 1.0
 * @userid samirali3
 * @GTID 903683513
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class AVL<T extends Comparable<? super T>> {

    // Do not add new instance variables or modify existing ones.
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        for (T i: data) {
            if (i == null) {
                throw new IllegalArgumentException("No null elements allowed");
            }
            this.add(i);
        }
    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        root = addHelper(data, root);
    }

    /**
     * Add helper method; adds data at next open spot and calls methods to rebalance
     * and update balance factors and heights.
     * @param data the data to add
     * @param node current node to add to
     * @return balanced node
     */
    private AVLNode<T> addHelper(T data, AVLNode<T> node) {
        if (node == null) {
            size++;
            return new AVLNode<>(data);
        }
        int comp = data.compareTo(node.getData());
        if (comp < 0) {
            node.setLeft(addHelper(data, node.getLeft()));
        } else if (comp > 0) {
            node.setRight(addHelper(data, node.getRight()));
        } else {
            return node;
        }
        update(node);
        return balancer(node);
    }

    /**
     * Updates the height and balance factors.
     * @param node node to recalculate
     */
    private void update(AVLNode<T> node) {
        int left = height(node.getLeft());
        int right = height(node.getRight());
        node.setHeight(Math.max(left, right) + 1);
        node.setBalanceFactor(left - right);
    }

    /**
     * Rebalances AVL.
     * @param node node to balance
     * @return balanced node
     */
    private AVLNode<T> balancer(AVLNode<T> node) {
        if (node.getBalanceFactor() < -1) {
            if (node.getRight().getBalanceFactor() > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            node = rotateLeft(node);
        } else if (node.getBalanceFactor() > 1) {
            if (node.getLeft().getBalanceFactor() < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            node = rotateRight(node);
        }
        return node;
    }

    /**
     * Right rotation method.
     * @param node node to rotate
     * @return rotated node
     */
    private AVLNode<T> rotateRight(AVLNode<T> node) {
        AVLNode<T> temp = node.getLeft();
        node.setLeft(temp.getRight());
        temp.setRight(node);
        update(node);
        update(temp);
        return temp;
    }

    /**
     * Left rotation method.
     * @param node node to rotate
     * @return rotated node
     */
    private AVLNode<T> rotateLeft(AVLNode<T> node) {
        AVLNode<T> temp = node.getRight();
        node.setRight(temp.getLeft());
        temp.setLeft(node);
        update(node);
        update(temp);
        return temp;
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data, NOT predecessor. As a reminder, rotations can occur
     * after removing the successor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        AVLNode<T> removed = new AVLNode<>(null);
        root = removeHelper(data, root, removed);
        return removed.getData();
    }

    /**
     * Remove helper method using successor.
     * @param data data to be removed
     * @param node current node to search
     * @param removed node that will store removed node
     * @return balanced parent node after removal
     */
    private AVLNode<T> removeHelper(T data, AVLNode<T> node, AVLNode<T> removed) {
        if (node == null) {
            throw new NoSuchElementException("Data not found");
        }
        int comp = data.compareTo(node.getData());
        if (comp > 0) {
            node.setRight(removeHelper(data, node.getRight(), removed));
        } else if (comp < 0) {
            node.setLeft(removeHelper(data, node.getLeft(), removed));
        } else {
            size--;
            removed.setData(node.getData());
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                AVLNode<T> child = new AVLNode<>(null);
                node.setRight(successor(node.getRight(), child));
                node.setData(child.getData());
            }
        }
        update(node);
        return balancer(node);
    }

    /**
     * Obtains successor of node to be removed.
     * @param node current node
     * @param child child node of to be removed node
     * @return successor node
     */
    private AVLNode<T> successor(AVLNode<T> node, AVLNode<T> child) {
        if (node.getLeft() == null) {
            child.setData(node.getData());
            return node.getRight();
        }
        node.setLeft(successor(node.getLeft(), child));
        update(node);
        return balancer(node);
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        return getHelper(data, root);
    }

    /**
     * Get helper method.
     * @param data data to search for
     * @param node current node to search
     * @return data from node if found
     */
    private T getHelper(T data, AVLNode<T> node) {
        if (node == null) {
            throw new NoSuchElementException("Data not found");
        }
        int temp = data.compareTo(node.getData());
        if (temp < 0) {
            return getHelper(data, node.getLeft());
        } else if (temp > 0) {
            return getHelper(data, node.getRight());
        } else {
            return node.getData();
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree.
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (root == null) {
            return false;
        }
        return (getHelper(data, root) != null);
    }

    /**
     * Returns the height of the root of the tree.
     *
     * Should be O(1).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return height(root);
    }

    /**
     * Height helper method.
     * @param node node to inspect
     * @return height of node
     */
    private int height(AVLNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            return node.getHeight();
        }
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Find all elements within a certain distance from the given data.
     * "Distance" means the number of edges between two nodes in the tree.
     *
     * To do this, first find the data in the tree. Keep track of the distance
     * of the current node on the path to the data (you can use the return
     * value of a helper method to denote the current distance to the target
     * data - but note that you must find the data first before you can
     * calculate this information). After you have found the data, you should
     * know the distance of each node on the path to the data. With that
     * information, you can determine how much farther away you can traverse
     * from the main path while remaining within distance of the target data.
     *
     * Use a HashSet as the Set you return. Keep in mind that since it is a
     * Set, you do not have to worry about any specific order in the Set.
     * 
     * This must be implemented recursively.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *              50
     *            /    \
     *         25      75
     *        /  \     / \
     *      13   37  70  80
     *    /  \    \      \
     *   12  15    40    85
     *  /
     * 10
     * elementsWithinDistance(37, 3) should return the set {12, 13, 15, 25,
     * 37, 40, 50, 75}
     * elementsWithinDistance(85, 2) should return the set {75, 80, 85}
     * elementsWithinDistance(13, 1) should return the set {12, 13, 15, 25}
     *
     * @param data     the data to begin calculating distance from
     * @param distance the maximum distance allowed
     * @return the set of all data within a certain distance from the given data
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   is the data is not in the tree
     * @throws java.lang.IllegalArgumentException if distance is negative
     */
    public Set<T> elementsWithinDistance(T data, int distance) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }
        if (!contains(data)) {
            throw new NoSuchElementException("Data not in tree");
        }
        HashSet<T> set = new HashSet<>();
        helper(data, distance, root, set);
        return set;
    }

    /**
     * ElementsWithinDistance helper that adds nodes along a path to the set.
     * @param data starting data to look from
     * @param distance max distance allowed
     * @param curr current node traversed
     * @param set Hash set to add data to
     * @return current distance from data
     */
    private int helper(T data, int distance, AVLNode<T> curr, Set<T> set) {
        int comp = data.compareTo(curr.getData());
        int child = -1;
        if (comp > 0) {
            child = helper(data, distance, curr.getRight(), set);
        } else if (comp < 0) {
            child = helper(data, distance, curr.getLeft(), set);
        }
        int currDist = child + 1;
        if (currDist <= distance) {
            set.add(curr.getData());
            if (currDist < distance) {
                if (curr.getRight() != null) {
                    below(curr.getRight(), distance, currDist + 1, set);
                }
                if (curr.getLeft() != null) {
                    below(curr.getLeft(), distance, currDist + 1, set);
                }
            }
        }
        return currDist;
    }

    /**
     * Adds children of current node if within distance.
     * @param curr current node
     * @param dist max distance allowed
     * @param currDist distance between current and starting node
     * @param set Hash set to add data to
     */
    private void below(AVLNode<T> curr, int dist, int currDist, Set<T> set) {
        if (currDist <= dist) {
            set.add(curr.getData());
            if (currDist < dist) {
                if (curr.getRight() != null) {
                    below(curr.getRight(), dist, currDist + 1, set);
                }
                if (curr.getLeft() != null) {
                    below(curr.getRight(), dist, currDist + 1, set);
                }
            }
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
