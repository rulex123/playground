package exercises.string;

public class ReverseString {

    public static void main(String[] args) {
        String myString = "Hello, there!";
        ReverseString unit = new ReverseString();
        System.out.println(unit.reverseStringInPlace(myString.toCharArray()));
        System.out.println(unit.reverseStringUsingRecursion(myString, 12));

    }


    private char[] reverseStringInPlace(char[] input) {
        for (int i = 0, j = input.length - 1; i < j; i++, j--) {
            // swap char
            char temp = input[i];
            input[i] = input[j];
            input[j] = temp;
        }

        return input;
    }


    private String reverseStringUsingRecursion(String input, int index) {
        if (index == 0) {
            return Character.toString(input.charAt(0));
        } else {
            return input.charAt(index) + this.reverseStringUsingRecursion(input, --index);
        }
    }

}
