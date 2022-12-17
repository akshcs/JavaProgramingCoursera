package org.example.client.impl.ProgrammingAndInterfaces.FilteringData.PracticeQuiz;

import org.example.client.impl.EarthQuakeClientImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("PAI_FD_PQ_Nov20")
@Component
public class PAI_FD_PQ_Nov20QuakeDataEarthQuakeClientImpl extends EarthQuakeClientImpl {
    @Override
    public String getDataSource() {
        return "EarthQuake/src/main/resources/JavaProgrammingDesignPrinciples/ProgrammingAndInterfaces/FilteringData/data/PracticeQuiz/nov20quakedata.atom.txt";
    }

//    @Override
//    public ArrayList<Filter> getDepthMinMaxFilter(double minDepth, double maxDepth){
//        ArrayList<Filter> filters = new ArrayList<>();
//        filters.add(new MinEqualDepthFilter(minDepth));
//        filters.add(new MaxEqualDepthFilter(maxDepth));
//        return filters;
//    }
//
//    @Override
//    public ArrayList<Filter> getMagnitudeMinMaxFilter(double minMagnitude, double maxMagnitude){
//        ArrayList<Filter> filters = new ArrayList<>();
//        filters.add(new MinEqualMagnitudeFilter(minMagnitude));
//        filters.add(new MaxEqualMagnitudeFilter(maxMagnitude));
//        return filters;
//    }
}
