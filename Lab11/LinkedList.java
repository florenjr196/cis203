// Class LinkedIntList can be used to store a list of integers.

import java.util.*;

public class LinkedList<E extends Comparable<E>>{

    // first value in the list
    private ListNode<E> front; 
    // size of list
    private int size;

    // post: constructs an empty list
    public LinkedList() {
        front = null;
        size = 0;
    }

    // post: returns the current number of elements in the list
    public int size() {
	return size;
    }

    // pre : 0 <= index < size()
    // post: returns the integer at the given index in the list
    public E get(int index) {
        return nodeAt(index).data;
    }

    // post: creates a comma-separated, bracketed version of the list
    public String toString() {
        // if the list is empty
        if (front == null) {
            return "[]";
        } 
        // otherwise
        String result = "[" + front.data;
        ListNode<E> current = front.next;
        while (current != null) {
               result += ", " + current.data;
               current = current.next;
        }
        result += "]";
        return result;
    }

    // post : returns the position of the first occurrence of the given
    //        value (-1 if not found)
    public int indexOf(E value) {
        int index = 0;
        ListNode<E> current = front;
        while (current != null) {
	    //System.out.println(current.data + " " + value);
            if (current.data.equals(value)) {
                return index;
            }
            index++;
            current = current.next;
	    
        }
        return -1;

    }

    // post: appends the given value to the end of the list
    public void add(E value) {
        size++;
        // if this is the only node in this list
        if (front == null) {
            front = new ListNode<E>(value);
            return;
        } 

        // otherwise, we need to put the new node at the end of the list
        ListNode<E> current = front;
        while (current.next != null) {
               current = current.next;
        }
        current.next = new ListNode<E>(value);
    }

    // pre: 0 <= index <= size()
    // post: inserts the given value at the given index
    public void add(int index, E value) {
        size++;
        if (index == 0) {
            // put the new node at the front
            front = new ListNode<E>(value, front);
        } else {
            // get the node just this node will link to
            ListNode<E> current = nodeAt(index - 1);
            current.next = new ListNode<E>(value, current.next);
        }
    }

    // pre : 0 <= index < size()
    // post: removes value at the given index
    public void remove(int index) {
        size--;
        // if we are removing the front ndoe
        if (index == 0) {
            front = front.next;
        } else {
            // get the node that the node we are removing is linked to
            ListNode<E> current = nodeAt(index - 1);
            current.next = current.next.next;
        }
    }

    // pre : 0 <= i < size()
    // post: returns a reference to the node at the given index
    private ListNode<E> nodeAt(int index) {
        ListNode<E> current = front;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
    
    public void sortND() {
	for(int i = 1; i <= size -1; i++) {
	    ListNode<E> pred = front;
	    ListNode<E> current = front.next;
	    while(current != null) {
		if(pred.data.compareTo(current.data) > 0) {
		    E temp = pred.data;
		    pred.data = current.data;
		    current.data = temp;
		}
		pred = current;
		current = current.next;
	    }
	} 
    }
    
    public void reverseND() {
	ListNode<E> newFront = null;
	ListNode<E> cursor = front;
	while (cursor != null) {
	    ListNode<E> newNode = new ListNode<E> (cursor.data);
	    newNode.next = newFront;
	    newFront = newNode;
	    cursor = cursor.next;
	}
	front = newFront;
	
    }
    
    public void reverseIP() {
	ListNode<E> done = null;
	ListNode<E> current = null;
	ListNode<E> remaining = front;
	while (remaining != null) {
	    current = remaining;
	    remaining = remaining.next;
	    current.next = done;
	    done = current;
	}
	front = done;
      
    }
}
