/*
    Name: Jeremy Florence
    Course: CIS 203 - Computer Science II
    Assignment: 4
    Due: 4/14/14
*/

// This is the CombinationLock class which constructs a CombinationLock object
// which simulates the turning of a combination lock. It contains a turn()
// method which handles this function. It also contains an inner ListNode 
// class.
public class CombinationLock {
    // Field
    private ListNode top;	// The ListNode that the dial is pointing at
    
    // Constructor
    // Returns: a CombinationLock object
    public CombinationLock() {
	this.top = new ListNode();
	buildLock();
	drawLock();
    }
    
    // Parameter: direction - the direction to turning
    // Parameter: target - the number of the node to turn to
    // Precondition: target <= 39 && target >= 0
    // Postcondition: top is changed to the target node and all nodes that
    //			pass through the dial have their marks changed to '+'
    public void turn(boolean direction, int target) {
	if(target > 39 || target < 0) {
	    throw new IllegalArgumentException();
	}
	clearLock();
	ListNode temp = this.top;
	if(direction) {
	    while(temp.data != target) {
		temp.mark = '+';
		temp = temp.prev;
	    }
	} else {
	    
	    while(temp.data != target) {
		temp.mark = '+';
		temp = temp.next;
	    }
	}
	this.top = temp;
	drawLock();
    }
    
    // Postcondition: the CombinationLock is printed to the console.
    public void drawLock() {
	if(top.data > 9) {
	    printSpaces(11);
	} else {
	    printSpaces(12);
	}
	System.out.println(top.data);
	printSpaces(12);
	System.out.println(top.mark);
	ListNode left = top.prev;
	ListNode right = top.next;
	for(int i = 0; i < 9; i++) {
	    printSpaces(-i+11);
	    System.out.print(left.mark);
	    printSpaces(2*i + 1);
	    System.out.println(right.mark);
	    left = left.prev;
	    right = right.next;
	}
	if(left.data < 10) {
	    printSpaces(1);
	}
	System.out.print(left.data + "" + left.mark);
	printSpaces(19);
	System.out.println(right.mark + "" + right.data);
	left = left.prev;
	right = right.next;
	for(int i = 0; i < 9; i++) {
	    printSpaces(i+3);
	    System.out.print(left.mark);
	    printSpaces(-2*i+17);
	    System.out.println(right.mark);
	    left = left.prev;
	    right = right.next;
	}
	printSpaces(12);
	System.out.println(right.mark);
	if (right.data > 9) {
	    printSpaces(11);
	} else {
	    printSpaces(12);
	}
	System.out.println(right.data);
    }
    
    // Postcondition: sets the mark of all the ListNodes back to '-'
    public void clearLock() {
	ListNode temp = this.top;
	for(int i = 0; i < 40; i++) {
	    temp.mark = '-';
	    temp = temp.next;
	}
    }
    
    // Postcondition: 40 ListNodes representing each of the numbers on the 
    //			lock are doubly linked together
    private void buildLock() {
	ListNode current = this.top;
	for(int i = 1; i < 40; i++) {
	    current.next = new ListNode(i,null, current);
	    current = current.next;
	}
	current.next = this.top;
	this.top.prev = current;
	
    }
    
    // Parameter: n - the amount of spaces to print.
    // Postcondition: n spaces are printed without changing lines.
    private void printSpaces(int n) {
	for(int i = 0; i < n; i++) {
	    System.out.print(" ");
	}
    } 
    
    // This is the ListNode class. It allows for ListNodes to be doubly linked
    // with a prev and a next field.
    private class ListNode {
	// Fields
	private int data;	// The number on the lock.
	private char mark;	// the mark representing whether or not the 
				// number passed the dial on a turn.
	private ListNode prev;	// The previous ListNode.
	private ListNode next;	// The next ListNode
	
	// Constructors
	// Returns: a ListNode with 0 for data, '-' for a mark,
	// 		and null for next and prev.
	private ListNode() {
	    this(0,null,null);
	}
	
	// Parameter: data - the number on the lock for which this node is to
	//			represent.
	// Parameter: next - the ListNode after this one.
	// Parameter: prev - the ListNode before this one.
	// Returns: a ListNode with some number between 0 and 39 for data,
	//		and '-' for a mark.
	private ListNode(int data, ListNode next, ListNode prev) {
	    this.mark = '-';
	    this.data = data;
	    this.next = next;
	    this.prev = prev;
	}
    }
}