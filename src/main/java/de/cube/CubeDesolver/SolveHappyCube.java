/**
 * 
 */
package de.cube.CubeDesolver;

import java.util.ArrayList;
import java.util.List;

import de.cube.input.InitializeBlueCube;
import de.cube.input.InitializePurpleCube;
import de.cube.input.InitializeRedCube;
import de.cube.input.InitializeYellowCube;

/**
 * Encapsulate Core Solver Logic
 * 
 * @author Rohit
 * 
 */
public class SolveHappyCube {

	private List<FaceWrapper> permutableFaces;
	private FaceWrapper baseFace;
	boolean match = false;
	String currentPuzzle;// Which is the current puzzle being solved

	/**
	 * Fit the edges together
	 */
	public void solve() {
		// Solve iteratively for each puzzle
		// First solving for blue puzzle
		setDataForBlueCubePuzzle();
		solvePuzzle();
		// Solve the red cube puzzle
		setDataForRedCubePuzzle();
		solvePuzzle();
		// Solve the yellow cube puzzle
		setDataForYellowCubePuzzle();
		solvePuzzle();
		//Solve the Purple CUbe puzzle
		setDataForPurpleCubePuzzle();
		solvePuzzle();


	}

	/**
	 * 
	 */
	private void solvePuzzle() {
		
		permutableFaces = buildPermutableFaceList();

		// Get all possible face combinations to match with the base face
		Permutations<FaceWrapper> c = new Permutations<FaceWrapper>(permutableFaces, 5);

		while (c.hasNext()) {
			List<FaceWrapper> perm = c.next();
			if (!match)
				matchEdges(perm);
		}
	}

	/**
	 * Checks if the face states received from permutation are distinct - as in
	 * belong to different faces Checks if the edges match the base face
	 * 
	 * @param faces
	 */
	private void matchEdges(List<FaceWrapper> faces) {

		if (CubeUtility.validatePermutation(faces)) {

			baseFace = faces.get(4);
			// Assign face states received from permutation algorithm to their
			// logical orientations
			int[] leftEdge = faces.get(0).getRightEdge();
			int[] rightEdge = faces.get(1).getLeftEdge();
			int[] bottomEdge = faces.get(2).getTopEdge();
			int[] topEdge = faces.get(3).getBottomEdge();

			// From Base Cube get the 4 edges for comparison
			int[] leftBaseEdge = baseFace.getLeftEdge();
			int[] rightBaseEdge = baseFace.getRightEdge();
			int[] topBaseEdge = baseFace.getTopEdge();
			int[] bottomBaseEdge = baseFace.getBottomEdge();

			// Compare Left Axis
			for (int leftIndex = 0; leftIndex < 5; leftIndex++) {
				int add = 0;
				if (0 == leftIndex) {
					add = leftEdge[leftIndex] + leftBaseEdge[leftIndex] + topEdge[0];
				} else if (4 == leftIndex) {
					add = leftEdge[leftIndex] + leftBaseEdge[leftIndex] + bottomEdge[0];
				} else {
					add = leftEdge[leftIndex] + leftBaseEdge[leftIndex];
				}

				if (1 != add)
					return;
			}

			// Compare Right Axis
			for (int rightIndex = 0; rightIndex < 5; rightIndex++) {
				int add = 0;
				if (0 == rightIndex) {
					add = rightEdge[rightIndex] + rightBaseEdge[rightIndex] + topEdge[4];
				} else if (4 == rightIndex) {
					add = rightEdge[rightIndex] + rightBaseEdge[rightIndex] + bottomEdge[4];
				} else {
					add = rightEdge[rightIndex] + rightBaseEdge[rightIndex];
				}

				if (1 != add)
					return;
			}

			// Compare Top Axis
			for (int topIndex = 0; topIndex < 5; topIndex++) {
				int add = 0;
				add = topEdge[topIndex] + topBaseEdge[topIndex];
				if (1 != add)
					return;
			}

			// Compare Bottom Axis

			for (int bottomIndex = 0; bottomIndex < 5; bottomIndex++) {
				int add = 0;
				add = bottomEdge[bottomIndex] + bottomBaseEdge[bottomIndex];
				if (1 != add)
					return;
			}

			fitTopFace(faces);

		}

	}

