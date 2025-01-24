/**
 * @author Vedant Nahar & Laksh Makhija
*/

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class SortingTestCases {
    private static final long TIMEOUT = 200;

    @Test(timeout = TIMEOUT)
    public void testSelectionSortEmptyList() {
        Integer[] arr = {};
        Integer[] expected = {};
        Sorting.selectionSort(arr, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortSingleElementList() {
        Integer[] arr = {5};
        Integer[] expected = {5};
        Sorting.selectionSort(arr, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortAlreadySortedList() {
        Integer[] arr = {1, 2, 3, 4, 5};
        Integer[] expected = {1, 2, 3, 4, 5};
        Sorting.selectionSort(arr, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortInstability() {
        String left = "4";
        String right = new String("4");
        String[] arr = {"5", left, "2", "3", "6", "1", "0", "7", right, "2"};
        String[] expected = {"0", "1", "2", "2", "3", left, right, "5", "6", "7"};
        Sorting.selectionSort(arr, Comparator.naturalOrder());
        assertFalse(arr[5] == left);
        assertFalse(arr[6] == right);
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortLargeList() {
        Integer[] arr = new Integer[1000];
        Integer[] expected = new Integer[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = 1000 - i;
            expected[i] = i + 1;
        }
        Sorting.selectionSort(arr, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortListWithNullableElements() {
        Integer[] arr = {5, null, 2, null, 6, 1, 0, 7};
        Integer[] expected = {null, null, 0, 1, 2, 5, 6, 7};
        Sorting.selectionSort(arr, Comparator.nullsFirst(Comparator.naturalOrder()));
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortListWithCustomObjects() {
        Person[] arr = {new Person("Alice", 25), new Person("Bob", 30), new Person("Charlie", 20)};
        Person[] expected = {new Person("Alice", 25), new Person("Bob", 30), new Person("Charlie", 20)};
        Sorting.selectionSort(arr, Comparator.comparing(Person::getName));
        assertArrayEquals(expected, arr);
    }

    // Custom Person class for testing
    private static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Person person = (Person) obj;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortAlreadySortedList() {
        Integer[] arr = {1, 2, 3, 4, 5};
        Integer[] expected = {1, 2, 3, 4, 5};
        Sorting.cocktailSort(arr, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortReverseSortedList() {
        Integer[] arr = {5, 4, 3, 2, 1};
        Integer[] expected = {1, 2, 3, 4, 5};
        Sorting.cocktailSort(arr, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortListWithFewElements() {
        Integer[] arr = {5, 2};
        Integer[] expected = {2, 5};
        Sorting.cocktailSort(arr, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortListWithDuplicates() {
        String left = "4";
        String right = new String("4");
        String[] arr = {"5", left, "2", "3", "6", "1", "0", "7", right, "2"};
        String[] expected = {"0", "1", "2", "2", "3", left, right, "5", "6", "7"};
        Sorting.cocktailSort(arr, Comparator.naturalOrder());
        assertFalse(arr[5] != left);
        assertFalse(arr[6] != right);
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortListWithLargeRangeOfValues() {
        Integer[] arr = new Integer[1000];
        Integer[] expected = new Integer[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = 1000 - i;
            expected[i] = i + 1;
        }
        Sorting.cocktailSort(arr, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortListWithNullElements() {
        Integer[] arr = {5, null, 2, null, 6, 1, 0, 7};
        Integer[] expected = {null, null, 0, 1, 2, 5, 6, 7};
        Sorting.cocktailSort(arr, Comparator.nullsFirst(Comparator.naturalOrder()));
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortListWithRandomElements() {
        Integer[] arr = {5, 2, 7, 1, 4, 3, 6};
        Integer[] expected = {1, 2, 3, 4, 5, 6, 7};
        Sorting.cocktailSort(arr, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortListWithEqualElements() {
        Integer[] arr = {5, 5, 5, 5, 5};
        Integer[] expected = {5, 5, 5, 5, 5};
        Sorting.cocktailSort(arr, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }
    @Test(timeout = TIMEOUT)
    public void testMergeSortEmptyList() {
        Integer[] arr = {};
        Integer[] expected = {};
        Sorting.mergeSort(arr, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortSingleElementList() {
        Integer[] arr = {5};
        Integer[] expected = {5};
        Sorting.mergeSort(arr, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortTwoElementList() {
        Integer[] arr = {5, 2};
        Integer[] expected = {2, 5};
        Sorting.mergeSort(arr, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortAlreadySortedList() {
        Integer[] arr = {1, 2, 3, 4, 5};
        Integer[] expected = {1, 2, 3, 4, 5};
        Sorting.mergeSort(arr, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortReverseSortedList() {
        Integer[] arr = {5, 4, 3, 2, 1};
        Integer[] expected = {1, 2, 3, 4, 5};
        Sorting.mergeSort(arr, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortListWithDuplicateValues() {
        String left = "4";
        String right = new String("4");
        String[] arr = {"5", left, "2", "3", "6", "1", "0", "7", right, "2"};
        String[] expected = {"0", "1", "2", "2", "3", left, right, "5", "6", "7"};
        Sorting.mergeSort(arr, Comparator.naturalOrder());
        assertFalse(arr[5] != left);
        assertFalse(arr[6] != right);
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortLargeList() {
        Integer[] arr = new Integer[1000];
        Integer[] expected = new Integer[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = 1000 - i;
            expected[i] = i + 1;
        }
        Sorting.mergeSort(arr, Comparator.naturalOrder());
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortListWithNullableElements() {
        Integer[] arr = {5, null, 2, null, 6, 1, 0, 7};
        Integer[] expected = {null, null, 0, 1, 2, 5, 6, 7};
        Sorting.mergeSort(arr, Comparator.nullsFirst(Comparator.naturalOrder()));
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortListWithCustomObjects() {
        Person[] arr = {new Person("Alice", 25), new Person("Bob", 30), new Person("Charlie", 20)};
        Person[] expected = {new Person("Alice", 25), new Person("Bob", 30), new Person("Charlie", 20)};
        Sorting.mergeSort(arr, Comparator.comparing(Person::getName));
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortListWithSpecialValues() {
        Double[] arr = {5.0, Double.NaN, 2.0, Double.POSITIVE_INFINITY, 6.0, 1.0, 0.0, Double.NEGATIVE_INFINITY};
        Double[] expected = {Double.NEGATIVE_INFINITY, 0.0, 1.0, 2.0, 5.0, 6.0, Double.POSITIVE_INFINITY, Double.NaN};
        Sorting.mergeSort(arr, Comparator.nullsFirst(Comparator.naturalOrder()));
        assertArrayEquals(expected, arr);
    }
    @Test(timeout = TIMEOUT)
    public void testKthSelectListWithDuplicates() {
        Integer[] arr = {5, 5, 5, 5, 5};
        Integer expected = 5;
        Integer result = Sorting.kthSelect(3, arr, Comparator.naturalOrder(), new Random());
        assertEquals(expected, result);
    }

    @Test(timeout = TIMEOUT)
    public void testKthSelectListWithUniqueElements() {
        Integer[] arr = {1, 2, 3, 4, 5};
        Integer expected = 3;
        Integer result = Sorting.kthSelect(3, arr, Comparator.naturalOrder(), new Random());
        assertEquals(expected, result);
    }

    @Test(timeout = TIMEOUT)
    public void testKthSelectEmptyList() {
        Integer[] arr = {};
        assertThrows(IllegalArgumentException.class, () -> {
            Sorting.kthSelect(1, arr, Comparator.naturalOrder(), new Random());
        });
    }

    @Test(timeout = TIMEOUT)
    public void testKthSelectListWithFewElements() {
        Integer[] arr = {5, 3, 7};
        Integer expected1 = 3;
        Integer expected2 = 5;
        Integer expected3 = 7;
        Integer result1 = Sorting.kthSelect(1, arr, Comparator.naturalOrder(), new Random());
        Integer result2 = Sorting.kthSelect(2, arr, Comparator.naturalOrder(), new Random());
        Integer result3 = Sorting.kthSelect(3, arr, Comparator.naturalOrder(), new Random());
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
    }

    @Test(timeout = TIMEOUT)
    public void testKthSelectListWithManyElements() {
        Integer[] arr = new Integer[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = i + 1;
        }
        Integer expected = 500;
        Integer result = Sorting.kthSelect(500, arr, Comparator.naturalOrder(), new Random());
        assertEquals(expected, result);
    }

    @Test(timeout = TIMEOUT)
    public void testKthSelectListWithExtremeValues() {
        Integer[] arr = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        Integer expected1 = Integer.MIN_VALUE;
        Integer expected2 = Integer.MAX_VALUE;
        Integer result1 = Sorting.kthSelect(1, arr, Comparator.naturalOrder(), new Random());
        Integer result2 = Sorting.kthSelect(2, arr, Comparator.naturalOrder(), new Random());
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
    }

    @Test(timeout = TIMEOUT)
    public void testKthSelectKthElementAtBeginning() {
        Integer[] arr = {1, 2, 3, 4, 5};
        Integer expected = 1;
        Integer result = Sorting.kthSelect(1, arr, Comparator.naturalOrder(), new Random());
        assertEquals(expected, result);
    }

    @Test(timeout = TIMEOUT)
    public void testKthSelectKthElementAtEnd() {
        Integer[] arr = {1, 2, 3, 4, 5};
        Integer expected = 5;
        Integer result = Sorting.kthSelect(5, arr, Comparator.naturalOrder(), new Random());
        assertEquals(expected, result);
    }

    @Test(timeout = TIMEOUT)
    public void testKthSelectInvalidValuesOfK() {
        Integer[] arr = {1, 2, 3, 4, 5};
        assertThrows(IllegalArgumentException.class, () -> {
            Sorting.kthSelect(0, arr, Comparator.naturalOrder(), new Random());
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Sorting.kthSelect(-1, arr, Comparator.naturalOrder(), new Random());
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Sorting.kthSelect(6, arr, Comparator.naturalOrder(), new Random());
        });
    }
    @Test(timeout = TIMEOUT)
    public void testKthSelectRandomOrder() {
        Integer[] arr = {5, -2, 7, -1, 4, -3, 6};
        Integer[] expected = {-3, -2, -1, 4, 5, 6, 7};
        int k = 4;
        Integer result = Sorting.kthSelect(k, arr, Comparator.naturalOrder(), new Random());
        assertEquals(expected[k - 1], result);
    }
    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortEmptyList() {
        int[] arr = {};
        int[] expected = {};
        Sorting.lsdRadixSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortSingleElementList() {
        int[] arr = {5};
        int[] expected = {5};
        Sorting.lsdRadixSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortSortedList() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        Sorting.lsdRadixSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortReverseSortedList() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        Sorting.lsdRadixSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortListWithDuplicateValues() {
        int[] arr = {5, 4, 2, 3, 6, 1, 0, 7, 4, 2};
        int[] expected = {0, 1, 2, 2, 3, 4, 4, 5, 6, 7};
        Sorting.lsdRadixSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortListWithNegativeNumbers() {
        int[] arr = {-5, -4, -2, -3, -6, -1, 0, -7, -4, -2};
        int[] expected = {-7, -6, -5, -4, -4, -3, -2, -2, -1, 0};
        Sorting.lsdRadixSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortLargeList() {
        int[] arr = new int[1000];
        int[] expected = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = 1000 - i;
            expected[i] = i + 1;
        }
        Sorting.lsdRadixSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortListWithZero() {
        int[] arr = {5, 0, 2, 0, 6, 1, 0, 7};
        int[] expected = {0, 0, 0, 1, 2, 5, 6, 7};
        Sorting.lsdRadixSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortListWithMaxIntegerValue() {
        int[] arr = {Integer.MAX_VALUE, 5, 2, 6, 1, 0, 7};
        int[] expected = {0, 1, 2, 5, 6, 7, Integer.MAX_VALUE};
        Sorting.lsdRadixSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortListWithMinIntegerValue() {
        int[] arr = {Integer.MIN_VALUE, 5, 2, 6, 1, 0, 7};
        int[] expected = {Integer.MIN_VALUE, 0, 1, 2, 5, 6, 7};
        Sorting.lsdRadixSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testKthSelectNegativeNumbers() {
        Integer[] arr = {-5, -2, -7, -1, -3};
        int k = 2;
        Integer expected = -5;
        Random rand = new Random();
        Integer result = Sorting.kthSelect(k, arr, Comparator.naturalOrder(), rand);
        assertEquals(expected, result);
    }

}