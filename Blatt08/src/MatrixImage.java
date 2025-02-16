import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MatrixImage implements Image {
    public int[][] field;
    
    /**
     * A Constructor for the MatrixImage class
     * @param sx the size on the x-axis
     * @param sy the size on the y-axis
     */
    public MatrixImage(int sx, int sy) {
        field = new int[sx][sy];
    }

    /**
     * A deep dopy constructor for a given MatrixImage that
     * @param that the to be copied MatrixImage
     */
     public MatrixImage(MatrixImage that) {
        this(that.sizeX(), that.sizeY());
        for (int x = 0; x < sizeX(); x++) {
            field[x] = that.field[x].clone();
        }
    }
    
    /**
     * Initializes a MatrixImage from a given file
     * @param filename the file
     * @throws java.io.FileNotFoundException if there is no such file
     */
    public MatrixImage(String filename) throws java.io.FileNotFoundException {
        System.setIn(new FileInputStream(filename));
        Scanner in = new Scanner(System.in);
        int sx = in.nextInt();
        int sy = in.nextInt();
        field = new int[sx][sy];
        for (int y = 0; y < sy; y++) {
            for (int x = 0; x < sx; x++) {
                field[x][y] = in.nextInt();
            }
        }
    }
    
    /**
    * @return the size on the x-axis
    */
    @Override
    public int sizeX() {
        return field.length;
    }
    
    /**
    * @return the size on the y-axis
    */
    @Override
    public int sizeY() {
        return field[0].length;
    }
    
    /**
     * Calculates the contrast between two coordinates/nodes
     * @param p0 first coordinate
     * @param p1 second coordinate
     * @return the absolute value of the contrast
     * @throws InputMismatchException if the coordinates are not in the image
     */
    @Override
    public double contrast(Coordinate p0, Coordinate p1) throws InputMismatchException {
        if (p0.x < 0 || p0.x >= sizeX() || p0.y < 0 || p0.y >= sizeY() ||
                p1.x < 0 || p1.x >= sizeX() || p1.y < 0 || p1.y >= sizeY()) {
            throw new InputMismatchException("Coordinate is out of bonds");
        }

        return Math.abs(this.field[p0.x][p0.y] - this.field[p1.x][p1.y]);
    }
    
    /**
     * Removes the given vertical path from the image.
     * Create a deep copy of the image with the correct new Matrix size.
     * @param path the do be deleted vertical path
     */
    @Override
    public void removeVPath(int[] path) {
        // edge cases for empty path or matrix
        if (path.length == 0 || field.length == 0 || field[0].length == 0) return;


        MatrixImage deepCopy = new MatrixImage(this);

        for (int col = 0; col < path.length; col++) { // for each path index (for each x)
            // iterate over y, if not last row
            if (col < deepCopy.sizeY()) { // ensure the column index is within bounds
                int startRow = path[col];
                if (startRow < deepCopy.sizeX() - 1) { // double check: ensure the row is within bounds
                    for (int row = startRow; row < deepCopy.sizeX() - 1; row++) { // start with the picked row and go down till the pre last row
                        deepCopy.field[row][col] = deepCopy.field[row + 1][col];
                    }
                }
            }
        }
        // copy to a temp array
        int[][] temp_array = new int[deepCopy.sizeX() - 1][deepCopy.sizeY()];
        for (int y = 0; y < deepCopy.sizeX() - 1; y++) {
            temp_array[y] = deepCopy.field[y].clone();
        }

        // copy temp array to a object variable
        this.field = temp_array;

    }

    @Override
    public String toString() {
        String str = "";
        for (int y = 0; y < sizeY(); y++) {
            for (int x = 0; x < sizeX(); x++) {
                str += field[x][y] + " ";
            }
            str += "\n";
        }
        return str;
    }

    @Override
    public void render() {
        System.out.println(toString());
    }

}

