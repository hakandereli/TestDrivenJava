package org.company.unit.example1;

import java.io.*;

public class FileService {
    private final File file;

    public FileService(File file) {
        this.file = file;
    }

    public void writeToFile(String content) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
    }

    public String readFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.readLine();
        }
    }
}
