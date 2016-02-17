import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
public class test {
    public static void main(String[] args) {
        // String[] a = {"stove","grill","oven"};
        // CutthroatKitchen x = new CutthroatKitchen();
        // for (String i : a) {
        //     CookingStation station = new CookingStation(i);
        //     x.insert(station);
        // }
        // // x.moveToPos(2);
        // System.out.println(x.length());
        // System.out.println(x.getValue());
        int penaltyThreshold = 0;
        String prefix = "sim";
        int[] fnum = {0, 1, 2, 3};
        String[] fname = new String[fnum.length];
        for (int i = 0; i < fnum.length; i++) {
            fname[i] = "sim" + Integer.toString(fnum[i]) + ".txt";
            // System.out.println(fname);
        }
        System.out.println("This is a CutthroatKitchen game.");
        // read items into each station of the kitchen
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

        CookingItem foodItem; //items
        CutthroatKitchen k = new CutthroatKitchen();
        Scanner inline;
        String line;
        String name, item;
        int time, under, over;
        int kitchenPenalty = 0;

        while (infile.hasNext()) {
            name = infile.nextLine();
            line = infile.nextLine();
            CookingStation station = new CookingStation(name);
            while (!line.equals("")) {
                inline = new Scanner(line);
                item = inline.next();
                time = inline.nextInt();
                under = inline.nextInt();
                over = inline.nextInt();
                name += " " + item + " " + time + " " + under + " " + over;
                line = infile.nextLine();
                foodItem = new CookingItem(item, time, under, over);
                station.addItem(foodItem);
            }
            k.addStation(station);
            // System.out.println(name);
        }

        System.out.println(k); // prints stations in kitchen
        System.out.println(k.getStation().isEmpty());
        // while(!k.isEmpty()) {
            int j = 1;
            int temp = k.currPos();
            k.moveToStart();
            // goes through stations in the kitchen
            for (int i = 0; i < k.length(); i++) {
                k.getValue().tick();
                // System.out.println(kitchen.getValue());
                if (!k.isAtEnd()) {
                    k.next();
                }
            }
            k.moveToPos(temp);
            k.cnext();
            // tend to the next item in the station
            CookingItem x = k.getStation().tend(fnum[j], penaltyThreshold);
            // System.out.println(k);
            // k.remove();
            // System.out.println(k);
            // System.out.println(k.getValue());
            // System.out.println(k.getValue().isEmpty());
            // if (x != null) {
            //     kitchenPenalty += x.penalty();
            // }
            // if (k.getValue().isEmpty()) {
            //     k.remove();
            // }
            // k.cnext();
            // k.cnext();
            // System.out.println(k.getValue().isEmpty());
            // System.out.println(k.getValue());
        // }

        // System.out.println(k.getValue().getValue()); // prints stations in kitchen
        // System.out.println(k);
        // while (!k.isEmpty()) {
        //tick
        // while (!k.isEmpty()) {
        // for (int y = 0; y < 5; y++) {


    //
    //     // }
    //
    //     // System.out.println(k.getValue());
    //     // }
	// 	// kitchen.moveToPos(temp);
    //     // System.out.println(k);
    //     // System.out.print(kitchen);
    //     // System.out.println();
    }
}
