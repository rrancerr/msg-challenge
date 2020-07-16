package de.stadler.marco.challenge;

import java.util.ArrayList;

public class HeuristicCalculator {

    private final double[][] matrix;
    private final int[][] alreadyUsedPaths;
    private final ArrayList<Location> locationArrayList;
    private Solution solution;


    public HeuristicCalculator(double[][] matrix, ArrayList<Location> locationArrayList) {
        this.matrix = matrix;
        this.locationArrayList = locationArrayList;
        this.solution = new Solution(0.0, new ArrayList<>(), new ArrayList<>());
        this.alreadyUsedPaths = new int[matrix.length][matrix.length];
    }


    public void calcNearestNeighbor() {
        if (this.matrix == null || this.locationArrayList == null || this.solution == null) {
            System.out.println("ERROR: Calculation not possible -> variables not initialized");
            return;
        }


        boolean checkColumn = true;
        int startIdx = 0;
        MatrixLocationObj lastMatrixLocation = new MatrixLocationObj(startIdx, 0, -1.0);

        double lastDistance = -1;
        double currentDistance = 0.0;

        while (this.solution.getLocationPath().size() < this.matrix.length) {
            if (checkColumn) {
                //check the column
                for (int i = 0; i < matrix.length; i++) {
                    currentDistance = matrix[i][lastMatrixLocation.getColIdx()];
                    if (!isAlreadyInPath(i, lastMatrixLocation.getColIdx()) && currentDistance != 0.0) {
                        if (lastDistance == -1) {
                            //this is the first iteration
                            lastDistance = currentDistance;
                        }
                        //this is not the first iteration
                        if (currentDistance <= lastDistance) {
                            lastMatrixLocation.setRowIdx(i);
                            lastMatrixLocation.setDistance(currentDistance);
                            lastDistance = currentDistance;
                        }
                    }
                }
            } else {
                //check the row
                for (int i = 0; i < matrix.length; i++) {
                    currentDistance = matrix[lastMatrixLocation.getRowIdx()][i];
                    if (!isAlreadyInPath(lastMatrixLocation.getRowIdx(), i) && currentDistance != 0.0) {
                        if (lastDistance == -1) {
                            //this is the first iteration
                            lastDistance = currentDistance;
                        }
                        //this is not the first iteration
                        if (currentDistance <= lastDistance) {
                            lastMatrixLocation.setColIdx(i);
                            lastMatrixLocation.setDistance(currentDistance);
                            lastDistance = currentDistance;
                        }
                    }
                }
            }

            addToSolution(lastMatrixLocation, checkColumn, startIdx);

            lastDistance = -1;
            checkColumn = !checkColumn;
        }

        this.solution.printSolution();
    }

    private void addToSolution(MatrixLocationObj currentShortest, boolean isColumnIdx, int startIdx) {

        if (this.solution.getLocationPath().size() == this.matrix.length - 1) {
            //last destination reached -> go back to start
            if (!isColumnIdx) {
                currentShortest.setColIdx(startIdx);
                currentShortest.setDistance(this.matrix[currentShortest.getRowIdx()][startIdx]);
            } else {
                currentShortest.setRowIdx(startIdx);
                currentShortest.setDistance(this.matrix[currentShortest.getColIdx()][startIdx]);
            }
        }

        updateUsedPathsMatrix(currentShortest, isColumnIdx);
        //sum up distance
        this.solution.setSumDistance(this.solution.getSumDistance() + currentShortest.getDistance());

        //add to distance list
        this.solution.getDistanceList().add(currentShortest.getDistance());


        //add to list
        if (isColumnIdx) {
            //use col as indicator
            this.solution.getLocationPath().add(this.locationArrayList.get(currentShortest.getColIdx()));
        } else {
            //use row as indicator
            this.solution.getLocationPath().add(this.locationArrayList.get(currentShortest.getRowIdx()));
        }
    }

    private void updateUsedPathsMatrix(MatrixLocationObj currentShortest, boolean isColumnIdx) {
        if (isColumnIdx) {
            for (int i = 0; i < this.alreadyUsedPaths.length; i++) {
                this.alreadyUsedPaths[i][currentShortest.getColIdx()] = 1;
                this.alreadyUsedPaths[currentShortest.getColIdx()][i] = 1;
            }
        } else {
            for (int i = 0; i < this.alreadyUsedPaths.length; i++) {
                this.alreadyUsedPaths[currentShortest.getRowIdx()][i] = 1;
                this.alreadyUsedPaths[i][currentShortest.getRowIdx()] = 1;
            }
        }
    }

    private boolean isAlreadyInPath(int rowIdx, int colIdx) {
        return alreadyUsedPaths[rowIdx][colIdx] == 1;
    }
}
