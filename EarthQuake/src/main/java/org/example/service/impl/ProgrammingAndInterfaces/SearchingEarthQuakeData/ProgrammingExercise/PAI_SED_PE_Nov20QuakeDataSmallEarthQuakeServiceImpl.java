package org.example.service.impl.ProgrammingAndInterfaces.SearchingEarthQuakeData.ProgrammingExercise;

import org.example.client.intf.EarthQuakeClient;
import org.example.service.impl.EarthQuakeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("PAI_SED_PE_Nov20_Small")
@Service
public class PAI_SED_PE_Nov20QuakeDataSmallEarthQuakeServiceImpl extends EarthQuakeServiceImpl {

    @Qualifier("PAI_SED_PE_Nov20_Small")
    @Autowired
    private EarthQuakeClient earthQuakeClient;

    @Override
    public EarthQuakeClient getEarthQuakeClient() {
        return earthQuakeClient;
    }
}
