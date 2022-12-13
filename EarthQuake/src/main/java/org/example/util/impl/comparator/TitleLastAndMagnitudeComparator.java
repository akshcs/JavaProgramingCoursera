package org.example.util.impl.comparator;

import org.example.DAO.QuakeEntry;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    @Override
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String[] qe1_Title = qe1.getInfo().strip().split(" ");
        String[] qe2_Title = qe2.getInfo().strip().split(" ");
        int titleLastDiff = qe1_Title[qe1_Title.length-1].compareTo(qe2_Title[qe2_Title.length-1]);
        if(titleLastDiff!=0){
            return titleLastDiff;
        }
        return Double.compare(qe1.getMagnitude(),qe2.getMagnitude());
    }
}
