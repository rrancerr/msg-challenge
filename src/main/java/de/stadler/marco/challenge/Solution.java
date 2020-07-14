package de.stadler.marco.challenge;

import java.util.ArrayList;

public class Solution {
    private double sumDistance;
    private ArrayList<Location> locationPath;


    public Solution(double sumDistance, ArrayList<Location> locationPath) {
        this.sumDistance = sumDistance;
        this.locationPath = locationPath;
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
}
