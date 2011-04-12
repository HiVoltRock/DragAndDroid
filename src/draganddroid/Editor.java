package draganddroid;

import global.Constants;

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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * launched when plugin menu button or Launch Editor 
 * is clicked. Launches frame where user can edit
 * desired android app elements 
 * 
 * @author afavia.student
 *
 */
public class Editor implements WindowListener {
	
	Vector<AndroidElement> elements;
	JFrame f;
	boolean firstOpen;
	String rootDir; //root directory of the desired Android app
	String xmlDir;  //directory of main.xml in the Android app
	
	public Editor() {
		elements = new Vector<AndroidElement>();
		firstOpen = true;
	}
	
	/**
	 * opens Editor canvas so user can drag and drop 
	 * android elements to "phone" 
	 */
	public void Open()
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
		}
		
		f = new JFrame("Drag And Droid Editor");
		OurCanvas c = new OurCanvas(this);
		c.setBackground(Color.WHITE);
		c.setSize(Constants.EditorWidth, Constants.EditorHeight);
		MenuBar menu = new MenuBar();
		Menu file = new Menu("File");
		Menu tools = new Menu("Tools");
		
		MenuItem New = new MenuItem("New");
		MenuItem miGenerate = new MenuItem("Generate");
		MenuItem Button = new MenuItem("Button");
		MenuItem Label = new MenuItem("Label");
		MenuItem TextBox = new MenuItem("TextBox");
		
		New.addActionListener(new MenuResponder(this, f, c));
		miGenerate.addActionListener(new MenuResponder(this, f, c));
		Button.addActionListener(new MenuResponder(this, f, c));
		Label.addActionListener(new MenuResponder(this, f, c));
		TextBox.addActionListener(new MenuResponder(this, f, c));	
		
		New.setShortcut(new MenuShortcut(KeyEvent.VK_N));
		miGenerate.setShortcut(new MenuShortcut(KeyEvent.VK_G));
		
		file.add(New);
		file.add(miGenerate);
		tools.add(Button);
		tools.add(Label);
		tools.add(TextBox);		
		
		//Das Tool Box
		JFrame toolbox = new JFrame("ToolBox");
		JButton AddButton = new JButton("AddButton");
		JButton AddTextbox = new JButton("AddTextbox");
		JButton AddLabel = new JButton("AddLabel");
		JButton Generate = new JButton("Generate");	
		
		//Das Tool Box Container
		Container contentpane = toolbox.getContentPane();
		contentpane.setLayout(new GridLayout(4,2));
		contentpane.add(AddButton);
		contentpane.add(AddTextbox);
		contentpane.add(AddLabel);
		contentpane.add(Generate);
		
		//Das Button Listeners
		ButtonListener bclicked = new ButtonListener(this);
		AddButton.addActionListener(bclicked);
		AddTextbox.addActionListener(bclicked);
		AddLabel.addActionListener(bclicked);
		Generate.addActionListener(bclicked);
		
		//Das Mouse Listeners
		c.addMouseListener(bclicked);
	    c.addMouseMotionListener(bclicked);
		
		//Das Tool Box Properties
		toolbox.setSize(200,200);
		toolbox.setLocation(500, 0);
		toolbox.setVisible(true);
			
		menu.add(file);
		menu.add(tools);
		f.setMenuBar(menu);
		
		f.add(c);
			
		f.pack();
		f.setVisible(true);		
		
		//test t = new test();
		//t.testXMLParser(); 
	}

	/**
	 * when editor gets launched a second time, check for any new additions to 
	 * xml file, as in any new elements.
	 */
	private void CheckForNewElements() {
		System.out.println("Checking for new elements");		
		SaxXMLParserForEditor parser = new SaxXMLParserForEditor(Constants.filename, elements);	
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
			
			//checking to make sure we're in the right directory!
			File xmlFile = new File(rootDir + "/res/layout/main.xml");
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
			JOptionPane.showMessageDialog(f, "We can't seem to find your Android files. Are you in the right directory?");
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
}
