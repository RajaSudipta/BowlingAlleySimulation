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
        ((JPanel) win.getContentPane()).setOpaque(false);

        JPanel colPanel = new JPanel();
        colPanel.setLayout(new GridLayout(1, 3));

        // Controls Panel
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(9, 2));
        controlsPanel.setBorder(new TitledBorder("Queries"));

        maxScore = new JButton("Top Score");
        JPanel maxScorePanel = new JPanel();
        maxScorePanel.setLayout(new FlowLayout());
        maxScore.addActionListener(this);
        maxScorePanel.add(maxScore);
        controlsPanel.add(maxScorePanel);
        
        TopScorer = new JButton("Top Scorer");
        JPanel TopScorerPanel = new JPanel();
        TopScorerPanel.setLayout(new FlowLayout());
        TopScorer.addActionListener(this);
        TopScorerPanel.add(TopScorer);
        controlsPanel.add(TopScorerPanel);
        
        minScore = new JButton("Lowest Score");
        JPanel minScorePanel = new JPanel();
        minScorePanel.setLayout(new FlowLayout());
        minScore.addActionListener(this);
        minScorePanel.add(minScore);
        controlsPanel.add(minScorePanel);
        

        LowestScorer = new JButton("Lowest Scorer");
        JPanel LowestScorePanel = new JPanel();
        LowestScorePanel.setLayout(new FlowLayout());
        LowestScorer.addActionListener(this);
        LowestScorePanel.add(LowestScorer);
        controlsPanel.add(LowestScorePanel);
        

        maxPlayerScore = new JButton("Player Highest Score");
        JPanel maxPlayerScorePanel = new JPanel();
        maxPlayerScorePanel.setLayout(new FlowLayout());
        maxPlayerScore.addActionListener(this);
        maxPlayerScorePanel.add(maxPlayerScore);
        controlsPanel.add(maxPlayerScorePanel);

        minPlayerScore = new JButton("Player Lowest Score");
        JPanel minPlayerScorePanel = new JPanel();
        minPlayerScorePanel.setLayout(new FlowLayout());
        minPlayerScore.addActionListener(this);
        minPlayerScorePanel.add(minPlayerScore);
        controlsPanel.add(minPlayerScorePanel);

        lastScores = new JButton("Last Scores by player");
        JPanel lastScoresPanel = new JPanel();
        lastScoresPanel.setLayout(new FlowLayout());
        lastScores.addActionListener(this);
        lastScoresPanel.add(lastScores);
        controlsPanel.add(lastScoresPanel);

        finished = new JButton("Close");
        JPanel finishedPanel = new JPanel();
        finishedPanel.setLayout(new FlowLayout());
        finished.addActionListener(this);
        finishedPanel.add(finished);
        controlsPanel.add(finishedPanel);

        // Scores Database
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout());
        scorePanel.setBorder(new TitledBorder("Results"));


        party = new Vector();

        Vector empty = new Vector();
        empty.add("(Empty)");

        outputList = new JList(empty);
        outputList.setFixedCellWidth(120);
        outputList.setVisibleRowCount(5); 
        outputList.addListSelectionListener(this);
        JScrollPane scorePane = new JScrollPane(outputList);
        scorePanel.add(scorePane);
       
        
       // Bowler Database
        JPanel bowlerPanel = new JPanel();
        bowlerPanel.setLayout(new FlowLayout());
        bowlerPanel.setBorder(new TitledBorder("Player List"));

        try {
            bowlerdb = new Vector(BowlerFile.getBowlers());
        } catch (Exception e) {
            System.err.println("File Error");
            bowlerdb = new Vector();
        }
        allBowlers = new JList(bowlerdb);
        allBowlers.setVisibleRowCount(8);
        allBowlers.setFixedCellWidth(120);
        JScrollPane bowlerPane = new JScrollPane(allBowlers);
        bowlerPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        allBowlers.addListSelectionListener(this);
        bowlerPanel.add(bowlerPane);

        colPanel.add(scorePanel);
        colPanel.add(controlsPanel);
        colPanel.add(bowlerPanel);
        

        win.getContentPane().add("Center", colPanel);

        win.pack();

        // Center Window on Screen
        Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
        win.setLocation(
                ((screenSize.width) / 2) - ((win.getSize().width) / 2),
                ((screenSize.height) / 2) - ((win.getSize().height) / 2));
        win.setVisible(true);

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
            if (selectedNick != null) {
                party.clear();
                Vector<Integer> lastScores = CreateDB.obtainLastScoresByPlayer(selectedNick);
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
        
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource().equals(allBowlers)) {
            selectedNick =
                    ((String) ((JList) e.getSource()).getSelectedValue());
        }
        
    }
    

}
