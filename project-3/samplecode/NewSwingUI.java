import java.awt.*;
public class NewSwingUI implements UIContext {
  private Graphics graphics;
  private static NewSwingUI swingUI;
  private NewSwingUI() {
  }
  public static NewSwingUI getInstance() {
    if (swingUI == null) {
      swingUI = new NewSwingUI();
    }
    return swingUI;
  }
  public  void setGraphics(Graphics graphics) {
    this.graphics = graphics;
  }
  public void drawLabel(String s, Point p) {
    if (p != null) {
      if (s != null) {
        graphics.drawString(s, (int) p.getX(), (int) p.getY());
      }
    }
    int length = graphics.getFontMetrics().stringWidth(s);
    graphics.drawString("_", (int)p.getX() + length, (int)p.getY());
  }
  public void drawLine(Point point1,  Point point2) {
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    if (point1 != null) {
      i1 = Math.round((float) (point1.getX()));
      i2 = Math.round((float) (point1.getY()));
      if (point2 != null) {
        i3 = Math.round((float) (point2.getX()));
        i4 = Math.round((float) (point2.getY()));
      } else {
        i3 = i1;
        i4 = i2;
      }
      graphics.drawLine(i1, i2, i3, i4);
    }
  }
  
  public void drawTriangle(Point point1,  Point point2, Point point3) {
    drawLine(point1, point2);
    drawLine(point2, point3);
    drawLine(point3, point1);
  }
  
  public void drawPolygon(java.util.List<Point> points) {
    if (points != null && points.size() > 1) {
      int[] xPoints = new int[points.size()];
      int[] yPoints = new int[points.size()];

      for (int i = 0; i < points.size(); i++) {
        xPoints[i] = (int) points.get(i).getX();
        yPoints[i] = (int) points.get(i).getY();
      }

      graphics.drawPolygon(xPoints, yPoints, points.size());
    }
  }
}
