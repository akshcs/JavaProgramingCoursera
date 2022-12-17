package org.example.client.impl.ProgrammingAndInterfaces.SearchingEarthQuakeData.ProgrammingExercise;

import org.example.client.impl.EarthQuakeClientImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("PAI_SED_PE_Nov20_Small")
@Component
public class PAI_SED_PE_Nov20QuakeDataSmallEarthQuakeClientImpl extends EarthQuakeClientImpl {
    @Override
    public String getDataSource() {
        return "EarthQuake/src/main/resources/JavaProgrammingDesignPrinciples/ProgrammingAndInterfaces/SearchingEarthQuakeData/data/ProgrammingExercise/nov20quakedatasmall.atom.txt";
    }
}
