/*

	     ▄████████    ▄████████          ███        ▄████████    ▄████████     ███        ▄████████    ▄████████
	    ███    ███   ███    ███      ▀█████████▄   ███    ███   ███    ███ ▀█████████▄   ███    ███   ███    ███
	    ███    █▀    ███    █▀          ▀███▀▀██   ███    █▀    ███    █▀     ▀███▀▀██   ███    █▀    ███    ███
	    ███          ███                 ███   ▀  ▄███▄▄▄       ███            ███   ▀  ▄███▄▄▄      ▄███▄▄▄▄██▀
	    ███        ▀███████████          ███     ▀▀███▀▀▀     ▀███████████     ███     ▀▀███▀▀▀     ▀▀███▀▀▀▀▀
	    ███    █▄           ███          ███       ███    █▄           ███     ███       ███    █▄  ▀███████████
	    ███    ███    ▄█    ███          ███       ███    ███    ▄█    ███     ███       ███    ███   ███    ███
	    ████████▀   ▄████████▀          ▄████▀     ██████████  ▄████████▀     ▄████▀     ██████████   ███    ███
	                                                                                                  ███    ███
		HOMEWORK 3 JUNIT TESTS

	    This is NOT just a basic set of unit tests.

	    It hopefully aims to be as comprehensible as possible when finding all of the frustrating edge cases.

	    Of course there might be test cases missing from this suite, but it is overall designed to be "tests and
	    much more."

	    Along with the massive amount of tests located within this file, we do a lot more to avoid unnecessary
	    point deduction:

		- ASCII art for drawing array structures when failing tests, allowing a more visual debugging experience.
	    - Automatic print statement debugging: print statements in your final code *will* be docked points. For
	      ALL tests in this file, if there is any print statement detected it will automatically fail and tell
	      you accordingly.
	    - Reflection for checking private or otherwise inaccessible methods or fields, as well as being able
	      to pul this data without relying on something like toArray.
	    - Exception error message checking
	    - Reflection-based isolation system: there are dozens of tests relying on a few methods to be correct as a
	      dependency--addToIndex being a primary one. To avoid these tests being unnecessarily failed, the tests
	      will instead forcibly inject their data to avoid this issue, hopefully preventing most chain effects.

	    If you have any questions or concerns, reach out on Piazza!

	    Cheers,
	    - Ryder Johnson
	    - Justin Hwang

*/
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.lang.reflect.Constructor;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import java.lang.reflect.InvocationTargetException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.Arrays;
import org.junit.Assert;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RunWith(Enclosed.class)
public class Homework3Tests {

	public static class ArrayQueueTests {
	
	    ArrayQueue<String> empty;
	    ArrayQueueWrapper<String> emptyWrapper;
	    String[] emptyArray;
	    ArrayQueue<String> full;
	    ArrayQueueWrapper<String> fullWrapper;
	    String[] fullArray;
	
	    @Before
	    public void setup() {
	        empty = new ArrayQueue<>();
	        emptyWrapper = new ArrayQueueWrapper<>(empty);
	        emptyArray = new String[] {null, null, null, null, null, null, null, null, null};
	
	        full = new ArrayQueue<>();
	        fullWrapper = new ArrayQueueWrapper<>(full);
	        fullWrapper.forceSetSize(9);
	        fullWrapper.forceSetArray(new String[] {"Homestead", "Aragon", "Tino", "Spartan", "Citrus", "Poofs", "Fire", "Quix", "Monkeys"});
	        fullArray = new String[] {"Homestead", "Aragon", "Tino", "Spartan", "Citrus", "Poofs", "Fire", "Quix", "Monkeys"};
	    }
	
	    @Test
	    public void constructorTest() {
	        AssertUtils.assertArrayEquals(new String[] {null,null,null,null,null,null,null,null,null}, empty.getBackingArray());
	        assertEquals(0, empty.size());
	        assertEquals(0, empty.getFront());
	    }
	
