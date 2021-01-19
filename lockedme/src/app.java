import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.*;
import java.util.*;


public class app {

    final static String FOLDER = "/Users/HCL/Desktop/";

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showWelcomeScreen();
        showMainMenu();

    }

    private static void collectMainMenuOption() {
        System.out.println("Please choose 1, 2 or 3:");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                showFilesInAscendingOrder();
                break;
            case "2":
                showFileOperations();
            case "3":
                System.out.println("Thanks for using lockedme.com. Closing application.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input provided, please choose 1, 2 or 3.");
        }
        showMainMenu();
    }

    private static void searchForAFile() throws InvalidPathException {
        System.out.println("Name of file to search: ");
        String filePath = scanner.nextLine();
        Path path = Paths.get(FOLDER + filePath);

        File f = new File(FOLDER + filePath);
        if (f.exists() && !f.isDirectory()) {
            System.out.println("File:\"" + filePath + "\" exist in" + FOLDER);
        } else
            System.out.println("File:\"" + filePath + "\" does NOT exist.");
    }


    private static void showFileOperations() {
        System.out.println("--------------");
        System.out.println("1.) Add a file");
        System.out.println("2.) Delete a file");
        System.out.println("3.) Search for a file");
        System.out.println("4.) Back to main menu");
        System.out.println("--------------");
        collectFileOperation();
    }

    private static void addAFile() throws InvalidPathException {
        System.out.println("Please provide a file path:");
        String filePath = scanner.nextLine();
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            System.out.println("File does not exist");
            return;
        }

        String newFilePath = FOLDER + "/" + path.getFileName();
        int inc = 0;
        while (Files.exists(Paths.get(newFilePath))) {
            inc++;
            newFilePath = FOLDER + "/" + inc + "_" + path.getFileName();
        }
        try {
            Files.copy(path, Paths.get(newFilePath));
            System.out.println("New File Added.");
        } catch (IOException e) {
            System.out.println("Unable to copy file to: " + newFilePath);
        }

    }

    private static void deleteAFile() throws InvalidPathException {
        System.out.println("Name of file to delete: ");
        String filePath = scanner.nextLine();
        Path path = Paths.get(FOLDER + filePath);
        if (Files.exists(path)) {
            System.out.println("File Exits.");
            try {
                File file = new File(FOLDER + filePath);
                if (file.delete())
                    System.out.println("File \"" + filePath + "\" was deleted ");
                else
                    System.out.println("File was not deleted.");
            } catch (InvalidPathException e) {
                System.out.println("Invalid file path.");
            }


        } else
            System.out.println("File does NOT exist.");

    }

    private static void collectFileOperation() {
        System.out.println("Please choose 1, 2, 3 or 4:");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                addAFile();
                break;
            case "2":
                deleteAFile();
                break;
            case "3":
                searchForAFile();
                break;
            case "4":
                break;
        }
        showFileOperations();
    }

    private static void showFilesInAscendingOrder() {
        System.out.println("------------------");
        System.out.println("Showing files in ascending order");
        File[] files = new File(FOLDER).listFiles();
        Set<String> sorted = new TreeSet<>();
        for (File file : files) {
            if (!file.isFile()) {
                continue;
            }
            sorted.add(file.getName());
        }
        sorted.forEach(System.out::println);
        System.out.println("------------------");
    }

    private static void showMainMenu() {
        System.out.println("\n\t\t MAIN MENU");
        System.out.println("1.) Show files in ascending order");
        System.out.println("2.) Perform file operations");
        System.out.println("3.) Close the application");
        System.out.println("================================");
        collectMainMenuOption();
    }

    private static void showWelcomeScreen() {
        System.out.println("================================");
        System.out.println("******** LockedMe.com ********");
        System.out.println("CREATED BY: Josh Persaud");
        System.out.println("================================\n");
    }
}