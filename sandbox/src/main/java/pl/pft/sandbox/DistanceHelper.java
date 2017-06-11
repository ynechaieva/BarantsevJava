package pl.pft.sandbox;
import java.lang.Math;

public class DistanceHelper {

	public static void main(String[] args){

		Point p1 = new Point(3, 8.3);
		Point p2 = new Point(2.4, 6.1);

		System.out.print("Расстояние между точками (static function) ");
		p1.print();
		System.out.print(" и ");
		p2.print();
		System.out.print(" = " + distance(p1, p2));
		System.out.println();

		System.out.print("Расстояние между точками (Point method) ");
		p1.print();
		System.out.print(" и ");
		p2.print();
		System.out.print(" = " + p1.distance(p2));
	}

	public static double distance(Point p1, Point p2){
		return Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2));
	}
}