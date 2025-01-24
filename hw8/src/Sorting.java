import java.util.Comparator;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Your implementation of various sorting algorithms.
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
public class Sorting {
    /**
     * Swaps two objects in an array.
     * @param arr Array containing data
     * @param a Integer of first index to be swapped
     * @param b Integer of second index to be swapped
     * @param <T> Data type
     */
    private static <T> void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n^2)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array and comparator cannot be null");
        }
        for (int i = arr.length - 1; i > 0; i--) {
            int max = 0;
            for (int j = 1; j <= i; j++) {
                if (comparator.compare(arr[j], arr[max]) > 0) {
                    max = j;
                }
            }
            swap(arr, max, i);
        }
    }

    /**
     * Implement cocktail sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array and comparator cannot be null");
        }
        boolean swaps = true;
        int start = 0;
        int end = arr.length - 1;

        while (swaps && start < end) {
            swaps = false;
            int newEnd = end - 1;
            for (int i = start; i < end; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    swaps = true;
                    newEnd = i;
                }
            }
            end = newEnd;
            if (!swaps) {
                break;
            } else {
                swaps = false;
                int newStart = start + 1;
                for (int j = end; j > start; j--) {
                    if (comparator.compare(arr[j], arr[j - 1]) < 0) {
                        swap(arr, j, j - 1);
                        swaps = true;
                        newStart = j;
                    }
                }
                start = newStart;
            }
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array and comparator cannot be null");
        }
        if (arr.length <= 1) {
            return;
        }
        int len = arr.length;
        int mid = len / 2;
        T[] left = (T[]) new Object[mid];
        T[] right = (T[]) new Object[len - mid];
        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = arr[i + left.length];
        }
        mergeSort(left, comparator);
        mergeSort(right, comparator);
        int i = 0;
        int j = 0;
        for (int x = 0; x < arr.length; x++) {
            if (j >= right.length || (i < left.length && comparator.compare(left[i], right[j]) <= 0)) {
                arr[x] = left[i];
                i++;
            } else {
                arr[x] = right[j];
                j++;
            }
        }
    }

    /**
     * Implement kth select.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param k          the index to retrieve data from + 1 (due to
     *                   0-indexing) if the array was sorted; the 'k' in "kth
     *                   select"; e.g. if k == 1, return the smallest element
     *                   in the array
     * @param arr        the array that should be modified after the method
     *                   is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @return the kth smallest element
     * @throws java.lang.IllegalArgumentException if the array or comparator
     *                                            or rand is null or k is not
     *                                            in the range of 1 to arr
     *                                            .length
     */
    public static <T> T kthSelect(int k, T[] arr, Comparator<T> comparator,
                                  Random rand) {
        if (arr == null || comparator == null || rand == null
                || k < 1 || k > arr.length) {
            throw new IllegalArgumentException("Arguments cannot be null or k not in range");
        }
        return kthHelper(k, arr, comparator, rand, 0, arr.length - 1);
    }

    /**
     * Recursive kthSelection helper method.
     * @param k index to retrieve data from
     * @param arr array containing data
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @param start Starting point
     * @param end End index
     * @return kth smallest index
     * @param <T> data type
     */
    private static <T> T kthHelper(int k, T[] arr, Comparator<T> comparator, Random rand, int start, int end) {
        if (start >= end) {
            return arr[end];
        }
        int pivotIndex = rand.nextInt(end - start) + start;
        T piv = arr[pivotIndex];
        swap(arr, start, pivotIndex);
        int i = start + 1;
        int j = end;
        while (i <= j) {
            while (i < j && comparator.compare(arr[i], piv) <= 0) {
                i++;
            }
            while (i < j && comparator.compare(arr[j], piv) >= 0) {
                j--;
            }
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        swap(arr, start, j);
        if (j == (k - 1)) {
            return arr[j];
        } else if (j > (k - 1)) {
            return kthHelper(k, arr, comparator, rand, start, j - 1);
        } else {
            return kthHelper(k, arr, comparator, rand, j + 1, end);
        }
    }

    /**
    * Implement LSD (least significant digit) radix sort.
    *
    * Make sure you code the algorithm as you have been taught it in class.
    * There are several versions of this algorithm and you may not get full
    * credit if you do not implement the one we have taught you!
    *
    * Remember you CANNOT convert the ints to strings at any point in your
    * code! Doing so may result in a 0 for the implementation.
    *
    * It should be:
    * out-of-place
    * stable
    * not adaptive
    *
    * Have a worst case running time of:
    * O(kn)
    *
    * And a best case running time of:
    * O(kn)
    *
    * You are allowed to make an initial O(n) passthrough of the array to
    * determine the number of iterations you need. The number of iterations
    * can be determined using the number with the largest magnitude.
    *
    * At no point should you find yourself needing a way to exponentiate a
    * number; any such method would be non-O(1). Think about how how you can
    * get each power of BASE naturally and efficiently as the algorithm
    * progresses through each digit.
    *
    * Refer to the PDF for more information on LSD Radix Sort.
    *
    * You may use ArrayList or LinkedList if you wish, but it may only be
    * used inside radix sort and any radix sort helpers. Do NOT use these
    * classes with other sorts. However, be sure the List implementation you
    * choose allows for stability while being as efficient as possible.
    *
    * Do NOT use anything from the Math class except Math.abs().
    *
    * @param arr the array to be sorted
    * @throws java.lang.IllegalArgumentException if the array is null
    */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        LinkedList<Integer>[] buckets = new LinkedList[19];
        int div = 1;
        boolean cont = true;
        while (cont) {
            cont = false;
            for (int num : arr) {
                int dig = (num / div);
                if (dig / 10 != 0) {
                    cont = true;
                }
                if (buckets[dig % 10 + 9] == null) {
                    buckets[dig % 10 + 9] = new LinkedList<>();
                }
                buckets[dig % 10 + 9].add(num);
            }
            int idx = 0;
            for (LinkedList<Integer> buc : buckets) {
                if (buc != null) {
                    for (int num : buc) {
                        arr[idx] = num;
                        idx++;
                    }
                    buc.clear();
                }
            }
            div *= 10;
        }
    }

    /**
     * Helper method for lsdRadix sort that counts number of digits in number.
     * @param num number to count digits of
     * @return amount of digits in number
     */
    private static int countDigits(int num) {
        if (num == 0) {
            return 1;
        }
        int count = 0;
        while (num != 0) {
            count++;
            num /= 10;
        }
        return count;
    }

    /**
     * Implement heap sort.
     *
     * It should be:
     * out-of-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Use java.util.PriorityQueue as the heap. Note that in this
     * PriorityQueue implementation, elements are removed from smallest
     * element to largest element.
     *
     * Initialize the PriorityQueue using its build heap constructor (look at
     * the different constructors of java.util.PriorityQueue).
     *
     * Return an int array with a capacity equal to the size of the list. The
     * returned array should have the elements in the list in sorted order.
     *
     * @param data the data to sort
     * @return the array with length equal to the size of the input list that
     * holds the elements from the list is sorted order
     * @throws java.lang.IllegalArgumentException if the data is null
     */
    public static int[] heapSort(List<Integer> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        PriorityQueue<Integer> q = new PriorityQueue<>(data);
        int[] sorted = new int[data.size()];
        for (int i = 0; i < data.size(); i++) {
            sorted[i] = q.remove();
        }
        return sorted;
    }
}
