package sn.eath.eshop.exception;

public class AbstractNotFoundException extends AbstractException{
    public AbstractNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public AbstractNotFoundException(Throwable cause){
        super(cause);
    }

    public AbstractNotFoundException(String message){
        super(message);
    }

    public AbstractNotFoundException(){
        super();
    }

    @Override
    public String getMessage(){
        return getDomaineName().concat(" was not found");
    }

}
