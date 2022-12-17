package org.example.service.impl.ProgrammingAndInterfaces.review;

import org.example.client.intf.EarthQuakeClient;
import org.example.service.impl.EarthQuakeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("PAI_Review_Nov20")
@Service
public class PAI_Review_Nov20QuakeDataEarthQuakeServiceImpl extends EarthQuakeServiceImpl {

    @Qualifier("PAI_Review_Nov20")
    @Autowired
    private EarthQuakeClient earthQuakeClient;

    @Override
    public EarthQuakeClient getEarthQuakeClient() {
        return earthQuakeClient;
    }
}
