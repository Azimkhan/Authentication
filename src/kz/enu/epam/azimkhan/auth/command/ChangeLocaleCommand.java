package kz.enu.epam.azimkhan.auth.command;

import kz.enu.epam.azimkhan.auth.resource.LocaleManager;
import kz.enu.epam.azimkhan.auth.resource.UrlManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.*;

/**
 *
 */
public class ChangeLocaleCommand extends ActionCommand{

    private LocaleManager localeManager = LocaleManager.INSTANCE;
    private static final Logger logger = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String languageTag = request.getParameter("code");
        UrlManager urlManager = UrlManager.INSTANCE;
        String path = urlManager.getString("path.page.main");

        if (languageTag != null){
            languageTag = languageTag.toLowerCase();

            HashMap<String, Locale> locales = localeManager.getLocales();
            Iterator<Map.Entry<String, Locale>> iterator = locales.entrySet().iterator();

            while (iterator.hasNext()){
                Map.Entry<String, Locale> entry = iterator.next();
                Locale currentLocale = entry.getValue();
                String currentTag = entry.getValue().toLanguageTag().toLowerCase();

                if (currentTag.equals(languageTag)){
                    // Session-scoped locale
                    HttpSession session = request.getSession();
                    LocaleManager.INSTANCE.setLocale(session, currentLocale);
                    logger.info("Locale changed to " + currentLocale.getDisplayLanguage() + "(" +currentLocale.getDisplayCountry()+")");
                    return path;
                }

            }

            logger.info("Locale not found: " + languageTag);
        }


        return path;
    }
}
