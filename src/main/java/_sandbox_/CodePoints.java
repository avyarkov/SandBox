package _sandbox_;

public class CodePoints {
    public static void main(String[] args) {
        String string = "a\uD83D\uDF1B︽\uD83D\uDF0B";
        System.out.println("string = " + string);
        System.out.println("string.chars():");
        string.chars().forEach(System.out::println);
        System.out.println("string.codePoints():");
        string.codePoints().forEach(System.out::println);
        System.out.println("string.codePointAt(i):");
        for (int i = 0; i < string.length(); i++) {
            System.out.println(string.codePointAt(i));
        }
        System.out.println("string.offsetByCodePoints(0, i)");
        for (int i = 0; i < string.codePointCount(0, string.length()); i++) {
            System.out.println(string.offsetByCodePoints(0, i));
        }
    }
}
