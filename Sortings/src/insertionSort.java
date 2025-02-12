import java.util.Arrays;

public class insertionSort {
    public static void main(String[] args) {
        int a[] = {15,5,2,4,13,43,21,3,34,2,33,22,1,23,0};
        insertionSort(a);
        System.out.println(Arrays.toString((a)));
    }

//    static void insertionSort(int[] arr){
//        int size = arr.length;
//        for(int i=0;i<size-1;i++){
//            int j=i+1;
//            while(j>0){
//                if(arr[j]<arr[j-1]){
//                    int temp=arr[j];
//                    arr[j]=arr[j-1];
//                    arr[j-1]=temp;
//                    j--;
//                }
//                else break;
//            }
//        }
//    }

    //without using swapping, just shifting the greater elements to right

    static void insertionSort(int[] arr){
        int size = arr.length;
        for(int i=0;i<size-1;i++){
            int j=i+1;
            int key=arr[j];
            while(j>0 && arr[j-1]>key){
                arr[j]=arr[j-1];
                j--;
            }
            arr[j]=key;

            System.out.println("Pass " +i + Arrays.toString(arr));
        }
    }
}



//sort the array partially, and then one by one insert the elements from unsorted part to sort parted
//in sorted manner

//T: Best Case O(n), Average Case O(n^2), Worst Case O(n^2)

//STABLE
