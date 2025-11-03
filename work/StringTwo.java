import java.util.Scanner;

public class StringTwo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter first string: ");
        String str1 = input.nextLine();

        System.out.print("Enter second string: ");
        String str2 = input.nextLine();

        String result = str1 + " Technologies " + str2;

        System.out.println(result);

        input.close();
    }
}
