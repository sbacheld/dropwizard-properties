package me.seanbachelder.dropwizard;

import me.seanbachelder.dropwizard.readers.YamlPropertiesReader;
import org.junit.Test;

import java.io.File;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by sean on 2/5/15.
 */
public class TestYamlPropertiesReader {
    @Test
    public void testReadFile() throws Exception {
        File file = TestUtils.getResourceAsFile("yaml-properties.yml");
        YamlPropertiesReader reader = new YamlPropertiesReader();
        Properties properties = reader.readFile(file);

        assertTrue("Properties should have at least one entry", properties.size() > 0);
        assertEquals("singleInteger does not match", "1", properties.getProperty("singleInteger"));
        assertEquals("quotedProperty does not match", "Some property", properties.getProperty("quotedProperty"));
        assertEquals("empty does not match", "", properties.getProperty("empty"));
        assertEquals("specialChars does not match", "$(10fx@*!`'\"''", properties.getProperty("specialChars"));
        assertEquals("property after blank line was missed", "Previous line was blank", properties.getProperty("afterBlankLine"));
        assertEquals("property after comment not correct", "After comment", properties.getProperty("afterComment"));
    }
}
