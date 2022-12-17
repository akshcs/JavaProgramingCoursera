package org.example.client.impl.SortingAlgorithms.SortingAtScale.PracticeQuiz;

import org.example.client.impl.EarthQuakeClientImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Qualifier("SA_SAS_PQ_Dec6_Sample2")
@Component
public class Dec6Sample2EarthQuakeClientImpl extends EarthQuakeClientImpl {
    @Override
    public String getDataSource() {
        return "EarthQuake/src/main/resources/JavaProgrammingDesignPrinciples/SortingAlgorithms/SortingAtScale/data/PracticeQuiz/earthQuakeDataDec6sample2.atom.txt";
    }
}
