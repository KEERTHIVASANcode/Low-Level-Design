
package com.tictactoe.twoplayers.strategy;

import java.util.Scanner;
import com.tictactoe.twoplayers.model.Board;

/**
 * Console-based move input strategy.
 */
public class ConsoleMoveStrategy implements MoveStrategy {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public int[] nextMove(Board board) {
        System.out.println("Enter row and column:");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        return new int[]{row, col};
    }
}
