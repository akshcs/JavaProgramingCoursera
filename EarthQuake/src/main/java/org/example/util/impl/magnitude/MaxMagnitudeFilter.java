package org.example.util.impl.magnitude;

import org.example.DAO.QuakeEntry;
import org.example.util.intf.Filter;

public class MaxMagnitudeFilter implements Filter {
    private double maxMagnitude;

    public MaxMagnitudeFilter(double minMagnitude){
        this.maxMagnitude = minMagnitude;
    }

    @Override
    public boolean satisfies(QuakeEntry quakeEntry) {
        return (quakeEntry.getMagnitude()<maxMagnitude);
    }
}
