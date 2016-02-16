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
		int itemPos = station.currPos(); //store the "index" of the current item
		//CookingItem temp = station.getCurr();
		station.moveToStart(); //always tick from the start (therefore cann't do cnext() later)
		for (int i = 0; i < station.length(); i++) {
			station.getValue().tick();
			if (!station.isAtEnd()) {
				station.next();
			}
		}
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

/*
	but the thing is you can only remove the current item right. so the question is:
	when current item is removed, curr is updated to the next item, and you would not want
	to skip that item right? meaning if remove() returns CookingItem, then you want to do
	station.moveToPos(itemPos) rather than station.moveToPos(itemPos+1)

	fact: you are not bound to do itemPos+1.
	do a boolean var. when remove() returns CookingItem, change that boolean.
	and then change that boolean back.

	itemIsRemoved = true  when removeThreshold is met
	stationIsRemoved = true when station.length() == 0 --> station.isEmpty()


	it makes more sense to calculate the penalty in kitchen. since you will be return temp.
	you can just do

		if (!tend(X,X) = null) {
			totalPenalty +=
		}

*/

// tend the current item, returning it if you decide to remove it
// (based on remaining time or penalty), or return null if nothing happened.
	public CookingItem tend(int removeThreshold, int penaltyThreshold) {
		if (station.getValue().timeRemaining() <= removeThreshold) {
			CookingItem temp = (CookingItem) station.getValue(); //store the item that you'll remove
			station.remove(); //remove the current item from this station
			//totalStationPenalty += temp.penalty(); //accumulate the penalty
			itemIsRemoved = true;
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
		return this.stationName;
	}
/*
	public int getTotalStationPenalty() {
		return this.totalStationPenalty;
	}
*/

/*
		station is a CList. getValue() returns T (CookingItem). a cooking item has timeRemaining()




		which item do you want to tend to? --> not determined here.
		how about do

		there is a curr in every CList.
		you do  station.cnext() to move to the next item in CList.

		what if the original next item is removed?

		if you do remove method, your curr is already at the next item.
		This means that if your remove method is executed, the item (in that station) which you need to tend to
		next time is already updated.

		so you can do an if else statement.

		the process goes as follows:
		you tend to an item (determined by the current station, current item. i.e., where you are at)
		if return null (item was not removed) --> station.getCurr() = station.getCurr().cnext();
		if return current item (i.e. else) --> curr inside station is already moved;
		in both cases, you need to move to the next station.
		You can only "move to the next station" in the kitchen. thus, this is something you cannot do
		inside CookingStation class.

		if (station.length()  == 0) {
			kitchen.remove();
			stationIsRemoved = true; //curr is at the next station now.
			//then you don't want to do stationPos +1 then.


		remove : remove the item(station) from the CList (kitchen).

		kitchen.remove(station)

		if nothing was removed -->


		kitchen
		station


*/

// in your tend method, after you tend one item, you ALWAYS want to move to the next item
//your cutthroatkitchen tick method is not going to work bc if you start at the end, it's not going
// enter the while loop.

//



}
