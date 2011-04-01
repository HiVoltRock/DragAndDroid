package draganddroid;

import javax.swing.JFrame;

public class Editor {
	
	public Editor() {}
	
	public void Open()
	{
		JFrame f = new JFrame("Drag And Droid");
		
		f.setSize(480, 800);
		
		AJPanel frame = new AJPanel();
        frame.pack();
        frame.setVisible(true);
		
		f.pack();
		f.setVisible(true);
		
		
	}

}
