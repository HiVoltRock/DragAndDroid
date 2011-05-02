package draganddroid.handlers;

import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import editorView.Editor;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	Editor E;
	
	/**
	 * The constructor.
	 */
	public SampleHandler() {
		E = new Editor();
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			E.Open();
			
			FileOutputStream eraser = new FileOutputStream(E.xmlDir);
			byte b[] = new byte[0];
			eraser.write(b);
			eraser.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
