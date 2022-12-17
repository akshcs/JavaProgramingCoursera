package org.example.client.impl.SortingAlgorithms.ImplementingSelectionSort.ProgrammingExercise;

import org.example.client.impl.EarthQuakeClientImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Qualifier("SA_ISS_PE_SampleSix_2")
@Component
public class SampleSix2EarthquakeClientImpl extends EarthQuakeClientImpl {
    @Override
    public String getDataSource() {
        return "EarthQuake/src/main/resources/JavaProgrammingDesignPrinciples/SortingAlgorithms/ImplementingSelectionSort/data/ProgrammingExercise/earthquakeDataSampleSix2.atom.txt";
    }
}
