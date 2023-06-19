class Node {
    public Cell cell;
    public double g;
    public double h;
    public Node parent;

    public Node(Cell cell, Node parent, double g, double h) {
        this.cell = cell;
        this.parent = parent;
        this.g = g;
        this.h = h;
    }

    public double getF() {
        return this.g + this.h;
    }
}

enum Direction {
    UP, DOWN, LEFT, RIGHT, BLOCKED, NONE
}
