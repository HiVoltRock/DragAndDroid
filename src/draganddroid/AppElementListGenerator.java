package draganddroid;

import java.io.FileWriter;
import global.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

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

    Vector<AndroidElement> elements;

    public AppElementListGenerator(Vector<AndroidElement> elements) {
        this.elements = elements;
    }

    public void GenerateElementList() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(Constants.filename), true);

            out.println("<?xml version='1.0' encoding='UTF-8' ?>");
            out.println("<ElementList>");
            
            for (int i = 0; i < elements.size(); i++) {
                out.println(elements.elementAt(i).outputElementXML());
            }
               
            out.println("</ElementList>");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
