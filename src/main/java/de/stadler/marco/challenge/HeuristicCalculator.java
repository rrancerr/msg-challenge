package de.stadler.marco.challenge;

import java.util.ArrayList;
import java.util.Arrays;

public class HeuristicCalculator {

    private final double[][] matrix;
    private final int[][] alreadyUsedPaths;
    private final ArrayList<Location> locationArrayList;
    private Solution currentSolution;
    private ArrayList<Solution> solutions;

    public HeuristicCalculator(double[][] matrix, ArrayList<Location> locationArrayList) {
        this.matrix = matrix;
        this.locationArrayList = locationArrayList;
        this.currentSolution = new Solution(0.0, new ArrayList<>(), new ArrayList<>());
        this.alreadyUsedPaths = new int[matrix.length][matrix.length];
        this.solutions = new ArrayList<>();
    }


    public void calcNearestNeighbor() {
        if (this.locationArrayList == null || this.currentSolution == null) {
            System.out.println("ERROR: Calculation not possible -> variables not initialized");
            return;
        }


        boolean checkColumn;
        double currentShortestDistance;
        double currentDistance;

        for (int startIdx = 0; startIdx < locationArrayList.size(); startIdx++) {
            MatrixLocationObj lastMatrixLocation = new MatrixLocationObj(startIdx, 0, -1.0);
            checkColumn = true;
            currentShortestDistance = -1;

            while (this.currentSolution.getLocationPath().size() < this.matrix.length) {
                if (checkColumn) {
                    //check the column
                    for (int i = 0; i < matrix.length; i++) {
                        currentDistance = matrix[i][lastMatrixLocation.getColIdx()];
                        if (!isAlreadyInPath(i, lastMatrixLocation.getColIdx()) && currentDistance != 0.0) {
                            if (currentShortestDistance == -1) {
                                //this is the first iteration
                                currentShortestDistance = currentDistance;
                            }
                            //this is not the first iteration
                            if (currentDistance <= currentShortestDistance) {
                                lastMatrixLocation.setRowIdx(i);
                                lastMatrixLocation.setDistance(currentDistance);
                                currentShortestDistance = currentDistance;
                            }
                        }
                    }
                } else {
                    //check the row
                    for (int i = 0; i < matrix.length; i++) {
                        currentDistance = matrix[lastMatrixLocation.getRowIdx()][i];
                        if (!isAlreadyInPath(lastMatrixLocation.getRowIdx(), i) && currentDistance != 0.0) {
                            if (currentShortestDistance == -1) {
                                //this is the first iteration
                                currentShortestDistance = currentDistance;
                            }
                            //this is not the first iteration
                            if (currentDistance <= currentShortestDistance) {
                                lastMatrixLocation.setColIdx(i);
                                lastMatrixLocation.setDistance(currentDistance);
                                currentShortestDistance = currentDistance;
                            }
                        }
                    }
                }

                addToSolution(lastMatrixLocation, checkColumn, startIdx);

                currentShortestDistance = -1;
                checkColumn = !checkColumn;
            }
            System.out.println();
            Solution solutionToAdd = new Solution(currentSolution.getSumDistance(), new ArrayList<>(currentSolution.getLocationPath()), new ArrayList<>(currentSolution.getDistanceList()));
            this.solutions.add(solutionToAdd);
            resetVars();
        }
        System.out.println("AMOUNT OF SOLUTIONS = " + this.solutions.size());
    }

    private void resetVars() {
        //reset usedPathMatrix
        for (int[] alreadyUsedPath : this.alreadyUsedPaths) {
            Arrays.fill(alreadyUsedPath, 0);
        }

        //reset currentSolution
        this.currentSolution.reset();
    }

    private void addToSolution(MatrixLocationObj currentShortest, boolean isColumnIdx, int startIdx) {

        boolean reachedStartAgain = false;

        if (this.currentSolution.getLocationPath().size() == this.matrix.length - 1) {
            //last destination reached -> go back to start
            reachedStartAgain = true;
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
        this.currentSolution.setSumDistance(this.currentSolution.getSumDistance() + currentShortest.getDistance());

        //add to distance list
        this.currentSolution.getDistanceList().add(currentShortest.getDistance());


        //add to list
        if (isColumnIdx) {
            //use col as indicator
            this.currentSolution.getLocationPath().add(this.locationArrayList.get(currentShortest.getColIdx()));
        } else {
            //use row as indicator
            this.currentSolution.getLocationPath().add(this.locationArrayList.get(currentShortest.getRowIdx()));
        }

        if (reachedStartAgain) {
            this.currentSolution.getLocationPath().add(this.locationArrayList.get(startIdx));
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

    public Solution getBestSolution() {
        Solution result = null;
        if (this.solutions == null || this.solutions.isEmpty()) {
            return null;
        }

        for (Solution solution : this.solutions) {
            if (result == null || solution.getSumDistance() < result.getSumDistance()) {
                result = solution;
            }
        }
        return result;
    }
}
