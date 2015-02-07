package me.seanbachelder.dropwizard;

import me.seanbachelder.dropwizard.writers.SystemPropertiesWriter;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by sean on 2/5/15.
 */
public class TestSystemPropertiesWriter {
    SystemPropertiesWriter writer = new SystemPropertiesWriter();
    String key = "key";
    String value = "value";
    String prepopulatedKey = "key2";
    String prepopulatedValue = "value2";

    @Before
    public void before() {
        System.clearProperty(key);
        System.setProperty(prepopulatedKey, prepopulatedValue);
    }

    @Test
    public void testSetProperty() {
        writer.setProperty(key, value);
        assertEquals("values do not match", value, System.getProperty(key));
    }

    @Test
    public void testClearProperty() {
        writer.clearProperty(prepopulatedKey);
        assertNull(System.getProperty(prepopulatedKey));
    }

    @Test
    public void testSetPropertyOverwrite() {
        writer.setProperty(prepopulatedKey, value);
        assertEquals("Overwrite failed", value, System.getProperty(prepopulatedKey));
    }
}
