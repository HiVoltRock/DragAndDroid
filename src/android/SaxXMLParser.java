package android;

import java.io.IOException;
import java.util.Vector;
import org.xml.sax.Attributes;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;


/**
 *Parse the xml file created by the editor
 *and create a list of Elements to be added
 *to the actual android application
 *
 * @author Anthony Favia
 */
public class SaxXMLParser extends DefaultHandler {

    String filename;
    
    Element tempElement;
    
    Vector<Element> elementList;
    boolean pName;
    boolean pX;
    boolean pCaption;
    boolean pY;
    boolean pHeight;
    boolean pWidth;
    
    public SaxXMLParser(String filename, Vector<Element> el) {
        this.filename = filename;
        elementList = el;
    }
    
    public void parseDocument() {
        //get a factory
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {
            pName = false;
            pX = false;
            pCaption = false;
            pY = false;
            pHeight = false;
            pWidth = false;
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
    }
    
    //Event Handlers
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
    	System.out.println("STARTELEMENT");
        if (qName.equalsIgnoreCase("Element")) {
            tempElement = new Element();
            tempElement.setType(attributes.getValue("type"));
            tempElement.setType(attributes.getValue("name"));
            tempElement.setType(attributes.getValue("x"));
            tempElement.setType(attributes.getValue("y"));
            tempElement.setType(attributes.getValue("height"));
            tempElement.setType(attributes.getValue("width"));
            tempElement.setType(attributes.getValue("caption"));
        }
        /*else if (qName.equalsIgnoreCase("name")) {
            pName = true;
        } else if (qName.equalsIgnoreCase("x")) {
            pX = true;
        } else if (qName.equalsIgnoreCase("y")) {
            pY = true;
	    } else if (qName.equalsIgnoreCase("height")) {
	        pHeight = true;
	    } else if (qName.equalsIgnoreCase("width")) {
	        pWidth = true;
	    } else if (qName.equalsIgnoreCase("caption")) {
	    	pCaption = true;
	    }
	    */
    }
    
    public void characters(char ch[], int start, int length)
    	throws SAXException {
        //tempVal = new String(ch, start, length);
    	/*
    	if (pName) {
            tempElement.setName(new String(ch, start, length));
            pName = false;
        } else if (pX) {
            tempElement.setX(Integer.parseInt(new String(ch, start, length)));
            pX = false;
        } else if (pY) {
            tempElement.setY(Integer.parseInt(new String(ch, start, length)));
            pY = false;
	    } else if (pHeight) {
	        tempElement.setHeight(Integer.parseInt(new String(ch, start, length)));
	        pHeight = false;
	    } else if (pWidth) {
	        tempElement.setWidth(Integer.parseInt(new String(ch, start, length)));
	        pWidth = false;
	    } else if (pCaption) {
	    	tempElement.setName(new String(ch, start, length));
	    	pCaption = false;
	    }
	    */
    }

    public void endElement(String uri, String localName, String qName) 
    	throws SAXException {
    	elementList.add(tempElement);
    }
    // Issue a warning
    public void warning(SAXParseException exception) {
        System.err.println("WARNING: line " + exception.getLineNumber() + ": "+
                           exception.getMessage());
    }

    // Report a parsing error
    public void error(SAXParseException exception) {
        System.err.println("ERROR: line " + exception.getLineNumber() + ": " +
                           exception.getMessage());
    }

    // Report a non-recoverable error and exit
    public void fatalError(SAXParseException exception) throws SAXException {
        System.err.println("FATAL: line " + exception.getLineNumber() + ": " +
                           exception.getMessage());
        throw(exception);
    }
}
