package kz.enu.epam.azimkhan.auth.notification.service;

import kz.enu.epam.azimkhan.auth.notification.entity.Notification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Notification1 service
 * This class manages notifications in session
 */
public class NotificationService implements Iterable<Notification>{

    /**
     * Session key
     */
    private static final String SERVICE_VAR = "_notifications";
    private HttpServletRequest request;

    /**
     * Class constructor
     * @param request
     */
    public NotificationService(HttpServletRequest request){
        this.request = request;
    }

    /**
     * Pushes a notification to session,
     * this notification will be stored in session
     * util popped out
     * @param notification
     */
    public void push(Notification notification){
        List<Notification> queue = getOrCreateList(request);
        queue.add(notification);
    }

    /**
     * Pushes a notification to session,
     * this notification will be stored in session
     * util popped out
     * @param request
     * @param notification
     */
    public static void push(HttpServletRequest request, Notification notification){
        List<Notification> queue = getOrCreateList(request);
        queue.add(notification);
    }

    /**
     * Creates or retrieves a list of
     * notifications from session
     * @param request
     * @return
     */
    private static List<Notification> getOrCreateList(HttpServletRequest request){
        HttpSession session =request.getSession();
        Object ob = session.getAttribute(SERVICE_VAR);
        List<Notification> list = null;
        if(ob != null){
            list = (List) ob;
        } else{
            list = new LinkedList<Notification>();
            session.setAttribute(SERVICE_VAR, list);
        }

        return list;
    }

    /**
     * Iterator to iterate over notifications
     * @return
     */
    @Override
    public Iterator<Notification> iterator() {
        List<Notification> list = getOrCreateList(request);
        return list.iterator();
    }

    public static boolean haveNotifications(HttpServletRequest request){
        return !getOrCreateList(request).isEmpty();
    }

    public boolean haveNotifications(){
        return haveNotifications(request);
    }

    public static void test(){

    }

}
