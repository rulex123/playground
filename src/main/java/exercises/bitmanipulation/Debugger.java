package exercises.bitmanipulation;

public class Debugger {

  public static void main(String[] args) {
    System.out.println("1:" + isPowerOfTwo(1));
    System.out.println("2:" + isPowerOfTwo(2));
    System.out.println("4:" + isPowerOfTwo(4));
    System.out.println("8:" + isPowerOfTwo(8));
    System.out.println("16:" + isPowerOfTwo(16));
    System.out.println("3:" + isPowerOfTwo(3));
    System.out.println("5:" + isPowerOfTwo(5));
    System.out.println("6:" + isPowerOfTwo(6));
    System.out.println("7:" + isPowerOfTwo(7));
    System.out.println("9:" + isPowerOfTwo(9));
  }

  public static boolean isPowerOfTwo(int num) {
    return ((num & (num-1)) == 0);
  }
}
