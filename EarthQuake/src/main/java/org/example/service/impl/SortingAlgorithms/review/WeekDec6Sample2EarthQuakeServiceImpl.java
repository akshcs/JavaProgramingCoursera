package org.example.service.impl.SortingAlgorithms.review;

import org.example.client.intf.EarthQuakeClient;
import org.example.service.impl.EarthQuakeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("SA_Review_Week_Dec6_Sample2")
@Service
public class WeekDec6Sample2EarthQuakeServiceImpl extends EarthQuakeServiceImpl {
    @Qualifier("SA_Review_Week_Dec6_Sample2")
    @Autowired
    private EarthQuakeClient earthQuakeClient;
    @Override
    public EarthQuakeClient getEarthQuakeClient() {
        return earthQuakeClient;
    }
}
