
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * This is an advance set of unit tests for ArrayQueue and LinkedQueue including 
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
public class QueueTest {

    private static final int TIMEOUT = 200;
    private ArrayQueue<String> array;
    private LinkedQueue<String> linked;

    @Before
    public void setup() {
        array = new ArrayQueue<>();
        linked = new LinkedQueue<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayQueue.INITIAL_CAPACITY],
            array.getBackingArray());
        assertEquals(0, linked.size());
        assertNull(linked.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEnqueue() {
        array.enqueue("0a");    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPop() {
        String temp = "0a";

        array.enqueue(temp);    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        array.enqueue("5a");    // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, array.size());

        assertSame(temp, array.dequeue());  // 1a, 2a, 3a, 4a, 5a

        assertEquals(5, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPeek() {
        String temp = "0a";

        array.enqueue(temp);    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, array.size());

        assertSame(temp, array.peek());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testArrayAddNullData() {
        array.enqueue(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayDequeueNull() {
        array.dequeue();
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayPeekNull() {
        array.peek();
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEnqueueAtExceedCapacity() {
        for (int i = 0; i < 20; i++) {
            array.enqueue(i + "a");
        }
        assertEquals(20, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY * 4];
        for (int i = 0; i < 20; i++) {
            expected[i] = i + "a";
        }
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeueAtExceedCapacity() {
        String temp = "0a";
        array.enqueue(temp);
        for (int i = 1; i < 20; i++) {
            array.enqueue(i + "a");
        }
        assertEquals(20, array.size());

        assertEquals(temp, array.dequeue());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY * 4];
        for (int i = 1; i < 20; i++) {
            expected[i] = i + "a";
        }
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeueEveryElements() {
        for (int i = 0; i < 20; i++) {
            array.enqueue(i + "a");
        }
        assertEquals(20, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY * 4];
        for (int i = 0; i < 20; i++) {
            array.dequeue();
        }
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPeekAtExceedCapacity() {
        String temp = "0a";
        array.enqueue(temp);
        for (int i = 1; i < 20; i++) {
            array.enqueue(i + "a");
        }
        assertEquals(20, array.size());

        assertEquals(temp, array.peek());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY * 4];
        for (int i = 0; i < 20; i++) {
            expected[i] = i + "a";
        }
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testCircularArrayProperty() {
        array.enqueue("0a");    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, array.size());

        array.dequeue(); //null, 1a, 2a, 3a, 4a

        assertEquals(4, array.size());
        assertEquals(1, array.getFront());

        array.enqueue("5a"); //null, 1a, 2a, 3a, 4a, 5a
        array.enqueue("6a"); //null, 1a, 2a, 3a, 4a, 5a, 6a
        array.enqueue("7a"); //null, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        array.enqueue("8a"); //null, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        array.enqueue("9a"); //9a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        expected[0] = "9a";

        assertArrayEquals(expected, array.getBackingArray());

        assertEquals("1a", array.dequeue()); //9a, null, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        array.enqueue("10a");
        expected[1] = "10a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testCircularArrayPropertyAtExceedCapacity() {
        array.enqueue("0a");    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, array.size());

        array.dequeue(); //null, 1a, 2a, 3a, 4a

        assertEquals(4, array.size());
        assertEquals(1, array.getFront());

        array.enqueue("5a"); //null, 1a, 2a, 3a, 4a, 5a
        array.enqueue("6a"); //null, 1a, 2a, 3a, 4a, 5a, 6a
        array.enqueue("7a"); //null, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        array.enqueue("8a"); //null, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        array.enqueue("9a"); //9a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        array.enqueue("10a"); //1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a, 10a

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY * 2];
        expected[0] = "1a";
        expected[1] = "2a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        expected[5] = "6a";
        expected[6] = "7a";
        expected[7] = "8a";
        expected[8] = "9a";
        expected[9] = "10a";

        assertArrayEquals(expected, array.getBackingArray());

        assertEquals("1a", array.dequeue()); //null, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a, 10a

        assertEquals(9, array.size());

        array.enqueue("11a"); //null, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a, 10a, 11a
        expected[0] = null;
        expected[10] = "11a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    public void testCircularArrayDequeu() {
        array.enqueue("0a");    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, array.size());

        array.dequeue(); //null, 1a, 2a, 3a, 4a
        array.dequeue(); //null, null, 2a, 3a, 4a

        assertEquals(4, array.size());
        assertEquals(1, array.getFront());

        array.enqueue("5a"); //null, null, 2a, 3a, 4a, 5a
        array.enqueue("6a"); //null, null, 2a, 3a, 4a, 5a, 6a
        array.enqueue("7a"); //null, null, 2a, 3a, 4a, 5a, 6a, 7a
        array.enqueue("8a"); //null, null, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        array.enqueue("9a"); //9a, null, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        array.enqueue("10a"); //9a, 10a, 2a, 3a, 4a, 5a, 6a, 7a, 8a

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];

        for (int i = 0; i < 9; i++) {
            array.dequeue();
        }

        assertArrayEquals(expected, array.getBackingArray());
        assertEquals(0, array.size());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedEnqueue() {
        linked.enqueue("0a");   // 0a
        linked.enqueue("1a");   // 0a, 1a
        linked.enqueue("2a");   // 0a, 1a, 2a
        linked.enqueue("3a");   // 0a, 1a, 2a, 3a
        linked.enqueue("4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, linked.size());

        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedDequeue() {
        String temp = "0a";

        linked.enqueue(temp);   // 0a
        linked.enqueue("1a");   // 0a, 1a
        linked.enqueue("2a");   // 0a, 1a, 2a
        linked.enqueue("3a");    // 0a, 1a, 2a, 3a
        linked.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        linked.enqueue("5a");    // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, linked.size());

        assertSame(temp, linked.dequeue()); // 1a, 2a, 3a, 4a, 5a

        assertEquals(5, linked.size());

        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPeek() {
        String temp = "0a";

        linked.enqueue(temp);   // 0a
        linked.enqueue("1a");   // 0a, 1a
        linked.enqueue("2a");   // 0a, 1a, 2a
        linked.enqueue("3a");    // 0a, 1a, 2a, 3a
        linked.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, linked.size());

        assertSame(temp, linked.peek());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLinkedAddNullData() {
        linked.enqueue(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLinkedDequeueNull() {
        linked.dequeue();
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLinkedPeekNull() {
        linked.peek();
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedDequeueLastElement() {
        String temp = "0a";

        linked.enqueue(temp);   // 0a
        linked.enqueue("1a");   // 0a, 1a
        linked.enqueue("2a");   // 0a, 1a, 2a
        linked.enqueue("3a");    // 0a, 1a, 2a, 3a
        linked.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        linked.enqueue("5a");    // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, linked.size());

        assertSame(temp, linked.dequeue()); // 1a, 2a, 3a, 4a, 5a
        assertSame("1a", linked.dequeue()); // 2a, 3a, 4a, 5a
        assertSame("2a", linked.dequeue()); // 3a, 4a, 5a
        assertSame("3a", linked.dequeue()); // 4a, 5a
        assertSame("4a", linked.dequeue()); // 5a
        assertSame("5a", linked.dequeue()); // 

        assertEquals(0, linked.size());
        assertNull(linked.getHead());
        assertNull(linked.getTail());

    }
}