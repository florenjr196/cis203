import java.util.*;

public class Demo1 {

    public static void main (String [] args) {
        System.out.print("Enter a postfix expression: ");
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();
        System.out.println(expression + " = " + evaluate(expression));
    }

    public static int evaluate (String exp) {
        //open a Scanner on the string exp
        Scanner sc = new Scanner(exp);
	// construct a Stack (we are using Java's Stack class here)
        Stack<Integer> rands = new Stack<Integer>();

        while (sc.hasNext()) {
            if(sc.hasNextInt()) {
		rands.push(sc.nextInt());
	    } else {
		char c = sc.next().charAt(0);
		int rand1 = rands.pop();
		int rand2 = rands.pop();
		rands.push(evaluate(rand2, rand1, c));
	    }
	}
        return rands.pop();
    }

    public static int evaluate(int rand1, int rand2, char op) {
        if (op == '+')
	    return rand1 + rand2;
        else if (op == '-')
	    return rand1 - rand2;
        else if (op == '*')
	    return rand1 * rand2;
        else
            return rand1 / rand2;
    }
}
