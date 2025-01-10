package sn.eath.eshop.exception.user;


import sn.eath.eshop.exception.AbstractGetException;

import java.util.Map;

public class UserGetException extends AbstractGetException {
    public UserGetException(String message, Throwable cause){
        super(message, cause);
    }
    public UserGetException(Throwable cause){
        super(cause);
    }
    public UserGetException(){
        super();
    }
    public UserGetException(String message, Map<String, Object> details){
        super(message, details);
    }
    public UserGetException(String message) {
        super(message);
    }
    @Override
    public String getDomaineName(){
        return "User";
    }
}
