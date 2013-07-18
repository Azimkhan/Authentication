package kz.enu.epam.azimkhan.auth.resource;

import java.util.ResourceBundle;

/**
 * Url manager
 */
public enum PathManager {
    INSTANCE;

    private static final String BUNDLE_NAME = "kz.enu.epam.azimkhan.auth.resource.path";
    private final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * Get path
     * @param key
     * @return
     */
    public String getString(String key) {
        return bundle.getString(key);
    }
}
