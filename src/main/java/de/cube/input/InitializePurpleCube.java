package de.cube.input;

/**
 * Set Data for purple cube
 * 
 * @author Rohit
 * 
 */
public class InitializePurpleCube {
	private static int[][] face1 = { { 1, 1, 0, 1, 0 }, { 1, 1, 1, 1, 0 }, { 1, 1, 1, 1, 0 }, { 0, 1, 1, 1, 1 },
			{ 0, 0, 1, 0, 0 } };
	private static int[][] face2 = { { 0, 0, 0, 1, 1 }, { 1, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1 }, { 0, 1, 1, 1, 0 },
			{ 0, 1, 0, 1, 0 } };
	private static int[][] face3 = { { 0, 1, 0, 0, 0 }, { 1, 1, 1, 1, 0 }, { 0, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0 },
			{ 0, 0, 1, 0, 0 } };
	private static int[][] face4 = { { 1, 1, 0, 1, 1 }, { 0, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0 },
			{ 0, 1, 0, 1, 0 } };;
	private static int[][] face5 = { { 0, 0, 1, 0, 1 }, { 0, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0 },
			{ 1, 0, 1, 1, 0 } };
	private static int[][] face6 = { { 0, 1, 0, 1, 1 }, { 0, 1, 1, 1, 0 }, { 0, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0 },
			{ 1, 1, 0, 1, 0 } };

	public static int[][] getFace1() {
		return face1;
	}

	public static int[][] getFace2() {
		return face2;
	}

	public static int[][] getFace3() {
		return face3;
	}

	public static int[][] getFace4() {
		return face4;
	}

	public static int[][] getFace5() {
		return face5;
	}

	public static int[][] getFace6() {
		return face6;
	}

}
