/**
 * Name: Tim Jiang, Andrew Tang
 * Email: s6jiang@ucsd.edu
 * PID: A00000000
 * Sources Used: JDK 17 Docs
 *
 * This file contains all the public tests (visible on Gradescope)
 * Use this as a guide to write tests to verify your MyLinkedList and Iterator 
 * implementation 
 * Iterator values were set manually to decouple its dependency on next() 
 * during testing.
 */

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

/**
 * This class contains public test cases for MyListIterator. listLen1 is a
 * linkedlist of length 1 and listLen2 is a linkedlist of length 2.
 */

public class MyListIteratorPublicTester {
    private MyLinkedList listLen1, listLen2;
    private MyLinkedList.MyListIterator listLen1Iter, listLen2Iter;

    /**
     * Standard Test Fixture. A list with one entry and a list with two entries
     */
    @Before
    public void setUp() {
        listLen1 = new MyLinkedList();
        listLen1.add("Christine");
        listLen1Iter = listLen1.new MyListIterator();

        listLen2 = new MyLinkedList();
        listLen2.add("Paul");
        listLen2.add("Cao");
        listLen2Iter = listLen2.new MyListIterator();
    }

    /**
     * Test the MyListIterator constructor
     */
    @Test
    public void testConstructor() {
        assertFalse("The initial value of canRemoveOrSet",
                listLen1Iter.canRemoveOrSet);
        assertEquals(
                "The index of the element the iterator is initially pointing" +
                " on", 0, listLen1Iter.idx);
        assertEquals("The data of initial iterator's left", listLen1.head,
                listLen1Iter.left);
        assertEquals("The data of initial iterator's left", null,
                listLen1Iter.left.data);
        assertEquals("The data of initial iterator's right", "Christine",
                listLen1Iter.right.data);
    }

    /**
     * Test hasNext() on listLen2, multiple calls
     */
    @Test
    public void testHasNext() {
        assertTrue("call hasNext when there is a next node",
                listLen2Iter.hasNext());
        // Manually doing listLen2Iter.next();
        listLen2Iter.left = listLen2.head.getNext();
        listLen2Iter.right = listLen2.head.getNext().getNext();
        listLen2Iter.idx = 1;
        listLen2Iter.canRemoveOrSet = true;
        listLen2Iter.forward = true;
        assertTrue("call hasNext when there is a next node",
                listLen2Iter.hasNext());
        // Manually doing listLen2Iter.next();
        listLen2Iter.left = listLen2.head.getNext().getNext();
        listLen2Iter.right = listLen2.head.getNext().getNext().getNext();
        listLen2Iter.idx = 2;
        listLen2Iter.canRemoveOrSet = true;
        listLen2Iter.forward = true;
        assertFalse("call hasNext when there isn't a next node",
                listLen2Iter.hasNext());
    }

    /**
     * Test next() on listLen1, one call
     */
    @Test
    public void testNextOneCall() {
        assertEquals("The element returned from calling next", "Christine",
                listLen1Iter.next());
        assertEquals("Index of iterator after 1 next()", 1, listLen1Iter.idx);
        assertTrue("Able to remove node", listLen1Iter.canRemoveOrSet);
        assertTrue("Direction is forward", listLen1Iter.forward);
    }

    /**
     * Test next() on listLen2, two calls
     */
    @Test
    public void testNextTwoCalls() {
        assertEquals("The element returned from calling next", "Paul",
                listLen2Iter.next());
        assertEquals("Index of iterator after 1 next()", 1, listLen2Iter.idx);
        assertEquals("The element returned from calling next", "Cao",
                listLen2Iter.next());
        assertEquals("Index of iterator after 2 next()", 2, listLen2Iter.idx);
        assertTrue("Able to remove node", listLen2Iter.canRemoveOrSet);
        assertTrue("Direction is forward", listLen2Iter.forward);
    }

