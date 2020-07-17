package de.stadler.marco.challenge;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //get a file
        javax.swing.SwingUtilities.invokeLater(FileChooser::starter);
    }

    public static void gotData(File file) {
        ArrayList<Location> locationArrayList = CSVReader.read(file);

        if (locationArrayList.isEmpty()) {
            System.out.println("ERROR: Unable to read data");
            return;
        }

        double[][] distanceMatrix = Utils.calcDistances(locationArrayList);

        HeuristicCalculator calc = new HeuristicCalculator(distanceMatrix, locationArrayList);
        calc.calcNearestNeighbor();
        Solution bestSolution = calc.getBestSolution();
        bestSolution.printSolution();
        bestSolution = Utils.sortSolution(bestSolution);
        System.out.println();
        bestSolution.printSolution();
    }

}
