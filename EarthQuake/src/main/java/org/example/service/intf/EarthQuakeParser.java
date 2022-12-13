package org.example.service.intf;

import org.example.DAO.QuakeEntry;

import java.util.ArrayList;

public interface EarthQuakeParser {
    ArrayList<QuakeEntry> read(String source);
}
