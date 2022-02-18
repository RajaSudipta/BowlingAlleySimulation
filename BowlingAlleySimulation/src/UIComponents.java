import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UIComponents {

	public static JPanel getPanel(JButton curbutton)
	{
		JPanel curbuttonPanel = new JPanel();
		curbuttonPanel.setLayout(new FlowLayout());
		curbuttonPanel.add(curbutton);
		return curbuttonPanel;
	}
	public static void SetWindow(JFrame win){
        Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
        win.setLocation(
                ((screenSize.width) / 2) - ((win.getSize().width) / 2),
                ((screenSize.height) / 2) - ((win.getSize().height) / 2));
        win.setVisible(true);
    }
	public static JPanel getFieldPanel(String label, JPanel curPanel, JLabel curLabel, JTextField curField)
	{
		curPanel.setLayout(new FlowLayout());
		curPanel.add(curLabel);
		curPanel.add(curField);
		return curPanel;
	}
}
