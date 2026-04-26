import java.util.Scanner;

class NumberThread extends Thread {
    private int number;

    public NumberThread(int number){
        this.number = number;
    }

    public void run(){
        try {
            for (int i = 0; i < 5; i++){
                System.out.println("Thread Number: " + number);
                if (i<4) Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");

        }
    }
}

public class Ex1_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        int threadCount = 1; 

        System.out.println("Press Enter to start a new thread (type 'exit' to quit):");
        while (true) { 
            String input = sc.nextLine(); 
            if (input.equalsIgnoreCase("exit")) break;

            NumberThread t = new NumberThread(threadCount++);
            t.start();
        }
        sc.close();
    }
}