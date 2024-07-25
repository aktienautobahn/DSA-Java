import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {

    PermutationTestWrapper getWrapper(PermutationVariation p, int n){
        PermutationTestWrapper testInterface = new PermutationTestWrapper();
        testInterface.setAttirbutes(new Permutation(n),p,n,n);
        return testInterface;
    }

    private class OriginalWrongLength extends Permutation{
        public OriginalWrongLength(int N) {
            super(N);
            this.original = new int[N+1];
        }
        @Override
        public void backtracking(LinkedList<Integer> candidate) {super.backtracking(candidate);}
        @Override
        public void derangements() {super.derangements();}
    }
    @Test
    void testOriginalWrongLength(){
        PermutationVariation p = new OriginalWrongLength(6);
        PermutationTestWrapper wrapper = getWrapper(p, 6);
        assertThrows(AssertionError.class, ()->{
            wrapper.testPermutation();
        }, "PermutationTest.testPermutation() dose not check, that the original has the correct length.");
    }

    private class NumberTwice extends Permutation{
        public NumberTwice(int N) {
            super(N);
            this.original[original.length-1]=original[0];
        }
        @Override
        public void backtracking(LinkedList<Integer> candidate) {super.backtracking(candidate);}
        @Override
        public void derangements() {super.derangements();}
    }
    @Test
    void testNumberTwiceInOriginal(){
        PermutationVariation p = new NumberTwice(6);
        PermutationTestWrapper wrapper = getWrapper(p, 6);
        assertThrows(AssertionError.class, ()->{
            wrapper.testPermutation();
        }, "PermutationTest.testPermutation() dose not check, that the original only contains unique numbers");
    }

    private class DerangementsNotEmpty extends Permutation{
        public DerangementsNotEmpty(int N) {
            super(N);
            this.allDerangements.add(new int[]{5});
        }
        @Override
        public void backtracking(LinkedList<Integer> candidate) {super.backtracking(candidate);}
        @Override
        public void derangements() {super.derangements();}
    }
    @Test
    void testDerangementsNotEmpty(){
        PermutationVariation p = new DerangementsNotEmpty(6);
        PermutationTestWrapper wrapper = getWrapper(p, 6);
        assertThrows(AssertionError.class, ()->{
            wrapper.testPermutation();
        }, "PermutationTest.testPermutation() dose not check, that allDerangements is a empty list.");
    }

    private class DerangementsNull extends Permutation{
        public DerangementsNull(int N) {
            super(N);
            this.allDerangements = null;
        }
        @Override
        public void backtracking(LinkedList<Integer> candidate) {super.backtracking(candidate);}
        @Override
        public void derangements() {super.derangements();}
    }
    @Test
    void testDerangementsNull(){
        PermutationVariation p = new DerangementsNull(6);
        PermutationTestWrapper wrapper = getWrapper(p, 6);
        assertThrows(AssertionError.class, ()->{
            wrapper.testPermutation();
        }, "PermutationTest.testPermutation() dose not check, that allDerangements is not null.");
    }

    private class WrongDerangementCount extends Permutation{
        public WrongDerangementCount(int N) {super(N);}
        @Override
        public void backtracking(LinkedList<Integer> candidate) {super.backtracking(candidate);}
        @Override
        public void derangements() {
            super.derangements();
            this.allDerangements.pop();
        }
    }
    @Test
    void testWrongDerangementCount(){
        PermutationVariation p = new WrongDerangementCount(6);
        PermutationTestWrapper wrapper = getWrapper(p, 6);
        assertThrows(AssertionError.class, ()->{
            wrapper.testDerangements();
        }, "PermutationTest.testDerangements() dose not check, that the number of derangements is correct");
    }

    private class DerangementHasFixpoint extends Permutation{
        public DerangementHasFixpoint(int N) {super(N);}
        @Override
        public void backtracking(LinkedList<Integer> candidate) {super.backtracking(candidate);}
        @Override
        public void derangements() {
            super.derangements();
            for(int[] ar : this.allDerangements){
                for(int i = 0; i < ar.length; i++) {
                    ar[i] = this.original[i];
                }
                break;
            }
        }
    }
    @Test
    void testDerangementHasFixpoint(){
        PermutationVariation p = new DerangementHasFixpoint(6);
        PermutationTestWrapper wrapper = getWrapper(p, 6);
        assertThrows(AssertionError.class, ()->{
            wrapper.testDerangements();
        }, "PermutationTest.testDerangements() dose not check, that every permutation as no fixpoint.");
    }

    private class DerangementHasNonOriginalNumber extends Permutation{
        public DerangementHasNonOriginalNumber(int N) {super(N);}
        @Override
        public void backtracking(LinkedList<Integer> candidate) {super.backtracking(candidate);}
        @Override
        public void derangements() {
            super.derangements();
            for(int[] ar : this.allDerangements){
                int max = Arrays.stream(ar).max().getAsInt();
                ar[0] = max + 1;
                break;
            }
        }
    }
    @Test
    void testDerangementHasNonOriginalNumber(){
        PermutationVariation p = new DerangementHasNonOriginalNumber(6);
        PermutationTestWrapper wrapper = getWrapper(p, 6);
        assertThrows(AssertionError.class, ()->{
            wrapper.testsameElements();
        }, "PermutationTest.testsameElements() dose not check, that every derangment only cotains numbers also contained in original.");
    }

    private class DerangementHasWrongLength extends Permutation{
        public DerangementHasWrongLength(int N) {super(N);}
        @Override
        public void backtracking(LinkedList<Integer> candidate) {super.backtracking(candidate);}
        @Override
        public void derangements() {
            super.derangements();
            Object[] ar = this.allDerangements.toArray();
            LinkedList<int[]> derange = new LinkedList<int[]>();

            int[] shorted = Arrays.copyOfRange((int[]) ar[0], 0, ((int[]) ar[0]).length-1);
            derange.push(shorted);

            for(int i = 1; i < ar.length; i++){
                derange.push((int[])ar[i]);
            }

            this.allDerangements = derange;
        }
    }
    @Test
    void testDerangementHasWrongLength(){
        PermutationVariation p = new DerangementHasWrongLength(6);
        PermutationTestWrapper wrapper = getWrapper(p, 6);
        assertThrows(AssertionError.class, ()->{
            wrapper.testsameElements();
        }, "PermutationTest.testsameElements() dose not check, that every derangment has the same length as original.");
    }


    private class DerangementPermutationTwice extends Permutation{
        public DerangementPermutationTwice(int N) {super(N);}
        @Override
        public void backtracking(LinkedList<Integer> candidate) {super.backtracking(candidate);}
        @Override
        public void derangements() {
            super.derangements();
            Object[] ar = this.allDerangements.toArray();
            LinkedList<int[]> derange = new LinkedList<int[]>();

            derange.push((int[])ar[1]);

            for(int i = 1; i < ar.length; i++){
                derange.push((int[])ar[i]);
            }

            this.allDerangements = derange;
        }
    }
    @Test
    void testDerangementPermutationTwice(){
        PermutationVariation p = new DerangementPermutationTwice(6);
        PermutationTestWrapper wrapper = getWrapper(p, 6);
        assertThrows(AssertionError.class, ()->{
            wrapper.testsameElements();
        }, "PermutationTest.testsameElements() dose not check, that no to permuations are the same.");
    }

    @Test
    void worksWithPermutation(){
        PermutationVariation p = new Permutation(6);
        PermutationTestWrapper wrapper = getWrapper(p, 6);
        wrapper.testPermutation();
        wrapper.testDerangements();
        wrapper.testsameElements();
    }
}
