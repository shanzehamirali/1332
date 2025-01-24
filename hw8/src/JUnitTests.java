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
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import javax.print.attribute.IntegerSyntax;
import java.util.Comparator;
import java.io.PrintStream;
import org.junit.Assert;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.Assert.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.GZIPInputStream;
@RunWith(Enclosed.class)
public class JUnitTests {
	
	
	
	/**
	 * Test cases for CocktailSort. This should be
	 * - in-place
	 * - stable
	 * - adaptive
	 */
	public static class CocktailSortTests {
	
	    CountComparator<IntegerWrapper> comparator;
	
	    @Before
	    public void setup() {
	        comparator = new CountComparator<>();
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void nullArray() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class,
	                () -> Sorting.cocktailSort(null, comparator));
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void nullComparator() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(6, 7, 0);
	        ArrayDrawingBuilder.printInitialArray(arr);
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class,
	                () -> Sorting.cocktailSort(null, comparator));
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void regularSorting() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(6, 7, 0, 2, 5, 4, 1, 8, 9, 3);
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.cocktailSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(IntegerWrapper.makeArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), arr);
	        AssertUtils.checkComparisons(comparator, 29);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void stabilityTest() {
	        IntegerWrapper[] arr = new IntegerWrapper[] {
	                new IntegerWrapper(6, "L"),
	                new IntegerWrapper(7, "L"),
	                new IntegerWrapper(0, "L"),
	                new IntegerWrapper(2),
	                new IntegerWrapper(5),
	                new IntegerWrapper(4),
	                new IntegerWrapper(6, "R"),
	                new IntegerWrapper(7, "R"),
	                new IntegerWrapper(0, "R")
	        };
	
	        IntegerWrapper[] correctArr = new IntegerWrapper[] {
	                new IntegerWrapper(0, "L"),
	                new IntegerWrapper(0, "R"),
	                new IntegerWrapper(2),
	                new IntegerWrapper(4),
	                new IntegerWrapper(5),
	                new IntegerWrapper(6, "L"),
	                new IntegerWrapper(6, "R"),
	                new IntegerWrapper(7, "L"),
	                new IntegerWrapper(7, "R"),
	        };
	
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.cocktailSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(correctArr, arr);
	        AssertUtils.checkComparisons(comparator, 24);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void bestCaseScenario() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.cocktailSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(IntegerWrapper.makeArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), arr);
	        AssertUtils.checkComparisons(comparator, 9);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void worstCaseScenario() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.cocktailSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(IntegerWrapper.makeArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), arr);
	        AssertUtils.checkComparisons(comparator, 45);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void stabilityWithAllDuplicates() {
	        IntegerWrapper[] arr = new IntegerWrapper[] {
	                new IntegerWrapper(7, "A"),
	                new IntegerWrapper(7, "B"),
	                new IntegerWrapper(7, "C"),
	                new IntegerWrapper(7, "D"),
	                new IntegerWrapper(7, "E"),
	                new IntegerWrapper(7, "F"),
	                new IntegerWrapper(7, "G"),
	                new IntegerWrapper(7, "H"),
	        };
	
	        IntegerWrapper[] correctArr = new IntegerWrapper[] {
	                new IntegerWrapper(7, "A"),
	                new IntegerWrapper(7, "B"),
	                new IntegerWrapper(7, "C"),
	                new IntegerWrapper(7, "D"),
	                new IntegerWrapper(7, "E"),
	                new IntegerWrapper(7, "F"),
	                new IntegerWrapper(7, "G"),
	                new IntegerWrapper(7, "H"),
	        };
	
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.cocktailSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(correctArr, arr);
	        AssertUtils.checkComparisons(comparator, 7);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void bruteForceTests() {
	        ArrayList<IntegerWrapper> list = new ArrayList<>();
	        list.add(new IntegerWrapper(0));
	        list.add(new IntegerWrapper(1));
	        list.add(new IntegerWrapper(2));
	        list.add(new IntegerWrapper(3));
	        list.add(new IntegerWrapper(4));
	        list.add(new IntegerWrapper(5));
	        list.add(new IntegerWrapper(6));
	        list.add(new IntegerWrapper(7));
	
	        ArrayList<ArrayList<IntegerWrapper>> permutations = BruteForceUtils.generatePermutations(list);
	
	        long totalComparisons = 0;
	        long numTests = 0;
	
	        for (ArrayList<IntegerWrapper> permutation : permutations) {
	            numTests++;
	            IntegerWrapper[] arr = permutation.toArray(new IntegerWrapper[0]);
	
	            Sorting.cocktailSort(arr, comparator);
	
	            try {
	                AssertUtils.assertArrayEquals(list.toArray(new IntegerWrapper[0]), arr);
	            } catch (Throwable e) {
	                System.out.println(e.getMessage());
	                System.out.println("");
	            }
	
	
	            totalComparisons += comparator.getComparisonCount();
	            comparator.resetComparisonCount();
	
	            if (numTests % 10000 == 0) {
	                System.out.printf("Ran %,d tests...\n", numTests);
	            }
	        }
	
	        AssertUtils.checkTotalComparisons(879606, totalComparisons);
	
	        System.out.println(ColorUtils.formatColorString(
	                AsciiColorCode.BRIGHT_WHITE_BACKGROUND,
	                AsciiColorCode.BRIGHT_BLACK_FOREGROUND,
	                "NOTE: The CocktailSort brute-force efficiency check only checks the total\n" +
	                        "number of comparisons done in all test cases. If one particular case runs\n" +
	                        "with fewer comparisons, but another case runs with too many comparisons,\n" +
	                        "it will not change the total that is checked here."
	        ));
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void testWithStrings() {
	        CountComparator<String> stringComparator = new CountComparator<>();
	
	        String[] arr = new String[] {"Homestead", "Cobras", "Astraea", "Midknight", "MORT", "Aragon"};
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.cocktailSort(arr, stringComparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(new String[] {"Aragon", "Astraea", "Cobras", "Homestead", "MORT", "Midknight"}, arr);
	        AssertUtils.checkComparisons(stringComparator, 12);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void sortWithOneNegative() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(4, 3, 1, -2, 5, 7, 6);
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.cocktailSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(IntegerWrapper.makeArray(-2, 1, 3, 4, 5, 6, 7), arr);
	        AssertUtils.checkComparisons(comparator, 15);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void reallyBigArray() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(1678, 254, 1706, 176, 6329, 1323, 5940, 1756, 1731, 2481, 27, 1796, 2521, 6328, 3467, 2910, 359, 3005, 2468, 180, 2974, 2767, 4414, 118, 604, 5895, 1771, 114, 2046, 7457, 4391, 368, 3847, 3173, 6672, 5687, 5907, 5414, 5712, 125, 7769, 78, 6036, 1699, 148, 449, 1727, 67, 3663, 3357, 1778, 325, 5436, 581, 1732, 4272, 7174, 1591, 9084, 6424, 1261, 5114, 3070, 1987, 341, 2075, 9496, 360, 179, 5419, 4522, 85, 2611, 1501, 6090, 3314, 353, 876, 1477, 4043, 2539, 2338, 4362, 103, 1629, 461, 9408, 346, 1468, 498, 3604, 1294, 3015, 4738, 4143, 3572, 88, 133, 9072, 3128, 33, 2054, 292, 3647, 2485, 4779, 1787, 177, 5152, 1025, 6081, 8044, 3061, 3937, 4909, 4270, 5817, 7056, 2877, 870, 2713, 1153, 59, 1922, 1250, 4488, 7407, 5557, 7426, 3729, 841, 1058, 2522, 2067, 1807, 2992, 6800, 1923, 4795, 999, 1676, 379, 1168, 3641, 3245, 3175, 1730, 6995, 1076, 58, 4028, 316, 2811, 1714, 8033, 2607, 5084, 131, 1768, 1740, 2357, 687, 3546, 1986, 1318, 166, 8608, 4728, 3136, 144, 9752, 5813, 624, 4230, 4646, 3201, 108, 2252, 3276, 686, 492, 1741, 4499, 2959, 3310, 2194, 2102, 987, 1540, 1296, 857, 5675, 2590, 5010, 829, 3538, 1718, 2106, 3322, 4237, 5804, 868, 5460, 5736, 4564, 694, 245, 3501, 343, 216, 1405, 236, 4828, 8513, 3419, 4998, 6147, 1410, 3256, 8727, 5144, 6619, 5827, 2930, 4607, 2586, 840, 4698, 670);
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.cocktailSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(IntegerWrapper.makeArray(27,  33,  58,  59,  67,  78,  85,  88,  103, 108, 114, 118, 125, 131, 133, 144, 148, 166, 176, 177, 179, 180, 216, 236, 245, 254, 292, 316, 325, 341, 343, 346, 353, 359, 360, 368, 379, 449, 461, 492, 498, 581, 604, 624, 670, 686, 687, 694, 829, 840, 841, 857, 868, 870, 876, 987, 999, 1025,1058,1076,1153,1168,1250,1261,1294,1296,1318,1323,1405,1410,1468,1477,1501,1540,1591,1629,1676,1678,1699,1706,1714,1718,1727,1730,1731,1732,1740,1741,1756,1768,1771,1778,1787,1796,1807,1922,1923,1986,1987,2046,2054,2067,2075,2102,2106,2194,2252,2338,2357,2468,2481,2485,2521,2522,2539,2586,2590,2607,2611,2713,2767,2811,2877,2910,2930,2959,2974,2992,3005,3015,3061,3070,3128,3136,3173,3175,3201,3245,3256,3276,3310,3314,3322,3357,3419,3467,3501,3538,3546,3572,3604,3641,3647,3663,3729,3847,3937,4028,4043,4143,4230,4237,4270,4272,4362,4391,4414,4488,4499,4522,4564,4607,4646,4698,4728,4738,4779,4795,4828,4909,4998,5010,5084,5114,5144,5152,5414,5419,5436,5460,5557,5675,5687,5712,5736,5804,5813,5817,5827,5895,5907,5940,6036,6081,6090,6147,6328,6329,6424,6619,6672,6800,6995,7056, 7174, 7407, 7426, 7457, 7769, 8033, 8044, 8513, 8608, 8727, 9072, 9084, 9408, 9496, 9752), arr);
	        AssertUtils.checkComparisons(comparator, 16980);
	    }
	
	}

	
	
	
	
	/**
	 * Test cases for kth-select. This should be an adaptive, in-place algorithm.
	 */
	public static class KthSelectTests {
	
	    CountComparator<IntegerWrapper> comparator;
	    Random rand;
	
	    @Before
	    public void setup() {
	        comparator = new CountComparator<>();
	        rand = new FakeRandom();
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void nullArray() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class,
	                () -> Sorting.kthSelect(1, null, comparator, rand));
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void nullComparator() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(6, 7, 0);
	        ArrayDrawingBuilder.printInitialArray(arr);
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class,
	                () -> Sorting.kthSelect(1, arr, null, rand));
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void nullRand() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(6, 7, 0);
	        ArrayDrawingBuilder.printInitialArray(arr);
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class,
	                () -> Sorting.kthSelect(1, arr, comparator, null));
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void invalidIndices() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(6, 7, 0);
	        ArrayDrawingBuilder.printInitialArray(arr);
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class,
	                () -> Sorting.kthSelect(-1, arr, comparator, rand));
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class,
	                () -> Sorting.kthSelect(0, arr, comparator, rand));
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class,
	                () -> Sorting.kthSelect(4, arr, comparator, rand));
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class,
	                () -> Sorting.kthSelect(5, arr, comparator, rand));
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void regularUseCase() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(6, 7, 0, 2, 5, 4, 1, 8, 9, 3);
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        assertEquals(new IntegerWrapper(2), Sorting.kthSelect(3, arr, comparator, rand));
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(IntegerWrapper.makeArray(0, 1, 2, 3, 5, 4, 7, 6, 8, 9), arr);
	        AssertUtils.checkComparisons(comparator, 21);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void preSortedArray() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        assertEquals(new IntegerWrapper(2), Sorting.kthSelect(3, arr, comparator, rand));
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(IntegerWrapper.makeArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), arr);
	        AssertUtils.checkComparisons(comparator, 24);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void reverseSortedArray() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        assertEquals(new IntegerWrapper(3), Sorting.kthSelect(4, arr, comparator, rand));
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(IntegerWrapper.makeArray(1, 0, 2, 3, 4, 5, 6, 9, 7, 8), arr);
	        AssertUtils.checkComparisons(comparator, 19);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void randomArrays() {
	
	        IntegerWrapper[][] arrays = new IntegerWrapper[][] {
	                IntegerWrapper.makeArray(6, 8, 4, 7, 5, 9, 1, 2, 0, 3),
	                IntegerWrapper.makeArray(3, 2, 7, 6, 1, 4, 9, 8, 5, 0),
	                IntegerWrapper.makeArray(3, 7, 2, 6, 4, 1, 9, 0, 8, 5),
	                IntegerWrapper.makeArray(5, 2, 3, 7, 1, 9, 6, 8, 0, 4),
	                IntegerWrapper.makeArray(7, 2, 3, 4, 1, 6, 9, 8, 0, 5)
	        };
	
	        int[] comparisons = new int[] {25, 20, 35, 33, 24};
	
	        for (int i = 0; i < arrays.length; ++i) {
	
	            this.rand = new FakeRandom();
	            comparator.resetComparisonCount();
	
	            IntegerWrapper[] arr = arrays[i];
	
	            assertEquals(new IntegerWrapper(4), Sorting.kthSelect(5, arr, comparator, rand));
	
	            AssertUtils.checkComparisons(comparator, comparisons[i]);
	
	        }
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void reallyBigArray() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(1678, 254, 1706, 176, 6329, 1323, 5940, 1756, 1731, 2481, 27, 1796, 2521, 6328, 3467, 2910, 359, 3005, 2468, 180, 2974, 2767, 4414, 118, 604, 5895, 1771, 114, 2046, 7457, 4391, 368, 3847, 3173, 6672, 5687, 5907, 5414, 5712, 125, 7769, 78, 6036, 1699, 148, 449, 1727, 67, 3663, 3357, 1778, 325, 5436, 581, 1732, 4272, 7174, 1591, 9084, 6424, 1261, 5114, 3070, 1987, 341, 2075, 9496, 360, 179, 5419, 4522, 85, 2611, 1501, 6090, 3314, 353, 876, 1477, 4043, 2539, 2338, 4362, 103, 1629, 461, 9408, 346, 1468, 498, 3604, 1294, 3015, 4738, 4143, 3572, 88, 133, 9072, 3128, 33, 2054, 292, 3647, 2485, 4779, 1787, 177, 5152, 1025, 6081, 8044, 3061, 3937, 4909, 4270, 5817, 7056, 2877, 870, 2713, 1153, 59, 1922, 1250, 4488, 7407, 5557, 7426, 3729, 841, 1058, 2522, 2067, 1807, 2992, 6800, 1923, 4795, 999, 1676, 379, 1168, 3641, 3245, 3175, 1730, 6995, 1076, 58, 4028, 316, 2811, 1714, 8033, 2607, 5084, 131, 1768, 1740, 2357, 687, 3546, 1986, 1318, 166, 8608, 4728, 3136, 144, 9752, 5813, 624, 4230, 4646, 3201, 108, 2252, 3276, 686, 492, 1741, 4499, 2959, 3310, 2194, 2102, 987, 1540, 1296, 857, 5675, 2590, 5010, 829, 3538, 1718, 2106, 3322, 4237, 5804, 868, 5460, 5736, 4564, 694, 245, 3501, 343, 216, 1405, 236, 4828, 8513, 3419, 4998, 6147, 1410, 3256, 8727, 5144, 6619, 5827, 2930, 4607, 2586, 840, 4698, 670);
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        assertEquals(new IntegerWrapper(670), Sorting.kthSelect(45, arr, comparator, rand));
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.checkComparisons(comparator, 513);
	    }
	
	}

	
	
	
	/**
	 * Test cases for MergeSort. This should be
	 * - out-of-place
	 * - stable
	 * - non-adaptive
	 */
	public static class MergeSortTests {
	
	    CountComparator<IntegerWrapper> comparator;
	
	    @Before
	    public void setup() {
	        comparator = new CountComparator<>();
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void nullArray() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class,
	                () -> Sorting.mergeSort(null, comparator));
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void nullComparator() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(6, 7, 0);
	        ArrayDrawingBuilder.printInitialArray(arr);
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class,
	                () -> Sorting.mergeSort(null, comparator));
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void regularSorting() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(6, 7, 0, 2, 5, 4, 1, 8, 9, 3);
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.mergeSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(IntegerWrapper.makeArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), arr);
	        AssertUtils.checkComparisons(comparator, 21);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void stabilityTest() {
	        IntegerWrapper[] arr = new IntegerWrapper[] {
	                new IntegerWrapper(6, "L"),
	                new IntegerWrapper(7, "L"),
	                new IntegerWrapper(0, "L"),
	                new IntegerWrapper(2),
	                new IntegerWrapper(5),
	                new IntegerWrapper(4),
	                new IntegerWrapper(6, "R"),
	                new IntegerWrapper(7, "R"),
	                new IntegerWrapper(0, "R")
	        };
	
	        IntegerWrapper[] correctArr = new IntegerWrapper[] {
	                new IntegerWrapper(0, "L"),
	                new IntegerWrapper(0, "R"),
	                new IntegerWrapper(2),
	                new IntegerWrapper(4),
	                new IntegerWrapper(5),
	                new IntegerWrapper(6, "L"),
	                new IntegerWrapper(6, "R"),
	                new IntegerWrapper(7, "L"),
	                new IntegerWrapper(7, "R"),
	        };
	
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.mergeSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(correctArr, arr);
	        AssertUtils.checkComparisons(comparator, 19);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void bestCaseScenario() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.mergeSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(IntegerWrapper.makeArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), arr);
	        AssertUtils.checkComparisons(comparator, 15);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void worstCaseScenario() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.mergeSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(IntegerWrapper.makeArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), arr);
	        AssertUtils.checkComparisons(comparator, 19);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void stabilityWithAllDuplicates() {
	        IntegerWrapper[] arr = new IntegerWrapper[] {
	                new IntegerWrapper(7, "A"),
	                new IntegerWrapper(7, "B"),
	                new IntegerWrapper(7, "C"),
	                new IntegerWrapper(7, "D"),
	                new IntegerWrapper(7, "E"),
	                new IntegerWrapper(7, "F"),
	                new IntegerWrapper(7, "G"),
	                new IntegerWrapper(7, "H"),
	        };
	
	        IntegerWrapper[] correctArr = new IntegerWrapper[] {
	                new IntegerWrapper(7, "A"),
	                new IntegerWrapper(7, "B"),
	                new IntegerWrapper(7, "C"),
	                new IntegerWrapper(7, "D"),
	                new IntegerWrapper(7, "E"),
	                new IntegerWrapper(7, "F"),
	                new IntegerWrapper(7, "G"),
	                new IntegerWrapper(7, "H"),
	        };
	
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.mergeSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(correctArr, arr);
	        AssertUtils.checkComparisons(comparator, 12);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void bruteForceTests() {
	        ArrayList<IntegerWrapper> list = new ArrayList<>();
	        list.add(new IntegerWrapper(0));
	        list.add(new IntegerWrapper(1));
	        list.add(new IntegerWrapper(2));
	        list.add(new IntegerWrapper(3));
	        list.add(new IntegerWrapper(4));
	        list.add(new IntegerWrapper(5));
	        list.add(new IntegerWrapper(6));
	        list.add(new IntegerWrapper(7));
	
	        ArrayList<ArrayList<IntegerWrapper>> permutations = BruteForceUtils.generatePermutations(list);
	
	        long totalComparisons = 0;
	        long numTests = 0;
	
	        for (ArrayList<IntegerWrapper> permutation : permutations) {
	            numTests++;
	            IntegerWrapper[] arr = permutation.toArray(new IntegerWrapper[0]);
	
	            Sorting.mergeSort(arr, comparator);
	
	            try {
	                AssertUtils.assertArrayEquals(list.toArray(new IntegerWrapper[0]), arr);
	            } catch (Throwable e) {
	                System.out.println(e.getMessage());
	                System.out.println("");
	            }
	
	
	            totalComparisons += comparator.getComparisonCount();
	            comparator.resetComparisonCount();
	
	            if (numTests % 10000 == 0) {
	                System.out.printf("Ran %,d tests...\n", numTests);
	            }
	        }
	
	        AssertUtils.checkTotalComparisons(634368, totalComparisons);
	
	        System.out.println(ColorUtils.formatColorString(
	                AsciiColorCode.BRIGHT_WHITE_BACKGROUND,
	                AsciiColorCode.BRIGHT_BLACK_FOREGROUND,
	                "NOTE: The MergeSort brute-force efficiency check only checks the total\n" +
	                        "number of comparisons done in all test cases. If one particular case runs\n" +
	                        "with fewer comparisons, but another case runs with too many comparisons,\n" +
	                        "it will not change the total that is checked here."
	        ));
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void testWithStrings() {
	        CountComparator<String> stringComparator = new CountComparator<>();
	        String[] arr = new String[] {"Homestead", "Cobras", "Astraea", "Midknight", "MORT", "Aragon"};
	        ArrayDrawingBuilder.printInitialArray(arr);
	        Sorting.mergeSort(arr, stringComparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	        AssertUtils.assertArrayEquals(new String[] {"Aragon", "Astraea", "Cobras", "Homestead", "MORT", "Midknight"}, arr);
	        AssertUtils.checkComparisons(stringComparator, 10);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void sortWithOneNegative() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(4, 3, 1, -2, 5, 7, 6);
	        ArrayDrawingBuilder.printInitialArray(arr);
	        Sorting.mergeSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	        AssertUtils.assertArrayEquals(IntegerWrapper.makeArray(-2, 1, 3, 4, 5, 6, 7), arr);
	        AssertUtils.checkComparisons(comparator, 11);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void reallyBigArray() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(1678, 254, 1706, 176, 6329, 1323, 5940, 1756, 1731, 2481, 27, 1796, 2521, 6328, 3467, 2910, 359, 3005, 2468, 180, 2974, 2767, 4414, 118, 604, 5895, 1771, 114, 2046, 7457, 4391, 368, 3847, 3173, 6672, 5687, 5907, 5414, 5712, 125, 7769, 78, 6036, 1699, 148, 449, 1727, 67, 3663, 3357, 1778, 325, 5436, 581, 1732, 4272, 7174, 1591, 9084, 6424, 1261, 5114, 3070, 1987, 341, 2075, 9496, 360, 179, 5419, 4522, 85, 2611, 1501, 6090, 3314, 353, 876, 1477, 4043, 2539, 2338, 4362, 103, 1629, 461, 9408, 346, 1468, 498, 3604, 1294, 3015, 4738, 4143, 3572, 88, 133, 9072, 3128, 33, 2054, 292, 3647, 2485, 4779, 1787, 177, 5152, 1025, 6081, 8044, 3061, 3937, 4909, 4270, 5817, 7056, 2877, 870, 2713, 1153, 59, 1922, 1250, 4488, 7407, 5557, 7426, 3729, 841, 1058, 2522, 2067, 1807, 2992, 6800, 1923, 4795, 999, 1676, 379, 1168, 3641, 3245, 3175, 1730, 6995, 1076, 58, 4028, 316, 2811, 1714, 8033, 2607, 5084, 131, 1768, 1740, 2357, 687, 3546, 1986, 1318, 166, 8608, 4728, 3136, 144, 9752, 5813, 624, 4230, 4646, 3201, 108, 2252, 3276, 686, 492, 1741, 4499, 2959, 3310, 2194, 2102, 987, 1540, 1296, 857, 5675, 2590, 5010, 829, 3538, 1718, 2106, 3322, 4237, 5804, 868, 5460, 5736, 4564, 694, 245, 3501, 343, 216, 1405, 236, 4828, 8513, 3419, 4998, 6147, 1410, 3256, 8727, 5144, 6619, 5827, 2930, 4607, 2586, 840, 4698, 670);
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.mergeSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(IntegerWrapper.makeArray(27,  33,  58,  59,  67,  78,  85,  88,  103, 108, 114, 118, 125, 131, 133, 144, 148, 166, 176, 177, 179, 180, 216, 236, 245, 254, 292, 316, 325, 341, 343, 346, 353, 359, 360, 368, 379, 449, 461, 492, 498, 581, 604, 624, 670, 686, 687, 694, 829, 840, 841, 857, 868, 870, 876, 987, 999, 1025,1058,1076,1153,1168,1250,1261,1294,1296,1318,1323,1405,1410,1468,1477,1501,1540,1591,1629,1676,1678,1699,1706,1714,1718,1727,1730,1731,1732,1740,1741,1756,1768,1771,1778,1787,1796,1807,1922,1923,1986,1987,2046,2054,2067,2075,2102,2106,2194,2252,2338,2357,2468,2481,2485,2521,2522,2539,2586,2590,2607,2611,2713,2767,2811,2877,2910,2930,2959,2974,2992,3005,3015,3061,3070,3128,3136,3173,3175,3201,3245,3256,3276,3310,3314,3322,3357,3419,3467,3501,3538,3546,3572,3604,3641,3647,3663,3729,3847,3937,4028,4043,4143,4230,4237,4270,4272,4362,4391,4414,4488,4499,4522,4564,4607,4646,4698,4728,4738,4779,4795,4828,4909,4998,5010,5084,5114,5144,5152,5414,5419,5436,5460,5557,5675,5687,5712,5736,5804,5813,5817,5827,5895,5907,5940,6036,6081,6090,6147,6328,6329,6424,6619,6672,6800,6995,7056, 7174, 7407, 7426, 7457, 7769, 8033, 8044, 8513, 8608, 8727, 9072, 9084, 9408, 9496, 9752), arr);
	        AssertUtils.checkComparisons(comparator, 1517);
	    }
	
	}
	
	
	public static class RadixSortTests {
	
	    @Test
	    public void nullArray() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> Sorting.lsdRadixSort(null));
	    }
	
	    @Test
	    public void typicalUseCase() {
	        int[] arr = new int[] {-50, -40, 23, 25, 8, -4, 19, 89, 103};
	
	        Sorting.lsdRadixSort(arr);
	
	        AssertUtils.assertArrayEquals(new int[] {-50, -40, -4, 8, 19, 23, 25, 89, 103}, arr);
	
	    }
	
	    @Test
	    public void allNegativeNumbers() {
	        int[] arr = new int[] {0, -22, -11, -44, -55, -33};
	
	        Sorting.lsdRadixSort(arr);
	
	        AssertUtils.assertArrayEquals(new int[] {-55, -44, -33, -22, -11, 0}, arr);
	
	    }
	
	    @Test
	    public void largeMagnitudeNumbers() {
	        int[] arr = new int[] {44444444, 22222222, 55555555, 11111111, 33333333};
	
	        Sorting.lsdRadixSort(arr);
	
	        AssertUtils.assertArrayEquals(new int[] {11111111, 22222222, 33333333, 44444444, 55555555}, arr);
	
	    }
	
	    @Test
	    public void largeMagnitudeNumbersSameLastDigit() {
	        int[] arr = new int[] {44444441, 22222221, 55555551, 11111111, 33333331};
	
	        Sorting.lsdRadixSort(arr);
	
	        AssertUtils.assertArrayEquals(new int[] {11111111, 22222221, 33333331, 44444441, 55555551}, arr);
	    }
	
	    @Test
	    public void largeMagnitudeNumbersSameLastTwoDigit() {
	        int[] arr = new int[] {44444411, 22222211, 55555511, 11111111, 33333311};
	
	        Sorting.lsdRadixSort(arr);
	
	        AssertUtils.assertArrayEquals(new int[] {11111111, 22222211, 33333311, 44444411, 55555511}, arr);
	    }
	
	    @Test
	    public void differentFirstDigits() {
	        int[] arr = new int[] {41111111, 21111111, 31111111, 511111111, 11111111};
	
	        Sorting.lsdRadixSort(arr);
	
	        AssertUtils.assertArrayEquals(new int[] {11111111, 21111111, 31111111, 41111111, 511111111}, arr);
	    }
	
	    @Test
	    public void integerMinValueHandledProperly() {
	        int[] arr = new int[] {-3, -4, -6, -5, -1, -2, -7, -9, Integer.MIN_VALUE};
	
	        Sorting.lsdRadixSort(arr);
	
	        AssertUtils.assertArrayEquals(new int[] {Integer.MIN_VALUE, -9, -7, -6, -5, -4, -3, -2, -1}, arr);
	    }
	
	    @Test
	    public void allMinValue() {
	        int[] arr = new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
	
	        Sorting.lsdRadixSort(arr);
	
	        AssertUtils.assertArrayEquals(new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE}, arr);
	    }
	
	    @Test
	    public void maxValueHandledProperly() {
	        int[] arr = new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
	
	        Sorting.lsdRadixSort(arr);
	
	        AssertUtils.assertArrayEquals(new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE}, arr);
	    }
	
	    @Test
	    public void veryLargeArray() {
	        int[] arr = new int[] {2956,4852,9434,-5813,5089,-2337,3272,3746,-8894,2324,5642,4773,6906,-9763,-1152,8881,1376,-8183,7459,9592,-2021,1692,-994,9355,3904,7143,-7788,-7630,7301,-441,-1863,-9842,-2890,823,6590,-6524,9908,2640,4378,-4865,-9289,6274,-9210,6511,-8104,-6050,-7393,-5971,-1942,-9368,-4075,-4707,8486,4931,-4312,1455,1060,5484,4299,-3285,6116,-7314,-8262,4062,112,-6761,-5023,981,9671,-2732,1218,-7946,-8341,-1389,3035,-4233,-3127,9118,-7709,-2653,-6998,-4391,-7235,5247,1139,3351,-5339,-6682,33,349,7696,-8815,-2495,-1547,3430,7380,-1468,3114,-6840,-8736,2166,-3522,-1705,2403,4141,-46,-9684,7064,-9447,507,3509,-2179,5563,-10000,-5497,4220,-4549,-7867,8723,1297,-1310,-5576,-6208,-3838,9276,8249,9750,-2811,9039,-9921,-4944,-3443,-7077,8802,-362,4615,-8578,1771,-757,-5655,5800,428,665,-9605,270,8644,-5418,5010,191,-3206,-2258,-7472,-5102,-8420,-9052,6827,6985,8565,-2100,-283,5168,5721,3667,-8973,7222,-6287,8328,-6919,-7156,5326,3588,744,3825,-8025,2877,-3601,-3759,8012,-6366,-6603,-9131,-8657,-4628,-5181,9197,-3996,4536,2008,-2574,-6129,1613,7933,8407,8091,-836,2087,6353,8960,-4154,-3364,6195,7854,5405,-1073,-520,-6445,1929,9987,3983,-9526,6748,-5892,4457,-915,-5734,2719,-204,7538,5958,-3917,-3048,1534,2482,3193,5879,-1231,6037,586,-125,7775,-4786,-4470,-2969,-5260,-3680,-1784,6432,2561,7617,2798,6669,-599,2245,9513,1850,-8499,-678,8170,4694,-7551,-1626,9829,902,-2416};
	
	        Sorting.lsdRadixSort(arr);
	
	        AssertUtils.assertArrayEquals(new int[] {-10000,-9921,-9842,-9763,-9684,-9605,-9526,-9447,-9368,-9289,-9210,-9131,-9052,-8973,-8894,-8815,-8736,-8657,-8578,-8499,-8420,-8341,-8262,-8183,-8104,-8025,-7946,-7867,-7788,-7709,-7630,-7551,-7472,-7393,-7314,-7235,-7156,-7077,-6998,-6919,-6840,-6761,-6682,-6603,-6524,-6445,-6366,-6287,-6208,-6129,-6050,-5971,-5892,-5813,-5734,-5655,-5576,-5497,-5418,-5339,-5260,-5181,-5102,-5023,-4944,-4865,-4786,-4707,-4628,-4549,-4470,-4391,-4312,-4233,-4154,-4075,-3996,-3917,-3838,-3759,-3680,-3601,-3522,-3443,-3364,-3285,-3206,-3127,-3048,-2969,-2890,-2811,-2732,-2653,-2574,-2495,-2416,-2337,-2258,-2179,-2100,-2021,-1942,-1863,-1784,-1705,-1626,-1547,-1468,-1389,-1310,-1231,-1152,-1073,-994,-915,-836,-757,-678,-599,-520,-441,-362,-283,-204,-125,-46,33,112,191,270,349,428,507,586,665,744,823,902,981,1060,1139,1218,1297,1376,1455,1534,1613,1692,1771,1850,1929,2008,2087,2166,2245,2324,2403,2482,2561,2640,2719,2798,2877,2956,3035,3114,3193,3272,3351,3430,3509,3588,3667,3746,3825,3904,3983,4062,4141,4220,4299,4378,4457,4536,4615,4694,4773,4852,4931,5010,5089,5168,5247,5326,5405,5484,5563,5642,5721,5800,5879,5958,6037,6116,6195,6274,6353,6432,6511,6590,6669,6748,6827,6906,6985,7064,7143,7222,7301,7380,7459,7538,7617,7696,7775,7854,7933,8012,8091,8170,8249,8328,8407,8486,8565,8644,8723,8802,8881,8960,9039,9118,9197,9276,9355,9434,9513,9592,9671,9750,9829,9908,9987}, arr);
	    }
	
	}

	
	
	
	public static class HeapSortTests {
	
	    @Test
	    public void nullArray() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> Sorting.heapSort(null));
	    }
	
	    @Test
	    public void typicalUseCase() {
	        int[] arr = new int[] {-50, -40, 23, 25, 8, -4, 19, 89, 103};
	
	        int[] returned = Sorting.heapSort(convertToList(arr));
	
	        AssertUtils.assertArrayEquals(new int[] {-50, -40, -4, 8, 19, 23, 25, 89, 103}, returned);
	
	    }
	
	    @Test
	    public void allNegativeNumbers() {
	        int[] arr = new int[] {0, -22, -11, -44, -55, -33};
	
	        int[] returned = Sorting.heapSort(convertToList(arr));
	
	        AssertUtils.assertArrayEquals(new int[] {-55, -44, -33, -22, -11, 0}, returned);
	
	    }
	
	    @Test
	    public void largeMagnitudeNumbers() {
	        int[] arr = new int[] {44444444, 22222222, 55555555, 11111111, 33333333};
	
	        int[] returned = Sorting.heapSort(convertToList(arr));
	
	        AssertUtils.assertArrayEquals(new int[] {11111111, 22222222, 33333333, 44444444, 55555555}, returned);
	
	    }
	
	    @Test
	    public void largeMagnitudeNumbersSameLastDigit() {
	        int[] arr = new int[] {44444441, 22222221, 55555551, 11111111, 33333331};
	
	        int[] returned = Sorting.heapSort(convertToList(arr));
	
	        AssertUtils.assertArrayEquals(new int[] {11111111, 22222221, 33333331, 44444441, 55555551}, returned);
	    }
	
	    @Test
	    public void largeMagnitudeNumbersSameLastTwoDigit() {
	        int[] arr = new int[] {44444411, 22222211, 55555511, 11111111, 33333311};
	
	        int[] returned = Sorting.heapSort(convertToList(arr));
	
	        AssertUtils.assertArrayEquals(new int[] {11111111, 22222211, 33333311, 44444411, 55555511}, returned);
	    }
	
	    @Test
	    public void differentFirstDigits() {
	        int[] arr = new int[] {41111111, 21111111, 31111111, 511111111, 11111111};
	
	        int[] returned = Sorting.heapSort(convertToList(arr));
	
	        AssertUtils.assertArrayEquals(new int[] {11111111, 21111111, 31111111, 41111111, 511111111}, returned);
	    }
	
	    @Test
	    public void integerMinValueHandledProperly() {
	        int[] arr = new int[] {3, 4, 6, 5, 1, 2, 7, Integer.MIN_VALUE};
	
	        int[] returned = Sorting.heapSort(convertToList(arr));
	
	        AssertUtils.assertArrayEquals(new int[] {Integer.MIN_VALUE, 1, 2, 3, 4, 5, 6, 7}, returned);
	    }
	
	    @Test
	    public void allMinValue() {
	        int[] arr = new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
	
	        int[] returned = Sorting.heapSort(convertToList(arr));
	
	        AssertUtils.assertArrayEquals(new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE}, returned);
	    }
	
	    @Test
	    public void maxValueHandledProperly() {
	        int[] arr = new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
	
	        int[] returned = Sorting.heapSort(convertToList(arr));
	
	        AssertUtils.assertArrayEquals(new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE}, returned);
	    }
	
	    @Test
	    public void veryLargeArray() {
	        int[] arr = new int[] {2956,4852,9434,-5813,5089,-2337,3272,3746,-8894,2324,5642,4773,6906,-9763,-1152,8881,1376,-8183,7459,9592,-2021,1692,-994,9355,3904,7143,-7788,-7630,7301,-441,-1863,-9842,-2890,823,6590,-6524,9908,2640,4378,-4865,-9289,6274,-9210,6511,-8104,-6050,-7393,-5971,-1942,-9368,-4075,-4707,8486,4931,-4312,1455,1060,5484,4299,-3285,6116,-7314,-8262,4062,112,-6761,-5023,981,9671,-2732,1218,-7946,-8341,-1389,3035,-4233,-3127,9118,-7709,-2653,-6998,-4391,-7235,5247,1139,3351,-5339,-6682,33,349,7696,-8815,-2495,-1547,3430,7380,-1468,3114,-6840,-8736,2166,-3522,-1705,2403,4141,-46,-9684,7064,-9447,507,3509,-2179,5563,-10000,-5497,4220,-4549,-7867,8723,1297,-1310,-5576,-6208,-3838,9276,8249,9750,-2811,9039,-9921,-4944,-3443,-7077,8802,-362,4615,-8578,1771,-757,-5655,5800,428,665,-9605,270,8644,-5418,5010,191,-3206,-2258,-7472,-5102,-8420,-9052,6827,6985,8565,-2100,-283,5168,5721,3667,-8973,7222,-6287,8328,-6919,-7156,5326,3588,744,3825,-8025,2877,-3601,-3759,8012,-6366,-6603,-9131,-8657,-4628,-5181,9197,-3996,4536,2008,-2574,-6129,1613,7933,8407,8091,-836,2087,6353,8960,-4154,-3364,6195,7854,5405,-1073,-520,-6445,1929,9987,3983,-9526,6748,-5892,4457,-915,-5734,2719,-204,7538,5958,-3917,-3048,1534,2482,3193,5879,-1231,6037,586,-125,7775,-4786,-4470,-2969,-5260,-3680,-1784,6432,2561,7617,2798,6669,-599,2245,9513,1850,-8499,-678,8170,4694,-7551,-1626,9829,902,-2416};
	
	        int[] returned = Sorting.heapSort(convertToList(arr));
	
	        AssertUtils.assertArrayEquals(new int[] {-10000,-9921,-9842,-9763,-9684,-9605,-9526,-9447,-9368,-9289,-9210,-9131,-9052,-8973,-8894,-8815,-8736,-8657,-8578,-8499,-8420,-8341,-8262,-8183,-8104,-8025,-7946,-7867,-7788,-7709,-7630,-7551,-7472,-7393,-7314,-7235,-7156,-7077,-6998,-6919,-6840,-6761,-6682,-6603,-6524,-6445,-6366,-6287,-6208,-6129,-6050,-5971,-5892,-5813,-5734,-5655,-5576,-5497,-5418,-5339,-5260,-5181,-5102,-5023,-4944,-4865,-4786,-4707,-4628,-4549,-4470,-4391,-4312,-4233,-4154,-4075,-3996,-3917,-3838,-3759,-3680,-3601,-3522,-3443,-3364,-3285,-3206,-3127,-3048,-2969,-2890,-2811,-2732,-2653,-2574,-2495,-2416,-2337,-2258,-2179,-2100,-2021,-1942,-1863,-1784,-1705,-1626,-1547,-1468,-1389,-1310,-1231,-1152,-1073,-994,-915,-836,-757,-678,-599,-520,-441,-362,-283,-204,-125,-46,33,112,191,270,349,428,507,586,665,744,823,902,981,1060,1139,1218,1297,1376,1455,1534,1613,1692,1771,1850,1929,2008,2087,2166,2245,2324,2403,2482,2561,2640,2719,2798,2877,2956,3035,3114,3193,3272,3351,3430,3509,3588,3667,3746,3825,3904,3983,4062,4141,4220,4299,4378,4457,4536,4615,4694,4773,4852,4931,5010,5089,5168,5247,5326,5405,5484,5563,5642,5721,5800,5879,5958,6037,6116,6195,6274,6353,6432,6511,6590,6669,6748,6827,6906,6985,7064,7143,7222,7301,7380,7459,7538,7617,7696,7775,7854,7933,8012,8091,8170,8249,8328,8407,8486,8565,8644,8723,8802,8881,8960,9039,9118,9197,9276,9355,9434,9513,9592,9671,9750,9829,9908,9987}, returned);
	    }
	
	    private static ArrayList<Integer> convertToList(int[] arr) {
	        ArrayList<Integer> list = new ArrayList<>();
	        for (int i : arr) {
	            list.add(i);
	        }
	        return list;
	    }
	
	}

	
	
	
	/**
	 * Tests for Selection Sort. The Selection Sort algorithm should be
	 * - in-place
	 * - non-adaptive
	 * - unstable
	 */
	public static class SelectionSortTests {
	
	    CountComparator<IntegerWrapper> comparator;
	
	    @Before
	    public void setup() {
	        comparator = new CountComparator<>();
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void nullArray() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class,
	                () -> Sorting.selectionSort(null, comparator));
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void nullComparator() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(6, 7, 0);
	        ArrayDrawingBuilder.printInitialArray(arr);
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class,
	                () -> Sorting.selectionSort(null, comparator));
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void regularSorting() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(6, 7, 0, 2, 5, 4, 1, 8, 9, 3);
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.selectionSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(IntegerWrapper.makeArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), arr);
	        AssertUtils.checkComparisons(comparator, 45);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void sortWithOneDuplicate() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(6, 7, 0, 2, 5, 4, 4, 1, 8, 9, 3);
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.selectionSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(IntegerWrapper.makeArray(0, 1, 2, 3, 4, 4, 5, 6, 7, 8, 9), arr);
	        AssertUtils.checkComparisons(comparator, 55);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void sortWithManyDuplicates() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(1, 1, 1, 1, 1, 1, 1, 1, 1);
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.selectionSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(IntegerWrapper.makeArray(1, 1, 1, 1, 1, 1, 1, 1, 1), arr);
	        AssertUtils.checkComparisons(comparator, 36);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void sortWithOneNegative() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(4, 3, 1, -2, 5, 7, 6);
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.selectionSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(IntegerWrapper.makeArray(-2, 1, 3, 4, 5, 6, 7), arr);
	        AssertUtils.checkComparisons(comparator, 21);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void bruteForceTests() {
	        ArrayList<IntegerWrapper> list = new ArrayList<>();
	        list.add(new IntegerWrapper(0));
	        list.add(new IntegerWrapper(1));
	        list.add(new IntegerWrapper(2));
	        list.add(new IntegerWrapper(3));
	        list.add(new IntegerWrapper(4));
	        list.add(new IntegerWrapper(5));
	        list.add(new IntegerWrapper(6));
	        list.add(new IntegerWrapper(7));
	
	        ArrayList<ArrayList<IntegerWrapper>> permutations = BruteForceUtils.generatePermutations(list);
	
	        long totalComparisons = 0;
	        long numTests = 0;
	
	        for (ArrayList<IntegerWrapper> permutation : permutations) {
	            numTests++;
	            IntegerWrapper[] arr = permutation.toArray(new IntegerWrapper[0]);
	            IntegerWrapper[] arrCopy = Arrays.copyOf(arr, 8);
	            //ArrayDrawingBuilder.printInitialArray(arr);
	
	            Sorting.selectionSort(arr, comparator);
	
	            try {
	                AssertUtils.assertArrayEquals(list.toArray(new IntegerWrapper[0]), arr);
	                AssertUtils.checkComparisons(comparator, 28);
	            } catch (Throwable e) {
	                System.out.println("");
	                System.out.println(Arrays.toString(arrCopy));
	            }
	
	
	            totalComparisons += comparator.getComparisonCount();
	            comparator.resetComparisonCount();
	
	            if (numTests % 10000 == 0) {
	                System.out.printf("Ran %,d tests...\n", numTests);
	            }
	        }
	
	        AssertUtils.checkTotalComparisons(1128960, totalComparisons);
	
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void testWithStrings() {
	        CountComparator<String> stringComparator = new CountComparator<>();
	
	        String[] arr = new String[] {"Homestead", "Cobras", "Astraea", "Midknight", "MORT", "Aragon"};
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.selectionSort(arr, stringComparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(new String[] {"Aragon", "Astraea", "Cobras", "Homestead", "MORT", "Midknight"}, arr);
	        AssertUtils.checkComparisons(stringComparator, 15);
	    }
	
	    @Test(timeout = TestConfiguration.TIMEOUT)
	    public void reallyBigArray() {
	        IntegerWrapper[] arr = IntegerWrapper.makeArray(1678, 254, 1706, 176, 6329, 1323, 5940, 1756, 1731, 2481, 27, 1796, 2521, 6328, 3467, 2910, 359, 3005, 2468, 180, 2974, 2767, 4414, 118, 604, 5895, 1771, 114, 2046, 7457, 4391, 368, 3847, 3173, 6672, 5687, 5907, 5414, 5712, 125, 7769, 78, 6036, 1699, 148, 449, 1727, 67, 3663, 3357, 1778, 325, 5436, 581, 1732, 4272, 7174, 1591, 9084, 6424, 1261, 5114, 3070, 1987, 341, 2075, 9496, 360, 179, 5419, 4522, 85, 2611, 1501, 6090, 3314, 353, 876, 1477, 4043, 2539, 2338, 4362, 103, 1629, 461, 9408, 346, 1468, 498, 3604, 1294, 3015, 4738, 4143, 3572, 88, 133, 9072, 3128, 33, 2054, 292, 3647, 2485, 4779, 1787, 177, 5152, 1025, 6081, 8044, 3061, 3937, 4909, 4270, 5817, 7056, 2877, 870, 2713, 1153, 59, 1922, 1250, 4488, 7407, 5557, 7426, 3729, 841, 1058, 2522, 2067, 1807, 2992, 6800, 1923, 4795, 999, 1676, 379, 1168, 3641, 3245, 3175, 1730, 6995, 1076, 58, 4028, 316, 2811, 1714, 8033, 2607, 5084, 131, 1768, 1740, 2357, 687, 3546, 1986, 1318, 166, 8608, 4728, 3136, 144, 9752, 5813, 624, 4230, 4646, 3201, 108, 2252, 3276, 686, 492, 1741, 4499, 2959, 3310, 2194, 2102, 987, 1540, 1296, 857, 5675, 2590, 5010, 829, 3538, 1718, 2106, 3322, 4237, 5804, 868, 5460, 5736, 4564, 694, 245, 3501, 343, 216, 1405, 236, 4828, 8513, 3419, 4998, 6147, 1410, 3256, 8727, 5144, 6619, 5827, 2930, 4607, 2586, 840, 4698, 670);
	        ArrayDrawingBuilder.printInitialArray(arr);
	
	        Sorting.selectionSort(arr, comparator);
	        ArrayDrawingBuilder.printFinalArray(arr);
	
	        AssertUtils.assertArrayEquals(IntegerWrapper.makeArray(27,  33,  58,  59,  67,  78,  85,  88,  103, 108, 114, 118, 125, 131, 133, 144, 148, 166, 176, 177, 179, 180, 216, 236, 245, 254, 292, 316, 325, 341, 343, 346, 353, 359, 360, 368, 379, 449, 461, 492, 498, 581, 604, 624, 670, 686, 687, 694, 829, 840, 841, 857, 868, 870, 876, 987, 999, 1025,1058,1076,1153,1168,1250,1261,1294,1296,1318,1323,1405,1410,1468,1477,1501,1540,1591,1629,1676,1678,1699,1706,1714,1718,1727,1730,1731,1732,1740,1741,1756,1768,1771,1778,1787,1796,1807,1922,1923,1986,1987,2046,2054,2067,2075,2102,2106,2194,2252,2338,2357,2468,2481,2485,2521,2522,2539,2586,2590,2607,2611,2713,2767,2811,2877,2910,2930,2959,2974,2992,3005,3015,3061,3070,3128,3136,3173,3175,3201,3245,3256,3276,3310,3314,3322,3357,3419,3467,3501,3538,3546,3572,3604,3641,3647,3663,3729,3847,3937,4028,4043,4143,4230,4237,4270,4272,4362,4391,4414,4488,4499,4522,4564,4607,4646,4698,4728,4738,4779,4795,4828,4909,4998,5010,5084,5114,5144,5152,5414,5419,5436,5460,5557,5675,5687,5712,5736,5804,5813,5817,5827,5895,5907,5940,6036,6081,6090,6147,6328,6329,6424,6619,6672,6800,6995,7056, 7174, 7407, 7426, 7457, 7769, 8033, 8044, 8513, 8608, 8727, 9072, 9084, 9408, 9496, 9752), arr);
	        AssertUtils.checkComparisons(comparator, 26106);
	    }
	
	}
















}


