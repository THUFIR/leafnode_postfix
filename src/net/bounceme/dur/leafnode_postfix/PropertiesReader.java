package net.bounceme.dur.leafnode_postfix;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesReader {

    private static final Logger LOG = Logger.getLogger(PropertiesReader.class.getName());
    private static final Level LEVEL = Level.FINE;
    private static Properties props = new Properties();

    public static Properties getProps() {
        LOG.log(LEVEL, "getting props...");
        try {
            props.load(PropertiesReader.class.getResourceAsStream("/nntp.properties"));
        } catch (IOException ex) {
            Logger.getLogger(PropertiesReader.class.getName()).log(LEVEL, "no properties file");
            Logger.getLogger(PropertiesReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return props;
    }
}