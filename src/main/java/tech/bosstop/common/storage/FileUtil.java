package tech.bosstop.common.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    public static boolean fileExists(File file) {
        return file.exists();
    }

    public static String readFile(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }

    public static void writeFile(String path, String content) throws IOException {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
