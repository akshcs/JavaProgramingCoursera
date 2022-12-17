package org.example.util.SortingAlgorithms.ImplementingSelectionSort.EarthquakeSortStarterProgram;

import org.example.DAO.QuakeEntry;
import java.util.ArrayList;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minMag = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minMag).getMagnitude()) {
                minMag = i;
            }
        }
        return minMag;
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxDepth = from;
        for (int i=from+1; i< quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(maxDepth).getDepth()) {
                maxDepth = i;
            }
        }
        return maxDepth;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       for (int i=0; i< in.size(); i++) {
            int minMag = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qMinMag = in.get(minMag);
            in.set(i,qMinMag);
            in.set(minMag,qi);
        }
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for (int i=0; i< in.size(); i++) {
            int maxDepth = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qMaxDepth = in.get(maxDepth);
            in.set(i,qMaxDepth);
            in.set(maxDepth,qi);
        }
    }
}
