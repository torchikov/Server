package sax;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ReadXMLFileSax {
    static final Logger logger = LogManager.getLogger(ReadXMLFileSax.class.getName());

    public static Object readXML(String xmlFile) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            SaxHandler handler = new SaxHandler();
            parser.parse(xmlFile, handler);

            return handler.getObject();

        } catch (Exception e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
