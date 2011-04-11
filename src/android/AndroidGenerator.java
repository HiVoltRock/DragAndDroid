package android;

import global.Constants;

import java.io.File;
import java.util.Vector;

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
	SaxXMLParserForAndroid parser;
	File xml;
	
	public AndroidGenerator() {
		applicationElements = new Vector<Element>();
		parser = new SaxXMLParserForAndroid(Constants.filename, applicationElements);
	}
	
	public void GenerateAndroidCode(String xmlDir) 
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
		xml = new File(xmlDir);
		if(xml.canWrite())
		{
			System.out.println("We can write to the xml file directly");
		}
		else
		{
			System.out.println("We can not write to the xml file directly");
		}
		
		
		//re-update xml - ANTHONY
		
	}
	
	public void getMainXml()
	{
		
	}
	
}
