
package com.tictactoe.twoplayers.strategy;

import com.tictactoe.twoplayers.model.Board;

/**
 * Strategy interface for move generation.
 */
public interface MoveStrategy {
    int[] nextMove(Board board);
}
