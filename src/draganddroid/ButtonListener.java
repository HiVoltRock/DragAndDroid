package draganddroid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import testing.test;
import android.AndroidGenerator;

public class ButtonListener extends MouseAdapter implements ActionListener{

	Editor editor;
    OurCanvas ourcanvasref;
	AppElementListGenerator generator;
	AndroidGenerator andGen;
	
	int x,y; //some coordinates to use
	int currentItemDragged;
	
	//The following variables control which button is active
	int task = 0;
	
	int addbutton = 1;
	int addlabel = 2;
	int addtextbox = 3;
	int generate = 4;
	
	//Constructor
	public ButtonListener(Editor e){
		this.editor = e;
		generator = new AppElementListGenerator(this.editor.elements);
		andGen = new AndroidGenerator();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("AddButton")){
			task = addbutton;
		}
		else if(e.getActionCommand().equals("AddLabel")){
			task = addlabel;
		}
		else if(e.getActionCommand().equals("AddTextbox")){
			task = addtextbox;
		}
		else if(e.getActionCommand().equals("Generate")){
			task = generate;
		
			//generates the temp xml file
			generator.GenerateElementList();
			
			//generates the main.xml file in /res/layout
        	andGen.GenerateAndroidCode();
		}
	}
	
	 @Override
    public void mouseClicked(MouseEvent k){
        if(task == addbutton){
        	String name = JOptionPane.showInputDialog(null,
        			  "What is the name of the button?",
        			  "Enter the name of the button",
        			  JOptionPane.QUESTION_MESSAGE);
            editor.elements.add(new AButton(name,k.getX(),k.getY()));
        }
        else if(task == addlabel){
        	String name = JOptionPane.showInputDialog(null,
      			  "What is the name of the label?",
      			  "Enter the name of the label",
      			  JOptionPane.QUESTION_MESSAGE);
            editor.elements.add(new ALabel(name,k.getX(),k.getY()));
        }
        else if(task == addtextbox){
        	String name = JOptionPane.showInputDialog(null,
      			  "What is the name of the text box?",
      			  "Enter the name of the text box",
      			  JOptionPane.QUESTION_MESSAGE);
            editor.elements.add(new ATextBox(name,k.getX(),k.getY()));
        }	       
    }
	 /*..
	  * The mouse gets pressed anywhere on the screen this function then loops
	  * through our list of elements to find a match. It stores the x and y
	  * relative to the mouse click in the x and y coordinates and stores the
	  * element index in currentItemDragged
	  */
	 @Override
	 public void mousePressed(MouseEvent k){
		 for(int i = 0; i<editor.elements.size(); i++){
			 if(editor.elements.elementAt(i).isInside(k.getX(), k.getY())){
				 x = editor.elements.elementAt(i).getX();
				 y = editor.elements.elementAt(i).getY();
				 currentItemDragged = i;
				 
			 }
		 }
	 }
	 
	 /*..
	  * This function gets called when the mouse is pressed and dragged. It uses
	  * the currentItemDragged index to simply move the coordinates of the said index.
	  * I added the setX and setY functions to our AndroidElement class for this
	  * functionality. Every change in x or y passes the new x and y and also repaints
	  * the canvas.
	  */
	 @Override
	 public void mouseDragged(MouseEvent k){
		 editor.elements.elementAt(currentItemDragged).setX(k.getX());
		 editor.elements.elementAt(currentItemDragged).setY(k.getY());
		 ourcanvasref.repaint();
		 
	 }
	 
	 /*..
	  * We set the currentItemDragged to -1, this way if the user presses
	  * and clicks off the button it doesn't spontaneously move the previously
	  * dragged button. Repaint is also needed to be safe.
	  */
	 @Override
	 public void mouseReleased(MouseEvent k){
		 currentItemDragged = -1;
		 ourcanvasref.repaint();
	 }
}
 