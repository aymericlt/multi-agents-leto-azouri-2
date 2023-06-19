import java.awt.*;

public class AStarAgent extends Agent {

    public AStarAgent(String id, int startX, int startY, int targetX, int targetY, Color color, Grid grid) {
        super(id, startX, startY, targetX, targetY, color, grid);
    }

    @Override
    public void run() {
        this.mailBox.put(this.getId(), "OK");
        System.out.println(mailBox);
        while (!this.isArrived()) {
            synchronized (mailBox) {
                AStarMove();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (getDirection() == Direction.BLOCKED) { //Evite de retourner bloquer directement la fonction syncrhonized dans le cas bloqué
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private Direction getDirection() {
        return AStar.getNextMove(grid, this, grid.getCells()[this.targetX][this.targetY]);
    }

    private synchronized void AStarMove() {
        int previousX = x;
        int previousY = y;
        boolean moved = false;

        Direction direction = getDirection();

        switch (direction) {
            case UP:
                if (grid.isCellEmpty(this.x, this.y + 1)) {
                    this.y++;
                    moved = true;
                }
                break;
            case DOWN:
                if (grid.isCellEmpty(this.x, this.y - 1)) {
                    this.y--;
                    moved = true;
                }
                break;
            case LEFT:
                if (grid.isCellEmpty(this.x - 1, this.y)) { //A vérifier
                    this.x--;
                    moved = true;
                }
                break;
            case RIGHT:
                if (grid.isCellEmpty(this.x + 1, this.y)) { //A vérifier
                    this.x++;
                    moved = true;
                }
                break;
            case BLOCKED:
                break;
        }

        if (!moved) {
            System.out.println(this.getId() + " is stuck");
        } else {
            System.out.println(this.getId() + " moved to " + x + " " + y);
            grid.moveAgent(previousX, previousY, x, y);
            this.grid.printGrid();
            try {
                this.grid.gridFrame.update();
            } catch (Error e) {
                e.printStackTrace();
            }
        }

        ;
    }
}
