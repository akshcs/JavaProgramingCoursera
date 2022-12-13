package org.example.util.impl.comparator;

import org.example.DAO.QuakeEntry;

import java.util.Comparator;

public class MagnitudeAndDepthComparator implements Comparator<QuakeEntry> {
    @Override
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        int magDiff = Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        if(magDiff!=0){
            return magDiff;
        }
        return Double.compare(qe2.getDepth(), qe1.getDepth());
    }
}
