package org.example.util.impl.depth;

import org.example.DAO.QuakeEntry;
import org.example.util.intf.Filter;

public class MinDepthFilter implements Filter {
    private double minDepth;

    public MinDepthFilter(double minDepth){
        this.minDepth = minDepth;
    }
    @Override
    public boolean satisfies(QuakeEntry quakeEntry) {
        return (quakeEntry.getDepth()>minDepth);
    }
}
