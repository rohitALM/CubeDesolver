/**
 * 
 */
package de.cube.CubeDesolver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Encapsulate Core Solver Logic
 * 
 * @author Rohit
 * 
 */
public class SolveHappyCube {

	private List<FaceWrapper> permutableFaces;
	// Select a base face - Setting Face 1 as default Base Face
	private FaceWrapper baseFace = new FaceWrapper(1, InitializeFaces.getFace1());

	public void solve() {

		// FaceWrapper test = new FaceWrapper(4, InitializeFaces.getFace5());
		// int[] lestEdge = test.getLeftEdge();
		// int[] rightEdge = test.getRightEdge();
		// int[] topEdge = test.getTopEdge();
		// int[] bottomEdge = test.getBottomEdge();

		permutableFaces = buildPermutableFaceList();

		// Get all possible face combinations to match with the base face
		Permutations<FaceWrapper> c = new Permutations<FaceWrapper>(permutableFaces, 4);

		while (c.hasNext()) {
			List<FaceWrapper> perm = c.next();
			matchEdges(perm);
		}

	}

	/**
	 * Checks if the face states received from permutation are distinct - as in
	 * belong to differen faces Checks if the edges match the base face
	 * 
	 * @param faces
	 */
	private void matchEdges(List<FaceWrapper> faces) {

		if (validatePermutation(faces)) {

			// Assign face states received from permutation algorithm to their
			// logical orientations
			int[] leftEdge = faces.get(0).getRightEdge();
			int[] rightEdge = faces.get(1).getLeftEdge();
			int[] topEdge = faces.get(2).getTopEdge();
			int[] bottomEdge = faces.get(3).getBottomEdge();

			// From Base Cube get the 4 edges for comparison
			int[] leftBaseEdge = baseFace.getLeftEdge();
			int[] rightBaseEdge = baseFace.getRightEdge();
			int[] topBaseEdge = baseFace.getTopEdge();
			int[] bottomBaseEdge = baseFace.getBottomEdge();

			// Compare Left Axis
			for (int leftIndex = 0; leftIndex < 5; leftIndex++) {
				int add = 0;
				if (0 == leftIndex) {
					add = leftEdge[leftIndex] + leftBaseEdge[leftIndex] + topBaseEdge[0];
				} else if (4 == leftIndex) {
					add = leftEdge[leftIndex] + leftBaseEdge[leftIndex] + bottomBaseEdge[0];
				} else {
					add = leftEdge[leftIndex] + leftBaseEdge[leftIndex];
				}

				if (add > 1)
					return;
			}

			// Compare Right Axis
			for (int rightIndex = 0; rightIndex < 5; rightIndex++) {
				int add = 0;
				if (0 == rightIndex) {
					add = rightEdge[rightIndex] + rightBaseEdge[rightIndex] + topBaseEdge[4];
				} else if (4 == rightIndex) {
					add = rightEdge[rightIndex] + rightBaseEdge[rightIndex] + bottomBaseEdge[4];
				} else {
					add = leftEdge[rightIndex] + leftBaseEdge[rightIndex];
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

			System.out.println("Solution Found");
			validatePermutation(faces);
			handleSolution(faces);

		}

	}

	private void handleSolution(List<FaceWrapper> faces) {
		System.out.println("Solution Found");
	}

	/**
	 * Checks if the combination received from permutation are distinct - as in
	 * belong to different faces
	 * 
	 * @param faces
	 * @return
	 */
	private boolean validatePermutation(List<FaceWrapper> faces) {
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
	 * Every face of the cube can have 4 states i.e. when it is rotated each
	 * time by 90 degrees it enters a new state. This method aggregates all the
	 * possible states of the face
	 * 
	 * @return
	 */
	private List<FaceWrapper> buildPermutableFaceList() {

		List<FaceWrapper> list = new ArrayList<FaceWrapper>(20);

		list = returnFaceState(InitializeFaces.getFace2(), list, 2);
		list = returnFaceState(InitializeFaces.getFace3(), list, 3);
		list = returnFaceState(InitializeFaces.getFace4(), list, 4);
		list = returnFaceState(InitializeFaces.getFace5(), list, 5);
		list = returnFaceState(InitializeFaces.getFace6(), list, 6);

		return list;

	}

	/*
	 * Adds the 4 states of a face to the permutable list
	 */
	private List<FaceWrapper> returnFaceState(int[][] face, List<FaceWrapper> list, int faceId) {

		for (int i = 0; i < 4; i++) {
			FaceWrapper wrapper = new FaceWrapper(faceId, face);
			list.add(wrapper);
			face = CubeUtility.rotateFace(face);
		}
		return list;

	}

}
