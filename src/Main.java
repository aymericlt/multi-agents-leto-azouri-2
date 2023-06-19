import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Grid grid = setup3();

        // Génération de gridFrame pour l'affichage graphique
        GridFrame gridFrame = new GridFrame(grid);
        grid.gridFrame = gridFrame;
//        grid.printGrid();
//        gridFrame.update();

        for (Agent agent : grid.getAgents()) {
            Thread newThread = new Thread(agent);
            newThread.start();
        }


    }

    public static Grid setup1() {
        Grid grid = new Grid(5);

        Color c1 = new Color(7, 189, 7);
        Color c2 = new Color(117, 23, 147);
        Color c3 = new Color(22, 152, 171);
        Color c4 = new Color(231, 209, 6);

        NaiveAgent agentA = new NaiveAgent("A", 0, 0, 4, 4, c1, grid);
        NaiveAgent agentB = new NaiveAgent("B", 0, 2, 2, 0, c2, grid);
        NaiveAgent agentC = new NaiveAgent("C", 2, 1, 1, 3, c3, grid);
        NaiveAgent agentD = new NaiveAgent("D", 4, 1, 3, 3, c4, grid);

        grid.addAgent(agentA);
        grid.addAgent(agentB);
        grid.addAgent(agentC);
        grid.addAgent(agentD);

        return grid;
    }

    public static Grid setup2() {
        Grid grid = new Grid(5);

        Color c1 = new Color(7, 189, 7);
        Color c2 = new Color(117, 23, 147);
        Color c3 = new Color(22, 152, 171);
        Color c4 = new Color(231, 209, 6);

        NaiveAgent agentA = new NaiveAgent("A", 0, 2, 0, 3, c1, grid);
        NaiveAgent agentB = new NaiveAgent("B", 1, 2, 1, 3, c2, grid);
        NaiveAgent agentC = new NaiveAgent("C", 2, 2, 2, 3, c3, grid);
        NaiveAgent agentD = new NaiveAgent("D", 0, 0, 0, 4, c4, grid);

        grid.addAgent(agentA);
        grid.addAgent(agentB);
        grid.addAgent(agentC);
        grid.addAgent(agentD);

        return grid;
    }

    public static Grid setup3() {
        Grid grid = new Grid(5);

        Color c1 = new Color(7, 189, 7);
        Color c2 = new Color(117, 23, 147);
        Color c3 = new Color(22, 152, 171);
        Color c4 = new Color(231, 209, 6);

        CommunicativeAgent agentA = new CommunicativeAgent("A", 0, 2, 0, 3, c1, grid);
        CommunicativeAgent agentB = new CommunicativeAgent("B", 1, 2, 1, 3, c2, grid);
        CommunicativeAgent agentC = new CommunicativeAgent("C", 2, 2, 2, 3, c3, grid);
        CommunicativeAgent agentD = new CommunicativeAgent("D", 0, 0, 0, 4, c4, grid);

        grid.addAgent(agentA);
        grid.addAgent(agentB);
        grid.addAgent(agentC);
        grid.addAgent(agentD);

        return grid;
    }
}