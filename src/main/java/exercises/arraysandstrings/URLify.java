package exercises.arraysandstrings;

/**
 * Write a method to replace all spaces in a string with '%20'. You may assume that the string has sufficient space at
 * the end to hold the additional characters, and that you are given the "true" length of the string. <br>
 * EXAMPLE:<br>
 * Input: "Mr John Smith    ", 13<br>
 * Output: "Mr%20John%20Smith"
 *
 * @author emanno
 * @version 1.0 Aug 4, 2017
 */
public class URLify {

    public static void main(String[] args) {
        String s = "Mr John Smith    ";
        char[] chars = s.toCharArray();
        urlify(chars, 13);
        System.out.println(new String(chars));

        s = "Urlify this as well please!        ";
        chars = s.toCharArray();
        urlify(chars, 27);
        System.out.println(new String(chars));
    }


    public static void urlify(char[] input, int trueLength) {
        int spaceCount = 0;

        // determine how many spaces there are in char array
        for (int i = 0; i < trueLength; i++) {
            if (input[i] == ' ')
                spaceCount++;
        }

        // determine index at which to start writing into char array
        int index = (trueLength + spaceCount * 2) - 1;

        for (int i = trueLength - 1; i >= 0; i--) {
            if (input[i] == ' ') {
                input[index] = '0';
                input[index - 1] = '2';
                input[index - 2] = '%';
                index -= 3;
            } else {
                input[index] = input[i];
                index--;
            }
        }
    }

}
