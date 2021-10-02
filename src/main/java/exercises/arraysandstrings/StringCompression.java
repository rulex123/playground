package exercises.arraysandstrings;

/**
 * Implement a method to perform basic string compression using the counts of repeated characters. For example, the
 * string aabcccccaaaa would become a2b1c5a3. If the "compressed" string would not become smaller than your original
 * string, your method should return the original string. You can assume the original string has only uppercase and
 * lowercase letters (a-z).
 *
 * @author emanno
 * @version 1.0 Aug 7, 2017
 */
public class StringCompression {

    public static void main(String[] args) {
        StringCompression unit = new StringCompression();
        System.out.println(unit.compress("aabcccccaaaz"));
    }


    private String compress(String s) {
        StringBuilder sb = new StringBuilder();

        int charCount = 0;
        for (int i = 0; i < s.length(); i++) {
            charCount++;

            if ((i + 1) >= s.length() || (s.charAt(i) != s.charAt(i + 1))) {
                sb.append(s.charAt(i));
                sb.append(charCount);
                // reset counter for char
                charCount = 0;
            }

        }

        return sb.length() == s.length() ? s : sb.toString();
    }

}
