package testing;

import editorView.EditPropertiesDialog;
import editorView.Editor;
import element.AButton;
import junit.framework.TestCase;

public class Tester extends TestCase {
	Tester() {}
	
	void TestEditProps() {
		new EditPropertiesDialog(new Editor(), true, new AButton("ANTHONYbtn", 100, 100));
	}
	
}
