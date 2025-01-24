import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * @author Akshat Shenoi
 * @version 1.0
 */
public class StackStudentTest {

    private static final int TIMEOUT = 200;
    private ArrayStack<String> array;
    private LinkedStack<String> linked;

    @Before
    public void setup() {
        array = new ArrayStack<>();
        linked = new LinkedStack<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayStack.INITIAL_CAPACITY],
            array.getBackingArray());
        assertEquals(0, linked.size());
        assertNull(linked.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPush() {
        array.push("0a");   // 0a
        array.push("1a");   // 0a, 1a
        array.push("2a");   // 0a, 1a, 2a
        array.push("3a");   // 0a, 1a, 2a, 3a
        array.push("4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, array.size());

        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = null;
        expected[6] = null;
        expected[7] = null;
        expected[8] = null;
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPushEmpty() {
        array.push("0a");   // 0a

        assertEquals(1, array.size());

        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = null;
        expected[2] = null;
        expected[3] = null;
        expected[4] = null;
        expected[5] = null;
        expected[6] = null;
        expected[7] = null;
        expected[8] = null;
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPushFull() {
        array.push("0a");   // 0a
        array.push("1a");   // 0a, 1a
        array.push("2a");   // 0a, 1a, 2a
        array.push("3a");   // 0a, 1a, 2a, 3a
        array.push("4a");   // 0a, 1a, 2a, 3a, 4a
        array.push("5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        array.push("6a");   // 0a, 1a, 2a, 3a, 4a, 5a, 6a
        array.push("7a");   // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        array.push("8a");   // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        assertEquals(9, array.size());
        array.push("9a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a
        assertEquals(10, array.size());
        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY*2];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        expected[9] = "9a";
        expected[10] = null;
        expected[11] = null;
        expected[12] = null;
        expected[13] = null;
        expected[14] = null;
        expected[15] = null;
        expected[16] = null;
        expected[17] = null;
        assertArrayEquals(expected, array.getBackingArray());
    }
    @Test(timeout = TIMEOUT)
    public void testArrayPop() {
        String temp = "5a";

        array.push("0a");   // 0a
        array.push("1a");   // 0a, 1a
        array.push("2a");   // 0a, 1a, 2a
        array.push("3a");   // 0a, 1a, 2a, 3a
        array.push("4a");   // 0a, 1a, 2a, 3a, 4a
        array.push(temp);   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, array.size());

        assertSame(temp, array.pop());  // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, array.size());

        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayPopEmpty() {
        array.pop();
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPopSize1() {
        array.push("0a"); // 0a
        assertEquals(1, array.size());
        assertEquals("0a", array.pop());
        assertEquals(0, array.size());
        assertArrayEquals(array.getBackingArray(), new Object[ArrayStack.INITIAL_CAPACITY]);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPeek() {
        String temp = "4a";

        array.push("0a");   // 0a
        array.push("1a");   // 0a, 1a
        array.push("2a");   // 0a, 1a, 2a
        array.push("3a");   // 0a, 1a, 2a, 3a
        array.push(temp);   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, array.size());

        assertSame(temp, array.peek());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayPeekEmpty() {
        array.peek();
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPush() {
        linked.push("0a");  // 0a
        linked.push("1a");  // 1a, 0a
        linked.push("2a");  // 2a, 1a 0a
        linked.push("3a");  // 3a, 2a, 1a 0a
        linked.push("4a");  // 4a, 3a, 2a, 1a 0a

        assertEquals(5, linked.size());

        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPushEmpty() {
        linked.push("0a");  // 0a

        assertEquals(1, linked.size());

        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLinkedPushNull() {
        linked.push(null);  // 0a
        assertNull(linked.getHead());
        assertEquals(0, linked.size());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPop() {
        String temp = "5a";

        linked.push("0a");  // 0a
        linked.push("1a");  // 1a, 0a
        linked.push("2a");  // 2a, 1a, 0a
        linked.push("3a");  // 3a, 2a, 1a, 0a
        linked.push("4a");  // 4a, 3a, 2a, 1a, 0a
        linked.push(temp);  // 5a, 4a, 3a, 2a, 1a, 0a
        assertEquals(6, linked.size());

        assertSame(temp, linked.pop()); // 4a, 3a, 2a, 1a, 0a

        assertEquals(5, linked.size());

        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLinkedPopEmpty() {
        linked.pop();
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPopSize1() {
        String temp = "0a";
        linked.push(temp); // 0a
        assertEquals(temp, linked.getHead().getData());
        assertEquals(1, linked.size());
        assertNull(linked.getHead().getNext());
        assertEquals(temp, linked.pop()); // empty
        assertNull(linked.getHead());
        assertEquals(0, linked.size());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPeek() {
        String temp = "4a";

        linked.push("0a");  // 0a
        linked.push("1a");  // 1a, 0a
        linked.push("2a");  // 2a, 1a, 0a
        linked.push("3a");  // 3a, 2a, 1a, 0a
        linked.push(temp);  // 4a, 3a, 2a, 1a, 0a
        assertEquals(5, linked.size());

        assertSame(temp, linked.peek());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLinkedPeekEmpty() {
        linked.peek();
    }
}
