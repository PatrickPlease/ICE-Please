import java.util.DoubleSummaryStatistics;
import java.util.Scanner;

public class TextUI {
    private Scanner scan = new Scanner(System.in);

    private MediaWindow mediaWindow;

    public TextUI() {
        mediaWindow = new MediaWindow();
    }

    public String getInput(String msg) {

        System.out.println(msg);
        return scan.nextLine();
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }


    public void runMediaPlayer() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = Integer.parseInt(getInput("Enter your choice: "));

            switch (choice) {
                case 1:
                    //mediaWindow.playMedia();
                    break;
                case 2:
                    mediaWindow.pauseMedia();
                    break;
                case 3:
                    mediaWindow.rewindMedia();
                    break;
                case 4:
                    mediaWindow.forwardMedia();
                    break;
                case 5:
                    mediaWindow.episodeOverview();
                    break;
                case 6:
                    mediaWindow.exitMedia();
                    exit = true;
                    break;
                default:
                    displayMessage("Invalid choice. Please try again.");
            }
        }
    }


}











//    Koden før:
//    private CreateAccount createAccountHandler = new CreateAccount();
//
//    public void start() {
//        System.out.println("Welcome to PleaseChill");
//        System.out.println("1. Create account");
//        System.out.println("2. Already have an account? Login");
//
//        int choice = Integer.parseInt(getInput("Enter your choice: "));
//
//        switch (choice) {
//            case 1:
//                System.out.println("Pat-rick 👍");
//                break;
//            case 2:
//                System.out.println("Pat-rick 👍"); // hentyd til login skærm
//                break;
//            default:
//                System.out.println("Invalid choice. Please choose 1 or 2, thank you.");
//        }