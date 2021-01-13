package cn.az.boot.exception;

/**
 * @author az
 * @since 11/15/20
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable t) {
        super(message, t);
    }
}
