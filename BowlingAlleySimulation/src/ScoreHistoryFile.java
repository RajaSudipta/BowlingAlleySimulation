/**
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ScoreHistoryFile {
	/* Path for Windows */
	private static String SCOREHISTORY_DAT = "src\\SCOREHISTORY.DAT";
	/* Path For Linux */
//	private static String SCOREHISTORY_DAT = "src/SCOREHISTORY.DAT";
	
	public static void addScore(String nick, String date, String score) {
		CreateDB.insertSingleRowIntoScoreTable(nick, date, score);
	}
	
	public static Vector getAllScores() {
		Vector scores = new Vector();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM score;" );
			while ( rs.next() ) {
				String  nickname = rs.getString("nickname");
				String  datetime = rs.getString("datetime");
				String score = rs.getString("score");
				scores.add(new Score(nickname, datetime, score));
			}
			rs.close();
			stmt.close();
			c.close();
			return scores;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return scores;
	}
	
	public static Vector getScores(String nick) {
		Vector scores = new Vector();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM score;" );
			while ( rs.next() ) {
				String  nickname = rs.getString("nickname");
				if(nick.equals(nickname)) {
					String  datetime = rs.getString("datetime");
					String score = rs.getString("score");
					scores.add(new Score(nick, datetime, score));
				}
			}
			rs.close();
			stmt.close();
			c.close();
			return scores;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return scores;
	}
	
	public static double getUniversalHighestScore() {
		Vector scoresList = null;
		try {
			scoresList = getAllScores();
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
		
		if(scoresList != null) {
			double highestScore = Double.MIN_VALUE;
			Iterator it = scoresList.iterator();
			while (it.hasNext()) {
				Score score = (Score) it.next();
				double currScore = (double)Integer.parseInt(score.getScore());
				highestScore = Math.max(highestScore, currScore);
			}
			return highestScore;
		} else {
			return Double.MIN_VALUE;
		}
		
	}
	
	public static double getUniversalLowestScore() {
		Vector scoresList = null;
		try {
			scoresList = getAllScores();
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
		
		if(scoresList != null) {
			double lowestScore = Double.MAX_VALUE;
			Iterator it = scoresList.iterator();
			while (it.hasNext()) {
				Score score = (Score) it.next();
				double currScore = (double)Integer.parseInt(score.getScore());
				lowestScore = Math.min(lowestScore, currScore);
			}
			return lowestScore;
		} else {
			return Double.MAX_VALUE;
		}
		
	}
	
	public static double getHighestScoreByNickName(String nick) {
		Vector scoresList = null;
		try {
			scoresList = getScores(nick);
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
		
		if(scoresList != null) {
			double highestScore = Double.MIN_VALUE;
			Iterator it = scoresList.iterator();
			while (it.hasNext()) {
				Score score = (Score) it.next();
				if(score.getNickName().equals(nick)) {
					double currScore = (double)Integer.parseInt(score.getScore());
					highestScore = Math.max(highestScore, currScore);
				}
			}
			return highestScore;
		} else {
			return Double.MIN_VALUE;
		}
		
	}
	
	public static double getLowestScoreByNickName(String nick) {
		Vector scoresList = null;
		try {
			scoresList = getScores(nick);
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
		
		if(scoresList != null) {
			double lowestScore = Double.MAX_VALUE;
			Iterator it = scoresList.iterator();
			while (it.hasNext()) {
				Score score = (Score) it.next();
				if(score.getNickName().equals(nick)) {
					double currScore = (double)Integer.parseInt(score.getScore());
					lowestScore = Math.min(lowestScore, currScore);
				}
			}
			return lowestScore;
		} else {
			return Double.MAX_VALUE;
		}
		
	}
	
	public static double averageScore(String nick) {
		Vector scoresList = null;
		try {
			scoresList = getScores(nick);
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
		
		if(scoresList != null) {
			Iterator it = scoresList.iterator();

			double sum = 0;
			double count = 0;
			while (it.hasNext()) {
				count = count + (double)(1);
				Score score = (Score) it.next();
				double actualScore = (double)Integer.parseInt(score.getScore());
				sum = sum + actualScore;
			}

			double avg = sum/count;
			return avg;
		} else {
			return Double.MIN_VALUE;
		}
	}

}
