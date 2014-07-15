/**
 * 
 */
package de.cube.CubeDesolver;

import java.util.List;

/**
 * Contains all the utility methods
 * 
 * @author Rohit
 * 
 */
public final class CubeUtility {

	/**
	 * This method returns the cube face rotated clockwise by 90 degrees
	 * 
	 * @param face
	 * @return
	 */
	public static int[][] rotateFace(int[][] face) {
		int n = 5;
		int[][] rotatedFace = new int[5][5];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				rotatedFace[i][j] = face[n - j - 1][i];
			}
		}

		return rotatedFace;
	}

	List<int[][]> permutateFaces() {
		return null;

	}

	/**
	 * For every Cube Face - Generate 4 rotated possibilities These 4 options
	 * will be fed to the permutator
	 * 
	 */
	void explodeFaceOrientation() {
		List<int[][]> explodedFacesList;
	}

}
