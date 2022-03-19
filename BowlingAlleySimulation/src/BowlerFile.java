/* BowlerFile.java
 *
 *  Version:
 *  		$Id$
 * 
 *  Revisions:
 * 		$Log: BowlerFile.java,v $
 * 		Revision 1.5  2003/02/02 17:36:45  ???
 * 		Updated comments to match javadoc format.
 * 		
 * 		Revision 1.4  2003/02/02 16:29:52  ???
 * 		Added ControlDeskEvent and ControlDeskObserver. Updated Queue to allow access to Vector so that contents could be viewed without destroying. Implemented observer model for most of ControlDesk.
 * 		
 * 
 */

/**
 * Class for interfacing with Bowler database
 *
 */

import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

class BowlerFile {

	/** The location of the bowelr database */
	/* Path for Windows */
	private static String BOWLER_DAT = "src\\BOWLERS.DAT";
	/* Path For Linux */
//	private static String BOWLER_DAT = "src/BOWLERS.DAT";
    
    /**
     * Retrieves a matching Bowler from the bowler database.
     *
     * @param nickName	The NickName of the Bowler
     *
     * @return a Bowler object.
     *
     */

//	public static Bowler registerPatron(String nickName) {
//		Bowler patron = null;
//
//		try {
//			// only one patron / nick.... no dupes, no checks
//
//			patron = BowlerFile.getBowlerInfo(nickName);
//
//		} catch (FileNotFoundException e) {
//			System.err.println("Error..." + e);
//		} catch (IOException e) {
//			System.err.println("Error..." + e);
//		}
//
//		return patron;
//	}
	
	public static Bowler registerPatron(String nickName) {
		Bowler patron = null;

		try {
			// only one patron / nick.... no dupes, no checks

			patron = BowlerFile.getBowlerInfo(nickName);

		} catch (Exception e) {
			System.err.println("Error..." + e);
		} 
		
		return patron;
	}
	

    /**
     * Retrieves bowler information from the database and returns a Bowler objects with populated fields.
     *
     * @param nickName	the nickName of the bolwer to retrieve
     *
     * @return a Bowler object
     * 
     */

//	public static Bowler getBowlerInfo(String nickName)
//		throws IOException, FileNotFoundException {
//
//		BufferedReader in = new BufferedReader(new FileReader(BOWLER_DAT));
//		String data;
//		while ((data = in.readLine()) != null) {
//			// File format is nick\tfname\te-mail
//			String[] bowler = data.split("\t");
//			if (nickName.equals(bowler[0])) {
//				System.out.println(
//					"Nick: "
//						+ bowler[0]
//						+ " Full: "
//						+ bowler[1]
//						+ " email: "
//						+ bowler[2]);
//				return (new Bowler(bowler[0], bowler[1], bowler[2]));
//			}
//		}
//		System.out.println("Nick not found...");
//		return null;
//		
//	}
	
	public static Bowler getBowlerInfo(String nickName) {
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
			
			String nick = null;
			String full = null;
			String email = null;
			
			while ( rs.next() ) {
				nick = rs.getString("nickname");
				if(nickName.equals(nick)) {
					full = rs.getString("fullname");
					email = rs.getString("email");
					System.out.println(
							"Nick: "
								+ nick
								+ " Full: "
								+ full
								+ " email: "
								+ email);
					break;
				}
			}
			rs.close();
			stmt.close();
			c.close();
			if(nick != null && full != null && email != null) {
				return (new Bowler(nick, full, email));
			}
			
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Nick not found...");
		return null;
	}

    /**
     * Stores a Bowler in the database
     *
     * @param nickName	the NickName of the Bowler
     * @param fullName	the FullName of the Bowler
     * @param email	the E-mail Address of the Bowler
     *
     */

//	public static void putBowlerInfo(
//		String nickName,
//		String fullName,
//		String email)
//		throws IOException, FileNotFoundException {
//
//		String data = nickName + "\t" + fullName + "\t" + email + "\n";
//
//		RandomAccessFile out = new RandomAccessFile(BOWLER_DAT, "rw");
//		out.skipBytes((int) out.length());
//		out.writeBytes(data);
//		out.close();
//	}
	
	public static void putBowlerInfo(String nickName, String fullName, String email) {
		CreateDB.insertSingleRowIntoBowlerTable(nickName, fullName, email);
	}

    /**
     * Retrieves a list of nicknames in the bowler database
     *
     * @return a Vector of Strings
     * 
     */

//	public static Vector getBowlers()
//		throws IOException, FileNotFoundException {
//
//		Vector allBowlers = new Vector();
//
//		BufferedReader in = new BufferedReader(new FileReader(BOWLER_DAT));
//		String data;
//		while ((data = in.readLine()) != null) {
//			// File format is nick\tfname\te-mail
//			String[] bowler = data.split("\t");
//			//"Nick: bowler[0] Full: bowler[1] email: bowler[2]
//			allBowlers.add(bowler[0]);
//		}
//		return allBowlers;
//	}
	
	public static Vector getBowlers() {

		Vector allBowlers = new Vector();
		
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
				allBowlers.add(nickname);
			}
			rs.close();
			stmt.close();
			c.close();
			return allBowlers;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return allBowlers;
	}

}