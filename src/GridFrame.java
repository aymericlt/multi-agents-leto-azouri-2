import javax.swing.*;
import java.awt.*;

public class GridFrame extends JFrame {
    private Grid grid;
    private JPanel panel;

    public GridFrame(Grid grid) {
        this.grid = grid;
        setTitle("Grid");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(grid.getCells().length, grid.getCells()[0].length));
        populatePanel();

        add(panel);
        setVisible(true);
    }

    private void populatePanel() {
        panel.removeAll();
        for (int i = 0; i < grid.getCells().length; i++) {
            for (int j = 0; j < grid.getCells()[i].length; j++) {
                Cell cell = grid.getCells()[i][j];
                Agent agent = cell.getAgent();
                JLabel cellLabel = new JLabel("", SwingConstants.CENTER);
                cellLabel.setBorder(BorderFactory.createLineBorder(Color.black));
                cellLabel.setOpaque(true);
                for (Agent a : grid.getAgents()) {
                    if (a.getTargetX() == i && a.getTargetY() == j) {
                        cellLabel.setBackground(this.lightColor(a.getColor()));
                    }
                }
                if (agent != null) {
                    cellLabel.setFont(new Font("Serif", Font.BOLD, 32));
                    cellLabel.setText(agent.getId());
                    cellLabel.setForeground(agent.getColor());
                }
                panel.add(cellLabel);
            }
        }
    }


    public synchronized void update() {
        populatePanel();
        panel.revalidate();
        panel.repaint();
    }

    private Color lightColor(Color color) { // Création d'un couleur plus claire à partir de la couleur entrée pour créer du contraste
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        // Calcul des nouvelles valeurs des composantes RGB
        int newRed = (int) (red + (255 - red) * 0.6);
        int newGreen = (int) (green + (255 - green) * 0.6);
        int newBlue = (int) (blue + (255 - blue) * 0.6);

        // Vérification et ajustement des valeurs
        newRed = Math.min(newRed, 255);
        newGreen = Math.min(newGreen, 255);
        newBlue = Math.min(newBlue, 255);

        return new Color(newRed, newGreen, newBlue);
    }
}
