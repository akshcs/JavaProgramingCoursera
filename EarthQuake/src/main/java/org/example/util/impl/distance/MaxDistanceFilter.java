package org.example.util.impl.distance;

import org.example.DAO.Location;
import org.example.DAO.QuakeEntry;
import org.example.util.intf.Filter;

public class MaxDistanceFilter implements Filter {
    private double maxDistance;
    private Location loc;

    public MaxDistanceFilter(double maxDistance, Location loc){
        this.maxDistance = maxDistance;
        this.loc = loc;
    }
    @Override
    public boolean satisfies(QuakeEntry quakeEntry) {
        return (quakeEntry.getLocation().distanceTo(loc)<maxDistance);
    }
}
