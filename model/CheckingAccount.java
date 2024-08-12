package exam_module2.model;

import java.time.LocalDate;
import java.util.Date;

public class CheckingAccount extends AccountBank{
    private String cardNumber;
    private double balance;

    public CheckingAccount(String id, String accountCode, String accountHolderName, LocalDate creationDate,
                           String cardNumber, double balance) {
        super(id, accountCode, accountHolderName, creationDate);
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "cardNumber='" + cardNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