	private void handleSolution(List<FaceWrapper> faces) {
		CubeUtility.printSolution(faces, currentPuzzle);

	}

	/**
	 * The four walls of the solution are now determined This method determines
	 * in which orientation the top face fits
	 * 
	 * @param faces
	 */
	private void fitTopFace(List<FaceWrapper> faces) {
		int topFaceId = determineTopFaceID(faces);

		FaceWrapper topFace = CubeUtility.returnWrapperFaceFromId(topFaceId);

		// Get the edges from the four wall faces for comparison with top face
		int[] leftEdge = faces.get(0).getLeftEdge();
		int[] rightEdge = faces.get(1).getRightEdge();
		int[] bottomEdge = faces.get(2).getBottomEdge();
		int[] topEdge = faces.get(3).getTopEdge();

		// Rotate top face till it fits
		for (int i = 0; i < 4; i++) {

			match = true;

			// From Top Cube get the 4 edges for comparison
			int[] leftTopEdge = topFace.getLeftEdge();
			int[] rightTopEdge = topFace.getRightEdge();
			int[] topTopEdge = topFace.getTopEdge();
			int[] bottomTopEdge = topFace.getBottomEdge();

			// Compare Left Axis
			for (int leftIndex = 0; leftIndex < 5; leftIndex++) {
				int add = 0;
				if (0 == leftIndex) {
					add = leftEdge[leftIndex] + leftTopEdge[leftIndex] + topEdge[0];
				} else if (4 == leftIndex) {
					add = leftEdge[leftIndex] + leftTopEdge[leftIndex] + bottomEdge[0];
				} else {
					add = leftEdge[leftIndex] + leftTopEdge[leftIndex];
				}

				if (1 != add)
					match = false;
			}

			// Compare Right Axis
			for (int rightIndex = 0; rightIndex < 5; rightIndex++) {
				int add = 0;
				if (0 == rightIndex) {
					add = rightEdge[rightIndex] + rightTopEdge[rightIndex] + topEdge[4];
				} else if (4 == rightIndex) {
					add = rightEdge[rightIndex] + rightTopEdge[rightIndex] + bottomEdge[4];
				} else {
					add = rightEdge[rightIndex] + rightTopEdge[rightIndex];
				}

				if (1 != add)
					match = false;
			}

			// Compare Top Axis
			for (int topIndex = 0; topIndex < 5; topIndex++) {
				int add = 0;
				add = topEdge[topIndex] + topTopEdge[topIndex];
				if (1 != add)
					match = false;
			}

			// Compare Bottom Axis

			for (int bottomIndex = 0; bottomIndex < 5; bottomIndex++) {
				int add = 0;
				add = bottomEdge[bottomIndex] + bottomTopEdge[bottomIndex];
				if (1 != add)
					match = false;
			}

			if (match)
				break;
			topFace = CubeUtility.rotateFaceWithWrapper(topFace);

		}

		if (match) {
			System.out.println("Worked");
			faces.add(topFace);
			handleSolution(faces);
		}

	}

	/**
	 * Determines what is the id of the top face e.g. - if the id of the faces
	 * that make up the 4 walls is 2,3,5,6 The id of the top face is 4 This is
	 * computed by (2+3+4+5+6)-(2+3+5+6)
	 * 
	 * @param faces
	 * @return
	 */
	private int determineTopFaceID(List<FaceWrapper> faces) {
		int sumofIds = 1 + 2 + 3 + 4 + 5 + 6;
		int actualSumOfIds = 0;
		for (int i = 0; i < faces.size(); i++) {
			actualSumOfIds += faces.get(i).getFaceId();
		}
		return sumofIds - actualSumOfIds;

	}

