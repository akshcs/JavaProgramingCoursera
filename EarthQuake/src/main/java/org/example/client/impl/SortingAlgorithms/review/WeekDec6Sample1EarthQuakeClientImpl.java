package org.example.client.impl.SortingAlgorithms.review;

import org.example.client.impl.EarthQuakeClientImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Qualifier("SA_Review_Week_Dec6_Sample1")
@Component
public class WeekDec6Sample1EarthQuakeClientImpl extends EarthQuakeClientImpl {
    @Override
    public String getDataSource() {
        return "EarthQuake/src/main/resources/JavaProgrammingDesignPrinciples/SortingAlgorithms/review/data/earthQuakeDataWeekDec6sample1.atom.txt";
    }
}
