
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * This is a more complete set of JUnit tests for CircularSinglyLinkedList.
 *
 * IT DOES NOT TEST EFFICIENCY AT ALL!!
 * You have to figure that out on your own.
 *
 * I'm also not checking illegal argument exceptions as those should be easy to
 * check without needing JUnits.
 *
 * I'm also also not checking every single case of out of range indeces. Too much time.
 *
 * Be aware that if one function is wrong, it may break a lot of tests not
 * related to said function. If addAtIndex doesn't work, you will fail 
 * basically every test.
 *
 * Each function has its own category of tests:
 *
 * Constructor: func01
 * addAtIndex: func02
 * addToFront: func03
 * addToBack: func04
 * removeAtIndex: func05
 * removeFromFront: func06
 * removeFromBack: func07
 * get: func08
 * isEmpty: func09
 * clear: func10
 * lastOccurence: func11
 * toArray: func12
 *
 * You can determine which categories of tests will run by setting the boolean
 * to true for that category. Setting it to false will automatically pass the
 * tests, so it will still count for the total tests.
 *
 * @author Andrew Kaminer - follow me on LinkedIn lol
 */
public class CompleteTest {
    private static final int TIMEOUT = 200;
    private CircularSinglyLinkedList<String> list;

    private final boolean func01 = true;
    private final boolean func02 = true;
    private final boolean func03 = true;
    private final boolean func04 = true;
    private final boolean func05 = true;
    private final boolean func06 = true;
    private final boolean func07 = true;
    private final boolean func08 = true;
    private final boolean func09 = true;
    private final boolean func10 = true;
    private final boolean func11 = true;
    private final boolean func12 = true;

    @Before
    public void setup() {
        list = new CircularSinglyLinkedList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void func01Test0() {
        if (func01) {
            assertEquals(0, list.size());
            assertNull(list.getHead());
        }
    }

    /**
     * Test valid input on an empty list
     */
    @Test(timeout = TIMEOUT)
    public void func02Test0() {
        if (func02) {
            list.addAtIndex(0, "1a");   // 1a
            assertEquals(1, list.size());
            assertEquals("1a", list.getHead().getData());
            assertEquals(list.getHead(), list.getHead().getNext()); 
            // check pointer to head
        }
    }

    /**
     * Test index out of bounds on an empty list
     */
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void func02Test1() {
        if (func02) {
            list.addAtIndex(1, "1a");   // error
        } else {
            throw new IndexOutOfBoundsException("ouch");
        }
    }

    /**
     * Test valid input at index 0 on list size = 1
     */
    @Test(timeout = TIMEOUT)
    public void func02Test2() {
        if (func02) {
            list.addAtIndex(0, "1a");   // 1a

            list.addAtIndex(0, "2a");   // 2a, 1a
            assertEquals(2, list.size());

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals("2a", curr.getData());

            curr = curr.getNext();
            assertEquals("1a", curr.getData());

            curr = curr.getNext();
            assertEquals("2a", curr.getData()); // check pointer to head still exists
        }
    }

    /**
     * Test valid input at index 1 on list size = 1
     */
    @Test(timeout = TIMEOUT)
    public void func02Test3() {
        if (func02) {
            list.addAtIndex(0, "1a");   // 1a
            
            list.addAtIndex(1, "2a");   // 1a, 2a
            assertEquals(2, list.size());

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals("1a", curr.getData());

            curr = curr.getNext();
            assertEquals("2a", curr.getData());

            curr = curr.getNext();
            assertEquals("1a", curr.getData()); // check pointer to head exists
        }
    }

    /**
     * Test invalid input on list size = 1
     */
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void func02Test4() {
        if (func02) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(2, "2a");   // exception
        } else {
            throw new IndexOutOfBoundsException("yikes");
        }
    }

