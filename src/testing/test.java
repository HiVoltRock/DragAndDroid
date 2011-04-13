package testing;

import android.AndroidGenerator;

/**
 * can use this class to test specific classes without having 
 * launch entire application
 * 
 * @author Anthony
 *
 */
public class test {

	String xml = "main.xml";
	
	public test() {
		
	}
	
	public void testXMLParser() {
		AndroidGenerator a = new AndroidGenerator();
		a.GenerateAndroidCode(xml);
		for ( int i = 0; i < a.elements.size(); i++ )
		{
			System.out.println(a.elements.elementAt(i).getType() + " " +
					a.elements.elementAt(i).getX() + " " +
					a.elements.elementAt(i).getY() + " " +
					a.elements.elementAt(i).getName() + " " +
					a.elements.elementAt(i).getHeight() + " " +
					a.elements.elementAt(i).getWidth());
		}	
	}
}
