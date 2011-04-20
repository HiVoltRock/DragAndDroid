package android;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Vector;

import xml.AppElementListGenerator;
import xml.ElementXMLUpdator;
import xml.SaxXMLParser;
import draganddroid.ElementSortX;
import draganddroid.ElementSortY;
import element.AndroidElement;
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
	//renamed "applicationElements" to "elements" because it's WAY shorter
	public Vector<AndroidElement> elements;
	SaxXMLParser parser;
	File xml;
	
	public int originalElementCt;
	
	ElementXMLUpdator updator;
	
	public AndroidGenerator() {
		elements = new Vector<AndroidElement>();
		parser = new SaxXMLParser(Constants.filename, elements);
		updator = new ElementXMLUpdator();
	}
	
	public void GenerateAndroidCode(String xmlDir) 
	{
		//first populate application elements by parsing XML file
		parser.parseDocument();
		
		System.out.println("Number of elements: " + elements.size());
		System.out.println("Generated Android code. Elements: ");
		for ( int i = 0; i < elements.size(); i++ )
		{
			System.out.println(elements.elementAt(i).getName() + " " +
					elements.elementAt(i).getX() + " " +
					elements.elementAt(i).getY() + " " +
					elements.elementAt(i).getHeight() + " " +
					elements.elementAt(i).getWidth());
		}
		originalElementCt = elements.size();
		
		// generate appropriate android code
		xml = new File(xmlDir);
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
		try 
		{
			System.out.println("XML File exists");
			PrintWriter pw = new PrintWriter(xml);
			sortElements("y", elements);
			
			//erase the contents of the file so far so we can build from scratch
			FileOutputStream eraser = new FileOutputStream(xml);
			byte b[] = new byte[0];
			eraser.write(b);
			eraser.close();
			
			//place appropriate tags for the elements so they display correctly in the AVD or on a phone
			setAttributes(elements);
			
			//print the top non-element related lines of the Android file
			pw.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			pw.println("<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"");
			pw.println("\tandroid:layout_width=\"fill_parent\"");
			pw.println("\tandroid:layout_height=\"fill_parent\">");
			
			for(AndroidElement e : elements)
			{
				e.printAndroidXml(pw);
			}
			
			
			pw.println("</RelativeLayout>");
			pw.close();
			
			
		} 
		catch (FileNotFoundException fnfe) 
		{
			System.out.println("The file wasn't found when dealing with the PrintWriter. AndroidGenerator.java.\nAre you in the right directory?");
			fnfe.printStackTrace();
		}
		catch(SecurityException se)
		{
			System.out.println("It seems you don't have write access to this file. Printwriter. AndroidGenerator.java");
			se.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("I/O Problem. FileOutputStream and/or byte array. AndroidGenerator.java");
			ioe.printStackTrace();
		}
		

		// if any new elements added to elements, update XML
		AppElementListGenerator a = new AppElementListGenerator(elements);
		a.GenerateElementList();
		
	}
	/**
	 * Method for sorting our Vector when we need it for generating the Android main.xml file
	 * 
	 * @param key the paramater by which you want to sort. e.g., "x", "y", etc
	 * @param elements the Vector<Element> you wish sorted
	 * @return the sorted Vector<Element>
	 */
	public Vector<AndroidElement> sortElements(String key, Vector<AndroidElement> elements)
	{
		if(key.equals("x"))
		{
			Collections.sort(elements, new ElementSortX());
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
	
	public Vector<AndroidElement> setAttributes(Vector<AndroidElement> elements)
	{
		int basex;	//the element's x coordinate
		int upperx;	//upper-bound for checking elements
		int lowerx;	//lower bound for checking elements
		
		int basey;
		int uppery;
		int lowery;
		
		String below = "";
		String left = "";
		
		//we'll need to reference next and previous elements, so we can't use a for each
		for(int i = 0; i < elements.size(); i++)
		{
					
			//since text boxes fill the whole horizontal line, as long as it's not the first item we can set its "below" feature
			if(elements.elementAt(i).getType().equals("ATextBox"))
			{
				//if textbox is last element this line causes an out of bounds array error
				elements.elementAt(i+1).setBelow(elements.elementAt(i).getName());
				
				//there are no left and right...so move on
				continue;
			}
			
			/**
			 * It seems that only the one ON THE RIGHT should have alignParentRight=true. Having the one on the left will screw
			 * things up...
			 */
			//if the x-position is sufficiently right, we should set right-alignment
			if(elements.elementAt(i).getX() > Constants.EditorWidth / 2)
			{
				elements.elementAt(i).alignParentRight();
				System.out.println("Set parent right!");
			}
			
			
			/*
			 * Trying to set elements to left but so far comment above holds true. only alignParentRight can equal true and not
			 * mess things up.
			 * 
			else if(elements.elementAt(i).getX() < Constants.EditorWidth / 2)
			{
				elements.elementAt(i).setLeft(left);
			}
			
			if(!elements.elementAt(i).getBelow().equals(""))
			{
				left = elements.elementAt(i).getLeft();
			}
			
			End of left attempt
			*/
			
			
			//it shouldn't matter if upper or lower go above or below the screen resolution. We're setting relative positioning anyway
			// so negative numbers and such shouldn't matter. We'll see how true that is in testing...
			basex = elements.elementAt(i).getX();
			upperx = basex + 15;
			lowerx = basex - 15;
			
			basey = elements.elementAt(i).getY();
			uppery = basey + 15;
			lowery = basey - 15;
			
			//if the element has a "setBelow" property, then all elements "beside" it must have the same "setBelow"
			// so things aren't drawn on top of one another. Seems unnecessary, but it fixes a weird-ass bug
			if(!elements.elementAt(i).getBelow().equals(""))
			{
				below = elements.elementAt(i).getBelow();
			}
			
			//after we establish upper and lower bounds (because users will never get the alignment perfect, 
			//we see if the elements are in a horizontal row. If they are, determine left and right. 
			for(int j = i; j < elements.size(); j++)
			{
				
				
				//lowery < element[i].getY < uppery
				//If these are true we KNOW they're in a line. Need to have left and right set 
				if(elements.elementAt(j).getY() < uppery && elements.elementAt(j).getY() > lowery)
				{
					//we've established they're in a line. They are below the same element
					elements.elementAt(j).setBelow(below);
					
					//textBoxes fill the whole horizontal line on the screen. Skip those for setLeft and setRight
					if(elements.elementAt(j).getType().equals("ATextBox"))
					{
						continue;
					}
					//If element j has a greater x than element i, then j is to the right of i
					if(elements.elementAt(j).getX() > elements.elementAt(i).getX())
					{
						elements.elementAt(j).setRight(elements.elementAt(i).getName());
					}
					//likewise if j has a smaller x than i, j is to the left of i
					if (elements.elementAt(j).getX() < elements.elementAt(i).getX())
					{
						elements.elementAt(j).setLeft(elements.elementAt(i).getName());
						elements.elementAt(j).removeParentRight();
						System.out.println("Removed parent right!");						
					}
					
				}
				else //elements are not in a line. We can set a "below" feature
				{
					elements.elementAt(j).setBelow(elements.elementAt(i).getName());
				}
			}
		}
		
		return elements;
	}
	
}
