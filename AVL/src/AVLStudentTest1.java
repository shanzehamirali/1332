import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * @author Akshat Shenoi
 * @version 1.0
 */
public class AVLStudentTest1 {

    private static final int TIMEOUT = 200;
    private AVL<Integer> tree;

    @Before
    public void setup() {
        tree = new AVL<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testConstructor() {
        /*
              1
             / \
            0   2
        */

        List<Integer> toAdd = new ArrayList<>();
        toAdd.add(1);
        toAdd.add(0);
        toAdd.add(2);
        tree = new AVL<>(toAdd);

        assertEquals(3, tree.size());

        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 1, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 0, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 2, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testConstructorNull() {
        List<Integer> toAdd = null;
        tree = new AVL<>(toAdd);
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        // Right rotate
        /*
                2
               /
              1
             /
            0
            ->
              1
             / \
            0   2
         */

        tree.add(2);
        tree.add(1);
        tree.add(0);

        assertEquals(3, tree.size());

        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 1, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 0, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 2, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());


        // Right left rotate
        /*
            0
             \
              2
             /
            1
            ->
              1
             / \
            0   2
         */

        tree = new AVL<>();
        tree.add(0);
        tree.add(2);
        tree.add(1);

        assertEquals(3, tree.size());
        root = tree.getRoot();
        assertEquals((Integer) 1, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        left = root.getLeft();
        assertEquals((Integer) 0, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        right = root.getRight();
        assertEquals((Integer) 2, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testAdd2() {
        // Left rotate
        /*
                2
                 \
                  3
                   \
                    4
            ->
              3
             / \
            2   4
         */

        tree.add(2);
        tree.add(3);
        tree.add(4);

        assertEquals(3, tree.size());

        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 3, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 2, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 4, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());


        // Left Right rotate
        /*
            3
           /
          1
           \
            2
            ->
              2
             / \
            1   3
         */

        tree = new AVL<>();
        tree.add(3);
        tree.add(1);
        tree.add(2);

        assertEquals(3, tree.size());
        root = tree.getRoot();
        assertEquals((Integer) 2, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        left = root.getLeft();
        assertEquals((Integer) 1, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        right = root.getRight();
        assertEquals((Integer) 3, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNull() {
        tree.add(null);
    }


    @Test(timeout = TIMEOUT)
    public void testRemove() {
        Integer temp = 1;

        /*
                  3
                /   \
              1      4
             / \
            0   2
            ->
                  3
                /   \
              2      4
             /
            0
         */


        tree.add(3);
        tree.add(temp);
        tree.add(4);
        tree.add(0);
        tree.add(2);
        assertEquals(5, tree.size());

        assertSame(temp, tree.remove(1));
        assertEquals(4, tree.size());
        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 3, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 2, left.getData());
        assertEquals(1, left.getHeight());
        assertEquals(1, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 4, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
        AVLNode<Integer> leftLeft = left.getLeft();
        assertEquals((Integer) 0, leftLeft.getData());
        assertEquals(0, leftLeft.getHeight());
        assertEquals(0, leftLeft.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRightRotation() {
        Integer temp = 3;
        /*
                  5
                /   \
              3      7
             / \    /  \
            1   4  6    8
           /
          0
            ->
                  5
                /   \
              1      7
             / \     /  \
            0   4   6    8

         */


        tree.add(5);
        tree.add(temp);
        tree.add(7);
        tree.add(1);
        tree.add(4);
        tree.add(6);
        tree.add(8);
        tree.add(0);
        assertEquals(8, tree.size());

        assertSame(temp, tree.remove(3));
        assertEquals(7, tree.size());
        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 5, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 1, left.getData());
        assertEquals(1, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 7, right.getData());
        assertEquals(1, right.getHeight());
        assertEquals(0, right.getBalanceFactor());

        AVLNode<Integer> leftLeft = left.getLeft();
        assertEquals((Integer) 0, leftLeft.getData());
        assertEquals(0, leftLeft.getHeight());
        assertEquals(0, leftLeft.getBalanceFactor());
        AVLNode<Integer> leftRight = left.getRight();
        assertEquals((Integer) 4, leftRight.getData());
        assertEquals(0, leftRight.getHeight());
        assertEquals(0, leftRight.getBalanceFactor());
        AVLNode<Integer> rightLeft = right.getLeft();
        assertEquals((Integer) 6, rightLeft.getData());
        assertEquals(0, rightLeft.getHeight());
        assertEquals(0, rightLeft.getBalanceFactor());
        AVLNode<Integer> rightRight = right.getRight();
        assertEquals((Integer) 8, rightRight.getData());
        assertEquals(0, rightRight.getHeight());
        assertEquals(0, rightRight.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLeftRotation() {
        Integer temp = 1;
        /*
                  6
                /   \
              3      8
             / \    /  \
            1   4  7    9
                 \
                  5
            ->
                  6
                /   \
              4      8
             / \    /  \
            3   5  7    9

         */


        tree.add(6);
        tree.add(3);
        tree.add(8);
        tree.add(temp);
        tree.add(4);
        tree.add(7);
        tree.add(9);
        tree.add(5);
        assertEquals(8, tree.size());

        assertSame(temp, tree.remove(1));
        assertEquals(7, tree.size());
        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 6, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 4, left.getData());
        assertEquals(1, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 8, right.getData());
        assertEquals(1, right.getHeight());
        assertEquals(0, right.getBalanceFactor());

        AVLNode<Integer> leftLeft = left.getLeft();
        assertEquals((Integer) 3, leftLeft.getData());
        assertEquals(0, leftLeft.getHeight());
        assertEquals(0, leftLeft.getBalanceFactor());
        AVLNode<Integer> leftRight = left.getRight();
        assertEquals((Integer) 5, leftRight.getData());
        assertEquals(0, leftRight.getHeight());
        assertEquals(0, leftRight.getBalanceFactor());
        AVLNode<Integer> rightLeft = right.getLeft();
        assertEquals((Integer) 7, rightLeft.getData());
        assertEquals(0, rightLeft.getHeight());
        assertEquals(0, rightLeft.getBalanceFactor());
        AVLNode<Integer> rightRight = right.getRight();
        assertEquals((Integer) 9, rightRight.getData());
        assertEquals(0, rightRight.getHeight());
        assertEquals(0, rightRight.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRightLeftRotation() {
        Integer temp = 1;
        /*
                  6
                /   \
              3      8
             / \    /  \
            1   5  7    9
               /
              4
            ->
                  6
                /   \
              4      8
             / \    /  \
            3   5  7    9

         */


        tree.add(6);
        tree.add(3);
        tree.add(8);
        tree.add(temp);
        tree.add(5);
        tree.add(7);
        tree.add(9);
        tree.add(4);
        assertEquals(8, tree.size());

        assertSame(temp, tree.remove(1));
        assertEquals(7, tree.size());
        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 6, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 4, left.getData());
        assertEquals(1, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 8, right.getData());
        assertEquals(1, right.getHeight());
        assertEquals(0, right.getBalanceFactor());

        AVLNode<Integer> leftLeft = left.getLeft();
        assertEquals((Integer) 3, leftLeft.getData());
        assertEquals(0, leftLeft.getHeight());
        assertEquals(0, leftLeft.getBalanceFactor());
        AVLNode<Integer> leftRight = left.getRight();
        assertEquals((Integer) 5, leftRight.getData());
        assertEquals(0, leftRight.getHeight());
        assertEquals(0, leftRight.getBalanceFactor());
        AVLNode<Integer> rightLeft = right.getLeft();
        assertEquals((Integer) 7, rightLeft.getData());
        assertEquals(0, rightLeft.getHeight());
        assertEquals(0, rightLeft.getBalanceFactor());
        AVLNode<Integer> rightRight = right.getRight();
        assertEquals((Integer) 9, rightRight.getData());
        assertEquals(0, rightRight.getHeight());
        assertEquals(0, rightRight.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLeftRightRotation() {
        Integer temp = 4;
        /*
                  5
                /   \
              3      7
             / \    /  \
            1   4  6    8
             \
              2
            ->
                  5
                /   \
              2      7
             / \    /  \
            1   3  6    8

         */


        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(1);
        tree.add(temp);
        tree.add(6);
        tree.add(8);
        tree.add(2);
        assertEquals(8, tree.size());

        assertSame(temp, tree.remove(4));
        assertEquals(7, tree.size());
        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 5, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 2, left.getData());
        assertEquals(1, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 7, right.getData());
        assertEquals(1, right.getHeight());
        assertEquals(0, right.getBalanceFactor());

        AVLNode<Integer> leftLeft = left.getLeft();
        assertEquals((Integer) 1, leftLeft.getData());
        assertEquals(0, leftLeft.getHeight());
        assertEquals(0, leftLeft.getBalanceFactor());
        AVLNode<Integer> leftRight = left.getRight();
        assertEquals((Integer) 3, leftRight.getData());
        assertEquals(0, leftRight.getHeight());
        assertEquals(0, leftRight.getBalanceFactor());
        AVLNode<Integer> rightLeft = right.getLeft();
        assertEquals((Integer) 6, rightLeft.getData());
        assertEquals(0, rightLeft.getHeight());
        assertEquals(0, rightLeft.getBalanceFactor());
        AVLNode<Integer> rightRight = right.getRight();
        assertEquals((Integer) 8, rightRight.getData());
        assertEquals(0, rightRight.getHeight());
        assertEquals(0, rightRight.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        tree.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveEmpty() {
        tree.remove(1);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveNotInAVL() {
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(1);
        tree.add(4);
        tree.add(6);
        tree.add(8);
        assertEquals(7, tree.size());
        tree.remove(9);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        Integer temp1 = 1;
        Integer temp0 = 0;
        Integer temp2 = 2;
        Integer temp3 = 3;

        /*
               1
             /   \
            0     2
                    \
                     3
         */

        tree.add(temp1);
        tree.add(temp0);
        tree.add(temp2);
        tree.add(temp3);
        assertEquals(4, tree.size());

        // We want to make sure the data we retrieve is the one from the tree,
        // not the data that was passed in.
        assertSame(temp0, tree.get(0));
        assertSame(temp1, tree.get(1));
        assertSame(temp2, tree.get(2));
        assertSame(temp3, tree.get(3));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetNull() {
        tree.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetNotInTree() {
        tree.get(3);
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        /*
               1
             /   \
            0     2
                    \
                     3
         */

        tree.add(1);
        tree.add(0);
        tree.add(2);
        tree.add(3);
        assertEquals(4, tree.size());

        assertTrue(tree.contains(0));
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(3));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsNull() {
        tree.contains(null);
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        /*
                     3
                   /   \
                 1      4
                / \
               0   2
         */

        tree.add(3);
        tree.add(1);
        tree.add(4);
        tree.add(0);
        tree.add(2);

        assertEquals(2, tree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        /*
              1
             / \
            0   2
        */

        List<Integer> toAdd = new ArrayList<>();
        toAdd.add(1);
        toAdd.add(0);
        toAdd.add(2);
        tree = new AVL<>(toAdd);

        tree.clear();
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testElementsWithinDistance() {
        /*
                    76
                 /      \
               34        90
              /  \      /
            20    40  81
         */

        tree.add(76);
        tree.add(34);
        tree.add(90);
        tree.add(20);
        tree.add(40);
        tree.add(81);

        Set<Integer> result = new HashSet<>();
        result.add(90);

        // Should be {90}
        assertEquals(result, tree.elementsWithinDistance(90, 0));

        result.clear();
        result.add(20);
        result.add(34);
        result.add(40);
        result.add(76);

        // Should be {20, 34, 40, 76}
        assertEquals(result, tree.elementsWithinDistance(34, 1));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testElementsWithinDistanceNull() {
        tree.elementsWithinDistance(null, 1);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testElementsWithinDistanceNegative() {
        tree.elementsWithinDistance(1, -1);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testElementsWithinDistanceNotInAVL() {
        tree.elementsWithinDistance(1, 1);
    }

}
