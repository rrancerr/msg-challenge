package de.stadler.marco.challenge;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static de.stadler.marco.challenge.Main.gotData;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


/***
 * @author Marco Stadler
 */
public class FileChooser {

    private FileChooser() {

    }

    /***
     * displays the ui frame
     */
    static void starter() {
        // Create and set up the window.
        final JFrame frame = new JFrame("Centered");

        // setup UI
        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        JButton button = new JButton("Choose csv file...");

        button.addActionListener(e -> createFileChooser(frame));

        frame.getContentPane().add(button);
    }


    /***
     * creats the file-chooser window
     * @param frame the frame where the filechooser shall be displayed
     */
    private static void createFileChooser(final JFrame frame) {

        String filename = File.separator + "tmp";
        JFileChooser fileChooser = new JFileChooser(new File(filename));

        // pop up an "Open File" file chooser dialog
        fileChooser.showOpenDialog(frame);


        File chosenFile = fileChooser.getSelectedFile();

        if (chosenFile != null) {
            gotData(chosenFile);
        }
    }
}
