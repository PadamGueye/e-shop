package sn.eath.eshop.exception;

public class AbstractSaveException extends AbstractException{
    public AbstractSaveException(String message, Throwable cause){
        super(message, cause);
    }

    public AbstractSaveException(Throwable cause){
        super(cause);
    }

    public AbstractSaveException(String message){
        super(message);
    }

    public AbstractSaveException(){
        super();
    }
}
