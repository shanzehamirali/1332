
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
    Cheers,
    - Ryder Johnson
    - Justin Hwang
 */

import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import java.util.HashSet;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.Assert;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.GZIPInputStream;
import java.io.PrintStream;

@RunWith(Enclosed.class)
public class JUnitTests {
	public static class ClearTests {
	    
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void clearEmptyTable() {
	
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[13]);
	
	        map.clear();
	
	        AssertUtils.assertHashMapEquals(map, new Integer[13], new String[13], 0);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void clearResizedTable() {
	
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[13]);
	
	        for (int i = 0; i < 20; ++i) {
	            map.put(i, "" + i);
	        }
	
	        map.clear();
	
	        AssertUtils.assertHashMapEquals(map, new Integer[13], new String[13], 0);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void clearTableWithData() {
	
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Homestead", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Madtown", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "TKO", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Aragon", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Goldstrikers", true)
	        });
	
	        map.clear();
	
	        AssertUtils.assertHashMapEquals(map, new Integer[13], new String[13], 0);
	
	    }
	
	}

	
	
	public static class ConstructorTests {
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void noArgsConstructor() {
	        LinearProbingHashMap<Integer, String> emptyMap = new LinearProbingHashMap<>();
	        AssertUtils.assertHashMapEquals(emptyMap, new Integer[13], new String[13], 0);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void oneArgConstructorSizeZero() {
	        LinearProbingHashMap<Integer, String> sizeZero = new LinearProbingHashMap<>(0);
	        AssertUtils.assertHashMapEquals(sizeZero, new Integer[0], new String[0], 0);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void oneArgConstructorSizeOne() {
	        LinearProbingHashMap<Integer, String> sizeOneEmpty = new LinearProbingHashMap<>(1);
	        AssertUtils.assertHashMapEquals(sizeOneEmpty, new Integer[1], new String[1], 0);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void oneArgConstructorSizeFive() {
	        LinearProbingHashMap<Integer, String> sizeFive = new LinearProbingHashMap<>(5);
	        AssertUtils.assertHashMapEquals(sizeFive, new Integer[5], new String[5], 0);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void oneArgConstructorSizeEleven() {
	        LinearProbingHashMap<Integer, String> sizeEleven = new LinearProbingHashMap<>(11);
	        AssertUtils.assertHashMapEquals(sizeEleven, new Integer[11], new String[11], 0);
	    }
	
	}

	
	
	
	
	public static class ContainsKeyTests {
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void keyIsNull() {
	        LinearProbingHashMap<Integer, String> empty = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", false),
	                null,
	                null
	
	        });
	
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> empty.containsKey(null), null);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void keyExistsAtIndex() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", false),
	                null,
	                null
	
	        });
	
	        assertTrue(map.containsKey(2));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, null, null}, new String[] {"Citrus", "Madtown", "Homestead", null, null}, new boolean[] {false, false, false, false, false}, 3);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void keyExistsAfterDelMarker() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(5, "Homestead", false),
	                null,
	                null
	
	        });
	
	        assertTrue(map.containsKey(5));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 5, null, null}, new String[] {"Citrus", "Madtown", "Homestead", null, null}, new boolean[] {false, true, false, false, false}, 2);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void keyExistsOneStepFoward() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(5, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", false),
	                null,
	                null
	
	        });
	
	        assertTrue(map.containsKey(5));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 5, 2, null, null}, new String[] {"Citrus", "Madtown", "Homestead", null, null}, new boolean[] {false, false, false, false, false}, 3);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void keyNotInList() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", false),
	                null,
	                null
	        });
	
	        assertFalse(map.containsKey(5));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, null, null}, new String[] {"Citrus", "Madtown", "Homestead", null, null}, new boolean[] {true, false, false, false, false}, 2);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void loopsThroughEntireTable() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(7, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(3, "Spartan", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Aragon", true),
	        });
	
	        assertTrue(map.containsKey(7));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 7, 2, 3, 4}, new String[] {"Citrus", "Madtown", "Homestead", "Spartan", "Aragon"}, new boolean[] {true, false, true, true, true}, 1);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void stopsIteratingAtNull() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                null,
	
	                // Illegal state! (INTENTIONAL)
	                // This is not possible with normal use. However, if your code fails, this test,
	                // then you probably do not stop iterating at null as intended.
	                LinearProbingMapEntryWrapper.constructMapEntry(5, "Spartan", false),
	                null
	        });
	
	        assertFalse(map.containsKey(5));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, null, 5, null}, new String[] {"Citrus", "Madtown", null, "Spartan", null}, new boolean[] {true, false, false, false, false}, 2);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void treatsDelMarkerWithSameKeyAsNull() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(5, "Homestead", true),
	
	                // Illegal state! (INTENTIONAL)
	                // This is not possible with normal use. However, if your code fails, this test,
	                // then you probably do not stop iterating when you find a DEL marker with the same key.
	                LinearProbingMapEntryWrapper.constructMapEntry(5, "Spartan", false),
	                null
	        });
	
	        assertFalse(map.containsKey(5));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 5, 5, null}, new String[] {"Citrus", "Madtown", "Homestead", "Spartan", null}, new boolean[] {true, false, true, false, false}, 2);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void keyExistsInDelMarker() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(3, "Spartan", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Aragon", false),
	        });
	
	        assertFalse(map.containsKey(2));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, 3, 4}, new String[] {"Citrus", "Madtown", "Homestead", "Spartan", "Aragon"}, new boolean[] {true, false, true, false, false}, 3);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void mapFullOfNulls() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                null,
	                null,
	                null,
	                null,
	                null
	        });
	
	        assertFalse(map.containsKey(2));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[5], new String[5], new boolean[5], 0);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void mapFullOfDels() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(3, "Spartan", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Aragon", true),
	        });
	
	        assertFalse(map.containsKey(6));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, 3, 4}, new String[] {"Citrus", "Madtown", "Homestead", "Spartan", "Aragon"}, new boolean[] {true, true, true, true, true}, 0);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void integerMinValue() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(Integer.MIN_VALUE, "Spartan", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Aragon", true),
	        });
	
	        assertTrue(map.containsKey(Integer.MIN_VALUE));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, Integer.MIN_VALUE, 4}, new String[] {"Citrus", "Madtown", "Homestead", "Spartan", "Aragon"}, new boolean[] {true, true, true, false, true}, 1);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void stopsIteratingAfterSeeingSizeItemsSizeZero() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(7, "Spartan", true),
	
	                // Illegal state! (INTENTIONAL)
	                // If your code replaces this item, then you are iterating
	                // beyond the item marker with the matching key.
	
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                null,
	                null,
	                null,
	        });
	
	        LinearProbingHashMapWrapper<Integer, String> wrapper = new LinearProbingHashMapWrapper(map);
	        wrapper.forceSetSize(0);
	
	        // ------------------------------------------------------------
	        // If you're seeing this, you probably are not properly terminating
	        // your probing. See Piazza @624 for explanation.
	        // ------------------------------------------------------------
	        assertFalse(map.containsKey(0));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {7, 0, null, null, null}, new String[] {"Spartan", "Citrus", null, null, null}, new boolean[] {true, false, false, false, false}, 0);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void stopsIteratingAfterSeeingSizeItemsSizeThree() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Spartan", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(7, "Homestead", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(14, "Goldstrikers", true),
	
	                // Illegal state! (INTENTIONAL)
	                // If your code replaces this item, then you are iterating
	                // beyond the item marker with the matching key.
	
	                LinearProbingMapEntryWrapper.constructMapEntry(21, "Citrus", false),
	                null,
	                null,
	                null
	        });
	
	        LinearProbingHashMapWrapper<Integer, String> wrapper = new LinearProbingHashMapWrapper(map);
	        wrapper.forceSetSize(2);
	
	        // ------------------------------------------------------------
	        // If you're seeing this, you probably are not properly terminating
	        // your probing. See Piazza @624 for explanation.
	        // ------------------------------------------------------------
	        assertFalse(map.containsKey(21));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 7, 14, 21, null, null, null}, new String[] {"Spartan", "Homestead", "Goldstrikers", "Citrus", null, null, null}, new boolean[] {false, false, true, false, false, false, false}, 2);
	
	    }
	}

	
	
	
	
	public static class GetTests {
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void keyIsNull() {
	        LinearProbingHashMap<Integer, String> empty = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", false),
	                null,
	                null
	
	        });
	
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> empty.get(null), null);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void keyExistsAtIndex() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", false),
	                null,
	                null
	
	        });
	
	        assertEquals("Homestead", map.get(2));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, null, null}, new String[] {"Citrus", "Madtown", "Homestead", null, null}, new boolean[] {false, false, false, false, false}, 3);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void keyExistsAfterDelMarker() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(5, "Homestead", false),
	                null,
	                null
	
	        });
	
	        assertEquals("Homestead", map.get(5));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 5, null, null}, new String[] {"Citrus", "Madtown", "Homestead", null, null}, new boolean[] {false, true, false, false, false}, 2);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void keyExistsOneStepFoward() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(5, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", false),
	                null,
	                null
	
	        });
	
	        assertEquals("Madtown", map.get(5));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 5, 2, null, null}, new String[] {"Citrus", "Madtown", "Homestead", null, null}, new boolean[] {false, false, false, false, false}, 3);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void keyNotInList() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", false),
	                null,
	                null
	        });
	
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> map.get(5), null);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, null, null}, new String[] {"Citrus", "Madtown", "Homestead", null, null}, new boolean[] {true, false, false, false, false}, 2);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void loopsThroughEntireTable() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(7, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(3, "Spartan", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Aragon", true),
	        });
	
	        assertEquals("Madtown", map.get(7));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 7, 2, 3, 4}, new String[] {"Citrus", "Madtown", "Homestead", "Spartan", "Aragon"}, new boolean[] {true, false, true, true, true}, 1);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void stopsIteratingAtNull() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                null,
	
	                // Illegal state! (INTENTIONAL)
	                // This is not possible with normal use. However, if your code fails, this test,
	                // then you probably do not stop iterating at null as intended.
	                LinearProbingMapEntryWrapper.constructMapEntry(5, "Spartan", false),
	                null
	        });
	
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> map.get(5), null);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, null, 5, null}, new String[] {"Citrus", "Madtown", null, "Spartan", null}, new boolean[] {true, false, false, false, false}, 2);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void treatsDelMarkerWithSameKeyAsNull() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(5, "Homestead", true),
	
	                // Illegal state! (INTENTIONAL)
	                // This is not possible with normal use. However, if your code fails, this test,
	                // then you probably do not stop iterating when you find a DEL marker with the same key.
	                LinearProbingMapEntryWrapper.constructMapEntry(5, "Spartan", false),
	                null
	        });
	
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> map.get(5), null);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 5, 5, null}, new String[] {"Citrus", "Madtown", "Homestead", "Spartan", null}, new boolean[] {true, false, true, false, false}, 2);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void keyExistsInDelMarker() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(3, "Spartan", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Aragon", false),
	        });
	
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> map.get(2), null);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, 3, 4}, new String[] {"Citrus", "Madtown", "Homestead", "Spartan", "Aragon"}, new boolean[] {true, false, true, false, false}, 3);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void mapFullOfNulls() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                null,
	                null,
	                null,
	                null,
	                null
	        });
	
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> map.get(2), null);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[5], new String[5], new boolean[5], 0);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void mapFullOfDels() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(3, "Spartan", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Aragon", true),
	        });
	
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> map.get(6), null);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, 3, 4}, new String[] {"Citrus", "Madtown", "Homestead", "Spartan", "Aragon"}, new boolean[] {true, true, true, true, true}, 0);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void integerMinValue() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(Integer.MIN_VALUE, "Spartan", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Aragon", true),
	        });
	
	        assertEquals("Spartan", map.get(Integer.MIN_VALUE));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, Integer.MIN_VALUE, 4}, new String[] {"Citrus", "Madtown", "Homestead", "Spartan", "Aragon"}, new boolean[] {true, true, true, false, true}, 1);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void stopsIteratingAfterSeeingSizeItemsSizeZero() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(7, "Spartan", true),
	
	                // Illegal state! (INTENTIONAL)
	                // If your code replaces this item, then you are iterating
	                // beyond the item marker with the matching key.
	
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                null,
	                null,
	                null,
	        });
	
	        LinearProbingHashMapWrapper<Integer, String> wrapper = new LinearProbingHashMapWrapper(map);
	        wrapper.forceSetSize(0);
	
	        // ------------------------------------------------------------
	        // If you're seeing this, you probably are not properly terminating
	        // your probing. See Piazza @624 for explanation.
	        // ------------------------------------------------------------
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> map.get(0), null);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {7, 0, null, null, null}, new String[] {"Spartan", "Citrus", null, null, null}, new boolean[] {true, false, false, false, false}, 0);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void stopsIteratingAfterSeeingSizeItemsSizeThree() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Spartan", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(7, "Homestead", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(14, "Goldstrikers", true),
	
	                // Illegal state! (INTENTIONAL)
	                // If your code replaces this item, then you are iterating
	                // beyond the item marker with the matching key.
	
	                LinearProbingMapEntryWrapper.constructMapEntry(21, "Citrus", false),
	                null,
	                null,
	                null
	        });
	
	        LinearProbingHashMapWrapper<Integer, String> wrapper = new LinearProbingHashMapWrapper(map);
	        wrapper.forceSetSize(2);
	
	        // ------------------------------------------------------------
	        // If you're seeing this, you probably are not properly terminating
	        // your probing. See Piazza @624 for explanation.
	        // ------------------------------------------------------------
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> map.get(21), null);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 7, 14, 21, null, null, null}, new String[] {"Spartan", "Homestead", "Goldstrikers", "Citrus", null, null, null}, new boolean[] {false, false, true, false, false, false, false}, 2);
	
	    }
	}

	
	
	
	
	public static class KeySetTests {
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void tableFullOfNull() {
	
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                null,
	                null,
	                null,
	                null,
	                null
	        });
	
	        HashSet<Integer> set = new HashSet<>();
	
	        assertEquals(set, map.keySet());
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void usesHashSet() {
	
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                null,
	                null,
	                null,
	                null,
	                null
	        });
	
	        assertTrue(map.keySet() instanceof HashSet);
	    }
	    
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void hashMapHasOneItem() {
	
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                null,
	                null,
	                LinearProbingMapEntryWrapper.constructMapEntry(7, "Homestead", false),
	                null,
	                null
	        });
	
	        HashSet<Integer> set = new HashSet<>();
	        set.add(7);
	
	        assertEquals(set, map.keySet());
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void hashMapContainsDelMarkers() {
	
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Homestead", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(7, "Aragon", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(14, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(21, "Goldstrikers", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(28, "TKO", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(35, "Orbit", true),
	                null
	        });
	
	        HashSet<Integer> set = new HashSet<>();
	        set.add(0);
	        set.add(14);
	        set.add(28);
	
	        assertEquals(set, map.keySet());
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void tableHasOnlyDelMarkers() {
	
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Homestead", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(7, "Aragon", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(14, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(21, "Goldstrikers", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(28, "TKO", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(35, "Orbit", true),
	                null
	        });
	
	        HashSet<Integer> set = new HashSet<>();
	
	        assertEquals(set, map.keySet());
	
	    }
	
	}

	
	
	public static class PutTests {
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void addToEmpty() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[5]);
	        assertNull(map.put(3, "HRT"));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {null, null, null, 3, null}, new String[] {null, null, null, "HRT", null}, 1);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void noCollisionNoResize() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                new LinearProbingMapEntry<>(0, "Spartan"),
	                null,
	                null,
	                new LinearProbingMapEntry<>(3, "Madtown"),
	                null
	        });
	        assertNull(map.put(2, "Homestead"));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, null, 2, 3, null}, new String[] {"Spartan", null, "Homestead", "Madtown", null}, 3);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void oneCollisionNoResize() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                new LinearProbingMapEntry<>(0, "Spartan"),
	                null,
	                null,
	                new LinearProbingMapEntry<>(3, "Madtown"),
	                null
	        });
	        assertNull(map.put(5, "Homestead"));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 5, null, 3, null}, new String[] {"Spartan", "Homestead", null, "Madtown", null}, 3);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void twoCollisionsNoResize() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                new LinearProbingMapEntry<>(0, "Spartan"),
	                new LinearProbingMapEntry<>(1, "Madtown"),
	                null,
	                null,
	                null
	        });
	        assertNull(map.put(5, "Homestead"));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 5, null, null}, new String[] {"Spartan", "Madtown", "Homestead", null, null}, 3);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void oneCollisionPastDEL() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                new LinearProbingMapEntry<>(0, "Spartan"),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Citrus", true),
	                new LinearProbingMapEntry<>(2, "Madtown"),
	                null,
	                null
	        });
	        assertNull(map.put(5, "Homestead"));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 5, 2, null, null}, new String[] {"Spartan", "Homestead", "Madtown", null, null}, 3);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void collisionJumpsToFirstDEL() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                new LinearProbingMapEntry<>(0, "Spartan"),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Orbit", true),
	                new LinearProbingMapEntry<>(3, "Madtown"),
	                null
	        });
	        assertNull(map.put(5, "Homestead"));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 5, 2, 3, null}, new String[] {"Spartan", "Homestead", "Orbit", "Madtown", null}, new boolean[] {false, false, true, false, false}, 3);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void delWithSameKeyIsTreatedAsNull() {
	
	        // --------------------------------------------------------------------------
	        // NOTE: This test uses a tree that is in an illegal state. This is INTENTIONAL
	        //       in order to test your specific implementation more thoroughly.
	        // --------------------------------------------------------------------------
	
	        // --------------------------------------------------------------------------
	        // NOTE: As specified, finding a DEL marker with the same key should have the
	        //       same behavior as finding null.
	        // --------------------------------------------------------------------------
	
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Spartan", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(9, "MVRT", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(3, "Orbit", false),
	
	                // Illegal state! (INTENTIONAL)
	                // If your code replaces this item, then you are iterating
	                // beyond the DEL marker with the matching key.
	                LinearProbingMapEntryWrapper.constructMapEntry(9, "Monkeys", false),
	                null,
	                null,
	                null,
	                null,
	        });
	
	        assertNull(map.put(9, "Homestead"));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 9, 3, 9, null, null, null, null}, new String[] {"Spartan", "Citrus", "Homestead", "Orbit", "Monkeys", null, null, null, null}, new boolean[] {false, false, false, false, false, false, false, false, false}, 5);
	
	    }@Test(timeout = TestConfiguration.TIMEOUT)
	    public void delWithSameKeyIsTreatedAsNullMultipleDelMarkers() {
	
	        // --------------------------------------------------------------------------
	        // NOTE: This test uses a tree that is in an illegal state. This is INTENTIONAL
	        //       in order to test your specific implementation more thoroughly.
	        // --------------------------------------------------------------------------
	
	        // --------------------------------------------------------------------------
	        // NOTE: As specified, finding a DEL marker with the same key should have the
	        //       same behavior as finding null.
	        // --------------------------------------------------------------------------
	
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Spartan", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(9, "Madtown", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(3, "Orbit", false),
	
	                // Illegal state! (INTENTIONAL)
	                // If your code replaces this item, then you are iterating
	                // beyond the DEL marker with the matching key.
	                LinearProbingMapEntryWrapper.constructMapEntry(9, "Monkeys", false),
	                null,
	                null,
	                null,
	                null,
	        });
	        assertNull(map.put(9, "Homestead"));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {9, 1, 9, 3, 9, null, null, null, null}, new String[] {"Homestead", "Citrus", "Madtown", "Orbit", "Monkeys", null, null, null, null}, new boolean[] {false, false, true, false, false, false, false, false, false}, 4);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void replacesExistingKey() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                new LinearProbingMapEntry<>(0, "Spartan"),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Citrus", true),
	                new LinearProbingMapEntry<>(2, "Orbit"),
	                new LinearProbingMapEntry<>(7, "Madtown"),
	                null,
	                null,
	                null
	        });
	        assertEquals("Madtown", map.put(7, "Homestead"));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, 7, null, null, null}, new String[] {"Spartan", "Citrus", "Orbit", "Homestead", null, null, null}, new boolean[] {false, true, false, false, false, false, false}, 3);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void fullOfDELMarkers() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Monkeys", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(3, "Aragon", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Panthers", true),
	
	        });
	        assertNull(map.put(5, "Homestead"));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {5, 1, 2, 3, 4}, new String[] {"Homestead", "Madtown", "Monkeys", "Aragon", "Panthers"}, new boolean[] {false, true, true, true, true}, 1);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void fullOfEitherNodesOrDELMarkers() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Monkeys", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(3, "Aragon", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Panthers", true),
	
	        });
	        assertNull(map.put(5, "Homestead"));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 5, 3, 4}, new String[] {"Citrus", "Madtown", "Homestead", "Aragon", "Panthers"}, new boolean[] {false, false, false, true, true}, 3);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void hasIsIntegerMinValue() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                null,
	                null,
	                null,
	                null,
	                null
	
	        });
	        assertNull(map.put(Integer.MIN_VALUE, "Homestead"));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {null, null, null, Integer.MIN_VALUE, null}, new String[] {null, null, null, "Homestead", null}, new boolean[] {false, false, false, false, false}, 1);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void resizeCase() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Monkeys", false),
	                null,
	                null
	
	        });
	        assertNull(map.put(3, "Homestead"));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, 3, null, null, null, null, null, null, null}, new String[] {"Citrus", "Madtown", "Monkeys", "Homestead", null, null, null, null, null, null, null}, new boolean[] {false, false, false, false, false, false, false, false, false, false, false}, 4);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void resizeEvenThoughReplacingValue() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Monkeys", false),
	                null,
	                null
	
	        });
	        assertEquals("Monkeys", map.put(2, "Homestead"));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, null, null, null, null, null, null, null, null}, new String[] {"Citrus", "Madtown", "Homestead", null, null, null, null, null, null, null, null}, new boolean[] {false, false, false, false, false, false, false, false, false, false, false}, 3);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void nullKey() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", false),
	                null,
	                null
	
	        });
	
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> map.put(null, "Monkeys"), null);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void nullValue() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", false),
	                null,
	                null
	
	        });
	
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> map.put(3, null), null);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void replacesExistingKeyImmediately() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                new LinearProbingMapEntry<>(0, "Spartan"),
	
	                // Illegal state! (INTENTIONAL)
	                // If your code replaces this item, then you are iterating
	                // beyond the item marker with the matching key.
	
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                new LinearProbingMapEntry<>(0, "Orbit"),
	                new LinearProbingMapEntry<>(0, "Madtown"),
	                null,
	                null,
	                null
	        });
	        assertEquals("Spartan", map.put(0, "Homestead"));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 0, 0, 0, null, null, null}, new String[] {"Homestead", "Citrus", "Orbit", "Madtown", null, null, null}, new boolean[] {false, true, false, false, false, false, false}, 3);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void stopsIteratingAfterSeeingSizeItemsSizeZero() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(7, "Spartan", true),
	
	                // Illegal state! (INTENTIONAL)
	                // If your code replaces this item, then you are iterating
	                // beyond the item marker with the matching key.
	
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                null,
	                null,
	                null,
	        });
	
	        LinearProbingHashMapWrapper<Integer, String> wrapper = new LinearProbingHashMapWrapper(map);
	        wrapper.forceSetSize(0);
	
	        // ------------------------------------------------------------
	        // If you're seeing this, you probably are not properly terminating
	        // your probing. See Piazza @624 for explanation.
	        // ------------------------------------------------------------
	        assertNull(map.put(0, "Homestead"));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 0, null, null, null}, new String[] {"Homestead", "Citrus", null, null, null}, new boolean[] {false, false, false, false, false}, 1);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void stopsIteratingAfterSeeingSizeItemsSizeThree() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Spartan", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(7, "Homestead", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(14, "Goldstrikers", true),
	
	                // Illegal state! (INTENTIONAL)
	                // If your code replaces this item, then you are iterating
	                // beyond the item marker with the matching key.
	
	                LinearProbingMapEntryWrapper.constructMapEntry(21, "Citrus", false),
	                null,
	                null,
	                null
	        });
	
	        LinearProbingHashMapWrapper<Integer, String> wrapper = new LinearProbingHashMapWrapper(map);
	        wrapper.forceSetSize(2);
	
	        // ------------------------------------------------------------
	        // If you're seeing this, you probably are not properly terminating
	        // your probing. See Piazza @624 for explanation.
	        // ------------------------------------------------------------
	        assertNull(map.put(21, "Monkeys"));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 7, 21, 21, null, null, null}, new String[] {"Spartan", "Homestead", "Monkeys", "Citrus", null, null, null}, new boolean[] {false, false, false, false, false, false, false}, 3);
	
	    }
	
	
	}

	
	
	
	
	public static class RemoveTests {
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void keyIsNull() {
	        LinearProbingHashMap<Integer, String> empty = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", false),
	                null,
	                null
	
	        });
	
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> empty.remove(null), null);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void keyExistsAtIndex() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", false),
	                null,
	                null
	
	        });
	
	        assertEquals("Homestead", map.remove(2));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, null, null}, new String[] {"Citrus", "Madtown", "Homestead", null, null}, new boolean[] {false, false, true, false, false}, 2);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void keyExistsAfterDelMarker() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(5, "Homestead", false),
	                null,
	                null
	
	        });
	
	        assertEquals("Homestead", map.remove(5));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 5, null, null}, new String[] {"Citrus", "Madtown", "Homestead", null, null}, new boolean[] {false, true, true, false, false}, 1);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void keyNotInList() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", false),
	                null,
	                null
	        });
	
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> map.remove(5), null);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, null, null}, new String[] {"Citrus", "Madtown", "Homestead", null, null}, new boolean[] {true, false, false, false, false}, 2);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void loopsThroughEntireTable() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(7, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(3, "Spartan", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Aragon", true),
	        });
	
	        assertEquals("Madtown", map.remove(7));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 7, 2, 3, 4}, new String[] {"Citrus", "Madtown", "Homestead", "Spartan", "Aragon"}, new boolean[] {true, true, true, true, true}, 0);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void stopsIteratingAtNull() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                null,
	
	                // Illegal state! (INTENTIONAL)
	                // This is not possible with normal use. However, if your code fails, this test,
	                // then you probably do not stop iterating at null as intended.
	                LinearProbingMapEntryWrapper.constructMapEntry(5, "Spartan", false),
	                null
	        });
	
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> map.remove(5), null);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, null, 5, null}, new String[] {"Citrus", "Madtown", null, "Spartan", null}, new boolean[] {true, false, false, false, false}, 2);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void treatsDelMarkerWithSameKeyAsNull() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(5, "Homestead", true),
	
	                // Illegal state! (INTENTIONAL)
	                // This is not possible with normal use. However, if your code fails, this test,
	                // then you probably do not stop iterating when you find a DEL marker with the same key.
	                LinearProbingMapEntryWrapper.constructMapEntry(5, "Spartan", false),
	                null
	        });
	
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> map.remove(5), null);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 5, 5, null}, new String[] {"Citrus", "Madtown", "Homestead", "Spartan", null}, new boolean[] {true, false, true, false, false}, 2);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void keyExistsInDelMarker() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(3, "Spartan", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Aragon", false),
	        });
	
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> map.remove(2), null);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, 3, 4}, new String[] {"Citrus", "Madtown", "Homestead", "Spartan", "Aragon"}, new boolean[] {true, false, true, false, false}, 3);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void mapFullOfNulls() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                null,
	                null,
	                null,
	                null,
	                null
	        });
	
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> map.remove(2), null);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[5], new String[5], new boolean[5], 0);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void mapFullOfDels() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(3, "Spartan", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Aragon", true),
	        });
	
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> map.remove(6), null);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, 3, 4}, new String[] {"Citrus", "Madtown", "Homestead", "Spartan", "Aragon"}, new boolean[] {true, true, true, true, true}, 0);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void integerMinValue() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Homestead", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(Integer.MIN_VALUE, "Spartan", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Aragon", true),
	        });
	
	        assertEquals("Spartan", map.remove(Integer.MIN_VALUE));
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, Integer.MIN_VALUE, 4}, new String[] {"Citrus", "Madtown", "Homestead", "Spartan", "Aragon"}, new boolean[] {true, true, true, true, true}, 0);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void stopsIteratingAfterSeeingSizeItemsSizeZero() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(7, "Spartan", true),
	
	                // Illegal state! (INTENTIONAL)
	                // If your code replaces this item, then you are iterating
	                // beyond the item marker with the matching key.
	
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Citrus", false),
	                null,
	                null,
	                null,
	        });
	
	        LinearProbingHashMapWrapper<Integer, String> wrapper = new LinearProbingHashMapWrapper(map);
	        wrapper.forceSetSize(0);
	
	        // ------------------------------------------------------------
	        // If you're seeing this, you probably are not properly terminating
	        // your probing. See Piazza @624 for explanation.
	        // ------------------------------------------------------------
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> map.remove(0), null);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {7, 0, null, null, null}, new String[] {"Spartan", "Citrus", null, null, null}, new boolean[] {true, false, false, false, false}, 0);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void stopsIteratingAfterSeeingSizeItemsSizeThree() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Spartan", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(7, "Homestead", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(14, "Goldstrikers", true),
	
	                // Illegal state! (INTENTIONAL)
	                // If your code replaces this item, then you are iterating
	                // beyond the item marker with the matching key.
	
	                LinearProbingMapEntryWrapper.constructMapEntry(21, "Citrus", false),
	                null,
	                null,
	                null
	        });
	
	        LinearProbingHashMapWrapper<Integer, String> wrapper = new LinearProbingHashMapWrapper(map);
	        wrapper.forceSetSize(2);
	
	        // ------------------------------------------------------------
	        // If you're seeing this, you probably are not properly terminating
	        // your probing. See Piazza @624 for explanation.
	        // ------------------------------------------------------------
	        ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> map.remove(21), null);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 7, 14, 21, null, null, null}, new String[] {"Spartan", "Homestead", "Goldstrikers", "Citrus", null, null, null}, new boolean[] {false, false, true, false, false, false, false}, 2);
	
	    }
	}

	
	
	
	public static class ResizeTests {
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void resizeCaseSamePositions() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Homestead", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(3, "TKO", false),
	                null,
	                null,
	                null,
	        });
	
	        map.resizeBackingTable(15);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, 3, null, null, null, null, null, null, null, null, null, null, null}, new String[] {"Homestead", "Madtown", "Citrus", "TKO", null, null, null, null, null, null, null, null, null, null, null}, new boolean[15], 4);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void resizeCaseNewPositions() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Homestead", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(7, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(14, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(21, "TKO", false),
	                null,
	                null,
	                null,
	        });
	
	        map.resizeBackingTable(15);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, null, null, null, null, null, 21, 7, null, null, null, null, null, null, 14}, new String[] {"Homestead", null, null, "TKO", null, null, "TKO", "Madtown", null, null, null, null, null, null, "Citrus"}, new boolean[15], 4);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void resizeCaseWithCollisions() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Homestead", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(7, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(14, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(21, "TKO", false),
	                null,
	                null,
	                null,
	        });
	
	        map.resizeBackingTable(14);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 14, null, null, null, null, null, 7, 21, null, null, null, null, null}, new String[] {"Homestead", "Citrus", null, null, null, null, null, "Madtown", "TKO", null, null, null, null, null}, new boolean[14], 4);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void resizeCaseWithDelMarkers() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Homestead", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(7, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(14, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(21, "TKO", true),
	                null,
	                null,
	                null,
	        });
	
	        map.resizeBackingTable(14);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, null, null, null, null, null, null, 7, null, null, null, null, null, null}, new String[] {"Homestead", null, null, null, null, null, null, "Madtown", null, null, null, null, null, null}, new boolean[14], 2);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void resizeProbingLoops() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                null,
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Homestead", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(5, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(9, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(13, "TKO", false),
	                null,
	                null,
	                null,
	        });
	
	        map.resizeBackingTable(4);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {13, 1, 5, 9}, new String[] {"TKO", "Homestead", "Madtown", "Citrus"}, new boolean[4], 4);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void resizeProbingTooSmall() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                null,
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Homestead", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(5, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(9, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(13, "TKO", false)
	        });
	
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> map.resizeBackingTable(3), null);
	
	        AssertUtils.assertHashMapEquals(map, new Integer[] {null, 1, 5, 9, 13}, new String[] {null, "Homestead", "Madtown", "Citrus", "TKO"}, new boolean[5], 4);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void resizeCaseExceedsLoadFactor() {
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Homestead", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(1, "Madtown", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(3, "TKO", false)
	        });
	
	        map.resizeBackingTable(4);
	        AssertUtils.assertHashMapEquals(map, new Integer[] {0, 1, 2, 3}, new String[] {"Homestead", "Madtown", "Citrus", "TKO"}, 4);
	    }
	
	}

	
	
	
	
	public static class ValuesTests {
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void tableIsEmpty() {
	
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                null,
	                null,
	                null,
	                null,
	                null
	        });
	
	        List<String> list = new ArrayList<>();
	
	        AssertUtils.assertArrayEquals(list.toArray(), map.values().toArray());
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void tableHasValidValues() {
	
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Homestead", false),
	                null,
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Citrus", false),
	                null,
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Aragon", false),
	                null
	        });
	
	        List<String> list = new ArrayList<>();
	        list.add("Homestead");
	        list.add("Citrus");
	        list.add("Aragon");
	
	        AssertUtils.assertArrayEquals(list.toArray(), map.values().toArray());
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void tableHasDelMarker() {
	
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Homestead", false),
	                null,
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Citrus", true),
	                null,
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Aragon", false),
	                null
	        });
	
	        List<String> list = new ArrayList<>();
	        list.add("Homestead");
	        list.add("Aragon");
	
	        AssertUtils.assertArrayEquals(list.toArray(), map.values().toArray());
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void tableHasThreeDelMarkers() {
	
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Homestead", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Madtown", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Citrus", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "TKO", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Aragon", false),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Jump!", true)
	        });
	
	        List<String> list = new ArrayList<>();
	        list.add("Homestead");
	        list.add("Citrus");
	        list.add("Aragon");
	
	        AssertUtils.assertArrayEquals(list.toArray(), map.values().toArray());
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void tableHasOnlyDelMarkers() {
	
	        LinearProbingHashMap<Integer, String> map = LinearProbingHashMapWrapper.constructHashMap(new LinearProbingMapEntry[] {
	                LinearProbingMapEntryWrapper.constructMapEntry(0, "Homestead", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Madtown", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Citrus", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "TKO", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(4, "Aragon", true),
	                LinearProbingMapEntryWrapper.constructMapEntry(2, "Jump!", true)
	        });
	
	        List<String> list = new ArrayList<>();
	
	        AssertUtils.assertArrayEquals(list.toArray(), map.values().toArray());
	
	    }
	
	}















}



