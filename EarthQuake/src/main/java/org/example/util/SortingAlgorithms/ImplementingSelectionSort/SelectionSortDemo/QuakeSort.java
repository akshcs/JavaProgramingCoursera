package org.example.util.SortingAlgorithms.ImplementingSelectionSort.SelectionSortDemo;

import org.example.DAO.QuakeEntry;
import java.util.ArrayList;

public class QuakeSort {
    public QuakeEntry getSmallestMagnitude(ArrayList<QuakeEntry> quakes) {
        QuakeEntry min = quakes.get(0);
        for(QuakeEntry q: quakes) {
            if (q.getMagnitude() < min.getMagnitude()) {
                min = q;
            }
        }
        return min;
    }
    
    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in) {
        //out starts as empty ArrayList
        ArrayList<QuakeEntry> out = new ArrayList<QuakeEntry>();
        //As long as in is not empty
        while(!in.isEmpty()) {
            //Find smallest element in in (minElement)
            QuakeEntry minElement = getSmallestMagnitude(in); 
            //Remove minElement from in
            in.remove(minElement);                            
            //Add minElement to out
            out.add(minElement);
        }
        //out is the answer
        return out;
    }
}
