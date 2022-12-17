package org.example.client.intf;


import org.example.DAO.Location;
import org.example.DAO.QuakeEntry;
import org.example.service.impl.EarthQuakeServiceImpl;
import org.example.util.intf.Filter;

import java.util.ArrayList;

public interface EarthQuakeClient {

    ArrayList<QuakeEntry> getAllQuakes();

    ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeEntries, Filter filter);

    void dumpCSV(ArrayList<QuakeEntry> list);
    ArrayList<QuakeEntry> bigQuakes(double magMin);
    void createCSV();
    ArrayList<QuakeEntry> closeToMe(Location city, double distance);

    ArrayList<QuakeEntry> closeToMeInACountry(Location city, double distance, String country);

    ArrayList<QuakeEntry> quakesOfDepth(double minDepth, double maxDepth);

    ArrayList<QuakeEntry> quakesOfDepthAndMagnitude(double minDepth, double maxDepth, double minMagnitude, double maxMagnitude);

    ArrayList<QuakeEntry> quakesOfDepthMagnitudeAndPhrase(double minDepth, double maxDepth, double minMagnitude, double maxMagnitude,
                                                          String phrase, EarthQuakeServiceImpl.PhrasePattern pattern);

    ArrayList<QuakeEntry> quakesOfPhrase(String phrase, EarthQuakeServiceImpl.PhrasePattern pattern);

    ArrayList<QuakeEntry> findClosestQuakes(Location city, int howMany);

    ArrayList<QuakeEntry> findLargestQuakes(int howMany);

    ArrayList<QuakeEntry> getQuakesCloseToMeWithMagnitudeAndPhrase(double distance, Location city, double minMagnitude, double maxMagnitude,
                                                                   String phrase, EarthQuakeServiceImpl.PhrasePattern pattern);

    int sort(ArrayList<QuakeEntry> allQuakes, String sortingAlgo, String sortingParam, boolean isAsc, int passes);

    void sortWithComparator(ArrayList<QuakeEntry> allQuakes, String comparator, boolean asc);

    String getDataSource();

    ArrayList<Filter> getDepthMinMaxFilter(double minDepth, double maxDepth);

    ArrayList<Filter> getMagnitudeMinMaxFilter(double minMagnitude, double maxMagnitude);
}
