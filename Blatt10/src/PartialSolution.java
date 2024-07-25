import java.util.*;

/**
 * ParitialSolution provides at least the functionality which is required
 * for the use in searching for solutions of the game in a search tree.
 * It can store a game situation (Board) and a sequence of mooves.
 */
public class PartialSolution {
    private Board boardCopy;
    private LinkedList<Move> moveList;


    /**
     * Constructor, generates an empty solution based on the provided
     * <em>board</em> with an empty move sequence.
     *
     * @param board initial state of the board
     */
    public PartialSolution(Board board) {
        this.boardCopy = new Board(board);
        this.moveList = new LinkedList<>();
    }
    /**
     * Copy constructor, generates a deep copy of the input
     *
     * @param that The partial solution that is to be copied
     */
    public PartialSolution(PartialSolution that) {
        this.boardCopy = new Board(that.boardCopy);
        this.moveList = new LinkedList<>(that.moveList);

    }

    /**
     * Tests whether the solution has been reached, i.e. whether
     * current board is in the goal state.
     *
     * @return {@code true}, if the board is in goal state
     */
    public boolean isSolution() {
        return boardCopy.targetReached();
    }
    /**
     * Return the sequence of moves which resulted in this partial solution.
     *
     * @return The sequence of moves.
     */
    public LinkedList<Move> moveSequence() {
        return moveList;
    }

    public void doMove(Move move) {
        this.boardCopy.doMove(move);
        this.moveList.add(move);

    }

    public Iterable<Move> validMoves() {
        List<Move> validMoves = new ArrayList<>();
        boardCopy.validMoves().forEach(validMoves::add);
        return validMoves;
    }

    public Board getBoard() {
        return boardCopy;
    }


    @Override
    public String toString() {
        String str = "";
        int lastRobot = -1;
        for (Move move : moveSequence()) {
            if (lastRobot == move.iRobot) {
                str += " -> " + move.endPosition;
            } else {
                if (lastRobot != -1) {
                    str += ", ";
                }
                str += "R" + move.iRobot + " -> " + move.endPosition;
            }
            lastRobot = move.iRobot;
        }
        return str;
    }
}

