package kz.enu.epam.azimkhan.auth.exception;

/**
 *
 */
public class DAOTechnicalException extends Exception{
    public DAOTechnicalException() {
    }

    public DAOTechnicalException(String message) {
        super(message);
    }

    public DAOTechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOTechnicalException(Throwable cause) {
        super(cause);
    }

    public DAOTechnicalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
