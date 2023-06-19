import java.awt.*;

public class CommunicativeAgent extends Agent {

    public CommunicativeAgent(String id, int startX, int startY, int targetX, int targetY, Color color, Grid grid) {
        super(id, startX, startY, targetX, targetY, color, grid);
    }

    @Override
    public void run() {
        this.mailBox.put(this.getId(), "OK");
        System.out.println(mailBox);
        boolean continuePlaying = true;

        while (continuePlaying) {
            synchronized (mailBox) {
                boolean gameBlocked = false;
                for (String key : mailBox.keySet()) {
                    if (!mailBox.get(key).equals("OK")) {
                        gameBlocked = true;
                    }
                }

                System.out.println("(" + this.getId() + ") " + mailBox);

                if (!gameBlocked) {
                    CommunicativeMove();

                    if (getDirection() == Direction.BLOCKED && getBlockingAgent().isArrived()) {
                        System.out.println("I am " + getId() + " and I am blocked by " + getBlockingAgent().getId());
                        this.mailBox.put(getBlockingAgent().getId(), "AWAY");
                        this.mailBox.put(this.getId(), "BLOCKED");
                    }
                } else { //Jeu bloqué, c'est à dire qu'un joueur est bloqué et un autre doit se décaler
                    if (mailBox.get(this.getId()) == "BLOCKED") {
                        System.out.println("Waiting for " + getBlockingAgent().getId() + " to move");
                    } else if (mailBox.get(this.getId()) == "AWAY") {


                        System.out.println("I am " + getId() + " and I am moving away");

                        randomMove(this);

                        for (String key : mailBox.keySet()) {
                            if (mailBox.get(key).equals("BLOCKED")) {
                                this.mailBox.replace(key, "TRYAGAIN");
                            }
                        }

                    } else if (mailBox.get(this.getId()) == "TRYAGAIN") {
                        System.out.println("I am " + getId() + " and I will try again");
                        CommunicativeMove();
                        if (getDirection() == Direction.BLOCKED) {
                            System.out.println(getId() + " is till blocked");
                            this.mailBox.put(this.getId(), "BLOCKED");
                        } else {
                            System.out.println(getId() + " is moving");
                        }

                        if (this.isArrived())  {
                            this.mailBox.put(this.getId(), "OK");
                            for (String key : mailBox.keySet()) {
                                if (mailBox.get(key).equals("AWAY")) {
                                    this.mailBox.replace(key, "OK");
                                }
                            }
                        }


                    }
                }


//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            continuePlaying = false;
            for (Agent agent : grid.getAgents()) {
                if (!agent.isArrived()) {
                    continuePlaying = true;
                }
            }
        }

        System.out.println("I am " + getId() + " and I am arrived");
    }



    public void randomMove(Agent agent) {
        int previousX = x;
        int previousY = y;
        boolean moved = false;
        System.out.println("Trying to move " + agent.getId());
        while (!moved) {
            int random = (int) (Math.random() * 4);
            try {
                switch (random) {
                    case 0:
                        if (grid.isCellEmpty(agent.x + 1, agent.y)) {
                            agent.x++;
                            moved = true;
                        }
                        break;
                    case 1:
                        if (grid.isCellEmpty(agent.x - 1, agent.y)) {
                            agent.x--;
                            moved = true;
                        }
                        break;
                    case 2:
                        if (grid.isCellEmpty(agent.x, agent.y + 1)) {
                            agent.y++;
                            moved = true;
                        }
                        break;
                    case 3:
                        if (grid.isCellEmpty(agent.x, agent.y - 1)) {
                            agent.y--;
                            moved = true;
                        }
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Out of bounds");
            }

        }


        if (!grid.moveAgent(previousX, previousY, x, y)) {
//            System.out.println(agent.getId() + " is stuck");
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
    }

    private Direction getDirection() {
        return AStar.getNextMove(grid, this, grid.getCells()[this.targetX][this.targetY]);
    }

//    public Agent getBlockingAgent() {
//        int deltaX = targetX - this.x;
//        int deltaY = targetY - this.y;
//
//        // Determine the direction to move in
//        int stepX = (deltaX != 0) ? (int) Math.signum(deltaX) : 0;
//        int stepY = (deltaY != 0) ? (int) Math.signum(deltaY) : 0;
//
//        // Start checking from the next cell
//        int currentX = this.x + stepX;
//        int currentY = this.y + stepY;
//
//        // Check for an agent in the path in a straight line towards the target
//        while (currentX != targetX || currentY != targetY) {
//            // Check if the cell contains an agent
//            if (grid.getCells()[currentX][currentY].getAgent() != null) {
//                return grid.getCells()[currentX][currentY].getAgent(); // Return the blocking agent
//            }
//
//            // Move to the next cell in the direction of the target
//            currentX += stepX;
//            currentY += stepY;
//        }
//
//        // No blocking agent found
//        return null;
//    }

    public Agent getBlockingAgent() {
        int currentX = x;
        int currentY = y;
        int objectiveX = targetX;
        int objectiveY = targetY;
        int[] obstacleCoordinates = new int[2];

        while (currentX != objectiveX || currentY != objectiveY) {
            if (currentX != x || currentY != y) {  // Ne pas vérifier le point de départ
                if (this.grid.getCells()[currentX][currentY].getAgent() != null) {  // Si un obstacle est rencontré
                    return this.grid.getCells()[currentX][currentY].getAgent();
                }
            }

            if (currentX < objectiveX)
                currentX++;
            else if (currentX > objectiveX)
                currentX--;

            if (currentY < objectiveY)
                currentY++;
            else if (currentY > objectiveY)
                currentY--;
        }

        return null;  // Aucun obstacle trouvé
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
                if (grid.isCellEmpty(this.x - 1, this.y)) {
                    this.x--;
                    moved = true;
                }
                break;
            case RIGHT:
                if (grid.isCellEmpty(this.x + 1, this.y)) {
                    this.x++;
                    moved = true;
                }
                break;
            case BLOCKED:
                break;
        }

        if (!moved) {
            if (!this.isArrived()) {
                System.out.println(this.getId() + " is stuck");
            }
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
