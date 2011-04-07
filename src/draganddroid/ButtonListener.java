package draganddroid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class ButtonListener extends MouseAdapter implements ActionListener{

	Editor editor;// = new Editor();
	//The following variables control which button is active
	int task = 0;
	
	int addbutton = 1;
	int addlabel = 2;
	int addtextbox = 3;
	int generate = 4;
	
	//Constructor
	public ButtonListener(Editor e){
		this.editor = e;
		
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
			AppElementListGenerator generator = new AppElementListGenerator(editor.elements);
        	generator.GenerateElementList();
		}
	}
	
	 @Override
    public void mouseClicked(MouseEvent k){
        if(task == addbutton){
        	String name = JOptionPane.showInputDialog(null,
        			  "What is the name of the button?",
        			  "Enter th ename of the button",
        			  JOptionPane.QUESTION_MESSAGE);
            editor.elements.add(new AButton(name,k.getX(),k.getY()));
        }
        else if(task == addlabel){
        	String name = JOptionPane.showInputDialog(null,
      			  "What is the name of the label?",
      			  "Enter th ename of the label",
      			  JOptionPane.QUESTION_MESSAGE);
            editor.elements.add(new ALabel(name,k.getX(),k.getY()));
        }
        else if(task == addtextbox){
        	String name = JOptionPane.showInputDialog(null,
      			  "What is the name of the text box?",
      			  "Enter th ename of the text box",
      			  JOptionPane.QUESTION_MESSAGE);
            editor.elements.add(new ATextBox(name,k.getX(),k.getY()));
        }	       
    }
}
 