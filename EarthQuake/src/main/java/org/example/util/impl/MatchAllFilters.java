package org.example.util.impl;

import org.example.DAO.QuakeEntry;
import org.example.util.intf.Filter;

import java.util.ArrayList;

public class MatchAllFilters implements Filter {
    private ArrayList<Filter> filters = new ArrayList<>();

    public MatchAllFilters(ArrayList<Filter> filters){
        this.filters = filters;
    }

    @Override
    public boolean satisfies(QuakeEntry quakeEntry) {
        for(Filter filter: filters){
            if(!filter.satisfies(quakeEntry)){
                return false;
            }
        }
        return true;
    }
}
