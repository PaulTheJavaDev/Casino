import java.util.HashMap;
import java.util.Scanner;

public class Account {

    static Scanner scanner = new Scanner(System.in);
    
    /*
    users saves: username and password
    doesn't allow any duplicates
     */
    public static HashMap<String, String> users = new HashMap<>();

    private int balance;
    private String username;
    private String password;

    public Account(String username, String password, int balance) {
        this.balance = balance;
        this.password = password;
        this.username = username;

        users.put(username, password);
    }

    //getters work without the keyword: this
    public String returnPassword() {
        return password;
    }

    public String returnUsername() {
        return username;
    }

    public int returnBalance() {
        return balance;
    }

    public int withdrawMoney(int money) {
        return balance -= money;
    }

    public int depositMoney(int money) {
        return balance += money;
    }
}
