import java.util.*;
import java.io.*;

public class CutthroatKitchen extends CList<CookingStation> {

	private final int penaltyThreshold = 0;
	private int removeThreshold; //or int[] ? 
	private boolean stationIsRemoved = false; 

	public static void main(String[] args) {


		System.out.println("This is a CutthroatKitchen game.");
    	

    	// read items in to each station of the kitchen
    	Scanner infile = null;
        boolean inerror = false;

        try {
            System.out.println("0 " + args[0] + " should be input filename");
            infile = new Scanner(new FileReader(args[0]));
        } catch (ArrayIndexOutOfBoundsException a) {
            System.err.println("must give input filename at command line");
            inerror = true;
        } catch (IOException f) {
            System.err.println("can't open that file, try again");
            inerror = true;
        }
        if (inerror) {
            System.err.println("exiting...");
            System.exit(1);
        }
        
        CookingItem itemOne; //items
        CList<CookingStation> kitchen = new CList<CookingStation>(); //CList of stations
        CookingStation station; //stations (CList of items)


        Scanner inline;
        String line;
        String name, item;
        int time, under, over;
        while (infile.hasNext()) {
        	station = new CookingStation();
            name = infile.nextLine(); 
            line = infile.nextLine(); //one item info
            station.setStationName(name);
            while (!line.equals("")) {
                inline = new Scanner(line);
                item = inline.next();
                time = inline.nextInt();
                under = inline.nextInt();
                over = inline.nextInt();
                itemOne = new CookingItem(item, time, under, over);
                station.addItem(itemOne);
                //name += " " + item + " " + time + " " + under + " " + over;
                line = infile.nextLine(); //read the next item info 
            }
            kitchen.insert(station); //station w/ its name & items are added to the kitchen CList
            //prints all items in that station
            System.out.println("Items in " + station.getStationName() + ": " + station.toString());  

        }
        System.out.println("Items in the kitchen: " + kitchen.toString()); 
	} //end Main 

	//tick all items at all station; update current station 
	public void tickKitchen() {
		int stationPos = kitchen.currPos(); //store the "index" of the current station
		kitchen.moveToStart(); //always tick from the start (therefore cann't do cnext() later) 
		while (!kitchen.isAtEnd()) {
			kitchen.getValue().tick(); //.curr
			kitchen.next();
		}
		kitchen.getValue().tick(); //.curr (tick the last station('s items) in the kitchen)
		if (stationIsRemoved) {
			station.moveToPos(itemPos); 
			//bc curr is already pointing at the item next to the item being removed
			isRemoved = false; //update boolean
		} else { //move curr to the next item in the station
			itemPos += 1; //move to the next item (for tend() prupose)
			itemPos = itemPos % (station.length() -1); 
			station.moveToPos(itemPos); //move to the next item in the station
		}
	}

	/*
		instead of saying station.length() == 0, you can say kitchen.getCurr()
		kitchen.getCurr() will give you a station 

	*/

	/**
	* remove the station (item) from the kitchen (CList)
	* @return the removed station; null if the station is not removed 
	*
	*/
	public CookingStation removeStation() {
		if (kitchen.getValue().isEmpty()) { //station.length() == 0 
			CookingStation temp = kitchen.getValue(); //store the station that is to be removed
			kitchen.remove();  //curr is at the next station now
			stationIsRemoved = true;
			return temp;
		}
		return null;
	}


	//160214
	public void tickKitchen() {
		int stationPos = kitchen.currPos(); //counts which station we're at
		
		//kitchen.curr.tend(removeThreshold[0], this.penaltyThreshold);
		kitchen.getCurr().tend(removeThreshold[0], this.penaltyThreshold);
		kitchen.moveToStart(); // move to the first station in the kichen
		
		while (!kitchen.isAtEnd()) { // while you haven't reached the last station
			kitchen.getCurr().tick(); // you tick all items in that station
			//kitchen.curr.tick();
			kitchen.next(); //you move to the next station 
		} //end while
		kitchen.getCurr().tick(); // tick items in the last station 
		//kitchen.curr.tick(); 
		stationPos += 1; //move to the next station (for tend() purpose)
		stationPos = stationPos % (kitchen.length()-1); 
		kitchen.moveToPos(stationPos);
		//why don't you just do kitchen.cnext() ?? 
	}
/*
	//do kitchen.cnext() to move curr station to the next station. 

	in your main method (where the game takes place), you want to do kitchen.cnext() EVERYTIME
	after you do tend(), regardless of the tend result.
	
	if we have an empty station (CList), is it removed automatically?? 

*/


}