import java.util.ArrayList;

public class Word {
    String currentWord;
    int wordlength;
    
    public Word() {
        currentWord = "";
        wordlength = 5;
    }
    
    public Word(String word) {
        currentWord = word;
        wordlength = word.length();
    }
    
    public void addLetter(char letter) {
        currentWord = currentWord + letter;
    }
    
    public void deleteLetter() {
        if (currentWord.length() > 0) {
            currentWord = currentWord.substring(0,currentWord.length() -1);
        }
    }
    
    public boolean isFiveChars() {
        return currentWord.length() == wordlength;
    }
    
    public String toString() {
        return currentWord;
    }
    
    public boolean contains(String letters) {
        return currentWord.contains(letters);
    }
    
    public int length() {
        return currentWord.length();
    }
    
    private int numberOfEqualLetters(char letter) {
        int counter = 0;
        
        for (int i = 0; i < currentWord.length() ; i = i + 1) {
            if (currentWord.charAt(i) == letter) {
                counter = 1 + counter;
            }
        }
        
        return counter;       
    }
    
    private boolean letterIsUnique(char letter) {
        return numberOfEqualLetters(letter) <= 1;
    }
    
    private ArrayList<Integer> getIndexOf(char letter) {
        int index = currentWord.indexOf(letter);
        ArrayList<Integer> occurrences = new ArrayList<>();
        while(index >= 0) {
            occurrences.add(index);
            index = currentWord.indexOf(letter,index+1);
        }
        return occurrences;
    }

}