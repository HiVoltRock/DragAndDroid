package android;

import global.Constants;

import java.io.File;
import java.util.Collections;
import java.util.Vector;

import draganddroid.ElementSortX;
import draganddroid.ElementSortY;

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
	
	public int originalElementCt;
	
	ElementXMLUpdator updator;
	
	public AndroidGenerator() {
		applicationElements = new Vector<Element>();
		parser = new SaxXMLParserForAndroid(Constants.filename, applicationElements);
		updator = new ElementXMLUpdator();
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
		originalElementCt = applicationElements.size();
		
		// generate appropriate android code
		xml = new File(xmlDir);
		if(xml.exists())
		{
			System.out.println("XML File exists");
			
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
			
			Vector<Element> copy = new Vector<Element>(applicationElements);
			
			copy = sortElements("x", copy);
			
			System.out.println("Sorted copy Vector: ");
			for ( int i = 0; i < copy.size(); i++ )
			{
				System.out.println(applicationElements.elementAt(i).getName() + " " +
						applicationElements.elementAt(i).getX() + " " +
						applicationElements.elementAt(i).getY());
			}
			
			
		}
		else
		{
			System.out.println("The main.xml file for the Android project selected can't be found + \n" +
					"Is it a valid Android project directory? \n" +
					"Is it the root directory?");
		}
		
		
		//re-update xml - ANTHONY
		// if any new elements added to elements, update XML
		if ( applicationElements.size() != originalElementCt ) {
			for ( int i = originalElementCt-1; i < applicationElements.size(); i++ ) {
				updator.UpdateXMLFile( applicationElements.elementAt(i) );
			}
		}
		
	}
	/**
	 * Method for sorting our Vector when we need it for generating the Android main.xml file
	 * 
	 * @param key the paramater by which you want to sort. e.g., "x", "y", etc
	 * @param elements the Vector<Element> you wish sorted
	 * @return the sorted Vector<Element>
	 */
	public Vector<Element> sortElements(String key, Vector<Element> elements)
	{
		if(key.equals("x"))
		{
			Collections.sort(elements, new ElementSortX());
			
			System.out.println("Sorted copy INSIDE sortElements: ");
			for ( int i = 0; i < elements.size(); i++ )
			{
				System.out.println(applicationElements.elementAt(i).getName() + " " +
						applicationElements.elementAt(i).getX() + " " +
						applicationElements.elementAt(i).getY());
			}
		}
		else if(key.equals("y"))
		{
			Collections.sort(elements, new ElementSortY());
		}
		else
		{
			System.out.println("You didn't select a valid parameter to sort. Valid parameters are 'x', 'y'");
		}
		
		return elements;
	}
	
}
