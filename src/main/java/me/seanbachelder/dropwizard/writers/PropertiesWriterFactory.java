package me.seanbachelder.dropwizard.writers;

/**
 * Created by sean on 2/5/15.
 */
public class PropertiesWriterFactory {
    public static PropertiesWriter getPropertiesWriter(PropertiesWriterType propertiesWriterType) {
        PropertiesWriter writer = null;

        switch (propertiesWriterType) {
            case SYSTEM:
                writer = new SystemPropertiesWriter();
                break;
        }

        return writer;
    }
}
