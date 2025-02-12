import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello and welcome!");
//        Scanner input = new Scanner(System.in);
//        System.out.println("enter number 1 :: ");
//        float n1 = input.nextInt();
//        System.out.println("enter number 2 :: ");
//        float n2 = input.nextInt();
//        System.out.println("line read as inout ::: "+(n1+n2));

        try{
            byte a =56;
            byte b = 45;

            float res = a+b;

            System.out.println("result ::: "+res);
        }
        catch(Exception e){
            System.out.println("error :: "+e);
        }

    }
}