package me.seanbachelder.dropwizard;

import me.seanbachelder.dropwizard.readers.PropertiesReader;
import me.seanbachelder.dropwizard.writers.PropertiesWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Properties;

/**
 * Created by sean on 2/5/15.
 */
public class PropertiesUpdateTask implements Runnable {
    final private static Logger logger = LoggerFactory.getLogger(PropertiesUpdateTask.class);

    private String propertiesFilename;
    private PropertiesReader propertiesReader;
    private PropertiesWriter propertiesWriter;
    private Properties currentProperties;

    public PropertiesUpdateTask(String propertiesFilename, PropertiesReader propertiesReader, PropertiesWriter propertiesWriter) {
        this.propertiesFilename = propertiesFilename;
        this.propertiesReader = propertiesReader;
        this.propertiesWriter = propertiesWriter;
        this.currentProperties = null;
    }

    @Override
    public void run() {
        Properties properties;
        try {
            properties = propertiesReader.readFile(new File(propertiesFilename));
        } catch (Exception e) {
            logger.error("Error encountered while reading file: " + propertiesFilename);
            return;
        }

        if (currentProperties != null) {
            // Remove deleted properties
            for (String key : currentProperties.stringPropertyNames()) {
                if (!properties.containsKey(key)) {
                    propertiesWriter.clearProperty(key);
                }
            }
        }

        for (String key : properties.stringPropertyNames()) {
            propertiesWriter.setProperty(key, properties.getProperty(key));
        }
        currentProperties = properties;
    }
}