class CountComparator<T extends Comparable<? super T>> implements Comparator<T> {

    private int comparisonCount;
    private ArrayList<String> log;

    public CountComparator() {
        comparisonCount = 0;
        log = new ArrayList<>();
    }

    @Override
    public int compare(T o1, T o2) {

        if (o1 == null || o2 == null) {
            throw new IllegalArgumentException(
                    String.format("Neither o1 nor o2 may be null! Received o1 = %s, o2 = %s", o1, o2));
        }

        ++comparisonCount;
        log.add(String.format("%3d: Compared %s to %s", comparisonCount, o1, o2));

        return o1.compareTo(o2);

    }

    /**
     * Resets the number of comparisons back to 0.
     */
    public void resetComparisonCount() {
        comparisonCount = 0;
        log = new ArrayList<>();
    }

    /**
     * Retrieves the number of comparisons that occurred
     * @return
     */
    public int getComparisonCount() {
        return comparisonCount;
    }

    /**
     * Returns the entire comparison log of this CountComparator as one long String.
     * @return The log, with each comparison on its own line.
     */
    public String getLog() {
        StringBuilder output = new StringBuilder("Here is the log of all comparisons performed:");
        for (String comparison : log) {
            output.append("\n");
            output.append(comparison);
        }
        return output.toString();
    }

}

