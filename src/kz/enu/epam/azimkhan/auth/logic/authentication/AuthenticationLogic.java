package kz.enu.epam.azimkhan.auth.logic.authentication;

import kz.enu.epam.azimkhan.auth.dao.UserDAO;
import kz.enu.epam.azimkhan.auth.entity.User;
import kz.enu.epam.azimkhan.auth.exception.AuthenticationException;
import kz.enu.epam.azimkhan.auth.util.PasswordDigest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Performs authentication
 */
public class AuthenticationLogic {
    public static final String SESSION_VAR = "";
    /**
     * Request
     */
    private HttpServletRequest request;

    public AuthenticationLogic(HttpServletRequest request) {
        this.request = request;

    }

    /**
     * Authenticate user
     * @param login
     * @param password
     * @throws AuthenticationException
     */
    public void authenticate(final String login, final String password) throws AuthenticationException{
        if (login != null && password != null){
            String hash = PasswordDigest.md5hash(password);
            UserDAO dao = new UserDAO();
            User user =  dao.findByLoginAndPassword(login, hash);

            if (null != user){
                HttpSession session = request.getSession(true);
                session.setAttribute(SESSION_VAR, user);
            } else{
                throw new AuthenticationException(AuthenticationException.INVALID_LOGIN_OR_PASSWORD);
            }
        } else{
            throw new AuthenticationException((password == null) ? AuthenticationException.EMPTY_PASSWORD : AuthenticationException.EMPTY_LOGIN);
        }
    }

    /**
     * check if user is logged in to the system
     * @param request
     * @return
     */
    public static boolean isLoggedIn(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        return  (session.getAttribute(SESSION_VAR) != null);

    }

    /**
     * perform logout
     * @param request
     */
    public static void logout(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.removeAttribute(SESSION_VAR);
    }
}
