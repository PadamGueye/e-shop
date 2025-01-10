package sn.eath.eshop.exception;

public class AbstractInvalidModelException extends AbstractException{
    public AbstractInvalidModelException(String message, Throwable cause){
        super(message, cause);
    }

    public AbstractInvalidModelException(Throwable cause){
        super(cause);
    }

    public AbstractInvalidModelException(String message){
        super(message);
    }

    public AbstractInvalidModelException(){
        super();
    }

    @Override
    public String getMessage(){
        if(super.getMessage() != null) return getDomaineName() + " request is invalid: " + super.getMessage();
        return getDomaineName() + " request is invalid";
    }
}
