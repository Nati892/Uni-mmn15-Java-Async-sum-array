import netscape.javascript.JSUtil;

import java.util.Random;
import java.util.Scanner;

public class Summarizer {

    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        runProgram();
    }


    private static void runProgram() {
        System.out.println("Hi, Please enter the array size");
        int arrSize = inputPositiveNum();
        System.out.println("Please enter the number of threads");
        int threadsAmount = inputPositiveNum();

        SummarizeThread[] threadsArr = new SummarizeThread[threadsAmount];
        int[] myArr = new int[arrSize];
        initIntArr(myArr);
        PoolMonitor monitor = new PoolMonitor(myArr);

        System.out.println("Summing it all up");
        for (int i = 0; i < threadsArr.length; i++) {
            threadsArr[i] = new SummarizeThread(monitor);
        }

        for (int i = 0; i < threadsArr.length; i++) {
            threadsArr[i].start();
        }

        System.out.println("result: " + monitor.getSum());
    }


    //get an int from the user
    private static int inputPositiveNum() {
        int res = 0;
        boolean good_input = false;
        while (!good_input) {
            try {
                System.out.println("Please enter an integer");
                res = Integer.parseInt(scanner.next());
                if (res > 0)
                    good_input = true;
                else
                    System.out.println("The number must be bigger then zero");
            } catch (Exception e) {
                System.out.println("Please re-enter integer");
            }
        }
        return res;
    }

    private static void initIntArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100) + 1;
        }
    }
}
