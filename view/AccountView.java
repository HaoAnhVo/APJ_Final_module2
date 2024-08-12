package exam_module2.view;

import case_study.service.UserService;
import exam_module2.model.AccountBank;
import exam_module2.model.CheckingAccount;
import exam_module2.model.SavingAccount;
import exam_module2.utility.Validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AccountView {

    private final Validate validate;
    private final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public AccountView(Validate validate) {
        this.validate = validate;
    }

    public void displayAccountDetails(AccountBank account) {
        System.out.println("ID: " + account.getId());
        System.out.println("Ma tai khoan: " + account.getAccountCode());
        System.out.println("Ten chu tai khoan: " + account.getAccountHolderName());
        System.out.println("Ngay tao tai khoan: " + account.getCreationDate());

        if (account instanceof SavingAccount) {
            SavingAccount savings = (SavingAccount) account;
            System.out.println("So tien gui tiet kiem: " + savings.getDepositAmount());
            System.out.println("Ngay gui tiet kiem: " + savings.getDepositDate());
            System.out.println("Lai suat: " + savings.getInterestRate());
            System.out.println("Ky han: " + savings.getTerm());
        } else if (account instanceof CheckingAccount) {
            CheckingAccount checking = (CheckingAccount) account;
            System.out.println("So the: " + checking.getCardNumber());
            System.out.println("So du tai khoan: " + checking.getBalance());
        }
    }

    public AccountBank promptForAccountDetails(String id) {
        String accountCode = validate.promptForInput("Nhap ma tai khoan:");

        String accountHolderName = validate.promptForValidName("Ho ten chu tai khoan:");

        LocalDate creationDate = validate.promptForDate("Nhap ngay tao the (yyyy-MM-dd):");

        System.out.println("Lựa chọn định dạng tài khoản:");
        System.out.println("1. Tiet kiem");
        System.out.println("2. Thanh toan");
        System.out.print("Nhap lua chon cua ban: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> {
                double depositAmount = validate.promptForPositiveDouble("Nhap so tien gui tiet kiem:");
                LocalDate depositDate = validate.promptForDate("Nhap ngay tao tai khoan tiet kiem (yyyy-MM-dd):");
                double interestRate = validate.promptForPositiveDouble("Lai suat cua ngan hang:");
                int term = validate.promptForPositiveInt("Nhap ky han gui tiet kiem (thang):");
                return new SavingAccount(id, accountCode, accountHolderName, creationDate,
                        depositAmount, depositDate, interestRate, term);
            }
            case 2 -> {
                String cardNumber = validate.promptForInput("Nhap so the:");
                double balance = validate.promptForPositiveDouble("Nhap so tien trong tai khoan:");
                return new CheckingAccount(id, accountCode, accountHolderName, creationDate,
                        cardNumber, balance);
            }
            default -> {
                System.out.println("Lua chon khong hop le.");
                return null;
            }
        }
    }

    public void displayAddSuccess() {
        System.out.println("Them moi tai khoan thanh cong.");
    }

    public void displayDeleteConfirmation(String accountId) {
        System.out.println("Bạn chắc rằng muốn xóa tài khoản có ID " + accountId + "? (yes/no)");
    }

    public boolean promptForDeleteConfirmation() {
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }

    public void displayDeleteSuccess() {
        System.out.println("Xóa tài khoản thành công.");
    }

    public void displayError(String message) {
        System.out.println("Lỗi: " + message);
    }


    public void displayAccount(AccountBank account) {
        System.out.println("ID tai khoan: " + account.getId());
        System.out.println("Ma tai khoan: " + account.getAccountCode());
        System.out.println("Ten chu tai khoan: " + account.getAccountHolderName());
        System.out.println("Ngay tao tai khoan: " + account.getCreationDate().format(DATE_FORMAT));
        if (account instanceof SavingAccount) {
            SavingAccount savings = (SavingAccount) account;
            System.out.println("So tien gui tiet kiem: " + savings.getDepositAmount());
            System.out.println("Ngay gui tiet kiem: " + savings.getDepositDate().format(DATE_FORMAT));
            System.out.println("Lai suat ngan hang: " + savings.getInterestRate());
            System.out.println("Ky han: " + savings.getTerm());
        } else if (account instanceof CheckingAccount) {
            CheckingAccount checking = (CheckingAccount) account;
            System.out.println("So the: " + checking.getCardNumber());
            System.out.println("So tien trong the: " + checking.getBalance());
        }
        System.out.println();
    }

}
