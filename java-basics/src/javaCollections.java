import java.util.*;

public class javaCollections {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        HashMap<Integer,ArrayList<Integer>> mp = new HashMap<>();
        mp.put(1,list);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // Add elements to the PriorityQueue
        pq.add(10);
        pq.add(20);
        pq.add(15);
        pq.add(5);

        PriorityQueue<Integer> pqr = new PriorityQueue<>(Collections.reverseOrder());

        // Add elements to the PriorityQueue
        pqr.add(10);
        pqr.add(20);
        pqr.add(15);
        pqr.add(5);

        System.out.println(list);
        System.out.println(mp);
        System.out.println(pq);
        System.out.println(pqr);
        System.out.println(pq.peek());
        System.out.println(pqr.peek());
    }
}