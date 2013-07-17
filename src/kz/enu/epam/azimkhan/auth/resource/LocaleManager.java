package kz.enu.epam.azimkhan.auth.resource;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public enum LocaleManager {
    INSTANCE;

    public static final String DEFAULT_LOCALE = "default_locale";
    public static final String VAR = "locale";
    private static final String BUNDLE_NAME = "locale";

    private Locale defaultLocale = Locale.forLanguageTag("ru-RU");
    private HashMap<String, Locale> locales = new HashMap<String, Locale>();
    {
        locales.put("English", Locale.forLanguageTag("en-GB"));
        locales.put("Русский", Locale.forLanguageTag("ru-RU"));
    }

    public Locale getDefaultLocale(){
        //TODO parse from file
        return defaultLocale;
    }

    public HashMap<String, Locale> getLocales(){
        //TODO parse from file
        return locales;
    }

    public void setLocale(ServletContext context, Locale locale){

        context.setAttribute(VAR, (locale != null) ? locale : defaultLocale);
    }

    public void setLocale(HttpSession session, Locale locale){
        session.setAttribute(VAR, (locale != null) ? locale : defaultLocale);
    }

    public Locale getLocale(HttpServletRequest request){
        Object ob  = request.getSession().getAttribute(VAR);

        if (ob != null && ob.getClass().equals(Locale.class)){
            return (Locale) ob;
        }

        return defaultLocale;
    }

}
