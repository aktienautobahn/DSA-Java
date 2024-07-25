import java.util.InputMismatchException;
import java.util.LinkedList;

/**
 * This class represents a generic TicTacToe game board.
 */
public class Board {
    private int n;
    private int[][] boardMatrix;
    private int nStones;

    /**
     *  Creates Board object, am game board of size n * n with 1<=n<=10.
     */
    public Board(int n)
    {
        if (validBoard(n)) {
            this.n = n; // store the size of the matrix
            boardMatrix = new int[n][n];// initializing an 2D-array of N x N fields
        }   else {
            throw new InputMismatchException("n must be greater than or equals 1 and less than or equals 10");
        }
    }


//    /**
//     * Copy constructor for the Board class. Creates a deep copy of the specified Board object.
//     * @param other The Board object to copy.
//     */
//    public Board(Board other) {
//        this.n = other.n; // Copy the size of the board
//        this.nStones = other.nStones; // Copy the number of stones/moves
//        this.boardMatrix = new int[n][n]; // Create a new matrix of the same size
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                this.boardMatrix[i][j] = other.boardMatrix[i][j]; // Copy each element
//            }
//        }
//    }


    /**
     * Helper method to validate board size.
     * @param n Size of the board to validate
     * @return true if the board size is valid, false otherwise.
     */
    private boolean validBoard(int n)
    {
        if (n >= 1 && n <= 10) return true;

        return false;
    }

    private boolean validPos(Position pos) {
        if (pos.x < 0 || pos.x >= this.n) return false;
        if (pos.y < 0 || pos.y >= this.n) return false;

        return true;
    }
    /**
     *  @return     length/width of the Board object
     */
    public int getN() { return this.n; }
    
    /**
     *  @return     number of currently free fields
     */
    public int nFreeFields() {
        int count = 0; //
        for (int x = 0; x < this.n; x++) {
            for (int y = 0; y < this.n; y++) {
                if (this.boardMatrix[x][y] == 0) count++;
            }
        }
        return count;
    }
    
    /**
     *  @return     token at position pos
     */
    public int getField(Position pos) throws InputMismatchException
    {
        if (validPos(pos)) { // check if position is inside the board
            return this.boardMatrix[pos.x][pos.y];

        } else {
            throw new InputMismatchException("Position is invalid");
        }


    }

    /**
     *  Sets the specified token at Position pos.
     */    
    public void setField(Position pos, int token) throws InputMismatchException
    {
        if (token != 1 && token != -1 && token != 0) {         // check if token is valid
            throw new InputMismatchException("Token is invalid");
        }

        if (!validPos(pos)) { // check if position is inside the board
            throw new InputMismatchException("Position is invalid");
        }

//        if (getField(pos) != 0) {         // check if position is empty
//            throw new InputMismatchException("Position is not empty");
//        }

        try {
            this.boardMatrix[pos.x][pos.y] = token;
            this.nStones++;             // if successful -> add nStones;
        } catch (Exception e) {
            // Exception
            throw new RuntimeException("An error occurred while setting token to the declared position", e);

        }

    }
    
    /**
     *  Places the token of a player at Position pos.
     */
    public void doMove(Position pos, int player)
    {
        int token = (player == 1) ? 1 : -1; // assigns the player's number to token
        if (getField(pos) == 0) {
            setField(pos, token);
//            isGameWon(); // check win condition
        } else {
            throw new InputMismatchException("Set field used");
        }
    }

    /**
     *  Clears board at Position pos.
     */
    public void undoMove(Position pos)
    {
        if (validPos(pos) && getField(pos) != 0) { // check if position is inside the board
            this.boardMatrix[pos.x][pos.y] = 0;

        } else {
            throw new InputMismatchException("Position is invalid");
        }

    }
    
    /**
     *  @return     true if game is won, false if not
     */
    public boolean isGameWon() {
        // Check columns and rows
        for (int i = 0; i < this.n; i++) {
            boolean columnHasWinner = true;
            boolean rowHasWinner = true;
            for (int j = 1; j < this.n; j++) {
                columnHasWinner &= (boardMatrix[j][i] == boardMatrix[j-1][i]) && (boardMatrix[j][i] != 0);
                rowHasWinner &= (boardMatrix[i][j] == boardMatrix[i][j-1]) && (boardMatrix[i][j] != 0);
            }
            if (columnHasWinner || rowHasWinner) {
                return true;
            }
        }

        // Check diagonals
        boolean diagLTRHasWinner = true;
        boolean diagRTLHasWinner = true;
        for (int j = 1; j < this.n; j++) {
            diagLTRHasWinner &= (boardMatrix[j][j] == boardMatrix[j-1][j-1]) && (boardMatrix[j][j] != 0);
            diagRTLHasWinner &= (boardMatrix[this.n - j - 1][j] == boardMatrix[this.n - j][j-1]) && (boardMatrix[this.n - j - 1][j] != 0);
        }

        return diagLTRHasWinner || diagRTLHasWinner;
    }

    /**
     *  @return     set of all free fields as some Iterable object
     */
    public Iterable<Position> validMoves()
    {
        LinkedList<Position> allEmptyFields = new LinkedList<>();
        for (int y = 0; y < this.n; y++) {
            for (int x = 0; x < this.n; x++) {
                Position emptyCandidate = new Position(x, y);
                if (getField(emptyCandidate) == 0) {
                    allEmptyFields.add(emptyCandidate);
                }
            }
        }
        return allEmptyFields;
    }

    /**
     *  Outputs current state representation of the Board object.
     *  Practical for debugging.
     */
    public void print()
    {
        for (int y = 0; y < this.n; y++) {
            for (int x = 0; x < this.n; x++) {
                if (this.boardMatrix[x][y] == -1) {
                    System.out.print("o");
                } else if (this.boardMatrix[x][y] == 1) {
                    System.out.print("x");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println();

    }

}

