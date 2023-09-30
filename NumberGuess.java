import java.util.Random;
import java.util.Scanner;

public class NumberGuess {
    public static void main(String[] args) {
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 5;
        int score = 0;

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println(" ");
        System.out.println("Welcome to Number Guessing Game!");
        System.out.println("I'm thinking of a number between " + minRange + " and " + maxRange);
        System.out.println("You have " + maxAttempts + " attempts to guess the number.");

        int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            System.out.print("Attempt " + attempt + ": Enter your guess: ");
            int guess = scanner.nextInt();

            if (guess == randomNumber) {
                System.out.println("Congratulations! You guessed the number correctly.");
                score = maxAttempts - attempt + 1;
                break;
            } else if (guess < randomNumber) {
                System.out.println("Hint:Too low! Try again.");
            } else {
                System.out.println("Hint:Too high! Try again.");
            }
        }

        if (score > 0) {
            System.out.println("Your score is " + score);
        } else {
            System.out.println("Game over! The number was " + randomNumber);
        }
    }
}
