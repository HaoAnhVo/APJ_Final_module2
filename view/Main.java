package exam_module2.view;

import exam_module2.controller.AccountController;
import exam_module2.utility.Validate;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Validate validate = new Validate();
        AccountView view = new AccountView(validate);
        AccountController controller = new AccountController(view);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Them moi");
            System.out.println("2. Xoa");
            System.out.println("3. Xem danh sach cac tai khoan ngan hang");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                controller.addAccount();
            } else if (option == 2) {
                System.out.print("Nhap ID tai khoan muon xoa: ");
                String accountId = scanner.nextLine();
                controller.deleteAccount(accountId);
            } else if(option == 3) {
                System.out.println("Danh sach tai khoan:");
                controller.viewAccounts();
            }
            else if (option == 4) {
                break;
            } else {
                System.out.println("Lua chon khong hop le.");
            }
        }
    }
}
