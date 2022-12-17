package org.example.client.impl.ProgrammingAndInterfaces.FilteringData.ProgrammingExercise;

import org.example.client.impl.EarthQuakeClientImpl;
import org.example.util.impl.depth.MaxEqualDepthFilter;
import org.example.util.impl.depth.MinEqualDepthFilter;
import org.example.util.impl.magnitude.MaxEqualMagnitudeFilter;
import org.example.util.impl.magnitude.MinEqualMagnitudeFilter;
import org.example.util.intf.Filter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Qualifier("PAI_FD_PE_Nov20_Small")
@Component
public class PAI_FD_PE_Nov20QuakeDataSmallEarthQuakeClientImpl extends EarthQuakeClientImpl {
    @Override
    public String getDataSource() {
        return "EarthQuake/src/main/resources/JavaProgrammingDesignPrinciples/ProgrammingAndInterfaces/FilteringData/data/ProgrammingExercise/nov20quakedatasmall.atom.txt";
    }

    @Override
    public ArrayList<Filter> getDepthMinMaxFilter(double minDepth, double maxDepth){
        ArrayList<Filter> filters = new ArrayList<>();
        filters.add(new MinEqualDepthFilter(minDepth));
        filters.add(new MaxEqualDepthFilter(maxDepth));
        return filters;
    }

    @Override
    public ArrayList<Filter> getMagnitudeMinMaxFilter(double minMagnitude, double maxMagnitude){
        ArrayList<Filter> filters = new ArrayList<>();
        filters.add(new MinEqualMagnitudeFilter(minMagnitude));
        filters.add(new MaxEqualMagnitudeFilter(maxMagnitude));
        return filters;
    }
}