    /**
     * Test valid input at index 0 on list size = 2
     */
    @Test(timeout = TIMEOUT)
    public void func02Test5() {
            if (func02) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a
            list.addAtIndex(0, "3a");   // 3a, 2a, 1a

            assertEquals(list.size(), 3);

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals(curr.getData(), "3a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "2a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "1a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "3a"); // check pointer to head exists
        }
    }

    /**
     * Test valid input at index 1 on list size = 2
     */
    @Test(timeout = TIMEOUT)
    public void func02Test6() {
        if (func02) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a
            list.addAtIndex(1, "3a");   // 2a, 3a, 1a

            assertEquals(list.size(), 3);

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals(curr.getData(), "2a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "3a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "1a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "2a"); // check pointer to head exists
        }
    }

    /**
     * Test valid input at index 2 on list size = 2
     */
    @Test(timeout = TIMEOUT)
    public void func02Test7() {
        if (func02) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a
            list.addAtIndex(2, "3a");   // 2a, 1a, 3a

            assertEquals(list.size(), 3);

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals(curr.getData(), "2a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "1a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "3a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "2a"); // check pointer to head exists
        }
    }

    /**
     * Test input at invalid index 3 on list size = 2
     */
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void func02Test8() {
        if (func02) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a
            list.addAtIndex(3, "3a");   // exception
        } else {
            throw new IndexOutOfBoundsException("jinkies");
        }
    }

    /**
     * Test valid input on list size = 0
     */
    @Test(timeout = TIMEOUT)
    public void func03Test0() {
        if (func03) {
            list.addToFront("1a");  // 1a
            
            assertEquals(list.size(), 1);

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals("1a", curr.getData());

            curr = curr.getNext();
            assertEquals("1a", curr.getData()); // check pointer to head exists
        }
    }

    /**
     * Test valid input on list size = 1
     */
    @Test(timeout = TIMEOUT)
    public void func03Test1() {
        if (func03) {
            list.addToFront("1a");  // 1a
            list.addToFront("2a");  // 2a, 1a

            assertEquals(list.size(), 2);

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals("2a", curr.getData());

            curr = curr.getNext();
            assertEquals("1a", curr.getData());

            curr = curr.getNext();
            assertEquals("2a", curr.getData()); // check pointer to head exists
        }
    }

    /**
     * Test valid input on list size = 2
     */
    @Test(timeout = TIMEOUT)
    public void func03Test2() {
        if (func03) {
            list.addToFront("1a");  // 1a
            list.addToFront("2a");  // 2a, 1a
            list.addToFront("3a");  // 3a, 2a, 1a

            assertEquals(list.size(), 3);

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals("3a", curr.getData());

            curr = curr.getNext();
            assertEquals("2a", curr.getData());

            curr = curr.getNext();
            assertEquals("1a", curr.getData());

            curr = curr.getNext();
            assertEquals("3a", curr.getData()); // check pointer to head exists
        }
    }

    /**
     * Test valid input on list size = 0
     */
    @Test(timeout = TIMEOUT)
    public void func04Test0() {
        if (func04) {
            list.addToBack("1a");   // 1a

            assertEquals(list.size(), 1);

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals("1a", curr.getData());

            curr = curr.getNext();
            assertEquals("1a", curr.getData()); // check pointer to head exists
        }
    }

    /**
     * Test valid input on list size = 1
     */
    @Test(timeout = TIMEOUT)
    public void func04Test1() {
        if (func04) {
            list.addToBack("1a");   // 1a
            list.addToBack("2a");   // 1a, 2a

            assertEquals(list.size(), 2);

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals(curr.getData(), "1a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "2a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "1a"); // check pointer to head exists
        }
    }

    /**
     * Test valid input on list size = 2
     */
    @Test(timeout = TIMEOUT)
    public void func04Test2() {
        if (func04) {
            list.addToBack("1a");   // 1a
            list.addToBack("2a");   // 1a, 2a
            list.addToBack("3a");   // 1a, 2a, 3a

            assertEquals(list.size(), 3);

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals(curr.getData(), "1a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "2a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "3a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "1a"); // check pointer to head exists`
        }
    }

    /**
     * Test valid input on list size = 3
     */
    @Test(timeout = TIMEOUT)
    public void func04Test3() {
        if (func04) {
            list.addToBack("1a");   // 1a
            list.addToBack("2a");   // 1a, 2a
            list.addToBack("3a");   // 1a, 2a, 3a
            list.addToBack("4a");   // 1a, 2a, 3a, 4a

            assertEquals(list.size(), 4);

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals(curr.getData(), "1a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "2a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "3a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "4a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "1a"); // check pointer to head exists`
        }
    }

    /**
     * Test invalid index 0 in list of size = 0
     */
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void func05Test0() {
        if (func05) {
            list.removeAtIndex(0);
        } else {
            throw new IndexOutOfBoundsException("zoinks");
        }
    }

    /**
     * Test valid index 0 in list of size = 1
     */
    @Test(timeout = TIMEOUT)
    public void func05Test1() {
        if (func05) {
            list.addAtIndex(0, "1a");   // 1a
            String s = list.removeAtIndex(0);      // null

            assertEquals(list.size(), 0);
            assertNull(list.getHead());
            assertEquals(s, "1a");
        }
    }

    /**
     * Test invalid index 1 in list of size = 1
     */
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void func05Test2() {
        if (func05) {
        list.addAtIndex(0, "1a");   // 1a
        String s = list.removeAtIndex(1);      // exception
        } else {
            throw new IndexOutOfBoundsException("jeepers");
        }
    }

    /**
     * Test valid index 0 in list of size = 2
     */
    @Test(timeout = TIMEOUT)
    public void func05Test3() {
        if (func05) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a

            String s = list.removeAtIndex(0);   // 1a

            assertEquals(s, "2a");
            assertEquals(list.getHead().getData(), "1a");
            assertEquals(list.size(), 1);
            assertEquals(list.getHead().getNext().getData(), "1a");
        }
    }

    /**
     * Test valid index 1 in list of size = 2
     */
    @Test(timeout = TIMEOUT)
    public void func05Test4() {
        if (func05) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a
            String s = list.removeAtIndex(1);   // 2a

            assertEquals(s, "1a");
            assertEquals(list.getHead().getData(), "2a");
            assertEquals(list.size(), 1);
            assertEquals(list.getHead().getNext().getData(), "2a"); // pointer
        }
    }

    /**
     * Test invalid index 2 in list of size = 2
     */
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void func05Test5() {
        if (func05) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a
            String s = list.removeAtIndex(2);   // exception
        } else {
            throw new IndexOutOfBoundsException("heck");
        }
    }

    /**
     * Test valid index 0 in list of size = 3
     */
    @Test(timeout = TIMEOUT)
    public void func05Test6() {
        if (func05) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a
            list.addAtIndex(0, "3a");   // 3a, 2a, 1a
            String s = list.removeAtIndex(0);   // 2a, 1a

            assertEquals(s, "3a");
            assertEquals(list.size(), 2);

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals(curr.getData(), "2a");
            
            curr = curr.getNext();
            assertEquals(curr.getData(), "1a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "2a");
        }
    }

    /**
     * Test valid index 1 in list of size = 3
     */
    @Test(timeout = TIMEOUT)
    public void func05Test7() {
        if (func05) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a
            list.addAtIndex(0, "3a");   // 3a, 2a, 1a
            String s = list.removeAtIndex(1);   // 3a, 1a

            assertEquals(s, "2a");
            assertEquals(list.size(), 2);

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals(curr.getData(), "3a");
            
            curr = curr.getNext();
            assertEquals(curr.getData(), "1a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "3a");
        }
    }

    /**
     * Test valid index 2 in list of size = 3
     */
    @Test(timeout = TIMEOUT)
    public void func05Test8() {
        if (func05) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a
            list.addAtIndex(0, "3a");   // 3a, 2a, 1a
            String s = list.removeAtIndex(2);   // 3a, 2a

            assertEquals(s, "1a");
            assertEquals(list.size(), 2);

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals(curr.getData(), "3a");
            
            curr = curr.getNext();
            assertEquals(curr.getData(), "2a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "3a");
        }
    }

    /**
     * Test invalid index 3 in list of size = 3
     */
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void func05Test9() {
        if (func05) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a
            list.addAtIndex(0, "3a");   // 3a, 2a, 1a
            String s = list.removeAtIndex(3);   // exception
        } else {
            throw new IndexOutOfBoundsException("darn");
        }
    }

    /**
     * Test on list of size = 0
     */
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void func06Test0() {
        if (func06) {
            list.removeFromFront();
        } else {
            throw new NoSuchElementException("darn");
        }
    }

    /**
     * Test on list size = 1
     */
    @Test(timeout = TIMEOUT)
    public void func06Test1() {
        if (func06) {
            list.addAtIndex(0, "1a");   // 1a
            String s = list.removeFromFront();  // null

            assertNull(list.getHead());
            assertEquals(s, "1a");
            assertEquals(list.size(), 0);
        }
    }

    /**
     * Test on list size = 2
     */
    @Test(timeout = TIMEOUT)
    public void func06Test2() {
        if (func06) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a
            String s = list.removeFromFront();  // 1a

            assertEquals(s, "2a");
            assertEquals(list.getHead().getData(), "1a");
            assertEquals(list.size(), 1);
            assertEquals(list.getHead().getNext().getData(), "1a"); // pointer
        }
    }

    /**
     * Test on list size = 3
     */
    @Test(timeout = TIMEOUT)
    public void func06Test3() {
        if (func06) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a
            list.addAtIndex(0, "3a");   // 3a, 2a, 1a
            String s = list.removeFromFront();  // 2a, 1a

            assertEquals(s, "3a");
            assertEquals(list.size(), 2);

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals(curr.getData(), "2a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "1a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "2a");
        }
    }

    /**
     * Test on list size = 0
     */
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void func07Test0() {
        if (func07) {
            list.removeFromBack();
        } else {
            throw new NoSuchElementException("gosh darn it");
        }
    }

    /**
     * Test on list size = 1
     */
    @Test(timeout = TIMEOUT)
    public void func07Test1() {
        if (func07) {
            list.addAtIndex(0, "1a");   // 1a
            String s = list.removeFromBack();  // null

            assertEquals(list.size(), 0);
            assertEquals(s, "1a");
            assertNull(list.getHead());
        }
    }

    /**
     * Test on list size = 2
     */
    @Test(timeout = TIMEOUT)
    public void func07Test2() {
        if (func07) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a
            String s = list.removeFromBack(); // 2a

            assertEquals(list.getHead().getData(), "2a");
            assertEquals(s, "1a");
            assertEquals(list.size(), 1);
            assertEquals(list.getHead().getNext().getData(), "2a");
        }
    }

    /**
     * Test on list size = 3
     */
    @Test(timeout = TIMEOUT)
    public void func07Test3() {
        if (func07) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a
            list.addAtIndex(0, "3a");   // 3a, 2a, 1a
            String s = list.removeFromBack();   // 3a, 2a

            assertEquals(s, "1a");

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals(curr.getData(), "3a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "2a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "3a");

            assertEquals(list.size(), 2);
        }
    }

    /**
     * Test index 0 on empty list
     */
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void func08Test0() {
        if (func08) {
            list.get(0);
        } else {
            throw new IndexOutOfBoundsException("shoot");
        }
    }

    /**
     * Test valid index 0 on list of length 1
     */
    @Test(timeout = TIMEOUT)
    public void func08Test1() {
        if (func08) {
            list.addAtIndex(0, "1a");   // 1a
            String s = list.get(0);     // 1a

            assertEquals("1a", s);
            assertEquals(list.size(), 1);
            assertEquals(list.getHead().getData(), "1a");
            assertEquals(list.getHead().getNext().getData(), "1a"); // pointer
        }
    }

    /**
     * Test valid index 1 on list of length 2
     */
    @Test(timeout = TIMEOUT)
    public void func08Test2() {
        if (func08) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a
            String s = list.get(1);     // 2a, 1a

            assertEquals(s, "1a");

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals(curr.getData(), "2a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "1a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "2a"); // pointer check

            assertEquals(list.size(), 2);
        }
    }

    /**
     * Test valid index 1 on list of length 3
     */
    @Test(timeout = TIMEOUT)
    public void func08Test3() {
        if (func08) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a
            list.addAtIndex(0, "3a");   // 3a, 2a, 1a
            String s = list.get(1);     // 3a, 2a, 1a

            assertEquals(s, "2a");

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals(curr.getData(), "3a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "2a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "1a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "3a"); // pointer check

            assertEquals(list.size(), 3);
        }
    }

    /**
     * Test on empty list
     */
    @Test(timeout = TIMEOUT)
    public void func09Test0() {
        if (func09) {
            assertTrue(list.isEmpty());
            assertEquals(list.size(), 0);
            assertNull(list.getHead());
        }
    }

    /**
     * Test on a nonempty list
     */
    @Test(timeout = TIMEOUT)
    public void func09Test1() {
        if (func09) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a

            assertFalse(list.isEmpty());
            assertEquals(list.size(), 2);
            assertEquals(list.getHead().getData(), "2a");
            assertEquals(list.getHead().getNext().getData(), "1a");
            assertEquals(list.getHead().getNext().getNext().getData(), "2a");
        }
    }

    /**
     * Test on an empty list
     */
    @Test(timeout = TIMEOUT)
    public void func10Test0() {
        if (func10) {
            list.clear();

            assertNull(list.getHead());
            assertEquals(list.size(), 0);
        }
    }

    /**
     * Test on a nonempty list
     */
    @Test(timeout = TIMEOUT)
    public void func10Test1() {
        if (func10) {
            list.addAtIndex(0, "1a");   // 1a
            list.addAtIndex(0, "2a");   // 2a, 1a

            list.clear();

            assertNull(list.getHead());
            assertEquals(list.size(), 0);
        }
    }

    /** 
     * Test on an empty list
     */
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void func11Test0() {
        if (func11) {
            list.removeLastOccurrence("1a");
        } else {
            throw new NoSuchElementException("yikes");
        }
    }

    /**
     * Test nonexistent element on list size = 1
     */
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void func11Test1() {
        if (func11) {
            list.addAtIndex(0, "1a"); // 1a
            list.removeLastOccurrence("2a"); // 1a
        } else {
            throw new NoSuchElementException("L");
        }
    }

    /**
     * Test existing element on list size = 1
     */
    @Test(timeout = TIMEOUT)
    public void func11Test2() {
        if (func11) {
            list.addAtIndex(0, "1a"); // 1a
            String s = list.removeLastOccurrence("1a"); // null
                                                      //
            assertEquals(s, "1a");
            assertEquals(list.size(), 0);
            assertNull(list.getHead());
        }
    }

    /**
     * Test nonexisting element on list size = 2
     */
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void func11Test3() {
        if (func11) {
            list.addAtIndex(0, "1a"); // 1a
            list.addAtIndex(0, "2a"); // 2a, 1a
            list.removeLastOccurrence("3a"); // exception
        } else {
            throw new NoSuchElementException("L");
        }
    }

    /**
     * Test existing element at index = 0 on list size = 2
     */
    @Test(timeout = TIMEOUT)
    public void func11Test4() {
        if (func11) {
            list.addAtIndex(0, "1a"); // 1a
            list.addAtIndex(0, "2a"); // 2a, 1a
            String s = list.removeLastOccurrence("2a"); // 1a

            assertEquals(s, "2a");
            assertEquals(list.getHead().getData(), "1a");
            assertEquals(list.getHead().getNext().getData(), "1a"); // pointer
            assertEquals(list.size(), 1);
        }
    }

    /**
     * Test existing element at index = 0 on list size = 2
     */
    @Test(timeout = TIMEOUT)
    public void func11Test5() {
        if (func11) {
            list.addAtIndex(0, "1a"); // 1a
            list.addAtIndex(0, "2a"); // 2a, 1a
            String s = list.removeLastOccurrence("1a"); // 2a

            assertEquals(s, "1a");
            assertEquals(list.getHead().getData(), "2a");
            assertEquals(list.getHead().getNext().getData(), "2a"); // pointer
            assertEquals(list.size(), 1);
        }
    }

    /**
     * Test nonexistant element at on list size = 3
     */
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void func11Test6() {
        if (func11) {
            list.addAtIndex(0, "1a"); // 1a
            list.addAtIndex(0, "2a"); // 2a, 1a
            list.addAtIndex(0, "3a"); // 3a, 2a, 1a
            String s = list.removeLastOccurrence("4a"); // exception
        } else {
            throw new NoSuchElementException("L");
        }
    }

    /**
     * Test existing element at index 0 on list size = 3
     */
    @Test(timeout = TIMEOUT)
    public void func11Test7() {
        if (func11) {
            list.addAtIndex(0, "1a"); // 1a
            list.addAtIndex(0, "2a"); // 2a, 1a
            list.addAtIndex(0, "3a"); // 3a, 2a, 1a
            String s = list.removeLastOccurrence("3a"); // 2a, 1a

            assertEquals(list.size(), 2);
            assertEquals(s, "3a");

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals(curr.getData(), "2a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "1a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "2a");
        }
    }

    /**
     * Test existing element at index 1 on list size = 3
     */
    @Test(timeout = TIMEOUT)
    public void func11Test8() {
        if (func11) {
            list.addAtIndex(0, "1a"); // 1a
            list.addAtIndex(0, "2a"); // 2a, 1a
            list.addAtIndex(0, "3a"); // 3a, 2a, 1a
            String s = list.removeLastOccurrence("2a"); // 3a, 1a

            assertEquals(list.size(), 2);
            assertEquals(s, "2a");

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals(curr.getData(), "3a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "1a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "3a");
        }
    }

    /**
     * Test existing element at index 2 on list size = 3
     */
    @Test(timeout = TIMEOUT)
    public void func11Test9() {
        if (func11) {
            list.addAtIndex(0, "1a"); // 1a
            list.addAtIndex(0, "2a"); // 2a, 1a
            list.addAtIndex(0, "3a"); // 3a, 2a, 1a
            String s = list.removeLastOccurrence("1a"); // 3a, 2a

            assertEquals(list.size(), 2);
            assertEquals(s, "1a");

            CircularSinglyLinkedListNode curr = list.getHead();
            assertEquals(curr.getData(), "3a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "2a");

            curr = curr.getNext();
            assertEquals(curr.getData(), "3a");
        }
    }

    /**
     * Test list of length zero
     */
    @Test(timeout = TIMEOUT)
    public void func12Test0() {
        if (func12) {
            Object[] arr = list.toArray();
            assertArrayEquals(arr, new Object[0]);
        }
    }

    /**
     * Test list of length one
     */
    @Test(timeout = TIMEOUT)
    public void func12Test1() {
        if (func12) {
            list.addAtIndex(0, "1a");
            Object[] arr = list.toArray();
            Object[] compArr = new Object[1];
            compArr[0] = "1a";

            assertArrayEquals(arr, compArr);
        }
    }

    /**
     * Test list of length two
     */
    @Test(timeout = TIMEOUT)
    public void func12Test2() {
        list.addAtIndex(0, "1a");
        list.addAtIndex(0, "2a");

        Object[] arr = list.toArray();
        Object[] compArr = new Object[2];
        compArr[0] = "2a";
        compArr[1] = "1a";

        assertArrayEquals(arr, compArr);
    }
}