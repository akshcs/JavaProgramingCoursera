package org.example.util.SortingAlgorithms.ImplementingSelectionSort.EarthquakeSortStarterProgram;

import org.example.DAO.QuakeEntry;
import java.util.ArrayList;

public class QuakeSortWithTwoArrayLists {
    // This is the code from the Video of Selection Sort with Two ArrayLists
    
    public QuakeSortWithTwoArrayLists() {
        // TODO Auto-generated constructor stub
    }
   
    public QuakeEntry getSmallestMagnitude(ArrayList<QuakeEntry> quakes) {
        QuakeEntry min = quakes.get(0);
        for (QuakeEntry q: quakes) {
            if (q.getMagnitude() < min.getMagnitude()) {
                min = q;
            }
        }
        
        return min;
    }
    
    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in) {
        ArrayList<QuakeEntry> out = new ArrayList<QuakeEntry>();
        while(!in.isEmpty()) {
            QuakeEntry minElement = getSmallestMagnitude(in);
            in.remove(minElement);
            out.add(minElement);
        }
        
        return out;
    }
}
