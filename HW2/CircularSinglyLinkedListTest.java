
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
// import org.junit.jupiter.api.*;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
/**
 * @author Achyutan Narayanan, Taeho Park, Hannah Huang, Ian Boraks, Mason H
 * @version 1.0
 * Circular singly-linked list test. Credit to Taeho Park, Hannah Huang, Ian Boraks, and Mason H for providing the
 * methods, which I combined into one comprehensive test. I also added a few things here and there for improved testing.
 * As well as consolidated and reordered multiple tests for organization purposes.
 */
public class CircularSinglyLinkedListTest {
    private static final int TIMEOUT = 200;
    private CircularSinglyLinkedList<String> list;

    @Before
    public void setUp() {
        list = new CircularSinglyLinkedList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testAddExceptions() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.addAtIndex(-1, "a0"));

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.addAtIndex(1, "a0"));

        assertThrows(
                IllegalArgumentException.class,
                () -> list.addAtIndex(0, null));

        assertThrows(
                IllegalArgumentException.class,
                () -> list.addToFront(null));

        assertThrows(
                IllegalArgumentException.class,
                () -> list.addToFront(null));
        assertThrows(IllegalArgumentException.class, () -> list.addToBack(null));
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveExceptions() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.removeAtIndex(-1));

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.removeAtIndex(0));

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.removeAtIndex(1));

        assertThrows(
                NoSuchElementException.class,
                () -> list.removeFromBack());

        assertThrows(
                NoSuchElementException.class,
                () -> list.removeFromFront());

        assertThrows(
                IllegalArgumentException.class,
                () -> list.removeLastOccurrence(null));

        for (int i = 0; i < 10; i++) {
            list.addToFront("a" + i);
        }

        assertThrows(
                NoSuchElementException.class,
                () -> list.removeLastOccurrence("b0"));
        list.clear();
        assertThrows(NoSuchElementException.class, () -> list.removeFromBack());
        list.clear();
    }

    //removeLastOccurrence no such element
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceNoSuchElement() {
        list.addToFront("a");
        list.addToFront("b");
        list.addToFront("c");
        assertThrows(NoSuchElementException.class, () -> list.removeLastOccurrence("d"));

    }

    //get exceptions
    @Test(timeout = TIMEOUT)
    public void testGetExceptions() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));

    }

    //testing toArray
    @Test(timeout = TIMEOUT)
    public void testToArray(){
        assertArrayEquals(new Integer[0],list.toArray());
        list.addToBack("0");
        list.addToBack("1");
        list.addToBack("2");
        assertArrayEquals(new String[]{"0","1","2"},list.toArray());
    }
    @Test(timeout = TIMEOUT)
    public void testToArrayWhenEmpty() {
        list.addToFront("0a");

        assertEquals("0a", list.removeFromBack());
        list.toArray();
    }

    @Test(timeout = TIMEOUT)
    public void testAddFirstNode() {
        list.addAtIndex(0, "0");
        assertEquals("0", list.get(0));
        assertEquals(list.getHead(), list.getHead().getNext());
        list.clear();
        list.addToBack("0");
        assertEquals("0", list.get(0));
        assertEquals(list.getHead(), list.getHead().getNext());
        list.clear();
        list.addToFront("0");
        assertEquals("0", list.get(0));
        assertEquals(list.getHead(), list.getHead().getNext());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackSizeOne() {
        list.addToFront("0a");

        assertEquals("0a", list.removeFromBack());
        assertNull(list.getHead());
        assertEquals(0, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontSizeOne() {
        list.addToFront("0a");

        assertEquals("0a", list.removeFromFront());
        assertNull(list.getHead());
        assertEquals(0, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexSizeOne() {
        list.addToFront("0a");

        assertEquals("0a", list.removeAtIndex(0));
        assertNull(list.getHead());
        assertEquals(0, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveMultipleFromFront() {
        list.addToFront("0a");
        list.addToFront("1b");
        list.addToFront("2c");
        list.addToFront("3d");

        assertEquals("3d", list.removeFromFront());
        assertArrayEquals(new String[]{"2c", "1b", "0a"}, list.toArray());
        assertEquals("2c", list.removeFromFront());
        assertArrayEquals(new String[]{"1b", "0a"}, list.toArray());
        assertEquals("1b", list.removeFromFront());
        assertArrayEquals(new String[]{"0a"}, list.toArray());
        assertEquals("0a", list.removeFromFront());
        assertArrayEquals(new String[]{}, list.toArray());
        assertNull(list.getHead());
        assertEquals(0, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveMultipleFromBack() {
        list.addToFront("0a");
        list.addToFront("1b");
        list.addToFront("2c");
        list.addToFront("3d");

        assertEquals("0a", list.removeFromBack());
        assertArrayEquals(new String[]{"3d", "2c", "1b"}, list.toArray());
        assertEquals("1b", list.removeFromBack());
        assertArrayEquals(new String[]{"3d", "2c"}, list.toArray());
        assertEquals("2c", list.removeFromBack());
        assertArrayEquals(new String[]{"3d"}, list.toArray());
        assertEquals("3d", list.removeFromBack());
        assertArrayEquals(new String[]{}, list.toArray());
        assertNull(list.getHead());
        assertEquals(0, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveMultipleAtIndex() {
        list.addToFront("0a");
        list.addToFront("1b");
        list.addToFront("2c");
        list.addToFront("3d");

        assertEquals("2c", list.removeAtIndex(1));
        assertArrayEquals(new String[]{"3d", "1b", "0a"}, list.toArray());
        assertEquals("0a", list.removeAtIndex(2));
        assertArrayEquals(new String[]{"3d", "1b"}, list.toArray());
        assertEquals("1b", list.removeAtIndex(1));
        assertArrayEquals(new String[]{"3d"}, list.toArray());
        assertEquals("3d", list.removeAtIndex(0));
        assertArrayEquals(new String[]{}, list.toArray());
        assertNull(list.getHead());
        assertEquals(0, list.size());
    }

    //remove when one node
    @Test(timeout = TIMEOUT)
    public void removeNodesMultipleCombinationsOfRemove() {
        list.addToFront("a");
        list.addToFront("b");
        list.addToFront("c");
        assertEquals("b", list.removeAtIndex(1));
        assertArrayEquals(new String[]{"c","a"}, list.toArray());
        assertEquals("c", list.removeFromFront());
        assertArrayEquals(new String[]{"a"}, list.toArray());
        assertEquals("a", list.removeFromBack());
        assertNull(list.getHead());
        assertArrayEquals(new String[]{}, list.toArray());


    }


    //This should test each of the methods and ensure that incrementing/decrementing is done properly.
    @Test(timeout = TIMEOUT)
    public void testSize(){
        //Adding
        assertEquals(0,list.size());
        list.addToFront("0");                     // 0
        assertEquals(1,list.size());
        list.addToBack("2");                      // 0,2
        assertEquals(2,list.size());
        list.addAtIndex(1,"1");             // 0,1,2
        assertEquals(3,list.size());

        //Removing
        list.removeAtIndex(1);                       // 0,2
        assertEquals(2,list.size());
        list.removeFromFront();                      // 2
        assertEquals(1,list.size());
        list.removeFromBack();                       //
        assertEquals(0, list.size());
    }


    //Comprehensive edge-case testing, courtesy of Mason H
    //The following methods may seem like repeats from previous, but what they do is collectively test the edge cases
    //so that if any edge case is missing from above, it'll be caught here, plus all edge case testing gets consolidated
    //this was an absolute lifesaver for me - Achyutan
    @Test(timeout = TIMEOUT)
    public void testAddingAtIndex(){
        //Test removing from middle
        list.addAtIndex(0,"0");               // 0
        assertEquals("0", list.get(0));
        list.addAtIndex(1,"2");               // 0, 2
        assertEquals("2", list.get(1));
        list.addAtIndex(1,"1");               // 0, 1, 2
        assertEquals("1", list.get(1));
    }

    @Test(timeout = TIMEOUT)
    public void testAddingAtToFront(){
        list.addToFront("1");                 //1
        assertEquals("1", list.get(0));
        assertEquals(list.getHead().getNext(),list.getHead());
        list.addToFront("0");                 //0,1
        assertEquals("0", list.get(0));
        assertEquals(list.getHead().getNext().getNext(),list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testAddingAtToBack(){
        list.addToBack("0");                 //0
        assertEquals("0", list.get(0));
        assertEquals(list.getHead().getNext(),list.getHead());
        list.addToBack("1");                 //0,1
        assertEquals("1", list.get(1));
        assertEquals(list.getHead().getNext().getNext(),list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testRemovingFromFront(){
        list.addToFront("0");                 //0
        assertEquals("0", list.get(0));
        list.removeFromFront();                  //
        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testRemovingFromBack(){
        list.addToBack("0");                 //0
        assertEquals("0", list.get(0));
        list.removeFromBack();                  //
        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceEdgeCases(){
        //Test edge case where first element is actually the last occurrence which can lead to
        //the head still pointing to the node, and hence it not actually being deleted.
        list.addToBack("0");                  //0
        assertEquals("0", list.removeLastOccurrence("0"));                  //
        assertNull(list.getHead());

        list.addToBack("0");                  //0
        list.addToBack("1");                  //0,1
        assertEquals("1", list.removeLastOccurrence("1"));                 //0
        assertEquals(1,list.size());

        list.addToBack("1");                  //0,1
        list.addToBack("2");                  //0,1,2
        assertEquals("1", list.removeLastOccurrence("1"));                 //0,2

        list.clear();                   //
        assertNull(list.getHead());

        list.addToBack("1");                  //1
        list.addToBack("1");                  //1,1
        list.addToBack("1");                  //1,1,1
        list.addToBack("1");                  //1,1,1,1
        list.addToBack("0");                  //1,1,1,1,0
        list.addToBack("1");                  //1,1,1,1,0,1
        list.addToBack("0");                  //1,1,1,1,0,1,0

        assertEquals("1", list.removeLastOccurrence("1"));                 //1,1,1,1,0,0
        assertArrayEquals(new String[]{"1","1","1","1","0","0"}, list.toArray());



    }


    //whether it is actually a circular singly linkedlist. believe me, weird stuff can happen
    @Test(timeout = TIMEOUT)
    public void testCircular() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a

        CircularSinglyLinkedListNode<String> curr = list.getHead();

        String[] expected = {"0a", "1a", "0a"};
        for (int i = 0; i < 3; i++) {
            assertEquals(expected[i], curr.getData());
            curr = curr.getNext();
        }
    }

    //have to use .equals() when comparing objects.
    @Test(timeout = TIMEOUT)
    public void testForEquality() {
        list.addAtIndex(0, new String("0a"));   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a

        String temp = new String("1a");
        assertEquals(new String("1a"), list.removeLastOccurrence(temp));
    }


    @Test(timeout = TIMEOUT)
    public void neverGonnaGiveYouUp() {
        System.out.println("I'm not sure why, but a method seems to have thrown an unexpected exception: ");
        try {
            throw new NeverGonnaGiveYouUp("Rickrolled!");
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        System.out.println("If you see this, the autograder ran properly");
        System.out.println("If you did miss any other tests you can see them below.");

    }
    //yep :)
    class NeverGonnaGiveYouUp extends RuntimeException {

        public NeverGonnaGiveYouUp(String message) {
            super(message);
        }
    }


}