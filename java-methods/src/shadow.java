import java.util.Arrays;

public class shadow {
    static  int x=90;
    public static void main(String[] args) {
        int x=0;
        System.out.println(x);
        x=40;
        System.out.println(x);
        fun();
        fun2(1,2,3,4,5,6);
        fun2(3,4,5,6,7,8,9);
    }

    private static void fun() {
        System.out.println(x);
    }


    //varibale length arguements receiving functions
    // syntax ...a , just like spread operator in javascript
    private static  void fun2(int ...a){
        System.out.println(Arrays.toString((a)));
    }
}
