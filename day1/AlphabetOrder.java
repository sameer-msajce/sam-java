import java.util.Scanner;

public class AlphabetOrder {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter first character: ");
        char ch1 = input.next().charAt(0);

        System.out.print("Enter second character: ");
        char ch2 = input.next().charAt(0);

        if (ch1 < ch2) {
            System.out.println(ch1 + "," + ch2);
        } else if (ch2 < ch1) {
            System.out.println(ch2 + "," + ch1);
        } else {
            System.out.println("Both characters are the same: " + ch1);
        }

        input.close();
    }
}
