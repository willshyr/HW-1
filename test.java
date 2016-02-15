public class test {
    public static void main(String[] args) {
        CList<Integer> x= new CList<Integer>();
        for (int i = 0; i < 9; i++) {
            x.insert2(i);
        }
        System.out.println(x);
    }
}
