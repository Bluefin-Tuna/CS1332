
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * This is an advance set of unit tests for ArrayStack and LinkedStack including 
 * hopefully all of the edge cases that I can possibly think of
 * 
 * I do not gaurentee that this test will include every edge cases nor ensure your
 * homework grade. Please check multiple Junit test that the other students wrote
 * to double check for any possible edge case.
 * 
 * Also, we cooperate to get a good grade, so please also share your junit test
 * so that we can work together to make sure that no possible edge case is left 
 * in our program. Thank you.
 *
 * @author Feiyang (Thomas) Xie
 * @version 1.0
 */
public class StackTest {

    private static final int TIMEOUT = 200;
    private ArrayStack<String> array;
    private LinkedStack<String> linked;

    @Before
    public void setup() {
        array = new ArrayStack<>();
        linked = new LinkedStack<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayStack.INITIAL_CAPACITY],
            array.getBackingArray());
        assertEquals(0, linked.size());
        assertNull(linked.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPush() {
        array.push("0a");   // 0a
        array.push("1a");   // 0a, 1a
        array.push("2a");   // 0a, 1a, 2a
        array.push("3a");   // 0a, 1a, 2a, 3a
        array.push("4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, array.size());

        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPop() {
        String temp = "5a";

        array.push("0a");   // 0a
        array.push("1a");   // 0a, 1a
        array.push("2a");   // 0a, 1a, 2a
        array.push("3a");   // 0a, 1a, 2a, 3a
        array.push("4a");   // 0a, 1a, 2a, 3a, 4a
        array.push(temp);   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, array.size());

        assertSame(temp, array.pop());  // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, array.size());

        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPushAtExceedCapacity() {
        for (int i = 0; i < 20; i++) {
            array.push(i + "a");
        }
        assertEquals(20, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY * 4];
        for (int i = 0; i < 20; i++) {
            expected[i] = i + "a";
        }
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPopAtExceedCapacity() {
        String temp = "0a";
        array.push(temp);
        for (int i = 1; i < 20; i++) {
            array.push(i + "a");
        }
        assertEquals(20, array.size());

        assertEquals("19a", array.pop());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY * 4];
        for (int i = 0; i < 19; i++) {
            expected[i] = i + "a";
        }
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPopEveryElements() {
        for (int i = 0; i < 20; i++) {
            array.push(i + "a");
        }
        assertEquals(20, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY * 4];
        for (int i = 0; i < 20; i++) {
            array.pop();
        }
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPeekAtExceedCapacity() {
        String temp = "0a";
        array.push(temp);
        for (int i = 1; i < 20; i++) {
            array.push(i + "a");
        }
        assertEquals(20, array.size());

        assertEquals("19a", array.peek());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY * 4];
        for (int i = 0; i < 20; i++) {
            expected[i] = i + "a";
        }
        assertArrayEquals(expected, array.getBackingArray());
    }


    @Test(timeout = TIMEOUT)
    public void testArrayPeek() {
        String temp = "4a";

        array.push("0a");   // 0a
        array.push("1a");   // 0a, 1a
        array.push("2a");   // 0a, 1a, 2a
        array.push("3a");   // 0a, 1a, 2a, 3a
        array.push(temp);   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, array.size());

        assertSame(temp, array.peek());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testArrayAddNullData() {
        array.push(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayPopNull() {
        array.pop();
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayPeekNull() {
        array.peek();
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPush() {
        linked.push("0a");  // 0a
        linked.push("1a");  // 1a, 0a
        linked.push("2a");  // 2a, 1a 0a
        linked.push("3a");  // 3a, 2a, 1a 0a
        linked.push("4a");  // 4a, 3a, 2a, 1a 0a

        assertEquals(5, linked.size());

        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPop() {
        String temp = "5a";

        linked.push("0a");  // 0a
        linked.push("1a");  // 1a, 0a
        linked.push("2a");  // 2a, 1a, 0a
        linked.push("3a");  // 3a, 2a, 1a, 0a
        linked.push("4a");  // 4a, 3a, 2a, 1a, 0a
        linked.push(temp);  // 5a, 4a, 3a, 2a, 1a, 0a
        assertEquals(6, linked.size());

        assertSame(temp, linked.pop()); // 4a, 3a, 2a, 1a, 0a

        assertEquals(5, linked.size());

        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPeek() {
        String temp = "4a";

        linked.push("0a");  // 0a
        linked.push("1a");  // 1a, 0a
        linked.push("2a");  // 2a, 1a, 0a
        linked.push("3a");  // 3a, 2a, 1a, 0a
        linked.push(temp);  // 4a, 3a, 2a, 1a, 0a
        assertEquals(5, linked.size());

        assertSame(temp, linked.peek());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLinkedAddNullData() {
        linked.push(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLinkedPopNull() {
        linked.pop();
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLinkedPeekNull() {
        linked.peek();
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPopLastElement() {

        linked.push("0a");   // 0a
        linked.push("1a");   // 0a, 1a
        linked.push("2a");   // 0a, 1a, 2a
        linked.push("3a");    // 0a, 1a, 2a, 3a
        linked.push("4a");    // 0a, 1a, 2a, 3a, 4a
        linked.push("5a");    // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, linked.size());

        assertSame("5a", linked.pop()); // 1a, 2a, 3a, 4a, 5a
        assertSame("4a", linked.pop()); // 2a, 3a, 4a, 5a
        assertSame("3a", linked.pop()); // 3a, 4a, 5a
        assertSame("2a", linked.pop()); // 4a, 5a
        assertSame("1a", linked.pop()); // 5a
        assertSame("0a", linked.pop()); // 

        assertEquals(0, linked.size());
        assertNull(linked.getHead());
    }
}