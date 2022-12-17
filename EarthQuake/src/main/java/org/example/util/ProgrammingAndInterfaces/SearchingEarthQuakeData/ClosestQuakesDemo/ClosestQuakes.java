package org.example.util.ProgrammingAndInterfaces.SearchingEarthQuakeData.ClosestQuakesDemo;

import org.example.DAO.QuakeEntry;
import org.example.DAO.Location;
import java.util.ArrayList;

public class ClosestQuakes
{
   public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for(int j=0; j < howMany; j++) {
            int minIndex = 0;
            for(int k=1; k < copy.size(); k++){
                QuakeEntry quake = copy.get(k);
                Location loc = quake.getLocation();
                if (loc.distanceTo(current) < 
                    copy.get(minIndex).getLocation().distanceTo(current)){
                    minIndex = k;   
                }
            }
          
            ret.add(copy.get(minIndex));
            copy.remove(minIndex);
        }
        return ret;
   }
}
