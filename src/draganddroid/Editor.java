package draganddroid;

import java.awt.Canvas;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Editor {
	
	public Editor() {
		
	}
	
	public void Open()
	{
		JFrame f = new JFrame("Drag And Droid");
		f.setSize(480, 800);
		Canvas c = new OurCanvas();
		c.setSize(480,800);
		f.add(c);
		
		Editor E = new Editor();
		
		/*
		AJPanel frame = new AJPanel();
        frame.pack();
        frame.setVisible(true);
		*/
		
		//Das Tool Box
		JFrame toolbox = new JFrame("ToolBox");
		JButton AddButton = new JButton("AddButton");
		
		
		//Das Tool Box Container
		Container contentpane = toolbox.getContentPane();
		contentpane.setLayout(new GridLayout(8,2));
		contentpane.add(AddButton);
		
		//Das Button Listeners
		ButtonListener bclicked = new ButtonListener(E);
		AddButton.addActionListener(bclicked);
		
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

		OurCanvas() {
			super();
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

		}

	}

}
