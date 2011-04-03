package draganddroid;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class AJPanel extends JFrame {

	private static final long serialVersionUID = 1L;

	public AJPanel() {
        super("ToolBar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image1 = new ImageIcon("InsertPicHere");
        JButton button1 = new JButton(image1);
        ImageIcon image2 = new ImageIcon("InsertAnotherPicHere");
        JButton button2 = new JButton(image2);
        ImageIcon image3 = new ImageIcon("InsertYetAnotherPicHere");
        JButton button3 = new JButton(image3);
        JToolBar bar = new JToolBar();
        bar.add(button1);
        bar.add(button2);
        bar.add(button3);
        JTextArea edit = new JTextArea(8,40);
        JScrollPane scroll = new JScrollPane(edit);
        JPanel pane = new JPanel();
        BorderLayout bord = new BorderLayout();
        pane.setLayout(bord);
        pane.add("North", bar);
        pane.add("Center", scroll);

        setContentPane(pane);
        
    }
}