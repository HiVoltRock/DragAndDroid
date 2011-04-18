package xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import element.AEvent;
import element.AndroidElement;
import global.Constants;
import global.EventType;

/**
 * This class will generate a formatted file of all of the elements that
 * have been dragged and dropped onto the canvas when finished.
 * 
 * The file will be passed to the android part and that will, parse this file
 * and create those specified elements 
 * 
 * @author Anthony Favia
 *
 */
public class AppElementListGenerator {

    public Vector<AndroidElement> elements;

    public AppElementListGenerator(Vector<AndroidElement> elements) {
        this.elements = elements;
    }

    public void GenerateElementList() 
    {
        try {
        	File file = new File(Constants.filename);
        	
        	System.out.println(file.getName());
        	BufferedWriter out = new BufferedWriter(new FileWriter(file));

        	FileOutputStream eraser = new FileOutputStream(Constants.filename);
			byte b[] = new byte[0];
			eraser.write(b);
			eraser.close();
			
            out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            out.write("<ElementList>\n");
            
            for (int i = 0; i < elements.size(); i++) {
                out.write(elements.elementAt(i).outputElementXML());
            }
            
            Vector<AEvent> allEvents = getEventList();
            for ( AEvent ae : allEvents ) {
            	out.write("<Event type=\"" + ae.type + 
            			"\" name=\"" + ae.name + "\" event=\"" +
            			ae.event + "\">/n");
            	out.write("</Event>");
            }
            
            out.write("</ElementList>\n");
            
            
            out.close();
            System.out.println("file loc -> " + file.getAbsolutePath());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Vector<AEvent> getEventList() {
    	Vector<AEvent> ae = new Vector<AEvent>();
    	for( AndroidElement e : elements ) {
    		for (EventType event : e.getEvents() )
    		{
    			ae.add(new AEvent(e.getType(), e.getName(), event));
    		}
    	}
    	return ae;
    }
	
}
