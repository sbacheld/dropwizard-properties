package me.seanbachelder.dropwizard;

import io.dropwizard.lifecycle.Managed;
import me.seanbachelder.dropwizard.readers.PropertiesReader;
import me.seanbachelder.dropwizard.writers.PropertiesWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by sean on 2/5/15.
 */
public class PropertiesManager implements Managed {
    final private static Logger logger = LoggerFactory.getLogger(PropertiesManager.class);

    private String propertiesFilename;
    private PropertiesReader propertiesReader;
    private PropertiesWriter propertiesWriter;
    private int interval;
    private ScheduledExecutorService ses;

    public PropertiesManager(String propertiesFilename, PropertiesReader propertiesReader, PropertiesWriter propertiesWriter, int interval, ScheduledExecutorService ses) {
        this.propertiesFilename = propertiesFilename;
        this.propertiesReader = propertiesReader;
        this.propertiesWriter = propertiesWriter;
        this.interval = interval;
        this.ses = ses;
    }

    @Override
    public void start() throws Exception {
        logger.info("Starting properties service");
        File propertiesFile = new File(propertiesFilename);
        if (!propertiesFile.exists()) {
            logger.error("The specified properties file does not exist: " + propertiesFilename);
        } else if (!propertiesFile.canRead()) {
            logger.error("The specified properties file cannot be read: " + propertiesFilename);
        } else {
            Runnable propertiesUpdateTask = new PropertiesUpdateTask(propertiesFilename, propertiesReader, propertiesWriter);
            ses.scheduleWithFixedDelay(propertiesUpdateTask, 0, interval, TimeUnit.SECONDS);
        }
    }

    @Override
    public void stop() throws Exception {
        logger.info("Stopping properties service");
        ses.shutdown();
    }
}
