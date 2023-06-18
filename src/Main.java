import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(5);

        Color c1 = new Color(7, 189, 7);
        Color c2 = new Color(117, 23, 147);
        Color c3 = new Color(22, 152, 171);

        Agent agentA = new Agent("A", 0, 0, 4, 4, c1);
        Agent agentB = new Agent("B", 0, 2, 2, 0, c2);
        Agent agentC = new Agent("C", 2, 1, 1, 3, c3);

        grid.addAgent(agentA);
        grid.addAgent(agentB);
        grid.addAgent(agentC);

        grid.printGrid();
        GridFrame gridFrame = new GridFrame(grid);
        while (!grid.isDone()) {
            grid.moveAgentsSimple();
            System.out.println("=================");
            grid.printGrid();
            gridFrame.update();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}