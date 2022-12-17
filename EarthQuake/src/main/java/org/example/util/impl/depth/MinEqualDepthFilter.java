package org.example.util.impl.depth;

import org.example.DAO.QuakeEntry;
import org.example.util.intf.Filter;

public class MinEqualDepthFilter implements Filter {
    private double minDepth;

    public MinEqualDepthFilter(double minDepth){
        this.minDepth = minDepth;
    }
    @Override
    public boolean satisfies(QuakeEntry quakeEntry) {
        return (quakeEntry.getDepth()>=minDepth);
    }
}
