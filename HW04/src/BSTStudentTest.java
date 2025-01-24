import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * @author Akshat Shenoi
 * @version 1.1
 */
public class BSTStudentTest {

    private static final int TIMEOUT = 200;
    private BST<Integer> tree;

    private BST<String> stringTree;

    @Before
    public void setup() {
        tree = new BST<>();
        stringTree = new BST<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, tree.size());
        assertEquals(0, stringTree.size());
        assertNull(tree.getRoot());
        assertNull(stringTree.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testConstructor() {
        /*
              2
             /
            0
             \
              1
        */

        List<Integer> data = new ArrayList<>();
        data.add(2);
        data.add(0);
        data.add(1);
        tree = new BST<>(data);

        assertEquals(3, tree.size());

        assertEquals((Integer) 2, tree.getRoot().getData());
        assertEquals((Integer) 0, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 1, tree.getRoot().getLeft().getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testConstructorDuplicateData() {
        /*
              2
             /
            0
             \
              1
        */

        List<Integer> data = new ArrayList<>();
        data.add(2);
        data.add(0);
        data.add(1);
        data.add(1);
        tree = new BST<>(data);

        assertEquals(3, tree.size());

        assertEquals((Integer) 2, tree.getRoot().getData());
        assertEquals((Integer) 0, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 1, tree.getRoot().getLeft().getRight().getData());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testConstructorNullCollection() {
        List<Integer> data = null;
        tree = new BST<>(data);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testConstructorNullData() {
        List<Integer> data = new ArrayList<>();
        data.add(2);
        data.add(0);
        data.add(1);
        data.add(null);
        tree = new BST<>(data);
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        /*
              1
             / \
            0   2
        */

        tree.add(1);
        tree.add(0);
        tree.add(2);

        assertEquals(3, tree.size());

        assertEquals((Integer) 1, tree.getRoot().getData());
        assertEquals((Integer) 0, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 2, tree.getRoot().getRight().getData());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNull() {
        tree.add(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddDuplicate() {
        /*
              1
             / \
            0   2
        */

        tree.add(1);
        tree.add(0);
        tree.add(2);
        tree.add(2);

        assertEquals(3, tree.size());

        assertEquals((Integer) 1, tree.getRoot().getData());
        assertEquals((Integer) 0, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 2, tree.getRoot().getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveSingleChild() {
        Integer temp = 2;

        /*
              1
             / \
            0   2
                 \
                  3
                   \
                    4

            ->

              1
             / \
            0   3
                 \
                  4
        */

        tree.add(1);
        tree.add(0);
        tree.add(temp);
        tree.add(3);
        tree.add(4);
        assertEquals(5, tree.size());

        assertSame(temp, tree.remove(2));

        assertEquals(4, tree.size());

        assertEquals((Integer) 1, tree.getRoot().getData());
        assertEquals((Integer) 0, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 3, tree.getRoot().getRight().getData());
        assertEquals((Integer) 4, tree.getRoot().getRight()
                     .getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRoot()  {
        Integer temp = 1;
        tree = new BST<>();

        /*
              1
             / \
            0   2
                 \
                  3
                   \
                    4

            ->

              0
               \
                2
                 \
                  3
                   \
                    4
        */

        tree.add(temp);
        tree.add(0);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        assertEquals(5, tree.size());

        assertSame(temp, tree.remove(1));

        assertEquals(4, tree.size());

        assertEquals((Integer) 0, tree.getRoot().getData());
        assertEquals((Integer) 2, tree.getRoot().getRight().getData());
        assertEquals((Integer) 3, tree.getRoot().getRight()
                .getRight().getData());
        assertEquals((Integer) 4, tree.getRoot().getRight().getRight()
                .getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRoot2()  {
        Integer temp = 3;
        tree = new BST<>();

        /*
              3
             / \
            1   4
           / \   \
          0   2   5


            ->

              2
             / \
            1   4
           /     \
          0       5

        */

        tree.add(temp);
        tree.add(1);
        tree.add(0);
        tree.add(2);
        tree.add(4);
        tree.add(5);
        assertEquals(6, tree.size());

        assertSame(temp, tree.remove(3));

        assertEquals(5, tree.size());

        assertEquals((Integer) 2, tree.getRoot().getData());
        assertEquals((Integer) 1, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 0, tree.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 4, tree.getRoot().getRight().getData());
        assertEquals((Integer) 5, tree.getRoot().getRight()
                .getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRoot3()  { //Checks that you're returning node.getLeft and not null in removePredecessor
        tree = new BST<>();

        /*
              7
             / \
            4   8
             \   \
              6   9
             /
            5
            ->

              6
             / \
            4   8
             \   \
              5   9



        */

        tree.add(7);
        tree.add(4);
        tree.add(8);
        tree.add(6);
        tree.add(9);
        tree.add(5);
        assertEquals(6, tree.size());

        assertSame(7, tree.remove(7));

        assertEquals(5, tree.size());

        assertEquals((Integer) 6, tree.getRoot().getData());
        assertEquals((Integer) 4, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 5, tree.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 8, tree.getRoot().getRight().getData());
        assertEquals((Integer) 9, tree.getRoot().getRight()
                .getRight().getData());
    }


    @Test(timeout = TIMEOUT)
    public void testRemoveTwoChildren() {
        Integer temp = 3;

        /*
              1
             / \
            0   3
               / \
              2   4

            ->

              1
             / \
            0   2
                 \
                  4
        */

        tree.add(1);
        tree.add(0);
        tree.add(temp);
        tree.add(2);
        tree.add(4);
        assertEquals(5, tree.size());

        assertSame(temp, tree.remove(3));

        assertEquals(4, tree.size());

        assertEquals((Integer) 1, tree.getRoot().getData());
        assertEquals((Integer) 0, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 2, tree.getRoot().getRight().getData());
        assertEquals((Integer) 4, tree.getRoot().getRight().getRight().getData());
    }


    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveNotFound() {
        tree.add(1);
        tree.add(0);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        assertEquals(5, tree.size());

        tree.remove(10);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        tree.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveWhenEmpty() {
        tree.remove(2);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveValueEquality() {
        String temp = "a";
        stringTree.add(temp);
        stringTree.add("b");
        stringTree.add("c");

        assertEquals("a", stringTree.getRoot().getData());
        assertEquals("b", stringTree.getRoot().getRight().getData());
        assertEquals("c", stringTree.getRoot().getRight().getRight().getData());

        assertEquals(temp, stringTree.remove(new String("a")));

        assertEquals("b", stringTree.getRoot().getData());
        assertEquals("c", stringTree.getRoot().getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        Integer temp200 = 200;
        Integer temp185 = 185;
        Integer temp190 = 190;
        Integer temp195 = 195;
        Integer temp215 = 215;
        Integer temp205 = 205;
        Integer temp210 = 210;

        /*
                  200
              /        \
            185         215
             \         /
              190     205
               \       \
                195     210
        */

        tree.add(temp200);
        tree.add(temp185);
        tree.add(temp190);
        tree.add(temp195);
        tree.add(temp215);
        tree.add(temp205);
        tree.add(temp210);
        assertEquals(7, tree.size());

        // We want to make sure the data we retrieve is the one from the tree,
        // not the data that was passed in. The Integers need to be outside of
        // the range [-128, 127] so that they are not cached.
        assertSame(temp185, tree.get(185));
        assertSame(temp190, tree.get(190));
        assertSame(temp195, tree.get(195));
        assertSame(temp200, tree.get(200));
        assertSame(temp205, tree.get(205));
        assertSame(temp210, tree.get(210));
        assertSame(temp215, tree.get(215));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetNull() {
        tree.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetNotInBST() {
        Integer temp200 = 200;
        Integer temp185 = 185;
        Integer temp190 = 190;
        Integer temp195 = 195;
        Integer temp215 = 215;
        Integer temp205 = 205;
        Integer temp210 = 210;

        /*
                  200
              /        \
            185         215
             \         /
              190     205
               \       \
                195     210
        */

        tree.add(temp200);
        tree.add(temp185);
        tree.add(temp190);
        tree.add(temp195);
        tree.add(temp215);
        tree.add(temp205);
        tree.add(temp210);
        assertEquals(7, tree.size());

        // We want to make sure the data we retrieve is the one from the tree,
        // not the data that was passed in. The Integers need to be outside of
        // the range [-128, 127] so that they are not cached.
        assertSame(temp185, tree.get(185));
        assertSame(temp190, tree.get(190));
        assertSame(temp195, tree.get(195));
        assertSame(temp200, tree.get(200));
        assertSame(temp205, tree.get(205));
        assertSame(temp210, tree.get(210));
        assertSame(temp215, tree.get(215));
        tree.get(100);
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        /*
                3
             /     \
            0       6
             \     /
              1   4
               \   \
                2   5
        */

        tree.add(3);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(6);
        tree.add(4);
        tree.add(5);
        assertEquals(7, tree.size());

        assertTrue(tree.contains(0));
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(4));
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(6));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsNull() {
        assertTrue(tree.contains(null));
    }

    @Test(timeout = TIMEOUT)
    public void testPreorder() {
        /*
                3
             /     \
            0       8
             \     /
              1   4
               \   \
                2   6
                   / \
                  5   7
        */

        tree.add(3);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(8);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        tree.add(7);
        assertEquals(9, tree.size());

        List<Integer> preorder = new ArrayList<>();
        preorder.add(3);
        preorder.add(0);
        preorder.add(1);
        preorder.add(2);
        preorder.add(8);
        preorder.add(4);
        preorder.add(6);
        preorder.add(5);
        preorder.add(7);

        // Should be [3, 0, 1, 2, 8, 4, 6, 5, 7]
        assertEquals(preorder, tree.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testInorder() {
        /*
                3
             /     \
            0       8
             \     /
              1   4
               \   \
                2   6
                   / \
                  5   7
        */

        tree.add(3);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(8);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        tree.add(7);
        assertEquals(9, tree.size());

        List<Integer> inorder = new ArrayList<>();
        inorder.add(0);
        inorder.add(1);
        inorder.add(2);
        inorder.add(3);
        inorder.add(4);
        inorder.add(5);
        inorder.add(6);
        inorder.add(7);
        inorder.add(8);

        // Should be [0, 1, 2, 3, 4, 5, 6, 7, 8]
        assertEquals(inorder, tree.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorder() {
        /*
                3
             /     \
            0       8
             \     /
              1   4
               \   \
                2   6
                   / \
                  5   7
        */

        tree.add(3);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(8);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        tree.add(7);
        assertEquals(9, tree.size());

        List<Integer> postorder = new ArrayList<>();
        postorder.add(2);
        postorder.add(1);
        postorder.add(0);
        postorder.add(5);
        postorder.add(7);
        postorder.add(6);
        postorder.add(4);
        postorder.add(8);
        postorder.add(3);

        // Should be [2, 1, 0, 5, 7, 6, 4, 8, 3]
        assertEquals(postorder, tree.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorder() {
        /*
                3
             /     \
            0       8
             \     /
              1   4
               \   \
                2   6
                   / \
                  5   7
        */

        tree.add(3);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(8);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        tree.add(7);
        assertEquals(9, tree.size());

        List<Integer> levelorder = new ArrayList<>();
        levelorder.add(3);
        levelorder.add(0);
        levelorder.add(8);
        levelorder.add(1);
        levelorder.add(4);
        levelorder.add(2);
        levelorder.add(6);
        levelorder.add(5);
        levelorder.add(7);

        // Should be [3, 0, 8, 1, 4, 2, 6, 5, 7]
        assertEquals(levelorder, tree.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorderEmpty() {
        List<Integer> levelorder = new ArrayList<>();
        // Should be []
        assertEquals(levelorder, tree.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        /*
              2
             /
            0
             \
              1
        */

        tree.add(2);
        tree.add(0);
        tree.add(1);
        assertEquals(3, tree.size());

        assertEquals(2, tree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testHeightEmpty() {
        assertEquals(-1, tree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        /*
              2
             /
            0
             \
              1
        */

        tree.add(2);
        tree.add(0);
        tree.add(1);
        assertEquals(3, tree.size());

        tree.clear();
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testGetMaxDataPerLevel() {
        /*
              3
             / \
            1   4
             \
              2
        */

        tree.add(3);
        tree.add(4);
        tree.add(1);
        tree.add(2);

        List<Integer> expected = new ArrayList<>();
        expected.add(3);
        expected.add(4);
        expected.add(2);
        assertEquals(expected, tree.getMaxDataPerLevel());
    }
}