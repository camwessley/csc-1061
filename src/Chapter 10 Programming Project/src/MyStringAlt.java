/*  Name:       Cameron Wessley
    Date:       6/7/2025
    Program:    Chapter 10 Programming Project
    Description:
                This program re-implements a handful of methods from the String class.

    Inputs:     None
    Outputs:    Results of test cases
 */

public class MyStringAlt {

    // Private array in which to store chars
    private final char[] charArray;

    // Constructor accepting an array of chars
    public MyStringAlt(char[] chars) {
        this.charArray = chars;
    }

    // Method to convert an integer into an array of single chars containing each digit place of the integer
    public static MyStringAlt valueOf(int i) {

        // Second temporary var of input int and set digit places to zero
        int tmp = i;
        int places = 0;

        // Until temp var is equal to zero, increment places by one and divide integer in temp var by 10 to push the
        // digit of concern one place to the left, which removes the last digit of the integer
        do {
            places++;
            tmp /= 10;
        } while (tmp != 0);

        // Initialize a new char array with length equal to the number of digit places
        char[] arr = new char[places];

        // For each digit place
        for (int j = 0; j < places; j++) {

            // Convert the last digit of the integer (useful modulus operator) into a literal char, and insert it into
            // its digit place from the right of the array; this is necessary because the number in the resulting array
            // will be backwards otherwise
            arr[places - j - 1] = Character.forDigit(i % 10, 10);

            // Push digit of concern one place to the left by removing the last digit in the integer
            i /= 10;
        }

        // Return the new MyStringAlt object containing the converted char array
        return new MyStringAlt(arr);
    }

    // Method to return char at index of the char array
    public char charAt(int index) {
        return charArray[index];
    }

    // Method to return length of the char array
    public int length() {
        return charArray.length;
    }

    // Method to return a substring of the char array as a new MyStringAlt object
    public MyStringAlt substring(int beg, int end) {

        // Create a temporary char array with a length of the specified 'substring'
        char[] tempArray = new char[end - beg];

        // Copy only the specified substring length of the char array to the new temp char array using arraycopy
        System.arraycopy(charArray, beg, tempArray, 0, end - beg);

        // Return new MyStringAlt containing the 'substring' as a char array
        return new MyStringAlt(tempArray);
    }

    // Method to convert the char array to lowercase
    public MyStringAlt toLowerCase() {

        // Temporary char array for to-lowercase chars
        char[] tmpArray = new char[this.length()];

        // For each index in char array
        for (int i = 0; i < this.length(); i++) {

            // Set char in temp char array to lowercase of char at the same index
            char c = this.charAt(i);
            tmpArray[i] = Character.toLowerCase(c);
        }

        // Return new MyStringAlt containing the lowercase char array
        return new MyStringAlt(tmpArray);
    }

    // Method to test whether supplied MyStringAlt is equal to this MyStringAlt
    public boolean equals(MyStringAlt s) {

        // If the lengths are different, then they simply are not equal, so return false
        if (this.length() != s.length()) {
            return false;
        }

        // Test whether each char in supplied MyStringAlt is equal to the char at the same index of this MyStringAlt,
        // and if they are not equal, return false
        for (int i = 0; i < this.length(); i++) {
            if (this.charAt(i) != s.charAt(i)) {
                return false;
            }
        }

        // Both MyString Alt objects are of the same length and contain equal chars, so they must be equal; return true
        return true;
    }
}

class MSATest {
    public static void main(String[] args) {

        // Testing methods

        // Create some constants for demonstrating functionality
        final String TEST_STRING = "One fish, Two fish, Red fish, Blue fish!";
        final int TEST_INT = 32768;
        final char[] TEST_CHARS = TEST_STRING.toCharArray();

        // Test valueOf()
        System.out.println("Converting test integer " + TEST_INT + " to MyStringAlt:");
        MyStringAlt msa = MyStringAlt.valueOf(TEST_INT);
        System.out.print("\t" + msa.getClass() + ": ");
        for (int i = 0; i < msa.length(); i++) {
            System.out.print(msa.charAt(i));
        }

        // Test charAt()
        msa = new MyStringAlt(TEST_CHARS);
        System.out.print("\n\nChars at indexes 0, 1, 2, 4, 8, 16, and 32 of test char array \"" + TEST_STRING + "\":\n\t");
        for (int i : new int[]{0, 1, 2, 4, 8, 16, 32}) {
            System.out.print(msa.charAt(i));
        }

        // Test length()
        System.out.println("\n\nLength of test char array \"" + TEST_STRING + "\":");
        System.out.println("\t" + msa.length());

        // Test substring()
        MyStringAlt sub = msa.substring(10, 18);
        System.out.print("\nSubstring at range 10-18:\n\t");
        for (int i = 0; i < sub.length(); i++) {
            System.out.print(sub.charAt(i));
        }

        // Test toLowerCase()
        System.out.print("\n\nLowercase of test char array \"" + TEST_STRING + "\":\n\t");
        MyStringAlt low = msa.toLowerCase();
        for (int i = 0; i < low.length(); i++) {
            System.out.print(low.charAt(i));
        }

        // Test equals()
        MyStringAlt neq = new MyStringAlt("Red fish, Blue fish, One fish, Two fish!".toCharArray());
        System.out.println("\n\nTest if MyStringAlt is equal to test string \"Red fish, Blue fish, One fish, Two fish!\" (should be false):\t");
        System.out.println("\t" + msa.equals(neq));
        System.out.println("\nTest if MyStringAlt is equal to an identical object (should be true):\t");
        System.out.println("\t" + msa.equals(new MyStringAlt(TEST_CHARS)));
    }
}