/**
 * Wrapper class for Integers that contains extra metadata for testing purposes.
 */
class IntegerWrapper implements Comparable<IntegerWrapper> {

    /**
     * Integer data for this IntegerWrapper to store.
     */
    private int data;

    /**
     * Extra data (typically a single char) to test for sorting algorithm stability
     */
    private String metadata;

    /**
     * Most specific constructor.
     * @param data Data to be stored in this IntegerWrapper.
     * @param metadata Metadata to be stored in this IntegerWrapper.
     */
    public IntegerWrapper(int data, String metadata) {
        this.data = data;
        this.metadata = metadata == null ? "" : metadata;
    }

    /**
     * Non-metadata constructor.
     * @param data Data to be stored in this IntegerWrapper.
     */
    public IntegerWrapper(int data) {
        this(data, "");
    }

    /**
     * Default constructor. Defaults data to 0 and metadata to null.
     */
    public IntegerWrapper() {
        this(0, "");
    }

    /**
     * Retrieves the value from the data field.
     * @return The value in the data field.
     */
    public int getData() {
        return data;
    }

    /**
     * Retrieves the value from the metadata field.
     * @return The value in the metadata field.
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * Checks if this IntegerWrapper's data AND metadata are equal.
     * @param other The other Object to compare with.
     * @return True if both data and metadata are equal, false otherwise.
     */
    public boolean equals(Object other) {

        if (other == null || !other.getClass().equals(this.getClass())) {
            return false;
        }

        IntegerWrapper o = (IntegerWrapper) other;

        return this.data == o.data && this.metadata.equals(o.metadata);

    }

