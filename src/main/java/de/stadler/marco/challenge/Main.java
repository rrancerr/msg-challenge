package de.stadler.marco.challenge;

import java.io.File;
import java.util.List;

/**
 * Main class with the psvm-function to run the program.
 *
 * @author Marco Stadler
 */
public class Main {

    public static void main(String[] args) {
        //get a file
        javax.swing.SwingUtilities.invokeLater(FileChooser::starter);
    }

    /***
     * Called after receiving a file - handles all operations to receive the shortes journey path.
     * @param file the csv file from filechooser that yields the data.
     */
    public static void gotData(File file) {
        List<Location> locationArrayList = CSVReader.read(file);

        if (locationArrayList.isEmpty()) {
            return;
        }

        double[][] distanceMatrix = Utils.calcDistances(locationArrayList);

        HeuristicCalculator calc = new HeuristicCalculator(distanceMatrix, locationArrayList);
        calc.calcNearestNeighbor();
        Solution bestSolution = calc.getBestSolution();
        System.out.println(bestSolution.toString());
        bestSolution = Utils.sortSolution(bestSolution);
        System.out.println(bestSolution.toString());
    }

}
