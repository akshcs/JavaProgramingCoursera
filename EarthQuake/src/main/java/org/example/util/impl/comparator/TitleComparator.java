package org.example.util.impl.comparator;

import org.example.DAO.QuakeEntry;

import java.util.Comparator;

public class TitleComparator implements Comparator<QuakeEntry> {
    @Override
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        return qe1.getInfo().compareTo(qe2.getInfo());
    }
}
