package kz.enu.epam.azimkhan.auth.resource;

import java.util.ResourceBundle;

/**
 * Configuration Manager
 */
public enum DBConfigurationManager {
    INSTANCE;

    private static final String BUNDLE_NAME = "database";
    public static final String DATABASE_DRIVER_NAME = "driver_name";
    public static final String DATABASE_CONNECTION_URL = "url";
    public static final String DATABASE_USERNAME = "username";
    public static final String DATABASE_PASSWORD = "password";

    private ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * Retrieves requested parameter from properties file
     * @param key
     * @return
     */
    public String getString(String key) {
        return bundle.getString(key);
    }
}
