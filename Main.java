import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        /*
        Casino casaBlanca = new Casino();
        Account acc1 = new Account("PaulTheJavaDev", "HelloWorld!", 10000);
        Account acc2 = new Account("Yayayayay", "HelloWorld!", 10000);
        Account acc3 = new Account("PaulTheJavaDev", "HelloWorld!", 10000);


        out.println(Account.users);
         */

        /*
        Account acc1 = new Account("PaulTheJavaDev", "HelloWorld!", 10000);
        int password = acc1.returnBalance();
        out.println(password);

        Casino.createAccount();
         */
        //Casino.createAccount();

        while (true) {
            System.out.println("\nWelcome to the Casino! Choose an option:");
            System.out.println("1. Create Account\n2. Play Blackjack\n3. Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Casino.createAccount();
                case "2":
                    Casino.playBlackJack();
                case "Quit":
                    System.out.println("See you soon!");
                    return; //better than System.exit(0);
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

}
