import java.awt.*;

public class PolygonCommand extends Command {
    private Polygon polygon;
    private int pointCount;

    public PolygonCommand() {
        this.polygon = new Polygon();
        pointCount = 0;
    }

    // Adds a point to the polygon
    public void setPolygonPoint(Point point) {
        polygon.addPoint(point);
        pointCount++;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void execute() {
        model.addItem(polygon);
    }

    
    public boolean undo() {
        model.removeItem(polygon);
        return true;
    }

    public boolean redo() {
        execute();
        return true;
    }

    public boolean end() {
        if (polygon.getPoints().size() < 2) {
            undo();
            return false;
        }
        return true;
    }
}
