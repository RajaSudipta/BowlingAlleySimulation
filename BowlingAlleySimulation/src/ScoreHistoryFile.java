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

//	private static String SCOREHISTORY_DAT = "src\\SCOREHISTORY.DAT";
	private static String SCOREHISTORY_DAT = "src/SCOREHISTORY.DAT";

//	public static void addScore(String nick, String date, String score)
//		throws IOException, FileNotFoundException {
//
//		String data = nick + "\t" + date + "\t" + score + "\n";
//
//		RandomAccessFile out = new RandomAccessFile(SCOREHISTORY_DAT, "rw");
//		out.skipBytes((int) out.length());
//		out.writeBytes(data);
//		out.close();
//	}
	
	public static void addScore(String nick, String date, String score) {
		CreateDB.insertSingleRowIntoScoreTable(nick, date, score);
	}

//	public static Vector getScores(String nick)
//		throws IOException, FileNotFoundException {
//		Vector scores = new Vector();
//
//		BufferedReader in =
//			new BufferedReader(new FileReader(SCOREHISTORY_DAT));
//		String data;
//		while ((data = in.readLine()) != null) {
//			// File format is nick\tfname\te-mail
//			String[] scoredata = data.split("\t");
//			//"Nick: scoredata[0] Date: scoredata[1] Score: scoredata[2]
//			if (nick.equals(scoredata[0])) {
//				scores.add(new Score(scoredata[0], scoredata[1], scoredata[2]));
//			}
//		}
//		return scores;
//	}
	
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
