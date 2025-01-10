package sn.eath.eshop.exception;

import java.util.Map;

public class AbstractGetException extends AbstractException{
    public AbstractGetException(String message){
        super(message);
    };

    public AbstractGetException(Throwable cause){
        super(cause);
    }

    public AbstractGetException(String message, Throwable cause){
        super(cause);
    }

    public AbstractGetException (String message, Map<String, Object> details){
        super(message, details);
    }
    public AbstractGetException() {
        super();
    }

    @Override
    public String getMessage(){
        return "something went wrong on our end while getting the " + getDomaineName()  + " . Please try again later";

    }

}
