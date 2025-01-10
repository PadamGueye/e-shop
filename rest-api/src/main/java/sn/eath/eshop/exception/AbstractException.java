package sn.eath.eshop.exception;

import java.util.Map;


public class AbstractException extends RuntimeException{
    private String domaineName;
    private String message;
    private Throwable cause;
    private Map<String, Object> details;

    public AbstractException (String message){
        super(message);
        this.message = message;
    }

    public AbstractException(Throwable cause){
        super(cause);
        this.cause = cause;
        this.message = cause.getMessage();
    }

    public AbstractException(String message, Throwable cause){
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }

    public AbstractException(String message, Map<String, Object> details){
        super(message);
        this.message = message;
        this.details = details;
    }

    public AbstractException() {

    }

    public String getDomaineName() {
        return domaineName;
    }

    public void setDomaineName(String domaineName) {
        this.domaineName = domaineName;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }
}
