package me.seanbachelder.dropwizard.readers;

import com.fasterxml.jackson.dataformat.yaml.snakeyaml.DumperOptions;
import com.fasterxml.jackson.dataformat.yaml.snakeyaml.Yaml;
import com.fasterxml.jackson.dataformat.yaml.snakeyaml.constructor.Constructor;
import com.fasterxml.jackson.dataformat.yaml.snakeyaml.nodes.Tag;
import com.fasterxml.jackson.dataformat.yaml.snakeyaml.representer.Representer;
import com.fasterxml.jackson.dataformat.yaml.snakeyaml.resolver.Resolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Created by sean on 2/5/15.
 */
public class YamlPropertiesReader implements PropertiesReader {
    final private static Logger logger = LoggerFactory.getLogger(YamlPropertiesReader.class);

    private class CustomResolver extends Resolver {
        public final Pattern ALL = Pattern.compile("^[0-9]+$");

        @Override
        protected void addImplicitResolvers() {
            addImplicitResolver(Tag.STR, ALL, "1234567890");
        }
    }

    @Override
    public Properties readFile(File file) throws Exception {
        Properties properties = new Properties();

//        Yaml yaml = new Yaml(new Constructor(), new Representer(), new DumperOptions(), new CustomResolver());
        Yaml yaml = new Yaml();
        Map<String, String> values = (Map<String, String>) yaml.load(new FileInputStream(file));

        for (String key : values.keySet()) {
            Object value = values.get(key);
            if (value != null) {
                properties.setProperty(key, value.toString());
            } else {
                properties.setProperty(key, "");
            }
        }

        return properties;
    }
}
