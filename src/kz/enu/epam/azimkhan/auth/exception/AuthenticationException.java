package kz.enu.epam.azimkhan.auth.exception;

/**
 * Auth exception
 */
public class AuthenticationException extends Exception {
    public static final int EMPTY_LOGIN = 1;
    public static final int EMPTY_PASSWORD = 2;
    public static final int INVALID_LOGIN_OR_PASSWORD = 3;

    /**
     * Code of the error
     */
    private int errorCode;

    public AuthenticationException(int errorCode){
        this.errorCode = errorCode;
    }

    /**
     * return error code
     * @return
     */
    public int getErrorCode(){
        return this.errorCode;
    }
}
