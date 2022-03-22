import java.util.Vector;

public class drive {

	public static void main(String[] args) {

//		int numLanes = 3;
//		int maxPatronsPerParty=5;
		
		/* DB Oerations, run once at the beginnning if database not there */
		
		CreateDB.connectDB();
		CreateDB.createBowlerTable();
		CreateDB.createScoreTable();
		CreateDB.createLanePatronTable();
		CreateDB.insertIntoBowlerTable();
//		CreateDB.insertIntoScoreTable();
		CreateDB.insertIntoLanePatronTable(4, 6);
		
		/************************Debug Codes********************************/
//		CreateDB.updateLanePatronTable(4, 6);
//		
//		CreateDB.insertSingleRowIntoBowlerTable("xyz", "xyz", "xyz");
//		CreateDB.insertSingleRowIntoScoreTable("xyz", "xyz", "xyz");
//		CreateDB.printLanePatronTable();
//		CreateDB.printBowlerTable();
//		CreateDB.printScoreTable();
//		
//		System.out.println("Mike avg: " + ScoreHistoryFile.averageScore("Mike")); 
//		System.out.println("All-Over Highest Score: " + ScoreHistoryFile.getUniversalHighestScore()); 
//		System.out.println("All-Over Lowest Score: " + ScoreHistoryFile.getUniversalLowestScore()); 
//		System.out.println("Mike Highest Score: " + ScoreHistoryFile.getHighestScoreByNickName("Mike")); 
//		System.out.println("Mike Lowest Score: " + ScoreHistoryFile.getLowestScoreByNickName("Mike")); 
//		
//		System.out.println("Mikex Lowest Score: " + ScoreHistoryFile.getLowestScoreByNickName("Mikex")); 
		/*******************************************************************/
		
//		CreateDB.deleteZeroScores();
		Vector v = CreateDB.getLanePatron();
		
		int numLanes = Integer.parseInt((String) v.get(0));
		int maxPatronsPerParty = Integer.parseInt((String) v.get(1));
		
		ControlDesk controlDesk = new ControlDesk(numLanes);
		
		ControlDeskSubscriber cds=new ControlDeskSubscriber();

		ControlDeskView cdv = new ControlDeskView( controlDesk, maxPatronsPerParty);
		cds.subscribe(controlDesk, cdv);
	}
}
