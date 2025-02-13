import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class KaprekarConstant {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int kaprekarConst = 6174;
        System.out.print("Enter the number of tries : ");
        int tries = input.nextInt();
        while(tries>0) {
            Random random = new Random();
            int n = 1000 + random.nextInt(9000);
            System.out.print("number generated : "+n+" ");
            boolean check=false;
            int count = 7;
            while (count > 0) {
                String num = String.format("%04d", n);  // Ensure the number is always 4 digits, padding with leading zeros if necessary
                char[] numAsc = num.toCharArray();
                Arrays.sort(numAsc);

                // Reverse numAsc array (using StringBuilder for simplicity)
                String numDesc = new StringBuilder(new String(numAsc)).reverse().toString();

                // Parse both ascending and descending numbers
                int n1 = Integer.parseInt(numDesc);
                int n2 = Integer.parseInt(new String(numAsc));
                if (n1 - n2 == kaprekarConst) {
                    check = true;
                    break;
                }

                n = n1 - n2;
                count--;
            }

            if(check) System.out.println("Yes true!");
            else System.out.println("Nope, :::::::::::::::::::::::::::::::::::::: thrown.");
            tries--;
        }
    }
}
