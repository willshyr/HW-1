import java.util.*;
import java.io.*;

public class CutthroatKitchen extends CList<CookingStation> {

	private final int penaltyThreshold;
	private boolean stationIsRemoved;
	private CList<CookingStation> kitchen; //CList of stations
	// private CookingStation station;


	public CutthroatKitchen() {
		this.kitchen = new CList<CookingStation>();
		// this.station = new CookingStation(name);
		this.penaltyThreshold = 0; //penaltyThreshold
		this.stationIsRemoved = false;
	}

	public CookingStation getStation() {
		return this.kitchen.getValue();
	}

	public void addStation(CookingStation it) {
		this.kitchen.insert2(it);
	}





}
