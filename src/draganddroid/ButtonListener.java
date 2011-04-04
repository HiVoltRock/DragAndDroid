package draganddroid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
			System.out.println("Add button");
		}
		else if(e.getActionCommand().equals("AddLabel")){
			task = addlabel;
			System.out.println("Add label");
		}
		else if(e.getActionCommand().equals("AddTextbox")){
			task = addtextbox;
			System.out.println("Add text box");
		}
		else if(e.getActionCommand().equals("Generate")){
			task = generate;
			System.out.println("generate");
			AppElementListGenerator generator = new AppElementListGenerator(editor.elements);
        	//generator.GenerateElementList();
		}
	}
	
	 @Override
    public void mouseClicked(MouseEvent k){
        if(task == addbutton){
            editor.elements.add(new AButton("Button",k.getX(),k.getY()));
        }
        else if(task == addlabel){
            editor.elements.add(new ALabel("Label",k.getX(),k.getY()));
        }
        else if(task == addtextbox){
            editor.elements.add(new ATextBox("Text Box",k.getX(),k.getY()));
        }	       
    }
}
 