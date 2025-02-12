import java.util.Arrays;

public class Array2D {
    public static void main(String[] args) {
        int[][] arr = new int[4][];
        arr[0] = new int[]{1,2,3};
//        arr[0] = {1,2,3,4,4};
        arr[1] = new int[]{1,2,3,4,5};
        arr[2] = new int[]{1,2,3,7,8,9};
        arr[3] = new int[4];
        arr[3][3]=3;

        System.out.println(Arrays.toString((arr)));

        for(int[] ar : arr)
            System.out.println(Arrays.toString((ar)));
    }
}
