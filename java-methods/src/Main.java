import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String name = "Naveen Kumar Jaiswal";
        changeString(name);
        int a[] = {1,2,3,4,5};
        changeArray(a);
        System.out.println("changed values :::: " + name + "\n" + Arrays.toString((a)));
    }

    private static void changeArray(int[] a) {
        a[0]=100;
    }

    static void changeString(String name ) {
        System.out.println("address 1 "+name);
        name = "Naveen Patnayak";
    }
}