/**
 * @param <K> The generic used by the HashMap as a key.
 * @param <V> The generic used by the HashMap as a value.
 */
class LinearProbingHashMapWrapper<K, V> {
    private final ReflectionWrapper wrapper;

    public LinearProbingHashMapWrapper(LinearProbingHashMap<K, V> map) {
        this.wrapper = new ReflectionWrapper(map);
    }

    /**
     */
    public static <K, V> LinearProbingHashMap<K, V> constructHashMap(LinearProbingMapEntry<K, V>[] table) {
        try {
            Class<?> mapClass = LinearProbingHashMap.class;

            // Finds the correct (1-arg) constructor
            Constructor<LinearProbingHashMap<K, V>> constructor = null;

            for (Constructor<LinearProbingHashMap<K, V>> c : (Constructor<LinearProbingHashMap<K, V>>[]) mapClass.getDeclaredConstructors()) {
                if (c.getParameterTypes().length == 1) {
                    constructor = c;
                }
            }

            if (constructor == null) {
                throw new NoSuchMethodException("Could not find constructor!");
            }

            constructor.setAccessible(true);
            LinearProbingHashMap<K, V> newMap = constructor.newInstance(table.length);

            LinearProbingHashMapWrapper<K, V> newWrapper = new LinearProbingHashMapWrapper<>(newMap);
            newWrapper.forceSetTable(table);

            int size = 0;
            for (LinearProbingMapEntry<K, V> item : table) {
                if (item != null && ! (new LinearProbingMapEntryWrapper<>(item)).getRemoved()) {
                    size++;
                }
            }
            newWrapper.forceSetSize(size);

            return (LinearProbingHashMap<K, V>) newWrapper.wrapper.getInternalReference();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void forceSetSize(int size) {
        try {
            wrapper.setValue("size", size);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void forceSetTable(LinearProbingMapEntry<K,V>[] table) {
        try {
            wrapper.setValue("table", table);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}



/**
 * @param <K> The generic used by the HashMap as a key.
 * @param <V> The generic used by the HashMap as a value.
 */
class LinearProbingMapEntryWrapper<K, V> {
    private final ReflectionWrapper wrapper;

    public LinearProbingMapEntryWrapper(LinearProbingMapEntry<K, V> entry) {
        this.wrapper = new ReflectionWrapper(entry);
    }

    /**
     *
     * @throws NoSuchMethodException if the constructor was unable to be found
     * @throws InvocationTargetException N/A
     * @throws InstantiationException N/A
     * @throws IllegalAccessException N/A
     */
    public static <K, V> LinearProbingMapEntry<K, V> constructMapEntry(K key, V value, boolean isRemoved) {

        try {

            Class<?> mapClass = LinearProbingMapEntry.class;

            // Finds the correct (1-arg) constructor
            Constructor<LinearProbingMapEntry<K, V>> constructor = null;

            for (Constructor<LinearProbingMapEntry<K, V>> c : (Constructor<LinearProbingMapEntry<K, V>>[]) mapClass.getDeclaredConstructors()) {
                if (c.getParameterTypes().length == 2) {
                    constructor = c;
                }
            }

            if (constructor == null) {
                throw new NoSuchMethodException("Could not find constructor for MapEntry!");
            }

            constructor.setAccessible(true);
            LinearProbingMapEntryWrapper<K, V> newWrapper;

            if (key == null || value == null) {
                newWrapper = new LinearProbingMapEntryWrapper<>(null);
            } else {
                newWrapper = new LinearProbingMapEntryWrapper<>(constructor.newInstance(key, value));
            }


            newWrapper.forceSetRemoved(isRemoved);

            return (LinearProbingMapEntry<K, V>) newWrapper.wrapper.getInternalReference();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void forceSetKey(K key) {
        try {
            wrapper.setValue("key", key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void forceSetValue(V value) {
        try {
            wrapper.setValue("value", value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void forceSetRemoved(boolean removed) {
        try {
            wrapper.setValue("removed", removed);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getRemoved() {
        try {
            return (Boolean) wrapper.getFieldValue("removed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public K getKey() {
        try {
            return (K) wrapper.getFieldValue("key");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public V getValue() {
        try {
            return (V) wrapper.getFieldValue("value");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        if (wrapper.getInternalReference() == null) {
            return "null";
        }
        return (getRemoved() ? "DEL-" : "") + wrapper.getInternalReference().toString();
    }

    public boolean equals(Object o) {

        if (o instanceof LinearProbingMapEntryWrapper) {
            LinearProbingMapEntryWrapper otherWrapper = (LinearProbingMapEntryWrapper) o;
            return equals(otherWrapper.wrapper.getInternalReference());
        }

        if (wrapper.getInternalReference() == null) {
            return o == null;
        }

        return wrapper.getInternalReference().equals(o);
    }

}


/**
 * The main wrapper to encapsulate any object where Reflection is needed.
 * <p>
 * Without any boilerplate code Reflection can become exponentially more tedious
 * as the requirements scale, and this class aims to largely solve that issue.
 * <p>
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
                int max = Math.round((float) (Arrays.stream(expected).map(Object::toString).mapToInt(String::length).max().orElse(5) + 2) / 2) * 2;

                String expectedString = new ArrayDrawingBuilder(expected, max)
                        .withArrayVisualization()
                        .withLabel("Expected")
                        .build();

                ArrayDrawingBuilder actualBuilder = new ArrayDrawingBuilder(actual, max);


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

                fail(message);
            } catch (Exception e) {
                // Catch issues like the inputted array being null when it shouldn't be,
                // defaulting it to the normal JUnit exception message
                fail(error.getMessage());
            }
        }
    }

    public static <T> void assertListsEqual(List<T> expected, List<T> actual) {
        StringBuilder failMessage = new StringBuilder("Failed! ");

        if (expected.size() != actual.size()) {
            failMessage.append("Expected length " + expected.size() + " but recevived length " + actual.size() + ".");
        }

        for (int i = 0; i < expected.size(); i++) {
            if (expected.get(i) != actual.get(i)) {
                failMessage.append(String.format("\nLists differed at index %d. Expected %s, received %s.", i, expected.get(i).toString(), actual.get(i).toString()));
            }
        }
        if (!failMessage.toString().equals("Failed! ")) {
            fail(failMessage.toString());
        }
    }

    /**
     * Checks if the backing array of the given map is equal to the correct one
     * @param correctKeys The correct list of Keys for the backing array
     * @param correctValues The correct list of Values for the backing array
     * @param correctDELs The correct value of each DEL marker
     * @param size The expected size of the array
     * @param <K> The type of the HashMap's keys
     * @param <V> The type of the HashMap's entries
     */
    public static <K, V> void  assertHashMapEquals(LinearProbingHashMap<K, V> map, K[] correctKeys, V[] correctValues, boolean[] correctDELs, int size) {

        if (correctKeys.length != correctValues.length) {
            throw new IllegalArgumentException("The correct keys must be the same length as the correct values!");
        }

        try {
            LinearProbingMapEntryWrapper<K, V>[] correctArray = new LinearProbingMapEntryWrapper[correctKeys.length];

            for (int i = 0; i < correctKeys.length; ++i) {
                if (correctKeys[i] == null) {
                    correctArray[i] = new LinearProbingMapEntryWrapper<>(null);
                } else {
                    correctArray[i] = new LinearProbingMapEntryWrapper(LinearProbingMapEntryWrapper.constructMapEntry(correctKeys[i], correctValues[i], correctDELs != null && correctDELs[i]));
                }
            }

            LinearProbingMapEntryWrapper<K, V>[] receivedArrayOfWrapper = new LinearProbingMapEntryWrapper[map.getTable().length];
            LinearProbingMapEntry<K, V>[] table = map.getTable();
            for (int i = 0; i < table.length; ++i) {
                receivedArrayOfWrapper[i] = new LinearProbingMapEntryWrapper<>(table[i]);
            }

            AssertUtils.assertArrayEquals(correctArray, receivedArrayOfWrapper);
            assertEquals(size, map.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Checks if the backing array of the given map is equal to the correct one
     * @param correctKeys The correct list of Keys for the backing array
     * @param correctValues The correct list of Values for the backing array
     *
     * @param size The expected size of the array
     * @param <K> The type of the HashMap's keys
     * @param <V> The type of the HashMap's entries
     */
    public static <K, V> void  assertHashMapEquals(LinearProbingHashMap<K, V> map, K[] correctKeys, V[] correctValues, int size) {
        assertHashMapEquals(map, correctKeys, correctValues, new boolean[correctKeys.length], size);
    }


    /**
     * Helper method to turn any type array into an array of Strings by
     * calling the toString() method of each item in the array.
     * @param input The array (of any reference type) to convert to a String array.
     * @return The same items in the given array, in the same order, but as their String representations.
     * @param <T> The type of the array being passed in.
     */
    public static <T> String[] convertToStringArray (T[] input) {
        String[] arr = new String[input.length];
        for (int i = 0 ; i < input.length; ++i) {
            arr[i] = input[i] == null ? null : input[i].toString();
        }
        return arr;
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
            fail("Encountered a test that did not throw the expected expected of " + expected.getName());

        } catch (Exception e) {
            assertSame("checking if the thrown exception is what we expected", expected, e.getClass()); // If this line fails, the code threw the wrong exception (or threw no exception at all)
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

class CompressionUtils {

    public static String serializeCharList(Collection<?> charList) {
        int currentN = 0;

        StringBuilder builder = new StringBuilder();

        for (Object c : charList) {
            if (c == null) {
                currentN++;
            } else {
                if (currentN != 0) {
                    builder.append(currentN);
                    currentN = 0;
                }

                builder.append(c);
            }
        }

        if (currentN != 0) {
            builder.append(currentN);
        }

        return builder.toString();
    }

    /**
     * Turns a generic array into a Collection of Character that the method
     * serializeCharList(Collection) can accept.
     * @param charArray The array to turn into a Character array.
     * @return The serialized list.
     */
    public static String serializeCharList(Object[] charArray) {
        ArrayList<Character> collection = new ArrayList<>();
        for (Object item : charArray) {
            collection.add(item == null ? null : item.toString().charAt(0));
        }
        return serializeCharList(collection);
    }

    public static String decompressString(String str) throws IOException {
        byte[] byteArr = Base64.getDecoder().decode(str);

        GZIPInputStream bais = new GZIPInputStream(new ByteArrayInputStream(byteArr));

        BufferedReader br = new BufferedReader(new InputStreamReader(bais, StandardCharsets.UTF_8));

        String line;

        StringBuilder output = new StringBuilder();

        while ((line = br.readLine()) != null) {
            output.append(line).append("\n");
        }

        return output.toString();
    }


    public static ArrayList<Character> compressedStringToCharList(String str) {
        StringBuilder numBuilder = new StringBuilder();

        ArrayList<Character> charList = new ArrayList<>();

        for (char c: str.toCharArray()) {
            if (Character.isLetter(c)) {
                if (numBuilder.length() > 0) {
                    int nullElements = Integer.parseInt(numBuilder.toString());

                    for (int i = 0; i < nullElements; i++) {
                        charList.add(null);
                    }

                    numBuilder.setLength(0);
                }

                charList.add(c);
            } else if (Character.isDigit(c)) {
                numBuilder.append(c);
            }
        }

        if (numBuilder.length() != 0) {
            int nullElements = Integer.parseInt(numBuilder.toString());

            for (int j = 0; j < nullElements; j++) {
                charList.add(null);
            }
        }


        return charList;
    }
}

final class TestConfiguration {

    /**
     * Set this boolean to TRUE if you want the TREE visualizations.
     * Set this to FALSE if you want the ARRAY visualizations.
     */
    public static final boolean USE_TREE_DRAWINGS = true;

    /**
     * Set this to the desired timeout (in milliseconds).
     */
    public static final int TIMEOUT = 1000;
}



class ArrayDrawingBuilder {
    private static final int ARROW_SIZE = 1;

    private static final int LABEL_WHITESPACE = 10;

    private final Object[] objects;

    private String finalOutput = "";

    private String label;

    private int[] highlightedIndices;

    private int padding;

    public ArrayDrawingBuilder(Object[] objects, int padding) {
        this.objects = Arrays.stream(objects).map(o -> o == null ? "null" : o).toArray();
        this.padding = padding;
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

        int insertIndex = 2;

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
        return elementIndex * padding + (padding / 2);
    }

    public String getArrayString(Object[] arr) {
        StringBuilder start = new StringBuilder("\n");

        int topLength = arr.length;

        for (Object o : arr) {
            topLength += padding;
        }

        topLength -= 1;

//        start.append(createLine(topLength));


        StringBuilder middle = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            middle.append("|");

            boolean highlighted = false;

            if (highlightedIndices != null) {
                for (int highlightedIndex: highlightedIndices) {
                    if (i == highlightedIndex) {
                        highlighted = true;
                        break;
                    }
                }
            }

            int addedPadding = (padding - arr[i].toString().length()) / 2;

            middle.append(" ".repeat(addedPadding));


            if (highlighted) {
                middle.append(ColorUtils.formatColorString(AsciiColorCode.RED_BACKGROUND, AsciiColorCode.BLACK_FOREGROUND, arr[i].toString()));
            } else {
                middle.append(arr[i].toString());
            }

            middle.append(" ".repeat(addedPadding));

        }

        middle.append("|");

        start.append(createLine(getRawStringLength(middle)));
        start.append(middle).append("\n");
        start.append(createLine(getRawStringLength(middle)));

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

//        start.append(createLine(topLength));


        return start.toString();
    }

    private static String createLine(int topLength) {

        return "+" + "-".repeat(Math.max(0, topLength - 2)) +
                "+" +
                "\n";
    }

    private static int getRawStringLength(StringBuilder str) {
        return str.toString().replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -/]*[@-~]", "").length();
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

class TreeDrawingTool {
    
    private static char[][] canvas;
    private static int[][] coords;

    /**
     * Generates tree, ignoring the leading null
     */
    public static String[] generateTree(String[] args, boolean ignoreLeadingItem) {
        if (!ignoreLeadingItem)
            return generateTree(args);

        String[] withoutLeadingNull = new String[args.length];
        for (int i = 0; i < args.length - 1; ++i) {
            withoutLeadingNull[i] = args[i + 1];
        }
        return generateTree(withoutLeadingNull);
    }

    /**
     * Each arg should be length 3
     */
    public static String[] generateTree(String[] args) {

        if (args.length == 0) {
            return new String[] {
                    "(No tree drawn, since there were no items in the tree!"
            };
        }

        // Format the args
        for (int i = 0; i < args.length; i++) {
            if (!stringIsValid(args[i])) {
                args[i] = "no";
            } else if (args[i].length() > 3) {
                args[i] = args[i].substring(0, 3);
            } else if (args[i].length() == 0) {
                args[i] = "---";
            } else if (args[i].length() == 1) {
                args[i] = args[i];
            } else if (args[i].length() == 2) {
                args[i] = args[i] + ' ';
            }
        }

        // calculate size of canvas
        int height = (int) (Math.log(args.length) / Math.log(2)) + 1; // number of rows of data
        int canvasHeight = (height * 3) + (height - 1) * 4; // Each box is 3 tall. Each gap betwen rows is 3 tall.
        int canvasWidth = (1 << (height-1)) * 8 - 1;

        //initialize canvas
        canvas = new char[canvasHeight][];
        for (int r = 0; r < canvasHeight; ++r) {
            char[] row = new char[canvasWidth];
            for (int c = 0; c < canvasWidth; ++c) {
                row[c] = ' ';
            }
            canvas[r] = row;
        }

        // Initialize list of coords
        coords = new int[args.length][];

        // Draws all items in canvas
        int currentRow = 1;
        for (int i = 0; i < args.length; i++) {
            if (((i+1) & i) == 0 && i > 0) { //checks if number is a power of 2
                currentRow += 7;
            }
            int itemsOnThisRow = 1 << ((currentRow - 1) / 7);
            int widthPerItem = (canvasWidth / itemsOnThisRow) + 1;
            int col = (int) (((i + 1 - itemsOnThisRow) + 0.5) * widthPerItem) - 1;
            drawItemAt(currentRow, col, args[i]);
            coords[i] = new int[] {currentRow, col, stringIsValid(args[i]) ? 1 : 0};
        }

        // Draws all arrows
        for (int i = 0; i <= args.length/2 - 1; ++i) {
            int childOneIndex = (i << 1) + 1;
            int childTwoIndex = (i + 1) << 1;

            if (childOneIndex < coords.length)
                drawArrow(coords[i], coords[childOneIndex]);
            if (childTwoIndex < coords.length)
                drawArrow(coords[i], coords[childTwoIndex]);
        }

        // Returns the canvas
        return getCanvas();
    }

    private static void drawArrow(int[] start, int[] end) {
        if (start[2] != 0 && end[2] != 0) { // If it starts at invalid location, don't draw arrow
            setCharAt(start[0] + 1, start[1] + (start[1] > end[1] ? -1 : 1), '+');
            setCharAt(start[0] + 2, start[1] + (start[1] > end[1] ? -1 : 1), '|');
            setCharAt(end[0] - 3, end[1] + (start[1] > end[1] ? 1 : -1), '|');
            setCharAt(end[0] - 2, end[1] + (start[1] > end[1] ? 1 : -1), 'V');

            setCharAt(start[0] + 3, Math.max(start[1], end[1]) - 1, '+');
            setCharAt(start[0] + 3, Math.min(start[1], end[1]) + 1, '+');
            for (int c = Math.min(start[1], end[1]) + 2; c < Math.max(end[1], start[1]) - 1; ++c) {
                setCharAt(start[0] + 3, c, '-');
            }
        }
    }
    
    private static String[] getCanvas() {
        String[] rowStrings = new String[canvas.length];

        int skipColumns = 0;

        outerLoop:
        while(skipColumns < canvas[0].length) {
            for (int r = 0; r < canvas.length; ++r) {
                if (canvas[r][skipColumns] != ' ') {
                    break outerLoop;
                }
            }
            ++skipColumns;
        }

        for (int r = 0; r < rowStrings.length; ++r) {
            StringBuilder row = new StringBuilder();
            for (int c = skipColumns; c < canvas[r].length; ++c) {
                row.append(canvas[r][c]);
            }

            rowStrings[r] = row.toString();
        }

        return rowStrings;
    }

    public static void printCanvas(String[] rows) {
        for (String row : rows) {
            System.out.println(row);
        }
    }
    

    /**
     * Row and col denote the CENTER of each box.
     * Item must be exactly 3 characters wide!
     */
    private static void drawItemAt(int row, int col, String item) {

        if (!stringIsValid(item)) return;
        
//        drawLine(row - 1, col - 1, row - 1, col + 1);
//        drawLine(row - 1, col - 1, row + 1, col - 1);
//        drawLine(row + 1, col - 1, row + 1, col + 1);
//        drawLine(row - 1, col + 1, row + 1, col + 1);
        for (int i = 0; i < item.length(); i++) {
            setCharAt(row, col + i, item.charAt(i));
        }
    }

    /**
     * Draws a line at the given two points.
     */
    private static void drawLine(int r1, int c1, int r2, int c2) {
        if (r1 == r2) {
            for (int c = Math.min(c1, c2); c < Math.max(c1, c2); ++c) {
                setCharAt(r1, c, '-');
            }
        } else {
            for (int r = Math.min(r1, r2); r < Math.max(r1, r2); ++r) {
                setCharAt(r, c1, '|');
            }
        }
        setCharAt(r1, c1, '+');
        setCharAt(r2, c2, '+');
    }
    

    /**
     * Sets the canvas's char at that location to be the given char. If
     * the given coordinates are invalid, then this method does nothing.
     */
    private static void setCharAt(int row, int col, char character) {
        if (row >= 0 && row < canvas.length && col >= 0 && col < canvas[0].length) {
            canvas[row][col] = character;
        }
    }

    private static boolean stringIsValid(String str) {
        return !(str == null || str.equalsIgnoreCase("no") || str.equalsIgnoreCase("null"));
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