	    @Test
	    public void enqueueFrontZeroSizeZeroInvalidData() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> empty.enqueue(null), null);
	        assertEquals(0, empty.size());
	        assertEquals(0, empty.getFront());
	    }
	
	    @Test
	    public void enqueueFrontZeroSizeZeroValidData() {
	        empty.enqueue("Sun Tzu");
	        emptyArray[0] = "Sun Tzu";
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(1, empty.size());
	        assertEquals(0, empty.getFront());
	    }
	
	    @Test
	    public void enqueueFrontZeroSizeFour() {
	        ArrayQueue<String> queue = new ArrayQueue<>();
	        ArrayQueueWrapper<String> wrapper = new ArrayQueueWrapper<>(queue);
	        wrapper.forceSetArray(new String[] {"Skipper", "Sun Tzu", "Tremor", "Breakdown", null, null, null, null, null});
	        wrapper.forceSetFront(0);
	        wrapper.forceSetSize(4);
	        queue.enqueue("Tumbler");
	        AssertUtils.assertArrayEquals(new String[] {"Skipper", "Sun Tzu", "Tremor", "Breakdown", "Tumbler", null, null, null, null}, queue.getBackingArray());
	        assertEquals(5, queue.size());
	        assertEquals(0, queue.getFront());
	    }
	
	    @Test
	    public void enqueueFrontZeroQueueFull() {
	        full.enqueue("Tumbler");
	        AssertUtils.assertArrayEquals(new String[] {"Homestead", "Aragon", "Tino", "Spartan", "Citrus", "Poofs", "Fire", "Quix", "Monkeys",
	                "Tumbler", null, null, null, null, null, null, null, null}, full.getBackingArray());
	        assertEquals(10, full.size());
	        assertEquals(0, full.getFront());
	    }
	
	    @Test
	    public void enqueueFrontInMiddleSizeZeroInvalidData() {
	        emptyWrapper.forceSetFront(4);
	
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> empty.enqueue(null), null);
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(0, empty.size());
	        assertEquals(4, empty.getFront());
	    }
	
	    @Test
	    public void enqueueFrontInMiddleSizeZeroValidData() {
	        emptyWrapper.forceSetFront(4);
	
	        empty.enqueue("MVRT");
	        emptyArray[4] = "MVRT";
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(1, empty.size());
	        assertEquals(4, empty.getFront());
	    }
	
	    @Test
	    public void enqueueFrontInMiddleWrapNeeded() {
	        emptyWrapper.forceSetFront(4);
	        String[] array = new String[] {null, null, null, null, "MVRT", "Eaglestrike", "GRT", "Boba", "Biomechs"};
	        emptyWrapper.forceSetArray(array);
	        emptyWrapper.forceSetSize(5);
	        array[0] = "Jump!";
	
	        empty.enqueue("Jump!");
	
	        AssertUtils.assertArrayEquals(array, empty.getBackingArray());
	        assertEquals(6, empty.size());
	        assertEquals(4, empty.getFront());
	    }
	
	    @Test
	    public void enqueueFrontInMiddleWrapNeededTwo() {
	        emptyWrapper.forceSetFront(5);
	        String[] array = new String[] {"Biomechs", null, null, null, null, "MVRT", "Eaglestrike", "GRT", "Boba"};
	        emptyWrapper.forceSetArray(array);
	        emptyWrapper.forceSetSize(5);
	        array[1] = "Jump!";
	
	        empty.enqueue("Jump!");
	
	        AssertUtils.assertArrayEquals(array, empty.getBackingArray());
	        assertEquals(6, empty.size());
	        assertEquals(5, empty.getFront());
	    }
	
	    @Test
	    public void enqueueFrontInMiddleResizeNeeded() {
	        emptyWrapper.forceSetFront(5);
	        String[] array = new String[] {"Biomechs", "Jump!", "Westlake", "Panthers", "Septo", "MVRT", "Eaglestrike", "GRT", "Boba"};
	        emptyWrapper.forceSetArray(array);
	        emptyWrapper.forceSetSize(9);
	        String[] unwrappedArray = new String[] {"MVRT", "Eaglestrike", "GRT", "Boba", "Biomechs", "Jump!", "Westlake", "Panthers", "Septo",
	        "Duncan", null, null, null, null, null, null, null, null};
	
	        empty.enqueue("Duncan");
	
	        AssertUtils.assertArrayEquals(unwrappedArray, empty.getBackingArray());
	        assertEquals(10, empty.size());
	        assertEquals(0, empty.getFront());
	    }
	
	    @Test
	    public void enqueueFrontAtEndSizeZeroInvalidData() {
	        emptyWrapper.forceSetFront(8);
	
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> empty.enqueue(null), null);
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(0, empty.size());
	        assertEquals(8, empty.getFront());
	    }
	
	    @Test
	    public void enqueueFrontAtEndSizeZeroValidData() {
	        emptyWrapper.forceSetFront(8);
	
	        empty.enqueue("MVRT");
	        emptyArray[8] = "MVRT";
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(1, empty.size());
	        assertEquals(8, empty.getFront());
	    }
	
	    @Test
	    public void enqueueFrontAtEndWrapNeeded() {
	        emptyWrapper.forceSetFront(8);
	        String[] array = new String[] {"Eaglestrike", "GRT", "Boba", "Biomechs", null, null, null, null, "MVRT"};
	        emptyWrapper.forceSetArray(array);
	        emptyWrapper.forceSetSize(5);
	        array[4] = "Jump!";
	
	        empty.enqueue("Jump!");
	
	        AssertUtils.assertArrayEquals(array, empty.getBackingArray());
	        assertEquals(6, empty.size());
	        assertEquals(8, empty.getFront());
	    }
	
	    @Test
	    public void enqueueFrontAtEndResizeNeeded() {
	        emptyWrapper.forceSetFront(8);
	        String[] array = new String[] {"Biomechs", "Jump!", "Westlake", "Panthers", "Septo", "MVRT", "Eaglestrike", "GRT", "Boba"};
	        emptyWrapper.forceSetArray(array);
	        emptyWrapper.forceSetSize(9);
	        String[] unwrappedArray = new String[] {"Boba", "Biomechs", "Jump!", "Westlake", "Panthers", "Septo", "MVRT", "Eaglestrike", "GRT",
	                "Duncan", null, null, null, null, null, null, null, null};
	
	        empty.enqueue("Duncan");
	
	        AssertUtils.assertArrayEquals(unwrappedArray, empty.getBackingArray());
	        assertEquals(10, empty.size());
	        assertEquals(0, empty.getFront());
	    }
	
	    @Test
	    public void dequeueFrontAtZeroSizeZero() {
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> empty.dequeue(), null);
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(0, empty.getFront());
	        assertEquals(0, empty.size());
	    }
	
	    @Test
	    public void dequeueFrontAtZeroSizeOne() {
	        emptyWrapper.forceSetArray(new String[] {"Sun Tzu", null, null, null, null, null, null, null, null});
	        emptyWrapper.forceSetFront(0);
	        emptyWrapper.forceSetSize(1);
	        assertEquals("Sun Tzu", empty.dequeue());
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(1, empty.getFront());
	        assertEquals(0, empty.size());
	    }
	
	    @Test
	    public void dequeueFrontAtZeroSizeThree() {
	        emptyWrapper.forceSetArray(new String[] {"Sun Tzu", "Skipper", "Tremor", null, null, null, null, null, null});
	        emptyWrapper.forceSetFront(0);
	        emptyWrapper.forceSetSize(3);
	        emptyArray = new String[] {null, "Skipper", "Tremor", null, null, null, null, null, null};
	        assertEquals("Sun Tzu", empty.dequeue());
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(1, empty.getFront());
	        assertEquals(2, empty.size());
	    }
	
	    @Test
	    public void dequeueFrontAtZeroFull() {
	        assertEquals("Homestead", full.dequeue());
	        fullArray[0] = null;
	        AssertUtils.assertArrayEquals(fullArray, full.getBackingArray());
	        assertEquals(1, full.getFront());
	        assertEquals(8, full.size());
	    }
	
	    @Test
	    public void dequeueFrontInMiddleEmpty() {
	        emptyWrapper.forceSetFront(4);
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> empty.dequeue(), null);
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(4, empty.getFront());
	        assertEquals(0, empty.size());
	    }
	
	    @Test
	    public void dequeueFrontInMiddleSizeOne() {
	        emptyWrapper.forceSetArray(new String[] {null, null, null, null, "Sun Tzu", null, null, null, null});
	        emptyWrapper.forceSetFront(4);
	        emptyWrapper.forceSetSize(1);
	        assertEquals("Sun Tzu", empty.dequeue());
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(5, empty.getFront());
	        assertEquals(0, empty.size());
	    }
	
	    @Test
	    public void dequeueFrontInMiddleSizeThree() {
	        emptyWrapper.forceSetArray(new String[] {null, null, null, null, null, null, "Sun Tzu", "Skipper", "Tremor"});
	        emptyWrapper.forceSetFront(6);
	        emptyWrapper.forceSetSize(3);
	        emptyArray = new String[] {null, null, null, null, null, null, null, "Skipper", "Tremor"};
	        assertEquals("Sun Tzu", empty.dequeue());
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(7, empty.getFront());
	        assertEquals(2, empty.size());
	    }
	
	    @Test
	    public void dequeueFrontInMiddleSizeThreeWrap() {
	        emptyWrapper.forceSetArray(new String[] {"Tremor", null, null, null, null, null, null, "Sun Tzu", "Skipper"});
	        emptyWrapper.forceSetFront(7);
	        emptyWrapper.forceSetSize(3);
	        emptyArray = new String[] {"Tremor", null, null, null, null, null, null, null, "Skipper"};
	        assertEquals("Sun Tzu", empty.dequeue());
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(8, empty.getFront());
	        assertEquals(2, empty.size());
	    }
	
	    @Test
	    public void dequeueFrontInMiddleFull() {
	        fullWrapper.forceSetFront(4);
	        assertEquals("Citrus", full.dequeue());
	        fullArray[4] = null;
	        AssertUtils.assertArrayEquals(fullArray, full.getBackingArray());
	        assertEquals(5, full.getFront());
	        assertEquals(8, full.size());
	    }
	
	    @Test
	    public void dequeueFrontAtEndEmpty() {
	        emptyWrapper.forceSetFront(8);
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> empty.dequeue(), null);
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(0, empty.size());
	        assertEquals(8, empty.getFront());
	    }
	
	    @Test
	    public void dequeueFrontAtEndSizeOne() {
	        emptyWrapper.forceSetArray(new String[] {null, null, null, null, null, null, null, null, "Sun Tzu"});
	        emptyWrapper.forceSetFront(8);
	        emptyWrapper.forceSetSize(1);
	        assertEquals("Sun Tzu", empty.dequeue());
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(0, empty.getFront());
	        assertEquals(0, empty.size());
	    }
	
	    @Test
	    public void dequeueFrontAtEndSizeThreeWrap() {
	        emptyWrapper.forceSetArray(new String[] {"Skipper", "Tremor", null, null, null, null, null, null, "Sun Tzu"});
	        emptyWrapper.forceSetFront(8);
	        emptyWrapper.forceSetSize(3);
	        emptyArray = new String[] {"Skipper", "Tremor", null, null, null, null, null, null, null};
	        assertEquals("Sun Tzu", empty.dequeue());
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(0, empty.getFront());
	        assertEquals(2, empty.size());
	    }
	
	    @Test
	    public void dequeueFrontAtEndFull() {
	        fullWrapper.forceSetFront(8);
	        assertEquals("Monkeys", full.dequeue());
	        fullArray[8] = null;
	        AssertUtils.assertArrayEquals(fullArray, full.getBackingArray());
	        assertEquals(0, full.getFront());
	        assertEquals(8, full.size());
	    }
	
	    @Test
	    public void peekFrontAtZeroEmpty() {
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> empty.peek(), null);
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(0, empty.size());
	        assertEquals(0, empty.getFront());
	    }
	
	    @Test
	    public void ppekFrontAtZeroSizeOne() {
	        emptyWrapper.forceSetArray(new String[] {"Sun Tzu", null, null, null, null, null, null, null, null});
	        emptyArray = new String[] {"Sun Tzu", null, null, null, null, null, null, null, null};
	        emptyWrapper.forceSetFront(0);
	        emptyWrapper.forceSetSize(1);
	        assertEquals("Sun Tzu", empty.peek());
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(0, empty.getFront());
	        assertEquals(1, empty.size());
	    }
	
	    @Test
	    public void peekFrontAtZeroSizeThree() {
	        emptyWrapper.forceSetArray(new String[] {"Sun Tzu", "Skipper", "Tremor", null, null, null, null, null, null});
	        emptyWrapper.forceSetFront(0);
	        emptyWrapper.forceSetSize(3);
	        emptyArray = new String[] {"Sun Tzu", "Skipper", "Tremor", null, null, null, null, null, null};
	        assertEquals("Sun Tzu", empty.peek());
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(0, empty.getFront());
	        assertEquals(3, empty.size());
	    }
	
	    @Test
	    public void peekFrontAtZeroFull() {
	        assertEquals("Homestead", full.peek());
	        AssertUtils.assertArrayEquals(fullArray, full.getBackingArray());
	        assertEquals(0, full.getFront());
	        assertEquals(9, full.size());
	    }
	
	    @Test
	    public void peekFrontInMiddleEmpty() {
	        emptyWrapper.forceSetFront(4);
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> empty.peek(), null);
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(4, empty.getFront());
	        assertEquals(0, empty.size());
	    }
	
	    @Test
	    public void peekFrontInMiddleSizeOne() {
	        emptyWrapper.forceSetArray(new String[] {null, null, null, null, "Sun Tzu", null, null, null, null});
	        emptyWrapper.forceSetFront(4);
	        emptyWrapper.forceSetSize(1);
	        emptyArray = new String[] {null, null, null, null, "Sun Tzu", null, null, null, null};
	        assertEquals("Sun Tzu", empty.peek());
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(4, empty.getFront());
	        assertEquals(1, empty.size());
	    }
	
	    @Test
	    public void peekFrontInMiddleSizeThree() {
	        emptyWrapper.forceSetArray(new String[] {null, null, null, null, null, null, "Sun Tzu", "Skipper", "Tremor"});
	        emptyWrapper.forceSetFront(6);
	        emptyWrapper.forceSetSize(3);
	        emptyArray = new String[] {null, null, null, null, null, null, "Sun Tzu", "Skipper", "Tremor"};
	        assertEquals("Sun Tzu", empty.peek());
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(6, empty.getFront());
	        assertEquals(3, empty.size());
	    }
	
	    @Test
	    public void peekFrontInMiddleSizeThreeWrap() {
	        emptyWrapper.forceSetArray(new String[] {"Tremor", null, null, null, null, null, null, "Sun Tzu", "Skipper"});
	        emptyWrapper.forceSetFront(7);
	        emptyWrapper.forceSetSize(3);
	        emptyArray = new String[] {"Tremor", null, null, null, null, null, null, "Sun Tzu", "Skipper"};
	        assertEquals("Sun Tzu", empty.peek());
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(7, empty.getFront());
	        assertEquals(3, empty.size());
	    }
	
	    @Test
	    public void peekFrontInMiddleFull() {
	        fullWrapper.forceSetFront(4);
	        assertEquals("Citrus", full.peek());
	        AssertUtils.assertArrayEquals(fullArray, full.getBackingArray());
	        assertEquals(4, full.getFront());
	        assertEquals(9, full.size());
	    }
	
	    @Test
	    public void peekFrontAtEndEmpty() {
	        emptyWrapper.forceSetFront(8);
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> empty.peek(), null);
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(0, empty.size());
	        assertEquals(8, empty.getFront());
	    }
	
	    @Test
	    public void peekFrontAtEndSizeOne() {
	        emptyWrapper.forceSetArray(new String[] {null, null, null, null, null, null, null, null, "Sun Tzu"});
	        emptyWrapper.forceSetFront(8);
	        emptyWrapper.forceSetSize(1);
	        assertEquals("Sun Tzu", empty.peek());
	        emptyArray = new String[] {null, null, null, null, null, null, null, null, "Sun Tzu"};
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(8, empty.getFront());
	        assertEquals(1, empty.size());
	    }
	
	    @Test
	    public void peekFrontAtEndSizeThreeWrap() {
	        emptyWrapper.forceSetArray(new String[] {"Skipper", "Tremor", null, null, null, null, null, null, "Sun Tzu"});
	        emptyWrapper.forceSetFront(8);
	        emptyWrapper.forceSetSize(3);
	        emptyArray = new String[] {"Skipper", "Tremor", null, null, null, null, null, null, "Sun Tzu"};
	        assertEquals("Sun Tzu", empty.peek());
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	        assertEquals(8, empty.getFront());
	        assertEquals(3, empty.size());
	    }
	
	    @Test
	    public void peekFrontAtEndFull() {
	        fullWrapper.forceSetFront(8);
	        assertEquals("Monkeys", full.peek());
	        AssertUtils.assertArrayEquals(fullArray, full.getBackingArray());
	        assertEquals(8, full.getFront());
	        assertEquals(9, full.size());
	    }
	
	
	
	}
	
	public static class ArrayStackTests {
	    private ArrayStack<String> empty;
	    private ArrayStackWrapper<String> emptyWrapper;
	    private ArrayStack<String> sizeFour;
	    private ArrayStackWrapper<String> sizeFourWrapper;
	    private ArrayStack<String> full;
	    private ArrayStackWrapper<String> fullWrapper;
	
	    private String[] emptyArray;
	
	    @Before
	    public void setup() {
	        // IntelliJ yells at me for "illegal reflective access operation" XD
	        //ArrayStackWrapper.forceSetInitialCapacity(9);
	
	        empty = new ArrayStack<>();
	        emptyWrapper = new ArrayStackWrapper<>(empty);
	
	        sizeFour = new ArrayStack<>();
	        sizeFourWrapper = new ArrayStackWrapper<>(sizeFour);
	        sizeFourWrapper.forceSetSize(4);
	        sizeFourWrapper.forceSetArray(new String[] {"Justin", "Ethan", "Clarence", "Ansel", null, null, null, null, null});
	
	        full = new ArrayStack<>();
	        fullWrapper = new ArrayStackWrapper<>(full);
	        fullWrapper.forceSetSize(9);
	        fullWrapper.forceSetArray(new String[] {"Lindsay", "Elise", "Radhika", "Skyla", "Katia", "Tarini", "Kritt", "Aarushi", "Roopa"});
	
	        emptyArray = new String[] {null, null, null, null, null, null, null, null, null};
	
	    }
	
	    @Test
	    public void constructorTest() {
	        ArrayStack<String> stack = new ArrayStack<>();
	        AssertUtils.assertArrayEquals(new String[] {null, null, null, null, null, null, null, null, null}, stack.getBackingArray());
	        assertEquals(0, stack.size());
	    }
	
	    @Test
	    public void pushEmptyStackNullData() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> empty.push(null), null);
	        assertEquals(0, empty.size());
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	    }
	
	    @Test
	    public void pushEmptyStackValidData() {
	        empty.push("Lancer");
	
	        emptyArray[0] = "Lancer";
	        assertEquals(1, empty.size());
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	    }
	
	    @Test
	    public void pushSizeFourNullData() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> sizeFour.push(null), null);
	        assertEquals(4, sizeFour.size());
	        AssertUtils.assertArrayEquals(new String[] {"Justin", "Ethan", "Clarence", "Ansel", null, null, null, null, null}, sizeFour.getBackingArray());
	    }
	
	    @Test
	    public void pushSizeFourValidData() {
	        sizeFour.push("Edwar");
	        assertEquals(5, sizeFour.size());
	        AssertUtils.assertArrayEquals(new String[] {"Justin", "Ethan", "Clarence", "Ansel", "Edwar", null, null, null, null}, sizeFour.getBackingArray());
	    }
	
	    @Test
	    public void pushFullInvalidData() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> full.push(null), null);
	        assertEquals(9, full.size());
	        AssertUtils.assertArrayEquals(new String[] {"Lindsay", "Elise", "Radhika", "Skyla", "Katia", "Tarini", "Kritt", "Aarushi", "Roopa"}, full.getBackingArray());
	    }
	
	    @Test
	    public void pushFullValidData() {
	        // Resize case!
	        full.push("Yoon");
	        assertEquals(10, full.size());
	        AssertUtils.assertArrayEquals(new String[] {"Lindsay", "Elise", "Radhika", "Skyla", "Katia", "Tarini", "Kritt", "Aarushi", "Roopa", "Yoon", null, null, null, null, null, null, null, null}, full.getBackingArray());
	    }
	
	    @Test
	    public void pushFullValidDataResizeTwice() {
	        // Resize case twice. Make sure you're multiplying the capacity, not adding it.
	        full.push("Yoon");
	        full.push("Kern");
	        full.push("Aditi");
	        full.push("Armaan");
	        full.push("Eric");
	        full.push("Geoff");
	        full.push("Shreya");
	        full.push("Vi");
	        full.push("Auhon");
	        full.push("Itay");
	        assertEquals(19, full.size());
	        AssertUtils.assertArrayEquals(new String[] {"Lindsay", "Elise", "Radhika", "Skyla", "Katia", "Tarini", "Kritt", "Aarushi", "Roopa",
	                                        "Yoon", "Kern", "Aditi", "Armaan", "Eric", "Geoff", "Shreya", "Vi", "Auhon",
	                                        "Itay", null, null, null, null, null, null, null, null,
	                                        null, null, null, null, null, null, null, null, null}, full.getBackingArray());
	    }
	
	    @Test
	    public void popEmpty() {
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> empty.pop(), null);
	        assertEquals(0, empty.size());
	        assertEquals(emptyArray, empty.getBackingArray());
	    }
	
	    @Test
	    public void popSizeFour() {
	        assertEquals("Ansel", sizeFour.pop());
	        assertEquals(3, sizeFour.size());
	        AssertUtils.assertArrayEquals(new String[] {"Justin", "Ethan", "Clarence", null, null, null, null, null, null}, sizeFour.getBackingArray());
	    }
	
	    @Test
	    public void popFull() {
	        assertEquals("Roopa", full.pop());
	        assertEquals(8, full.size());
	        AssertUtils.assertArrayEquals(new String[] {"Lindsay", "Elise", "Radhika", "Skyla", "Katia", "Tarini", "Kritt", "Aarushi", null}, full.getBackingArray());
	    }
	
	    @Test
	    public void popFullPlusOne() {
	        full.push("Yoon");
	        assertEquals("Yoon", full.pop());
	        assertEquals(9, full.size());
	        AssertUtils.assertArrayEquals(new String[] {"Lindsay", "Elise", "Radhika", "Skyla", "Katia", "Tarini", "Kritt", "Aarushi", "Roopa", null, null, null, null, null, null, null, null, null}, full.getBackingArray());
	    }
	
	    @Test
	    public void peekEmpty() {
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> empty.peek(), null);
	        assertEquals(0, empty.size());
	        AssertUtils.assertArrayEquals(emptyArray, empty.getBackingArray());
	    }
	
	    @Test
	    public void peekSizeFour() {
	        assertEquals("Ansel", sizeFour.peek());
	        assertEquals(4, sizeFour.size());
	        AssertUtils.assertArrayEquals(new String[] {"Justin", "Ethan", "Clarence", "Ansel", null, null, null, null, null}, sizeFour.getBackingArray());
	    }
	
	    @Test
	    public void peekFull() {
	        assertEquals("Roopa", full.peek());
	        assertEquals(9, full.size());
	        AssertUtils.assertArrayEquals(new String[] {"Lindsay", "Elise", "Radhika", "Skyla", "Katia", "Tarini", "Kritt", "Aarushi", "Roopa"}, full.getBackingArray());
	    }
	
	}
	
	/**
	 * Tests for LinkedQueues.
	 * @author Justin Hwang, Ryder Johnson
	 */
	public static class LinkedQueueTests {
	
	    private LinkedQueue<String> empty;
	    private LinkedQueueWrapper<String> emptyWrapper;
	    private LinkedQueue<String> sizeOne;
	    private LinkedQueueWrapper<String> sizeOneWrapper;
	    private LinkedQueue<String> sizeThree;
	    private LinkedQueueWrapper<String> sizeThreeWrapper;
	
	    @Before
	    public void createLinkedQueues() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
	        empty = new LinkedQueue<>();
	        emptyWrapper = new LinkedQueueWrapper<>(empty);
	
	        sizeOne = new LinkedQueue<>();
	        sizeOneWrapper = new LinkedQueueWrapper<>(sizeOne);
	        LinkedNodeWrapper<String> node = new LinkedNodeWrapper<>(LinkedNodeWrapper.constructLinkedNode("Tremor"));
	        sizeOneWrapper.forceSetHead(node.getNode());
	        sizeOneWrapper.forceSetTail(node.getNode());
	        sizeOneWrapper.forceSetSize(1);
	
	        sizeThree = new LinkedQueue<>();
	        sizeThreeWrapper = new LinkedQueueWrapper<>(sizeThree);
	        
	        LinkedNodeWrapper<String> nodeOne = new LinkedNodeWrapper<>(LinkedNodeWrapper.constructLinkedNode("React"));
	        LinkedNodeWrapper<String> nodeTwo = new LinkedNodeWrapper<>(LinkedNodeWrapper.constructLinkedNode("Charged"));
	        LinkedNodeWrapper<String> nodeThree = new LinkedNodeWrapper<>(LinkedNodeWrapper.constructLinkedNode("Crescendo"));
	        nodeOne.setNext(nodeTwo.getNode());
	        nodeTwo.setNext(nodeThree.getNode());
	        
	        sizeThreeWrapper.forceSetHead(nodeOne.getNode());
	        sizeThreeWrapper.forceSetTail(nodeThree.getNode());
	        sizeThreeWrapper.forceSetSize(3);
	    }
	
	    @Test
	    public void enqueueSizeZeroDataNull() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> empty.enqueue(null), null);
	        assertEquals(0, empty.size());
	        assertNull(empty.getHead());
	        assertNull(empty.getTail());
	        AssertUtils.assertArrayEquals(new String[0], emptyWrapper.toArray());
	    }
	
	    @Test
	    public void enqueueSizeZeroDataNonNull() {
	        empty.enqueue("Skipper");
	
	        assertEquals(1, empty.size());
	        assertEquals("Skipper", emptyWrapper.getHeadData());
	        assertEquals("Skipper", emptyWrapper.getTailData());
	        AssertUtils.assertArrayEquals(new String[] {"Skipper"}, emptyWrapper.toArray());
	    }
	
	    @Test
	    public void enqueueSizeOneDataNull() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> sizeOne.enqueue(null), null);
	
	        assertEquals(1, sizeOne.size());
	        assertEquals("Tremor", sizeOneWrapper.getHeadData());
	        assertEquals("Tremor", sizeOneWrapper.getTailData());
	        AssertUtils.assertArrayEquals(new String[] {"Tremor"}, sizeOneWrapper.toArray());
	    }
	
	    @Test
	    public void enqueueSizeOneDataNonNull() {
	        sizeOne.enqueue("Skipper");
	
	        assertEquals(2, sizeOne.size());
	        assertEquals("Tremor", sizeOneWrapper.getHeadData());
	        assertEquals("Skipper", sizeOneWrapper.getTailData());
	        AssertUtils.assertArrayEquals(new String[] {"Tremor", "Skipper"}, sizeOneWrapper.toArray());
	    }
	
	    @Test
	    public void enqueueSizeThreeDataNull() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> sizeThree.enqueue(null), null);
	
	        assertEquals(3, sizeThree.size());
	        assertEquals("React", sizeThreeWrapper.getHeadData());
	        assertEquals("Crescendo", sizeThreeWrapper.getTailData());
	        AssertUtils.assertArrayEquals(new String[] {"React", "Charged", "Crescendo"}, sizeThreeWrapper.toArray());
	    }
	
	    @Test
	    public void enqueueSizeThreeDataNonNull() {
	        sizeThree.enqueue("Sun Tzu");
	
	        assertEquals(4, sizeThree.size());
	        assertEquals("React", sizeThreeWrapper.getHeadData());
	        assertEquals("Sun Tzu", sizeThreeWrapper.getTailData());
	        AssertUtils.assertArrayEquals(new String[] {"React", "Charged", "Crescendo", "Sun Tzu"}, sizeThreeWrapper.toArray());
	    }
	
	    @Test
	    public void dequeueSizeZero() {
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> empty.dequeue(), null);
	        assertEquals(0, empty.size());
	        assertNull(empty.getHead());
	        assertNull(empty.getTail());
	        AssertUtils.assertArrayEquals(new String[0], emptyWrapper.toArray());
	    }
	
	    @Test
	    public void dequeueSizeOne() {
	        assertEquals("Tremor", sizeOne.dequeue());
	        assertEquals(0, sizeOne.size());
	        assertNull(sizeOne.getHead());
	        assertNull(sizeOne.getTail());
	        AssertUtils.assertArrayEquals(new String[0], sizeOneWrapper.toArray());
	    }
	
	    @Test
	    public void dequeueSizeThree() {
	        assertEquals("React", sizeThree.dequeue());
	        assertEquals(2, sizeThree.size());
	        assertEquals("Charged", sizeThreeWrapper.getHeadData());
	        assertEquals("Crescendo", sizeThreeWrapper.getTailData());
	        AssertUtils.assertArrayEquals(new String[] {"Charged", "Crescendo"}, sizeThreeWrapper.toArray());
	    }
	
	    @Test
	    public void peekSizeZero() {
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> empty.dequeue(), null);
	        assertEquals(0, empty.size());
	        assertNull(empty.getHead());
	        assertNull(empty.getTail());
	        AssertUtils.assertArrayEquals(new String[0], emptyWrapper.toArray());
	    }
	
	    @Test
	    public void peekSizeOne() {
	        assertEquals("Tremor", sizeOne.peek());
	        assertEquals(1, sizeOne.size());
	        assertEquals("Tremor", sizeOneWrapper.getHeadData());
	        assertEquals("Tremor", sizeOneWrapper.getTailData());
	        AssertUtils.assertArrayEquals(new String[] {"Tremor"}, sizeOneWrapper.toArray());
	    }
	
	    @Test
	    public void peekSizeThree() {
	        assertEquals("React", sizeThree.peek());
	        assertEquals(3, sizeThree.size());
	        assertEquals("React", sizeThreeWrapper.getHeadData());
	        assertEquals("Crescendo", sizeThreeWrapper.getTailData());
	        AssertUtils.assertArrayEquals(new String[] {"React", "Charged", "Crescendo"}, sizeThreeWrapper.toArray());
	    }
	
	}
	
	/**
	 * Tests for LinkedStacks.
	 * @author Justin Hwang, Ryder Johnson
	 */
	public static class LinkedStackTests {
	
	    private LinkedStack<String> emptyStack;
	    private LinkedStackWrapper<String> emptyStackWrapper;
	    private LinkedStack<String> sizeOneStack;
	    private LinkedStackWrapper<String> sizeOneStackWrapper;
	    private LinkedStack<String> nonEmptyStack;
	    private LinkedStackWrapper<String> nonEmptyStackWrapper;
	
	    @Before
	    public void createLinkedStacks() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
	        emptyStack = new LinkedStack<>();
	        emptyStackWrapper = new LinkedStackWrapper<>(emptyStack);
	
	        sizeOneStack = new LinkedStack<>();
	        sizeOneStackWrapper = new LinkedStackWrapper<>(sizeOneStack);
	        LinkedNodeWrapper<String> node = new LinkedNodeWrapper<>(LinkedNodeWrapper.constructLinkedNode("Skipper"));
	        sizeOneStackWrapper.forceSetHead(node.getNode());
	        sizeOneStackWrapper.forceSetSize(1);
	
	        nonEmptyStack = new LinkedStack<>();
	        nonEmptyStackWrapper = new LinkedStackWrapper<>(nonEmptyStack);
	        LinkedNodeWrapper<String> nodeOne = new LinkedNodeWrapper<>(LinkedNodeWrapper.constructLinkedNode("React"));
	        LinkedNodeWrapper<String> nodeTwo = new LinkedNodeWrapper<>(LinkedNodeWrapper.constructLinkedNode("Charged"));
	        LinkedNodeWrapper<String> nodeThree = new LinkedNodeWrapper<>(LinkedNodeWrapper.constructLinkedNode("Crescendo"));
	
	        nodeOne.setNext(nodeTwo.getNode());
	        nodeTwo.setNext(nodeThree.getNode());
	        nonEmptyStackWrapper.forceSetHead(nodeOne.getNode());
	        nonEmptyStackWrapper.forceSetSize(3);
	    }
	
	    @Test
	    public void pushHeadNullDataNull() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> emptyStack.push(null), null);
	        AssertUtils.assertArrayEquals(new Object[0], emptyStackWrapper.toArray());
	        assertEquals(0, emptyStack.size());
	        assertNull(emptyStack.getHead());
	    }
	
	    @Test
	    public void pushHeadNullDataNotNull() {
	        emptyStack.push("Tremor");
	        AssertUtils.assertArrayEquals(new String[]{"Tremor"}, emptyStackWrapper.toArray());
	        assertEquals(1, emptyStack.size());
	        assertEquals("Tremor", emptyStackWrapper.getHeadData());
	    }
	
	    @Test
	    public void pushHeadNotNullDataNull() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> nonEmptyStack.push(null), null);
	        AssertUtils.assertArrayEquals(new String[] {"React", "Charged", "Crescendo"}, nonEmptyStackWrapper.toArray());
	        assertEquals(3, nonEmptyStack.size());
	        assertEquals("React", nonEmptyStackWrapper.getHeadData());
	    }
	
	    @Test
	    public void pushHeadNotNullDataNotNull() {
	        nonEmptyStack.push("Tremor");
	        AssertUtils.assertArrayEquals(new String[]{"Tremor", "React", "Charged", "Crescendo"}, nonEmptyStackWrapper.toArray());
	        assertEquals(4, nonEmptyStack.size());
	        assertEquals("Tremor", nonEmptyStackWrapper.getHeadData());
	    }
	
	    @Test
	    public void popHeadNull() {
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> emptyStack.pop(), null);
	        AssertUtils.assertArrayEquals(new Object[0], emptyStackWrapper.toArray());
	        assertEquals(0, emptyStack.size());
	        assertNull(emptyStack.getHead());
	    }
	
	    @Test
	    public void popSizeOne() {
	        assertEquals("Skipper", sizeOneStack.pop());
	        AssertUtils.assertArrayEquals(new String[0], sizeOneStackWrapper.toArray());
	        assertEquals(0, sizeOneStack.size());
	        assertNull(sizeOneStack.getHead());
	    }
	
	    @Test
	    public void popSizeThree() {
	        assertEquals("React", nonEmptyStack.pop());
	        AssertUtils.assertArrayEquals(new String[] {"Charged", "Crescendo"}, nonEmptyStackWrapper.toArray());
	        assertEquals(2, nonEmptyStack.size());
	        assertEquals("Charged", nonEmptyStackWrapper.getHeadData());
	    }
	
	    @Test
	    public void peekHeadNull() {
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> emptyStack.peek(), null);
	        AssertUtils.assertArrayEquals(new Object[0], emptyStackWrapper.toArray());
	        assertEquals(0, emptyStack.size());
	        assertNull(emptyStack.getHead());
	    }
	
	    @Test
	    public void peekSizeOne() {
	        assertEquals("Skipper", sizeOneStack.peek());
	        AssertUtils.assertArrayEquals(new String[] {"Skipper"}, sizeOneStackWrapper.toArray());
	        assertEquals(1, sizeOneStack.size());
	        assertEquals("Skipper", sizeOneStackWrapper.getHeadData());
	    }
	
	    @Test
	    public void peekSizeThree() {
	        assertEquals("React", nonEmptyStack.peek());
	        AssertUtils.assertArrayEquals(new String[] {"React", "Charged", "Crescendo"}, nonEmptyStackWrapper.toArray());
	        assertEquals(3, nonEmptyStack.size());
	        assertEquals("React", nonEmptyStackWrapper.getHeadData());
	    }
	
	}

}


