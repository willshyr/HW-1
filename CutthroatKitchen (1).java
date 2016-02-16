import java.util.*;
import java.io.*;

public class CutthroatKitchen extends CList<CookingStation> {


	private final int penaltyThreshold; //= 0; //penaltyThreshold
	private boolean stationIsRemoved;// = false; 
	private CList<CookingStation> kitchen; //CList of stations
	private CookingStation station;

	public CutthroatKitchen() {
		this.kitchen = new CList<CookingStation>(); 
		this.station = new CookingStation();
		this.penaltyThreshold = 0; //penaltyThreshold
		this.stationIsRemoved = false; 
	}



//PrintWriter outFile = new PrintWriter(new FileWriter("myOutput.txt"));



	public static void main(String[] args) throws IOException {

	
		System.out.println("This is a CutthroatKitchen game.");
    	
    	// read items into each station of the kitchen
    	Scanner inFile = null;
        boolean inerror = false;
        PrintWriter outFile; 

        try {
            System.out.println("0 " + args[0] + " should be input filename");
            inFile = new Scanner(new FileReader(args[0]));
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
        //CList<CookingStation> kitchen = new CList<CookingStation>(); //CList of stations
        //CookingStation station; //stations (CList of items)

        CutthroatKitchen var = new CutthroatKitchen(); 

        Scanner inLine;
        String line;
        String name, item;
        int time, under, over;
        int kitchenPenalty = 0;

        int[] remove = { 0, 1, 2, 3};
		String[] filename = {"sim0.txt", "sim1.txt", "sim2.txt", "simP.txt"}; //output filenames

       	for (int i = 0; i < remove.length; i++) {
       		System.out.println("This is the" + (i+1) + "round of cutthroatKitchen.");
       		inFile = new Scanner(new FileReader(args[0])); //re-read the input file
       		outFile = new PrintWriter(new FileWriter(filename[i]));
			while (inFile.hasNext()) {
				System.out.println("inside while infile.hasNext");
        		//CutthroatKitchen cutthroatKitchen = new CutthroatKitchen();
        		//station = new CookingStation();
            	name = inFile.nextLine(); //station name
            	var.station.setStationName(name); 
            	//inLine = new Scanner(line);
            	while (inFile.hasNext()) {
            		line = inFile.nextLine(); //one item info
            		if(!line.equals("")) {  
            		System.out.println("inside line.equals while"+line);
                	inLine = new Scanner(line);
                	item = inLine.next();
                	time = inLine.nextInt();
                	under = inLine.nextInt();
                	over = inLine.nextInt();
                	itemOne = new CookingItem(item, time, under, over);
                	System.out.println(itemOne);
                	var.station.addItem(itemOne);
                	} else {
						var.kitchen.insert2(var.station); //station w/ its name & items are added to the kitchen CList
						System.out.println("var.station"var.station);
                	}
                	//name += " " + item + " " + time + " " + under + " " + over;
                	/*
                	if (inFile.hasNext()){
                		System.out.println("inside if");
                		line = inFile.nextLine(); //read the next item info
                	} else {
                		line = inFile.next();
                	}
                	*/
            	} //end line.equals while
            	//var.kitchen.insert2(var.station); //station w/ its name & items are added to the kitchen CList
            	

            	
			} //end infile.hasNext() while            
			inFile.close();	// close infile scanner
            

       			while (!var.kitchen.isEmpty()) {
        			var.tickKitchen(); //every item.timeRemaing() -1; 
        			CookingItem k = var.kitchen.getValue().tend(remove[i], var.penaltyThreshold); //tend to the current item
        			if (k != null) {
        				kitchenPenalty += k.penalty(); 
        			}
        			outFile.println(var.kitchen.toString()); //print the state of each station (each item)
        		} //end kitchen.isEmpty() while
        	outFile.println("Final penalty was: " + kitchenPenalty);
        	outFile.close();
        	System.out.println("Finish one game."); 
        } //end for
        System.out.println("CutthroatKitchen games are finished."); 
    } //end Main 

/*
	    let's start the game!
        while there is still item in the kitchen
        i.e., while kitchen is not empty
        tickKitchen

        for item.toString(), you should only include

        	(item timeRemaining())
        
        We need to run 4 simulations in the main program (main method).
        So after each run (output process and final penalty to a file),
        we need to move inFile and inline Scanner to the beginning of the file. 
        	--> declare new scanner to the file
        okay great! now move on to the next question.
        in your tend method. you need to add one thing: calculate the penalty

		you only print out the total penalty after you exit out the while loop



		int kitchenPenalty = 0;
       for (int i = 0; i < remove.length; i++) {
       		outFile = new PrintWriter(new FileWriter(filename[i]));
       		while (!kitchen.isEmpty()) {
        		kitchen.tickKitchen(); //every item.timeRemaing() -1; 
        		CookingItem k = kitchen.getValue().tend(remove[i], penaltyThreshold); //tend to the current item
        		if (k != null) {
        			kitchenPenalty += k.penalty(); 
        		}
        		outFile.println(kitchen.toString()); //print the state of each station (each item)
        	} //end while
        	outFile.println("Final penalty was: " + kitchenPenalty);
        	outFile.close();
       }
  */

	/**
	* Tick all items at all station; update current station. 
	*/
	public void tickKitchen() {
		int stationPos = kitchen.currPos(); //store the "index" of the current station
		this.kitchen.moveToStart(); //always tick from the start (therefore cann't do cnext() later) 
		while (!kitchen.isAtEnd()) {
			this.kitchen.getValue().tick(); //.curr  (i.e. station.tick())
			this.kitchen.next();
		}
		System.out.println(this.kitchen.getValue());
		//this.kitchen.getValue().tick(); //.curr (tick the last station('s items) in the kitchen)
		if (this.stationIsRemoved) {
			station.moveToPos(stationPos); 
			//bc curr is already pointing at the item next to the item being removed
			stationIsRemoved = false; //update boolean
		} else { //move curr to the next item in the station
			stationPos += 1; //move to the next item (for tend() prupose)
			stationPos = stationPos % (station.length() -1); 
			station.moveToPos(stationPos); //move to the next item in the station
		}
	}

	/**
	* remove the station (item) from the kitchen (CList)
	* @return the removed station; null if the station is not removed 
	*
	*/
	public  CookingStation removeStation() {
		if (this.kitchen.getValue().isEmpty()) { //station.length() == 0 
			CookingStation temp = this.kitchen.getValue(); //store the station that is to be removed
			this.kitchen.remove();  //curr is at the next station now
			stationIsRemoved = true;
			return temp;
		}
		return null;
	}

	//160214
	/*
	public void tickKitchen2() {
		int stationPos = kitchen.currPos(); //counts which station we're at
		
		//kitchen.curr.tend(removeThreshold[0], this.penaltyThreshold);
		kitchen.getCurr().tend(removeThreshold, this.penaltyThreshold);
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
	*/
/*
	//do kitchen.cnext() to move curr station to the next station. 

	in your main method (where the game takes place), you want to do kitchen.cnext() EVERYTIME
	after you do tend(), regardless of the tend result.
	
	if we have an empty station (CList), is it removed automatically?? 

*/

}