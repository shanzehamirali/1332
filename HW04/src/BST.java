import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
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
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null || data.contains(null)) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        size = 0;
        for (T i : data) {
            this.add(i);
        }
    }

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (root == null) {
            root = new BSTNode<T>(data);
            size++;
        } else {
            add(data, root);
        }
    }

    /**
     * Helper method for add().
     * @param data Data to add
     * @param curr Node being accessed
     */
    private void add(T data, BSTNode<T> curr) {
        int comp = data.compareTo(curr.getData());
        BSTNode<T> newNode = new BSTNode<T>(data);

        if (comp == 0) {
            return;
        } else if (comp < 0) {
            if (curr.getLeft() == null) {
                curr.setLeft(newNode);
                size++;
            } else {
                add(data, curr.getLeft());
            }
        } else if (comp > 0) {
            if (curr.getRight() == null) {
                curr.setRight(newNode);
                size++;
            } else {
                add(data, curr.getRight());
            }
        }
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data. You MUST use recursion to find and remove the
     * predecessor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (!contains(data)) {
            throw new NoSuchElementException("Data not found");
        }
        BSTNode<T> dummy = new BSTNode<T>(null);
        root = rRemove(root, data, dummy);
        return dummy.getData();
    }

    /**
     * Helper remove() method.
     * @param curr Current node accessed
     * @param data Data to remove from tree
     * @param dummy Node to store removed data
     * @return Parent node of removed node
     */
    private BSTNode<T> rRemove(BSTNode<T> curr, T data, BSTNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException("Data not found");
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(rRemove(curr.getLeft(), data, dummy));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(rRemove(curr.getRight(), data, dummy));
        } else {
            dummy.setData(curr.getData());
            size--;
            if (curr.getRight() == null && curr.getLeft() == null) {
                return null;
            } else if (curr.getLeft() != null && curr.getRight() != null) {
                BSTNode<T> dummy2 = new BSTNode<T>(null);
                curr.setLeft(removePredecessor(curr.getLeft(), dummy2));
                curr.setData(dummy2.getData());
            } else {
                return (curr.getLeft() != null) ? curr.getLeft() : curr.getRight();
            }
        }
        return curr;
    }

    /**
     * Helper method for remove() that finds predecessor node.
     * @param curr Current node accessed
     * @param dummy Stores data of removed node's child node
     * @return Predecessor node of removed node
     */
    private BSTNode<T> removePredecessor(BSTNode<T> curr, BSTNode<T> dummy) {
        if (curr.getRight() == null) {
            dummy.setData(curr.getData());
            return curr.getLeft();
        } else {
            curr.setRight(removePredecessor(curr.getRight(), dummy));
            return curr;
        }
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively./**
     *
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (root == null) {
            throw new NoSuchElementException("Data not found");
        }
        T found = search(data, root);
        if (found != null) {
            return found;
        } else {
            throw new NoSuchElementException("Data not found");
        }
    }

    /**
     * Traverses BST to search for data in tree
     * @param data Data being searched for
     * @param curr Node being accessed
     * @return Returns data from node if found
     */
    private T search(T data, BSTNode<T> curr) {
        int comp = data.compareTo(curr.getData());
        if (comp > 0) {
            if (curr.getRight() == null) {
                return null;
            } else {
                return search(data, curr.getRight());
            }
        } else if (comp < 0) {
            if (curr.getLeft() == null) {
                return null;
            } else {
                return search(data, curr.getLeft());
            }
        } else {
            return curr.getData();
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
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
        return search(data, root) != null;
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> data = new ArrayList<>();
        preOrder(data, root);
        return data;
    }

    /**
     * Helper method for preorder().
     * @param list List to add data to
     * @param curr Current node accessed
     */
    private void preOrder(List<T> list, BSTNode<T> curr) {
        if (curr == null) {
            return;
        } else {
            list.add(curr.getData());
            preOrder(list, curr.getLeft());
            preOrder(list, curr.getRight());
        }
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> data = new ArrayList<>();
        inOrder(data, root);
        return data;
    }

    /**
     * Helper method for inorder().
     * @param list List to add data to
     * @param curr Current node being accessed
     */
    private void inOrder(List<T> list, BSTNode<T> curr) {
        if (curr == null) {
            return;
        } else {
            inOrder(list, curr.getLeft());
            list.add(curr.getData());
            inOrder(list, curr.getRight());
        }
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> data = new ArrayList<>();
        postOrder(data, root);
        return data;
    }

    /**
     * Helper method for postorder().
     * @param list List to add data to
     * @param curr Current node being accessed
     */
    private void postOrder(List<T> list, BSTNode<T> curr) {
        if (curr == null) {
            return;
        } else {
            postOrder(list, curr.getLeft());
            postOrder(list, curr.getRight());
            list.add(curr.getData());
        }
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        Queue<BSTNode<T>> q = new LinkedList<>();
        List<T> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        q.add(root);
        while (!q.isEmpty()) {
            BSTNode<T> curr = q.poll();
            list.add(curr.getData());
            if (curr.getLeft() != null) {
                q.add(curr.getLeft());
            }
            if (curr.getRight() != null) {
                q.add(curr.getRight());
            }
        }
        return list;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return height(root);
    }

    /**
     * Helper method for height().
     * @param node Node to calculate height of
     * @return height of given node
     */
    private int height(BSTNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            return (1 + Math.max(height(node.getLeft()), height(node.getRight())));
        }
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Generates a list of the max data per level from the top to the bottom
     * of the tree. (Another way to think about this is to get the right most
     * data per level from top to bottom.)
     * 
     * This must be done recursively.
     *
     * This list should contain the last node of each level.
     *
     * If the tree is empty, an empty list should be returned.
     *
     * Ex:
     * Given the following BST composed of Integers
     *      2
     *    /   \
     *   1     4
     *  /     / \
     * 0     3   5
     * getMaxDataPerLevel() should return the list [2, 4, 5] - 2 is the max
     * data of level 0, 4 is the max data of level 1, and 5 is the max data of
     * level 2
     *
     * Ex:
     * Given the following BST composed of Integers
     *               50
     *           /        \
     *         25         75
     *       /    \
     *      12    37
     *     /  \    \
     *   11   15   40
     *  /
     * 10
     * getMaxDataPerLevel() should return the list [50, 75, 37, 40, 10] - 50 is
     * the max data of level 0, 75 is the max data of level 1, 37 is the
     * max data of level 2, etc.
     *
     * Must be O(n).
     *
     * @return the list containing the max data of each level
     */
    public List<T> getMaxDataPerLevel() {
        List<T> list = new ArrayList<T>();
        if (root == null) {
            return list;
        }
        int depth = 1;
        list.add(root.getData());
        gmdpl(root, list, depth);
        return list;
    }

    /**
     * Helper method for getMaxDataPerLevel().
     * @param curr Current node accessed
     * @param list List to add data to
     * @param depth Depth of node accessed
     */
    private void gmdpl(BSTNode<T> curr, List<T> list, int depth) {
        if (list.size() < depth) {
            list.add(curr.getData());
        }
        if (curr.getRight() != null) {
            depth++;
            gmdpl(curr.getRight(), list, depth--);
        }
        if (curr.getLeft() != null) {
            depth++;
            gmdpl(curr.getLeft(), list, depth--);
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
    public BSTNode<T> getRoot() {
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
