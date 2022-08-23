import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        int number = scanner.nextInt();

        if (number > string.length()) {
            System.out.println(string);
        } else {
            System.out.println(string.substring(number) + string.substring(0, number));
        }
    }
}