package de.stadler.marco.challenge;

import java.util.ArrayList;

public class Solution {
    private double sumDistance;
    private ArrayList<Location> locationPath;
    private ArrayList<Double> distanceList;


    public Solution(double sumDistance, ArrayList<Location> locationPath, ArrayList<Double> distanceList) {
        this.sumDistance = sumDistance;
        this.locationPath = locationPath;
        this.distanceList = distanceList;
    }

    public ArrayList<Double> getDistanceList() {
        return distanceList;
    }

    public void setDistanceList(ArrayList<Double> distanceList) {
        this.distanceList = distanceList;
    }

    public double getSumDistance() {
        return sumDistance;
    }

    public ArrayList<Location> getLocationPath() {
        return locationPath;
    }

    public void setSumDistance(double sumDistance) {
        this.sumDistance = sumDistance;
    }

    public void setLocationPath(ArrayList<Location> locationPath) {
        this.locationPath = locationPath;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "sumDistance=" + sumDistance +
                ", locationPath=" + locationPath +
                ", distanceList=" + distanceList +
                '}';
    }

    public void printSolution() {
        System.out.println("SUM-DISTANCE: " + this.sumDistance);
        System.out.print("Path: ");
        for (Location location : this.locationPath) {
            System.out.print(location.getCity() + " -> ");
        }
        System.out.println();
        System.out.print("Distances: ");
        for (Double distance : this.distanceList) {
            System.out.print(distance + " -> ");
        }
        System.out.println();
    }
}
