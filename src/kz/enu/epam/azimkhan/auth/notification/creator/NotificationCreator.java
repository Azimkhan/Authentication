package kz.enu.epam.azimkhan.auth.notification.creator;

import kz.enu.epam.azimkhan.auth.notification.entity.Notification;
import kz.enu.epam.azimkhan.auth.resource.LocaleManager;
import kz.enu.epam.azimkhan.auth.resource.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Simplifies notification creation
 */
public class NotificationCreator {

    private HttpServletRequest request;

    /**
     * class constructor
     * @param request
     */
    public NotificationCreator(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Create notification by type and property key
     * @param type
     * @param propertyName
     * @return
     */
    public Notification createFromProperty(Notification.Type type, String propertyName){

        Notification notification = new Notification("????? " + propertyName + "??????", type);

        String message = MessageManager.INSTANCE.getMessage(request, propertyName);
        if (message != null){
            notification = new Notification(message, type);
        }

        return notification;
    }

    /**
     * Create notification by type and property key
     * @param type
     * @param propertyName
     * @return
     */
    public Notification createFromProperty(String propertyName){

        return createFromProperty(Notification.Type.INFO, propertyName);
    }
}
