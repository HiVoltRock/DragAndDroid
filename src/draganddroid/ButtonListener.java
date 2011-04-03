package draganddroid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class ButtonListener extends MouseAdapter implements ActionListener{

	Editor editor;// = new Editor();
	//The following variables control which button is active
	int task = 0;
	
	int addbutton = 1;
	
	//Constructor
	public ButtonListener(Editor e){
		this.editor = e;
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Random r = new Random();
		if(e.getActionCommand().equals("AddButton")){
			task = addbutton;
			editor.elements.add(new AButton(r.nextInt(400), r.nextInt(600)));
			System.out.println("Add button");
		}
		else if(e.getActionCommand().equals("AddLabel")){
			task = addbutton;
			editor.elements.add(new ALabel());
			System.out.println("Add label");
		}
		else if(e.getActionCommand().equals("AddTextbox")){
			task = addbutton;
			editor.elements.add(new ATextBox());
			System.out.println("Add text box");
		}
		
	}
	
	 @Override
	    public void mouseClicked(MouseEvent k){
	        if(task == addbutton){
	            System.out.println("mouseclicked");
	        }
	       
	    }

}
 