	/**
	 * Every face of the cube can have 4 states i.e. when it is rotated each
	 * time by 90 degrees it enters a new state. This method aggregates all the
	 * possible states of the face
	 * 
	 * @return
	 */
	private List<FaceWrapper> buildPermutableFaceList() {

		List<FaceWrapper> list = new ArrayList<FaceWrapper>();

		list = returnFaceState(InitializeFaces.getFace1(), list, 1);
		list = returnFaceState(InitializeFaces.getFace2(), list, 2);
		list = returnFaceState(InitializeFaces.getFace3(), list, 3);
		list = returnFaceState(InitializeFaces.getFace4(), list, 4);
		list = returnFaceState(InitializeFaces.getFace5(), list, 5);
		list = returnFaceState(InitializeFaces.getFace6(), list, 6);

		return list;

	}

	/**
	 * Adds the 4 states of a face to the permutable list
	 * 
	 * @param face
	 * @param list
	 * @param faceId
	 * @return
	 */
	private List<FaceWrapper> returnFaceState(int[][] face, List<FaceWrapper> list, int faceId) {

		for (int i = 0; i < 4; i++) {
			FaceWrapper wrapper = new FaceWrapper(faceId, face);
			list.add(wrapper);
			face = CubeUtility.rotateFace(face);
		}
		return list;

	}

	/**
	 * Sets the data to solve the blue cube puzzle
	 */
	private void setDataForBlueCubePuzzle() {
		currentPuzzle = "Blue";
		match = false;
		InitializeFaces.setFace1(InitializeBlueCube.getFace1());
		InitializeFaces.setFace2(InitializeBlueCube.getFace2());
		InitializeFaces.setFace3(InitializeBlueCube.getFace3());
		InitializeFaces.setFace4(InitializeBlueCube.getFace4());
		InitializeFaces.setFace5(InitializeBlueCube.getFace5());
		InitializeFaces.setFace6(InitializeBlueCube.getFace6());

	}

	/**
	 * Sets the data to solve the Red cube puzzle
	 */
	private void setDataForRedCubePuzzle() {
		currentPuzzle = "Red";
		match = false;
		InitializeFaces.setFace1(InitializeRedCube.getFace1());
		InitializeFaces.setFace2(InitializeRedCube.getFace2());
		InitializeFaces.setFace3(InitializeRedCube.getFace3());
		InitializeFaces.setFace4(InitializeRedCube.getFace4());
		InitializeFaces.setFace5(InitializeRedCube.getFace5());
		InitializeFaces.setFace6(InitializeRedCube.getFace6());

	}
	
	/**
	 * Sets the data to solve the Yellow cube puzzle
	 */
	private void setDataForYellowCubePuzzle() {
		currentPuzzle = "Yellow";
		match = false;
		InitializeFaces.setFace1(InitializeYellowCube.getFace1());
		InitializeFaces.setFace2(InitializeYellowCube.getFace2());
		InitializeFaces.setFace3(InitializeYellowCube.getFace3());
		InitializeFaces.setFace4(InitializeYellowCube.getFace4());
		InitializeFaces.setFace5(InitializeYellowCube.getFace5());
		InitializeFaces.setFace6(InitializeYellowCube.getFace6());

	}
	
	/**
	 * Sets the data to solve the Purple cube puzzle
	 */
	private void setDataForPurpleCubePuzzle() {
		currentPuzzle = "Purple";
		match = false;
		InitializeFaces.setFace1(InitializePurpleCube.getFace1());
		InitializeFaces.setFace2(InitializePurpleCube.getFace2());
		InitializeFaces.setFace3(InitializePurpleCube.getFace3());
		InitializeFaces.setFace4(InitializePurpleCube.getFace4());
		InitializeFaces.setFace5(InitializePurpleCube.getFace5());
		InitializeFaces.setFace6(InitializePurpleCube.getFace6());

	}

}