    /**
     * Test hasPrevious() on listLen2, multiple calls
     */
    @Test
    public void testHasPrev() {
        assertFalse("call when there isn't a previous node",
                listLen2Iter.hasPrevious());
        // Manually doing listLen2Iter.next();
        listLen2Iter.left = listLen2.head.getNext();
        listLen2Iter.right = listLen2.head.getNext().getNext();
        listLen2Iter.idx = 1;
        listLen2Iter.canRemoveOrSet = true;
        listLen2Iter.forward = true;
        assertTrue("call when there is a previous node",
                listLen2Iter.hasPrevious());
        // Manually doing listLen2Iter.next();
        listLen2Iter.left = listLen2.head.getNext().getNext();
        listLen2Iter.right = listLen2.head.getNext().getNext().getNext();
        listLen2Iter.idx = 2;
        listLen2Iter.canRemoveOrSet = true;
        listLen2Iter.forward = true;
        assertTrue("call when there is a previous node",
                listLen2Iter.hasPrevious());
    }

    /**
     * Test previous() on listLen1, one call
     */
    @Test
    public void testPreviousOneCall() {
        // Manually doing listLen1Iter.next();
        listLen1Iter.left = listLen1.head.getNext();
        listLen1Iter.right = listLen1.head.getNext().getNext();
        listLen1Iter.idx = 1;
        listLen1Iter.canRemoveOrSet = true;
        listLen1Iter.forward = true;
        assertEquals("The element returned from calling previous", "Christine",
                listLen1Iter.previous());
        assertEquals("Index of iterator after 0 previous()", 0,
                listLen1Iter.idx);
        assertTrue("Able to remove node", listLen1Iter.canRemoveOrSet);
        assertFalse("Direction is not forward", listLen1Iter.forward);
    }

    /**
     * Test previous() on listLen1, no previous
     */
    @Test
    public void testPreviousNoPrevious() {
        // Manually doing listLen1Iter.next();
        listLen1Iter.left = listLen1.head.getNext();
        listLen1Iter.right = listLen1.head.getNext().getNext();
        listLen1Iter.idx = 1;
        listLen1Iter.canRemoveOrSet = true;
        listLen1Iter.forward = true;
        assertEquals("The element returned from calling previous", "Christine",
                listLen1Iter.previous());
        assertEquals("Index of iterator after 2 previous()", 0,
                listLen1Iter.idx);
        assertTrue("Able to remove node", listLen1Iter.canRemoveOrSet);
        assertFalse("Direction is not forward", listLen1Iter.forward);
        try {
            listLen1Iter.previous(); // Should throw NoSuchElementException
            fail();
        } catch (NoSuchElementException e) {
            // do nothing
        }
    }

    /**
     * Test nextIndex() on listLen2 at the beginning of the list
     */
    @Test
    public void testNextIndex() {
        assertEquals("Index before any moves", 0, listLen2Iter.nextIndex());
        // Manually doing listLen2Iter.next();
        listLen2Iter.left = listLen2.head.getNext();
        listLen2Iter.right = listLen2.head.getNext().getNext();
        listLen2Iter.idx = 1;
        listLen2Iter.forward = true;
        listLen2Iter.canRemoveOrSet = true;
        assertEquals("Index after 1 next", 1, listLen2Iter.nextIndex());
    }


    /**
     * Test previousIndex() on listLen2, two calls
     */
    @Test
    public void testPrevIndex() {
        listLen2Iter.left = listLen2.head.getNext().getNext();
        listLen2Iter.right = listLen2.head.getNext().getNext().getNext();
        listLen2Iter.idx = 2;
        listLen2Iter.forward = true;
        listLen2Iter.canRemoveOrSet = true;
        assertEquals("Index after 2 next, no previous", 1,
                listLen2Iter.previousIndex());
        listLen2Iter.left = listLen2.head.getNext();
        listLen2Iter.right = listLen2.head.getNext().getNext();
        listLen2Iter.idx = 1;
        listLen2Iter.forward = true;
        listLen2Iter.canRemoveOrSet = true;
        assertEquals("Index after 2 next, 1 previous", 0,
                listLen2Iter.previousIndex());
    }

