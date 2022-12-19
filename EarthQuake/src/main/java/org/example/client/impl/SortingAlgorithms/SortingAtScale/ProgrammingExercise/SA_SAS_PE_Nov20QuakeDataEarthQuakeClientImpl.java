package org.example.client.impl.SortingAlgorithms.SortingAtScale.ProgrammingExercise;

import org.example.client.impl.EarthQuakeClientImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Qualifier("SA_SAS_PE_Nov20")
@Component
public class SA_SAS_PE_Nov20QuakeDataEarthQuakeClientImpl extends EarthQuakeClientImpl {
    @Override
    public String getDataSource() {
        return "EarthQuake/src/main/resources/JavaProgrammingDesignPrinciples/SortingAlgorithms/SortingAtScale/data/ProgrammingExercise/nov20quakedata.atom.txt";
    }
}
