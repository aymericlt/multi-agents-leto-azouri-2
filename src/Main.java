import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    private final static List<String> myColors = Arrays.asList("#000000","#00FF00","#0000FF","#FF0000","#01FFFE","#FFA6FE","#FFDB66","#006401","#010067","#95003A","#007DB5","#FF00F6","#FFEEE8","#774D00","#90FB92","#0076FF","#D5FF00","#FF937E","#6A826C","#FF029D","#FE8900","#7A4782","#7E2DD2","#85A900","#FF0056","#A42400");
    public static void main(String[] args) {
        Grid grid = setup1();

        // Génération de gridFrame pour l'affichage graphique
        GridFrame gridFrame = new GridFrame(grid);
        grid.gridFrame = gridFrame;


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

        AStarAgent agentA = new AStarAgent("A", 0, 2, 0, 3, c1, grid);
        AStarAgent agentB = new AStarAgent("B", 1, 2, 1, 3, c2, grid);
        AStarAgent agentC = new AStarAgent("C", 2, 2, 2, 3, c3, grid);
        AStarAgent agentD = new AStarAgent("D", 0, 0, 0, 4, c4, grid);

        grid.addAgent(agentA);
        grid.addAgent(agentB);
        grid.addAgent(agentC);
        grid.addAgent(agentD);

        return grid;
    }

    public static Grid setup4() {
        Grid grid = new Grid(5);

        Color c1 = new Color(7, 189, 7);
        Color c2 = new Color(117, 23, 147);
        Color c3 = new Color(22, 152, 171);
        Color c4 = new Color(231, 209, 6);
        Color c5 = new Color(225, 20, 20);
        Color c6 = new Color(93, 93, 93);

        AStarAgent agentA = new AStarAgent("A", 0, 2, 0, 3, c1, grid);
        AStarAgent agentB = new AStarAgent("B", 1, 2, 1, 3, c2, grid);
        AStarAgent agentC = new AStarAgent("C", 2, 2, 2, 3, c3, grid);
        AStarAgent agentE = new AStarAgent("E", 3, 2, 3, 3, c5, grid);
        AStarAgent agentF = new AStarAgent("F", 4, 2, 4, 3, c6, grid);

        AStarAgent agentD = new AStarAgent("D", 0, 0, 0, 4, c4, grid);


        grid.addAgent(agentA);
        grid.addAgent(agentB);
        grid.addAgent(agentC);
        grid.addAgent(agentD);
        grid.addAgent(agentE);
        grid.addAgent(agentF);

        return grid;
    }

    public static Grid setup5() {
        Grid grid = new Grid(5);

        Color c1 = new Color(7, 189, 7);
        Color c2 = new Color(117, 23, 147);
        Color c3 = new Color(22, 152, 171);
        Color c4 = new Color(231, 209, 6);
        Color c5 = new Color(225, 20, 20);
        Color c6 = new Color(93, 93, 93);

        CommunicativeAgent agentA = new CommunicativeAgent("A", 0, 2, 0, 3, c1, grid);
        CommunicativeAgent agentB = new CommunicativeAgent("B", 1, 2, 1, 3, c2, grid);
        CommunicativeAgent agentC = new CommunicativeAgent("C", 2, 2, 2, 3, c3, grid);
        CommunicativeAgent agentE = new CommunicativeAgent("E", 3, 2, 3, 3, c5, grid);
        CommunicativeAgent agentF = new CommunicativeAgent("F", 4, 2, 4, 3, c6, grid);

        CommunicativeAgent agentD = new CommunicativeAgent("D", 2, 0, 0, 4, c4, grid);


        grid.addAgent(agentA);
        grid.addAgent(agentB);
        grid.addAgent(agentC);
        grid.addAgent(agentD);
        grid.addAgent(agentE);
        grid.addAgent(agentF);

        return grid;
    }

    public static Grid setup6() {
        Grid grid = new Grid(5);

        grid.addAgent(new AStarAgent("A", 2, 3, 0, 1, Color.decode(myColors.get(0)), grid));
        grid.addAgent(new AStarAgent("B", 1, 0, 2, 3, Color.decode(myColors.get(1)), grid));
        grid.addAgent(new AStarAgent("C", 3, 2, 1, 2, Color.decode(myColors.get(2)), grid));
        grid.addAgent(new AStarAgent("D", 4, 1, 4, 0, Color.decode(myColors.get(3)), grid));
        grid.addAgent(new AStarAgent("E", 0, 4, 3, 4, Color.decode(myColors.get(4)), grid));
        grid.addAgent(new AStarAgent("F", 4, 3, 0, 2, Color.decode(myColors.get(5)), grid));
        grid.addAgent(new AStarAgent("G", 2, 1, 4, 1, Color.decode(myColors.get(6)), grid));
        grid.addAgent(new AStarAgent("H", 3, 0, 1, 3, Color.decode(myColors.get(7)), grid));
        grid.addAgent(new AStarAgent("I", 1, 4, 3, 0, Color.decode(myColors.get(8)), grid));

        return grid;
    }

    public static Grid setup7() {
        Grid grid = new Grid(5);

        grid.addAgent(new CommunicativeAgent("A", 2, 3, 0, 1, Color.decode(myColors.get(0)), grid));
        grid.addAgent(new CommunicativeAgent("B", 1, 0, 2, 3, Color.decode(myColors.get(1)), grid));
        grid.addAgent(new CommunicativeAgent("C", 3, 2, 1, 2, Color.decode(myColors.get(2)), grid));
        grid.addAgent(new CommunicativeAgent("D", 4, 1, 4, 0, Color.decode(myColors.get(3)), grid));
        grid.addAgent(new CommunicativeAgent("E", 0, 4, 3, 4, Color.decode(myColors.get(4)), grid));
        grid.addAgent(new CommunicativeAgent("F", 4, 3, 0, 2, Color.decode(myColors.get(5)), grid));
        grid.addAgent(new CommunicativeAgent("G", 2, 1, 4, 1, Color.decode(myColors.get(6)), grid));
        grid.addAgent(new CommunicativeAgent("H", 3, 0, 1, 3, Color.decode(myColors.get(7)), grid));
        grid.addAgent(new CommunicativeAgent("I", 1, 4, 3, 0, Color.decode(myColors.get(8)), grid));

        return grid;
    }

    public static Color getRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }

}