package pl.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTests {

  @Test
  public void testIdenticalPoints(){
    Point pt1 = new Point(1.0, 2.0);
    Point pt2 = new Point(1.0, 2.0);

    Assert.assertEquals(pt1.distance(pt2), 0.0);
  }

  @Test
  public void testPositivePoints(){
    Point pt1 = new Point(2.7, 3.2);
    Point pt2 = new Point(1.3, 4.5);

    //assert pt1.distance(pt2) == 0.0;
    Assert.assertEquals(pt1.distance(pt2), 1.91049731745428);
  }

  @Test
  public void testNegativePoints(){
    Point pt1 = new Point(-2.7, -3.2);
    Point pt2 = new Point(-1.3, -4.5);

    //assert pt1.distance(pt2) == 0.0;
    Assert.assertEquals(pt1.distance(pt2), 1.91049731745428);
  }

  @Test
  public void testDifferentPoints(){
    Point pt1 = new Point(-2.7, 3.2);
    Point pt2 = new Point(1.3, -4.5);

    //assert pt1.distance(pt2) == 0.0;
    Assert.assertEquals(pt1.distance(pt2), 8.676981041814026);
  }
}
