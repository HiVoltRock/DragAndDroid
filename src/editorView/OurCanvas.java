package editorView;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;


public class OurCanvas extends Canvas {

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
		this.repaint();
	}
}