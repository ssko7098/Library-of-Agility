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

public class FileUploaderWithDialog {

    private static JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
    private static FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");

    public static String chooseFile() {
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
}
