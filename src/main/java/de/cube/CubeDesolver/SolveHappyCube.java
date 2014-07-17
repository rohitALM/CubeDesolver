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

	int count = 0;

	private List<FaceWrapper> permutableFaces;
	// Select a base face - Setting Face 1 as default Base Face
	private FaceWrapper baseFace = new FaceWrapper(1, InitializeFaces.getFace1());

	/**
	 * Fit the edges together
	 */
	public void solve() {

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
	 * belong to different faces Checks if the edges match the base face
	 * 
	 * @param faces
	 */
	private void matchEdges(List<FaceWrapper> faces) {

		if (validatePermutation(faces)) {

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

			System.out.println("Solution Found");
			validatePermutation(faces);
			handleSolution(faces);
			System.out.println(count++);

		}

	}

	private void handleSolution(List<FaceWrapper> faces) {
		System.out.println("");
		fitTopFace(faces);

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

		boolean match = true;

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

		if (match)
			System.out.println("it worked");
		else
			System.out.println("Shit happened");

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
		int sumofIds = 2 + 3 + 4 + 5 + 6;
		int actualSumOfIds = 0;
		for (int i = 0; i < faces.size(); i++) {
			actualSumOfIds += faces.get(i).getFaceId();
		}
		return sumofIds - actualSumOfIds;

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
