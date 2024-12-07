import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polygon extends Item {

    private List<Point> points;

    public Polygon() {
        points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public boolean includes(Point point) {
        for (Point p : points) {
            if (distance(point, p) < 10.0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void render(UIContext uiContext) {
        if (points.size() < 2) return;
        for (int i = 0; i < points.size() - 1; i++) {
            uiContext.drawLine(points.get(i), points.get(i + 1));
        }

        if (points.size() > 2) {
            uiContext.drawLine(points.get(points.size() - 1), points.get(0));
        }
    }

    @Override
    public void translate(int dx, int dy) {
        for (Point p : points) {
            p.translate(dx, dy);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Polygon: ");
        for (Point p : points) {
            sb.append(p.toString()).append(" ");
        }
        return sb.toString();
    }
}
