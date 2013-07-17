package kz.enu.epam.azimkhan.auth.resource;

import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.jstl.fmt.LocaleSupport;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import java.util.ResourceBundle;

/**
 * Message manager
 */
public enum MessageManager {
    INSTANCE;

    private static final String BUNDLE_NAME = "message";
    private ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * get getMessage
     * @param key
     * @return
     */
    public String getMessage(String key) {
        return bundle.getString(key);
    }
}
