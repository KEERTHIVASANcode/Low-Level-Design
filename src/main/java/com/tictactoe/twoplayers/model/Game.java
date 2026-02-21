
package com.tictactoe.twoplayers.model;

/**
 * Game class controls turn management and flow.
 */
public class Game {

    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game(Board board, Player p1, Player p2) {
        this.board = board;
        this.player1 = p1;
        this.player2 = p2;
        this.currentPlayer = p1;
    }

    public void start() {

        while (true) {

            int[] move = currentPlayer.makeMove(board);
            int row = move[0];
            int col = move[1];

            boolean valid = board.makeMove(row, col, currentPlayer.getSymbol());

            if (!valid) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            if (board.checkWinner(row, col, currentPlayer.getSymbol())) {
                System.out.println(currentPlayer.getName() + " wins!");
                break;
            }

            if (board.isFull()) {
                System.out.println("Game Draw!");
                break;
            }

            switchTurn();
        }
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
}
