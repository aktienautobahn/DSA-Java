import java.util.ArrayList;

/**
 * This class implements a game of Row of Bowls.
 * For the games rules see Blatt05. The goal is to find an optimal strategy.
 */
public class RowOfBowls {
    int [] bowls;
    int [][] memoUpdated; // bloom filter array
    int [][] memo; // value array

    public RowOfBowls() {
    }

    /**
     * Implements an optimal game using dynamic programming
     * @param values array of the number of marbles in each bowl
     * @return number of game points that the first player gets, provided both parties play optimally
     */
    public int maxGain(int[] values)
    {

        if (values == null || values.length == 0) return 0;
        bowls = values; // used in optimal sequence
        int n = values.length;
        memo = new int[n][n];
        memoUpdated = new int[n][n];
        int headIndex = 0;
        int tailIndex = n-1;

        return maxGainOPT(values, headIndex, tailIndex);




    }

    public int maxGainOPT(int[] values, int headIndex, int tailIndex) {
        int score;

        if (this.memoUpdated[headIndex][tailIndex] == 1) return this.memo[headIndex][tailIndex];
        if (headIndex == tailIndex) {
             score = values[headIndex];
        } else {
            int pickHead = values[headIndex] - maxGainOPT(values, headIndex + 1, tailIndex);
            int pickTail = values[tailIndex] - maxGainOPT(values, headIndex, tailIndex - 1);
            score = Math.max(pickHead, pickTail);
        }
        memoUpdated[headIndex][tailIndex] = 1; // updateMemo
        memo[headIndex][tailIndex] = score; // store value in memo
        return score;
    }


    /**
     * Implements an optimal game recursively.
     *
     * @param values array of the number of marbles in each bowl
     * @return number of game points that the first player gets, provided both parties play optimally
     */
    public int maxGainRecursive(int[] values) {
        // TODO
        // initialize headIndex and tailindex
        int headIndex = 0;
        int tailIndex = values.length - 1;
        int difference = maxGainRecursive(values, headIndex, tailIndex);
        if (difference < 0) {
            System.out.println("Second player won with the difference of " + Math.abs(difference));
        } else if (difference > 0) {
            System.out.println("First player won with the difference of " + Math.abs(difference));

        } else {
            System.out.println("No winner!");
        }
        return difference;

    }


    private int maxGainRecursive(int[] values, int headIndex, int tailIndex) {
        // bruteforce approach, search for optimal solution
        // base case (bottom is reached): head > tail
        if (headIndex > tailIndex) return 0;

        int pickHead = values[headIndex] - maxGainRecursive(values, headIndex + 1, tailIndex);
        int pickTail = values[tailIndex] - maxGainRecursive(values, headIndex, tailIndex - 1);
        return Math.max(pickHead, pickTail);
    }


    /**
     * Calculates an optimal sequence of bowls using the partial solutions found in maxGain(int values)
     * @return optimal sequence of chosen bowls (represented by the index in the values array)
     */
    public Iterable<Integer> optimalSequence() {
        ArrayList<Integer> sequence = new ArrayList<>();

        int headIndex = 0;
        int tailIndex = bowls.length - 1;

        int leftChoiceValue;
        int rightChoiceValue;
        // iterate through all optimal choices
        while (headIndex < tailIndex) {
            // get the headIndex' value
            leftChoiceValue = bowls[headIndex] - memo[headIndex+1][tailIndex];
            // get the tailIndex's value
            rightChoiceValue = bowls[tailIndex] - memo[headIndex][tailIndex-1];
            // choose the max(headIndex's value, tailIndex's value) , add to sequence, increment index
            if (leftChoiceValue >= rightChoiceValue) {
                sequence.add(headIndex++);
            } else {
                sequence.add(tailIndex--);
            }
        }
        sequence.add(headIndex); // add last bowl's value
        return sequence;
    }


    public static void main(String[] args)
    {
        // For Testing
        int[] testData = {3, 4, 1, 2, 8, 5};
        RowOfBowls game = new RowOfBowls();
        int difference = game.maxGain(testData);
        System.out.println(difference);

        
        }
}

