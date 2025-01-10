package sn.eath.eshop.exception;

public class AbstractConflictException extends AbstractException{

    public AbstractConflictException(String message, Throwable cause){
        super(message, cause);
    }

    public AbstractConflictException(Throwable cause){
        super(cause);
    }

    public AbstractConflictException(String message){
        super(message);
    }

    public AbstractConflictException(){
        super();
    }

    @Override
    public String getMessage(){
        if(super.getMessage()!= null) return getDomaineName()+" make conflict: "+super.getMessage();
        if(super.getCause().getMessage()!= null && super.getMessage()!= null) return getDomaineName()+" make conflict: "+super.getMessage()+" due to "+super.getCause().getMessage();
        return getDomaineName().concat(" make conflict.");
    }
}
