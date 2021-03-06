package editorView;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import element.AButton;
import element.ALabel;
import element.ATextBox;
import element.AndroidElement;

/**
 * Dialog box that allows the user to edit various
 * properties of a particular android element
 * 
 * @author Anthony
 *
 */
public class EditPropertiesDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;

	AndroidElement element;
	
    private JButton btnCancel;
    private JButton btnOK;
    private JLabel lblName;
    private JLabel lblX;
    private JLabel lblY;
    private JPanel mainPanel;
    private JTextField txtName;
    private JTextField txtX;
    private JTextField txtY;
	
	public EditPropertiesDialog(Editor e, boolean modal, AndroidElement ae) {
		super(e, modal);
		element = ae;
		initComponents();
		pack();
		setLocationRelativeTo(e);
		setVisible(true);
	}
	
	public EditPropertiesDialog(Editor e, boolean modal, AButton ae) {
		super(e, modal);
		element = ae;
		initComponents();
		pack();
		setLocationRelativeTo(e);
		setVisible(true);
	}
	
	public EditPropertiesDialog(Editor e, boolean modal, ALabel ae) {
		super(e, modal);
		element = ae;
		initComponents();
		pack();
		setLocationRelativeTo(e);
		setVisible(true);
	}
	
	public EditPropertiesDialog(Editor e, boolean modal, ATextBox ae) {
		super(e, modal);
		element = ae;
		initComponents();
		pack();
		setLocationRelativeTo(e);
		setVisible(true);
	}

	private void initComponents() {
		mainPanel = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        txtX = new javax.swing.JTextField();
        txtY = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        lblX = new javax.swing.JLabel();
        lblY = new javax.swing.JLabel();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        mainPanel.setName("mainPanel");
        txtName.setName("txtName");
        txtName.setText(element.getName());
        txtName.setPreferredSize(new Dimension(150, 20));
        txtX.setName("txtX");
        txtX.setText(String.valueOf(element.getX()));
        txtX.setPreferredSize(new Dimension(150, 20));
        txtY.setName("txtY");
        txtY.setText(String.valueOf(element.getY()));
        txtY.setPreferredSize(new Dimension(150, 20));
        lblName.setName("lblName");
        lblName.setText("Name: ");
        lblX.setName("lblX");     
        lblX.setText("X: ");
        lblY.setName("lblY");
        lblY.setText("Y: ");
        btnOK.setName("btnOK");
        btnOK.setText("OK");
        btnOK.addMouseListener(new MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                element.setName( txtName.getText() );
                if ( !txtX.getText().equals("") )
                	element.setX( Integer.parseInt( txtX.getText() ) );
                if ( !txtY.getText().equals("") )
                	element.setY( Integer.parseInt( txtY.getText() ) );
                setVisible(false);
            }
        });
        btnCancel.setName("btnCancel");
        btnCancel.setText("Cancel");
        btnCancel.addMouseListener(new MouseAdapter() {
        	public void mousePressed(java.awt.event.MouseEvent evt) {
                setVisible(false);
            }
        });

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(btnOK)
                        .addGap(76, 76, 76)
                        .addComponent(btnCancel))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lblName)
                            .addComponent(lblX)
                            .addComponent(lblY))
                        .addGap(46, 46, 46)
                        .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblX))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblY))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOK)
                    .addComponent(btnCancel))
                .addGap(95, 95, 95))
        );

        this.add(mainPanel);
	}
}
