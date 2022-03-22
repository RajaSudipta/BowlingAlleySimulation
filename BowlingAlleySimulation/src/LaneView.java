/*
 *  constructs a prototype Lane View
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class LaneView implements LaneObserver, ActionListener {

	private boolean initDone = true;

	JFrame frame;
	Container cpanel;
	Vector bowlers;
	int cur;
	Iterator bowlIt;
	int numFrames=10;
	int numVal=23;
	JPanel[][] balls;
	JLabel[][] ballLabel;
	JPanel[][] scores;
	JLabel[][] scoreLabel;
	JLabel[][] emojiLabel;
	JPanel[][] ballGrid;
	JPanel[] pins;

	JPanel roll_panel;
	JLabel instruction_to_roll;
	JButton roll_button; 
	
	JButton maintenance;
	Lane lane;
	private boolean ExtraGame=false;

	public LaneView(Lane lane, int laneNum) {

		this.lane = lane;
		if(lane.isFirstGame()==false) {
			this.numFrames=3;
			this.numVal=9;
		}
		initDone = true;
		frame = new JFrame("Lane " + laneNum + ":");
		cpanel = frame.getContentPane();
		cpanel.setLayout(new BorderLayout());

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame.hide();
			}
		});

		cpanel.add(new JPanel());

	}

	public void show() {
		frame.show();
	}

	public void hide() {
		frame.hide();
	}

	private JPanel makeFrame(Party party) {

		initDone = false;
		bowlers = party.getMembers();
		int numBowlers = bowlers.size();

		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(0, 1));

		balls = new JPanel[numBowlers][this.numVal];
		ballLabel = new JLabel[numBowlers][23];
		scores = new JPanel[numBowlers][this.numFrames];
		scoreLabel = new JLabel[numBowlers][this.numFrames];
		emojiLabel = new JLabel[numBowlers][this.numFrames];
		ballGrid = new JPanel[numBowlers][this.numFrames];
		pins = new JPanel[numBowlers];

		for (int i = 0; i != numBowlers; i++) {
			for (int j = 0; j != numVal; j++) {
				ballLabel[i][j] = new JLabel(" ");
				balls[i][j] = new JPanel();
				balls[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				balls[i][j].add(ballLabel[i][j]);
			}
		}

		for (int i = 0; i != numBowlers; i++) {
			for (int j = 0; j != numFrames-1; j++) {
				ballGrid[i][j] = new JPanel();
				ballGrid[i][j].setLayout(new GridLayout(0, 5));
				ballGrid[i][j].add(new JLabel("  "), BorderLayout.EAST);
				ballGrid[i][j].add(balls[i][2 * j], BorderLayout.EAST);
				ballGrid[i][j].add(balls[i][2 * j + 1], BorderLayout.EAST);
			}
			int j = numFrames-1;
			ballGrid[i][j] = new JPanel();
			ballGrid[i][j].setLayout(new GridLayout(0, 5));
			ballGrid[i][j].add(balls[i][2 * j]);
			ballGrid[i][j].add(balls[i][2 * j + 1]);
			ballGrid[i][j].add(balls[i][2 * j + 2]);
		}

		for (int i = 0; i != numBowlers; i++) {
			pins[i] = new JPanel();
			pins[i].setBorder(BorderFactory.createTitledBorder(party.getPartyMemberNickname(((Bowler) bowlers.get(i)))));
			pins[i].setLayout(new GridLayout(0, this.numFrames));
			
			
			for (int k = 0; k != this.numFrames; k++) {
				scores[i][k] = new JPanel();
				scoreLabel[i][k] = new JLabel("  ", SwingConstants.CENTER);
				emojiLabel[i][k] = new JLabel("      ", SwingConstants.CENTER);
				scores[i][k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				scores[i][k].setLayout(new GridLayout(0, 1));
				scores[i][k].add(ballGrid[i][k], BorderLayout.EAST);
				scores[i][k].add(scoreLabel[i][k], BorderLayout.SOUTH);
				scores[i][k].add(emojiLabel[i][k], BorderLayout.NORTH);
				pins[i].add(scores[i][k], BorderLayout.EAST);
			}
			
			/*
			Icon gifIcon = new ImageIcon(this.getClass().getResource("images\\white.gif"));
		    JPanel emojiPanel = new JPanel();
		    JLabel gifLabel = new JLabel(gifIcon);
		    emojiPanel.setLayout(new FlowLayout());
		    emojiPanel.add(gifLabel);
		    pins[i].add(emojiPanel);*/
			
		 
			panel.add(pins[i]);
		}

		initDone = true;
		return panel;
	}

	public void receiveLaneEvent(Party pty, int theIndex, Bowler theBowler, int[][] theCumulScore, HashMap theScore, int theFrameNum, int[] theCurScores, int theBall, boolean mechProblem) {
		if (lane.isPartyAssigned()) {
			int numBowlers = pty.getMembers().size();
			while (!initDone) {
				//System.out.println("chillin' here.");
				try {
					Thread.sleep(1);
				} catch (Exception e) {
				}
			}

			if (theFrameNum == 1
				&& theBall == 0
				&& theIndex == 0) {
				System.out.println("Making the frame.");
				cpanel.removeAll();
				cpanel.add(makeFrame(pty), "Center");

				// Button Panel
				JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new FlowLayout());

				Insets buttonMargin = new Insets(4, 4, 4, 4);

				maintenance = new JButton("Maintenance Call");
				JPanel maintenancePanel = new JPanel();
				maintenancePanel.setLayout(new FlowLayout());
				maintenance.addActionListener(this);
				maintenancePanel.add(maintenance);

				buttonPanel.add(maintenancePanel);

				cpanel.add(buttonPanel, "South");

				roll_panel = new JPanel();
				roll_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
				instruction_to_roll=new JLabel(" (Press Space Bar to Roll.)");
				roll_button = new JButton("Roll the ball");
				roll_panel.add(roll_button);
				roll_panel.add(instruction_to_roll);
				roll_button.addActionListener(this);
				cpanel.add(roll_panel,"North");
				
				frame.pack();

			}

			int[][] lescores = theCumulScore;
			for (int k = 0; k < numBowlers; k++) {
				
				for (int i = 0; i < 21; i++) {
					if (((int[]) ((HashMap) theScore).get(bowlers.get(k)))[i] != -1)
						if (((int[]) ((HashMap) theScore).get(bowlers.get(k)))[i] == 10 && (i % 2 == 0 || i == 19))
							ballLabel[k][i].setText("X");
						else if ( i > 0 && ((int[]) ((HashMap) theScore).get(bowlers.get(k)))[i] + ((int[]) ((HashMap) theScore).get(bowlers.get(k)))[i - 1] == 10 && i % 2 == 1)
							ballLabel[k][i].setText("/");
						else if ( ((int[])((HashMap) theScore).get(bowlers.get(k)))[i] == -2 ){
							
							ballLabel[k][i].setText("F");
						} else
							ballLabel[k][i].setText((new Integer(((int[]) ((HashMap) theScore).get(bowlers.get(k)))[i])).toString());			
				}
			
				for (int i = 0; i <= theFrameNum - 1; i++) {
					if ((lescores[k][i] != 0)) { 
						if (i==0 || (i>0&&lescores[k][i]!=lescores[k][i-1]-1))
							scoreLabel[k][i].setText((new Integer(lescores[k][i])).toString());
					        
					    	if (i==0 || (i > 0 && lescores[k][i]!=lescores[k][i-1]-1)) {  
					    		int diff;
								if (i > 0)
									diff = Math.abs(lescores[k][i] - lescores[k][i-1]);
								else
									diff = lescores[k][i];
								if(diff <= 6) {
									emojiLabel[k][i].setText(" ( ˘︹˘ ) ");
								} else if(diff <= 8) {
									emojiLabel[k][i].setText(" (ㆆ_ㆆ) ");
								} else if(diff <= 10) {
									emojiLabel[k][i].setText(" (ɔ◔‿◔)ɔ ♥ ");
								} else {
									emojiLabel[k][i].setText(" ¯\\_( ͠❛ ͜ʖ ͡❛)_/¯ ");
								}
//		
					    	}
				
				}
			}

		}
	}
	}
		

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(maintenance)) {
			lane.pauseGame();
		}
		else if(e.getSource().equals(roll_button))
		{
		   lane.rollpressed();
		}
	}

}
