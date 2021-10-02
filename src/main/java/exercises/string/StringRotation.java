package exercises.string;

/**
 * Assume you have a method isSubstring which checks if one word is a substring of another. Given two strings s1 and s2,
 * write code to check if s2 is a rotation of s1 using only one call to isSubstring (e.g. "waterbottle" is a rotation of
 * "erbottlewat")
 *
 * @author emanno
 * @version 1.0 Aug 14, 2017
 */
public class StringRotation {

    public static void main(String[] args) {
        System.out.println(isRotation("waterbottle", "erbottlewat"));
        System.out.println(isRotation("waterbottle", "erbottleswat"));
    }


    public static boolean isRotation(String s1, String s2) {
        return isSubstring(s1.concat(s1), s2);
    }


    private static boolean isSubstring(String container, String contained) {
        return container.contains(contained);
    }

}
