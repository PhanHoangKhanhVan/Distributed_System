import java.util.Scanner;

public class Ex1_1{
    public static long factorial(int n){
        if (n == 0|| n == 1){
            return 1;
        }
        else {
            return n * factorial(n-1);
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = sc.nextInt();
        long result = factorial(n);
        System.out.print("The factorial of " + n + " is "+ result);
        sc.close();
    }
}
