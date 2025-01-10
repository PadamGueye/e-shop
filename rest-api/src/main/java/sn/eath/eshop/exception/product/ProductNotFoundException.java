package sn.eath.eshop.exception.product;


import sn.eath.eshop.exception.AbstractNotFoundException;

import java.util.Map;

public class ProductNotFoundException extends AbstractNotFoundException {
    public ProductNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
    public ProductNotFoundException(Throwable cause){
        super(cause);
    }
    public ProductNotFoundException(){
        super();
    }

    public ProductNotFoundException(String message, Map<String, Object> details){
        super(message, (Throwable) details);
    }

    public ProductNotFoundException(String message)
    {
        super(message);
    }
    @Override
    public String getDomaineName(){
        return "Product";
    }
}
