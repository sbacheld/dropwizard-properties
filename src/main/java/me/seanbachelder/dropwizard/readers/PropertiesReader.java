package me.seanbachelder.dropwizard.readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * Created by sean on 2/5/15.
 */
public interface PropertiesReader {
    public Properties readFile(File file) throws Exception;
}
