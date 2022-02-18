public class drive {

	public static void main(String[] args) {

		int numLanes = 3;
		int maxPatronsPerParty=5;

//		Alley a = new Alley( numLanes );
//		ControlDesk controlDesk = a.getControlDesk();
		
		ControlDesk controlDesk = new ControlDesk(numLanes);
		
		ControlDeskSubscriber cds=new ControlDeskSubscriber();

		ControlDeskView cdv = new ControlDeskView( controlDesk, maxPatronsPerParty);
//		controlDesk.subscribe( cdv );
		cds.subscribe(cdv);

	}
}
