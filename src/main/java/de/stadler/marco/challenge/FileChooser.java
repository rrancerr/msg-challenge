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

    private static final TextArea outputTextArea = new TextArea("");

    private FileChooser() {
    }

    /***
     * displays the ui frame
     */
    static void starter() {
        // Create and set up the window.
        final JFrame frame = new JFrame("msg-challenge");

        // setup UI
        frame.setSize(500, 350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        JLabel headingText = new JLabel("Klick auf den Button und wähl die Datei aus, die eingelesen werden soll!");
        JButton button = new JButton("Wähle csv-Datei...");
        JLabel outputText = new JLabel("Die Ergebnisse der Berechnung werden danach hier angezeigt...");

        button.addActionListener(e -> createFileChooser(frame));

        frame.getContentPane().add(headingText);
        frame.getContentPane().add(button);
        frame.getContentPane().add(outputText);
        frame.getContentPane().add(outputTextArea);
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

    public static void updateUI(Solution bestSolution) {
        outputTextArea.setText(bestSolution.toString());
    }
}
