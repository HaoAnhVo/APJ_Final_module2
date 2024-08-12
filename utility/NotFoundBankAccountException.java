package exam_module2.utility;

public class NotFoundBankAccountException extends Exception{
    public NotFoundBankAccountException(String message) {
        super(message);
    }
}
