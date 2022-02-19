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

public class EndGamePrompt implements ActionListener {

	private JFrame win;
	private JButton yesButton, noButton;

	private int result;
	
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

