package org.example.util.impl.depth;

import org.example.DAO.QuakeEntry;
import org.example.util.intf.Filter;

public class MaxDepthFilter implements Filter {
    private double maxDepth;

    public MaxDepthFilter(double maxDepth){
        this.maxDepth = maxDepth;
    }
    @Override
    public boolean satisfies(QuakeEntry quakeEntry) {
        return (quakeEntry.getDepth()<maxDepth);
    }
}
