package models;

public class AccountModel {
    private int accountID;
    private double accountBalance;
    private String accountType;

    public AccountModel(int accountID) {
        this.accountID = accountID;
        this.accountBalance = accountBalance;
        this.accountType = accountType;
    }




    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public double getAccountBalance(double accountBalance) {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountBalance2() {
        this.accountBalance = accountBalance;
        return String.valueOf(accountBalance);
    }
}

