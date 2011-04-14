package draganddroid;

import java.util.Comparator;

import element.AndroidElement;

public class ElementSortY implements Comparator<AndroidElement>
{

	@Override
	public int compare(AndroidElement element1, AndroidElement element2 ) 
	{
		return element1.getY() - element2.getY();
	}

}
