import java.util.List;
import java.util.ArrayList;

public class Grid {
    private Cell[][] cells;
    private List<Agent> agents;

    public Grid(int size) {
        this.cells = new Cell[size][size];
        this.agents = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.cells[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void addAgent(Agent agent) {
        this.agents.add(agent);
        this.cells[agent.getX()][agent.getY()].setAgent(agent);
    }

    public boolean isCellEmpty(int x, int y) {
        return this.cells[x][y].getAgent() == null;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    private void removeAgentFromCell(int x, int y) {
        this.cells[x][y].setAgent(null);
    }

    public void moveAgentsSimple() {
        for (Agent agent : this.agents) {
            int x = agent.getX();
            int y = agent.getY();
            agent.moveSimple(this);
            removeAgentFromCell(x, y);
            this.cells[agent.getX()][agent.getY()].setAgent(agent);
            if (agent.getX() == x && agent.getY() == y) {
                System.out.println(agent.getId() + " is stuck at (" + agent.getX() + ", " + agent.getY() + ")");
            } else {
                System.out.println(agent.getId() + " moved to (" + agent.getX() + ", " + agent.getY() + ")");
            }
        }
    }

    public boolean isDone() {
        for (Agent agent : this.agents) {
            if (agent.getX() != agent.getTargetX() || agent.getY() != agent.getTargetY()) {
                return false;
            }
        }
        return true;
    }

    public void printGrid() {
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells[i].length; j++) {
                System.out.print(" ");
                if (this.cells[i][j].getAgent() != null) {
                    System.out.print(this.cells[i][j].getAgent().getId());
                } else {
                    System.out.print(".");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
