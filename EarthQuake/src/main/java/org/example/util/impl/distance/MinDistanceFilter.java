package org.example.util.impl.distance;

import org.example.DAO.Location;
import org.example.DAO.QuakeEntry;
import org.example.util.intf.Filter;

public class MinDistanceFilter implements Filter {
    private double minDistance;
    private Location loc;

    public MinDistanceFilter(double minDistance, Location loc){
        this.minDistance = minDistance;
        this.loc = loc;
    }
    @Override
    public boolean satisfies(QuakeEntry quakeEntry) {
        return (quakeEntry.getLocation().distanceTo(loc)>minDistance);
    }
}
