package org.example.util.impl;

import org.example.util.intf.Paging;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PagingImpl<T> implements Paging<T> {
    @Override
    public Page<T> getDataInPages(List<T> data, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = ((start + pageable.getPageSize()) > data.size() ? data.size()
                : (start + pageable.getPageSize()));
        Page<T> page
                = new PageImpl<T>(data.subList(start, end), pageable, data.size());
        return page;
    }
}
