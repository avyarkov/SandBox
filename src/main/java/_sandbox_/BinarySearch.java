package _sandbox_;

import java.io.PrintWriter;
import java.util.Scanner;

public class BinarySearch {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        out.println("Input N (numbers from 0 to N):");
        out.flush();
        //noinspection UnnecessaryLocalVariable
        int n = in.nextInt();

        int l = 0, r = n;
        while (r - l > 1) {
            int m = (l + r) / 2;
            out.printf("Is %d less or equal than your number? (yes/no)%n", m);
            out.flush();
            String response = in.nextLine().toLowerCase();
            switch (response) {
                case "y", "yes" -> l = m;
                case "n", "no" -> r = m;
                default -> out.println("Unrecognized response...");
            }
        }

        out.printf("Your number is %d.", l);
        out.close();
    }

}
