public class CookingStation extends CList<CookingItem> {

	private String station; // station name


	public CookingStation(){
		CList cookingStation = new CList ();
	}

	// put a new dish at the end of the station
	public void addItem(CookingItem it) {

	}


	public void tick() {

	}	 // simulate one minute time passing for this station
	public CookingItem tend(int removeThreshold, int penaltyThreshold);
// tend the current item, returning it if you decide to remove it
// (based on remaining time or penalty), or return null if nothing happened.
}
