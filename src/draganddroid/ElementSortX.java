package draganddroid;

import java.util.Comparator;

import android.Element;

public class ElementSortX implements Comparator<Element>
{

	@Override
	public int compare(Element element1, Element element2) 
	{
		return element1.getX() - element2.getX();
	}

}
