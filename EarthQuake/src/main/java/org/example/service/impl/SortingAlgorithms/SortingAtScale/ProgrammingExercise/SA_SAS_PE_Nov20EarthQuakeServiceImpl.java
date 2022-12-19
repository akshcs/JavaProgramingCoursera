package org.example.service.impl.SortingAlgorithms.SortingAtScale.ProgrammingExercise;

import org.example.client.intf.EarthQuakeClient;
import org.example.service.impl.EarthQuakeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("SA_SAS_PE_Nov20")
@Service
public class SA_SAS_PE_Nov20EarthQuakeServiceImpl extends EarthQuakeServiceImpl {
    @Qualifier("SA_SAS_PE_Nov20")
    @Autowired
    private EarthQuakeClient earthQuakeClient;
    @Override
    public EarthQuakeClient getEarthQuakeClient() {
        return earthQuakeClient;
    }
}
