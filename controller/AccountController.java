package exam_module2.controller;

import case_study.service.TicketService;
import exam_module2.model.AccountBank;
import exam_module2.model.CheckingAccount;
import exam_module2.model.SavingAccount;
import exam_module2.utility.NotFoundBankAccountException;
import exam_module2.utility.Validate;
import exam_module2.view.AccountView;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AccountController {

    private static final String ACCOUNTS_FILE_PATH = "src/exam_module2/store/accounts.csv";

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static int nextId = 1;
    private final AccountView view;
    private List<AccountBank> accounts = new ArrayList<>();

    public AccountController(AccountView view) {
        this.view = view;
        loadAccountsFromFile();
        if (!accounts.isEmpty()) {
            nextId = accounts.stream()
                    .mapToInt(account -> Integer.parseInt(account.getId()))
                    .max()
                    .orElse(0) + 1;
        }
    }

    public void viewAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("Khong co tai khoan nao hien co.");
        } else {
            for (AccountBank account : accounts) {
                view.displayAccount(account);
            }
        }
    }

    public void addAccount() {
        String newId = String.valueOf(nextId++);
        AccountBank newAccount = view.promptForAccountDetails(newId);
        if (newAccount != null) {
            accounts.add(newAccount);
            saveAccountsToFile();
            view.displayAddSuccess();
        } else {
            view.displayError("Loi khi them moi tai khoan. Vui long thu lai");
        }
    }

    public void deleteAccount(String accountId) {
        Scanner scanner = new Scanner(System.in);
        try {
            AccountBank accountToRemove = findAccountById(accountId);
            if (accountToRemove != null) {
                accounts.remove(accountToRemove);
                saveAccountsToFile();
                view.displayDeleteSuccess();
            }
        } catch (NotFoundBankAccountException e) {
            view.displayError(e.getMessage());
        }
    }

    private AccountBank findAccountById(String id) throws NotFoundBankAccountException {
        return accounts.stream()
                .filter(account -> account.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundBankAccountException("Tai khoan khong ton tai"));
    }

    private void saveAccountsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNTS_FILE_PATH))) {
            for (AccountBank account : accounts) {
                writer.write(serializeAccount(account));
                writer.newLine();
            }
        } catch (IOException e) {
            view.displayError("Luu file tai khoan khong thanh cong.");
        }
    }

    private void loadAccountsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                AccountBank account = deserializeAccount(line);
                if (account != null) {
                    accounts.add(account);
                }
            }
        } catch (IOException e) {
            view.displayError("Khong the doc file tai khoan.");
        }
    }

    private String serializeAccount(AccountBank account) {
        StringBuilder sb = new StringBuilder();
        if (account instanceof SavingAccount) {
            SavingAccount savings = (SavingAccount) account;
            sb.append("Savings,")
                    .append(account.getId()).append(",")
                    .append(account.getAccountHolderName()).append(",")
                    .append(account.getAccountCode()).append(",")
                    .append(account.getCreationDate().format(DATE_FORMAT)).append(",")
                    .append(savings.getDepositAmount()).append(",")
                    .append(savings.getDepositDate().format(DATE_FORMAT)).append(",")
                    .append(savings.getInterestRate()).append(",")
                    .append(savings.getTerm());
        } else if (account instanceof CheckingAccount) {
            CheckingAccount checking = (CheckingAccount) account;
            sb.append("Checking,")
                    .append(account.getId()).append(",")
                    .append(account.getAccountCode()).append(",")
                    .append(account.getAccountHolderName()).append(",")
                    .append(account.getCreationDate().format(DATE_FORMAT)).append(",")
                    .append(checking.getCardNumber()).append(",")
                    .append(checking.getBalance());
        }
        return sb.toString();
    }

    private AccountBank deserializeAccount(String data) {
        String[] parts = data.split(",");
        if (parts.length < 5) return null;

        String type = parts[0];
        String id = parts[1];
        String accountHolderName = parts[2];
        String accountCode = parts[3];
        LocalDate creationDate = LocalDate.parse(parts[4], DATE_FORMAT);

        if ("Savings".equals(type)) {
            double depositAmount = Double.parseDouble(parts[5]);
            LocalDate depositDate = LocalDate.parse(parts[6], DATE_FORMAT);
            double interestRate = Double.parseDouble(parts[7]);
            int term = Integer.parseInt(parts[8]);
            return new SavingAccount(id, accountCode, accountHolderName, creationDate,
                    depositAmount, depositDate, interestRate, term);
        } else if ("Checking".equals(type)) {
            String cardNumber = parts[5];
            double balance = Double.parseDouble(parts[6]);
            return new CheckingAccount(id, accountCode, accountHolderName, creationDate,
                    cardNumber, balance);
        }
        return null;
    }
}