    /**
     * Returns a String containing the integer data stored in this node, as well
     * as the metadata string directly concatenated to the end of it.
     * @return
     */
    public String toString() {
        return String.format("%d%s", this.data, this.metadata);
    }

    public static IntegerWrapper[] makeArray(int... args) {
        IntegerWrapper[] arr = new IntegerWrapper[args.length];
        for (int i = 0; i < args.length; ++i) {
            arr[i] = new IntegerWrapper(args[i]);
        }
        return arr;
    }

    public int compareTo(IntegerWrapper other) {
        return this.data - other.data;
    }

}


class BruteForceUtils {

    public static <T> ArrayList<ArrayList<T>> generatePermutations(ArrayList<T> items) {
        ArrayList<ArrayList<T>> permutations = new ArrayList<>();

        if (items.size() == 1) {
            ArrayList<T> newPermutation = new ArrayList<>();
            newPermutation.add(items.get(0));
            permutations.add(newPermutation);
        }

        for (int i = 0; i < items.size();  ++i) {
            T removed = items.remove(i);

            ArrayList<ArrayList<T>> subtrees = generatePermutations(items);
            for (ArrayList<T> subtree : subtrees) {
                subtree.add(removed);
                permutations.add(subtree);
            }

            items.add(i, removed);
        }

        return permutations;
    }

