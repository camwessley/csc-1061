/*  Name:       Cameron Wessley
    Date:       6/5/2025
    Program:    Chapter 9 Programming Project
    Description:
                This program contains a LinearEquation class, which models a 2x2 system of linear equations and its
                solution. It can calculate both the solution and determinant, as well as evaluate whether the system has
                no solution.

    Inputs:     a, b, c, d, e, f coefficients
    Outputs:    Solution (x, y) and determinant if solvable; else, no solution message
 */

import java.util.Scanner;

// The class containing the main() method must share the same name as the Java file
public class Chap9_PP_Wessley {
    public static void main(String[] args) {

        // Scanner for user input
        Scanner sc = new Scanner(System.in);

        // Prompt user to enter coefficients for solving the system of equations
        System.out.println("Enter coefficients a, b, c, d, e, f for the two equations:");
        System.out.print("a: ");
        double a = sc.nextDouble();
        System.out.print("b: ");
        double b = sc.nextDouble();
        System.out.print("c: ");
        double c = sc.nextDouble();
        System.out.print("d: ");
        double d = sc.nextDouble();
        System.out.print("e: ");
        double e = sc.nextDouble();
        System.out.print("f: ");
        double f = sc.nextDouble();

        // Instantiate a new LinearEquation object from user defined coefficients
        LinearEquation equation = new LinearEquation(a, b, c, d, e, f);

        // Print the equation to the terminal in human-readable, algebraic format
        System.out.printf("\n%.2fx + %.2fy = %.2f\n%.2fx + %.2fy = %.2f", a, b, e, c, d, f);

        // If the equation is not solvable, inform the user and end the program
        if (equation.notSolvable()) {
            System.out.println("The equation has no solution");
        } else {

            // The system is solvable, so retrieve and print the x- and y-values of the solution
            System.out.printf("\n\nSolution: x = %.2f, y = %.2f%n", equation.getX(), equation.getY());

            // Print determinant
            System.out.printf("Determinant: %.2f%n", equation.getDeterminant());
        }

        // Close scanner for cleanliness
        sc.close();
    }
}

// This could easily be turned into a record class, which is far cleaner and does not carry unnecessary baggage
class LinearEquation {

    // Declare instance vars for coefficients
    private double a, b, c, d, e, f;

    // Constructor to initialize instance vars
    public LinearEquation(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    // Getters for all coefficient vars
    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    public double getE() {
        return e;
    }

    public double getF() {
        return f;
    }

    // Getter method for determinant, simply return a * d - b * c
    public double getDeterminant() {
        return a * d - b * c;
    }

    /*  Boolean method to determine if the system is not solvable; typical behavior results in logical double negative, so I
        would personally change it to isSolvable() { return getDeterminant() != 0; } for cleanliness
     */
    public boolean notSolvable() {
        return getDeterminant() == 0;
    }

    // Method to return the X solution
    public double getX() {
        return (e * d - b * f) / getDeterminant();
    }

    // Method to return the Y solution
    public double getY() {
        return (a * f - c * e) / getDeterminant();
    }
}