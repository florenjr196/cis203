import java.io.*;

public class ByteDemo {
      public static void main(String [] args) {
	    PrintStream ps = System.out;
	    Byte b = new Byte(109);
	    ps.println(b);
	    b.ror();
	    ps.println(b);
	    b.rol();
	    ps.println(b);
	    
      }
}