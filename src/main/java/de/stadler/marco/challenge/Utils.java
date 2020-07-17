package de.stadler.marco.challenge;

import java.util.ArrayList;

public class Utils {

    private static final int R = 6371;

    private static final String START_CITY = "Ismaning";

    private Utils() {
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     * <p>
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     *
     * @returns Distance in Meters
     */
    private static double calcDistance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    public static double[][] calcDistances(ArrayList<Location> locationArrayList) {
        //fill one side of the matrix
        int arraySize = locationArrayList.size();
        double[][] result = new double[arraySize][arraySize];
        double currentDistance;
        for (int i = 0; i < arraySize; i++) {
            double firstLat = locationArrayList.get(i).getLatitude(), firstLong = locationArrayList.get(i).getLongitude(), secLat, secLong;
            for (int j = i + 1; j < arraySize; j++) {
                secLat = locationArrayList.get(j).getLatitude();
                secLong = locationArrayList.get(j).getLongitude();
                currentDistance = calcDistance(firstLat, secLat, firstLong, secLong, 0.0, 0.0);
                result[i][j] = currentDistance;
                result[j][i] = currentDistance; //assume symmetric TSP
            }
        }

        return result;
    }

    /***
     * Sort the solution - start and end in START_CITY
     * @param bestSolution the solution with the shortest path
     */
    public static Solution sortSolution(Solution bestSolution) {
        Solution result = new Solution(bestSolution.getSumDistance(), new ArrayList<>(), new ArrayList<>());

        int temp;
        for (int i = 0; i < bestSolution.getLocationPath().size(); i++) {
            //search for the beginning
            if (bestSolution.getLocationPath().get(i).getCity().equals(START_CITY)) {
                temp = i;
                //this is the new start of the list
                //add the locations
                while (result.getLocationPath().size() < bestSolution.getLocationPath().size()) {
                    if (i == bestSolution.getLocationPath().size()) {
                        //if it reached the end, start at the beginning of the list
                        i = 0;
                    } else {
                        result.getLocationPath().add(bestSolution.getLocationPath().get(i));
                    }
                    i++;
                }
                //add the distances
                while (result.getDistanceList().size() < bestSolution.getDistanceList().size()) {
                    if (temp == bestSolution.getDistanceList().size()) {
                        //if it reached the end, start at the beginning of the list
                        temp = 0;
                    }
                    result.getDistanceList().add(bestSolution.getDistanceList().get(temp));

                    temp++;
                }
                break;
            }
        }

        return result;
    }
}
