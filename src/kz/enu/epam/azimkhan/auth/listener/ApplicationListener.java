package kz.enu.epam.azimkhan.auth.listener; /**
 *
 */

import kz.enu.epam.azimkhan.auth.connection.ConnectionPool;
import kz.enu.epam.azimkhan.auth.logic.authentication.AuthenticationLogic;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;

public class ApplicationListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener, ServletRequestListener{
    private Logger logger = Logger.getRootLogger();

    private ServletContext context;

    // Public constructor is required by servlet spec
    public ApplicationListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      context = sce.getServletContext();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.INSTANCE.shutDown();
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {

    }



    public void sessionDestroyed(HttpSessionEvent se) {

    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      if(sbe.getName().equals(AuthenticationLogic.SESSION_VAR)){
          context.setAttribute("authenticated", sbe.getValue() != null);
          context.setAttribute("user", sbe.getValue());
      }
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      context.setAttribute("authenticated", false);
      context.setAttribute("user", null);
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      attributeAdded(sbe);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

    }
}
