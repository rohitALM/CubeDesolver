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

	/**
	 * Returns the left edge of the cube
	 * 
	 * @return
	 */
	public int[] getLeftEdge() {

		int[] leftEdge = new int[faceState.length];
		for (int i = 0; i < faceState.length; i++) {
			leftEdge[i] = faceState[i][0];
		}

		return leftEdge;

	}

	/**
	 * Returns the Right edge of the cube
	 * 
	 * @return
	 */
	public int[] getRightEdge() {

		int[] rightEdge = new int[faceState.length];
		for (int i = 0; i < faceState.length; i++) {
			rightEdge[i] = faceState[i][faceState.length - 1];
		}

		return rightEdge;

	}

	/**
	 * Returns the Top edge of the cube
	 * 
	 * @return
	 */
	public int[] getTopEdge() {

		int[] topEdge = new int[faceState.length];
		for (int i = 0; i < faceState.length; i++) {
			topEdge[i] = faceState[0][i];
		}

		return topEdge;

	}

	/**
	 * Returns the Bottom edge of the cube
	 * 
	 * @return
	 */
	public int[] getBottomEdge() {

		int[] bottomEdge = new int[faceState.length];
		for (int i = 0; i < faceState.length; i++) {
			bottomEdge[i] = faceState[faceState.length - 1][i];
		}

		return bottomEdge;

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