class ArrayDrawingBuilder {
    private static final int ARROW_SIZE = 1;

    private static final int LABEL_WHITESPACE = 10;

    private final Object[] objects;

    private String finalOutput = "";

    private String label;

    private int[] highlightedIndices;

    public ArrayDrawingBuilder(Object[] objects) {
        this.objects = Arrays.stream(objects).map(o -> o == null ? "null" : o).toArray();
    }

    public ArrayDrawingBuilder withArrayVisualization() {
        finalOutput += getArrayString(objects);
        return this;
    }

    public ArrayDrawingBuilder withPointerAt(int index, String message) {
        // Replace with linkedlist system in next iteration (this was purely for sake of time)
        int pointerIndex = index >= objects.length ? finalOutput.split("\n")[1].length() + 5 : getMiddleIndex(index) + (label != null ? 10 : 0);

        for (int i = 0; i < ARROW_SIZE + 1; i++) {
            String symbol = i == 0 ? "^" : "|";

            finalOutput += " ".repeat(pointerIndex);
            finalOutput += symbol;

            if (i == ARROW_SIZE) {
                finalOutput += " " + message;

            }

            finalOutput += "\n";

        }


        return this;
    }

    public ArrayDrawingBuilder withHighlightedIndices(int... indices) {
        this.highlightedIndices = indices;

        return this;
    }

