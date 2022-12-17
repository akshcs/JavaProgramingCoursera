package org.example.service.impl.SortingAlgorithms.SortingAtScale.PracticeQuiz;

import org.example.client.intf.EarthQuakeClient;
import org.example.service.impl.EarthQuakeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("SA_SAS_PQ_Dec6_Sample1")
@Service
public class Dec6Sample1EarthQuakeServiceImpl extends EarthQuakeServiceImpl {
    @Qualifier("SA_SAS_PQ_Dec6_Sample1")
    @Autowired
    private EarthQuakeClient earthQuakeClient;
    @Override
    public EarthQuakeClient getEarthQuakeClient() {
        return earthQuakeClient;
    }
}
