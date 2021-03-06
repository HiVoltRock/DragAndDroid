package android;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.Vector;

import xml.AppElementListGenerator;
import xml.SaxXMLParser;
import draganddroid.ElementSortX;
import draganddroid.ElementSortY;
import element.AndroidElement;
import global.Constants;
import global.EventType;

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
	String rootDir; //root directory of Android project (for method stub generation)
	boolean seekGenerated = false; //so we know if there has been a seekBar generated or not
	
	//renamed "applicationElements" to "elements" because it's WAY shorter
	public Vector<AndroidElement> elements;
	SaxXMLParser parser;
	File xml;
	
	public int originalElementCt;
	
	public AndroidGenerator(String rootDir) 
	{
		elements = new Vector<AndroidElement>();
		parser = new SaxXMLParser(Constants.filename, elements);
		this.rootDir = rootDir;
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
			//System.out.println("XML File exists");
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
			pw.println("\tandroid:layout_height=\"fill_parent\"" +
					"\tandroid:id=\"@+id/myLayout\">");
			
			//TODO in TestBed.java file, should print new stuff after original code, 
			// label it, and then put a comment that tells the user that they must either
			//must get rid of old code or delete new code
			for(AndroidElement e : elements)
			{
				e.printAndroidXml(pw);
				
				//generates button listeners for every button. We assume if there's a button they want a listener
				if(e.getType().equals("AButton"))
				{
					generateMethodStub(e);
				}
				//only want to generate one set of listeners for all seekBars
				if(e.getType().equals("ASeekBar") && !seekGenerated)
				{
					seekBarMethods(e);
					seekGenerated = true;
				}
				
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
			if(elements.elementAt(i).getType().equals("ATextBox") && i < elements.size()-1)
			{
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
				//System.out.println("Set parent right!");
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
						//System.out.println("Removed parent right!");						
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
	
	/**
	 * Generates the onClick method stub in the main java file
	 */
	public void generateMethodStub(AndroidElement ae)
	{
		String srcDir = this.rootDir + "/src";
		
		//we need to actually find the file we're going to edit...
		File directory = new File(srcDir);
		File src = findFile(directory, getFileName(this.rootDir));
		
		try 
		{
			RandomAccessFile editedFile = new RandomAccessFile(src, "rw");
			long length = editedFile.length();
			System.out.println("File length before truncation: " + length);
			//assuming that the last line is only one closing brace. Chop that brace off
			editedFile.setLength(length-1);
			System.out.println("File length after truncation: " + editedFile.length());
			
			editedFile.seek(length);
			
			editedFile.writeUTF("\n\n\tpublic void " + ae.getName() + "_onClick(View view)\n\t{\n\n\t}\n}");			
			editedFile.close();
			
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Cannot find random access file");
			e.printStackTrace();
		}
		catch(IOException e)
		{
			System.out.println("I/O problem with random access file");
			e.printStackTrace();
		}
				
		
	}
	
	public void seekBarMethods(AndroidElement ae)
	{
		String srcDir = this.rootDir + "/src";
		
		//we need to actually find the file we're going to edit...
		File directory = new File(srcDir);
		File src = findFile(directory, getFileName(this.rootDir));
		
		try 
		{
			RandomAccessFile editedFile = new RandomAccessFile(src, "rw");
			long length = editedFile.length();
			//System.out.println("File length before truncation: " + length);
			
			//assuming that the last line is only one closing brace. Chop that brace off
			editedFile.setLength(length-1);
			//System.out.println("File length after truncation: " + editedFile.length());
			
			editedFile.seek(length);
			
			editedFile.writeUTF("\n\n\tprivate SeekBar.OnSeekBarChangeListener seekBarChangeListener =  "
					+"new SeekBar.OnSeekBarChangeListener()\n"
					+"\t{\n\n"
					+"\t\t@Override\n"
					+"\t\tpublic void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser)\n"
					+"\t\t{\n"
					+"\t\t//TODO: Your Code Here\n"
					+"\t\t}\n\n"
					+"\t\t@Override\n"
					+"\t\tpublic void onStartTrackingTouch(SeekBar seekBar) \n"
					+"\t\t{\n"
					+"\t\t//TODO: Your Code Here\n"
					+"\t\t}\n\n"
					+"\t\t@Override\n"
					+"\t\tpublic void onStopTrackingTouch(SeekBar seekBar) \n"
					+"\t\t{\n"
					+"\t\t//TODO: Your Code Here\n"
					+"\t\t}\n\n"
					+"\t};\n\n"
					+"}"
					
			);			
			editedFile.close();
			
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Cannot find random access file");
			e.printStackTrace();
		}
		catch(IOException e)
		{
			System.out.println("I/O problem with random access file");
			e.printStackTrace();
		}
		
		
	}
	
	public void seekBarCreate()
	{
		
	}
	
	/**
	 * Because Android names the main java file the same as the project, we find the file whose name matches the
	 * project name. I have the feeling this method breaks cross-platform compatibility because Windows does use forward slashes.
	 * Could probably hack it back together
	 * 
	 * TODO: make sure this works in Windows
	 */
	public String getFileName(String dir)
	{
		for(int i = dir.length()-1; i >= 0; --i)
		{
			if(dir.charAt(i) == '/')
			{
				dir = dir.substring(i+1, dir.length()) + ".java";
				break;
			}
		}
		
		//System.out.println("Project source file: " + dir);
		return dir;
	}
	
	/**
	 * This method takes in a directory (type File), and a string for what file it should find, then returns 
	 * the file it found. Super necessary to generate the method stub. that is to say, without the name, what
	 * exactly are we supposed to use to generate the code automatically? We can't, that's how
	 */
	public File findFile(File dir, String name) 
	{
		  File result = null; // no need to store result as String, we're returning File anyway
		  File[] dirlist  = dir.listFiles(); //list files in directory as array

		  for(int i = 0; i < dirlist.length; i++) 
		  { 
		    if(dirlist[i].isDirectory()) 
		    {
		      result = findFile(dirlist[i], name); //recursive call
		      if (result!=null) 
		      {
		    	  break; // recursive call found the file; terminate the loop
		      }
		    } 
		    else if(dirlist[i].getName().matches(name)) 
		    {
		      return dirlist[i]; // found the file...return
		    }
		  }
		  return result; // returns null if not found. Might cause problems if the user didn't do exactly what we assumed
		}
	
}
