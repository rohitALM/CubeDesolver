package de.cube.CubeDesolver;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 * 
 */
public class EntryPoint {
	public static void main(String[] args) {
		
		SolveHappyCube solveHappyCube = new SolveHappyCube();
		solveHappyCube.solve();
		
		// Select a base face - Setting Face 1 as default Base Face
		int[][] baseFace = InitializeFaces.getFace1();
		
		CubeUtility util = new CubeUtility();
		
		util.rotateFace(baseFace);

		List<String> children = new ArrayList<String>();
		children.add("Anna");
		children.add("Dan");
		children.add("Lola");
		children.add("Choti");
		children.add("Beta");
		Permutations<String> c = new Permutations<String>(children, 2);

		while (c.hasNext()) {
			List<String> perm = c.next();
			for (int i = 0; i < perm.size(); i++) {
				System.out.print(perm.get(i));
			}
			System.out.println();
		}

	}
}
