package practice.unsorted;

public class TestMain {

    private static void main(String... args) {

        System.out.println("Main.");

        f1("ABC");

    }

    public static void f1(String str) {
        System.out.println("f1 single.");
        System.out.println(str);
    }

    public static void f1(String... args) {
        System.out.println("f1 var args.");
        System.out.println(args[0]);
    }
}
