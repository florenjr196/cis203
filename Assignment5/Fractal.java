/*
    Name: Jeremy Florence
    Course: CIS 203 - Computer Science II
    Assignment: 5
    Due: 3/5/14
*/

// This is the class for the Fractal object. It constructs a string
// representation of the fractal figure which can be printed out.
public class Fractal {
    private String figure;

    // Constructor
    // Parameter: level - an integer representing the level in the figure
    public Fractal(int level) {
	this.figure = getFigure(level, 0);
    }
    
    // Parameter: level - an integer representing the level in the figure
    // Parameter: padding - an integer representing the amount of padding
    // Returns: a string representation of the figure.
    private String getFigure(int level, int padding) {
	String s = "";
	
	// Base case
	if(level == 0) {
	    for(int i = 0; i < padding; i++) {
		s += " ";
	    }
	    s += "*\n";
	//Recursive case
	} else {
	    s += getFigure((level - 1), padding)
		 + getStars((int)Math.pow(2, level), padding)
		 + getFigure((level -1), padding + 
			      (int)Math.pow(2, (level-1)));
	}
	return s;
    }
    
    // Parameter: num - the number of stars to add to the string.
    // Parameter: padding - the amount of padding to addd to the string.
    // Returns: a string consisting of stars and spaces.
    private String getStars(int num, int padding) {
	String s = "";
	for(int i = 0; i < padding; i++) {
	    s += " ";
	}
	for(int i = 0; i < num; i++) {
	    s += "*";
	}
	s += "\n";
	return s;
    }
    
    // Returns: a string representation of the Fractal.
    public String toString() {
	return this.figure;
    }
}