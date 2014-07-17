/**
 * 
 */
package de.cube.CubeDesolver;

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

}
