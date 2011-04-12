package android;

import global.Constants;

import java.io.File;
import java.util.Collections;
import java.util.Vector;

import draganddroid.ElementSortX;

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
		if(xml.exists())
		{
			/** (more for my not losing my place if I have to start and pickup later than for anyone else)
			 * Use a duplicate list so we can delete once placed to make any future sorts needed faster
			 * Basic layout algorithm:
			 * 1) Sort by y-coordinate
			 * 2) Place smallest y at the top
			 * 3) If x-coords don't conflict, place x in order left-to-right smallest-X -> largest X
			 * 		It may be necessary to select Xs in a RANGE. That is, if smallest X has a height 50, search within a height range
			 * 		of plus or minus 25 from that corresponding y to make sure things aren't missed in transcription
			 * 4) If there is a conflict (which there shouldn't be if user has ANY common sense), tell the user their elements overlap and they
			 * 		need to move one or more elements...? Seems like a cop-out, but that's genuinely the case. If coordinates overlap at all
			 * 		then it's a realistically unusable UI decision and it should be changed
			 * 5) Pick the next y
			 */
			
			
		}
		else
		{
			System.out.println("The main.xml file for the Android project selected can't be found + \n" +
					"Is it a valid Android project directory? \n" +
					"Is it the root directory?");
		}
		
		
		//re-update xml - ANTHONY
		
	}
	/**
	 * 
	 * @param key the paramater by which you want to sort. e.g., "x", "y", etc
	 * @param elements the Vector<Element> you wish sorted
	 * @return the sorted Vector<Element>
	 */
	public Vector<Element> sortElements(String key, Vector<Element> elements)
	{
		if(key.equals("x"))
		{
			//Collections.sort(elements, new ElementSortX());
		}
		else if(key.equals("y"))
		{
			//Collections.sort(elements, new ElementSortY());
		}
		else
		{
			System.out.println("You didn't sort by a valid parameter. Valid parameters are 'x', 'y'");
		}
		
		return elements;
	}
	
}
