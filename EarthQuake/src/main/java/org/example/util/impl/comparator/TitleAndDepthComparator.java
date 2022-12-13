package org.example.util.impl.comparator;

import org.example.DAO.QuakeEntry;

import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    @Override
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        int titleDiff = qe1.getInfo().compareTo(qe2.getInfo());
        if(titleDiff!=0){
            return titleDiff;
        }
        return Double.compare(qe1.getDepth(), qe2.getDepth());
    }
}
