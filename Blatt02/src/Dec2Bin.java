import java.util.ListIterator;
import java.util.Stack;
/** * A class for constructing a Decimal-to-Binary Number- Converter; * contains a main method for demonstration. */
public class Dec2Bin {

    public Stack<Integer> binStack;  // We make it public to modify it in our tests.
    private int N;

    /**
     * Constructor of an empty object. Use method {@code convert()} to convert a number.
     */
    public Dec2Bin() {
        binStack = new Stack<>();
    }

    /**
     * Returns the number that is converted as {@code int}.
     *
     * @return the converted number
     */
    public int getN() {
        return N;
    }

    /**
     * Converts the given number into binary format, with each digit being represented in a
     * stack of {@code int}.
     *
     * @param N the number that is to be converted.
     */
    public void convert(int N) {
        this.N = N;
        this.binStack.clear(); // clearing old stack data
        int tempN = N;
        // TODO implement this method
        if (tempN == 0) { // edge case for 0
            this.binStack.push(0);
        }
        while(tempN > 0) {
            int reminder = tempN % 2;
            this.binStack.push(reminder);
            tempN = tempN / 2;
        }
    }


    /**
     * Returns the digits that are stored in {@code binStack} as a string. To is the binary format of the
     * converted number.
     * For testing purpose, we require that the function works also, if the variable {@code binStack} is
     * modified externally.
     *
     * @return a string representation of the number in binary format.
     */
    @Override
    public String toString() {
        // Caution: Stack.toString() does NOT respect stack order. Do not use it.
        // TODO implement this method
        StringBuilder outputString = new StringBuilder();
        ListIterator<Integer> iterator = this.binStack.listIterator(this.binStack.size());
        while (iterator.hasPrevious()) {
            Integer value = iterator.previous();
            outputString.append(value);

        }
        return outputString.toString();
    }

    public static void main(String[] args) {
        Dec2Bin dec2bin = new Dec2Bin();
        for (int i = 0; i < 100; i++) {
            dec2bin.convert(i);
            System.out.println("Die Zahl " + dec2bin.getN() + " in Binärdarstellung: " + dec2bin);
            // Do it another time to demonstrate that toString does not erase the binStack.
            System.out.println("Die Zahl " + dec2bin.getN() + " in Binärdarstellung: " + dec2bin);
        }
    }
}

