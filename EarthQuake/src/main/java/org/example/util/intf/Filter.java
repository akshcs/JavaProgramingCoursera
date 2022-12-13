package org.example.util.intf;

import org.example.DAO.QuakeEntry;

public interface Filter {
    boolean satisfies(QuakeEntry quakeEntry);
}
