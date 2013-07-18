package kz.enu.epam.azimkhan.auth.exception;

/**
 *
 */
public class DAOLogicalException extends Exception{
    public DAOLogicalException() {
    }

    public DAOLogicalException(String message) {
        super(message);
    }

    public DAOLogicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOLogicalException(Throwable cause) {
        super(cause);
    }

    public DAOLogicalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
