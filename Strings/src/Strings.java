import java.util.Arrays;

public class Strings {
    public static void main(String[] args) {
        System.out.println(Arrays.toString((args)));
        Integer a=2;
        System.out.println("value of a ::: "+a);
        String n1 = "Naveen";
        String n2 = "Naveen";
        //these two strings are pointing to same object in heap, look
        System.out.println("n1==n2 ::: " + (n1==n2));
        System.out.println("n1.equals(n2) ::: " + n1.equals(n2));

        String n3 = new String("Prateek");
        String n4 = new String ("Prateek");
        //now these two strings are pointing to diffrernt objects in heap,
        // and thats == will give false as it compare value and object both
        // while .equals() give true as it compare values only, look
        System.out.println("n3 == n4 ::: " + (n3==n4));
        System.out.println("n3.equals(n4) ::: " + n3.equals(n4));

        //and we can't strings part s just like in array.
        //internally in String class it is handles like array. but can't be used like array.
        //n3[0] will give error, look

        System.out.println("values ::: "+n3.charAt(0)+n4.charAt(3));
    }
}
