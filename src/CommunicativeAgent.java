import java.awt.*;

public class CommunicativeAgent extends Agent {

    public CommunicativeAgent(String id, int startX, int startY, int targetX, int targetY, Color color, Grid grid) {
        super(id, startX, startY, targetX, targetY, color, grid);
    }

    @Override
    public void run() {
        this.mailBox.put(this.getId(), "STOP");
        System.out.println(mailBox);
        while (!this.isArrived()) {
//            synchronized (this.mailBox) {
//                boolean canMove = true;
//                for (String key : mailBox.keySet()) {
//                    String value = mailBox.get(key);
//                    if (value != "STOP") {
//                        canMove = false;
//                        break;
//                    }
//                }
//
//                if (canMove) {
//                    this.mailBox.replace(this.getId(), "MOVE");
//                }
//
//            }
            synchronized (mailBox) {
                CommunicativeMove();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

//        if (this.getId() != null) {
//            System.out.println("Direction pour " + this.getId() + " : " + this.getDirection());
//            CommunicativeMove();
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }

    private Direction getDirection() {
        return AStar.getNextMove(grid, this, grid.getCells()[this.targetX][this.targetY]);
    }

    private synchronized void CommunicativeMove() {
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
