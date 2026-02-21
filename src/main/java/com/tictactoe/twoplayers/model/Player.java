
package com.tictactoe.twoplayers.model;

import com.tictactoe.twoplayers.strategy.MoveStrategy;

/**
 * Represents a player in 2-player Tic Tac Toe.
 * Uses Strategy pattern to determine move source.
 */
public class Player {

    private String name;
    private char symbol;
    private MoveStrategy strategy;

    public Player(String name, char symbol, MoveStrategy strategy) {
        this.name = name;
        this.symbol = symbol;
        this.strategy = strategy;
    }

    public int[] makeMove(Board board) {
        return strategy.nextMove(board);
    }

    public char getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
}
