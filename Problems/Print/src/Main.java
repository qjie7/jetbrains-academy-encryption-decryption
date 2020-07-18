import java.util.Scanner;

public class Main {

    public static void print(String strArg) {
        System.out.println("print(\"" + strArg + "\")");
    }

    public static void print(String s , int n) {
        System.out.println("print(\"" + s + "\"" + ", " + n + ")");
    }

    // write your method here

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int val = scanner.nextInt();
        print(str);
        print(str, val);
    }
}