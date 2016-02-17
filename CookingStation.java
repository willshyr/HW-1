public class CookingStation extends CList<CookingItem> implements CookingStationInterface {

	private String stationName; // station name
	private CList<CookingItem> station;
	private boolean itemIsRemoved = false;
	//private int totalStationPenalty = 0;


	public CookingStation(String name) {
		this.station = new CList<CookingItem>(); //create an empty station(CList)
		this.stationName = name;
	}

	// put a new dish at the end of the station
	public void addItem(CookingItem it) {
		this.station.insert(it); //need to change method name
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
		// station.cnext();
		// while (!station.isAtEnd()) {
		// 	station.getValue().tick(); //.curr
		// 	station.next();
		// }
		// station.getValue().tick(); //.curr (tick the last item in that station)
		// if (itemIsRemoved) {
		// 	station.moveToPos(itemPos);
		// 	//bc curr is already pointing at the item next to the item being removed
		// 	itemIsRemoved = false; //update boolean
		// } else { //move curr to the next item in the station
		// 	itemPos += 1; //move to the next item (for tend() prupose)
		// 	itemPos = itemPos % (station.length() -1);
		// 	station.moveToPos(itemPos); //move to the next item in the station
		// }
	}

// tend the current item, returning it if you decide to remove it
// (based on remaining time or penalty), or return null if nothing happened.
	public CookingItem tend(int removeThreshold, int penaltyThreshold) {
		this.station.cnext();
		if (station.getValue().timeRemaining() <= removeThreshold) {
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

	public String getStationName() {
		return this.stationName;
	}

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
