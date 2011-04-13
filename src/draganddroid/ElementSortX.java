package draganddroid;

import java.util.Comparator;

public class ElementSortX implements Comparator<AndroidElement>
{

	@Override
	public int compare(AndroidElement element1, AndroidElement element2) 
	{
		return element1.getX() - element2.getX();
	}

}
