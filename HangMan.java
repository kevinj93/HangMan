import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

public class HangMan {
    private final char[] word;
    private final String wordString;
    private final char[] wordRevealed;
//    private final char[] foundLetters = new char[20]; // max 20 characters
//    private int foundLettersCount = 0;
    private final Scanner inp = new Scanner(System.in);
    private final Player p1, p2; //DON'T FORGET TO PASS THE NEWLY CREATED PLAYERS IN CONSTRUCTOR TO PLAYERS IN HM CLASS
    private Player currentPlayer;

    public HangMan(String fileName, Player p1, Player p2) {
        String[] words = FileReader.readFile(fileName);
        int randomWordIndex = new Random().nextInt(words.length);
        String word = words[randomWordIndex];

        this.wordString = word;
        this.p1 = p1;
        this.p2 = p2;

        currentPlayer = p1;

        wordRevealed = new char[word.length()];
        this.word = new char[word.length()];

        for (int i = 0; i < word.length(); i++) {
            wordRevealed[i] = '_';
            this.word[i] = word.charAt(i);
        }
    }
    public void play(){
        System.out.println("Welcome to HangMan by Kevin!\n");

        while(true) {

            if (isGameOver()) break;
            System.out.println("The word is:\n");
            printRevealed();
            System.out.printf("It's your turn, %s!%n%nOptions:%n 1- Guess a letter%n 2- Guess the word%n 3- Show lives%n 4- exit%n", currentPlayer.getName());
            int choice = getValidChoice();

            if (choice == 1) {  // CHOICE 1

                System.out.println("Enter a character:\n");
                String next = inp.next();

                if (next.length() == 1) {
                    updateArray(next.charAt(0));
                    printRevealed();

                } else System.out.println("Invalid character. Make sure to type only one character!");

            } else if (choice == 2) {   // CHOICE 2
                System.out.println("What do you think the word is? If you guess it right, you win.\n");
                if (isCorrect(inp.next())) { //TRY MAKING THIS A FUNCTION AS WELL
                    System.out.printf("Congrats, %s!, you win!", currentPlayer.getName());
                    break;
                }
                System.out.println("Incorrect Answer!");
                decreaseLives();
                switchPlayer();

            } else if (choice == 3) {   // CHOICE 3
                printStatus();
            }
            else if (choice == 4) {     // CHOICE 4
                System.out.println("\nThanks for playing! Goodbye!");
                break;

            } else { //DEFAULT
                System.out.println("Invalid choice! Please try again!\n");
            }
        }
    }

    private void printRevealed() {
        for (char letter : wordRevealed) System.out.print(String.valueOf(letter) + ' ');
        System.out.println("\n");
    }

    private void updateArray(char letter) {
        if (isLetterInWord(letter)) {
            for (int i = 0; i < word.length; i++) {
                if (letter == word[i]) {
                wordRevealed[i] = letter;
                }
            }
        } else {
            System.out.println("Sorry, the character you have entered is not there!");
            decreaseLives();

            if (currentPlayer.getLives() > 0) switchPlayer();
        }
    }

    private boolean isLetterInWord(char letter) {
        for (char c : word)
            if (Objects.equals(c, letter)) {
//                foundLetters[foundLettersCount] = letter; FOR LATER
//                foundLettersCount++;
                return true;
            }
        return false;

    }
    private void decreaseLives(){
        currentPlayer.decreaseLives();
    }
    private void printStatus(){
        String status = "You have %d lives left!%n%n";
        System.out.printf(status, currentPlayer.getLives());
    }
    private boolean isGameOver(){
        if (currentPlayer.getLives() == 0) {
            System.out.printf("Game over, %s! You have no lives left.%n%n",currentPlayer.getName());
        return true;
        }
        else if (Arrays.equals(word, wordRevealed)) {
            System.out.printf("Congrats, %s!, you win!%n",currentPlayer.getName());
            return true;

        }
        return false;
    }
    private void switchPlayer() {
        currentPlayer = (currentPlayer == p1) ? p2 : p1;
    }
    private boolean isCorrect(String word){
        return Objects.equals(word, wordString);
    }

    private int getValidChoice() {
        int choice = 0;
        boolean isValidChoice = false;

        while (!isValidChoice) {
            try {
                choice = inp.nextInt();
                isValidChoice = true;
            } catch (Exception e) {
                System.out.println("Invalid choice! Please try again!");
                inp.next();
            }
        }

        return choice;
    }
}