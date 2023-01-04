package _sandbox_;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LinesToSpacesUsingStreamGenerate {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println(Stream.generate(in::next).limit(in.nextInt()).collect(Collectors.joining(" ")));

        in.close();
    }
}
