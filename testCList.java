// import java.util.Scanner;
// import java.io.FileReader;
// import java.io.IOException;
public class testCList {
    public static void main(String[] args) {
        String[] a = {"stove","grill","oven"};
        CutthroatKitchen x = new CutthroatKitchen();
        for (String i : a) {
            CookingStation station = new CookingStation(i);
            x.insert(station);

        }
        // x.moveToPos(2);
        System.out.println(x.getValue());
        x.remove();
        System.out.println(x);
        System.out.println(x.getValue());
    }
}
