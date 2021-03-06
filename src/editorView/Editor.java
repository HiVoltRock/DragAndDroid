package editorView;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import xml.SaxXMLParser;
import element.AndroidElement;
import global.Constants;

/**
 * launched when plugin menu button or Launch Editor 
 * is clicked. Launches frame where user can edit
 * desired android app elements 
 * 
 * @author afavia.student
 *
 */
public class Editor extends JFrame implements WindowListener {
	
	private static final long serialVersionUID = 1L;
	
	Vector<AndroidElement> elements;
	boolean firstOpen;
	String rootDir; //root directory of the desired Android app
	public String xmlDir;  //directory of main.xml in the Android app
	JFrame toolbox; 
	
	public Editor() {
		elements = new Vector<AndroidElement>();
		firstOpen = true;
	}
	
	/**
	 * opens Editor canvas so user can drag and drop 
	 * android elements to "phone" 
	 * @throws IOException 
	 */
	public void Open() throws IOException
	{
		// get any new elements from xml that were added manually
		if (!firstOpen) 
		{
			CheckForNewElements();
		}
		else //first open
		{
			// put check in method so that it can be easily commented out 
			// during testing
			CheckForAndroidApp();	
			firstOpen = false;
			if ( new File(Constants.filename).exists() ) {
				FileOutputStream eraser = new FileOutputStream(Constants.filename);
				byte b[] = new byte[0];
				eraser.write(b);
				eraser.close();
			}
		}
		
		this.setTitle("Drag And Droid Editor");
		OurCanvas c = new OurCanvas(this);
		c.setBackground(Color.BLACK);
		c.setSize(Constants.EditorWidth, Constants.EditorHeight);
		MenuBar menu = new MenuBar();
		Menu file = new Menu("File");
		Menu tools = new Menu("Tools");
		
		MenuItem New = new MenuItem("New");
		MenuItem miGenerate = new MenuItem("Generate");

		MenuItem ElementProperties = new MenuItem("ElementProperties");
		MenuItem AddEvent = new MenuItem("Add Event");
		
		New.addActionListener(new MenuResponder(this, this, c));
		miGenerate.addActionListener(new MenuResponder(this, this, c));

		ElementProperties.addActionListener(new MenuResponder(this, this, c));
		AddEvent.addActionListener(new MenuResponder(this, this, c));
		
		New.setShortcut(new MenuShortcut(KeyEvent.VK_N));
		miGenerate.setShortcut(new MenuShortcut(KeyEvent.VK_G));
		ElementProperties.setShortcut(new MenuShortcut(KeyEvent.VK_P));
		AddEvent.setShortcut(new MenuShortcut(KeyEvent.VK_A));
		file.add(New);
		file.add(miGenerate);

		tools.add(ElementProperties);
		tools.add(AddEvent);
		//Das Tool Box
		toolbox = new JFrame("ToolBox");
		JButton AddButton = new JButton("AddButton");
		JButton AddTextbox = new JButton("AddTextbox");
		JButton AddLabel = new JButton("AddLabel");
		JButton AddSeekBar = new JButton("AddSeekBar");
		JButton AddUserField = new JButton("AddUserField");
		JButton Generate = new JButton("Generate");	
		
		//Das Tool Box Container
		Container contentpane = toolbox.getContentPane();
		contentpane.setLayout(new GridLayout(6,2));
		contentpane.add(AddButton);
		contentpane.add(AddTextbox);
		contentpane.add(AddLabel);
		contentpane.add(AddSeekBar);
		contentpane.add(AddUserField);
		contentpane.add(Generate);
		
		//Das Button Listeners
		ButtonListener bclicked = new ButtonListener(this);
		AddButton.addActionListener(bclicked);
		AddTextbox.addActionListener(bclicked);
		AddLabel.addActionListener(bclicked);
		Generate.addActionListener(bclicked);
		AddSeekBar.addActionListener(bclicked);
		AddUserField.addActionListener(bclicked);
		
		//Das Mouse Listeners
		c.addMouseListener(bclicked);
	    c.addMouseMotionListener(bclicked);
		
		//Das Tool Box Properties
		toolbox.setSize(200,300);
		toolbox.setLocation(500, 0);
		toolbox.setVisible(true);
			
		menu.add(file);
		menu.add(tools);

		this.setMenuBar(menu);
		this.add(c);
			
		this.pack();
		this.setVisible(true);	
	}

	/**
	 * when editor gets launched a second time, check for any new additions to 
	 * xml file, as in any new elements.
	 */
	private void CheckForNewElements() {	
		SaxXMLParser parser = new SaxXMLParser(Constants.filename, elements);	
		parser.parseDocument();
	}
	
	private void CheckForAndroidApp() {
		do
		{
			//create file chooser to get root directory of Android app
			final JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("Please Select root directory of Android project");
			fc.setCurrentDirectory(new java.io.File("."));
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //we only want the user to select a directory
			fc.setAcceptAllFileFilterUsed(false); //we don't want to accept any file
			fc.showOpenDialog(null);
			
			this.rootDir = fc.getSelectedFile().toString();
			System.out.println("Directory: " + rootDir);
			firstOpen = false;
		}
		while(!xmlExists(rootDir)); //making sure the user selected a valid directory and we can find any necessary files
									//and re-prompting if they should select again
	}
	
	private boolean xmlExists(String root)
	{
		//checking to make sure we're in the right directory!
		File xmlFile = new File(rootDir + "/res/layout/main.xml");
		
		if(xmlFile.exists())
		{
			this.xmlDir = rootDir + "/res/layout/main.xml";
			firstOpen = false;
			return true;
			
		}
		else
		{
			JOptionPane.showMessageDialog(this, "We can't seem to find your Android files. Are you in the right directory?");
		}
		
		return false;
	}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}

	/**
	 * returns a list of Strings containing the names
	 * of all of the AndroidElements in elements
	 * 
	 */
	public String[] getElementNames() {
		String[] names = new String[elements.size()];
		for ( int i = 0; i < elements.size(); i++ ) {
			names[i] = elements.elementAt(i).getName();
		}
		return names;
	}

	/**
	 * returns the Android element whose name 
	 * is s
	 * 
	 */
	public AndroidElement FindElement(String s) {
		for ( int i = 0; i < elements.size(); i++ ) {
			if ( elements.elementAt(i).getName().equals(s) )
				return elements.elementAt(i);
		}
		return null;
	}
	
	/**
	 * returns a list of Strings containing the names
	 * of all of the elements that can have events
	 * 
	 */
	public Object[] getEventElementNames() {
		Vector<Object> names = new Vector<Object>();
		for ( int i = 0; i < elements.size(); i++ ) {
			if ( elements.elementAt(i).getType().equals("AButton") 
					|| elements.elementAt(i).getType().equals("ASeekBar") ) {
				names.add(elements.elementAt(i).getName());
			}
		}
		return names.toArray();
	}
}
