package editorView;

import global.EventType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
		andGen = new AndroidGenerator(e.rootDir);
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
		else if ( ae.getActionCommand() == "ElementProperties" ) {
			Object[] possibilities = e.getElementNames();
			String s = (String)JOptionPane.showInputDialog(
			                    parent,
			                    "Choose the element\n"
			                    + "that you would like to edit",
			                    "Edit Properties",
			                    JOptionPane.PLAIN_MESSAGE,
			                    null,
			                    possibilities,
			                    "");
			if ( !s.equals(null) ) {
				new EditPropertiesDialog( e, true, e.FindElement( s ));
			}
			
		}
		else if ( ae.getActionCommand() == "Add Event" ) {
			Object[] possibilities = e.getButtonNames();
			String s = (String)JOptionPane.showInputDialog(
			                    parent,
			                    "Choose the button\n"
			                    + "that you would like to add an event for",
			                    "Edit Properties",
			                    JOptionPane.PLAIN_MESSAGE,
			                    null,
			                    possibilities,
			                    "");
			if ( !s.equals(null) ) {
				Object[] events = {"NONE", "ONCLICK", "LONGPRESS" };
				String t = (String)JOptionPane.showInputDialog(
	                    parent,
	                    "Which event would you like\n"
	                    + "add an event to " + s + "?",
	                    "Choose Event",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    events,
	                    "");
				if ( !t.equals(null) ) {
					if ( t.equals("ONCLICK") ) {
						e.FindElement( s ).event = EventType.ONCLICK;
	            	}
	            	else if ( t.equals("LONGPRESS") ) {
	            		e.FindElement( s ).event = EventType.LONGPRESS;
	            	}
	            	else {
	            		e.FindElement( s ).event = EventType.NONE;
	            	}
				}
			}
		}
	}

}