    /**
     * Test set() when forward is true
     */
    @Test
    public void testSetForward() {
        // equivalent to listLen2Iter.next();
        listLen2Iter.left = listLen2.head.getNext();
        listLen2Iter.right = listLen2.head.getNext().getNext();
        listLen2Iter.idx = 1;
        listLen2Iter.forward = true;
        listLen2Iter.canRemoveOrSet = true;

        listLen2Iter.set("Did it change?");
        assertEquals("Valid set when forward", "Did it change?",
                listLen2Iter.left.getElement());
        assertEquals("Valid set when forward, shouldn't change", "Cao",
                listLen2Iter.right.getElement());
    }

    /**
     * Test set() when forward is false
     */
    @Test
    public void testSetBackward() {
        // equivalent to 2 next() and 1 previous()
        listLen2Iter.left = listLen2.head.getNext();
        listLen2Iter.right = listLen2.head.getNext().getNext();
        listLen2Iter.idx = 1;
        listLen2Iter.forward = false;
        listLen2Iter.canRemoveOrSet = true;

        listLen2Iter.set("Did it change?");
        assertEquals("Valid set when backward", "Did it change?",
                listLen2Iter.right.getElement());
        assertEquals("Valid set when backward, shouldn't change", "Paul",
                listLen2Iter.left.getElement());
    }

    /**
     * Test remove() when forward is true
     */
    @Test
    public void testRemoveForward() {
        // equivalent to listLen2Iter.next();
        listLen2Iter.left = listLen2.head.getNext();
        listLen2Iter.right = listLen2.head.getNext().getNext();
        listLen2Iter.idx = 1;
        listLen2Iter.forward = true;
        listLen2Iter.canRemoveOrSet = true;

        listLen2Iter.remove();
        assertEquals("Valid remove when forward", null,
                listLen2Iter.left.getElement());
        assertEquals("Valid remove when forward, shouldn't change", "Cao",
                listLen2Iter.right.getElement());
        assertEquals("Index should decrement after removal", 0,
                listLen2Iter.idx);
        assertFalse("Prevent another remove or set",
                listLen2Iter.canRemoveOrSet);
    }

    /**
     * Test remove() when forward is false
     */
    @Test
    public void testRemoveBackward() {
        // equivalent to listLen2Iter.next();
        listLen2Iter.left = listLen2.head.getNext();
        listLen2Iter.right = listLen2.head.getNext().getNext();
        listLen2Iter.idx = 1;
        listLen2Iter.forward = false;
        listLen2Iter.canRemoveOrSet = true;

        listLen2Iter.remove();
        assertEquals("Valid remove when forward", null,
                listLen2Iter.right.getElement());
        assertEquals("Valid remove when backward, shouldn't change", "Paul",
                listLen2Iter.left.getElement());
        assertEquals("Index shouldn't decrement after removal", 1,
                listLen2Iter.idx);
        assertFalse("Prevent another remove or set",
                listLen2Iter.canRemoveOrSet);
    }

    /**
     * Test add() in the middle of the list
     */
    @Test
    public void testAddValidIndex() {
        // equivalent to listLen2Iter.next();
        listLen2Iter.left = listLen2.head.getNext();
        listLen2Iter.right = listLen2.head.getNext().getNext();
        listLen2Iter.idx = 1;
        listLen2Iter.forward = true;
        listLen2Iter.canRemoveOrSet = true;

        listLen2Iter.add("CSE 12");
        assertEquals("Valid add left", "CSE 12",
                listLen2Iter.left.getElement());
        assertEquals("Valid add right", "Cao", 
                listLen2Iter.right.getElement());
        assertEquals("Index change after add", 2, listLen2Iter.idx);
        assertFalse("Cannot remove immediately after add",
                listLen2Iter.canRemoveOrSet);
    }

    /**
     * Test add() in the beginning of the list
     */
    @Test
    public void testAddFirstIndex() {
        listLen2Iter.add("CSE 12");
        assertEquals("Valid add left", "CSE 12",
                listLen2Iter.left.getElement());
        assertEquals("Valid add right", "Paul",
                listLen2Iter.right.getElement());
        assertEquals("Index change after add", 1, listLen2Iter.idx);
        assertFalse("Cannot remove immediately after add",
                listLen2Iter.canRemoveOrSet);
    }
}
