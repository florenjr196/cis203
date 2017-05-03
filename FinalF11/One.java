
public class One {

    public static void main (String [] args) {

	ArrayIntList a = new ArrayIntList();
        System.out.println("Size of empty list is: " + a.size());
        for (int i = 1; i <= 20; i++)
	    a.add(i);
        System.out.println("Size of list now is: " + a.size());
	try {
	    a.add(21);
	} catch (IllegalStateException e) {
	    System.out.println(e + " : We are out of room in this list!");
	}
	System.out.println("Removing " + a.remove() + " and size of list "
			  + "is now " + a.size());
        for(int i = 1; i <= 19; i++)
	    a.remove();
        System.out.println("Size of list now is: " + a.size());
	try {
	    a.remove();
	} catch (IllegalStateException e) {
	    System.out.println(e + " : We tried to remove from an empty list!");
	}
    }


}
