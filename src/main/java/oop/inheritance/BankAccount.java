package oop.inheritance;

public class BankAccount {
    private int accountNumber;
    private double balance;
    private String customerName;
    private String emailAddress;
    private int phoneNumber;

    public BankAccount(int accountNumber, double balance, String customerName, String emailAddress,
                       int phoneNumber) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerName = customerName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void depositFunds(double depositAmount) {
        this.balance += depositAmount;
    }

    public boolean withdrawFunds(double withdrawalAmount) {
        // Insufficient Funds
        if (this.balance < withdrawalAmount)
            return false;

        this.balance -= withdrawalAmount;
        return true;
    }
}
