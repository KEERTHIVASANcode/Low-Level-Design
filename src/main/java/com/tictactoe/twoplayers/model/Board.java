
package com.tictactoe.twoplayers.model;

/**
 * Board class for 2-player Tic Tac Toe.
 * Maintains grid state and winner checking logic.
 */
public class Board {

    private int size;
    private char[][] grid;

    public Board(int size) {
        this.size = size;
        grid = new char[size][size];
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '-';
            }
        }
    }

    public boolean makeMove(int row, int col, char symbol) {
        if (row < 0 || col < 0 || row >= size || col >= size)
            return false;

        if (grid[row][col] != '-')
            return false;

        grid[row][col] = symbol;
        return true;
    }

    public boolean checkWinner(int row, int col, char symbol) {

        boolean win = true;

        // Check row
        for (int i = 0; i < size; i++) {
            if (grid[row][i] != symbol) {
                win = false;
                break;
            }
        }
        if (win) return true;

        // Check column
        win = true;
        for (int i = 0; i < size; i++) {
            if (grid[i][col] != symbol) {
                win = false;
                break;
            }
        }
        if (win) return true;

        // Main diagonal
        if (row == col) {
            win = true;
            for (int i = 0; i < size; i++) {
                if (grid[i][i] != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        // Anti-diagonal
        if (row + col == size - 1) {
            win = true;
            for (int i = 0; i < size; i++) {
                if (grid[i][size - i - 1] != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (grid[i][j] == '-')
                    return false;

        return true;
    }
}
