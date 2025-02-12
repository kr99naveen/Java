import java.util.Scanner;

public class pattern {
    public static void main(String[] args) {
        Scanner input = new Scanner((System.in));
        System.out.print("enter the size ::: ");
        int s = input.nextInt();
        if(!(s%2==0)) s++;
        for (int i = 0; i < s / 2; i++) {
            int gap = 2 * i;
            if (gap < 0) gap = 0;
            for (int j = 0; j <= s / 2 - 1 - gap / 2; j++) System.out.print("*");
            for (int j = 1; j <= gap; j++) System.out.print(" ");
            for (int j = s / 2 - 1 - gap / 2; j >= 0; j--) System.out.print("*");
            System.out.println();
        }
        for (int i = s / 2 - 1; i > 0; i--) {
            int gap = 2 * i - 2;
            if (gap < 0) gap = 0;
            for (int j = 0; j <= s / 2 - 1 - gap / 2; j++) System.out.print("*");
            for (int j = 1; j <= gap; j++) System.out.print(" ");
            for (int j = s / 2 - 1 - gap / 2; j >= 0; j--) System.out.print("*");
            System.out.println();
        }
        s++;
        for (int i = 0; i < s / 2; i++) {
            int gap = s / 2 - i;
            if (gap < 0) gap = 0;
            for (int j = 0; j < gap; j++) System.out.print(" ");
            for (int j = 0; j < 2 * i + 1; j++) System.out.print("*");
            for (int j = gap; j > 0; j--) System.out.print(" ");
            System.out.println();
        }
        for (int i = s / 2; i >= 0; i--) {
            int gap = s / 2 - i;
            if (gap < 0) gap = 0;
            for (int j = 0; j < gap; j++) System.out.print(" ");
            for (int j = 0; j < 2 * i + 1; j++) System.out.print("*");
            for (int j = gap; j > 0; j--) System.out.print(" ");
            System.out.println();
        }
    }

}
