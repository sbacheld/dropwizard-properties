package me.seanbachelder.dropwizard.writers;

import java.util.Properties;

/**
 * Created by sean on 2/5/15.
 */
public interface PropertiesWriter {
    public void setProperty(String key, String value);
    public void clearProperty(String key);
}
