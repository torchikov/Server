package sax;


import helpers.ReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler {
    static final Logger logger = LogManager.getLogger(SaxHandler.class.getName());

    private static final String CLASSNAME_TAG = "class";
    private Object object;
    private String element;

    @Override
    public void startDocument() throws SAXException {
        logger.info("Start parsing document");
    }

    @Override
    public void endDocument() throws SAXException {
        logger.info("End parsing document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (!qName.equals(CLASSNAME_TAG)) {
            element = qName;
        } else {
            String className = attributes.getValue(0);
            object = ReflectionHelper.createObject(className);
            logger.info("Created object " + className);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        element = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (element != null) {
            String value = new String(ch, start, length);
            ReflectionHelper.setFieldValue(object, element, value);
            logger.info("Added field " + element + " with value = " + value);
        }
    }

    public Object getObject() {
        return object;
    }
}
