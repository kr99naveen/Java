import java.util.Arrays;

public class selectionSort {
    public static void main(String[] args) {
        int a[] = {1,5,2,4,13,43,21,3,34,2,33,22,1,23};
        selectionSort(a);
        System.out.println(Arrays.toString((a)));
    }

    static  void selectionSort(int[] arr){
        int size = arr.length;
        for(int i=0;i<size;i++){
            int minIndex=i;
            for(int j=i+1;j<size;j++){
                if(arr[i]>arr[j]) minIndex=j;
            }
            int temp=arr[i];
            arr[i]=arr[minIndex];
            arr[minIndex]=temp;
        }
    }

    //get index of the and place the element that belong to that index, like from
    //smallest to the largest i.e fromm 0 to n-1

    //T : All cases  O(n^2)

    // NOT stable
}
