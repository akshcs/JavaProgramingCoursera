package org.example.util.impl.magnitude;

import org.example.DAO.QuakeEntry;
import org.example.util.intf.Filter;

public class MinMagnitudeFilter implements Filter {
    private double minMagnitude;

    public MinMagnitudeFilter(double minMagnitude){
        this.minMagnitude = minMagnitude;
    }

    @Override
    public boolean satisfies(QuakeEntry quakeEntry) {
        return (quakeEntry.getMagnitude()>=minMagnitude);
    }
}
