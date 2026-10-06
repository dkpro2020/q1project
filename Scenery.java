import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Scenery extends JPanel {

    // Created once instead of on every call
    private static final Dimension SIZE = new Dimension(1280, 720);

    private String season;
    private boolean isDay;

    private Color sky;
    private Color grass;
    private int[] trees = new int[3]; // set as an int so indexing can be possible for color variations in tree function


    public Scenery(boolean isDay, String season) {
        this.season = season;
        this.isDay = isDay;        

        switch (season) {
            case "winter":
                if (isDay) {
                    sky = new Color(190, 210, 225);
                    grass = new Color(199, 221, 199);

                    trees[0] = 85;
                    trees[1] = 70;
                    trees[2] = 60;
                } else {
                    sky = new Color(15, 22, 45);
                    grass = new Color(90, 100, 91);

                    trees[0] = 30;
                    trees[1] = 28;
                    trees[2] = 28;
                }
                break;
            case "spring":
                if (isDay) {
                    sky = new Color(135, 206, 235);
                    grass = new Color(124, 252, 0);

                    trees[0] = 20;
                    trees[1] = 175;
                    trees[2] = 70;
                } else {
                    sky = new Color(25, 35, 75);
                    grass = new Color(40, 70, 45);

                    trees[0] = 35;
                    trees[1] = 60;
                    trees[2] = 45;
                }
                break;
            case "summer":
                if (isDay) {
                    sky = new Color(70, 160, 230);
                    grass = new Color(60, 150, 40);

                    trees[0] = 34;
                    trees[1] = 110;
                    trees[2] = 34;
                } else {
                    sky = new Color(15, 20, 60);
                    grass = new Color(30, 60, 35);

                    trees[0] = 20;
                    trees[1] = 50;
                    trees[2] = 30;
                }
                break;
            case "fall":
                if (isDay) {
                    sky = new Color(160, 195, 215);
                    grass = new Color(160, 150, 70);
                    
                    trees[0] = 205;
                    trees[1] = 21;
                    trees[2] = 30;
                } else {
                    sky = new Color(20, 25, 50);
                    grass = new Color(55, 55, 35);

                    trees[0] = 60;
                    trees[1] = 40;
                    trees[2] = 25;
                }
                break;
        }

        setFocusable(true);
        setLayout(null);
    }

    @Override
    public Dimension getPreferredSize() {
        return SIZE;
    }

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

    private void drawBackground(Graphics g) {
        g.setColor(sky);
        g.fillRect(0, 0, 1280, 480);

        g.setColor(grass);
        g.fillRect(0, 480, 1280, 240);

        g.setColor(new Color(33, 130, 141));
        int[] xPoints = {540, 740, 690, 590};
        int[] yPoints = {720, 720, 480, 480};
        g.fillPolygon(xPoints, yPoints, 4);
    }

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

    private void drawBuildings(Graphics g) {
        for (byte i = 0; i < 100; i++) {
            int grayValue = (int) (Math.random() * 150) + 53; // (256 - 150) / 2
            g.setColor(new Color(grayValue, grayValue, grayValue));

            int height = (int) (Math.random() * 211.5 * 1.5);
            int width = (int) (Math.random() * 112.5) + 25;
            int xPos = (int) (Math.random() * 1280);
            int yPos = 480 - height;

            g.fillRect(xPos, yPos, width, height);
            g.setColor(Color.BLACK);
            g.drawRect(xPos, yPos, width, height);
            
            for (byte j = 0; j < 4; j++) {
                for (byte k = 0; k < 3; k++) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(xPos + (width / 7) + (k * width * 2 / 7), yPos + (height / 9) + (j * height * 2 / 9), width / 6, height / 8);

                    g.setColor(Color.BLACK);
                    g.drawRect(xPos + (width / 7) + (k * width * 2 / 7), yPos + (height / 9) + (j * height * 2 / 9), width / 6, height / 8);
                }
            }
        }
    }

    private void drawTrees(Graphics g) {
        Color bark = new Color(102, 91, 78);
        for (byte i = 0; i < 100; i++) {
            int yPos = (int) (Math.random() * 200) + 450;
            int size = (int) (Math.random() * 40) + 75;
            int xPos;

            int r = (int) (Math.random() * 40) + trees[0] - 20;
            int gColor = (int) (Math.random() * 40) + trees[1] - 20; // using g interferes with graphics variable
            int b = (int) (Math.random() * 40) + trees[2] - 20;

            if (i < 50) {
                xPos = (int) (Math.random() * 420);
            } else {
                xPos = 1280 - (int) (Math.random() * 420) - size; 
            }

            g.setColor(bark);
            g.fillRect((int) (xPos + (size * 0.375)), yPos + size - 50, (int) (size * 0.25), 1280 - yPos); // size / 2 - size / 8 = 3 * size / 8
            g.setColor(Color.BLACK);
            g.drawRect((int) (xPos + (size * 0.375)), yPos + size - 50, (int) (size * 0.25), 1280 - yPos); // make very long trunks so trees don't appear to be floating

            g.setColor(new Color(r, gColor, b));
            g.fillOval(xPos, yPos, size, size);
            g.setColor(Color.BLACK);
            g.drawOval(xPos, yPos, size, size);
        }
    }

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

    private void drawAnimals(Graphics g) {
        // draw pig
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
        int[] xPoints = {630, 640, 630};
        int[] yPoints = {580, 590, 600};
        g.fillPolygon(xPoints, yPoints, 3);

        g.setColor(Color.BLACK);
        g.fillOval(660, 585, 6, 6);
    }
}
