import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class CreateDB {
	public static void connectDB()
	{
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}
	
	public static void createBowlerTable()
	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			System.out.println("Opened database successfully");
			
			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS bowler " +
					"(nickname varchar(20) PRIMARY KEY," +
					" fullname varchar(40) NOT NULL, " + 
					" email varchar(60) NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("bowler Table created successfully");
	}
	
	public static void createScoreTable()
	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			System.out.println("Opened database successfully");
			
			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS score " +
					"(nickname varchar(20) REFERENCES bowlers(nickname)," +
					" datetime varchar(30) NOT NULL, " + 
					" score integer)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("score Table created successfully");
	}
	
	public static void createLanePatronTable()
	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			System.out.println("Opened database successfully");
			
			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS lanepatron " +
					"( id INTEGER PRIMARY KEY CHECK (id = 0)," +
					" numoflanes integer," +
					" maxpatrons integer)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("lanepatron Table created successfully");
	}
	
	public static void insertIntoBowlerTable()
	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			
			stmt = c.createStatement();
			String sql = "INSERT INTO bowler (nickname, fullname, email) VALUES " +
					"('Mike', 'M. J. Lutz', 'ml@nowhere.net'), " +
					"('Jim', 'J. R. Vallino', 'jv@nowhere.net'), " +
					"('Tom', 'T. R. Reichmayr', 'tr@nowhere.net'), " +
					"('Lana', 'L. R. Verschage', 'lv@nowhere.net');";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("bowler Records created successfully");
	}
	
	public static void insertSingleRowIntoBowlerTable(String nickName, String fullName, String email)
	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			
			String sql = "INSERT INTO bowler (nickname, fullname, email) VALUES(?,?,?) ";
			
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, nickName);
			pstmt.setString(2, fullName);
			pstmt.setString(3, email);
			
			pstmt.executeUpdate();
			pstmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("bowler Records created successfully");
		
	}
	
	public static void insertIntoScoreTable()
	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			
			stmt = c.createStatement();
			String sql = "INSERT INTO score (nickname, datetime, score) VALUES " +
					"('Mike', '22:25 3/6/2004', 130), " +
					"('Jim', '22:25 3/6/2004', 113), " +
					"('Tom', '22:25 3/6/2004', 105), " +
					"('Lana', '22:25 3/6/2004', 130);";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("score Records created successfully");
	}
	
	public static void insertSingleRowIntoScoreTable(String nick, String date, String score)
	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			
			String sql = "INSERT INTO score (nickname, datetime, score) VALUES(?,?,?) ";
			
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, nick);
			pstmt.setString(2, date);
			pstmt.setString(3, score);
			
			pstmt.executeUpdate();
			pstmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("bowler Records created successfully");
		
	}
	
	public static void insertIntoLanePatronTable(int lanes, int patrons)
	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			
			stmt = c.createStatement();
			String sql = "INSERT INTO lanepatron (id, numoflanes, maxpatrons) VALUES " +
					"(" + 0 + ", " + lanes + ", " + patrons + ");"; 
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("lanepatron Records created successfully");
	}
	
	public static void updateLanePatronTable(int lanes, int patrons) 
	{
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			
			String sql = "UPDATE lanepatron SET numoflanes = ? , "
	                + "maxpatrons = ? "
	                + "WHERE id = ?";
			
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, lanes);
            pstmt.setInt(2, patrons);
            pstmt.setInt(3, 0);
			
            pstmt.executeUpdate();
            pstmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}
	
	public static void printBowlerTable()
	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM bowler;" );
			while ( rs.next() ) {
				String  nickname = rs.getString("nickname");
				String  fullname = rs.getString("fullname");
				String email = rs.getString("email");
				System.out.println( "nickname: " + nickname );
				System.out.println( "fullname: " + fullname );
				System.out.println( "email: " + email );
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("bowler table printed successfully");  
	}
	
	public static void printScoreTable()
	{
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
				System.out.println( "nickname: " + nickname );
				System.out.println( "date_time: " + datetime );
				System.out.println( "score: " + score );
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("score table printed successfully");  
	}
	
	public static void printLanePatronTable()
	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM lanepatron;" );
			while ( rs.next() ) {
				String  lanes = rs.getString("numoflanes");
				String  patrons = rs.getString("maxpatrons");
				System.out.println( "number of lanes: " + lanes );
				System.out.println( "max patrons per party: " + patrons );
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("lanepatron table printed successfully");  
	}
	
	public static Vector getLanePatron()
	{
		Vector v = new Vector<String>(); 
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM lanepatron;" );
			while ( rs.next() ) {
				String  lanes = rs.getString("numoflanes");
				String  patrons = rs.getString("maxpatrons");
				v.add(lanes);
				v.add(patrons);
			}
			rs.close();
			stmt.close();
			c.close();
			return v;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return v;
	}
	
	public static int obtainUniversalMaxScore() {
		Connection c = null;
		Statement stmt = null;
		int maxScore = Integer.MIN_VALUE;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM score;" );
			while ( rs.next() ) {
				int score = Integer.parseInt(rs.getString("score"));
				maxScore = Math.max(maxScore, score);
			}
			rs.close();
			stmt.close();
			c.close();
			
			return maxScore;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return maxScore;
	}
	
	public static int obtainMaxScoreByPlayer(String nickName) {
		Connection c = null;
		Statement stmt = null;
		int maxScore = Integer.MIN_VALUE;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String query = "SELECT * FROM score WHERE nickname = '" + nickName + "';";
			ResultSet rs = stmt.executeQuery(query);
			while ( rs.next() ) {
				int score = Integer.parseInt(rs.getString("score"));
				maxScore = Math.max(maxScore, score);
			}
			rs.close();
			stmt.close();
			c.close();
			
			return maxScore;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return maxScore;
	}
	
	public static int obtainUniversalMinScore() {
		Connection c = null;
		Statement stmt = null;
		int minScore = Integer.MAX_VALUE;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM score;" );
			while ( rs.next() ) {
				int score = Integer.parseInt(rs.getString("score"));
				minScore = Math.min(minScore, score);
			}
			rs.close();
			stmt.close();
			c.close();
			
			return minScore;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return minScore;
	}
	
	public static int obtainMinScoreByPlayer(String nickName) {
		Connection c = null;
		Statement stmt = null;
		int minScore = Integer.MAX_VALUE;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String query = "SELECT * FROM score WHERE nickname = '" + nickName + "';";
			ResultSet rs = stmt.executeQuery(query);
			while ( rs.next() ) {
				int score = Integer.parseInt(rs.getString("score"));
				minScore = Math.min(minScore, score);
			}
			rs.close();
			stmt.close();
			c.close();
			
			return minScore;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return minScore;
	}
	
	public static String obtainTopScorer() {
		Connection c = null;
		Statement stmt = null;
		int maxScore = Integer.MIN_VALUE;
		String topScorer = null;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM score;");
			while ( rs.next() ) {
				int score = Integer.parseInt(rs.getString("score"));
				if(score > maxScore) {
					maxScore = score;
					topScorer = rs.getString("nickname");
				}
			}
			rs.close();
			stmt.close();
			c.close();
			return topScorer;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return topScorer;
	}
	
	public static String obtainLowestScorer() {
		Connection c = null;
		Statement stmt = null;
		int minScore = Integer.MAX_VALUE;
		String lowestScorer = null;
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM score;");
			while ( rs.next() ) {
				int score = Integer.parseInt(rs.getString("score"));
				if(score < minScore) {
					minScore = score;
					lowestScorer = rs.getString("nickname");
				}
			}
			rs.close();
			stmt.close();
			c.close();
			return lowestScorer;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return lowestScorer;
	}
	
	public static Vector obtainLastScoresByPlayer(String nickName) {
		Connection c = null;
		Statement stmt = null;
		Vector<Integer> scoreVec = new Vector<>();
		try {
			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:myBlog.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:bowlingAlley.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String query = "SELECT * FROM score WHERE nickname = '" + nickName + "';";
			ResultSet rs = stmt.executeQuery(query);
			while ( rs.next() ) {
				int score = Integer.parseInt(rs.getString("score"));
				scoreVec.add(score);
			}
			rs.close();
			stmt.close();
			c.close();
			
			return scoreVec;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return scoreVec;
	}
	
	
}
