import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Tanush Chopra
 * @version 1.0
 * @userid tchopra32
 * @GTID 903785867
 *
 * Collaborators: N/A
 *
 * Resources: N/A
 */
public class Sorting {

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
        if (arr == null || comparator == null) { throw new IllegalArgumentException(); }
        for (int i = 0; i < arr.length - 1; ++i) {
            int lv = i;
            for (int j = i + 1; j < arr.length; ++j) {
                if (comparator.compare(arr[j], arr[lv]) < 0) {
                    lv = j;
                }
            }
            Sorting.swap(arr, i, lv);
        }
    }

    private static <T> void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
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
        if (arr == null || comparator == null) { throw new IllegalArgumentException(); }
        boolean s = true;
        int start = 0;
        int end = arr.length - 1;
        int ls = 0;
        while (s) {
            s = false;
            for (int i = start; i < end; ++i) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    ls = i;
                    s = true;
                    Sorting.swap(arr, i, i + 1);
                }
            }
            if (!s) { break; }
            s = false;
            end = ls;
            for (int i = end; i >= start + 1; --i) {
                if (comparator.compare(arr[i - 1], arr[i]) > 0) {
                    ls = i;
                    s = true;
                    Sorting.swap(arr, i, i - 1);
                }
            }
            start = ls;
        }
    }

    public static <T> void print(T[] arr) {
        for (T d: arr) {
            System.out.print(d + " ");
        }
        System.out.println();
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
        if (arr == null || comparator == null) { throw new IllegalArgumentException(); }
        if (arr.length <= 1) { return; }
        T[] l = (T[]) (new Object[arr.length / 2]);
        T[] r = (T[]) (new Object[arr.length - l.length]);
        for (int i = 0; i < l.length; ++i) { l[i] = arr[i]; }
        for (int i = 0; i < r.length; ++i) { r[i] = arr[i + l.length]; }
        Sorting.mergeSort(l, comparator);
        Sorting.mergeSort(r, comparator);
        Sorting.merge(arr, l, r, comparator);
    }

    public static <T> void merge(T[] a, T[] l, T[] r, Comparator<T> c) {
        int i = 0, j = 0;
        for (int k = 0; k < a.length; ++k) {
            if (j >= r.length || (i < l.length && c.compare(l[i], r[j]) <= 0)) {
                a[k] = l[i++];
            } else {
                a[k] = r[j++];
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
        if (arr == null || comparator == null || rand == null) { throw new IllegalArgumentException(); }
        if (k < 1 || k > arr.length) { throw new IllegalArgumentException(); }
        return Sorting.kthSelect(k, 0, arr.length, arr, comparator, rand);
    }

    private static <T> T kthSelect(int k, int l, int r, T[] a, Comparator<T> c, Random ra) {
        int pi = ra.nextInt(r - l) + l;
        T p = a[pi];
        Sorting.swap(a, l, pi);
        int i = l + 1;
        int j = r - 1;
        while (i <= j) {
            while (i <= j && c.compare(a[i], p) <= 0) { ++i; }
            while (i <= j && c.compare(a[j], p) >= 0) { --j; }
            if (i <= j) { Sorting.swap(a, i++, j--); }
        }
        Sorting.swap(a, l, j);
        if (j == k - 1) {
            return a[j];
        } else if (j > k - 1) {
            return Sorting.kthSelect(k, l, j, a, c, ra);
        }
        return Sorting.kthSelect(k, j + 1, r, a, c, ra);
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
        if (arr == null) { throw new IllegalArgumentException(); }
        LinkedList<Integer>[] b = new LinkedList<Integer>[19];
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
        int[] a = new int[data.size()];
        PriorityQueue<Integer> h = new PriorityQueue<Integer>(data);
        int i = 0;
        while (!h.isEmpty()) {
            a[i++] = h.remove();
        }
        return a;
    }
}
