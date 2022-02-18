/**
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.util.*;
import java.text.*;

public class EndGamePrompt implements ActionListener {

	private JFrame win;
	private JButton yesButton, noButton;

	private int result;
	
	/*public JPanel getPanel(JButton curbutton)
	{
		JPanel curbuttonPanel = new JPanel();
		curbuttonPanel.setLayout(new FlowLayout());
		curbutton.addActionListener(this);
		curbuttonPanel.add(curbutton);
		return curbuttonPanel;
	}*/
	public EndGamePrompt( String partyName ) {

		result =0;
		
		win = new JFrame("Another Game for " + partyName + "?" );
		win.getContentPane().setLayout(new BorderLayout());
		((JPanel) win.getContentPane()).setOpaque(false);

		JPanel colPanel = new JPanel();
		colPanel.setLayout(new GridLayout( 2, 1 ));

		// Label Panel
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout());
		
		JLabel message = new JLabel( "Party " + partyName 
			+ " has finished bowling.\nWould they like to bowl another game?" );

		labelPanel.add( message );

		UiChanges(colPanel, labelPanel);

	}
	
	public void UiChanges(JPanel colPanel, JPanel labelPanel) {
		// Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2));
		
		Insets buttonMargin = new Insets(4, 4, 4, 4);
		
		/*yesButton = new JButton("Yes");
		JPanel yesButtonPanel = new JPanel();
		yesButtonPanel.setLayout(new FlowLayout());
		yesButton.addActionListener(this);
		yesButtonPanel.add(yesButton);

		noButton = new JButton("No");
		JPanel noButtonPanel = new JPanel();
		noButtonPanel.setLayout(new FlowLayout());
		noButton.addActionListener(this);
		noButtonPanel.add(noButton);

		buttonPanel.add(yesButton);
		buttonPanel.add(noButton);*/
		
		yesButton = new JButton("Yes");
		yesButton.addActionListener(this);
		buttonPanel.add(UIComponents.getPanel(yesButton));
		
		noButton = new JButton("No");
		noButton.addActionListener(this);
		buttonPanel.add(UIComponents.getPanel(noButton));
		
		
		// Clean up main panel
		colPanel.add(labelPanel);
		colPanel.add(buttonPanel);

		win.getContentPane().add("Center", colPanel);

		win.pack();

		// Center Window on Screen
		/*Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
		win.setLocation(
			((screenSize.width) / 2) - ((win.getSize().width) / 2),
			((screenSize.height) / 2) - ((win.getSize().height) / 2));
		win.show();*/
		UIComponents.SetWindow(win);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(yesButton)) {		
			result=1;
		}
		if (e.getSource().equals(noButton)) {		
			result=2;
		}

	}

	public int getResult() {
		while ( result == 0 ) {
			try {
				Thread.sleep(10);
			} catch ( InterruptedException e ) {
				System.err.println( "Interrupted" );
			}
		}
		return result;	
	}
	
	public void distroy() {
		win.hide();
	}
	
}

