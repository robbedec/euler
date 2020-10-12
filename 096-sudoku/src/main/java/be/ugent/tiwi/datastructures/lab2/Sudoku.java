package be.ugent.tiwi.datastructures.lab2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author sleroux, robbedec
 */
public class Sudoku {
    private final int[][] grid = new int[9][9];

    public Sudoku(String filename) throws FileNotFoundException, IOException {
        try(Scanner s = new Scanner(new FileReader(filename))){
            for (int i=0; i<9; i++){
                for (int j=0; j<9; j++){
                    grid[i][j] = s.nextInt();
                }
            }
        }
    }
    
    private class Tuple {
        int row, column;

        public Tuple(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    public int getTopThreeElements() {
        int number = 0;
        for (int i = 0; i < 3; ++i) {
            number += grid[0][i];

            if (i != 2) {
                number *= 10;
            }
        }

        return number;
    }

    private Tuple findNextZero() {
        int row = 0, column = 0;
        // Find next 0
        for (int i = row; i < grid.length; ++i) {
            for (int j = column; j < grid.length; ++j) {
                if (grid[i][j] == 0) {
                    return new Tuple(i, j);
                }
            }
        }

        return null;
    }

    private boolean isSafe(int row, int column, int number) {
        // Check if row contains number
        for (int i = 0; i < grid.length; ++i) {
            if (grid[i][column] == number) {
                return false;
            }
        }

        // Check if column contains number
        for (int i = 0; i < grid.length; ++i) {
            if (grid[row][i] == number) {
                return false;
            }
        }

        // Check if current box contains number
        int boxStartRow = row - (row % 3);
        int boxStartCol = column - (column % 3);
        for (int i = boxStartRow; i < boxStartRow + 3; ++i) {
            for (int j = boxStartCol; j < boxStartCol + 3; ++j) {
                if (grid[i][j] == number) {
                    return false;
                }
            }
        }

        // System.out.printf("INDECES row: %d, column: %d\n", row, column);
        // System.out.printf("START row: %d, column: %d\n", boxStartRow, boxStartCol);

        return true;
    }
    
    public boolean solve(){
        if (isSolved()) {
            return true;
        }

        // System.out.printf("row: %d, column: %d\n", row, column);
        Tuple nextZero = findNextZero();
        for (int i = 1; i < 10; ++i) {
            if (isSafe(nextZero.row, nextZero.column, i)) {
                // System.out.printf("SAFE %d\n", i);
                grid[nextZero.row][nextZero.column] = i;
                if (solve()) {
                    return true;
                } else {
                    grid[nextZero.row][nextZero.column] = 0;
                }
            }
        }

        return false;
    }
    
    public boolean isSolved(){
        // Check rows
        for (int i = 0; i < grid.length; ++i) {
            if (Arrays.stream(grid[i]).distinct().count() != 9 || Arrays.stream(grid[i]).sum() != 45) {
                // row does not contain 9 unique elements or doesn't total up to 45
                return false;
            }
        }

        // Check column
        HashSet<Integer> col = new HashSet<>();
        int count = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid.length; ++j) {
                col.add(grid[i][j]);
                count += grid[i][j];
            }

            if (col.size() != 9 || count != 45) {
                return false;
            }
            col.clear();
            count = 0;
        }

        // Check if every 3x3 contains all the numbers
        HashSet<Integer> items = new HashSet<>();
        for (int row_start = 0; row_start < 9; row_start += 3) {
            for (int col_start = 0; col_start < 9; col_start += 3) {
                for (int r = row_start; r < row_start + 3; ++r) {
                    for (int c = col_start; c < col_start + 3; ++c) {
                        //System.out.printf("row: %d, column: %d\n", r, c);
                        items.add(grid[r][c]);
                    }
                }
                if (items.size() != 9) {
                    return false;
                }
                items.clear();
                //System.out.println("\n");
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                sb.append(grid[i][j]).append(" ");

                if ((j+1) % 3 == 0 && j < 8)
                    sb.append("| ");
            }
            sb.append("\n");
            if ((i+1) % 3 == 0 && i < 8){
                sb.append("------+-------+------\n");
            }
        }
        
        return sb.toString();
    }
}
