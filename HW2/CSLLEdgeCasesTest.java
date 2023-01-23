
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;

public class CSLLEdgeCasesTest {
    private static final int TIMEOUT = 200;
    private CircularSinglyLinkedList<String> list;

    @Before
    public void setUp() {
        list = new CircularSinglyLinkedList<>();
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddNegativeIndex() {
        list.addAtIndex(-1, "0a");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testLargerThanSizeIndex() {
        list.addAtIndex(1, "0a");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNullData() {
        list.addAtIndex(0, null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToEmptyList() {
        list.addToFront("0a");
        assertEquals("0a",list.getHead().getData());
        assertSame(list.getHead(),list.getHead().getNext());
        assertEquals(1, list.size());
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveNegativeIndex() {
        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        list.removeAtIndex(-1);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveEqualsToSizeIndex() {
        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        list.removeAtIndex(3);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveFrontFromEmptyList() {
        list.removeFromFront();
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveBackFromEmptyList() {
        list.removeFromBack();
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromListOfSizeOne() {
        list.addToBack("0a");
        list.removeAtIndex(0);
        assertSame(null, list.getHead());
        assertEquals(0, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveBackFromListOfSizeOne() {
        list.addToBack("0a");
        list.removeFromBack();
        assertSame(null, list.getHead());
        assertEquals(0, list.size());
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetNegativeIndex() {
        list.addToBack("0a");
        list.addToBack("1a");
        list.get(-1);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveLastOccurrenceNull() {
        list.addToBack("0a");
        list.addToBack("1a");
        list.removeLastOccurrence(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveNoOccurrence() {
        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        list.removeLastOccurrence("3a");
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceFromMultiple() {
        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("0a");
        list.addToBack("2a");
        list.addToBack("3a");
        list.addToBack("0a");
        assertSame(list.get(5), list.removeLastOccurrence("0a"));
        assertSame(5, list.size());
        String[] expected = new String[5];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "0a";
        expected[3] = "2a";
        expected[4] = "3a";
        assertArrayEquals(expected, list.toArray());
    }
}