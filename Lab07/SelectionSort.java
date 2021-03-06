import java.util.*;

public class SelectionSort implements Sorter {

    // fields
    private int [] a;
    private int comparisons;
    private int moves;

    public SelectionSort (int [] a) {
	this.a = a;
    }


     // Rearranges the elements of a into sorted order using
     // the selection sort algorithm.
    public  void sort() {
        for (int i = 0; i < a.length - 1; i++) {
            // find index of smallest remaining value
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                this.comparisons++;
		if (a[j] < a[min]) {
                    min = j;
		}
	    }
            // swap smallest value its proper place, a[i]
            swap(i, min);
	    this.moves += 3;
	}
	System.out.println(this.comparisons);
	System.out.println(this.moves);
    }

    // Swaps a[i] with a[j].
    public  void swap(int i, int j) {
           int temp = a[i];
           a[i] = a[j];
           a[j] = temp;
    }
}
