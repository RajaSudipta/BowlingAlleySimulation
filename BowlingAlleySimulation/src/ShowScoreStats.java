/* ShowScore.java 
 * 
 * class for adhoc queries like get score of player, overall game max score,  
 * min score etc 
 * 
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ShowScoreStats implements ActionListener, ListSelectionListener {

    private JFrame win;

    private JButton finished,lastScores,maxPlayerScore,minPlayerScore;
    private JButton minScore,maxScore,TopScorer,LowestScorer;

    private JList<Vector> allBowlers;    
    private JList<Vector> outputList;
    
    private Vector party;
    private Vector bowlerdb;
    
    private String selectedNick;

    public ShowScoreStats() {

        win = new JFrame("Show Scores");
        win.getContentPane().setLayout(new BorderLayout());
        JPanel newTestPanel = new JPanel();
        ((JPanel) win.getContentPane()).setOpaque(false);

        JPanel colPanel = new JPanel();
        JPanel newTestPanel2 = new JPanel();
        colPanel.setLayout(new GridLayout(1, 3));
        
        JPanel newTestPaneli = new JPanel();

        // Controls Panel
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(9, 2));
        JPanel newTestPanel3 = new JPanel();
        controlsPanel.setBorder(new TitledBorder("Queries"));

        maxScore = new JButton("Top Score");
        JPanel maxScorePanel = new JPanel();
        JPanel newTestPanel4 = new JPanel();
        maxScorePanel.setLayout(new FlowLayout());
        maxScore.addActionListener(this);
        JPanel newTestPanel5 = new JPanel();
        maxScorePanel.add(maxScore);
        controlsPanel.add(maxScorePanel);
        
        TopScorer = new JButton("Top Scorer");
        JPanel newTestPanel6 = new JPanel();
        JPanel TopScorerPanel = new JPanel();
        TopScorerPanel.setLayout(new FlowLayout());
        JPanel newTestPanel7 = new JPanel();
        TopScorer.addActionListener(this);
        TopScorerPanel.add(TopScorer);
        JPanel newTestPanel8 = new JPanel();
        controlsPanel.add(TopScorerPanel);
        
        minScore = new JButton("Lowest Score");
        JPanel newTestPanel9 = new JPanel();
        JPanel minScorePanel = new JPanel();
        minScorePanel.setLayout(new FlowLayout());
        JPanel newTestPanel10 = new JPanel();
        minScore.addActionListener(this);
        minScorePanel.add(minScore);
        JPanel newTestPanel11 = new JPanel();
        controlsPanel.add(minScorePanel);
        

        LowestScorer = new JButton("Lowest Scorer");
        JPanel newTestPanel12 = new JPanel();
        JPanel LowestScorePanel = new JPanel();
        LowestScorePanel.setLayout(new FlowLayout());
        JPanel newTestPanel13 = new JPanel();
        LowestScorer.addActionListener(this);
        LowestScorePanel.add(LowestScorer);
        JPanel newTestPanel114 = new JPanel();
        controlsPanel.add(LowestScorePanel);
        

        maxPlayerScore = new JButton("Player Highest Score");
        JPanel newTestPanel115 = new JPanel();
        JPanel maxPlayerScorePanel = new JPanel();
        maxPlayerScorePanel.setLayout(new FlowLayout());
        JPanel newTestPanel116 = new JPanel();
        maxPlayerScore.addActionListener(this);
        maxPlayerScorePanel.add(maxPlayerScore);
        JPanel newTestPanel117 = new JPanel();
        controlsPanel.add(maxPlayerScorePanel);

        minPlayerScore = new JButton("Player Lowest Score");
        JPanel newTestPanel118 = new JPanel();
        JPanel minPlayerScorePanel = new JPanel();
        minPlayerScorePanel.setLayout(new FlowLayout());
        JPanel newTestPanel119 = new JPanel();
        minPlayerScore.addActionListener(this);
        minPlayerScorePanel.add(minPlayerScore);
        JPanel newTestPanel120 = new JPanel();
        controlsPanel.add(minPlayerScorePanel);

        lastScores = new JButton("Last Scores by player");
        JPanel newTestPanel121 = new JPanel();
        JPanel lastScoresPanel = new JPanel();
        lastScoresPanel.setLayout(new FlowLayout());
        JPanel newTestPanel122 = new JPanel();
        lastScores.addActionListener(this);
        lastScoresPanel.add(lastScores);
        JPanel newTestPanel123 = new JPanel();
        controlsPanel.add(lastScoresPanel);

        finished = new JButton("Close");
        JPanel newTestPanel124 = new JPanel();
        JPanel finishedPanel = new JPanel();
        finishedPanel.setLayout(new FlowLayout());
        JPanel newTestPanel125 = new JPanel();
        finished.addActionListener(this);
        finishedPanel.add(finished);
        JPanel newTestPanel126 = new JPanel();
        controlsPanel.add(finishedPanel);

        // Scores Database
        JPanel scorePanel = new JPanel();
        JPanel newTestPanel127 = new JPanel();
        scorePanel.setLayout(new FlowLayout());
        scorePanel.setBorder(new TitledBorder("Results"));
        JPanel newTestPanel128 = new JPanel();


        party = new Vector();

        Vector empty = new Vector();
        empty.add("(Empty)");
        
        JPanel newTestPanel129 = new JPanel();

        outputList = new JList(empty);
        outputList.setFixedCellWidth(120);
        JPanel newTestPanel130 = new JPanel();
        outputList.setVisibleRowCount(5); 
        outputList.addListSelectionListener(this);
        JPanel newTestPanel131 = new JPanel();
        JScrollPane scorePane = new JScrollPane(outputList);
        scorePanel.add(scorePane);
        JPanel newTestPanel132 = new JPanel();
       
        
       // Bowler Database
        JPanel bowlerPanel = new JPanel();
        bowlerPanel.setLayout(new FlowLayout());
        JPanel newTestPanel133 = new JPanel();
        bowlerPanel.setBorder(new TitledBorder("Player List"));

        try {
            bowlerdb = new Vector(BowlerFile.getBowlers());
        } catch (Exception e) {
            System.err.println("File Error");
            bowlerdb = new Vector();
        }
        JPanel newTestPanel2i = new JPanel();
        allBowlers = new JList(bowlerdb);
        allBowlers.setVisibleRowCount(8);
        JPanel newTestPanel134 = new JPanel();
        allBowlers.setFixedCellWidth(120);
        JScrollPane bowlerPane = new JScrollPane(allBowlers);
        JPanel newTestPanel135 = new JPanel();
        bowlerPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        allBowlers.addListSelectionListener(this);
        JPanel newTestPanel136 = new JPanel();
        bowlerPanel.add(bowlerPane);

        colPanel.add(scorePanel);
        colPanel.add(controlsPanel);
        colPanel.add(bowlerPanel);
        
        JPanel newTestPanel137 = new JPanel();

        win.getContentPane().add("Center", colPanel);

        win.pack();
        
        JPanel newTestPanel3i = new JPanel();

        // Center Window on Screen
        Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
        win.setLocation(
                ((screenSize.width) / 2) - ((win.getSize().width) / 2),
                ((screenSize.height) / 2) - ((win.getSize().height) / 2));
        win.setVisible(true);
        JPanel newTestPanel8i = new JPanel();

    }

//    maxScore, TopScorer, minScore, LowestScorer, maxPlayerScore
//    minPlayerScore, lastScores, finished
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource().equals(maxScore)) {
    		System.out.println("in maxScore");
            party.clear();
            party.add(CreateDB.obtainUniversalMaxScore());
            outputList.setListData(party);
    	}
    	
    	if(e.getSource().equals(minScore)) {
    		System.out.println("in minScore");
            party.clear();
            party.add(CreateDB.obtainUniversalMinScore());
            outputList.setListData(party);
    	}
    	
        if (e.getSource().equals(minPlayerScore)) {
            System.out.println("in minPlayerScore");
            if (selectedNick != null) {
                party.clear();
                party.add(CreateDB.obtainMinScoreByPlayer(selectedNick));
                outputList.setListData(party);
            }
        }

        if (e.getSource().equals(maxPlayerScore)) {
            System.out.println("in maxPlayerScore");
            if (selectedNick != null) {
            	party.clear();
                party.add(CreateDB.obtainMaxScoreByPlayer(selectedNick));
                outputList.setListData(party);
            }
        }
        
        if (e.getSource().equals(TopScorer)) {
            System.out.println("in TopScorer");
            party.clear();
            String topScorer = CreateDB.obtainTopScorer();
            party.add(topScorer);
            outputList.setListData(party);
        }
        
        if (e.getSource().equals(LowestScorer)) {
            System.out.println("in LowestScorer");
            party.clear();
            String lowestScorer = CreateDB.obtainLowestScorer();
            party.add(lowestScorer);
            outputList.setListData(party);
        }
        
        if (e.getSource().equals(lastScores)) {
            System.out.println("in lastScores");
            JPanel newTestPanel7i = new JPanel();
            if (selectedNick != null) {
                party.clear();
                Vector<Integer> lastScores = CreateDB.obtainLastScoresByPlayer(selectedNick);
                JPanel newTestPanel4i = new JPanel();
                Iterator it = lastScores.iterator();
                while(it.hasNext()){  
                	int score = (int) it.next();
                    System.out.println(score);  
                    party.add(score);
                }   
                outputList.setListData(party);
            }
        }
        
        if (e.getSource().equals(finished)) {
            win.hide();
        }
        
        if(true) {
        	JPanel newTestPanel5i = new JPanel();
        }
        
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource().equals(allBowlers)) {
            selectedNick =
                    ((String) ((JList) e.getSource()).getSelectedValue());
        }
        
    }
    

}