    public static <T> void printPermutations(ArrayList<ArrayList<T>> permutations) {
        for (ArrayList<T> permutation : permutations) {
            System.out.println(permutation.toString());
        }
    }

}


class FakeRandom extends Random {

    private static final int[] PSEUDORANDOM_ORDER = new int[] {6, 7, 0, 2, 5, 4, 3, 1, 9, 8};

    private int i = 0;

    @Override
    public int nextInt(int range) {

        do {
            i = (i + 1) % PSEUDORANDOM_ORDER.length;
        } while (PSEUDORANDOM_ORDER[i] >= range);

        return PSEUDORANDOM_ORDER[i];
    }

}

interface Drawable {
    String draw();
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

    public static void printInitialArray(Object[] arr) {
        System.out.println("Initial (unsorted) array: " + Arrays.toString(arr));
    }

    public static void printFinalArray(Object[] arr) {
//        System.out.println("Final (sorted) array: " + Arrays.toString(arr));
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

        int lastNonNullIndex = args.length - 1;
        while (args[lastNonNullIndex] == null && lastNonNullIndex > 1) {
            --lastNonNullIndex;
        }

        String[] noTrailingNulls = new String[lastNonNullIndex + 1];
        for (int i = 0; i < noTrailingNulls.length; ++i) {
            noTrailingNulls[i] = args[i];
        }

        args = noTrailingNulls;


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
        int canvasHeight = (height * 1) + (height) * 3; // Each box is 3 tall. Each gap between rows is 3 tall.
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
                currentRow += 4;
            }
            int itemsOnThisRow = 1 << ((currentRow - 1) / 4);
            int widthPerItem = (canvasWidth / itemsOnThisRow) + 1;
            int col = (int) (((i + 1 - itemsOnThisRow) + 0.5) * widthPerItem) - 1;
//            System.out.println("Drawing item " + args[i] + " at " + currentRow + ", " + col);
//            System.out.printf("There are %d items on this row. widthPerItem is %d\n", itemsOnThisRow, widthPerItem);
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
            setCharAt(start[0] + 1, start[1] + (start[1] > end[1] ? -1 : 1), start[1] > end[1] ? '/' : '\\');
            setCharAt(end[0] - 1, end[1] + (start[1] > end[1] ? 1 : -1), start[1] > end[1] ? '/' : '\\');

