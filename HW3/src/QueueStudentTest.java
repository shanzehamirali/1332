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
public class QueueStudentTest {

    private static final int TIMEOUT = 200;
    private ArrayQueue<String> array;
    private LinkedQueue<String> linked;

    @Before
    public void setup() {
        array = new ArrayQueue<>();
        linked = new LinkedQueue<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayQueue.INITIAL_CAPACITY],
            array.getBackingArray());
        assertEquals(0, linked.size());
        assertNull(linked.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEnqueue() {
        array.enqueue("0a");    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
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
    public void testArrayEnqueueFull() {
        array.enqueue("0a");    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        array.enqueue("5a");    // 0a, 1a, 2a, 3a, 4a, 5a
        array.enqueue("6a");    // 0a, 1a, 2a, 3a, 4a, 5a, 6a
        array.enqueue("7a");    // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        array.enqueue("8a");    // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        assertEquals(9, array.size());
        array.enqueue("9a");    // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        assertEquals(10, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY*2];
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
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEnqueueWrapAround() {
        array.enqueue("0a");    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.dequeue(); // 1a
        array.enqueue("2a");    // 1a, 2a
        array.enqueue("3a");    // 1a, 2a, 3a
        array.enqueue("4a");    // 1a, 2a, 3a, 4a
        array.enqueue("5a");    // 1a, 2a, 3a, 4a, 5a
        array.enqueue("6a");    // 1a, 2a, 3a, 4a, 5a, 6a
        array.enqueue("7a");    // 1a, 2a, 3a, 4a, 5a, 6a, 7a
        array.enqueue("8a");    // 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        assertEquals(8, array.size());
        array.enqueue("0a");    // 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 0a
        assertEquals(9, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        assertEquals(1, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEnqueueWrapAroundWithGaps() {
        array.enqueue("0a");    // 0a
        array.enqueue("1a");    // 0a, 1a
        assertEquals("0a", array.dequeue()); // 1a
        array.enqueue("2a");    // 1a, 2a
        array.enqueue("3a");    // 1a, 2a, 3a
        assertEquals("1a", array.dequeue()); // 2a, 3a
        array.enqueue("4a");    // 2a, 3a, 4a
        array.enqueue("5a");    // 2a, 3a, 4a, 5a
        array.enqueue("6a");    // 2a, 3a, 4a, 5a, 6a
        array.enqueue("7a");    // 2a, 3a, 4a, 5a, 6a, 7a
        array.enqueue("8a");    // 2a, 3a, 4a, 5a, 6a, 7a, 8a
        assertEquals(7, array.size());
        array.enqueue("0a");    // 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 0a
        assertEquals(8, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = null;
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        assertEquals(2, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEnqueueWrapAroundWithResize() {
        array.enqueue("0a");    // 0a
        array.enqueue("1a");    // 0a, 1a
        assertEquals("0a", array.dequeue()); // 1a
        array.enqueue("2a");    // 1a, 2a
        array.enqueue("3a");    // 1a, 2a, 3a
        assertEquals("1a", array.dequeue()); // 2a, 3a
        array.enqueue("4a");    // 2a, 3a, 4a
        array.enqueue("5a");    // 2a, 3a, 4a, 5a
        array.enqueue("6a");    // 2a, 3a, 4a, 5a, 6a
        array.enqueue("7a");    // 2a, 3a, 4a, 5a, 6a, 7a
        array.enqueue("8a");    // 2a, 3a, 4a, 5a, 6a, 7a, 8a
        assertEquals(7, array.size());
        array.enqueue("9a");    // 9a, null, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        array.enqueue("10a"); // 9a, 10a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        assertEquals(9, array.size());
        array.enqueue("11a"); // 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a, 10a, 11a, null, null, null
        assertEquals(10, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY * 2];
        expected[0] = "2a";
        expected[1] = "3a";
        expected[2] = "4a";
        expected[3] = "5a";
        expected[4] = "6a";
        expected[5] = "7a";
        expected[6] = "8a";
        expected[7] = "9a";
        expected[8] = "10a";
        expected[9] = "11a";
        expected[10] = null;
        expected[11] = null;
        expected[12] = null;
        expected[13] = null;
        expected[14] = null;
        expected[15] = null;
        expected[16] = null;
        expected[17] = null;
        assertEquals(0, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());
    }



    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testArrayEnqueueNull() {
        array.enqueue(null);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeue() {
        String temp = "0a";

        array.enqueue(temp);    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        array.enqueue("5a");    // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, array.size());

        assertSame(temp, array.dequeue());  // 1a, 2a, 3a, 4a, 5a

        assertEquals(5, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayDequeueEmpty() {
        array.dequeue();
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeueSize1() {
        String temp = "0a";

        array.enqueue(temp);    // 0a
        assertEquals(1, array.size());

        assertSame(temp, array.dequeue());  // 1a, 2a, 3a, 4a, 5a

        assertEquals(0, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = null;
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
    public void testArrayPeek() {
        String temp = "0a";

        array.enqueue(temp);    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, array.size());

        assertSame(temp, array.peek());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayPeekEmpty() {
        array.peek();
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedEnqueue() {
        linked.enqueue("0a");   // 0a
        linked.enqueue("1a");   // 0a, 1a
        linked.enqueue("2a");   // 0a, 1a, 2a
        linked.enqueue("3a");   // 0a, 1a, 2a, 3a
        linked.enqueue("4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, linked.size());

        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLinkedEnqueueNull() {
        linked.enqueue(null);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedEnqueueEmpty() {
        linked.enqueue("0a");   // 0a

        assertEquals(1, linked.size());

        assertNotNull(linked.getHead());
        assertEquals("0a", linked.getHead().getData());

        assertNotNull(linked.getTail());
        assertEquals("0a", linked.getTail().getData());

        assertNull(linked.getTail().getNext());
        assertNull(linked.getHead().getNext());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedDequeue() {
        String temp = "0a";

        linked.enqueue(temp);   // 0a
        linked.enqueue("1a");   // 0a, 1a
        linked.enqueue("2a");   // 0a, 1a, 2a
        linked.enqueue("3a");    // 0a, 1a, 2a, 3a
        linked.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        linked.enqueue("5a");    // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, linked.size());

        assertSame(temp, linked.dequeue()); // 1a, 2a, 3a, 4a, 5a

        assertEquals(5, linked.size());

        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLinkedDequeueEmpty() {
        linked.dequeue();
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedDequeueSize1() {
        String temp = "0a";

        linked.enqueue(temp);   // 0a
        assertEquals(1, linked.size());

        assertSame(temp, linked.dequeue()); //

        assertEquals(0, linked.size());

        assertNull(linked.getHead());
        assertNull(linked.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedDequeueFirstSize2() {
        String temp = "0a";
        linked.enqueue(temp); // 0a
        linked.enqueue("1a"); // 0a, 1a
        assertEquals(2, linked.size());

        assertSame(temp, linked.dequeue());

        assertEquals(1, linked.size());

        assertEquals("1a", linked.getHead().getData());
        assertEquals("1a", linked.getTail().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPeek() {
        String temp = "0a";

        linked.enqueue(temp);   // 0a
        linked.enqueue("1a");   // 0a, 1a
        linked.enqueue("2a");   // 0a, 1a, 2a
        linked.enqueue("3a");    // 0a, 1a, 2a, 3a
        linked.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, linked.size());

        assertSame(temp, linked.peek());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLinkedPeekEmpty() {
        linked.peek();
    }
}
