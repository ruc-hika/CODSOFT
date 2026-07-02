import java.util.Random;
import java.util.Scanner;

/**
 * Number Guessing Game
 * --------------------
 * Features:
 *  - Random number generation within a range (1–100)
 *  - Limited attempts per round
 *  - Multiple rounds option
 *  - Score tracking based on successful guesses
 *  - Professional structure with clear methods
 */
public class NumberGame {

    private static final int MAX_ATTEMPTS = 7;
    private static final int RANGE = 100;
    private int score;
    private final Scanner scanner;
    private final Random random;

    public NumberGame() {
        this.score = 0;
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    public void start() {
        System.out.println("🎮 Welcome to the Number Guessing Game!");
        boolean playAgain = true;

        while (playAgain) {
            playRound();
            System.out.println("🏆 Current Score: " + score);
            playAgain = askPlayAgain();
        }

        System.out.println("\nGame Over! Final Score: " + score);
        scanner.close();
    }

    private void playRound() {
        int numberToGuess = random.nextInt(RANGE) + 1;
        int attemptsLeft = MAX_ATTEMPTS;
        boolean guessedCorrectly = false;

        System.out.println("\nI have chosen a number between 1 and " + RANGE + ".");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it!");

        while (attemptsLeft > 0 && !guessedCorrectly) {
            int userGuess = getUserGuess();
            attemptsLeft--;

            if (userGuess == numberToGuess) {
                System.out.println("✅ Correct! You guessed the number!");
                guessedCorrectly = true;
                score++;
            } else if (userGuess > numberToGuess) {
                System.out.println("📈 Too high! Attempts left: " + attemptsLeft);
            } else {
                System.out.println("📉 Too low! Attempts left: " + attemptsLeft);
            }
        }

        if (!guessedCorrectly) {
            System.out.println("❌ Out of attempts! The number was: " + numberToGuess);
        }
    }

    private int getUserGuess() {
        while (true) {
            System.out.print("Enter your guess (1–" + RANGE + "): ");
            if (scanner.hasNextInt()) {
                int guess = scanner.nextInt();
                if (guess >= 1 && guess <= RANGE) {
                    return guess;
                } else {
                    System.out.println("⚠️ Please enter a number within the range 1–" + RANGE + ".");
                }
            } else {
                System.out.println("⚠️ Invalid input. Please enter a valid integer.");
                scanner.next(); // clear invalid input
            }
        }
    }

    private boolean askPlayAgain() {
        System.out.print("\nDo you want to play another round? (yes/no): ");
        String response = scanner.next().trim().toLowerCase();
        return response.equals("yes");
    }

    public static void main(String[] args) {
        new NumberGame().start();
    }
}
