package android;

import java.io.IOException;
import java.util.Vector;
import java.util.jar.Attributes;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 *
 * @author Anthony Favia
 */
public class SaxXMLParser extends DefaultHandler {

    private String tempVal;
    String filename;
    
    Element tempElement;
    
    Vector<Element> elementList;
    
    public SaxXMLParser(String filename, Vector<Element> el) {
        this.filename = filename;
        elementList = el;
    }
    
    public Vector<Element> parseDocument() {
        //get a factory
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {
            //get a new instance of parser
            SAXParser sp = spf.newSAXParser();
            //parse the file and also register this class for call backs
            sp.parse(filename, this);

        } catch (SAXException se) {
            se.printStackTrace();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return elementList;
    }
    
    //Event Handlers
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tempVal = "";
        if (qName.equalsIgnoreCase("Element")) {
            tempElement = new Element();
            tempElement.setType(attributes.getValue("type"));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        tempVal = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("Element")) {
            //add it to the list
            elementList.add(tempElement);

        } else if (qName.equalsIgnoreCase("name")) {
            tempElement.setName(tempVal);
        } else if (qName.equalsIgnoreCase("x")) {
            tempElement.setX(Integer.parseInt(tempVal));
        } else if (qName.equalsIgnoreCase("y")) {
            tempElement.setY(Integer.parseInt(tempVal));
	    } else if (qName.equalsIgnoreCase("height")) {
	        tempElement.setHeight(Integer.parseInt(tempVal));
	    } else if (qName.equalsIgnoreCase("width")) {
	        tempElement.setWidth(Integer.parseInt(tempVal));
	    } else if (qName.equalsIgnoreCase("caption")) {
	    	tempElement.setName(tempVal);
	    }

    }
}
