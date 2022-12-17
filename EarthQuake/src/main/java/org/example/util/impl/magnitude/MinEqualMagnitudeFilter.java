package org.example.util.impl.magnitude;

import org.example.DAO.QuakeEntry;
import org.example.util.intf.Filter;

public class MinEqualMagnitudeFilter implements Filter {
    private double minMagnitude;

    public MinEqualMagnitudeFilter(double minMagnitude){
        this.minMagnitude = minMagnitude;
    }

    @Override
    public boolean satisfies(QuakeEntry quakeEntry) {
        return (quakeEntry.getMagnitude()>=minMagnitude);
    }
}
