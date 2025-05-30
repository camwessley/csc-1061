/*  Name:       Cameron Wessley
    Date:       5/29/2025
    Program:    CSC 1060 Review Project
    Description:
                This is a simplified implementation of the game Craps. It simu-
                lates two six-sided dice using Java's Math.random(), performs
                game logic according to the basic rules of Craps, determines a
                winner of the game, allows the user to play over and over in a
                loop, and outputs statistics about the game and each round.
                Additionally, the program can keep track of multiple users and
                their names to display per-player data.

    Inputs:     Player names and whether to continue playing after end of round via STDIN
    Outputs:    User-readable game info via STDOUT
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // instantiate variables
        Scanner input = new Scanner(System.in);

        Roll roll;
        ArrayList<Roll> rolls;  // per-round rolls
        ArrayList<Round> rounds = new ArrayList<>(); // log of all rounds

        int point;
        String playerName;
        GameOutcome outcome;
        int wins = 0;
        int doubleWins = 0;
        int losses = 0;

        // main game loop
        while (true) {

            System.out.print("\nWhat is your name?\n> ");
            playerName = input.nextLine();

            // initial come-out roll
            System.out.printf("\n%s's come-out roll:\n\n", playerName);

            roll = new Roll(); // roll dice
            rolls = new ArrayList<>(); // reinstantiate roll tracker as empty arraylist
            rolls.add(roll); // add this roll to roll tracker
            System.out.printf("%s rolls %d\n%s%s", playerName, roll.getSum(), roll.d1.getFace(), roll.d2.getFace());

            // immediate loss
            if (roll.getSum() == 2 || roll.getSum() == 3 || roll.getSum() == 12) {
                System.out.println("\nCraps!");
                losses++;
                outcome = GameOutcome.LOSS;
            } else if (roll.getSum() == 7 || roll.getSum() == 11) { // immediate win
                System.out.println("\nNatural win!");
                wins++;
                outcome = GameOutcome.WIN;
            } else {

                // subsequent point phase rolls
                point = roll.getSum();

                while (true) {

                    // instructions state not to prompt user between point phase rolls
                    System.out.printf("\nPoint: %d", point);

                    roll = new Roll(); // reroll dice
                    rolls.add(roll);
                    System.out.printf("\n%s rolls %d\n%s%s", playerName, roll.getSum(), roll.d1.getFace(), roll.d2.getFace());

                    // special rare win
                    if (point % 2 == 0 && roll.d1.getValue() == point && roll.d2.getValue() == point) {
                        System.out.println("\nCongratulations! You rolled doubles matching the point and won big!");
                        doubleWins++;
                        outcome = GameOutcome.DOUBLE_WIN;
                        break;
                    } else if (roll.getSum() == point) { // user rolls point and wins
                        System.out.println("\nYou rolled the point!");
                        wins++;
                        outcome = GameOutcome.WIN;
                        break;
                    } else if (roll.getSum() == 7) { // user rolls 7 and loses
                        System.out.println("\nYou lose!");
                        losses++;
                        outcome = GameOutcome.LOSS;
                        break;
                    }
                }
            }
            // create new Round object from current round's data and append it to our list of rounds
            rounds.add(new Round(playerName, outcome, rolls.toArray(new Roll[0])));

            System.out.print("\nDo you want to play again?\n[y/n] > ");
            // just check for 'y', else quit
            if (!input.nextLine().equalsIgnoreCase("y")) {
                System.out.println("\nGoodbye!");
                break; // leave game loop
            }
        }

        input.close(); // close scanner for good practice

        for (Round round : rounds) { // for each previous round in round tracker
            System.out.printf("\nRound #%d [%s] %s:\n", rounds.indexOf(round) + 1, round.getPlayer(), round.getOutcome());
            for (Roll r : round.getRolls()) { // for each roll in the round's roll tracker
                System.out.printf("\tRoll #%d: %s\t= %d\n", rounds.indexOf(round) + 1, r.d1.getValue() + " " + r.d2.getValue(), r.getSum());
            }
        }
        System.out.printf("\nTotals:\n\tWins: %d\n\tDouble wins: %d\n\tLosses: %d\n", wins, doubleWins, losses);
    }
}