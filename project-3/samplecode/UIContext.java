import java.awt.*;
public interface UIContext {
  //  public abstract void drawCircle(Circle circle);
  public abstract void drawLine(Point point1, Point point2 );
  public abstract void drawLabel(String s, Point p);
  public abstract void drawTriangle(Point point1,  Point point2, Point point3);
  public abstract void drawPolygon(java.util.List<Point> points);
}