            if (Math.abs(start[1] - end[1]) == 4) {
                setCharAt(start[0] + 2, Math.min(start[1], end[1]) + 2, start[1] < end[1] ? '\\' : '/');
            }
            for (int c = Math.min(start[1], end[1]) + 3; c < Math.max(end[1], start[1]) - 2; ++c) {
                setCharAt(start[0] + 2, c, '-');
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

final class TestConfiguration {

    /**
     * Set this boolean to TRUE if you want the TREE visualizations.
     * Set this to FALSE if you want the ARRAY visualizations.
     */
    public static final boolean USE_TREE_DRAWINGS = true;

    /**
     * Set this to the desired timeout (in milliseconds).
     */
    public static final int TIMEOUT = 10000;

    /**
     * Toggles whether or not to show the full comparison log (for easier debugging).
     */
    public static final boolean SHOW_COMPARISON_LOG = false;

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
 * A set of utilities to remove redundant assertion boilerplate.
 */
class AssertUtils {

    public static void assertValidException(Exception e) {
        assertTrue(ExceptionUtils.isDescriptiveException(e));
    }

    public static void assertArrayEquals(int[] expected, int[] actual) {
        Integer[] e = new Integer[expected.length];
        Integer[] a = new Integer[actual.length];

        for (int i = 0; i < e.length; ++i) {
            e[i] = expected[i];
        }

        for (int i = 0; i < a.length; ++i) {
            a[i] = actual[i];
        }

        assertArrayEquals(e, a);
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

    public static void checkComparisons(CountComparator comparator, int comparisons) {
        if (comparator.getComparisonCount() < comparisons) {
            System.out.println(ColorUtils.formatColorString(AsciiColorCode.BRIGHT_BLUE_BACKGROUND,
                    AsciiColorCode.BRIGHT_WHITE_FOREGROUND, "Well done! Your code ran in " + comparator.getComparisonCount()
                            + " comparisons, while our reference code ran in " + comparisons + ".\n"
                            + "If many sorts are consistently more efficient than ours, please reply to our\n" +
                            "post on Piazza so we can improve the test cases!"));
        } else if (comparator.getComparisonCount() == comparisons) {
            //System.out.println("Efficiency passed. Expected " + comparisons + " comparisons, and found " + comparator.getComparisonCount() + " comparisons.");
        } else {
            if (!TestConfiguration.SHOW_COMPARISON_LOG) {
                System.out.println(ColorUtils.formatColorString(AsciiColorCode.BRIGHT_RED_BACKGROUND,
                        AsciiColorCode.BRIGHT_WHITE_FOREGROUND, "Your code ran " + comparator.getComparisonCount() + " comparisons when it should" +
                                " have only run " + comparisons + ".\nTo see the full comparison log, set" +
                                " TestConfiguration.SHOW_COMPARISON_LOG to true."));
            } else {
                System.out.println(ColorUtils.formatColorString(AsciiColorCode.BRIGHT_RED_BACKGROUND,
                        AsciiColorCode.BRIGHT_WHITE_FOREGROUND, "Your code ran " + comparator.getComparisonCount() + " comparisons when it should" +
                                " have only run " + comparisons + ". To hide the full comparison log, set" +
                                " TestConfiguration.SHOW_COMPARISON_LOG to false."));

                System.out.println(comparator.getLog());
            }
            fail();
        }
    }

    public static void checkTotalComparisons(long expected, long actual) {
        System.out.println("\n------- BRUTE FORCE EFFICIENCY REPORT --------");
        System.out.printf("Total comparisons: %,d\n", actual);
        System.out.printf("Expected comparisons: %,d\n", expected);
        System.out.println("----------------------------------------------\n");

        if (expected != actual) {
            fail("Failed efficiency check! Expected " + expected + " but found " + actual + "comparisons!");
        }
    }

    public static <T> void assertListsEqual(List<T> expected, List<T> actual) {
        StringBuilder failMessage = new StringBuilder("Failed! ");

        if (expected.size() != actual.size()) {
            failMessage.append("Expected length " + expected.size() + " but received length " + actual.size() + ".");
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

    public static void assertExceptionIsCorrect(Class<? extends Exception> expected, Runnable codeThatMayThrowException) {
        assertExceptionIsCorrect(expected, codeThatMayThrowException, null);
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
