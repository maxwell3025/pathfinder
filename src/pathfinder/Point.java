package pathfinder;

public class Point {
double x;
double y;

	public Point(double x,double y) {
		this.x=x;
		this.y=y;
	}
	public static Point add(Point a,Point b){
		return new Point(a.x+b.x,a.y+b.y);
	}

}
