package draganddroid;

import java.awt.Canvas;
import testing.test;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Editor {
	
	Vector<AndroidElement> elements;
	JFrame f;
	
	public Editor() {
		elements = new Vector<AndroidElement>();
	}
	
	public void Open()
	{
		f = new JFrame("Drag And Droid");
		f.setSize(480, 800);
		Canvas c = new OurCanvas(this);
		c.setBackground(Color.WHITE);
		c.setSize(480,800);
		f.add(c);
		
		//Editor E = new Editor();
		
		/*
		AJPanel frame = new AJPanel();
        frame.pack();
        frame.setVisible(true);
		*/
		
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
		
		f.pack();
		f.setVisible(true);		
		
		test t = new test();
		t.testXMLParser(); 
	}
}
