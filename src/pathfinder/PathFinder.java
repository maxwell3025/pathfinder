package pathfinder;

import java.util.*;

public class PathFinder{
	static boolean[][] isavail = {
			{ true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
			{ true, true, false, false, false, true, true, true, true, true, true, true, true, true, true, true },
			{ true, true, false, true, false, true, true, true, true, true, true, true, true, true, true, true },
			{ true, true, false, true, false, true, true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true } };
	int[][] distances = new int[16][16];
	Point[][] gradfield = new Point[16][16];

	public PathFinder() {
		resetdistances();
		System.out.print("hi");
		updatedistances(new intpoint(3, 3));

		for (int x = 0; x < distances.length; x++) {
			for (int y = 0; y < distances[x].length; y++) {
				if (distances[x][y] == -1) {
					System.out.print("wall\t");
				} else {
					System.out.print(distances[x][y] + ", \t");
				}

			}
			System.out.println("");
		}

	}

	public void updatedistances(intpoint p) {
		List<intpoint> queue = new ArrayList<intpoint>();
		queue.add(p);
		int queuenum = 0;
		boolean[][] calculated = isavail.clone();
		boolean[][] queued = new boolean[isavail.length][isavail[0].length];
		boolean[][] walls = new boolean[isavail.length][isavail[0].length];
		for (int x = 0; x < isavail.length; x++) {
			for (int y = 0; y < isavail[x].length; y++) {
				walls[x][y] = isavail[x][y];
			}
		}
		for (int x = 0; x < isavail.length; x++) {
			for (int y = 0; y < isavail[x].length; y++) {
				queued[x][y] = isavail[x][y];
			}
		}
		System.out.println(queued == walls);
		calculated[p.x][p.y] = false;
		queued[p.x][p.y] = false;
		while (queuenum < queue.size()) {
			System.out.println(queuenum + "" + queue.size());
			intpoint selection = queue.get(queuenum);
			int up = -1;
			int left = -1;
			int down = -1;
			int right = -1;
			try {
				if (queued[selection.x][selection.y + 1]) {
					queue.add(new intpoint(selection.x, selection.y + 1));
					queued[selection.x][selection.y + 1] = false;
				}
				if (walls[selection.x][selection.y + 1]) {
					up = distances[selection.x][selection.y + 1];
					System.out.println("up");
				}
			} catch (ArrayIndexOutOfBoundsException e) {

			}
			try {
				if (queued[selection.x - 1][selection.y]) {
					queue.add(new intpoint(selection.x - 1, selection.y));
					queued[selection.x - 1][selection.y] = false;
				}
				if (walls[selection.x - 1][selection.y]) {
					left = distances[selection.x - 1][selection.y];
					System.out.println("left");
				}
			} catch (ArrayIndexOutOfBoundsException e) {

			}
			try {
				if (queued[selection.x][selection.y - 1]) {
					queue.add(new intpoint(selection.x, selection.y - 1));
					queued[selection.x][selection.y - 1] = false;
				}
				if (walls[selection.x][selection.y - 1]) {
					down = distances[selection.x][selection.y - 1];
					System.out.println("down");
				}
			} catch (ArrayIndexOutOfBoundsException e) {

			}
			try {
				if (queued[selection.x + 1][selection.y]) {
					queue.add(new intpoint(selection.x + 1, selection.y));
					queued[selection.x + 1][selection.y] = false;
				}
				if (walls[selection.x + 1][selection.y]) {
					right = distances[selection.x + 1][selection.y];
					System.out.println("right");
				}
			} catch (ArrayIndexOutOfBoundsException e) {

			}
			calculated[selection.x][selection.y] = false;
			queuenum++;
			distances[selection.x][selection.y] = 1 + Math.max(Math.max(left, right), Math.max(up, down));
		}
		for (int x = 0; x < distances.length; x++) {
			for (int y = 0; y < distances[x].length; y++) {
				int selecteddist = distances[x][y];
				int distup=-1;
				int distdown=-1;
				int distleft=-1;
				int distright=-1;
				try {
					 distup = distances[x][y + 1];
				} catch (ArrayIndexOutOfBoundsException e) {

				}
				try {
					 distdown = distances[x][y - 1];
				} catch (ArrayIndexOutOfBoundsException e) {

				}
				try {
					 distleft = distances[x + 1][y];
				} catch (ArrayIndexOutOfBoundsException e) {

				}
				try {
					 distright = distances[x - 1][y];
				} catch (ArrayIndexOutOfBoundsException e) {

				}
				int horgrad=0;
				if(distleft!=-1&&distright!=-1){
					horgrad = distleft - selecteddist + selecteddist - distright;
				}
				int vergrad=0;
				if(distup!=-1&&distdown!=-1){
				vergrad = distup - selecteddist + selecteddist - distdown;
				}
				if (horgrad == 0 && vergrad == 0) {

				} else {
					gradfield[x][y] = new Point(horgrad, vergrad);
				}

			}
		}

	}

	public void resetdistances() {
		for (int x = 0; x < distances.length; x++) {
			for (int y = 0; y < distances[x].length; y++) {
				distances[x][y] = -1;
			}
		}
	}
}
