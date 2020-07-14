package de.stadler.marco.challenge;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileChooser {

    private static final long serialVersionUID = 1L;


    private FileChooser() {

    }

    static void createAndShowGUI() {

        // Create and set up the window.
        final JFrame frame = new JFrame("Centered");

        // Display the window.
        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set flow layout for the frame
        frame.getContentPane().setLayout(new FlowLayout());

        JButton button = new JButton("Choose csv file...");

        button.addActionListener(e -> createFileChooser(frame));

        frame.getContentPane().add(button);

    }

    private static void createFileChooser(final JFrame frame) {

        String filename = File.separator + "tmp";
        JFileChooser fileChooser = new JFileChooser(new File(filename));

        // pop up an "Open File" file chooser dialog
        fileChooser.showOpenDialog(frame);

        System.out.println("File to open: " + fileChooser.getSelectedFile());

        // pop up an "Save File" file chooser dialog



    }


}
