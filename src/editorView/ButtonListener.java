package editorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import xml.AppElementListGenerator;
import android.AndroidGenerator;
import element.AButton;
import element.ALabel;
import element.ASeekBar;
import element.ATextBox;
import element.AUserField;

/**
 * ButtonListener handles events like mouse clicks
 * and button presses on the editor as well
 * as the toolbox
 *
 */
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
	int addseekbar = 5;
	int adduserfield = 6;
	
	String currentElementName;
	
	//Constructor
	public ButtonListener(Editor e){
		this.editor = e;
		generator = new AppElementListGenerator(this.editor.elements);
		andGen = new AndroidGenerator(e.rootDir);
		currentItemDragged = -1;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		currentElementName="";
		if(e.getActionCommand().equals("AddButton")){
			task = addbutton;
			currentElementName = JOptionPane.showInputDialog(null,
      			  "What is the name of the button?",
      			  "Enter the name of the button",
      			  JOptionPane.QUESTION_MESSAGE);
		}
		else if(e.getActionCommand().equals("AddLabel")){
			task = addlabel;
			currentElementName = JOptionPane.showInputDialog(null,
        			  "What is the name of the label?",
        			  "Enter the name of the label",
        			  JOptionPane.QUESTION_MESSAGE);
		}
		else if(e.getActionCommand().equals("AddTextbox")){
			task = addtextbox;
        	currentElementName = JOptionPane.showInputDialog(null,
        			  "What is the name of the text box?",
        			  "Enter the name of the text box",
        			  JOptionPane.QUESTION_MESSAGE);
		}
		else if(e.getActionCommand().equals("AddSeekBar")){
			task = addseekbar;
        	currentElementName = JOptionPane.showInputDialog(null,
        			  "What is the name of the seek bar?",
        			  "Enter the name of the seek bar",
        			  JOptionPane.QUESTION_MESSAGE);
		}
		else if(e.getActionCommand().equals("AddUserField")){
			task = adduserfield;
        	currentElementName = JOptionPane.showInputDialog(null,
        			  "What is the name of the user field?",
        			  "Enter the name of the user field",
        			  JOptionPane.QUESTION_MESSAGE);
		}
		else if(e.getActionCommand().equals("Generate")){
			task = generate;
		
			FileOutputStream eraser;
			try {
				eraser = new FileOutputStream(editor.xmlDir);
				byte b[] = new byte[0];
				eraser.write(b);
				eraser.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
			//generates the temp xml file
			generator.GenerateElementList();
			
			//generates the main.xml file in /res/layout
        	andGen.GenerateAndroidCode(editor.xmlDir);
        	
        	editor.elements.clear();
        	editor.dispose();
        	editor.setVisible(false);
        	
        	editor.toolbox.dispose();
        	editor.toolbox.setVisible(false);
		}
		if ( currentElementName == null) {
			task = 0;
		}
	}
	
	 @Override
    public void mouseClicked(MouseEvent k){
        if(task == addbutton){
            editor.elements.add(new AButton(currentElementName,k.getX(),k.getY()));
            task = 0;
        }
        else if(task == addlabel){
            editor.elements.add(new ALabel(currentElementName,k.getX(),k.getY()));
            task = 0;
        }
        else if(task == addtextbox){
            editor.elements.add(new ATextBox(currentElementName,k.getX(),k.getY()));
            task = 0;
        }	
        else if(task == addseekbar) {
        	editor.elements.add(new ASeekBar(currentElementName,k.getX(),k.getY()));
            task = 0;
        }
        else if(task == adduserfield) {
        	editor.elements.add(new AUserField(currentElementName,k.getX(),k.getY()));
            task = 0;
        }
    }
	 /**
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
	 
	 /**
	  * This function gets called when the mouse is pressed and dragged. It uses
	  * the currentItemDragged index to simply move the coordinates of the said index.
	  * I added the setX and setY functions to our AndroidElement class for this
	  * functionality. Every change in x or y passes the new x and y and also repaints
	  * the canvas.
	  */
	 @Override
	 public void mouseDragged(MouseEvent k){
		 try{
			 
		 if(currentItemDragged != -1){
		 editor.elements.elementAt(currentItemDragged).setX(k.getX());
		 editor.elements.elementAt(currentItemDragged).setY(k.getY());
		 ourcanvasref.repaint();
		 }
		 }catch(NullPointerException e){}
	 }
	 
	 /**
	  * We set the currentItemDragged to -1, this way if the user presses
	  * and clicks off the button it doesn't spontaneously move the previously
	  * dragged button. Repaint is also needed to be safe.
	  */
	 @Override
	 public void mouseReleased(MouseEvent k){
		 try{
		 currentItemDragged = -1;
		 ourcanvasref.repaint();
		 } catch (NullPointerException e){}
	 }
}
 