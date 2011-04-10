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

	public test() {
		
	}
	
	public void testXMLParser() {
		AndroidGenerator a = new AndroidGenerator();
		a.GenerateAndroidCode();
		for ( int i = 0; i < a.applicationElements.size(); i++ )
		{
			System.out.println(a.applicationElements.elementAt(i).getType() + " " +
					a.applicationElements.elementAt(i).getX() + " " +
					a.applicationElements.elementAt(i).getY() + " " +
					a.applicationElements.elementAt(i).getName() + " " +
					a.applicationElements.elementAt(i).getHeight() + " " +
					a.applicationElements.elementAt(i).getWidth());
		}	
	}
}
