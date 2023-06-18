import java.awt.*;

public class Agent {
    private String id;
    private int x;
    private int y;
    private int targetX;
    private int targetY;

    private Color color;

    public Grid grid;

    public Agent(String id, int startX, int startY, int targetX, int targetY, Color color) {
        this.id = id;
        this.x = startX;
        this.y = startY;
        this.targetX = targetX;
        this.targetY = targetY;
        this.color = color;
    }

    public void moveSimple(Grid grid) {
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
    }

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

}
