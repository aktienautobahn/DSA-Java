import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

class BoardTest {

	@Test
	void testBoard() {
		Exception exception1 = assertThrows(InputMismatchException.class, () -> {
			Board board1 = new Board(-1);
		});
		Exception exception2 = assertThrows(InputMismatchException.class, () -> {
			Board board2 = new Board(11);
		});
		Exception exception3 = assertThrows(InputMismatchException.class, () -> {
			Board board3 = new Board(11);
		});

		Board board = new Board(3);
		assertNotNull(board);
		assertEquals(3, board.getN());
	}

	@Test
	void testGetN() {
		Board board = new Board(3);
		assertEquals(3, board.getN());
	}

	@Test
	void testNFreeFields() {
		Board board = new Board(3);
		assertEquals(9, board.nFreeFields()); // Assuming a new board is entirely empty
		board.doMove(new Position(0, 0), 1);
		board.doMove(new Position(0, 1), -1);
		assertEquals(7, board.nFreeFields());
	}
	@Test
	void testBoundaryPosition() {
		Board board = new Board(3);
		board.setField(new Position(2, 2), 1); // Setting the last cell
		assertEquals(1, board.getField(new Position(2, 2))); // Checking the last cell
	}

	@Test
	void testGetField() {
		Board board = new Board(3);
		board.doMove(new Position(0, 0), 1);
		assertEquals(1, board.getField(new Position(0, 0)));
		assertThrows(InputMismatchException.class, () -> {
			board.getField(new Position(3, 3)); // Invalid position
		});
	}

	@Test
	void testSetField() {
		Board board = new Board(3);
		board.setField(new Position(0, 0), 1);
		assertEquals(1, board.getField(new Position(0, 0)));
		assertThrows(InputMismatchException.class, () -> {
			board.setField(new Position(0, 0), 2); // Invalid token
		});
		assertThrows(InputMismatchException.class, () -> {
			board.setField(new Position(0, 0), 1); // Position already taken
		});
	}

	@Test
	void testDoMove() {
		Board board = new Board(3);
		board.doMove(new Position(0, 0), 1);
		assertEquals(1, board.getField(new Position(0, 0)));
		assertThrows(RuntimeException.class, () -> {
			board.doMove(new Position(0, 0), 1); // Position already occupied
		});
	}

	@Test
	void testUndoMove() {
		Board board = new Board(3);
		board.doMove(new Position(0, 0), 1);
		board.undoMove(new Position(0, 0));
		assertEquals(0, board.getField(new Position(0, 0)));
	}

	@Test
	void testIsGameWon() {
		Board board = new Board(3);
		assertFalse(board.isGameWon());
		board.doMove(new Position(0, 0), 1);
		board.doMove(new Position(1, 1), 1);
		board.doMove(new Position(2, 2), 1);
		assertTrue(board.isGameWon());
	}

	@Test
	void testValidMoves() {
		Board board = new Board(3);
		Iterable<Position> validMoves = board.validMoves();
		System.out.println(validMoves);
		int count = 0;
		for (Position pos : validMoves) {
			count++;
		}
		assertEquals(9, count); // Full board should have 9 moves available initially
	}

	@Test
	void testValidMovesEdgeCase() {
		Board board = new Board(1);
		Iterable<Position> validMoves = board.validMoves();
		System.out.println(validMoves);
		int count = 0;
		for (Position pos : validMoves) {
			count++;
		}
		assertEquals(1, count); // Full board should have 9 moves available initially
	}

	@Test
	void testPrint() {
		Board board = new Board(3);
		board.doMove(new Position(0, 0), 1);
		assertDoesNotThrow(() -> board.print()); // Check that the method completes without throwing an exception
	}

}


