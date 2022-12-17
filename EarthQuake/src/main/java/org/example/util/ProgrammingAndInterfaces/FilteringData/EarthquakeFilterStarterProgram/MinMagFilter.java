package org.example.util.ProgrammingAndInterfaces.FilteringData.EarthquakeFilterStarterProgram;

import org.example.DAO.QuakeEntry;
public class MinMagFilter implements Filter
{
    private double magMin; 
    
    public MinMagFilter(double min) { 
        magMin = min;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    } 

}
