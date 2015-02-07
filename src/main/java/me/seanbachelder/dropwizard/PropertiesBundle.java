package me.seanbachelder.dropwizard;

import io.dropwizard.Bundle;
import io.dropwizard.lifecycle.setup.ScheduledExecutorServiceBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import me.seanbachelder.dropwizard.readers.PropertiesReaderType;
import me.seanbachelder.dropwizard.readers.PropertiesReader;
import me.seanbachelder.dropwizard.readers.PropertiesReaderFactory;
import me.seanbachelder.dropwizard.writers.PropertiesWriter;
import me.seanbachelder.dropwizard.writers.PropertiesWriterFactory;
import me.seanbachelder.dropwizard.writers.PropertiesWriterType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by sean on 2/5/15.
 */
public class PropertiesBundle implements Bundle {
    final private static Logger logger = LoggerFactory.getLogger(PropertiesBundle.class);

    public static final String DEFAULT_PROPERTIES_FILENAME = "properties.yml";
    public static final int DEFAULT_INTERVAL = 60;
    public static final PropertiesReaderType DEFAULT_PROPERTIES_READER_TYPE = PropertiesReaderType.YAML;
    public static final PropertiesWriterType DEFAULT_PROPERTIES_WRITER_TYPE = PropertiesWriterType.SYSTEM;

    private int interval;
    private String propertiesFilename;
    private PropertiesReaderType propertiesReaderType;
    private PropertiesReader propertiesReader;
    private PropertiesWriterType propertiesWriterType;
    private PropertiesWriter propertiesWriter;

    public PropertiesBundle() {
        interval = DEFAULT_INTERVAL;
        propertiesFilename = DEFAULT_PROPERTIES_FILENAME;
        propertiesReaderType = DEFAULT_PROPERTIES_READER_TYPE;
        propertiesReader = PropertiesReaderFactory.getPropertiesReader(propertiesReaderType);
        propertiesWriterType = DEFAULT_PROPERTIES_WRITER_TYPE;
        propertiesWriter = PropertiesWriterFactory.getPropertiesWriter(propertiesWriterType);
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getPropertiesFilename() {
        return propertiesFilename;
    }

    public void setPropertiesFilename(String propertiesFilename) {
        this.propertiesFilename = propertiesFilename;
    }

    public PropertiesReaderType getPropertiesReaderType() {
        return propertiesReaderType;
    }

    public void setPropertiesReaderType(PropertiesReaderType propertiesReaderType) {
        this.propertiesReaderType = propertiesReaderType;
        this.propertiesReader = PropertiesReaderFactory.getPropertiesReader(propertiesReaderType);
    }

    public PropertiesReader getPropertiesReader() {
        return propertiesReader;
    }

    public void setPropertiesReader(PropertiesReader propertiesReader) {
        this.propertiesReader = propertiesReader;
    }

    public PropertiesWriterType getPropertiesWriterType() {
        return propertiesWriterType;
    }

    public void setPropertiesWriterType(PropertiesWriterType propertiesWriterType) {
        this.propertiesWriterType = propertiesWriterType;
        this.propertiesWriter = PropertiesWriterFactory.getPropertiesWriter(propertiesWriterType);
    }

    public PropertiesWriter getPropertiesWriter() {
        return propertiesWriter;
    }

    public void setPropertiesWriter(PropertiesWriter propertiesWriter) {
        this.propertiesWriter = propertiesWriter;
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
    }

    @Override
    public void run(Environment environment) {
        logger.info("Initializing properties service");

        String nameFormat = "PropertiesBundle";
        ScheduledExecutorServiceBuilder sesBuilder = environment.lifecycle().scheduledExecutorService(nameFormat);
        ScheduledExecutorService ses = sesBuilder.build();

        PropertiesManager propertiesManager = new PropertiesManager(propertiesFilename, propertiesReader, propertiesWriter, interval, ses);
        environment.lifecycle().manage(propertiesManager);
    }
}
