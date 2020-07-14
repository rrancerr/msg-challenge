package de.stadler.marco.challenge;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //get a file
        javax.swing.SwingUtilities.invokeLater(FileChooser::starter);
    }

    public static void gotData(File file) {
        ArrayList<Location> locationArrayList = new ArrayList<>();
        locationArrayList = CSVReader.read(file);

        if (locationArrayList.isEmpty()) {
            System.out.println("ERROR: Unable to read data");
            return;
        }

        double[][] distanceMatrix = new double[locationArrayList.size()][locationArrayList.size()];
        distanceMatrix = Utils.calcDistances(locationArrayList);


    }

}
