package kz.enu.epam.azimkhan.auth.notification.entity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.fmt.LocaleSupport;

/**
 *  Flash notification
 */
public class Notification {
    public enum Type{
        INFO, WARNING, ERROR
    }

    public Notification(String message) {
        this.message = message;
        this.type = Type.INFO;
    }

    public Notification(String message, Type type) {
        this.message = message;
        this.type = type;
    }

    private String message;

    /**
     * notification type
     */
    private Type type;

    /**
     * get notification getMessage
     * @return
     */
    public String getMessage(){
        return message;
    }


    /**
     * sets type
     * @param type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * gets type
     * @return
     */
    public Type getType() {
        return type;
    }
}
