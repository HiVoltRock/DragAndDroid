package android;

import java.util.Vector;
import global.Constants;

/**
 * 
 * This is the class where the xml file will call to be parsed,
 * then will take the list of elements, interpret and calculate
 * any other necessary properties of each element, then
 * generate the appropriate android code
 *
 */
public class AndroidGenerator 
{

	public Vector<Element> applicationElements;
	SaxXMLParser parser;
	
	public AndroidGenerator() {
		applicationElements = new Vector<Element>();
		parser = new SaxXMLParser(Constants.filename, applicationElements);
	}
	
	public void GenerateAndroidCode() 
	{
		//first populate application elements by parsing XML file
		parser.parseDocument();
		
		System.out.println("Number of elements: " + applicationElements.size());
		System.out.println("Generated Android code. Elements: ");
		for ( int i = 0; i < applicationElements.size(); i++ )
		{
			System.out.println(applicationElements.elementAt(i).getType() + " " +
					applicationElements.elementAt(i).getX() + " " +
					applicationElements.elementAt(i).getY() + " " +
					applicationElements.elementAt(i).getName() + " " +
					applicationElements.elementAt(i).getHeight() + " " +
					applicationElements.elementAt(i).getWidth());
		}
		
		
		// generate appropriate android code
		
	}
}
