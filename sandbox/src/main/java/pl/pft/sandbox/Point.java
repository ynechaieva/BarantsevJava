package pl.pft.sandbox;

/**
 * Created by ynech on 11/06/2017.
 */
public class Point {

  public double x;
  public double y;

  public  Point(double x, double y){
    this.x = x;
    this.y = y;
  }

  public void print(){
    System.out.print("(" + this.x + ", " + this.y + ")");
  }

  public double distance(Point p){
    return Math.sqrt(Math.pow((this.x - p.x), 2) + Math.pow((this.y - p.y), 2));
  }
}
