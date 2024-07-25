import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PermutationTest {
	PermutationVariation p1;
	PermutationVariation p2;
	public int n1;
	public int n2;
	int cases=1;
	
	void initialize() {
		n1=4;
		n2=6;
		Cases c= new Cases();
		p1= c.switchforTesting(cases, n1);
		p2= c.switchforTesting(cases, n2);
	}
	

	@Test
	void testPermutation() {
		System.out.println("testing Permutation constructor");

		initialize();
		System.out.println("Testing lengths of the original");
		// test length of the variable
		assertEquals(p1.original.length, n1);
		assertEquals(p2.original.length, n2);

		System.out.println("Testing for absence of duplicates");
		// test no duplicates
		assertFalse(hasDuplicates(p1.original)); // asserts, that there are no duplicates in the array
		assertFalse(hasDuplicates(p2.original)); // asserts, that there are no duplicates in the array

		System.out.println("Testing for empty allDerangements List right after creation");
		// allDerangements is an empty list: size = 0
		assertNotNull(p1.allDerangements);
		assertNotNull(p2.allDerangements);

		assertTrue(p1.allDerangements.isEmpty());
		assertTrue(p2.allDerangements.isEmpty());

	}

	public static boolean hasDuplicates(int[] array) {
		Set<Integer> set = new HashSet<>(); // HashSet by design has never duplicates
		for (int element : array) {
			if (!set.add(element)) {
				return true; // element was already in the set
			}
		}
		return false; // no duplicates found
	}

	@Test
	void testDerangements() {
		initialize();
		fixConstructor(); 		//in case there is something wrong with the constructor

		System.out.println("Testing Derangements of p1");
		p1.derangements();
		assertEquals(possibleDerangementsAmount(p1.original.length), p1.allDerangements.size());
		assertTrue(isNoFixedPoint(p1.original, p1.allDerangements));

		System.out.println("Testing Derangements of p2");

		p2.derangements();
		assertEquals(possibleDerangementsAmount(p2.original.length), p2.allDerangements.size());
		assertTrue(isNoFixedPoint(p2.original, p2.allDerangements));

	}

	public static int possibleDerangementsAmount(int N) {
		if (N == 0) return 1;
		return (int) (N  * possibleDerangementsAmount(N - 1) + Math.pow(-1,N));
	}

	private static boolean sameLength(int[] arrayA, LinkedList<int[]> derangements) {
		if (derangements == null) return false;
		if (arrayA.length == 0) return false;

		for (int[] arrayB : derangements) {
			// check length
			if (arrayA.length != arrayB.length) return false;
		}
		return true;
	}


	private static boolean isNoFixedPoint(int[] arrayA, LinkedList<int[]> derangements) {
		if (derangements == null || derangements.isEmpty()) return false; // check for null or empty
		if (arrayA.length == 0) return false;

		for (int[] arrayB: derangements) {
			// check length
			if (arrayA.length != arrayB.length) return false;

		// Check for no fixed points
				for (int i = 0; i < arrayA.length; i++) {
					if (arrayA[i] == arrayB[i]) {
						return false; // Fixed point found
					}
				}
		}
		return true;
	}

	@Test
	void testsameElements() {
		try {
			initialize();
		} catch (IllegalArgumentException e) {
			System.out.println("IllegalArgumentException is caught" + e + "\n" + "fixing Constructor...");
			fixConstructor();
		}
		//		 test if all derangements are permutations of the original array
		try {
			p1.derangements();
//			assertNotNull(p1.allDerangements);
			assertTrue(isPermutation(p1.original,p1.allDerangements));
		} catch (Exception e) {
			System.out.println("Exception catched: " + e);
		}

		try {
			p2.derangements();
//			assertNotNull(p1.allDerangements);
			assertTrue(isPermutation(p1.original, p1.allDerangements));
		} catch (Exception e) {
			System.out.println("Exception catched: " + e);
		}

	}

	public static boolean isPermutation(int[] arr1, LinkedList<int[]> permutations) {

		if (permutations == null) return false;
		if (permutations.isEmpty()) return false;

		int[] sortedOriginalArray = arr1;
		Arrays.sort(sortedOriginalArray);

		for (int[] permArray: permutations) {
			if (sortedOriginalArray.length != permArray.length) return false;

			int[] sortedPermArray = permArray;
			Arrays.sort(sortedPermArray);

			for (int i = 0; i < sortedOriginalArray.length; i++) {
				if (sortedOriginalArray[i] != sortedPermArray[i]) return false;
			}
		}

		return true;
	}


	void setCases(int c) {
		this.cases=c;
	}
	
	public void fixConstructor() {
		//in case there is something wrong with the constructor
		p1.allDerangements=new LinkedList<int[]>();
		for(int i=0;i<n1;i++)
			p1.original[i]=2*i+1;
		
		p2.allDerangements=new LinkedList<int[]>();
		for(int i=0;i<n2;i++)
			p2.original[i]=i+1;
	}
}


