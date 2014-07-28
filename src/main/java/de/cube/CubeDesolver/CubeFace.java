/**
 * 
 */
package de.cube.CubeDesolver;

/**
 * Bean encapsulating a face of the cube
 * 
 * @author Rohit
 * 
 */
public class CubeFace {

	private int[][] cubeFace;

	CubeFace(int[][] cubeFace) {
		cubeFace = new int[5][5];
	}

	public int[][] getCubeFace() {
		return cubeFace;
	}

	public void setCubeFace(int[][] cubeFace) {
		this.cubeFace = cubeFace;
	}

}
