package exam_module2.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validate {

    private final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");

    public Validate() {
    }

    public String promptForInput(String prompt) {
        String input;
        do {
            System.out.println(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Truong nay la bat buoc.");
            }
        } while (input.isEmpty());
        return input;
    }

    public String promptForValidName(String prompt) {
        String name;
        while (true) {
            System.out.println(prompt);
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Ten la thong tin bat buoc.");
            } else if (!NAME_PATTERN.matcher(name).matches()) {
                System.out.println("Ten khong hop le. Chi chap nhan chu cai va khoang trang.");
            } else {
                break;
            }
        }
        return name;
    }

    public LocalDate promptForDate(String prompt) {
        LocalDate date = null;
        while (date == null) {
            System.out.println(prompt);
            String dateInput = scanner.nextLine().trim();
            try {
                date = LocalDate.parse(dateInput, DATE_FORMAT);
            } catch (Exception e) {
                System.out.println("Sai dinh dang ngay thang. Hay su dung dinh dang yyyy-MM-dd.");
            }
        }
        return date;
    }

    public double promptForPositiveDouble(String prompt) {
        Double value = null;
        while (value == null) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            try {
                value = Double.parseDouble(input);
                if (value <= 0) {
                    System.out.println("Gia tri phai la gia tri duong (+).");
                    value = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Sai dinh dang. Vui long thu lai.");
            }
        }
        return value;
    }

    public int promptForPositiveInt(String prompt) {
        Integer value = null;
        while (value == null) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            try {
                value = Integer.parseInt(input);
                if (value <= 0) {
                    System.out.println("Gia tri phai la gia tri duong (+).");
                    value = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Sai dinh dang. Vui long thu lai.");
            }
        }
        return value;
    }

}
