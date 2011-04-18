package xml;

import global.Constants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import element.AndroidElement;

/**
 * updates xml file with any new elements added 
 * during android code generation
 * 
 * @author Anthony
 *
 */
public class ElementXMLUpdator {
	
	public ElementXMLUpdator() {
		
	}
	
	public void UpdateXMLFile(AndroidElement element) {
		try {
        	File file = new File(Constants.filename);
        	
        	System.out.println(file.getName());
        	BufferedWriter out = new BufferedWriter(new FileWriter(file));
        	
        	out.write( element.outputElementXML() );
            out.close();
            System.out.println("file loc -> " + file.getAbsolutePath());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
