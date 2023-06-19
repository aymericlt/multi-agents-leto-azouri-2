import java.util.ArrayList;
import java.util.List;

class AStar {

    public static Direction getNextMove(Grid grid, Agent agent, Cell targetCell) {
        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();

        Node startNode = new Node(grid.getCells()[agent.x][agent.y], null, 0, getManhattanDistance(agent.x, agent.y, targetCell.x, targetCell.y));
        openList.add(startNode);

        while (!openList.isEmpty()) {
            // Find the node with the lowest f score
            Node currentNode = openList.get(0);
            for (Node node : openList) {
                if (node.getF() < currentNode.getF()) {
                    currentNode = node;
                }
            }

            // Check if reached target
            if (currentNode.cell.x == targetCell.x && currentNode.cell.y == targetCell.y) {
                // We found the target, now backtrack to find the first move
                Node node = currentNode;
                while (node.parent != null && !node.parent.cell.equals(grid.getCells()[agent.x][agent.y])) {
                    node = node.parent;
                }
                return getDirection(grid.getCells()[agent.x][agent.y], node.cell);
            }

            openList.remove(currentNode);
            closedList.add(currentNode);

            // Check neighbors
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (Math.abs(dx) + Math.abs(dy) == 1) {
                        int newX = currentNode.cell.x + dx;
                        int newY = currentNode.cell.y + dy;

                        if (newX >= 0 && newX < grid.getCells().length && newY >= 0 && newY < grid.getCells()[0].length) {
                            if (grid.getCells()[newX][newY].getAgent() != null && !(newX == targetCell.x && newY == targetCell.y)) {
                                // Blocked by another agent
                                continue;
                            }

                            double newG = currentNode.g + 1;
                            double newH = getManhattanDistance(newX, newY, targetCell.x, targetCell.y);
                            Node newNode = new Node(grid.getCells()[newX][newY], currentNode, newG, newH);

                            // Check if this node is already in closedList
                            boolean inClosedList = false;
                            for (Node closedNode : closedList) {
                                if (closedNode.cell.equals(newNode.cell) && closedNode.g <= newG) {
                                    inClosedList = true;
                                    break;
                                }
                            }
                            if (inClosedList) continue;

                            // Check if new path to neighbor is better
                            boolean inOpenList = false;
                            for (Node openNode : openList) {
                                if (openNode.cell.equals(newNode.cell) && openNode.g <= newG) {
                                    inOpenList = true;
                                    break;
                                }
                            }

                            if (!inOpenList) {
                                openList.add(newNode);
                            }
                        }
                    }
                }
            }
        }

        return Direction.BLOCKED; // No path found
    }

    private static double getManhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static Agent getObstacle(Grid grid, Agent agent, Cell targetCell) {
        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();

        Node startNode = new Node(grid.getCells()[agent.x][agent.y], null, 0, getManhattanDistance(agent.x, agent.y, targetCell.x, targetCell.y));
        openList.add(startNode);

        while (!openList.isEmpty()) {
            // Find the node with the lowest f score
            Node currentNode = openList.get(0);
            for (Node node : openList) {
                if (node.getF() < currentNode.getF()) {
                    currentNode = node;
                }
            }

            openList.remove(currentNode);
            closedList.add(currentNode);

            // Check neighbors
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (Math.abs(dx) + Math.abs(dy) == 1) {
                        int newX = currentNode.cell.x + dx;
                        int newY = currentNode.cell.y + dy;

                        if (newX >= 0 && newX < grid.getCells().length && newY >= 0 && newY < grid.getCells()[0].length) {
                            if (grid.getCells()[newX][newY].getAgent() != null && !(newX == targetCell.x && newY == targetCell.y)) {
                                // Blocked by another agent
                                return grid.getCells()[newX][newY].getAgent(); // Return the blocking agent
                            }

                            double newG = currentNode.g + 1;
                            double newH = getManhattanDistance(newX, newY, targetCell.x, targetCell.y);
                            Node newNode = new Node(grid.getCells()[newX][newY], currentNode, newG, newH);

                            // Check if this node is already in closedList
                            boolean inClosedList = false;
                            for (Node closedNode : closedList) {
                                if (closedNode.cell.equals(newNode.cell) && closedNode.g <= newG) {
                                    inClosedList = true;
                                    break;
                                }
                            }
                            if (inClosedList) continue;

                            // Check if new path to neighbor is better
                            boolean inOpenList = false;
                            for (Node openNode : openList) {
                                if (openNode.cell.equals(newNode.cell) && openNode.g <= newG) {
                                    inOpenList = true;
                                    break;
                                }
                            }

                            if (!inOpenList) {
                                openList.add(newNode);
                            }
                        }
                    }
                }
            }
        }

        return null; // No blocking agent found
    }

    private static Direction getDirection(Cell from, Cell to) {
        if (from.x < to.x) {
            return Direction.RIGHT;
        } else if (from.x > to.x) {
            return Direction.LEFT;
        } else if (from.y < to.y) {
            return Direction.UP;
        } else if (from.y > to.y) {
            return Direction.DOWN;
        }
        return Direction.NONE;
    }
}
