import java.awt.*;
import java.util.HashMap;

public class NaiveAgent extends Agent {
    public NaiveAgent(String id, int startX, int startY, int targetX, int targetY, Color color, Grid grid) {
        super(id, startX, startY, targetX, targetY, color, grid);
    }

    @Override
    public void run() {
        this.mailBox.put(this.getId(), "OK");
        while (!this.isArrived()) {
            synchronized (mailBox) {
                NaiveMove();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private synchronized void NaiveMove() {
        int previousX = x;
        int previousY = y;
        boolean moved = false;

        if (this.x < this.targetX) {
            if (grid.isCellEmpty(this.x + 1, this.y)) {
                this.x++;
                moved = true;
            }
        } else if (this.x > this.targetX) {
            if (grid.isCellEmpty(this.x - 1, this.y)) {
                this.x--;
                moved = true;
            }
        }

        if (this.y < this.targetY) {
            if (grid.isCellEmpty(this.x, this.y + 1) && !moved) {
                this.y++;
                moved = true;
            }
        } else if (this.y > this.targetY) {
            if (grid.isCellEmpty(this.x, this.y - 1) && !moved) {
                this.y--;
                moved = true;
            }
        }

        if (!grid.moveAgent(previousX, previousY, x, y)) {
            System.out.println(this.getId() + " is stuck");
        } else {
            System.out.println(this.getId() + " moved to " + x + " " + y);
            this.grid.printGrid();
            try {
                this.grid.gridFrame.update();
            } catch (Error e) {
                e.printStackTrace();
            }
        }
    }

}
