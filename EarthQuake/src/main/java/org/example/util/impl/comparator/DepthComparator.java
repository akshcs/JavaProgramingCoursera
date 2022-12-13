package org.example.util.impl.comparator;

import org.example.DAO.QuakeEntry;

import java.util.Comparator;

public class DepthComparator implements Comparator<QuakeEntry> {
    @Override
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        return Double.compare(qe1.getDepth(), qe2.getDepth());
    }
}
