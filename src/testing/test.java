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
		System.out.println("xml parsed:");
		for ( int i = 0; i < a.applicationElements.size(); i++ )
		{
			System.out.println(a.applicationElements.elementAt(i).getType() + " " +
					a.applicationElements.elementAt(i).getX());
		}	
	}
}
