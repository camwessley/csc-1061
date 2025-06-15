/*  Name:       Cameron Wessley
    Date:       6/7/2025
    Program:    Chapter 10 Programming Project
    Description:
                This program re-implements a handful of methods from the String class.

    Inputs:     None
    Outputs:    Results of test cases
 */

public class MyStringNew {

    // Private String
    private final String str;

    // Constructor accepting a String
    public MyStringNew(String s) {
        this.str = s;
    }

    // Method to convert boolean value to new MyStringNew object
    public static MyStringNew valueOf(boolean b) {
        return new MyStringNew(b ? "true" : "false");
    }

    // Method to lexicographically compare two Strings
    public int compare(String s) {
        return str.compareTo(s);
    }

    // Method to return a substring of the String as a new MyStringNew object
    public MyStringNew substring(int beg) {
        return new MyStringNew(str.substring(beg));
    }

    // Method to convert the String to lowercase
    public MyStringNew toUpperCase() {
        return new MyStringNew(str.toUpperCase());
    }

    // Method to convert the string to a char array
    public char[] toChars() {
        return str.toCharArray();
    }
}

class MSNTest {
    public static void main(String[] args) {

        MyStringNew msn = new MyStringNew("I love fish");

        // Test valueOf()
        System.out.println(MyStringNew.valueOf(true).toChars());

        // Test compare()
        System.out.println(msn.compare("I love fish"));
        System.out.println(msn.compare("I hate fish"));

        // Test substring()
        System.out.println(msn.substring(5).toChars());

        // Test toUpperCase()
        System.out.println(msn.toUpperCase().toChars());

        // Test toChars()
        System.out.println(msn.toChars());
    }
}