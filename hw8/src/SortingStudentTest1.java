import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Akshat Shenoi
 * @version 1.0
 */
public class SortingStudentTest1 {

    private static final int TIMEOUT = 200;
    private IntegerWrapper[] integers;
    private final int[] intsToSort = {0,1,2,3,4,5,6,7};
    private IntegerWrapper[] sortedIntegers;
    private ComparatorPlus<IntegerWrapper> comp;

    @Before
    public void setUp() {
        /*
            Initial array:
                index 0: 5
                index 1: 4
                index 2: 2
                index 3: 3
                index 4: 6
                index 5: 1
                index 6: 0
                index 7: 7
         */

        /*
            Sorted array:
                index 0: 0
                index 1: 1
                index 2: 2
                index 3: 3
                index 4: 4
                index 5: 5
                index 6: 6
                index 7: 7
         */

        integers = new IntegerWrapper[8];
        integers[0] = new IntegerWrapper(5);
        integers[1] = new IntegerWrapper(4);
        integers[2] = new IntegerWrapper(2);
        integers[3] = new IntegerWrapper(3);
        integers[4] = new IntegerWrapper(6);
        integers[5] = new IntegerWrapper(1);
        integers[6] = new IntegerWrapper(0);
        integers[7] = new IntegerWrapper(7);

        sortedIntegers = new IntegerWrapper[intsToSort.length];
        for (int i = 0; i < sortedIntegers.length; i++) {
            sortedIntegers[i] = new IntegerWrapper(i);
        }
        comp = IntegerWrapper.getComparator();
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSort() {
        IntegerWrapper[] integers = generateIntegerWrappers();
        System.out.print("Inputted Array: ");
        for (int i = 0; i < integers.length; i++) {
            System.out.print(integers[i].value + ", ");
        }
        Sorting.selectionSort(integers, comp);
        System.out.println("Comparisons: " + comp.getCount());
        assertArrayEquals(sortedIntegers, integers);
        assertTrue("Number of comparisons: " + comp.getCount(),
            comp.getCount() <= 28 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testSelectionSortNullArr() {
        Sorting.selectionSort(null, comp);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testSelectionSortNullComparator() {
        Sorting.selectionSort(integers, null);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSort() {
        IntegerWrapper[] integers = generateIntegerWrappers();
        System.out.print("Inputted Array: ");
        for (int i = 0; i < integers.length; i++) {
            System.out.print(integers[i].value + ", ");
        }
        Sorting.cocktailSort(integers, comp);
        System.out.println("Comparisons: " + comp.getCount());
        assertArrayEquals(sortedIntegers, integers);
        assertTrue("Number of comparisons: " + comp.getCount(),
            comp.getCount() <= 28 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testCocktailSortNullArr() {
        Sorting.cocktailSort(null, comp);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testCocktailSortNullComparator() {
        Sorting.cocktailSort(integers, null);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSort() {
        IntegerWrapper[] integers = generateIntegerWrappers();
        System.out.print("Inputted Array: ");
        for (int i = 0; i < integers.length; i++) {
            System.out.print(integers[i].value + ", ");
        }
        Sorting.mergeSort(integers, comp);
        System.out.println("Comparisons: " + comp.getCount());
        assertArrayEquals(sortedIntegers, integers);
        assertTrue("Number of comparisons: " + comp.getCount(),
            comp.getCount() <= 28 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testMergeSortNullArr() {
        Sorting.mergeSort(null, comp);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testMergeSortNullComparator() {
        Sorting.mergeSort(integers, null);
    }

    @Test(timeout = TIMEOUT)
    public void testKthSelect() {
        IntegerWrapper[] integers = generateIntegerWrappers();
        System.out.print("Inputted Array: ");
        for (int i = 0; i < integers.length; i++) {
            System.out.print(integers[i].value + ", ");
        }
        int randomSeed = 4;
        assertEquals(new IntegerWrapper(0), Sorting.kthSelect(1,
                integers, comp, new Random(randomSeed)));
        System.out.println("Comparisons: " + comp.getCount());
        assertTrue("Number of comparisons: " + comp.getCount(),
            comp.getCount() <= 28 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testKthSelectNullArr() {
        Sorting.kthSelect(1, null, comp, new Random());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testKthSelectNullComparator() {
        Sorting.kthSelect(1, integers, null, new Random());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testKthSelectNullRand() {
        Sorting.kthSelect(1, integers, comp, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testKthSelectKBelowRange() {
        Sorting.kthSelect(-1, integers, comp, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testKthSelectKAboveRange() {
        Sorting.kthSelect(intsToSort.length, integers, comp, null);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSort() {
        int[] unsortedArray = new int[]{54, 28, 58, 84, 20, 122, -85, 3};
        int[] sortedArray = new int[]{-85, 3, 20, 28, 54, 58, 84, 122};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLsdRadixSortNullArr() {
        Sorting.lsdRadixSort(null);
    }

    @Test(timeout = TIMEOUT)
    public void testHeapSort() {
        int[] unsortedArray = new int[] {54, 28, 58, 84, 20, 122, -85, 3};
        List<Integer> unsortedList = new ArrayList<>();
        for (int i : unsortedArray) {
            unsortedList.add(i);
        }
        int[] sortedArray = new int[] {-85, 3, 20, 28, 54, 58, 84, 122};
        int[] actualArray = Sorting.heapSort(unsortedList);
        assertArrayEquals(sortedArray, actualArray);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testHeapSortNullData() {
        Sorting.heapSort(null);
    }

    private IntegerWrapper[] generateIntegerWrappers() {
        List<Integer> shuffledInts = new ArrayList<>();
        comp = IntegerWrapper.getComparator();
        for (int i : intsToSort) {
            shuffledInts.add(i);
        }
        Collections.shuffle(shuffledInts);

        IntegerWrapper[] integers = new IntegerWrapper[intsToSort.length];
        for (int i = 0; i < intsToSort.length; i++) {
            integers[i] = new IntegerWrapper(shuffledInts.get(i));
        }
        comp = IntegerWrapper.getComparator();
        return integers;
    }

    /**
     * Class for testing proper sorting.
     */
    private static class IntegerWrapper {
        private Integer value;

        /**
         * Create an IntegerWrapper.
         *
         * @param value integer value
         */
        public IntegerWrapper(Integer value) {
            this.value = value;
        }

        /**
         * Get the value
         *
         * @return value of the integer
         */
        public Integer getValue() {
            return value;
        }

        /**
         * Set the value of the IntegerWrapper.
         *
         * @param value the new value
         */
        public void setValue(Integer value) {
            this.value = value;
        }

        /**
         * Create a comparator that compares the wrapped values.
         *
         * @return comparator that compares the wrapped values
         */
        public static ComparatorPlus<IntegerWrapper> getComparator() {
            return new ComparatorPlus<>() {
                @Override
                public int compare(IntegerWrapper int1,
                                   IntegerWrapper int2) {
                    incrementCount();
                    return int1.getValue().compareTo(int2.getValue());
                }
            };
        }

        @Override
        public String toString() {
            return "Value: " + value;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (this == other) {
                return true;
            }
            return other instanceof IntegerWrapper
                && ((IntegerWrapper) other).value.equals(this.value);
        }
    }

    /**
     * Inner class that allows counting how many comparisons were made.
     */
    private abstract static class ComparatorPlus<T> implements Comparator<T> {
        private int count;

        /**
         * Get the number of comparisons made.
         *
         * @return number of comparisons made
         */
        public int getCount() {
            return count;
        }

        /**
         * Increment the number of comparisons made by one. Call this method in
         * your compare() implementation.
         */
        public void incrementCount() {
            count++;
        }
    }

}
