package sn.eath.eshop.exception.user;


import sn.eath.eshop.exception.AbstractSaveException;

public class UserSaveException extends AbstractSaveException {
    public UserSaveException(String message, Throwable cause){
        super(message, cause);
    }
    public UserSaveException(Throwable cause){
        super(cause);
    }
    public UserSaveException(){
        super();
    }

    public UserSaveException(String message)
    {
        super(message);
    }
    @Override
    public String getDomaineName(){
        return "User";
    }
}
