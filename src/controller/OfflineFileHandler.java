package util;

import java.io.*;

public class OfflineFileHandler {
    private static final String DATA_FILE = "offline_data.txt";

    // Method to save data to the offline file
    public static void saveDataOffline(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read data from the offline file
    public static String readOfflineData() {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    // Method to clear the content of the offline data file
    public static void clearOfflineData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            // This will overwrite the file with an empty string, effectively clearing it.
            writer.write("");
            System.out.println("Offline data cleared successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
