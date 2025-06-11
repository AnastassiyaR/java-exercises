package ee.taltech.iti0202.tk.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bank {
    List<BankAccount> accounts = new ArrayList<>();

    /**
     *  Bank account constructor
     */
    public BankAccount openAccount(String owner) {
        for (BankAccount i : accounts) {
            if (Objects.equals(i.owner, owner)) {
                return null;
            }
        }
        BankAccount newer = new BankAccount(owner);
        accounts.add(newer);
        return newer;
    }

    /**
     *  Checking account
     */
    public boolean checking(BankAccount bankAccount) {
        int count = 0;

        for (BankAccount i : accounts) {
            if (Objects.equals(i.accountNumber, bankAccount.accountNumber)) {
                count++;
            }
        }
        if (count == 1) {
            return true;
        }
        return false;
    }

    /**
     *  Find account
     */
    public BankAccount findAccount(String accountNumber) {
        for (BankAccount i : accounts) {
            if (Objects.equals(i.accountNumber, accountNumber)) {
                return i;
            }
        }
        return null;
    }

    /**
     *  Deposit account
     */
    public boolean depositToAccount(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);

        if (account != null && checking(account) && findAccount(accountNumber) != null) {
            if (account.deposit(amount)) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     *  Withdraw account
     */
    public boolean withdrawFromAccount(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        if (account != null && checking(account) && findAccount(accountNumber) != null) {
            if (account.withdraw(amount)) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     *  List of accounts
     */
    public List<BankAccount> listAccounts() {
        return accounts;
    }
}
