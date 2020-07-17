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
        int countLocations = 0;
        int countDistances = 0;
        System.out.println("SUM-DISTANCE: " + this.sumDistance);
        System.out.print("Path: ");
        ArrayList<Location> path = this.locationPath;
        for (int i = 0; i < path.size(); i++) {
            Location location = path.get(i);
            countLocations++;
            System.out.print(location.getCity());
            if (!(i + 1 == path.size())) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
        System.out.println("Traveld to " + countLocations + " locations.");
        System.out.print("Distances: ");
        for (Double distance : this.distanceList) {
            countDistances++;
            System.out.print(distance + " ");
        }
        System.out.println();
        System.out.println("Amount of Distances: " + countDistances);

    }

    public void reset() {
        this.sumDistance = 0;
        this.locationPath.clear();
        this.distanceList.clear();
    }
}
