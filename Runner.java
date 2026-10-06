import javax.swing.JFrame;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        System.out.println(
                "\n------------------------------\nWelcome to my Scenery Project!\n------------------------------\n");

        String time, season;
        Scanner scanner = new Scanner(System.in);

        // Loop while user doesn't enter desirable input for time and season
        do {
            System.out.print("Please enter either 'day' or 'night': ");
            time = scanner.next().toLowerCase();
        } while (!time.equals("night") && !time.equals("day"));

        do {
            System.out.print("Please enter a season ('winter', 'spring', 'summer', or 'fall'): ");
            season = scanner.next().toLowerCase();
        } while (!season.equals("winter") &&
                !season.equals("spring") &&
                !season.equals("summer") &&
                !season.equals("fall"));

        scanner.close();

        boolean isDay = false; // If the user enters "day", isDay will be true; otherwise, it will be false
        if (time.equals("day")) {
            isDay = true;
        }

        // Create the frame object and title it "Scenery Project"
        JFrame frame = new JFrame("Scenery Project");

        //Create the JPanel object and add it to the frame
        Scenery canvas = new Scenery(isDay, season); // Pass in isDay and season to Scenery class
        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
