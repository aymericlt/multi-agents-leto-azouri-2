import java.awt.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Agent implements Runnable {
    public String id;
    public int x;
    public int y;
    public int targetX;
    public int targetY;

    private Color color;

    public Grid grid;

    public ConcurrentHashMap<String, String> mailBox;

    public Agent(String id, int startX, int startY, int targetX, int targetY, Color color, Grid grid) {
        this.id = id;
        this.x = startX;
        this.y = startY;
        this.targetX = targetX;
        this.targetY = targetY;
        this.color = color;
        this.grid = grid;
        this.mailBox = grid.getGridMailBox();
    }

    public abstract void run();

    // getters and setters here
    public String getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTargetX() {
        return targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public Color getColor() {
        return color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public boolean isArrived() {
        return (x == targetX && y == targetY);
    }

}
