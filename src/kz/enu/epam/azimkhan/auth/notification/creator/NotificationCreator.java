package kz.enu.epam.azimkhan.auth.notification.creator;

import kz.enu.epam.azimkhan.auth.notification.entity.Notification;
import kz.enu.epam.azimkhan.auth.resource.LocaleManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Simplifies notification creation
 */
public class NotificationCreator {
    private static final String BUNDLE_NAME = "notification";

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
        LocaleManager localeManager = LocaleManager.INSTANCE;
        Locale currentLocale = localeManager.getLocale(request);
        Notification notification = new Notification("????? " + propertyName + "??????", type);

        try{

            ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, currentLocale);
            if (bundle != null){


                String message = bundle.getString(propertyName);
                if (message != null){
                    notification = new Notification(message, type);
                }
            }
        } catch (MissingResourceException e){
            //TODO log it
        }

        return notification;
    }

    /**
     * Create notification from property key
     * @param propertyName
     * @return
     */
    public Notification createFromProperty(String propertyName){
        return createFromProperty(Notification.Type.INFO, propertyName);
    }
}
