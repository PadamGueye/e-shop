package sn.eath.eshop.payload;


import java.util.Map;

public class ErrorResponse {

    private String message;
    private String errorCode;
    private String name;
    private Map<String, Object> details;

    public ErrorResponse(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public ErrorResponse(String message, String errorCode, String name, Map<String, Object> details) {
        this.message = message;
        this.errorCode = errorCode;
        this.name = name;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }
}
