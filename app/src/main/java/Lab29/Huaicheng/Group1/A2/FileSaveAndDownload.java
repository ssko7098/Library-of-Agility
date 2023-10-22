package Lab29.Huaicheng.Group1.A2;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileSaveAndDownload {

    private static JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
    private static FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");

    public static String chooseFile() {
        fileChooser.setDialogTitle("Choose File");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fileChooser.cancelSelection();
            return selectedFile.getPath();
        }

        fileChooser.cancelSelection();

        return null;
    }

    public static void downloadToDestination(String sourcePath, String destinationPath) {
        try {
            Path source = Paths.get(sourcePath);
            Path destination = Paths.get(destinationPath);
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File downloaded successfully.");
        } catch (IOException e) {
            System.err.println("Error downloading file: " + e.getMessage());
        }
    }

    public static void downloadFile(String scrollName, String uploader) {
        fileChooser.setDialogTitle("Choose Destination");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(filter);

        int returnValueDownload = fileChooser.showSaveDialog(null);

        if (returnValueDownload == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String destinationPath = selectedFile.getPath();

            String sourcePath = "src/main/resources/" + uploader + "/" + scrollName + ".txt";
            downloadToDestination(sourcePath, destinationPath);
        }

        fileChooser.cancelSelection();
    }

}
