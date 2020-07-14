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

        double[][] testMatrix = {
                {0.0, 14, 69, 26, 31, 16},
                {14, 0.0, 47, 66, 33, 52},
                {69, 47, 0.0, 58, 52, 23},
                {26, 66, 58, 0.0, 45, 27},
                {31, 33, 52, 45, 0.0, 59},
                {16, 52, 23, 27, 59, 0.0}
        };

//        HeuristicCalculator calc = new HeuristicCalculator(distanceMatrix, locationArrayList);
        HeuristicCalculator calc = new HeuristicCalculator(testMatrix, locationArrayList);
        calc.calcNearestNeighbor();

    }

}
