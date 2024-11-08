package store.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils {
    private static final String CSV_DELIMITER = ",";

    public static List<List<String>> readCsvBody(String filePath) {
        List<List<String>> data = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(Arrays.asList(line.split(CSV_DELIMITER)));
            }
        } catch (IOException e) {
            System.out.println("[ERROR]" + e.getMessage());
        }
        return removeFirstRow(data);
    }

    private static List<List<String>> removeFirstRow(List<List<String>> data) {
        data.removeFirst();
        return data;
    }
}
