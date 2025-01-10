package sn.eath.eshop.exception.product;


import sn.eath.eshop.exception.AbstractGetException;

import java.util.Map;

public class ProductGetException extends AbstractGetException {
    public ProductGetException(String message, Throwable cause){
        super(message, cause);
    }
    public ProductGetException(Throwable cause){
        super(cause);
    }
    public ProductGetException(){
        super();
    }
    public ProductGetException(String message, Map<String, Object> details){
        super(message, details);
    }
    public ProductGetException(String message) {
        super(message);
    }
    @Override
    public String getDomaineName(){
        return "Product";
    }
}
