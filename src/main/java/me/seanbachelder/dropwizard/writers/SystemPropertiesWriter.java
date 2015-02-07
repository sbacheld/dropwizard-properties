package me.seanbachelder.dropwizard.writers;

import java.util.Properties;

/**
 * Created by sean on 2/5/15.
 */
public class SystemPropertiesWriter implements PropertiesWriter {
    @Override
    public void setProperty(String key, String value) {
        System.setProperty(key, value);
    }

    @Override
    public void clearProperty(String key) {
        System.clearProperty(key);
    }
}
