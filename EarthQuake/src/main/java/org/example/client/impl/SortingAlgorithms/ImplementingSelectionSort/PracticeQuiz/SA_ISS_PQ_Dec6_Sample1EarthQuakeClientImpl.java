package org.example.client.impl.SortingAlgorithms.ImplementingSelectionSort.PracticeQuiz;

import org.example.client.impl.EarthQuakeClientImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Qualifier("SA_ISS_PQ_Dec6_Sample1")
@Component
public class SA_ISS_PQ_Dec6_Sample1EarthQuakeClientImpl extends EarthQuakeClientImpl {
    @Override
    public String getDataSource() {
        return "EarthQuake/src/main/resources/JavaProgrammingDesignPrinciples/SortingAlgorithms/ImplementingSelectionSort/data/PracticeQuiz/earthQuakeDataDec6sample1.atom.txt";
    }
}
