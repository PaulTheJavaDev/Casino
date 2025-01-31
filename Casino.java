//might convert this to import java.util.*; //need more research
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

public class Casino {

    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);
    static HashMap<String, String> takenUsers = new HashMap<>();
    static Account currentUser;
    static boolean userCreated = false;
    //static boolean userIsLoggedIn = false;

    public static void createAccount() {
        if (userCreated) {
            System.out.println("You've already created a Account! ");
            return;
        }

        String username;
        System.out.println("What would you like your username to be?");

        while (true) {

            username = scanner.next();

            if (takenUsers.containsKey(username)) {
                System.out.println("Username is already taken! Try another one.");
            } else {
                System.out.printf("\nWelcome %s to our new Platform!", username);
                break;
            }
        }

        System.out.println("What would you like your password to be?");
        String password = scanner.next();

        takenUsers.put(username, password);
        //new Account(username, password, 0);
        userCreated = true;
        currentUser = new Account(username, password, 9999);
        Casino.playBlackJack();
    }

    public static void playBlackJack() {

        ArrayList cards = new ArrayList();

        if (!userCreated) {
            System.out.println("You need to create a Account!");
            return;
        }

        if (currentUser.returnBalance() <= 0) {
            System.out.println("Please deposit some Cash into your Bank Account to play this game!");
        }

        System.out.printf("\nWelcome %s to Blackjack. \nHow much would you like to deposit?", currentUser.returnUsername());
        int depositAmount;

        while (true) {
            depositAmount = scanner.nextInt();
            scanner.nextLine();

            if (depositAmount > currentUser.returnBalance() || depositAmount <= 0) {
                System.out.println("Insufficient Deposit Amount, please consider checking your Bank Account.");
            } else {
                currentUser.withdrawMoney(depositAmount);
                break;
            }
        }

        int cardOne = random.nextInt(1, 11) + 1;
        int cardTwo = random.nextInt(1, 11) + 1;
        cards.add(cardOne);
        cards.add(cardTwo);

        System.out.printf("Your cards are the following: %d and %d", cardOne, cardTwo);

        int cardsTotal = cardOne + cardTwo;

        if (cardsTotal == 21) {
            System.out.println("You won the game!");
            currentUser.depositMoney(depositAmount * 3);
        } else if (cardsTotal > 21) {
            System.out.println("Sorry, You've lost. Better luck next time!");
            return;
        }

        System.out.println("Your cards are in total: %d. Would you like to draw another card?");
        //continuing blackjack here
    }

}
