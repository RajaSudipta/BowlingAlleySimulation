public class drive {

	public static void main(String[] args) {

		int numLanes = 3;
		int maxPatronsPerParty=5;
		
		/* DB Oerations */
		CreateDB.connectDB();
		CreateDB.createBowlerTable();
		CreateDB.createScoreTable();
		CreateDB.insertIntoBowlerTable();
		CreateDB.insertIntoScoreTable();
//		CreateDB.insertSingleRowIntoBowlerTable("xyz", "xyz", "xyz");
//		CreateDB.insertSingleRowIntoScoreTable("xyz", "xyz", "xyz");
		CreateDB.printBowlerTable();
		CreateDB.printScoreTable();
		
		System.out.println("Mike avg: " + ScoreHistoryFile.averageScore("Mike")); 
		System.out.println("All-Over Highest Score: " + ScoreHistoryFile.getUniversalHighestScore()); 
		System.out.println("All-Over Lowest Score: " + ScoreHistoryFile.getUniversalLowestScore()); 
		System.out.println("Mike Highest Score: " + ScoreHistoryFile.getHighestScoreByNickName("Mike")); 
		System.out.println("Mike Lowest Score: " + ScoreHistoryFile.getLowestScoreByNickName("Mike")); 
		
		System.out.println("Mikex Lowest Score: " + ScoreHistoryFile.getLowestScoreByNickName("Mikex")); 
		
		ControlDesk controlDesk = new ControlDesk(numLanes);
		
		ControlDeskSubscriber cds=new ControlDeskSubscriber();

		ControlDeskView cdv = new ControlDeskView( controlDesk, maxPatronsPerParty);
		cds.subscribe(controlDesk, cdv);
	}
}
