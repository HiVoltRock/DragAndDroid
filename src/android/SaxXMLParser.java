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
    
    public SaxXMLParser(String filename, Vector<Element> el) {
        this.filename = filename;
        elementList = el;
    }
    
    public void parseDocument() {
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
    }
    
    //Event Handlers
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase("Element")) {
            tempElement = new Element();
            tempElement.setType(attributes.getValue("type"));
            tempElement.setName(attributes.getValue("name"));
            tempElement.setX(Integer.parseInt(attributes.getValue("x")));
            tempElement.setY(Integer.parseInt(attributes.getValue("y")));
            tempElement.setHeight(Integer.parseInt(attributes.getValue("height")));
            tempElement.setWidth(Integer.parseInt(attributes.getValue("width")));
            tempElement.setCaption(attributes.getValue("caption"));
            elementList.add(tempElement);
        }
    }
    
    public void characters(char ch[], int start, int length)
    	throws SAXException {
    }

    public void endElement(String uri, String localName, String qName) 
    	throws SAXException {	
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
