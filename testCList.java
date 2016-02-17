// import java.util.Scanner;
// import java.io.FileReader;
// import java.io.IOException;
public class testCList {
    public static void main(String[] args) {
        String[] a = {"grill","stove","oven"};
        // CutthroatKitchen x = new CutthroatKitchen();
        // for (String i : a) {
            CookingItem foodItem = new CookingItem("beef", 7, 10, 13);
            CookingStation station = new CookingStation(a[0]);
            station.addItem(foodItem);
            // x.addStation(station);
        // }
        // x.moveToPos(2);
        // System.out.println(x);
        // System.out.println(x.getStation());
        System.out.println(station.getStationItem().timeRemaining());
        System.out.println(station.tend(1,0));
        // System.out.println(x.getStation().tend(1,0));
        // System.out.print(station.getValue());
        // System.out.println(station.isEmpty());
        // x.cnext();
        // System.out.println(x.isEmpty());

    }
}
