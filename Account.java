public class Account {
    private String id;
    private double balance;

    public Account(String id, double initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public double getBalance() { return balance; }
    public void deposit(double amount) { balance += amount; }
}