    public ArrayDrawingBuilder withLabel(String label) {
        this.label = label;

        String[] lines = finalOutput.split("\n");;
        int lineCount = lines.length;

        int insertIndex = lineCount / 2;

        StringBuilder newOutput = new StringBuilder();

        String space = " ".repeat(LABEL_WHITESPACE);

        for (int i = 0; i < lineCount; i++) {
            if (i == insertIndex) {
                newOutput.append(label).append(" ".repeat(LABEL_WHITESPACE - label.length()));
            } else {
                newOutput.append(space);
            }

            newOutput.append(lines[i]).append("\n");
        }

        finalOutput = newOutput.toString();

        return this;
    }

    public String build() {
        return finalOutput.stripTrailing();
    }

    private int getMiddleIndex(int elementIndex) {
        int index = 0;

        for (int i = 0; i < elementIndex; i++) {
            index += 3 + objects[i].toString().length();
        }

        // center arrow
        index += objects[elementIndex].toString().length() / 2 + 2;

        return index;
    }

    public String getArrayString(Object[] arr) {
        StringBuilder start = new StringBuilder("\n");

        int topLength = arr.length;

        for (Object o : arr) {
            topLength += o.toString().length() + 2;
        }

        topLength -= 1;

        start.append(createLine(topLength));

        for (int i = 0; i < arr.length; i++) {
            start.append("| ");

            boolean highlighted = false;

            if (highlightedIndices != null) {
                for (int highlightedIndex: highlightedIndices) {
                    if (i == highlightedIndex) {
                        highlighted = true;
                        break;
                    }
                }
            }


            if (highlighted) {
                start.append(ColorUtils.formatColorString(AsciiColorCode.RED_BACKGROUND, AsciiColorCode.BLACK_FOREGROUND, arr[i].toString()));
            } else {
                start.append(arr[i].toString());
            }


            start.append(" ");
        }

        start.append("|");

        if (highlightedIndices != null) {

            if (highlightedIndices.length != 0) {
                // start backwards to avoid duplicate
                for (int index = highlightedIndices.length - 1; index >= 0; index--) {
                    if (highlightedIndices[index] == objects.length) {
                        start.append(ColorUtils.formatColorString(AsciiColorCode.RED_BACKGROUND, AsciiColorCode.BLACK_FOREGROUND, " MISSING "));
                        break;
                    }
                }
            }
        }

        start.append("\n");

        start.append(createLine(topLength));


        return start.toString();
    }

