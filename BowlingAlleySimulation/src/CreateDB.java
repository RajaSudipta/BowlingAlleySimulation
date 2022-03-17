import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

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
}
