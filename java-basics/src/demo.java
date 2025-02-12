import java.util.Scanner;

public class demo {
    public static void main(String[] args) {
        System.out.println("hello demo");
        Scanner input = new Scanner(System.in);
        /*
        System.out.println("enter day number ::: ");
        int DayNum = input.nextInt();
        switch (DayNum){
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("invalid input");
        }


        //enhanced switch in java, same working, just different syntax, no break statements required.
        switch (DayNum) {
            case 1 -> {
                System.out.println("Day is Monday");
                System.out.println("Today is Monday");
            }
            case 2 -> System.out.println("Day is Tuesday");
            case 3 -> System.out.println("Day is Wednesday");
            case 4 -> System.out.println("Day is Thursday");
            case 5 -> System.out.println("Day is Friday");
            case 6 -> System.out.println("Day is Saturday");
            case 7 -> System.out.println("Day is Sunday");
            default -> System.out.println("invalid input");
        }

        switch (DayNum){
            case 1,2,3,4,5 -> System.out.println("weekday");
            case 6,7 -> System.out.println("weekend.");
        }
        */

        //NESTED switch
        int a=input.nextInt();
        char b=input.next().charAt(0);
        switch (a){
            case 1:
                System.out.println("one");
                break;
            case 2:
                System.out.println("two");
                switch (b){
                    case 'a':
                        System.out.println("character a");
                        break;
                    case 'b':
                        System.out.println("character b");
                }
                break;
            case 3:
                System.out.println("three");
            default:
                System.out.println("default");
        }


        //enhanced
        switch (a){
            case 1 -> System.out.println("one");
            case 2 -> {
                System.out.println("two");
                switch (b) {
                    case 'a':
                        System.out.println("character a");
                        break;
                    case 'b':
                        System.out.println("character b");
                }
            }
            case 3 -> System.out.println("three");
            default -> System.out.println("default");
        }
    }
}