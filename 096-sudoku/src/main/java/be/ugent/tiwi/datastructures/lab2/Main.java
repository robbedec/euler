package be.ugent.tiwi.datastructures.lab2;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sleroux, robbedec
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println(new File(".").getAbsolutePath());
            Sudoku s = new Sudoku("./lab2/lab2/1.txt");

            System.out.println("Start sudoku:");
            System.out.println(s);

            System.out.println(s.solve() ? "Found a solution for the sudoku" : "No solution found");
            System.out.println(s);
            System.out.println(s.getTopThreeElements());

            solveEuler();

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // https://projecteuler.net/problem=96
	// Solution: 24702
    private static void solveEuler() {
        int total = 0;
        for (int i = 1; i < 51; ++i) {
            StringBuilder sb = new StringBuilder("./lab2/lab2/sudokus/");
            sb.append(i);
            sb.append(".txt");

             System.out.println(sb.toString());
            try {
                Sudoku s = new Sudoku(sb.toString());
                System.out.println(s.solve());
                total += s.getTopThreeElements();

                System.out.println(s);
                System.out.println(s.getTopThreeElements());
                System.out.println();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.printf("The sum of the top left element of the 50 sudokus: %d\n", total);
    }
    
}
