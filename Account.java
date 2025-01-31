import java.util.HashMap;

public class Account {

    public static HashMap<String, String> users = new HashMap<>();
    /*
    users saves: username and password
    doesn't allow any duplicates
     */

    private int balance;
    private final String username;

    public Account(String username, String password, int balance) {
        this.balance = balance;
        this.username = username;

        users.put(username, password);
    }

    //getters work without the keyword: this

    public String returnUsername() {
        return username;
    }

    public int returnBalance() {
        return balance;
    }

    public int withdrawMoney(int money) {
        return balance -= money; //value is never used?
    }

    public int depositMoney(int money) {
        return balance += money; //value is also never used?
    }
}
