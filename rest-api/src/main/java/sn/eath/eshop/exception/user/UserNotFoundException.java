package sn.eath.eshop.exception.user;


import sn.eath.eshop.exception.AbstractNotFoundException;

import java.util.Map;

public class UserNotFoundException extends AbstractNotFoundException {
    public UserNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
    public UserNotFoundException(Throwable cause){
        super(cause);
    }
    public UserNotFoundException(){
        super();
    }

    public UserNotFoundException(String message, Map<String, Object> details){
        super(message, (Throwable) details);
    }

    public UserNotFoundException(String message)
    {
        super(message);
    }
    @Override
    public String getDomaineName(){
        return "User";
    }
}
