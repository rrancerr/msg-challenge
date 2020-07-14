package de.stadler.marco.challenge;

public class MatrixLocationObj {
    private int colIdx;
    private int rowIdx;
    private double distance;

    public MatrixLocationObj(int colIdx, int rowIdx, double distance) {
        this.colIdx = colIdx;
        this.rowIdx = rowIdx;
        this.distance = distance;
    }

    public int getRowIdx() {
        return rowIdx;
    }

    public int getColIdx() {
        return colIdx;
    }

    public void setColIdx(int colIdx) {
        this.colIdx = colIdx;
    }

    public double getDistance() {
        return distance;
    }

    public void setRowIdx(int rowIdx) {
        this.rowIdx = rowIdx;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
