import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Scenery extends JPanel {

    // Declare the dimension as a private and static variable so it isn't
    // recalculated every time getPreferredSize is called
    private static final Dimension SIZE = new Dimension(1280, 720);

    // Private instance variables for season and time
    private boolean isDay;
    private String season;

    // 4 colors are changed depending on the season and time
    private Color sky;
    private Color grass;
    private Color windowColor;

    // Since the RGB code for trees are randomly generenated in drawTrees, we
    // provide an array that the random generation can use as a base color
    private int[] trees = new int[3];

    public Scenery(boolean isDay, String season) {
        // Set method parameters as instance variables
        this.season = season;
        this.isDay = isDay;


        if (isDay) {
            windowColor = new Color(173, 216, 230);
            // Use switch case statement instead of if-else statement for more structured
            // and faster code; change the 4 core colors based on input
            switch (season) {
                case "winter":
                    sky = new Color(190, 210, 225);
                    grass = new Color(199, 221, 199);

                    trees[0] = 85;
                    trees[1] = 70;
                    trees[2] = 60;
                    break;
                case "spring":
                    sky = new Color(135, 206, 235);
                    grass = new Color(124, 252, 0);

                    trees[0] = 20;
                    trees[1] = 175;
                    trees[2] = 70;
                    break;
                case "summer":
                    sky = new Color(70, 160, 230);
                    grass = new Color(60, 150, 40);

                    trees[0] = 34;
                    trees[1] = 110;
                    trees[2] = 34;
                    break;
                case "fall":
                    sky = new Color(160, 195, 215);
                    grass = new Color(160, 150, 70);

                    trees[0] = 204;
                    trees[1] = 128;
                    trees[2] = 20;
                    break;
            }
        } else {
            windowColor = Color.YELLOW;
            switch (season) {
                case "winter":
                    sky = new Color(15, 22, 45);
                    grass = new Color(90, 100, 91);

                    trees[0] = 30;
                    trees[1] = 28;
                    trees[2] = 28;
                    break;
                case "spring":
                    sky = new Color(25, 35, 75);
                    grass = new Color(40, 70, 45);

                    trees[0] = 35;
                    trees[1] = 60;
                    trees[2] = 45;
                    break;
                case "summer":
                    sky = new Color(15, 20, 60);
                    grass = new Color(30, 60, 35);

                    trees[0] = 20;
                    trees[1] = 50;
                    trees[2] = 30;
                    break;
                case "fall":
                    sky = new Color(20, 25, 50);
                    grass = new Color(55, 55, 35);

                    trees[0] = 60;
                    trees[1] = 40;
                    trees[2] = 25;
                    break;
            }
        }

        setFocusable(true);
        setLayout(null);
    }

    // Return SIZE constant defined above
    @Override
    public Dimension getPreferredSize() {
        return SIZE;
    }

    // Call the main methods in paintComponenet
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        drawSky(g);
        drawBuildings(g);
        drawTrees(g);
        drawFlowers(g);
        drawAnimals(g);
    }

    // Draw grass & river in the lower third and the sky in the upper two thirds
    private void drawBackground(Graphics g) {
        g.setColor(sky);
        g.fillRect(0, 0, 1280, 480);

        g.setColor(grass);
        g.fillRect(0, 480, 1280, 240);

        g.setColor(new Color(33, 130, 141));
        int[] xPoints = { 540, 740, 690, 590 };
        int[] yPoints = { 720, 720, 480, 480 };
        g.fillPolygon(xPoints, yPoints, 4);
    }

    // Draws sky with birds and other objects depending on time
    public void drawSky(Graphics g) {
        for (byte i = 0; i < 3; i++) {
            int xPos = (int) (Math.random() * 800) + 400;
            int yPos = (int) (Math.random() * 100);
            int width = (int) (Math.random() * 20) + 40;
            int height = (int) (Math.random() * 10) + 20;

            g.setColor(Color.BLACK);
            g.fillArc(xPos, yPos, width, height, 0, 180);
            g.setColor(sky);
            g.fillArc(xPos, yPos + height / 5, width, height, 0, 180);

            g.setColor(Color.BLACK);
            g.fillArc(xPos + width - 5, yPos, width, height, 0, 180);
            g.setColor(sky);
            g.fillArc(xPos + width - 5, yPos + height / 5, width, height, 0, 180);
        }

        // Draw the sun and clouds at random positions if it is day
        // Draw stars at random positions if it is night
        if (isDay) {
            for (byte i = 0; i < 5; i++) {
                int xPos = (int) (Math.random() * 1280);
                int yPos = (int) (Math.random() * 200);
                int x = (int) (Math.random() * 50) + 200;
                int y = (int) (Math.random() * 50) + 50;
                g.setColor(Color.WHITE);
                g.fillOval(xPos, yPos, x, y);
            }

            g.setColor(Color.YELLOW);
            g.fillOval(-100, -100, 250, 250);
        } else {
            for (byte i = 0; i < 50; i++) {
                int xPos = (int) (Math.random() * 1280);
                int yPos = (int) (Math.random() * 150);
                g.setColor(Color.WHITE);
                g.fillOval(xPos, yPos, 5, 5);
            }
        }
    }

    // Draw buildings with random coordinates and size in middle third of screen
    private void drawBuildings(Graphics g) {
        // Draw 100 buildings
        for (byte i = 0; i < 100; i++) {
            // (256 - 150) / 2 = 53 is the lowest acceptable grey value since 150 is average
            int grayValue = (int) (Math.random() * 150) + 53;
            g.setColor(new Color(grayValue, grayValue, grayValue));

            // Buildings occupy middle third of the screen (1280 / 3 = 240)
            int height = (int) (Math.random() * 140) + 100; // Height is between 100 and 240
            int width = (int) (Math.random() * 70) + 50; // Width is between 50 and 120 (half of the height)
            int xPos = (int) (Math.random() * 1280); // Buidlings can occupy the entire width of the screen
            int yPos = 480 - height; // 480 = 2/3 of screen; 480 - height makes buildings touch bottom third

            // Draw buildings and outline
            g.fillRect(xPos, yPos, width, height);
            g.setColor(Color.BLACK);
            g.drawRect(xPos, yPos, width, height);

            // Draw windows on each building (3 x 4 grid)
            for (byte j = 0; j < 4; j++) {
                for (byte k = 0; k < 3; k++) {
                    // There are 7 width sections (3 windows and 4 blank sections) so we generate
                    // x coords with the following coordinates
                    g.setColor(windowColor);
                    g.fillRect(xPos + (width / 7) + (k * width * 2 / 7), yPos + (height / 9) + (j * height * 2 / 9),
                            width / 6, height / 8);

                    // There are 9 width sections (4 windows and 5 blank sections) so we generate
                    // y coords with the following coordinates
                    g.setColor(Color.BLACK);
                    g.drawRect(xPos + (width / 7) + (k * width * 2 / 7), yPos + (height / 9) + (j * height * 2 / 9),
                            width / 6, height / 8);
                }
            }
        }
    }

    // Draws trees and random positions on the screen
    private void drawTrees(Graphics g) {
        Color bark = new Color(102, 91, 78);

        for (int i = 0; i < 150; i++) {
            int yPos = (int) (Math.random() * 200) + 450;
            int size = (int) (Math.random() * 40) + 75;
            int xPos;

            // Generate random colors based on base tree color assigned in constructor
            int r = (int) (Math.random() * 40) + trees[0] - 20;
            int gColor = (int) (Math.random() * 40) + trees[1] - 20; // using g interferes with graphics variable
            int b = (int) (Math.random() * 40) + trees[2] - 20;

            // Make a gap in the trees to make room for the river
            if (i < 75) {
                xPos = (int) (Math.random() * 420);
            } else {
                xPos = 1280 - (int) (Math.random() * 420) - size;
            }

            // Draw trees and their trunks
            g.setColor(bark);
            g.fillRect((int) (xPos + (size * 0.375)), yPos + size - 50, (int) (size * 0.25), 1280 - yPos);
            g.setColor(Color.BLACK);
            g.drawRect((int) (xPos + (size * 0.375)), yPos + size - 50, (int) (size * 0.25), 1280 - yPos);

            g.setColor(new Color(r, gColor, b));
            g.fillOval(xPos, yPos, size, size);
            g.setColor(Color.BLACK);
            g.drawOval(xPos, yPos, size, size);
        }
    }

    // Draw 3 flowers at random positions near the top left of the river
    private void drawFlowers(Graphics g) {
        Color flowerStem = new Color(178, 212, 178);

        for (byte i = 0; i < 3; i++) {
            int xPos = (int) (Math.random() * 50) + 520;
            int yPos = (int) (Math.random() * 50) + 470;

            g.setColor(flowerStem);
            g.fillRect(xPos + 7, yPos + 10, 6, 25);
            g.setColor(Color.RED);
            g.fillOval(xPos, yPos, 20, 20);
            g.setColor(Color.YELLOW);
            g.fillOval(xPos + 5, yPos + 5, 10, 10);
        }
    }

    // Draw pig and fish
    private void drawAnimals(Graphics g) {
        /* Draw Pig */
        Color pig = new Color(253, 215, 228);

        // draw legs
        g.setColor(pig);
        g.fillRect(735, 525, 5, 10);
        g.fillRect(752, 525, 5, 10);
        g.setColor(Color.BLACK);
        g.drawRect(735, 525, 5, 10);
        g.drawRect(752, 525, 5, 10);

        // draw body
        g.setColor(pig);
        g.fillOval(730, 510, 30, 20);
        g.setColor(Color.BLACK);
        g.drawOval(730, 510, 30, 20);

        // draw head
        g.setColor(pig);
        g.fillOval(720, 500, 20, 20);
        g.setColor(Color.BLACK);
        g.drawOval(720, 500, 20, 20);

        // draw eyes and snout
        g.fillOval(724, 505, 4, 4);
        g.fillOval(730, 505, 4, 4);
        g.drawOval(725, 510, 8, 6);

        // draw fish
        g.setColor(Color.ORANGE);
        g.fillOval(640, 580, 30, 20);
        int[] xPoints = { 630, 640, 630 };
        int[] yPoints = { 580, 590, 600 };
        g.fillPolygon(xPoints, yPoints, 3);

        g.setColor(Color.BLACK);
        g.fillOval(660, 585, 6, 6);
    }
}
