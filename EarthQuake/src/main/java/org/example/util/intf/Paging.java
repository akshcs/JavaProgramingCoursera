package org.example.util.intf;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Paging<T> {
    Page<T> getDataInPages(List<T> data, Pageable pageable);
}
