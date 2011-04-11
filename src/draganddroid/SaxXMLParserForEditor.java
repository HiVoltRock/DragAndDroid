package draganddroid;

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
 * parses xml file and checks for new additions
 * by the new android generator
 * 
 * @author Anthony
 *
 */
public class SaxXMLParserForEditor extends DefaultHandler {
	String filename;
    
	AndroidElement tempAndroidElement;
    
    Vector<AndroidElement> elementList;
    
    public SaxXMLParserForEditor(String filename, Vector<AndroidElement> el) {
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
        	// make sure element is not already in list
        	// if not, add to list so drawing is updated
        	boolean isFound = false;
        	for ( int i = 0; i< elementList.size(); i++ ) {
        		if ( elementList.elementAt(i).name == attributes.getValue("name") ) {
        			isFound = true;
        		}
        	}
        	if ( !isFound ) {
        		if ( attributes.getValue("type") == "AButton" ) {
        			int x = Integer.parseInt(attributes.getValue("x"));
        			int y = Integer.parseInt(attributes.getValue("y"));
        			String name = attributes.getValue("name");
        			elementList.add(new AButton(name, x, y));
        		}
        		else if ( attributes.getValue("type") == "ALabel" ) {
        			int x = Integer.parseInt(attributes.getValue("x"));
        			int y = Integer.parseInt(attributes.getValue("y"));
        			String name = attributes.getValue("name");
        			elementList.add(new ALabel(name, x, y));
        		}
        		else if ( attributes.getValue("type") == "ATextBox" ) {
        			int x = Integer.parseInt(attributes.getValue("x"));
        			int y = Integer.parseInt(attributes.getValue("y"));
        			String name = attributes.getValue("name");
        			elementList.add(new ATextBox(name, x, y));
        		}
        	}
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
