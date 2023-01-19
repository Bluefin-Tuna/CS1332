import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * This is a basic set of unit tests for ArrayList.
 *
 * Passing these tests doesn't guarantee any grade on these assignments. These
 * student JUnits that we provide should be thought of as a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * We highly encourage you to write your own set of JUnits for each homework
 * to cover edge cases you can think of for each data structure. Your code must
 * work correctly and efficiently in all cases, which is why it's important
 * to write comprehensive tests to cover as many cases as possible.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class ArrayListTest {

    private static final int TIMEOUT = 200;
    private ArrayList<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, list.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex() {
        list.addAtIndex(0, "2a");   // 2a
        list.addAtIndex(0, "1a");   // 1a, 2a
        list.addAtIndex(2, "4a");   // 1a, 2a, 4a
        list.addAtIndex(2, "3a");   // 1a, 2a, 3a, 4a
        list.addAtIndex(0, "0a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexException() {
        list.addAtIndex(0, "2a");   // 2a
        list.addAtIndex(0, "1a");   // 1a, 2a
        list.addAtIndex(2, "4a");   // 1a, 2a, 4a
        list.addAtIndex(2, "3a");   // 1a, 2a, 3a, 4a
        list.addAtIndex(0, "0a");   // 0a, 1a, 2a, 3a, 4a

        list.addAtIndex(10, "0a");   // 0a, 1a, 2a, 3a, 4a
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront() {
        list.addToFront("4a");  // 4a
        list.addToFront("3a");  // 3a, 4a
        list.addToFront("2a");  // 2a, 3a, 4a
        list.addToFront("1a");  // 1a, 2a, 3a, 4a
        list.addToFront("0a");  // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack() {
        list.addToBack("0a");   // 0a
        list.addToBack("1a");   // 0a, 1a
        list.addToBack("2a");   // 0a, 1a, 2a
        list.addToBack("3a");   // 0a, 1a, 2a, 3a
        list.addToBack("4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        String temp = "2a"; // For equality checking.

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, temp);   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeAtIndex(2));    // 0a, 1a, 3a, 4a, 5a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        String temp = "0a"; // For equality checking.

        list.addAtIndex(0, temp);   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeFromFront());   // 1a, 2a, 3a, 4a, 5a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "1a";
        expected[1] = "2a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        String temp = "5a"; // For equality checking.

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, temp);   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeFromBack());    // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        // Should be empty at initialization
        assertTrue(list.isEmpty());

        // Should not be empty after adding elements
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        assertFalse(list.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        // Clearing the list should empty the array and reset size
        list.clear();

        assertEquals(0, list.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testEnsureCapacity() {
        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        list.addToBack("3a");
        list.addToBack("4a");
        list.addToBack("5a");
        list.addToBack("6a");
        list.addToBack("7a");
        list.addToBack("8a");
        list.addToBack("9a");
        list.addToBack("10a");
        list.addToBack("11a");
        list.addToBack("12a");

        assertEquals(13, list.size());

        String[] expected = new String[18];
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
        expected[10] = "10a";
        expected[11] = "11a";
        expected[12] = "12a";

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testEmptyRemoveFromBack() { list.removeFromBack(); }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testEmptyRemoveFromFront() { list.removeFromFront(); }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testNegativeRemoveIndex() {
        list.addToFront("1");
        list.removeAtIndex(-1); }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testInvalidRemoveIndex() {
        list.addToFront("1");
        list.removeAtIndex(1);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromLastIndex() {
        list.addToBack("1");
        list.addToBack("2");
        list.addToBack("3");
        assertEquals("3", list.removeAtIndex(2));
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testNegativeGetIndex() {
        list.addToFront("1");
        list.removeAtIndex(-1);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testInvalidGetIndex() {
        list.addToFront("1");
        list.removeAtIndex(1);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testNullAddFront() {
        list.addToFront("1");
        list.addToFront(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testNullAddBack() {
        list.addToBack("1");
        list.addToBack(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testNullAddIndex() {
        list.addAtIndex(0, "1");
        list.addAtIndex(0, null);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testNegativeAddIndex() {
        list.addAtIndex(0, "1");
        list.addAtIndex(-1, null);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testInvalidAddIndex() {
        list.addAtIndex(0, "1");
        list.addAtIndex(2, null);
    }

    @Test(timeout=TIMEOUT)
    public void testAddFrontAtFullCapacity() {
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY; i++) {
            list.addToFront("" + i);
        }
        Object[] backing = list.getBackingArray();
        assertEquals(ArrayList.INITIAL_CAPACITY, backing.length);

        list.addToFront("a");
        backing = list.getBackingArray();
        assertEquals(ArrayList.INITIAL_CAPACITY * 2, backing.length);
        assertEquals(10, list.size());
    }

    @Test(timeout=TIMEOUT)
    public void testAddBackAtFullCapacity() {
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY; i++) {
            list.addToBack("" + i);
        }
        Object[] backing = list.getBackingArray();
        assertEquals(ArrayList.INITIAL_CAPACITY, backing.length);

        list.addToBack("a");
        backing = list.getBackingArray();
        assertEquals(ArrayList.INITIAL_CAPACITY * 2, backing.length);
        assertEquals(10, list.size());
    }

    @Test(timeout=TIMEOUT)
    public void testAddIndexMiddleAtFullCapacity() {
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY; i++) {
            list.addAtIndex(i / 2, "" + i);
        }
        Object[] backing = list.getBackingArray();
        assertEquals(ArrayList.INITIAL_CAPACITY, backing.length);

        list.addAtIndex(ArrayList.INITIAL_CAPACITY / 2, "a");
        backing = list.getBackingArray();
        assertEquals(ArrayList.INITIAL_CAPACITY * 2, backing.length);
        assertEquals(10, list.size());
    }

    @Test(timeout=TIMEOUT)
    public void testAddIndexEndAtFullCapacity() {
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY; i++) {
            list.addAtIndex(i, "" + i);
        }
        Object[] backing = list.getBackingArray();
        assertEquals(ArrayList.INITIAL_CAPACITY, backing.length);

        list.addAtIndex(ArrayList.INITIAL_CAPACITY, "a");
        backing = list.getBackingArray();
        assertEquals(ArrayList.INITIAL_CAPACITY * 2, backing.length);
        assertEquals(ArrayList.INITIAL_CAPACITY + 1, list.size());
    }

    @Test(timeout=TIMEOUT)
    public void testRemoveBackAtFullCapacity() {
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY; i++) {
            list.addToFront("" + i);
        }
        assertEquals(list.removeFromBack(), "0");
    }

    @Test(timeout=TIMEOUT)
    public void testRemoveFrontAtFullCapacity() {
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY; i++) {
            list.addToBack("" + i);
        }
        assertEquals(list.removeFromFront(), "0");
    }

    @Test(timeout=TIMEOUT)
    public void testRemoveIndexAtFullCapacity() {
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY; i++) {
            list.addToBack("" + i);
        }
        assertEquals(list.removeAtIndex(1), "1");
    }

    @Test(timeout=TIMEOUT)
    public void testIncreaseCapacityTwice() {
        for (int i = 0; i <= ArrayList.INITIAL_CAPACITY * 2; i++) {
            list.addToBack("" + i);
        }
        Object[] backing = list.getBackingArray();
        assertEquals(ArrayList.INITIAL_CAPACITY * 4, backing.length);
        assertEquals(ArrayList.INITIAL_CAPACITY * 2 + 1, list.size());
    }

    @Test(timeout=TIMEOUT)
    public void testConstructor() {
        Object[] backing = list.getBackingArray();
        assertEquals(ArrayList.INITIAL_CAPACITY, backing.length);
        assertEquals(0, list.size());
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void testGetInvalidIndex() {
        list.addToBack("0"); // 0
        list.addToBack("1"); // 0, 1
        list.addToBack("2"); // 0, 1, 2

        list.get(3);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void testGetNegativeIndex() {
        list.addToBack("0"); // 0
        list.addToBack("1"); // 0, 1
        list.addToBack("2"); // 0, 1, 2

        list.get(-1);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void testGetNothing() {
        list.get(0);
    }
}