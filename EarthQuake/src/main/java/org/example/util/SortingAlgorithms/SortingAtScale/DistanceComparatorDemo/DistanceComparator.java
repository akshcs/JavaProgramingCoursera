package org.example.util.SortingAlgorithms.SortingAtScale.DistanceComparatorDemo;

import org.example.DAO.Location;
import org.example.DAO.QuakeEntry;

import java.util.Comparator;

public class DistanceComparator implements Comparator<QuakeEntry> {
    private final Location location;

    public DistanceComparator(Location location){
        this.location = location;
    }

    @Override
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        return Double.compare(location.distanceTo(qe1.getLocation()), location.distanceTo(qe2.getLocation()));
    }
}
