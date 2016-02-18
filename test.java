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
        int[] removeThreshold = {0, 1, 2, 3};
        String[] fname = new String[removeThreshold.length];

        for (int i = 0; i < removeThreshold.length; i++) {
            fname[i] = "sim" + Integer.toString(removeThreshold[i]) + ".txt";
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
        CutthroatKitchen kitchen = new CutthroatKitchen();
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
            kitchen.addStation(station);
            // System.out.println(name);
        }
        int j = 1;
        System.out.println(kitchen); // prints stations in kitchen
        // System.out.println(kitchen.getStation().isEmpty());
        // for (int k = 0; k < 7; k++) {
        // kitchen.getStation().getCurrStation().cnext();
        // kitchen.getStation().getCurrStation().cnext();
        kitchen.getKitchen().moveToStart();
        while(!kitchen.isEmpty()) {
            kitchen.tick();
            if (kitchen.getStation().isEmpty()) {
                kitchen.getKitchen().remove();
            } else {
                kitchen.getKitchen().cnext();
            }
            CookingItem tendItem = kitchen.getStation().tend(removeThreshold[j], penaltyThreshold);
            System.out.println(tendItem);
            if (tendItem != null) {
                kitchenPenalty = tendItem.penalty();
            }

            System.out.println(kitchen);
        // }
        }
        System.out.println(kitchenPenalty);
    }
}
