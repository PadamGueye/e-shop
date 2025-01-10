package sn.eath.eshop.exception.user;


import sn.eath.eshop.exception.AbstractConflictException;

public class UserConflictException extends AbstractConflictException {
    public UserConflictException(String message, Throwable cause){
        super(message, cause);
    }

    public UserConflictException(Throwable cause){
        super(cause);
    }

    public UserConflictException(String message){
        super(message);
    }

    public UserConflictException(){
        super();
    }

    @Override
    public String getDomaineName(){
        return "User";
    }
}
