/*  Name:       Cameron Wessley
    Date:       6/14/2025
    Program:    Chapter 11 Programming Project
    Description:
                This program demonstrates inheritance and polymorphism through hierarchical inheritance, defining a
                Person, a Student (type of Person), an Employee (type of Person), an Advisor (type of Employee), Faculty
                (a type of Employee), an Instructor (type of Employee), and Staff (type of Employee).

                Additionally, this program implements a custom date class, MyDate, based on java.util.GregorianCalendar.
                A suite of tests demonstrating functionality of the program can be found in the main() method at the end
                of the file.

    Inputs:     None
    Outputs:    Results of test cases - Each example class' type and the name of the person the class defines
 */


import java.util.GregorianCalendar;

// Custom date class utilizing GregorianCalendar

class MyDate {

    // All of these classes have assigned constants for storing member data, but most are never accessed because the
    // only data we are retrieving from a class is its NAME field - hence the use of consts

    private final int YEAR;
    private final int MONTH;
    private final int DAY;

    // GregorianCalendar is a wrapper class for abstract class java.util.Calendar, which allows accessing static vars
    // for date values via .get() method

    private final GregorianCalendar GCAL = new GregorianCalendar();

    // Default no-arg constructor which sets YEAR, MONTH, and DAY values to that of current time

    MyDate() {
        this.YEAR = GCAL.get(GregorianCalendar.YEAR);
        this.MONTH = GCAL.get(GregorianCalendar.MONTH);
        this.DAY = GCAL.get(GregorianCalendar.DAY_OF_MONTH);
    }

    // Constructor which accepts Unix timestamp in milliseconds (millis since 1970-1-1), sets GregorianCalendar's time
    // to that of the timestamp, and retrieves the date values for that time

    MyDate(long millis) {
        GCAL.setTimeInMillis(millis);
        this.YEAR = GCAL.get(GregorianCalendar.YEAR);
        this.MONTH = GCAL.get(GregorianCalendar.MONTH);
        this.DAY = GCAL.get(GregorianCalendar.DAY_OF_MONTH);
    }

    // Constructor which accepts literal date values

    MyDate(int year, int month, int day) {
        this.YEAR = year;
        this.MONTH = month;
        this.DAY = day;
    }
}

class Person {

    // Again, only NAME is accessed

    private final String NAME;
    private final String ADDR;
    private final String EMAIL;
    private final int ID;
    private final String PHONE;

    Person(String name, String address, String email, int id, String phoneNumber) {
        this.NAME = name;
        this.ADDR = address;
        this.EMAIL = email;
        this.ID = id;
        this.PHONE = phoneNumber;
    }

    public String getNAME() {
        return this.NAME;
    }

    /*
    Instead of overriding toString() with essentially the same output for each class, it is much cleaner to use one
    overridden method inherited by each class, which returns the name of the class calling it, rather than hardcoding
    the class name. For example:

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" + "name='" + this.name + '\'' + '}';
    }
    */

    @Override
    public String toString() {
        return "Person{" +
                "name='" + this.NAME + '\'' +
                '}';
    }
}

// Student class inherits fields and methods from Person class

class Student extends Person {
    private final String STATUS;

    Student(String name, String address, String email, int id, String phone, String status) {

        super(name, address, email, id, phone);

        // The correct way to avoid hard-coding in this situation would be to use an enum for Student's STATUS, but we
        // haven't covered those yet. It would be sloppy to name a bunch of constants containing a string equal to the
        // name of the constant.

        this.STATUS = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + super.getNAME() + '\'' +
                '}';
    }
}

// Employee class inherits fields and methods from Person class

class Employee extends Person {
    private final String OFFICE;
    private final int SALARY;
    private final MyDate DATE_HIRED;

    Employee(String name, String address, String email, int id, String phone,
             String office, int salary, MyDate dateHired) {

        // Call constructor of super (parent) class

        super(name, address, email, id, phone);

        this.OFFICE = office;
        this.SALARY = salary;
        this.DATE_HIRED = dateHired;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + super.getNAME() + '\'' +
                '}';
    }
}

// The instructions do not state what to include in an Advisor class, only that an object of its type must be created,
// from which to call toString()

