package sn.eath.eshop.exception.user;


import sn.eath.eshop.exception.AbstractInvalidModelException;

public class UserInvalidModelException extends AbstractInvalidModelException {
    public UserInvalidModelException(String message, Throwable cause){
        super(message, cause);
    }

    public UserInvalidModelException(Throwable cause){
        super(cause);
    }

    public UserInvalidModelException(String message){
        super(message);
    }

    public UserInvalidModelException(){
        super();
    }

    @Override
    public String getDomaineName(){
        return "User";
    }
}