    private static String createLine(int topLength) {

        return "+" + "-".repeat(Math.max(0, topLength)) +
                "+" +
                "\n";
    }
}


/**
 * A wrapper for ArrayQueue allowing previously hidden elements to be accessed.
 * <p>
 * Primarily uses {@link ReflectionWrapper} to modify fields.
 * @param <T> The data type of the ArrayQueue
 */
class ArrayQueueWrapper<T> {
    private final ReflectionWrapper wrapper;

    /**
     * Constructs a new ArrayQueueWrapper using an existing ArrayQueue.
     * @param arrayQueue The ArrayQueue to wrap around
     */
    public ArrayQueueWrapper(ArrayQueue<T> arrayQueue) {
        this.wrapper = new ReflectionWrapper(arrayQueue);
    }

    /**
     * Helper method for forcibly setting the size variable of a ArrayQueue
     * <p>
     * @param size The new size of the ArrayQueue
     */
    public void forceSetSize(int size) {
        try {
            wrapper.setValue("size", size);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper method for forcibly setting the INITIAL_CAPACITY constant of a ArrayQueue
     * <p>
     * @param capacity The new size of the ArrayQueue
     */
    public static void forceSetInitialCapacity(int capacity) {
        try {
            ReflectionWrapper tempWrapper = new ReflectionWrapper(new ArrayQueue<>());
            tempWrapper.setValue("INITIAL_CAPACITY", capacity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Forcibly sets the backing array of the wrapped ArrayQueue
     * @param newBackingArray The new backing array to set
     */
    public void forceSetArray(T[] newBackingArray) {
        try {
			wrapper.setValue("backingArray", Arrays.copyOf(newBackingArray, newBackingArray.length));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void forceSetFront(int front) {
        try {
            wrapper.setValue("front", front);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}


/**
 * A wrapper for ArrayStack allowing previously hidden elements to be accessed.
 * <p>
 * Primarily uses {@link ReflectionWrapper} to modify fields.
 * @param <T> The data type of the ArrayStack
 */
class ArrayStackWrapper<T> {
    private final ReflectionWrapper wrapper;

    /**
     * Constructs a new ArrayStack using an existing LinkedStack.
     * @param arrayStack The array stack to wrap around
     */
    public ArrayStackWrapper(ArrayStack<T> arrayStack) {
        this.wrapper = new ReflectionWrapper(arrayStack);
    }

    /**
     * Helper method for forcibly setting the size variable of a ArrayStack
     * <p>
     * @param size The new size of the ArrayStack
     */
    public void forceSetSize(int size) {
        try {
            wrapper.setValue("size", size);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper method for forcibly setting the INITIAL_CAPACITY constant of a ArrayStack
     * <p>
     * @param capacity The new size of the ArrayStack
     */
    public static void forceSetInitialCapacity(int capacity) {
        try {
            ReflectionWrapper tempWrapper = new ReflectionWrapper(new ArrayStack<>());
            tempWrapper.setValue("INITIAL_CAPACITY", capacity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Forciblly sets the backing array of the wrapped ArraySTack
     * @param newBackingArray The new backing array to set
     */
    public void forceSetArray(T[] newBackingArray) {
        try {
			wrapper.setValue("backingArray", Arrays.copyOf(newBackingArray, newBackingArray.length));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

final class AsciiColorCode {

    public static final String RESET_COLOR = "\033[0m";

    public static final String BLACK_FOREGROUND = "\033[30m";
    public static final String BLACK_BACKGROUND = "\033[40m";

    public static final String RED_FOREGROUND = "\033[31m";
    public static final String RED_BACKGROUND = "\033[41m";

    public static final String GREEN_FOREGROUND = "\033[32m";
    public static final String GREEN_BACKGROUND = "\033[42m";

    public static final String YELLOW_FOREGROUND = "\033[33m";
    public static final String YELLOW_BACKGROUND = "\033[43m";

    public static final String BLUE_FOREGROUND = "\033[34m";
    public static final String BLUE_BACKGROUND = "\033[44m";

    public static final String MAGENTA_FOREGROUND = "\033[35m";
    public static final String MAGENTA_BACKGROUND = "\033[45m";

    public static final String CYAN_FOREGROUND = "\033[36m";
    public static final String CYAN_BACKGROUND = "\033[46m";

    public static final String WHITE_FOREGROUND = "\033[37m";
    public static final String WHITE_BACKGROUND = "\033[47m";

    public static final String BRIGHT_BLACK_FOREGROUND = "\033[90m";
    public static final String BRIGHT_BLACK_BACKGROUND = "\033[100m";

    public static final String BRIGHT_RED_FOREGROUND = "\033[91m";
    public static final String BRIGHT_RED_BACKGROUND = "\033[101m";

    public static final String BRIGHT_GREEN_FOREGROUND = "\033[92m";
    public static final String BRIGHT_GREEN_BACKGROUND = "\033[102m";

    public static final String BRIGHT_YELLOW_FOREGROUND = "\033[93m";
    public static final String BRIGHT_YELLOW_BACKGROUND = "\033[103m";

    public static final String BRIGHT_BLUE_FOREGROUND = "\033[94m";
    public static final String BRIGHT_BLUE_BACKGROUND = "\033[104m";

    public static final String BRIGHT_MAGENTA_FOREGROUND = "\033[95m";
    public static final String BRIGHT_MAGENTA_BACKGROUND = "\033[105m";

    public static final String BRIGHT_CYAN_FOREGROUND = "\033[96m";
    public static final String BRIGHT_CYAN_BACKGROUND = "\033[106m";

    public static final String BRIGHT_WHITE_FOREGROUND = "\033[97m";
    public static final String BRIGHT_WHITE_BACKGROUND = "\033[107m";
}



/**
 * A set of utilities to remove redundant assertion boilerplate.
 */
class AssertUtils {

    public static void assertValidException(Exception e) {
        assertTrue(ExceptionUtils.isDescriptiveException(e));
    }

    public static void assertArrayEquals(Object[] expected, Object[] actual) {
        try {
            Assert.assertArrayEquals(expected, actual);
        } catch (AssertionError error) {
            try {
                String expectedString = new ArrayDrawingBuilder(expected)
                        .withArrayVisualization()
                        .withLabel("Expected")
                        .build();

                ArrayDrawingBuilder actualBuilder = new ArrayDrawingBuilder(actual);


                String errorMessage = error.getMessage();

                Matcher matcher = Pattern.compile("\\[([0-9]+)\\]").matcher(errorMessage);

                if (matcher.find()) {
                    int index = Integer.parseInt(matcher.group(1));

                    actualBuilder = actualBuilder.withHighlightedIndices(index)
                            .withArrayVisualization()
                            .withPointerAt(index, "Element differed")
                            .withLabel("Actual");


                } else {
                    actualBuilder = actualBuilder.withArrayVisualization().withLabel("Actual");
                }

                String actualString = actualBuilder.build();

                String message = expectedString + actualString + "\n" + error.getMessage();

                Assert.fail(message);
            } catch (Exception e) {
                // Catch issues like the inputted array being null when it shouldn't be,
                // defaulting it to the normal JUnit exception message
                Assert.fail(error.getMessage());
            }
        }
    }
}

class ColorUtils {
    /**
     * Formats a string to have an ASCII background in terminal.
     *
     * @param background The ASCII representation of the background color, pulled
     *                   from AsciiColorCode
     * @param s          The string to color
     * @return The colored string
     */
    public static String formatBackgroundColorString(String background, String s) {
        return background + s + AsciiColorCode.RESET_COLOR;
    }

    /**
     * Formats a string to have an ASCII foreground (text color) in terminal.
     *
     * @param foreground The ASCII representation of the foreground color, pulled
     *                   from AsciiColorCode
     * @param s          The string to color
     * @return The colored string
     */
    public static String formatForegroundColorString(String foreground, String s) {
        return foreground + s + AsciiColorCode.RESET_COLOR;

    }

    /**
     * Formats a string to have both an ASCII foreground and background in terminal
     *
     * @param background The ASCII representation of the background color, pulled
     *                   from AsciiColorCode
     * @param foreground The ASCII representation of the foreground color, pulled
     *                   from AsciiColorCode
     * @param s          The string to color
     * @return The colored string
     */
    public static String formatColorString(String background, String foreground, String s) {
        return foreground + background + s.replace("\n", AsciiColorCode.RESET_COLOR + "\n" + foreground + background) + AsciiColorCode.RESET_COLOR;
    }
}

/**
 * A set of utilities for handling test cases regarding exceptions.
 */
class ExceptionUtils {

    /**
     * Asserts a snippet of code throws the required exception, checking if it's descriptive along the way.
     * @param expected The expected exception to throw
     * @param codeThatMayThrowException A runnable containing the code intended to throw an exception
     * @param requiredSubstring A required substring for the message to be considered descriptive.
     *                          If this is null, then no substring is required.
     */
    public static void assertExceptionIsCorrect(Class<? extends Exception> expected, Runnable codeThatMayThrowException, String requiredSubstring) {
        try {
            codeThatMayThrowException.run();
            // If this line runs, then your code was supposed to throw an Exception but did not.
            Assert.fail("Encountered a test that did not throw the expected expected of " + expected.getName());

        } catch (Exception e) {
            Assert.assertSame("checking if the thrown exception is what we expected", expected, e.getClass()); // If this line fails, the code threw the wrong exception (or threw no exception at all)
            assertTrue(e.getClass().getName() + " exception thrown needs to be descriptive " + (requiredSubstring != null ? "\" and must contain the message \"" + requiredSubstring : "\""), isDescriptiveException(e, requiredSubstring)); // If this line fails, then the message was insufficiently descriptive
        }
    }

    /**
     * Determines whether a message is considered "descriptive" by the rubric.
     * This isn't a perfect check, but it at the very least ensures all exceptions
     * contain some sort of error message.
     *
     * @param e                 The exception to check
     * @param requiredSubstring The substring that most be contained in the exception message.
     *                          If this is null, then no substring is required.
     * @return true if the exception is considered descriptive
     */
    public static boolean isDescriptiveException(Exception e, String requiredSubstring) {
        if (requiredSubstring == null) {
            return !(e.getMessage() == null || e.getMessage().isBlank());
        } else {
            return e.getMessage().contains(requiredSubstring);
        }
    }

    /**
     * Convenience feature for isDescriptiveException(Exception e, String requiredSubstring).
     * @param e Exception to check.
     * @return Same as isDescriptiveException(Exception e, String requiredSubstring).
     */
    public static boolean isDescriptiveException(Exception e) {
        return isDescriptiveException(e, null);
    }
}


/**
 * A utility for redirecting the standard output for use in test cases.
 * <p>
 * Primary, this is being used to ensure "nothing should be printed" when the code is run.
 * The IORedirector will not on its own fail the tests, but is used before and after
 * to record all messages sent.
 */
class IORedirector {
    private static IORedirector instance;

    private PrintStream originalStream;
    private PrintStream redirectedStream;

    private String log = "";

    /**
     * Private singleton construction method, initially grabbing the standard output for later use.
     */
    private IORedirector() {
        this.originalStream = System.out;
    }

    /**
     * Begin redirecting the standard output into the custom PrintStream, used before a test is run.
     */
    public void beginRedirecting() {
        log = "";

        if (redirectedStream == null) {
            redirectedStream = getRedirectorStream();
        }

        System.setOut(redirectedStream);
    }

    /**
     * Standard Singleton-pattern instance system to avoid IORedirectors fighting over the same stream.
     * @return The IORedirector instance
     */
    public static IORedirector getInstance() {
        if (instance == null) {
            instance = new IORedirector();
        }

        return instance;
    }

    /**
     * All messages received from the standard output system are directed to this message via
     * the redirector stream. This is responsible for attaching those messages to the log.
     * @param s The input to handle
     */
    private void handleMessage(String s) {
        log += s.replaceAll("\r", "");
    }

    public boolean hasReceivedMessage() {
        return !log.isEmpty();
    }

    private PrintStream getRedirectorStream() {
        return new PrintStream(System.out, true) {
            @Override
            public void print(String s) {
                IORedirector.getInstance().handleMessage(s);
            }

            @Override
            public PrintStream printf(String message, Object... args) {
                IORedirector.getInstance().handleMessage(String.format(message, args));
                return this;
            }

            @Override
            public void println(String s) {
                IORedirector.getInstance().handleMessage(s + "\n");
            }
        };
    }
}


/**
 * A helper class used for traversing through a LinkedList without relying on the
 * user's methods. As the homework obviously can't be written here, it instead uses
 * reflection to brute force testing in a much more reliable manner.
 */
class LinkedListTraversalHelper {
    /**
     * Retrieves all data from a series of LinkedNodes using reflections
     * @param headNode The head node to begin traversal at.
     * @return An array of T representing the contents of the LinkedList, used for checking in test cases.
     * @param <T> The data type of the LinkedList
     */
    public static <T> T[] retrieveData(LinkedNode<T> headNode) {

        ArrayList<LinkedNodeWrapper<T>> arrayList = new ArrayList<>();

        if (headNode == null) {
            return (T[]) new Object[0];
        }

        LinkedNodeWrapper<T> headWrapper = new LinkedNodeWrapper<>(headNode);
        LinkedNodeWrapper<T> currWrapper = headWrapper;
        while (currWrapper.getNext() != null) {
            arrayList.add(currWrapper);
            currWrapper = new LinkedNodeWrapper<>(currWrapper.getNext());
        }
        arrayList.add(currWrapper);

        T[] data = (T[]) new Object[arrayList.size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = arrayList.get(i).getData();
        }

        return data;
    }
}



/**
 * A wrapper utility class for accessing package-protected or private classes located within LinkedNode<T></T>
 * @param <T> The generic used by the wrapper node
 */
class LinkedNodeWrapper<T>  {
    private final ReflectionWrapper wrapper;

    public LinkedNodeWrapper(LinkedNode<T> node) {
        this.wrapper = new ReflectionWrapper(node);
    }

    /**
     * Uses reflection to forcibly construct a new LinkedNode
     *
     * This method was required due to complications of the methods being marked as package-private,
     * while the files for these JUnit tests are initally in separate packages.
     * @param data The data to inject into the node
     * @return A new LinkedNode<T> containing the specified data
     * @throws NoSuchMethodException if the constructor was unable to be found
     * @throws InvocationTargetException N/A
     * @throws InstantiationException N/A
     * @throws IllegalAccessException N/A
     */
    public static <T> LinkedNode<T> constructLinkedNode(T data) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> nodeClass = LinkedNode.class;

        @SuppressWarnings("unchecked") Constructor<LinkedNode<T>> constructor = (Constructor<LinkedNode<T>>) nodeClass.getDeclaredConstructor(Object.class);
        constructor.setAccessible(true);

        return constructor.newInstance(data);
    }

    public T getData() {
        return forceCast(wrapper.invokeMethod("getData"));
    }

    public void setData(T data) {
        wrapper.invokeMethod("setData", data);
    }

    public void setNext(LinkedNode<T> next) {
        wrapper.invokeMethod("setNext", next);
    }

    public LinkedNode<T> getNext() {
        return (LinkedNode<T>) wrapper.invokeMethod("getNext");
    }

    public LinkedNode<T> getNode() {
        return (LinkedNode<T>) wrapper.getInternalReference();
    }

    private T forceCast(Object o) {
        //noinspection unchecked
        return (T) o;
    }

}


/**
 * A wrapper for CircularSinglyLinkedList allowing previously hidden elements to be accessed.
 * <p>
 * Primarily uses {@link ReflectionWrapper} to modify fields.
 * @param <T> The data type of the CircularSinglyLinkedList
 */
class LinkedQueueWrapper<T> {
    private final ReflectionWrapper wrapper;

    /**
     * Constructs a new LinkedQueueWrapper using an existing LinkedQueue.
     * @param linkedList The linked list to wrap around
     */
    public LinkedQueueWrapper(LinkedQueue<T> linkedList) {
        this.wrapper = new ReflectionWrapper(linkedList);
    }

    /**
     * Helper method for forcibly setting the node of a LinkedQueue's head.
     * <p>
     * This is completely from the main LinkedList implementation, as if the
     * user's addToIndex methods are incorrect all tests using it to initialize
     * would fail. Instead, reflection is used here to forcibly change it without
     * any interaction with the user's internal code.
     * @param newHeadNode the new head node to set
     */
    public void forceSetHead(LinkedNode<T> newHeadNode) {
        try {
            wrapper.setValue("head", newHeadNode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper method for forcibly setting the node of a LinkedQueue's head.
     * <p>
     * This is completely from the main LinkedList implementation, as if the
     * user's addToIndex methods are incorrect all tests using it to initialize
     * would fail. Instead, reflection is used here to forcibly change it without
     * any interaction with the user's internal code.
     * @param newTailNode the new head node to set
     */
    public void forceSetTail(LinkedNode<T> newTailNode) {
        try {
            wrapper.setValue("tail", newTailNode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper method for forcibly setting the size variable of a LinkedStack
     * <p>
     * @param size The new size of the LinkedList
     */
    public void forceSetSize(int size) {
        try {
            wrapper.setValue("size", size);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public T getHeadData() {
        try {
            LinkedNodeWrapper<T> headNode = new LinkedNodeWrapper<>((LinkedNode<T>) wrapper.getFieldValue("head"));
            return headNode.getData();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public T getTailData() {
        try {
            LinkedNodeWrapper<T> tailNode = new LinkedNodeWrapper<>((LinkedNode<T>) wrapper.getFieldValue("tail"));
            return tailNode.getData();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public T[] toArray() {
        try {
            return LinkedListTraversalHelper.retrieveData((LinkedNode<T>) wrapper.getFieldValue("head"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}



/**
 * A wrapper for LinkedStack allowing previously hidden elements to be accessed.
 * <p>
 * Primarily uses {@link ReflectionWrapper} to modify fields.
 * @param <T> The data type of the LinkedStack
 */
class LinkedStackWrapper<T> {
    private final ReflectionWrapper wrapper;

    /**
     * Constructs a new LinkedStackWrapper using an existing LinkedStack.
     * @param linkedList The linked list to wrap around
     */
    public LinkedStackWrapper(LinkedStack<T> linkedList) {
        this.wrapper = new ReflectionWrapper(linkedList);
    }

    /**
     * Helper method for forcibly setting the node of a LinkedStack's head.
     * <p>
     * This is completely from the main LinkedList implementation, as if the
     * user's addToIndex methods are incorrect all tests using it to initialize
     * would fail. Instead, reflection is used here to forcibly change it without
     * any interaction with the user's internal code.
     * @param newHeadNode the new head node to set
     */
    public void forceSetHead(LinkedNode<T> newHeadNode) {
        try {
            wrapper.setValue("head", newHeadNode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper method for forcibly setting the size variable of a LinkedStack
     * <p>
     * @param size The new size of the LinkedList
     */
    public void forceSetSize(int size) {
        try {
            wrapper.setValue("size", size);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public T getHeadData() {
        try {
            LinkedNodeWrapper<T> headNode = new LinkedNodeWrapper<>((LinkedNode<T>) wrapper.getFieldValue("head"));
            return headNode.getData();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public T[] toArray() {
        try {
            return LinkedListTraversalHelper.retrieveData((LinkedNode<T>) wrapper.getFieldValue("head"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}


/**
 * The main wrapper to encapsulate any object where Reflection is needed.
 * <p>
 * Without any boilerplate code Reflection can become exponentially more tedious
 * as the requirements scale, and this class aims to largely solve that issue.
 * <p>
 * An example for these is seen in the {@link LinkedNodeWrapper}.
 */
class ReflectionWrapper {
    /**
     * The internal reference to the object the Reflection API is accessing
     */
    private final Object internalReference;
    Method[] methods;

    /**
     * Constructs a new ReflectionWrapper around a given object.
     * @param internalReference The object to wrap around
     */
    public ReflectionWrapper(Object internalReference) {
        this.internalReference = internalReference;
    }

    /**
     * Retrives a method from the internalObject, regardless of whether it's private or not.
     * @param method The method to retrieve
     * @param methodInputs The inputs to pull
     * @return The given method in the target class
     * @throws NoSuchMethodException If the method was unable to be found
     */
    public Method getMethod(String method, Class<?>... methodInputs) throws NoSuchMethodException {
        Method m = internalReference.getClass().getDeclaredMethod(method, methodInputs);
        m.setAccessible(true);
        return m;
    }

    /**
     * Retrives a field from the internalObject, regardless of whether it's private or not.
     * @param field The field to retrieve
     * @return The given field in the target class, with forced visibility
     * @throws NoSuchFieldException If the method was unable to be found
     */
    public Field getField(String field) throws NoSuchFieldException {
        Field f = internalReference.getClass().getDeclaredField(field);
        f.setAccessible(true);
        return f;
    }

    /**
     * Retrieves and invokes a method from the internalObject, regardless of whether it's prviate or not.
     * @param method The method to retrieve
     * @param input The inputs to pull
     * @return The return value of the invoked method
     */
    public Object invokeMethod(String method, Object... input)  {
        try {
            Class<?>[] classes = new Class<?>[input.length];

            for (int i = 0; i < classes.length; i++) {
                classes[i] = input[i].getClass();
            }

            return getMethod(method, classes).invoke(internalReference, input);

        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets a field, regardless of its visibility, to a given value.
     * @param fieldName The name of the field
     * @param value The value to set the field to
     * @throws NoSuchFieldException If the field was unable to be found
     * @throws IllegalAccessException If the field has some sort of protection (should in theory never occur)
     */
    public void setValue(String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = internalReference.getClass().getDeclaredField(fieldName);

        field.setAccessible(true);

//        Field modifiersField = Field.class.getDeclaredField("modifiers");
//        modifiersField.setAccessible(true);
//        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        // Necessary because primitive fields cannot be set to their wrapper classes
        if (value.getClass().equals(Integer.class)) {
            field.setInt(internalReference, (int) value);
        } else {
            field.set(internalReference, value);
        }

    }

    /**
     * Gets a field's value, regardless of its visibility.
     * @param fieldName The name of the field
     * @return The value in the field, as an object
     * @throws NoSuchFieldException If the given field does not exist
     * @throws IllegalAccessException If the field has extra protection (should in theory never occur)
     */
    public Object getFieldValue(String fieldName) throws NoSuchFieldException {
        try {
            return getField(fieldName).get(internalReference);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Retrieves the internal object of the wrapper.
     * @return The internal reference to the object under the wrapper.
     */
    public Object getInternalReference() {
        return internalReference;
    }
}

class ArrayElement  implements Drawable {
    private Object element;

    public ArrayElement(Object element) {
        this.element = element;
    }

    @Override
    public String draw() {



        return null;
    }
}

interface Drawable {
    String draw();
}


class LinkedNodeDrawer<T> implements Drawable {
    private LinkedNodeWrapper<T> head;
    private LinkedNodeWrapper<T> headPointer;
    private LinkedNodeWrapper<T> tailPointer;

    public LinkedNodeDrawer(LinkedNode<T> head) {
        this.head = new LinkedNodeWrapper<>(head);
    }

    @Override
    public String draw() {
        String[] lines = new String[]{"", "", "", ""};


        drawNode(lines, head);

        LinkedNodeWrapper<T> current = head;

        while (current.getNext() != null) {
            LinkedNodeWrapper<T> next = new LinkedNodeWrapper<>(current.getNext());

            if (next.getNode().equals(head.getNode())) {

                lines[2] += "Head";
                return "\n" + String.join("\n", lines);
            }

            drawNode(lines, next);
            current = new LinkedNodeWrapper<>(current.getNext());
        }

        lines[2] += "null (end of linked nodes)";

        return "\n" + String.join("\n", lines);
    }

    public LinkedNodeDrawer<T> withHeadPointer(LinkedNode<T> node) {
        headPointer = new LinkedNodeWrapper<>(node);

        return this;
    }

    public LinkedNodeDrawer<T> withTailPointer(LinkedNode<T> node) {
        tailPointer = new LinkedNodeWrapper<>(node);

        return this;
    }

    public void drawNode(String[] lines, LinkedNodeWrapper<T> node) {
        T data = node.getData();


        String dashes = "-".repeat(data.toString().length() + 2);

        String line = "+" + dashes + "+    ";


        lines[1] += line;
        lines[2] += "| " + data + " | -> ";
        lines[3] += line;

        String before = " ".repeat((data.toString().length()) / 2);
        String after = " ".repeat(line.length() - before.length() - 4);

        if (data.equals(headPointer.getData())) {
            System.out.println(line.length());
            System.out.println(before.length());

            lines[0] += before + "HEAD" + after;
        } else if (data.equals(tailPointer.getData())) {
            lines[0] += before + "TAIL" + after;
        } else {
            lines[0] += " ".repeat(line.length());
        }


    }
}
