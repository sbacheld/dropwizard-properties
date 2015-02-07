package me.seanbachelder.dropwizard;

import java.io.File;

/**
 * Created by sean on 2/5/15.
 */
public class TestUtils {
    public static File getResourceAsFile(String resource) {
        return new File(TestUtils.class.getClassLoader().getResource("yaml-properties.yml").getFile());
    }
}
