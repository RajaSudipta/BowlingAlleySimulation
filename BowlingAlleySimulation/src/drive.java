public class drive {

	public static void main(String[] args) {

		int numLanes = 3;
		int maxPatronsPerParty=5;

		ControlDesk controlDesk = new ControlDesk(numLanes);
		
		ControlDeskSubscriber cds=new ControlDeskSubscriber();

		ControlDeskView cdv = new ControlDeskView( controlDesk, maxPatronsPerParty);
		cds.subscribe(cdv);

	}
}
