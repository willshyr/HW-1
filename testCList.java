// import java.util.Scanner;
// import java.io.FileReader;
// import java.io.IOException;
public class testCList {
    public static void main(String[] args) {
        String[] a = {"grill","stove"};
        CutthroatKitchen kitchen = new CutthroatKitchen();
        for (String i : a) {
            CookingItem foodItem = new CookingItem("beef", 1, 10, 13);
            CookingStation station = new CookingStation(i);
            station.addItem(foodItem);
            kitchen.addStation(station);
        }
        // kitchen.moveToPos(2);
        // for (int j = 1; j < 3; j++) {
        //     kitchen.tick();
        //     System.out.println(kitchen);
        //     CookingItem tendItem = kitchen.getStation().tend(1, 0);
        //     System.out.println(tendItem);
        //     // if (tendItem != null) {
        //     //     int kitchenPenalty = tendItem.penalty();
        //     // }
        // }
        System.out.println(kitchen.getStation().getCurrStation().getClass().getName());
        // kitchen.getStation().cnext();
        // System.out.println(kitchen.getStation());
        // System.out.println(kitchen.getStation());
        // CookingStation rmStation1 = kitchen.getKitchen().remove();
        // System.out.println(kitchen.getStation());
        // CookingStation rmStation2 = kitchen.getKitchen().remove();
        // System.out.println(kitchen.getStation());
        // CookingStation rmStation3 = kitchen.getKitchen().remove();
        // kitchen.getKitchen().remove();
        System.out.println(kitchen);
        // System.out.println(kitchen.getStation());
        // System.out.println(kitchen.getStation().isEmpty());
        // kitchen.tick();
        // System.out.println(kitchen);
        // System.out.println(kitchen.getKitchen().getClass().getName());
        // System.out.println(station.getStationItem().timeRemaining());
        // System.out.println(kitchen.getKitchen().getStation(t.end(1,0));
        // System.out.println(kitchen.getStation().tend(1,0));
        // System.out.print(station.getValue());
        // System.out.println(station.isEmpty());
        // System.out.println(kitchen.isEmpty());

    }
}
