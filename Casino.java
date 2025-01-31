import java.util.*;

public class Casino {

    static final Random random = new Random();
    static final Scanner scanner = new Scanner(System.in);
    static final HashMap<String, String> takenUsers = new HashMap<>();
    static Account currentUser;
    static boolean userCreated = false;

    public static void createAccount() {
        if (userCreated) {
            System.out.println("You've already created an account!");
            return;
        }

        System.out.println("Enter a username:");
        String username;
        while (true) {
            username = scanner.nextLine().trim();
            if (takenUsers.containsKey(username)) {
                System.out.println("Username is taken! Try another one.");
            } else {
                System.out.printf("\nWelcome, %s, to our platform!\n", username);
                break;
            }
        }

        System.out.println("Enter a password:");
        String password = scanner.nextLine();
        
        takenUsers.put(username, password);
        userCreated = true;

        //Test Account
        currentUser = new Account(username, password, 9999);
    }

    public static void playBlackJack() {

        /*
         Checking if the user is able to play the game
         - user must have created an Account
         - user must have sufficient balance
        */

        if (!userCreated) {
            System.out.println("You need to create an account first!");
            return;
        }

        if (currentUser.returnBalance() <= 0) {
            System.out.println("Please deposit some cash to play!");
            return;
        }

        System.out.printf("\nWelcome %s to Blackjack. How much would you like to bet?\n", currentUser.returnUsername());

        int depositAmount;
        while (true) {
            try {
                depositAmount = Integer.parseInt(scanner.nextLine());
                if (depositAmount > 0 && depositAmount <= currentUser.returnBalance()) {
                    currentUser.withdrawMoney(depositAmount);
                    break;
                } else {
                    System.out.println("Invalid amount. Check your balance and enter a valid bet.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        //storing the drawn cards
        List<Integer> cards = new ArrayList<>();

        int cardOne = drawCard(), cardTwo = drawCard();
        cards.add(cardOne);
        cards.add(cardTwo);

        //display cards
        int playerTotal = cardOne + cardTwo;
        System.out.printf("Your cards: %d and %d (Total: %d)\n", cardOne, cardTwo, playerTotal);

        if (playerTotal == 21) {
            System.out.println("Blackjack! You win!");
            currentUser.depositMoney(depositAmount * 3);
            return;
        }

        while (playerTotal < 21) {
            System.out.println("Your total is " + playerTotal + ". Would you like to draw another card? (yes/no)");
            String decision = scanner.nextLine().trim();

            if (!decision.equalsIgnoreCase("yes")) {
                break;
            }

            int newCard = drawCard();
            cards.add(newCard);
            playerTotal += newCard;

            System.out.printf("You drew a %d. New total: %d\n", newCard, playerTotal);
        }

        if (playerTotal > 21) {
            System.out.println("Bust! You lose.");
            return;
        }

        //implement dealer //earlier debug haha

        int dealerCardOne = drawCard(), dealerCardTwo = drawCard();
        int dealerTotal = dealerCardOne + dealerCardTwo;
        System.out.printf("Dealer's cards: %d and %d (Total: %d)\n", dealerCardOne, dealerCardTwo, dealerTotal);

        while (dealerTotal <= 17) {
            int newCard = drawCard();
            dealerTotal += newCard;
            System.out.println("Dealer draws a " + newCard + ". New total: " + dealerTotal);
        }

        System.out.println("Final Dealer Total: " + dealerTotal);

        //determine the winner
        if (dealerTotal > 21 || playerTotal > dealerTotal) {
            System.out.println("You Won!");
            currentUser.depositMoney(depositAmount * 3);
        } else if (playerTotal < dealerTotal) {
            System.out.println("You lost.");
        } else {
            currentUser.depositMoney(depositAmount);
            System.out.println("It's a tie! You get your money back.");
        }
    }

    private static int drawCard() {
        return random.nextInt(10) + 1;
    }
}
