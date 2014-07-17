/**
 * 
 */
package de.cube.CubeDesolver;

/**
 * This class encodes the input cubes directly
 * 
 * @author Rohit
 * 
 */
public class InitializeFaces {

	private static int[][] face1 = { { 0, 0, 1, 0, 1 }, { 1, 1, 1, 1, 1, }, { 0, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1 },
			{ 0, 1, 0, 1, 0 } };

	private static int[][] face2 = { { 0, 0, 1, 0, 0 }, { 0, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1 }, { 0, 1, 1, 1, 0 },
			{ 0, 0, 1, 0, 0 } };
	private static int[][] face3 = { { 1, 0, 1, 0, 1 }, { 1, 1, 1, 1, 1 }, { 0, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1 },
			{ 1, 0, 1, 0, 1 } };
	private static int[][] face4 = { { 0, 0, 1, 0, 0 }, { 0, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0 }, { 0, 1, 1, 1, 1 },
			{ 0, 0, 1, 0, 0 } };
	private static int[][] face5 = { { 0, 1, 0, 1, 0 }, { 1, 1, 1, 1, 0 }, { 0, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0 },
			{ 1, 1, 0, 1, 0 } };;
	// private static int[][] face5 = { { 0, 1, 0, 1, 0 }, { 1, 1, 1, 1, 1 },
	// { 0, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1 }, { 1, 0, 1, 0, 0 } };
	private static int[][] face6 = { { 0, 1, 0, 1, 0 }, { 0, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0 }, { 0, 1, 1, 1, 1 },
			{ 1, 1, 0, 1, 1 } };

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
