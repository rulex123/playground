package exercises.string;

/**
 * Implement strStr(). Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of
 * haystack.
 *
 * @author emanno
 * @version 1.0 Sep 16, 2017
 */
public class StrStr {

    public static void main(String[] args) {
        System.out.println(strStr("the cat is under the table", "cat"));
        System.out.println(strStr("the dog is under the table", "cat"));
        System.out.println(strStr("aaS", "aS"));
    }


    public static int strStr(String haystack, String needle) {
        if (haystack == null || (needle == null || needle.isEmpty()))
            return -1;

        int needleIndex = 0;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(needleIndex)) {
                needleIndex++;
                if (needleIndex == needle.length()) {
                    return i - needle.length() + 1;
                }
            } else {
                i -= needleIndex;
                needleIndex = 0;
            }
        }
        return -1;
    }

}
