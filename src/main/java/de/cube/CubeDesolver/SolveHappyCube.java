/**
 * 
 */
package de.cube.CubeDesolver;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulate Core Solver Logic
 * 
 * @author Rohit
 * 
 */
public class SolveHappyCube {

	private List<FaceWrapper> permutableFaces;
	// Select a base face - Setting Face 1 as default Base Face
	private int[][] baseFace = InitializeFaces.getFace1();

	public void solve() {
		
		FaceWrapper test = new FaceWrapper(4, InitializeFaces.getFace5());
		int[] lestEdge =test.getLeftEdge();
		int[] rightEdge = test.getRightEdge();
		int[] topEdge = test.getTopEdge();
		int[] bottomEdge = test.getBottomEdge();

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
			int[][] leftFace = faces.get(0).getFaceState();
			int[][] rightFace = faces.get(1).getFaceState();
			int[][] topFace = faces.get(2).getFaceState();
			int[][] bottomFace = faces.get(3).getFaceState();
			

		}

	}

	/**
	 * Checks if the combination received from permutation are distinct - as in
	 * belong to different faces
	 * 
	 * @param faces
	 * @return
	 */
	private boolean validatePermutation(List<FaceWrapper> faces) {
		int checkId = 0;
		for (int i = 0; i < faces.size(); i++) {
			if (checkId == faces.get(i).getFaceId()) {
				return false;
			}
			checkId = faces.get(i).getFaceId();
		}
		return true;

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
