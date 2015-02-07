package me.seanbachelder.dropwizard.readers;

/**
 * Created by sean on 2/5/15.
 */
public class PropertiesReaderFactory {
    public static PropertiesReader getPropertiesReader(PropertiesReaderType propertiesReaderType) {
        PropertiesReader reader = null;

        switch (propertiesReaderType) {
            case YAML:
                reader = new YamlPropertiesReader();
        }

        return reader;
    }
}
