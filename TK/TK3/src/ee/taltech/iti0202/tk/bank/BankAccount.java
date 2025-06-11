package ee.taltech.iti0202.tk.bank;

public class BankAccount {

    String accountNumber;
    String owner;
    double balance;

    /**
     *  Bank constructor
     */
    public BankAccount(String owner) {
        this.owner = owner;
    }

    /**
     *  deposit
     */
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    /**
     *  withdraw
     */
    public boolean withdraw(double amount) {
        if (amount > 0 && balance - amount >= 0) {
            balance -= amount;
            return true;
        }
        return false;
    }

    /**
     *  get account
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     *  get balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     *  get status
     */
    public String toString() {
        StringBuilder status = new StringBuilder();
        status.append("Account: ").append(accountNumber)
                .append(", Owner: ").append(owner)
                .append(", Balance: €").append(balance);

        return status.toString().trim();
    }
}
