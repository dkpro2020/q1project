import javax.swing.JFrame;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        System.out.println("\n------------------------------\nWelcome to my Scenery Project!\n------------------------------\n");

        String time, season;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Please enter either 'day' or 'night': ");
            time = scanner.next().toLowerCase();
        } while (!time.equals("night") && !time.equals("day"));

        do {
            System.out.print("Please enter a season ('winter', 'spring', 'summer', or 'fall'): ");
            season = scanner.next().toLowerCase();
        } while (
            !season.equals("winter") && 
            !season.equals("spring") && 
            !season.equals("summer") && 
            !season.equals("fall")
        );

        scanner.close();

        boolean isDay = false; // if the user enters "day", isDay will be set to true; otherwise, it will stay false
        if (time.equals("day")) {
            isDay = true;
        }

        JFrame frame = new JFrame("Scnery Project");
        Scenery canvas = new Scenery(isDay, season);
        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
