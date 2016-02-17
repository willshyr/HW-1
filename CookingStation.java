public class CookingStation extends CList<CookingItem> implements CookingStationInterface {

	private String stationName; // station name
	private CList<CookingItem> station;
	// private boolean itemIsRemoved = false;
	//private int totalStationPenalty = 0;


	public CookingStation(String name) {
		this.station = new CList<CookingItem>(); //create an empty station(CList)
		this.stationName = name;
	}

	// put a new dish at the end of the station
	public void addItem(CookingItem it) {
		this.station.insert(it); //need to change method name
	}

	public CookingItem getStationItem() {
		return this.station.getValue();
	}

	// simulate one minute time passing for this station
	public void tick() {
		int temp = this.station.currPos();
		// int itemPos = station.currPos(); //store the "index" of the current item
		//CookingItem temp = station.getCurr();
		station.moveToStart(); //always tick from the start (therefore cann't do cnext() later)
		for (int i = 0; i < this.station.length(); i++) {
			this.station.getValue().tick();
			if (!station.isAtEnd()) {
				station.next();
			}
		}
		station.moveToPos(temp);
	}

// tend the current item, returning it if you decide to remove it
// (based on remaining time or penalty), or return null if nothing happened.
	public CookingItem tend(int removeThreshold, int penaltyThreshold) {
		this.station.cnext();
		System.out.println(this.getStationItem().timeRemaining());
		// System.out.println(this.station.getValue());
		if (this.getStationItem().timeRemaining() <= removeThreshold) {
			CookingItem temp = station.getValue(); //store the item that you'll remove
			this.station.remove(); //remove the current item from this station
			return temp;
			//current item is the next item in the station
			//can we move to the next item (with the least remainingTime)
		} else {
			return null; //nothing is removed.
		}

	}

	// public void setStationName(String n){
	// 	stationName = n;
	// }

	// public String getStationName() {
	// 	return this.stationName;
	// }

	public String toString() {
		return this.stationName + " " + this.station.toString();
	}

	public boolean isEmpty() {
		return this.station.isEmpty();
	}
/*
	public int getTotalStationPenalty() {
		return this.totalStationPenalty;
	}
*/
}
