public class Cell {
    public int x;
    public int y;
    private Agent agent;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // getters and setters here
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
