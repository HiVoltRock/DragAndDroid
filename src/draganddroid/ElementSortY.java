package draganddroid;

import java.util.Comparator;

public class ElementSortY implements Comparator<AndroidElement>
{

	@Override
	public int compare(AndroidElement element1, AndroidElement element2 ) 
	{
		return element1.y - element2.y;
	}

}
