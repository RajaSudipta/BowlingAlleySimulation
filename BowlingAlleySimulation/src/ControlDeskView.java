/* ControlDeskView.java
 *
 *  Version:
 *			$Id$
 * 
 *  Revisions:
 * 		$Log$
 * 
 */

/**
 * Class for representation of the control desk
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class ControlDeskView implements ActionListener, ControlDeskObserver {

	private JButton addParty, finished, assign, configureBowlerPatron, showScoreStats;
	private JFrame win;
	private JList partyList;
	
	/** The maximum  number of members in a party */
	private int maxMembers;
	
	private ControlDesk controlDesk;

	/**
	 * Displays a GUI representation of the ControlDesk
	 *
	 */
	
	public ControlDeskView(ControlDesk controlDesk, int maxMembers) {

		this.controlDesk = controlDesk;
		this.maxMembers = maxMembers;
		int numLanes = controlDesk.getNumLanes();

		win = new JFrame("Control Desk");
		win.getContentPane().setLayout(new BorderLayout());
		((JPanel) win.getContentPane()).setOpaque(false);

		JPanel colPanel = new JPanel();
		colPanel.setLayout(new BorderLayout());

		// Controls Panel
		JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(new GridLayout(5, 1));
		controlsPanel.setBorder(new TitledBorder("Controls"));

		addParty = new JButton("Add Party");
		addParty.addActionListener(this);
		controlsPanel.add(UIComponents.getPanel(addParty));
		
		configureBowlerPatron= new JButton("Configure Bowler & Patron");
		configureBowlerPatron.addActionListener(this);
		controlsPanel.add(UIComponents.getPanel(configureBowlerPatron));
		
		assign = new JButton("Assign Lanes");
		assign.addActionListener(this);
		//controlsPanel.add(getPanel(assign));
		
		showScoreStats = new JButton("Show Score Statistics");
		showScoreStats.addActionListener(this);
		controlsPanel.add(UIComponents.getPanel(showScoreStats));

		
		finished = new JButton("Finished");
		finished.addActionListener(this);
		controlsPanel.add(UIComponents.getPanel(finished));
		
		//---- Show emoticon in the control panel screen
		/* Path For Windows */
		Icon gifIcon = new ImageIcon(this.getClass().getResource("images\\321GO.gif"));
		/* Path For Linux */
//		Icon gifIcon = new ImageIcon(this.getClass().getResource("images/321GO.gif"));
		JPanel emojiPanel = new JPanel();
		JLabel gifLabel = new JLabel(gifIcon);
		emojiPanel.setLayout(new FlowLayout());
		final JButton gifTest;
		emojiPanel.add(gifLabel);
		final JButton gifTest2;
		controlsPanel.add(emojiPanel);
		
		
		// Lane Status Panel
		JPanel laneStatusPanel = new JPanel();
		laneStatusPanel.setLayout(new GridLayout(numLanes, 1));
		laneStatusPanel.setBorder(new TitledBorder("Lane Status"));

		HashSet lanes=controlDesk.getLanes();
		Iterator it = lanes.iterator();
		int laneCount=0;
		while (it.hasNext()) {
			Lane curLane = (Lane) it.next();
			LaneStatusView laneStat = new LaneStatusView(curLane,(laneCount+1));
			curLane.subscribe(laneStat);
			((Pinsetter)curLane.getPinsetter()).subscribe(laneStat);
			JPanel lanePanel = laneStat.showLane();
			lanePanel.setBorder(new TitledBorder("Lane" + ++laneCount ));
			laneStatusPanel.add(lanePanel);
		}

		// Party Queue Panel
		JPanel partyPanel = new JPanel();
		partyPanel.setLayout(new FlowLayout());
		partyPanel.setBorder(new TitledBorder("Party Queue"));

		Vector empty = new Vector();
		empty.add("(Empty)");

		partyList = new JList(empty);
		partyList.setFixedCellWidth(120);
		partyList.setVisibleRowCount(10);
		JScrollPane partyPane = new JScrollPane(partyList);
		partyPane.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		partyPanel.add(partyPane);
		//		partyPanel.add(partyList);

		// Clean up main panel
		colPanel.add(controlsPanel, "East");
		colPanel.add(laneStatusPanel, "Center");
		colPanel.add(partyPanel, "West");

		win.getContentPane().add("Center", colPanel);

		win.pack();

		/* Close program when this window closes */
		win.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// Center Window on Screen
		UIComponents.SetWindow(win);

	}

	/**
	 * Handler for actionEvents
	 *
	 * @param e	the ActionEvent that triggered the handler
	 *
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(addParty)) {
			AddPartyView addPartyWin = new AddPartyView(this, maxMembers);
		}
		if (e.getSource().equals(assign)) {
			controlDesk.assignLane();
		}
		if (e.getSource().equals(finished)) {
			win.hide();
			System.exit(0);
		}
		if (e.getSource().equals(configureBowlerPatron)) {
//			System.out.println("Hello bowler patron change!!");
			LanePatronJFrame lpf = new LanePatronJFrame();
			lpf.start();
		}
		if (e.getSource().equals(showScoreStats)) {
//			System.out.println("Hello score stats!!");
			ShowScoreStats stat = new ShowScoreStats();
		}
	}

	/**
	 * Receive a new party from andPartyView.
	 *
	 * @param addPartyView	the AddPartyView that is providing a new party
	 *
	 */

	public void updateAddParty(AddPartyView addPartyView) {
		controlDesk.addPartyQueue(addPartyView.getParty());
	}

	/**
	 * Receive a broadcast from a ControlDesk
	 *
	 * @param ce	the ControlDeskEvent that triggered the handler
	 *
	 */

	public void receiveControlDeskEvent(ControlDeskEvent ce) {
		partyList.setListData(((Vector) ce.getPartyQueue()));
	}
}
