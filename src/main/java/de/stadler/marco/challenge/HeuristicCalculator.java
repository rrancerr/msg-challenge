package de.stadler.marco.challenge;

import java.util.ArrayList;

public class HeuristicCalculator {

    private final double[][] matrix;
    private final ArrayList<Location> locationArrayList;
    private Solution solution;


    public HeuristicCalculator(double[][] matrix, ArrayList<Location> locationArrayList) {
        this.matrix = matrix;
        this.locationArrayList = locationArrayList;
        this.solution = new Solution(0.0, new ArrayList<>());
    }


    public void calcNearestNeighbor() {
        if (this.matrix == null || this.locationArrayList == null || this.solution == null) {
            System.out.println("ERROR: Calculation not possible -> variables not initialized");
        }

        boolean checkColFlag = true;
        double currentEntry;
        MatrixLocationObj currentShortest = new MatrixLocationObj(0, 0, -1.0);
        while (this.solution.getLocationPath().size() < locationArrayList.size()) {
            if (checkColFlag) {
                //search in column
                for (int i = 0; i < matrix.length; i++) {
                    currentEntry = matrix[i][currentShortest.getColIdx()];
                    if (currentEntry != 0.0) {
                        //current entry is a 'real' distance
                        if (currentShortest.getDistance() == -1.0) {
                            //first iteration -> set initial
                            currentShortest.setDistance(currentEntry);
                            currentShortest.setRowIdx(i);
                            if (solution.getLocationPath().isEmpty()) {
                                System.out.print(currentShortest.getDistance() + " ");
                                addToSolution(currentShortest, checkColFlag);
                            }
                        } else {
                            if (currentShortest.getDistance() > currentEntry) {
                                //found a shorter distance
                                if (!isAlreadyInPath(i)) {
                                    currentShortest.setDistance(currentEntry);
                                    currentShortest.setRowIdx(i);
                                }
                            }
                        }
                    }
                }
            } else {
                //search in row
                for (int i = 0; i < matrix.length; i++) {
                    currentEntry = matrix[currentShortest.getRowIdx()][i];
                    if (currentEntry != 0.0) {
                        //current entry is a 'real' distance
                        if (currentShortest.getDistance() == -1.0) {
                            //first iteration -> set initial
                            currentShortest.setDistance(currentEntry);
                            currentShortest.setColIdx(i);
                            if (solution.getLocationPath().isEmpty()) {
                                addToSolution(currentShortest, checkColFlag);
                            }
                            if (currentShortest.getDistance() > currentEntry) {
                                //found a shorter distance
                                if (!isAlreadyInPath(i)) {
                                    currentShortest.setDistance(currentEntry);
                                    currentShortest.setColIdx(i);
                                }
                            }
                        }
                    }
                }
            }
            //currentShortest holds now the best path to go to
            //add to path
            addToSolution(currentShortest, checkColFlag);
            System.out.print(currentShortest.getDistance() + " ");
            //reset
            currentShortest.setDistance(-1.0);
            checkColFlag = !checkColFlag;
        }
    }

    private void addToSolution(MatrixLocationObj currentShortest, boolean isColumnIdx) {
        //sum up distance
        this.solution.setSumDistance(this.solution.getSumDistance() + currentShortest.getDistance());

        //add to list
        if (isColumnIdx) {
            //use col as indicator
            this.solution.getLocationPath().add(this.locationArrayList.get(currentShortest.getColIdx() + 1));
        } else {
            //use row as indicator
            this.solution.getLocationPath().add(this.locationArrayList.get(currentShortest.getRowIdx() + 1));
        }
    }

    private boolean isAlreadyInPath(int idx) {
        for (Location location : this.solution.getLocationPath()) {
            if (location.getNumber() == idx + 1) {
                return true;
            }
        }
        return false;
    }
}
