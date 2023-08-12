import java.util.Random;
import java.util.Scanner;

public class project2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1; // Lower bound of the range
        int upperBound = 100; // Upper bound of the range
        int maxAttempts = 10; // Maximum number of attempts for each round
        int totalRounds = 3; // Total number of rounds

        int totalScore = 0;

       try{
        for (int round = 1; round <= totalRounds; round++) {
            clearScreen();
    
            System.out.println("======================================");
            System.out.println("          Guess the Number!");
            System.out.println("======================================");
            System.out.println("Welcome to the Guess the Number game!");
            System.out.println("You have " + totalRounds + " rounds to earn the highest score.");
            System.out.println("\nGuess the number between " + lowerBound + " and " + upperBound);
            pressEnterToContinue(scanner);
            System.out.println("\n======================================");
            System.out.println("             Round " + round);
            System.out.println("======================================");
            int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;

            

            while (attempts < maxAttempts) {
                System.out.print("\nAttempt " + (attempts + 1) + ": Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == targetNumber) {
                    System.out.println("\n======================================");
                    System.out.println("        Congratulations!");
                    System.out.println("You guessed the correct number.");
                    int points = maxAttempts - attempts + 1;
                    totalScore += points;
                    System.out.println("You earned " + points + " points in this round.");
                    System.out.println("======================================");
                  pressEnterToContinue(scanner);
                    break;
                } else if (guess < targetNumber) {
                    System.out.println("The number is higher than your guess. Try again.");
                } else {
                    System.out.println("The number is lower than your guess. Try again.");
                }

                // Display hints for Level 1
                if (attempts >= 3) {
                    int difference = Math.abs(guess - targetNumber);
                    if (difference > 10) {
                        System.out.println("Hint: You are more than 10 points away from the number.");
                    } else if (difference < 10) {
                        System.out.println("Hint: You are 10 points or less away from the number.");
                    }
                }

                // Check if the number is divisible by 2, 3, 5, 7, 9, or 10
                if (attempts >= 5) {
                    if (targetNumber % 2 == 0) {
                        System.out.println("Hint: The number is divisible by 2.");
                    }
                    if (targetNumber % 3 == 0) {
                        System.out.println("Hint: The number is divisible by 3.");
                    }
                    if (targetNumber % 5 == 0) {
                        System.out.println("Hint: The number is divisible by 5.");
                    }
                    if (targetNumber % 7 == 0) {
                        System.out.println("Hint: The number is divisible by 7.");
                    }
                    if (targetNumber % 9 == 0) {
                        System.out.println("Hint: The number is divisible by 9.");
                    }
                    if (targetNumber % 10 == 0) {
                        System.out.println("Hint: The number is divisible by 10.");
                    }
                }
            }

            if (attempts > maxAttempts) {
                System.out.println("======================================");
                System.out.println("       Round " + round + " Over");
                System.out.println("You couldn't guess the number in this round.");
                System.out.println("The correct number was: " + targetNumber);
                System.out.println("======================================");
               pressEnterToContinue(scanner);
                
            }

             if (round < totalRounds) {
                 System.out.println("Press Enter to proceed to the next round.");
                 scanner.nextLine();

             }
        }

        System.out.println("\n======================================");
        System.out.println("           Game Over");
        System.out.println("Your total score: " + totalScore);
        System.out.println("======================================\n");
    }
    catch(Exception e)
    {
        System.out.println("Sorry, some error occured!!!");
    }
    finally
    {
        System.out.println("\n\nThanks for using this program...\nPress enter to exit...");
        scanner.nextLine();

    }
}
    private static void clearScreen() {
        System.out.print("\033[H\033[2J"); // to clear the screen
        System.out.flush();
    }

    private static void pressEnterToContinue(Scanner scanner) {
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }
}
