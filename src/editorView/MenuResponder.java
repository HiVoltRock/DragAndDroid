package editorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import xml.AppElementListGenerator;



import android.AndroidGenerator;

public class MenuResponder implements ActionListener {

	Editor e;
	JFrame parent;
	OurCanvas oc;
	AppElementListGenerator generator;
	AndroidGenerator andGen;
	
	public MenuResponder(Editor e, JFrame parent, OurCanvas oc) {
		this.e = e;
		this.parent = parent;
		this.oc = oc;
		generator = new AppElementListGenerator(this.e.elements);
		andGen = new AndroidGenerator();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if ( ae.getActionCommand() == "New" ) {
			e.elements.clear();
		}
		else if ( ae.getActionCommand() == "Generate" ) {
			//generates the temp xml file
			generator.GenerateElementList();
			
			//generates the main.xml file in /res/layout
        	andGen.GenerateAndroidCode(e.xmlDir);		
		}
		else if ( ae.getActionCommand() == "Button" ) {
			
		}
		else if ( ae.getActionCommand() == "Label" ) {
			
		}
		else if ( ae.getActionCommand() == "TextBox" ) {
			
		}
	}

}
