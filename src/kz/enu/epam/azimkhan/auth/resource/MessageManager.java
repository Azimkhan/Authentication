package kz.enu.epam.azimkhan.auth.resource;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.jstl.fmt.LocaleSupport;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Message manager
 */
public enum MessageManager {
    INSTANCE;

    private static final String BUNDLE_NAME = "kz.enu.epam.azimkhan.auth.resource.message";
    private ResourceBundle bundle = null;
    private Locale lastLocale = null;
    private LocaleManager localeManager = LocaleManager.INSTANCE;

    /**
     * get getMessage
     * @param key
     * @return
     */
    public synchronized String getMessage(HttpServletRequest request, String key) {

        Locale locale = localeManager.getLocale(request);

        if (lastLocale != null && lastLocale.equals(locale)){
            return bundle.getString(key);
        } else{
            Logger.getRootLogger().debug("Message manager reinitialized bundle");
            bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
            lastLocale = locale;
        }
        return bundle.getString(key);
    }
}
