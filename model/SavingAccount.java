package exam_module2.model;

import java.time.LocalDate;

public class SavingAccount extends AccountBank{
    private double depositAmount;
    private LocalDate depositDate;
    private double interestRate;
    private int term;

    public SavingAccount(String id, String accountCode, String accountHolderName, LocalDate creationDate,
                          double depositAmount, LocalDate depositDate, double interestRate, int term) {
        super(id, accountCode, accountHolderName, creationDate);
        this.depositAmount = depositAmount;
        this.depositDate = depositDate;
        this.interestRate = interestRate;
        this.term = term;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public LocalDate getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(LocalDate depositDate) {
        this.depositDate = depositDate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "SavingAccount{" +
                "depositAmount=" + depositAmount +
                ", depositDate=" + depositDate +
                ", interestRate=" + interestRate +
                ", term=" + term +
                '}';
    }
}
