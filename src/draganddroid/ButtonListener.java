package draganddroid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonListener extends MouseAdapter implements ActionListener{

	Editor e = new Editor();
	
	//Constructor
	public ButtonListener(Editor e){
		this.e = e;
		
	}
	
	//The following variables control which button is active
	int task = 0;
	
	int addbutton = 1;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("AddButton")){
			task = addbutton;
			System.out.print("Heard");
		}
		
	}
	
	 @Override
	    public void mouseClicked(MouseEvent k){
	        if(task == addbutton){
	            System.out.println("mouseclicked");
	        }
	       
	    }

}
 