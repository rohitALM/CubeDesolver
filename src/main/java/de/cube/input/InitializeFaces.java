/**
 * 
 */
package de.cube.input;

/**
 * This class encodes the input cubes directly
 * 
 * @author Rohit
 * 
 */
public class InitializeFaces {

	private static int[][] face1;
	private static int[][] face2;
	private static int[][] face3;
	private static int[][] face4;
	private static int[][] face5;
	private static int[][] face6;

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

	public static void setFace1(int[][] face1) {
		InitializeFaces.face1 = face1;
	}

	public static void setFace2(int[][] face2) {
		InitializeFaces.face2 = face2;
	}

	public static void setFace3(int[][] face3) {
		InitializeFaces.face3 = face3;
	}

	public static void setFace4(int[][] face4) {
		InitializeFaces.face4 = face4;
	}

	public static void setFace5(int[][] face5) {
		InitializeFaces.face5 = face5;
	}

	public static void setFace6(int[][] face6) {
		InitializeFaces.face6 = face6;
	}

}
