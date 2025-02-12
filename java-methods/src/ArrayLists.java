import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayLists {
    public static void main(String[] args) {
       /*

        ArrayList<Integer> list = new ArrayList<>(10);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        System.out.println(list);
        System.out.println(list.contains((1)));
        System.out.println(list.contains((10)));
        list.remove(3);
        System.out.println(list);
        System.out.println(list.get(0));
        //take 5 more inputs custom
        Scanner input = new Scanner((System.in));
        for(int i=0;i<10;i++) list.add(input.nextInt());
        System.out.println("new list ::: "+list);


        */


        //multidimensional array
        ArrayList<ArrayList<Integer>> list2 = new ArrayList<>();
        System.out.println(list2);
        list2.add(new ArrayList<>(Arrays.asList(1,2,3,4)));
        ArrayList<Integer> childList = new ArrayList<>();
        childList.add(1);
        childList.add((2));
        list2.add(childList);
        System.out.println(list2.get(0).get(2));
        System.out.println(list2);

    }

}
