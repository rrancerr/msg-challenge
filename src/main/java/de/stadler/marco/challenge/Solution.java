package de.stadler.marco.challenge;

import java.util.List;


/***
 * An object that has all information for one solution, including the summed up distance of the solution,
 * the path itself as a list and the distances for each journey from one location to another as a list.
 * @author Marco Stadler
 */
public class Solution {
    private double sumDistance;
    private final List<Location> locationPath;
    private final List<Double> distanceList;


    public Solution(double sumDistance, List<Location> locationPath, List<Double> distanceList) {
        this.sumDistance = sumDistance;
        this.locationPath = locationPath;
        this.distanceList = distanceList;
    }

    public List<Double> getDistanceList() {
        return distanceList;
    }

    public double getSumDistance() {
        return sumDistance;
    }

    public List<Location> getLocationPath() {
        return locationPath;
    }

    public void setSumDistance(double sumDistance) {
        this.sumDistance = sumDistance;
    }


    /***
     * Creates a formatted string of the solution object, including the sum of the distances, the city-names of the
     * path and the distances of the distances list.
     * @return formatted String of the solution
     */
    public String toString() {

        StringBuilder result = new StringBuilder();
        int countLocations = 0;
        int countDistances = 0;
        result.append("SUM-DISTANCE: ").append(this.sumDistance).append("\n");
        result.append("Path: ");
        List<Location> path = this.locationPath;
        for (int i = 0; i < path.size(); i++) {
            Location location = path.get(i);
            countLocations++;
            result.append(location.getCity());
            if (i + 1 != path.size()) {
                result.append(" -> ");
            }
        }
        result.append("\n");
        result.append("Traveld to ").append(countLocations).append(" locations.\n");
        result.append("Distances: ");
        for (Double distance : this.distanceList) {
            countDistances++;
            result.append(distance).append(" ");
        }
        result.append("\n");
        result.append("Amount of Distances: ").append(countDistances).append("\n");

        return result.toString();
    }

    public void reset() {
        this.sumDistance = 0;
        this.locationPath.clear();
        this.distanceList.clear();
    }
}
