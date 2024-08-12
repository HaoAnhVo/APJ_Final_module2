package exam_module2.model;

import java.time.LocalDate;

public abstract class AccountBank {
    private String id;
    private String accountCode;
    private String accountHolderName;
    private LocalDate creationDate;

    public AccountBank(String id, String accountCode, String accountHolderName, LocalDate creationDate) {
        this.id = id;
        this.accountCode = accountCode;
        this.accountHolderName = accountHolderName;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