class Advisor extends Employee {
    Advisor(String name, String address, String email, int id, String phone, String office, int salary,
            MyDate dateHired) {

        // Superclass constructor call

        super(name, address, email, id, phone, office, salary, dateHired);
    }

    @Override
    public String toString() {
        return "Advisor{" +
                "name='" + super.getNAME() + '\'' +
                '}';
    }
}

// Faculty class inherits Employee class, which inherits Person class

class Faculty extends Employee {

    // I decided to store office hours as 24-hour int time values in an array of length 2; OFFICE_HOURS[0] represents
    // starting hour, and OFFICE_HOURS[1] represents ending hour. This way, the values are more accessible.

    private final int[] OFFICE_HOURS = new int[2];
    private final int RANK;

    // Wrapping constructor args because longer than 120 chars. If your IDE replaces tab chars with spaces, this may
    // display weirdly.

    Faculty(String name, String address, String email, int id, String phone, String office, int salary,
            MyDate dateHired, int startHour, int endHour, int rank) {

        // Superclass constructor call

        super(name, address, email, id, phone, office, salary, dateHired);

        this.OFFICE_HOURS[0] = startHour;
        this.OFFICE_HOURS[1] = endHour;
        this.RANK = rank;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + super.getNAME() + '\'' +
                '}';
    }
}

// Instructor class inherits Employee class, which inherits Person class

class Instructor extends Employee {

    // Instructions for the test program state that both Faculty and Instructor classes need to have office hours
    // defined (explicit part-time or full-time). This contradicts the instructions for the class design, which state
    // that only the Faculty class has office hours, and not the Instructor class. Thus, I added office hours for
    // Instructor to meet project expectations as closely as possible; see Faculty class

    private final int[] OFFICE_HOURS = new int[2];
    private final int TIER;

    Instructor(String name, String address, String email, int id, String phone, String office, int salary,
               MyDate dateHired, int startHour, int endHour, int tier) {

        super(name, address, email, id, phone, office, salary, dateHired);

        this.OFFICE_HOURS[0] = startHour;
        this.OFFICE_HOURS[1] = endHour;
        this.TIER = tier;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "name='" + super.getNAME() + '\'' +
                '}';
    }
}

// Staff class inherits Employee class, which inherits Person class

class Staff extends Employee {
    private final String TITLE;

    Staff(String name, String address, String email, int id, String phone, String office, int salary, MyDate dateHired,
          String title) {

        super(name, address, email, id, phone, office, salary, dateHired);

        this.TITLE = title;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + super.getNAME() + '\'' +
                '}';
    }
}

// Test program demonstrating explicit toString() call on examples of each type;

public class Chap11PP_Wessley {
    public static void main(String[] args) {

        // Storing people in an array of type People, so they can be iterated through by corresponding id number, as
        // well as for cleanliness

        Person[] people = new Person[6];
        people[0] = new Person("Jason", "401 Cockatiel Ct.", "jason@sdf.org", 0,
                "(929) 299-1269");

        people[1] = new Student("Cameron", "4819 Tanager Pl.", "cam@sdf.org", 1,
                "(808) 213-8669", "junior");

        // Using explicit year/month/day for MyDate constructor
        people[2] = new Advisor("Elena", "234 Willow Ct.", "elena@sdf.org", 2,
                "(503) 264-8917", "C293", 86000, new MyDate(2018, 9, 14));

        // Full-time faculty working from 8A to 4P

        people[3] = new Faculty("Alex", "89 Harborview Dr.", "alex@sdf.org", 3,
                "(813) 405-6329", "F113", 93000, new MyDate(1689876543210L), 800,
                1600, 3);

        // Part-time faculty working from 12P to 4P

        people[4] = new Instructor("Jeremy", "1569 Maple St.", "jeremy@sdf.org", 4,
                "(847) 339-1245", "B165", 52000, new MyDate(), 1200, 1600, 5);

        // Unix timestamp for MyDate constructor

        people[5] = new Staff("Michelle", "422 Pacific Av.", "michelle@sdf.org", 5,
                "(206) 555-0183", "A104", 12000, new MyDate(1198531209275L),
                "Senior AI Architect");

        // Loop through each person and invoke their .toString() method
        for (Person p : people) {
            System.out.println(p.toString());
        }
    }
}

// 301 1749946752 #IU-251.26094.121 java-1.21.0-openjdk-amd64 ~kdx