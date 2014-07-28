/**
 * 
 */
package de.cube.CubeDesolver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	/**
	 * Rotates the cube face by 90 degrees Returns the result encapsulated in
	 * the wrapper object
	 * 
	 * @param face
	 * @return
	 */
	public static FaceWrapper rotateFaceWithWrapper(FaceWrapper face) {

		return new FaceWrapper(face.getFaceId(), rotateFace(face.getFaceState()));

	}

	/**
	 * Return Face wrapper object based on ID
	 * 
	 * @param faceID
	 * @return
	 */
	public static FaceWrapper returnWrapperFaceFromId(int faceID) {

		switch (faceID) {
		case 1:
			return new FaceWrapper(1, InitializeFaces.getFace1());

		case 2:
			return new FaceWrapper(2, InitializeFaces.getFace2());
		case 3:
			return new FaceWrapper(3, InitializeFaces.getFace3());
		case 4:
			return new FaceWrapper(4, InitializeFaces.getFace4());
		case 5:
			return new FaceWrapper(5, InitializeFaces.getFace5());
		case 6:
			return new FaceWrapper(6, InitializeFaces.getFace6());

		default:
			return null;
		}

	}

	/**
	 * Checks if the combination received from permutation are distinct - as in
	 * belong to different faces
	 * 
	 * @param faces
	 * @return
	 */
	public static boolean validatePermutation(List<FaceWrapper> faces) {
		List<Integer> idList = new ArrayList<Integer>();
		for (int i = 0; i < faces.size(); i++) {
			idList.add(faces.get(i).getFaceId());
		}

		Set<Integer> idSet = new HashSet<Integer>(idList);
		if (idSet.size() < idList.size()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Prints the solution to the file
	 * 
	 * @param faces
	 */

	public static void printSolution(List<FaceWrapper> faces , String puzzleId) {

		PrintWriter writer = null;
		int[][] data = mergeSolutionForPrint(faces);
		try {

			writer = new PrintWriter(puzzleId+"_Solution.txt");
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					if (0 == data[i][j]) {
						writer.append(' ');
					} else {
						writer.append('0');
					}
				}
				writer.println();
			}

		} catch (FileNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
				System.out.println("Could not close writer");
			}
		}
	}

	/**
	 * Flips Array Horizontally
	 * 
	 * @param theArray
	 */
	public static void flipInPlace(int[][] theArray) {
		for (int i = 0; i < (theArray.length / 2); i++) {
			int[] temp = theArray[i];
			theArray[i] = theArray[theArray.length - i - 1];
			theArray[theArray.length - i - 1] = temp;
		}
	}

	/**
	 * Merges all faces in one array for displaying unfolded solution
	 * 
	 * @param faces
	 * @return
	 */
	private static int[][] mergeSolutionForPrint(List<FaceWrapper> faces) {

		int[][] mergedArray = new int[20][15];
		int[][] leftFace = faces.get(0).getFaceState();
		int[][] rightFace = faces.get(1).getFaceState();
		int[][] bottomFace = faces.get(2).getFaceState();
		int[][] topFace = faces.get(3).getFaceState();
		int[][] baseFace = faces.get(4).getFaceState();
		int[][] apexFace = faces.get(5).getFaceState();

		flipInPlace(apexFace);
		flipInPlace(topFace);

		for (int i = 0; i < 20; i++) {
			if (i < 5) {
				int[] leftPiece = leftFace[i];
				int[] rightPiece = rightFace[i];
				int[] basePiece = baseFace[i];
				int aLength = leftPiece.length;

				System.arraycopy(leftPiece, 0, mergedArray[i], 0, aLength);
				System.arraycopy(basePiece, 0, mergedArray[i], aLength, aLength);
				System.arraycopy(rightPiece, 0, mergedArray[i], aLength * 2, aLength);
			}
			if (i >= 5 && i < 10) {
				int[] bottomPiece = bottomFace[i - 5];
				int aLength = bottomPiece.length;
				System.arraycopy(bottomPiece, 0, mergedArray[i], aLength, aLength);
			}
			if (i >= 10 && i < 15) {
				int[] apexPiece = apexFace[i - 10];
				int aLength = apexPiece.length;
				System.arraycopy(apexPiece, 0, mergedArray[i], aLength, aLength);
			}
			if (i >= 15 && i < 20) {
				int[] topPiece = topFace[i - 15];
				int aLength = topPiece.length;
				System.arraycopy(topPiece, 0, mergedArray[i], aLength, aLength);
			}
		}

		return mergedArray;

	}
}
