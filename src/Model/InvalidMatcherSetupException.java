package Model;

public class InvalidMatcherSetupException extends Exception{
    private String message;

    public InvalidMatcherSetupException(String msg)
    {
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
