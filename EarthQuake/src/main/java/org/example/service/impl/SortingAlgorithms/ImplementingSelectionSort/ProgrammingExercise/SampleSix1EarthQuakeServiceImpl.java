package org.example.service.impl.SortingAlgorithms.ImplementingSelectionSort.ProgrammingExercise;

import org.example.client.intf.EarthQuakeClient;
import org.example.service.impl.EarthQuakeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("SA_ISS_PE_SampleSix_1")
@Service
public class SampleSix1EarthQuakeServiceImpl extends EarthQuakeServiceImpl {
    @Qualifier("SA_ISS_PE_SampleSix_1")
    @Autowired
    private EarthQuakeClient earthQuakeClient;
    @Override
    public EarthQuakeClient getEarthQuakeClient() {
        return earthQuakeClient;
    }
}
