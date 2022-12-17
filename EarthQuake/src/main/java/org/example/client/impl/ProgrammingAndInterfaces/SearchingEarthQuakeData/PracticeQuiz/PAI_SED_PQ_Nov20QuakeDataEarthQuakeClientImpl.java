package org.example.client.impl.ProgrammingAndInterfaces.SearchingEarthQuakeData.PracticeQuiz;

import org.example.client.impl.EarthQuakeClientImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("PAI_SED_PQ_Nov20")
@Component
public class PAI_SED_PQ_Nov20QuakeDataEarthQuakeClientImpl extends EarthQuakeClientImpl {
    @Override
    public String getDataSource() {
        return "EarthQuake/src/main/resources/JavaProgrammingDesignPrinciples/ProgrammingAndInterfaces/SearchingEarthQuakeData/data/PracticeQuiz/nov20quakedata.atom.txt";
    }
}
