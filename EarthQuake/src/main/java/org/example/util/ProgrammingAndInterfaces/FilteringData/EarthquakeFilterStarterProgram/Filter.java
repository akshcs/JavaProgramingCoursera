package org.example.util.ProgrammingAndInterfaces.FilteringData.EarthquakeFilterStarterProgram;

import org.example.DAO.QuakeEntry;
public interface Filter
{
    public  boolean satisfies(QuakeEntry qe); 
}
