import java.util.*;

public class MoveCommand extends Command {
    private Vector<Item> movedItems;
    private int dx, dy;

    public MoveCommand(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
        movedItems = new Vector<>();
        Enumeration<Item> enumeration = model.getSelectedItems();
        while (enumeration.hasMoreElements()) {
            movedItems.add(enumeration.nextElement());
        }
    }

    @Override
    public void execute() {
        for (Item item : movedItems) {
            item.translate(dx, dy);
        }
        model.setChanged();
    }

    @Override
    public boolean undo() {
        for (Item item : movedItems) {
            item.translate(-dx, -dy);
        }
        model.setChanged();
        return true;
    }

    @Override
    public boolean redo() {
        execute();
        return true;
    }
}