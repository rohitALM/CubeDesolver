/**
 * 
 */
package de.cube.CubeDesolver;

/**
 * provides a wrapper object around a face Stores different face states and ties
 * them to a face
 * 
 * @author Rohit
 * 
 */
public class FaceWrapper {

	private int faceId;
	private int[][] faceState;

	/**
	 * Initialize wrapper
	 * 
	 * @param faceId
	 * @param faceState
	 */
	FaceWrapper(int faceId, int[][] faceState) {
		this.faceId = faceId;
		this.faceState = faceState;
	}

	public int getFaceId() {
		return faceId;
	}

	public void setFaceId(int faceId) {
		this.faceId = faceId;
	}

	public int[][] getFaceState() {
		return faceState;
	}

	public void setFaceState(int[][] faceState) {
		this.faceState = faceState;
	}

}
