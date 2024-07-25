import java.util.LinkedList;
import java.util.Random;

/**
 * This class implements and evaluates game situations of a TicTacToe game.
 */
public class TicTacToe {

    /**
     * Returns an evaluation for player at the current board state.
     * Arbeitet nach dem Prinzip der Alphabeta-Suche. Works with the principle of Alpha-Beta-Pruning.
     *
     * @param board     current Board object for game situation
     * @param player    player who has a turn
     * @return          rating of game situation from player's point of view
    **/
    public static int alphaBeta(Board board, int player)
    {
        // TODO
        int initialAlpha = Integer.MIN_VALUE;
        int initialBeta = Integer.MAX_VALUE;
        int initialDepth = board.nFreeFields();  // Set the initial depth

        int score = alphaBeta(board, player, initialAlpha, initialBeta, initialDepth);

        return player == 1? score : -score; // for both winner players the score is positive

    }


    public static int alphaBeta(Board board, int player, int alpha, int beta, int depth) {

        if ((depth == 0) || (board.isGameWon())) {

            // if the last mover was -1 , then player = 1.
            // otherwise if the last mover was 1 , then player is 1.
            return evaluateBoard(board, -(player));    // reversing the player to last mover
        }

        if (player == 1) { // maximizing player
            int maxEval = Integer.MIN_VALUE;
            for (Position eachMove: board.validMoves()) {
                board.doMove(eachMove, player);
                int eval = alphaBeta(board, -player, alpha, beta, depth - 1);
                board.undoMove(eachMove);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) {
                    break; // Beta cutoff
                }

            }
            return maxEval;
        } else { // minimizing player
            int minEval = Integer.MAX_VALUE;
            for (Position eachMove: board.validMoves()) {
                board.doMove(eachMove, player);
                int eval = alphaBeta(board, -player, alpha, beta, depth - 1);
                board.undoMove(eachMove);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) {
                    break; // Alpha cutoff
                }

            }
            return minEval;
        }

    }

    private static int evaluateBoard(Board board, int lastPlayer) {

        int freeFields = board.nFreeFields();
        if (board.isGameWon()) {
            // Positive for maximizing player's win, negative if inferred -player's win
            return lastPlayer == 1 ? (freeFields + 1) : -(freeFields + 1);
        }
        return 0;  // No conclusive win yet
    }

    
    /**
     * Vividly prints a rating for each currently possible move out at System.out.
     * (from player's point of view)
     * Uses Alpha-Beta-Pruning to rate the possible moves.
     * formatting: See "Beispiel 1: Bewertung aller Zugmöglichkeiten" (Aufgabenblatt 4).
     *
     * @param board     current Board object for game situation
     * @param player    player who has a turn
    **/
    public static void evaluatePossibleMoves(Board board, int player)
    {
        // TODO
        int[][] boardMatrix = new int[board.getN()][board.getN()];
        char playerChar = (player == 1) ? 'x' : 'o';
        System.out.println("Evaluation for player '"+ playerChar + "':");
        for (Position move : board.validMoves()) {
            board.doMove(move, player);
            int eval = alphaBeta(board, -player);
            board.undoMove(move);
            boardMatrix[move.x][move.y] = -eval;
        }
        for (int y = 0; y < board.getN(); y++) {
            for (int x = 0; x < board.getN(); x++) {
                System.out.print(" ");
                Position pos = new Position(x, y);
                int originalValue = board.getField(pos);
                if (originalValue == 0) {
                    System.out.print(boardMatrix[x][y]);
                } else {
                    System.out.print(originalValue == 1? 'x': 'o');
                }

            }
            System.out.println();

        }

    }

    public static Position randomValidMove(Board board) {
        LinkedList<Position> allEmptyFields = (LinkedList<Position>) board.validMoves();
        if (allEmptyFields.isEmpty()) {
            return null; // or handle this case as needed
        }
        Random random = new Random();
        return allEmptyFields.get(random.nextInt(allEmptyFields.size()));
    }
    public static void main(String[] args)
    {
        Board board = new Board(3);
        board.doMove(randomValidMove(board), 1); // first move of a player 1 is random
        evaluatePossibleMoves(board, -1);

        board.print();
    }
}

