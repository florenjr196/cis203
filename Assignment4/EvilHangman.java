/*
      Name: Jeremy Florence
      Course: CIS 203 - Computer Science II
      Assignment: 4
      Due: 2/24/14
*/

// This is the class for the EvilHangman game. It constructs an EvilHangman 
// object based on parameters based on user input. The core function of this
// class is the play() method which starts the game.
import java.util.*;
public class EvilHangman {
    public int length;		// the length of the word to guess
    public int guesses;		// the number of times a player may guess
    public boolean reportWords;		// whether or not the number of 
					// possible words should be reported
    public ArrayList<String> words;	// the list of words
    public String currentPattern;	// the current pattern
    public HashSet<Character> guessedLetters;		// the letters guessed
							// so far
    
    // Constructor
    // Parameter: l - an int representing the length of the word to guess.
    // Parameter: g - an int representing the number of guesses.
    // Parameter: r - a boolean to determine whether or not the number of
    //			possible words should be reported.
    // Parameter: list - the list of possible words.
    // Returns: an EvilHangman object.
    public EvilHangman(int l, int g, boolean r, ArrayList<String> list) {
	this.length = l;
	this.guesses = g;
	this.reportWords = r;
	this.words = list;
	this.currentPattern = "";
	while(this.currentPattern.length() < this.length) {
	    this.currentPattern += "-";
	}
	guessedLetters = new HashSet<Character>();
    }
    
    // Starts the game of EvilHangman.
    // Postcondition: If the player won, "You win!" is printed to the console",
    //			otherwise, "You lost." is printed.
    public void play() {
      Scanner userInput = new Scanner(System.in);
      do {
	  System.out.println("Number of guesses left: " + this.guesses);
	  if(reportWords) {
	      System.out.println("Possible words left: " + this.words.size());
	  }
	  System.out.println("Current pattern: " + this.currentPattern);
	  System.out.print(">>");
	  String guess = userInput.next();
	  
	  while (invalidInput(guess)) {
	      System.out.print(">>");
	      guess = userInput.next();
	  }
	  this.guessedLetters.add(guess.charAt(0));
	  this.guesses--;
	  this.currentPattern = combine(this.currentPattern, 
					    getPattern(guess.charAt(0)));
      } while (this.guesses > 0 && this.currentPattern.contains("-"));
      
      if (this.words.size() == 1 &&  
		  this.currentPattern.equals(this.words.get(0))) {
	  System.out.println(this.currentPattern);	
	  System.out.println("You win!");
      } else {
	  System.out.println("You lost.");
	  if(this.reportWords) {
	      System.out.println("Words still under consideration...");
	      System.out.println(this.words);
	  } else {
	      System.out.println("The word was: " + randomWord());
	  }
      }
    }
    
    // Parameter: letter - the letter the player guessed.
    // Precondition: letter is a sing, lowercase alphabetical letter.
    // Returns: A string representing a pattern for a list of words.
    private String getPattern(char letter) {
	HashMap<String, ArrayList<String>> families = getFamilies(letter);
	int max = 0;
	String family = "";
	for(String pattern : families.keySet()) {
	    if(families.get(pattern).size() > max ) {
		max = families.get(pattern).size();
		family = pattern;
	    }
	}
	
	if(max == 1 && families.size() > 1) {
	    for(String pattern : families.keySet()) {
		if(!(contains(pattern, letter))) {
		    family = pattern;
		}
	    }
	}
	this.words = families.get(family);
	return family;
    }
    
    // Parameter: letter - the letter the player guessed.
    // Returns: A map of patterns(strings) to lists of words 
    // that match those patterns.
    private HashMap<String, ArrayList<String>> getFamilies(char letter) {
	HashMap<String, ArrayList<String>> families = new HashMap<String,
							ArrayList<String>>();
	Iterator<String> itr = this.words.iterator();
	
	while(itr.hasNext()) {
	    String word = itr.next();
	    String pattern = "";
	    for(int i = 0; i < this.length; i++) {
		if(word.charAt(i) == letter) {
		    pattern += letter;
		} else {
		    pattern += "-";
		}
	    }
	    
	    pattern = combine(this.currentPattern, pattern);
	    
	    if (families.containsKey(pattern)) {
		families.get(pattern).add(word);
	    } else {
		ArrayList<String> newFamily = new ArrayList<String>();
		families.put(pattern, newFamily);
		families.get(pattern).add(word);
	    }
	}
	
	return families;
    }
    
    // Parameter: pattern1 - A string representing a pattern.
    // Paremeter: pattern2 - A string representing another pattern.
    // Returns: a string which is the combination of the two patterns.
    private String combine(String pattern1, String pattern2) {
	String newPattern = "";
	for(int i = 0; i < this.length; i++) {
	    if(pattern2.charAt(i) == '-') {
		newPattern += pattern1.charAt(i);
	    } else {
		newPattern += pattern2.charAt(i);
	    }
	}
	return newPattern;
    }
    
    // Returns: a random word.
    private String randomWord() {
	Random rand = new Random();
	return this.words.get(rand.nextInt(this.words.size()));
    }
    
    // Parameter: pattern - a string representing a pattern associated with
    //				a list of words.
    // Parameter: letter - the letter the player guessed.
    // Returns: true if the letter is in the pattern, false otherwise.
    private boolean contains(String pattern, char letter) {
	for(int i = 0; i < pattern.length(); i++) {
	    if(pattern.charAt(i) == letter) {
		return true;
	    }
	}
	return false;
    }
    
    // Parameter: ch - a character entered by the player.
    // Returns: true if the character is an alphabetical letter, false
    // 		otherwise.
    private boolean isLetter(char ch) {
	return(toLowerCase(ch) >= 'a' && toLowerCase(ch) <= 'z');
    }
    
    // Parameter: ch - a character entered by the player.
    // Returns: a lowercase alphabetical letter, assuming ch
    // 		is an alphabetical letter at all.
    private char toLowerCase(char ch) {
	if (ch >= 'A' && ch <= 'Z') {
	    return (char)(ch - 'A' + 'a');
	}
	return ch;
      
    }
    
    // Parameter: guess - A string representation of the player's guess.
    // Returns: True if there is something unacceptable about the player's
    //		input, false otherwise.
    private boolean invalidInput(String guess) {
	if(guess.length() > 1) {
	    System.out.println("Please enter a single letter.");
	    return true;
	}
	if(!isLetter(guess.charAt(0))) {
	    System.out.println("Please enter an alphabetical letter.");
	    return true;
	}
	if(this.guessedLetters.contains(guess.charAt(0))) {
	    System.out.println("Already guessed.");
	    return true;
	}
	return false;
    }
}