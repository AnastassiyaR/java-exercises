package ee.taltech.iti0202.diary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Diary {

    private String fileName;

    /**
     * Constructor to initialize the diary file name.
     *
     * @param fileName The name of the diary file.
     */
    public Diary(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Creates a new diary file if it does not already exist.
     */
    public void createFile() {
        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + fileName);
            } else {
                System.out.println("File already exists: " + fileName);
            }
        } catch (IOException e) {
            System.out.println("Error while creating file: " + e.getMessage());
        }
    }

    /**
     * Reads and prints the contents of the diary file.
     */
    public void readFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.printf("File not found: %1$s", fileName);
            return;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error while reading file:%1$s" + e.getMessage());
        }
    }

    /**
     * Writes a new entry to the diary file with a timestamp.
     *
     * @param entryText The text to be written to the diary.
     */
    public void writeFile(String entryText) {
        try (FileWriter fileWriter = new FileWriter(fileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            bufferedWriter.write(String.format("%1$s%n%2$s%n", timeStamp, entryText));
            System.out.println("Entry added");
        } catch (IOException e) {
            System.out.println("Error while writing file: %1$s" + e.getMessage());
        }
    }

    /**
     * Deletes the diary file.
     */
    public void deleteFile() {
        File file = new File(fileName);
        if (file.exists() && file.delete()) {
            System.out.println("File deleted: %1$s%n" + fileName);
        } else {
            System.out.printf("Error while deleting file: %1$s%n", fileName);
        }
    }

    /**
     * Renames the diary file to a new name.
     *
     * @param newFileName The new file name.
     */
    public void renameFile(String newFileName) {
        File oldFile = new File(fileName);
        File newFile = new File(newFileName);

        if (oldFile.exists() && oldFile.renameTo(newFile)) {
            fileName = newFileName;
            System.out.println("File renamed: %1$s%n" + fileName);
        } else {
            System.out.println("Error while renaming file: %1$s%n" + fileName);
        }
    }

    /**
     * Moves the diary file to a new directory.
     *
     * @param newDirectory The new directory name.
     */
    public void moveFile(String newDirectory) {
        File oldFile = new File(fileName); // writes name of file
        File dir = new File(newDirectory); // writes dir

        if (!oldFile.exists()) {
            System.out.printf("File does not exist: %1$s%n", fileName);
            return;
        }

        if (!dir.exists() && !dir.mkdirs()) {
            System.out.printf("Error creating directory: %1$s%n", newDirectory);
            return;
        }

        File newFile = new File(dir, oldFile.getName());
        if (oldFile.renameTo(newFile)) {
            fileName = newFile.getAbsolutePath();
            System.out.printf("File moved to: %1$s%n", fileName);
        } else {
            System.out.printf("Error while moving file%n");
        }
    }

    /**
     * Gets the current diary file name.
     *
     * @return The diary file name.
     */
    public String getFileName() {
        return fileName;
    }
}
