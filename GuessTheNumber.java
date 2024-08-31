package GuessNumber;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        boolean playAgain = true;
        int round = 1;
        int totalScore = 0;
        
        System.out.println("Welcome to the Guess the Number Game!");

        while (playAgain) {
            int attempts = 0;
            int score = 0;
            int maxAttempts = 5;
            int randomNumber = random.nextInt(100) + 1;

            System.out.println("\nRound " + round + ":");
            System.out.println("I have picked a number between 1 and 100. Try to guess it!");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    score = maxAttempts - attempts + 1; 
                    totalScore += score;
                    break;
                } else if (userGuess < randomNumber) {
                    System.out.println("Your guess is too low.");
                } else {
                    System.out.println("Your guess is too high.");
                }

                System.out.println("Attempts left: " + (maxAttempts - attempts));
            }
            if (attempts == maxAttempts) {
                System.out.println("You've run out of attempts! The number was: " + randomNumber);
            }
            System.out.println("Your score for this round: " + score);
            System.out.println("Total score: " + totalScore);
            System.out.print("Do you want to play another round? (yes/no): ");
            String userResponse = scanner.next();
            playAgain = userResponse.equalsIgnoreCase("yes");

            if (playAgain) {
                round++;
            } else {
                System.out.println("Thank you for playing! Your final score is: " + totalScore);
            }
        }

        scanner.close();
    }
}
