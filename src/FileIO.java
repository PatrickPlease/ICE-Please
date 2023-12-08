import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class FileIO {

    public static User readUserData(String username) {
        try (Scanner scanner = new Scanner(new File("data/UserData/" + username + "_UserData.txt"))) {
            if (scanner.hasNextLine()) {
                String password = scanner.nextLine();
                return new User(username, password);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return null;
    }

    public static void saveUserData(User user) {
        try (PrintWriter pWriter = new PrintWriter(new FileWriter("data/UserData/" + user.getUsername() + "_UserData.txt", true))) {
            if (user != null) {
                pWriter.println(user.getPassword());
            }
        } catch (IOException e) {
            System.out.println("Something is wrong with the Datafile: " + e.getMessage());
        }
    }

    public static List<String> readUserWatchlist(String username) {
        try (Scanner scanner = new Scanner(new File("data/UserData/" + username + "_UserData.txt"))) {
            List<String> watchlist = new ArrayList<>();
            while (scanner.hasNextLine()) {
                watchlist.add(scanner.nextLine());
            }
            return watchlist;
        } catch (FileNotFoundException e) {
            System.out.println("Watchlist file not found: " + e.getMessage());
        }
        return null;
    }

    public static void saveUserWatchlist(String username, List<String> watchlist) {
        try (PrintWriter pWriter = new PrintWriter(new FileWriter("data/UserData/" + username + "_UserData.txt"))) {
            for (String watchlistItem : watchlist) {
                pWriter.println(watchlistItem);
            }
        } catch (IOException e) {
            System.out.println("Something is wrong with the Watchlist file: " + e.getMessage());
        }
    }

    public static void addToWatchlist(User user, String newWatchlistItem) {
        try (PrintWriter pWriter = new PrintWriter(new FileWriter("data/UserData/" + user.getUsername() + "_UserData.txt", true))) {
            if (user != null) {
                pWriter.println(newWatchlistItem);
            }
        } catch (IOException e) {
            System.out.println("Something is wrong with the Watchlist file: " + e.getMessage());
        }
    }
}