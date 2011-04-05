package android;

import java.util.Vector;
import global.Constants;

/**
 * This is the class where the xml file will call to be parsed,
 * then will take the list of elements, interpret and calculate
 * any other necessary properties of each element, then
 * generate the appropriate android code
 *
 */
public class AndroidGenerator {

	Vector<Element> applicationElements;
	SaxXMLParser parser;
	
	public AndroidGenerator() {
		applicationElements = new Vector<Element>();
		parser = new SaxXMLParser(Constants.filename, applicationElements);
	}
	
	public void GenerateAndroidCode() {
		//first populate application elements by parsing XML file
		applicationElements = parser.parseDocument();
		
		// do interpretations and calulations for things like 
		// above, behind, etc..
		
		
		// generate appropriate android code
		
	}
}
