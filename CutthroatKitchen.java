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

	public void addStation(CookingStation it) {
		this.kitchen.insert(it);
	}

	public CookingStation getStation() {
		return this.kitchen.getValue();
	}

	public void tick() {
		int temp = this.kitchen.currPos();
		this.kitchen.moveToStart();
        // goes through stations in the kitchen
        for (int i = 0; i < kitchen.length(); i++) {
            this.kitchen.getValue().tick();
            // System.out.println(kitchen.getValue());
            if (!kitchen.isAtEnd()) {
                this.kitchen.next();
            }
        }
		this.kitchen.moveToPos(temp);
	}

	public String toString() {
		return this.kitchen.toString();
	}

	public boolean isEmpty() {
		return this.kitchen.isEmpty();
	}

	public CookingItem tend() {
		return this.kitchen.getValue().tend();
	}
	// public boolean foodCooking() {
	// 	for (int i = 0; i < kitchen.length(); i++) {
	// 		kitchen.getValue().tick();
	// 	}
	// }


}
