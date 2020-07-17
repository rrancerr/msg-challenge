package de.stadler.marco.challenge;

import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicLine;
import net.sf.geographiclib.GeodesicMask;

import java.util.ArrayList;
import java.util.List;

/***
 * Class provides helper mehtods.
 * @author Marco Stadler
 */
public class Utils {

    private static final int R = 6371;

    private static final String START_CITY = "Ismaning";

    private static final Geodesic GEOD = Geodesic.WGS84;

    private Utils() {
    }

    /**
     * @returns Distance in Meters
     * @deprecated getDistanceFromLib and calcDistanceFormular lead to the same result, but the library-function
     * provides more accuracy at higher distances
     * <p>
     * <p>
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     * <p>
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     */
    @Deprecated
    private static double calcDistanceFormular(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {

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

    /**
     * Get the distance between two points in meters.
     *
     * @param lat1 First point's latitude
     * @param lon1 First point's longitude
     * @param lat2 Second point's latitude
     * @param lon2 Second point's longitude
     * @return Distance between the first and the second point in meters
     */
    private static double getDistanceFromLib(double lat1, double lat2, double lon1, double lon2) {
        GeodesicLine line = GEOD.InverseLine(lat1, lon1, lat2, lon2, GeodesicMask.DISTANCE_IN | GeodesicMask.LATITUDE | GeodesicMask.LONGITUDE);
        return line.Distance();
    }

    /***
     * Creates a 2D symmetric matrix of the distances of a locationlist
     * example:<br>
     * <code>
     *           |    [0]   |    [1]
     *           | Ismaning |   Berlin
     *  --------------------------------
     *    [0]    |          | distance
     *           |   0.0    | Ismaning to
     *  Ismaning |          | Berlin
     *  --------------------------------
     *    [1]    | distance |
     *           | Berlin to|    0.0
     *   Berlin  | Ismaning |
     *
     * </code>
     * @param locationArrayList a locationarraylist that holds all the locations of the matrix
     * @return a 2D symmetric matrix of the distances
     */
    public static double[][] calcDistances(List<Location> locationArrayList) {
        int arraySize = locationArrayList.size();
        double[][] result = new double[arraySize][arraySize];
        double currentDistance;
        for (int i = 0; i < arraySize; i++) {
            double firstLat = locationArrayList.get(i).getLatitude();
            double firstLong = locationArrayList.get(i).getLongitude();
            double secLat;
            double secLong;
            for (int j = i + 1; j < arraySize; j++) {
                secLat = locationArrayList.get(j).getLatitude();
                secLong = locationArrayList.get(j).getLongitude();
                currentDistance = getDistanceFromLib(firstLat, secLat, firstLong, secLong);
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
