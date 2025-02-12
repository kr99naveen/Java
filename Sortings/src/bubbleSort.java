import java.util.Arrays;

public class bubbleSort {
    public static void main(String[] args) {
        int[] a = {1,5,2,4,13,43,21,3,34,2,33,22,1,23};
        bubbleSort(a);
        System.out.println(Arrays.toString((a)));
    }

    static void bubbleSort(int[] arr){
        int size = arr.length;
        for(int i=0;i<size-1;i++){
            for(int j=0;j<size-1-i;j++)
                if(arr[j]>arr[j+1])
                {
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
        }
    }

    //traverse array and move larger elements to right by compare the next of it
    //in every move, the largest element of the sub-array will move to right.

    //T : All cases O(n^2)

    //STABLE
}
