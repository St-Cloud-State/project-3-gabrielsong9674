import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PolygonButton extends JButton implements ActionListener {
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private PolygonCommand polygonCommand;
    private UndoManager undoManager;

    public PolygonButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Polygon");
        this.undoManager = undoManager;
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        mouseHandler = new MouseHandler();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        drawingPanel.addMouseListener(mouseHandler);
    }

    private class MouseHandler extends MouseAdapter {
        //private int pointCount = 0;
        private Point lastPoint = null;

        @Override
        public void mouseClicked(MouseEvent event) {
            if (SwingUtilities.isLeftMouseButton(event)) {
                Point currentPoint = View.mapPoint(event.getPoint());
                if (polygonCommand == null) {
                    polygonCommand = new PolygonCommand();
                    undoManager.beginCommand(polygonCommand);
                }

                polygonCommand.setPolygonPoint(currentPoint);

                if (lastPoint != null) {
                    Graphics g = drawingPanel.getGraphics();
                    g.setColor(Color.BLUE);
                    g.drawLine(lastPoint.x, lastPoint.y, currentPoint.x, currentPoint.y);
                }

                lastPoint = currentPoint;

            } else if (SwingUtilities.isRightMouseButton(event)) {
                if (polygonCommand != null && polygonCommand.getPolygon().getPoints().size() > 1) {
                    polygonCommand.end();
                    drawingPanel.removeMouseListener(this);
                    view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    undoManager.endCommand(polygonCommand);
                    polygonCommand = null;
                    lastPoint = null; 
                    view.refresh(); 
                }
            }
        }
    }
}
