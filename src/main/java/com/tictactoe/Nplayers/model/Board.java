
package com.tictactoe.Nplayers.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Board implementation supporting N players.
 * Uses per-player counters for scalable winner detection.
 */
public class Board {

    private int size;
    private char[][] grid;

    private Map<Character, int[]> rowCount = new HashMap<>();
    private Map<Character, int[]> colCount = new HashMap<>();
    private Map<Character, Integer> diagCount = new HashMap<>();
    private Map<Character, Integer> antiDiagCount = new HashMap<>();

    public Board(int size) {
        this.size = size;
        grid = new char[size][size];
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                grid[i][j] = '-';
    }

    public boolean makeMove(int row, int col, char symbol) {

        if (row < 0 || col < 0 || row >= size || col >= size)
            return false;

        if (grid[row][col] != '-')
            return false;

        grid[row][col] = symbol;

        rowCount.putIfAbsent(symbol, new int[size]);
        colCount.putIfAbsent(symbol, new int[size]);
        diagCount.putIfAbsent(symbol, 0);
        antiDiagCount.putIfAbsent(symbol, 0);

        rowCount.get(symbol)[row]++;
        colCount.get(symbol)[col]++;

        if (row == col)
            diagCount.put(symbol, diagCount.get(symbol) + 1);

        if (row + col == size - 1)
            antiDiagCount.put(symbol, antiDiagCount.get(symbol) + 1);

        return rowCount.get(symbol)[row] == size ||
               colCount.get(symbol)[col] == size ||
               diagCount.get(symbol) == size ||
               antiDiagCount.get(symbol) == size;
    }
}
