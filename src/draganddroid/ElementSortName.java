package draganddroid;

import java.util.Comparator;

import element.AndroidElement;

public class ElementSortName implements Comparator<AndroidElement>
{

	@Override
	public int compare(AndroidElement element1, AndroidElement element2) {
		return element1.getName().compareTo(element2.getName());
	}

}
