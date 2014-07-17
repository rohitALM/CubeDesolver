/**
 * 
 */
package de.cube.CubeDesolver;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
	
	public static void printSolution(List<FaceWrapper> faces) {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("NUMBERS.txt"));
			
			oos.writeObject(faces.get(0).getFaceState());
			//etc.

			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
