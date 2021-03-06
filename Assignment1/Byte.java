/*
    Name: Jeremy Florence
    Course: CIS 203 - Computer Science II
    Assignment: 1
    Due: 1/29/14
*/


// This is the class for the Byte object which has one field which is
// an int array of length 8 for each individual bit. Bytes are constructed
// by converting a decimal value to binary. Bytes may be added, subtracted, 
// negated, rotated, xor-ed and accessed to return their decimal value.
public class Byte {
    // Fields
    int [] bits;
    
    // Constructors
    
    // Parameter: n - a decimal number to be converted to binary
    // Returns: Byte object representing the binary form of n.
    public Byte(int n) {
	this.bits = new int[8];
	this.set(n);
	
    }
    
    // Default constructor to create a byte with the value of zero.
    public Byte() {
	this(0);
    }
    
    //Mutators
    
    // Parameter: other - another byte to add to this one
    // Postcondition: This byte gets the binary value 
    //			of the other byte added to it.
    public void add(Byte other) {
	int carry = 0;
	for(int i = this.bits.length - 1; i >= 0; i--) {
	    int tempBit = this.bits[i];
	    this.bits[i] = (this.bits[i] + other.bits[i] + carry) % 2;
	    carry = (tempBit + other.bits[i] + carry) / 2;
	}
    }
    
    // Parameter: n - a decimal value which will first be converted to a
    //		  a byte and then used as an argument to call the version
    //		  of add which adds two bytes together.
    // Postcondition: this byte gets the binary value 
    //			of an integer added to it.
    public void add(int n) {
	add(new Byte(n));
    }
    
    // Parameter: n - a decimal value to be subtracted from this byte
    //			which is done by adding the negative version of itself
    //			using the add method.
    // Postcondition: This byte gets the value of the difference of the other
    //			byte subtracted from this one.
    public void subtract (int n) {
	add(-n);
    }
    
    // Parameter: other - another byte which is first negated,
    //		  and then added using the add method.
    // Postcondition: The value of the other byte is subtracted 
    //			from this byte by negating the other byte
    //			and then adding it using the add method.
    //			The other byte is then negated again to change
    //			it back to it's original value.
    public void subtract (Byte other) {
	other.negate();
	add(other);
	other.negate();
	
    }
    
    // Postcondition: The byte is negated.
    public void negate() {
	for(int i = 0; i < this.bits.length; i++) {
	    this.bits[i] = (this.bits[i] + 1) % 2;
	}
	this.add(1);
    }
    
    // Parameter: n - The integer to converted into binary.
    // Postcondition: The bits field of this byte change to represent
    //			n in binary.
    public void set(int n) {
	if (n < 0) {
	    set(-n);
	    negate();
	} else {
	    for (int i = 0; i < this.bits.length; i++) {
		this.bits[7-i] = n % 2;
		n /= 2;
	    }
	}
    }
    
    // Parameter: other - another byte to be xored with this one
    // Postcondition: This byte's bits will change to 0 if they are
    //			the same as the other bits or 1 if they are different.
    public void xor(Byte other) {
	for(int i = 0; i < this.bits.length; i++) {
	    this.bits [i] = (this.bits[i] + other.bits[i]) % 2;
	}
    }
    
    // Postcondition: Shifts all the bits to the left, moving the highest
    //			bit to the position of the lowest one.
    public void rol() {
	int tempBit = this.bits[0];
	for (int i = 0; i < this.bits.length - 1; i++) {
	    this.bits[i] = this.bits[i+1];
	}
	this.bits[this.bits.length - 1] = tempBit;
    }
    
    // Postcondition: Shifts all the bits to the right, moving the lowest
    // 			bit to the position of the highest one.
    public void ror() {
	int tempBit = this.bits[this.bits.length - 1];
	for (int i = this.bits.length-1; i > 0 ; i--) {
	    this.bits[i] = this.bits[i-1];
	}
	this.bits[0] = tempBit;
    }
    
    //Accessors
    
    // Returns: The int value of this byte.
    public int decimalValue() {
	int n = 0;
	if (this.bits[0] == 1) {
	    negate();
	    n = -1 * (this.decimalValue());
	    negate();
	} else {
	    for (int i = 1; i < this.bits.length; i++) {
		if (this.bits[i] == 1) {
		    n += (int)(Math.pow(2,(7-i)));
		}
	    }
	}
	return n;
    }
    
    // Returns: True if the bytes are equivalent or false if they aren't.
    public boolean equals(Byte other) {
	return(this.decimalValue() == other.decimalValue());
    }
    
    // Returns: A string representation of this Byte.
    public String toString() {
	String s= "";
	for (int i = 0; i < this.bits.length; i++) {
	    s += this.bits[i];
	}
	return s;
    }
}