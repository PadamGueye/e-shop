package sn.eath.eshop.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sn.eath.eshop.payload.ErrorResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandlerAdvice {

    @ExceptionHandler({AbstractSaveException.class, AbstractGetException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleInternalException(Exception e) {
        return buildResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class, AbstractInvalidModelException.class, AbstractConflictException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleBadRequestException(Exception e) {
        return buildResponse(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AbstractNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleResourceNotFoundException(Exception e) {
        return buildResponse(e, HttpStatus.NOT_FOUND);
    }

    private ErrorResponse buildResponse(Exception exception, HttpStatus status) {
        Map<String, Object> details = new HashMap<>();
        String exceptionName = exception.getClass().getSimpleName();

        if(exception instanceof AbstractException) {
            details = ((AbstractException) exception).getDetails();
        }

        if(exception.getCause() != null) {
            details.put("localizedMessage", exception.getCause().getLocalizedMessage());
        }

        if (exception instanceof MethodArgumentNotValidException) {
            String message = buildMethodArgumentNotValidExceptionMessage((MethodArgumentNotValidException) exception);
            for (ObjectError error : ((MethodArgumentNotValidException) exception).getBindingResult().getAllErrors()) {
                details.put("detail", error.getDefaultMessage());
            }
            return new ErrorResponse(message, status.getReasonPhrase(), exceptionName, details);
        }

        if (exception instanceof ConstraintViolationException) {
            String message = buildConstraintViolationExceptionMessage((ConstraintViolationException) exception);
            Map<String, Object> violations = new HashMap<>();
            for (ConstraintViolation<?> violation : ((ConstraintViolationException) exception).getConstraintViolations()) {
                String field = null;
                for (Path.Node node : violation.getPropertyPath()) {
                    field = node.getName();
                }
                violations.put(field, violation.getMessage());
            }
            details.put("violations", violations);
            return new ErrorResponse(message, status.getReasonPhrase(),exceptionName, details);
        }
        return new ErrorResponse(exception.getMessage(), status.getReasonPhrase(), exceptionName, details);
    }

    private String buildMethodArgumentNotValidExceptionMessage(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("\n"));
    }

    private String buildConstraintViolationExceptionMessage(ConstraintViolationException exception) {

        return exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("\n"));
    }
}
