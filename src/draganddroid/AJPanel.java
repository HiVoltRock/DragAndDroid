package draganddroid;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class AJPanel extends JFrame {

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