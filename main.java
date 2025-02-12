public class main{
    public static void main(String[] args){
        String str = "Naveen";
        String result = fun(str);
        System.out.printf("Greet string ::: %s",result);
    }

    public static String fun(String str){
        return "Hello " + str;
    }
}