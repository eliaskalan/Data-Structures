package exceptions;

public class EmptyQueueException extends Exception{
    public EmptyQueueException() {
        super("The Queue is empty ");
    }
    public String getMessage()
    {
        return super.getMessage();
    }
}
