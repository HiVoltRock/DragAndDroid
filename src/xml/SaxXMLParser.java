package xml;

import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import element.AButton;
import element.ALabel;
import element.ATextBox;
import element.AndroidElement;

/**
 * parses xml file and checks for new additions
 * by the new android generator
 * 
 * @author Anthony
 *
 */
public class SaxXMLParser extends DefaultHandler {
String filename;
    
	AndroidElement tempElement;
    
    Vector<AndroidElement> elementList;
    
    public SaxXMLParser(String filename, Vector<AndroidElement> el) {
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
            if ( attributes.getValue("type").equals("AButton") ) {
            	tempElement = new AButton();
            }
            else if ( attributes.getValue("type").equals("ALabel") ) {
            	tempElement = new ALabel();
            }
            else if ( attributes.getValue("type").equals("ATextBox") ) {
            	tempElement = new ATextBox();
            }
            tempElement.setName(attributes.getValue("name"));
            tempElement.setX(Integer.parseInt(attributes.getValue("x")));
            tempElement.setY(Integer.parseInt(attributes.getValue("y")));
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
