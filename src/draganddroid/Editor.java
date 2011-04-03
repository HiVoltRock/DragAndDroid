package draganddroid;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Editor {
	
	Vector<AndroidElement> elements;
	
	public Editor() {
		elements = new Vector<AndroidElement>();
	}
	
	public void Open()
	{
		JFrame f = new JFrame("Drag And Droid");
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
		
		
		//Das Tool Box Container
		Container contentpane = toolbox.getContentPane();
		contentpane.setLayout(new GridLayout(8,2));
		contentpane.add(AddButton);
		contentpane.add(AddTextbox);
		contentpane.add(AddLabel);
		
		//Das Button Listeners
		ButtonListener bclicked = new ButtonListener(this);
		AddButton.addActionListener(bclicked);
		AddTextbox.addActionListener(bclicked);
		AddLabel.addActionListener(bclicked);
		
		//Das Mouse Listeners
		c.addMouseListener(bclicked);
	    c.addMouseMotionListener(bclicked);
		
		//Das Tool Box Properties
		toolbox.setSize(150,400);
		toolbox.setVisible(true);
		
		f.pack();
		f.setVisible(true);		
	}
	
	/*.. Here is das canvas this is where all the drawing will
	 * take place NAH MEAN
	 */
	
	class OurCanvas extends Canvas {

		private static final long serialVersionUID = 1L;
		Editor e;
		
		OurCanvas(Editor e) {
			super();
			this.e = e;
		}

		// Prevents flicker
		@Override
		public void update(Graphics g) {

			Image on = createImage(getWidth(), getHeight());
			print(on.getGraphics());
			g.drawImage(on, 0, 0, this);

		}

		// paints all of our data
		@Override
		public void paint(Graphics g) {
			for ( int i = 0; i < e.elements.size(); i++ ) {
				e.elements.elementAt(i).draw(g);
			}
		}

	}

}
