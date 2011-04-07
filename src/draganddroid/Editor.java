package draganddroid;

import global.Constants;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;

import testing.test;
import android.Element;
import android.SaxXMLParser;

public class Editor {
	
	Vector<AndroidElement> elements;
	JFrame f;
	boolean firstOpen;
	
	public Editor() {
		elements = new Vector<AndroidElement>();
		firstOpen = true;
	}
	
	public void Open()
	{
		f = new JFrame("Drag And Droid Editor");
		f.setSize(480, 800);
		Canvas c = new OurCanvas(this);
		c.setBackground(Color.WHITE);
		c.setSize(Constants.EditorWidth, Constants.EditorHeight);
		f.add(c);
		
		//Das Tool Box
		JFrame toolbox = new JFrame("ToolBox");
		JButton AddButton = new JButton("AddButton");
		JButton AddTextbox = new JButton("AddTextbox");
		JButton AddLabel = new JButton("AddLabel");
		JButton Generate = new JButton("Generate");	
		
		//Das Tool Box Container
		Container contentpane = toolbox.getContentPane();
		contentpane.setLayout(new GridLayout(4,2));
		contentpane.add(AddButton);
		contentpane.add(AddTextbox);
		contentpane.add(AddLabel);
		contentpane.add(Generate);
		
		//Das Button Listeners
		ButtonListener bclicked = new ButtonListener(this);
		AddButton.addActionListener(bclicked);
		AddTextbox.addActionListener(bclicked);
		AddLabel.addActionListener(bclicked);
		Generate.addActionListener(bclicked);
		
		//Das Mouse Listeners
		c.addMouseListener(bclicked);
	    c.addMouseMotionListener(bclicked);
		
		//Das Tool Box Properties
		toolbox.setSize(100,200);
		toolbox.setLocation(500, 0);
		toolbox.setVisible(true);
		
		// get any new elements from xml that were added manually
		if (!firstOpen) CheckForNewElements();
			
		f.pack();
		f.setVisible(true);		
		
		test t = new test();
		t.testXMLParser(); 
	}

	/**
	 * when editor gets launched a second time, check for any new additions to 
	 * xml file, as in any new elements.
	 * 
	 * still needs to be completed
	 */
	private void CheckForNewElements() {
		firstOpen = false;
		
		Vector<Element> e = new Vector<Element>();
		
		SaxXMLParser parser = new SaxXMLParser(Constants.filename, e);
		
		parser.parseDocument();
		
	}
}
