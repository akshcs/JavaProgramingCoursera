package org.example.service.impl;

import org.example.DAO.Location;
import org.example.DAO.QuakeEntry;
import org.example.client.intf.EarthQuakeClient;
import org.example.service.intf.EarthQuakeService;
import org.example.util.impl.magnitude.MinMagnitudeFilter;
import org.example.util.intf.Filter;
import org.example.util.intf.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public abstract class EarthQuakeServiceImpl implements EarthQuakeService {
    public enum PhrasePattern {
        START,
        END,
        ANY,
    }

    private EarthQuakeClient earthQuakeClient;

    @Autowired
    private Paging paging;

    public abstract EarthQuakeClient getEarthQuakeClient();

    @Override
    public ArrayList<QuakeEntry> getAllQuakes() {
        return getEarthQuakeClient().getAllQuakes();
    }

    @Override
    public ArrayList<QuakeEntry> getAllBigQuakes(double magMin) {
        ArrayList<QuakeEntry> quakeEntries = getEarthQuakeClient().getAllQuakes();
        Filter minMagnitudeFilter = new MinMagnitudeFilter(magMin);
        ArrayList<QuakeEntry> filteredQuakeEntries = getEarthQuakeClient().filter(quakeEntries, minMagnitudeFilter);
        System.out.println("# Quakes with magnitude greater than "+ magMin +  " = "+ filteredQuakeEntries.size());
        return filteredQuakeEntries;
    }

    @Override
    public ArrayList<QuakeEntry> getQuakesCloseToMe(double latitude, double longitude, double distance) {
        Location city = new Location(latitude, longitude);
        return getEarthQuakeClient().closeToMe(city, distance);
    }

    @Override
    public ArrayList<QuakeEntry> getQuakesCloseToMeInACountry(double latitude, double longitude, double distance, String country) {
        Location city = new Location(latitude, longitude);
        return getEarthQuakeClient().closeToMeInACountry(city, distance, country);
    }

    @Override
    public ArrayList<QuakeEntry> getQuakesByDepth(double minDepth, double maxDepth) {
        return getEarthQuakeClient().quakesOfDepth(minDepth, maxDepth);
    }

    @Override
    public ArrayList<QuakeEntry> getQuakesByDepthAndMagnitude(double minDepth, double maxDepth, double minMagnitude, double maxMagnitude) {
        return getEarthQuakeClient().quakesOfDepthAndMagnitude(minDepth, maxDepth, minMagnitude, maxMagnitude);
    }

    @Override
    public ArrayList<QuakeEntry> getQuakesByDepthMagnitudeAndPhrase(double minDepth, double maxDepth, double minMagnitude, double maxMagnitude,
                                                                    String phrase, String phasePattern) {
        try {
            PhrasePattern pattern = PhrasePattern.valueOf(phasePattern);
            return getEarthQuakeClient().quakesOfDepthMagnitudeAndPhrase(minDepth, maxDepth, minMagnitude, maxMagnitude, phrase, pattern);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Please Use a Valid Phase Pattern Only (START,END and ANY) are allowed");
        }
    }

    @Override
    public ArrayList<QuakeEntry> getQuakesByPhrase(String phasePattern, String phrase) {
        try {
            PhrasePattern pattern = PhrasePattern.valueOf(phasePattern);
            return getEarthQuakeClient().quakesOfPhrase(phrase, pattern);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Please Use a Valid Phase Pattern Only (START,END and ANY) are allowed");
        }
    }

    @Override
    public ArrayList<QuakeEntry> getNClosestQuakesFromMe(double latitude, double longitude, int howMany) {
        Location city = new Location(latitude, longitude);
        return getEarthQuakeClient().findClosestQuakes(city, howMany);
    }

    @Override
    public ArrayList<QuakeEntry> getNLargest(int howMany) {
        return getEarthQuakeClient().findLargestQuakes(howMany);
    }

    @Override
    public ArrayList<QuakeEntry> getQuakesCloseToMeWithMagnitudeAndPhrase(double latitude, double longitude, double distance,
                                                                          double minMagnitude, double maxMagnitude, String phasePattern, String phrase) {
        try {
            PhrasePattern pattern = PhrasePattern.valueOf(phasePattern);
            Location city = new Location(latitude, longitude);
            return getEarthQuakeClient().getQuakesCloseToMeWithMagnitudeAndPhrase(distance, city, minMagnitude, maxMagnitude, phrase, pattern);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Please Use a Valid Phase Pattern Only (START,END and ANY) are allowed");
        }
    }

    @Override
    public Page<QuakeEntry> getQuakesPagedAndSorted(String sortingAlgo, String sortingParam, boolean isAsc, int passes, Pageable pageable){
        ArrayList<QuakeEntry> allQuakes = getAllQuakes();
        int numberOfSwaps = getEarthQuakeClient().sort(allQuakes, sortingAlgo, sortingParam, isAsc, passes);
        System.out.println("Number of Passes = " + numberOfSwaps);
        return paging.getDataInPages(allQuakes, pageable);
    }

    @Override
    public Page<QuakeEntry> getQuakesPagedAndSortedWithComparator(String comparator, boolean asc, Pageable pageable) {
        ArrayList<QuakeEntry> allQuakes = getAllQuakes();
        getEarthQuakeClient().sortWithComparator(allQuakes, comparator, asc);
        return paging.getDataInPages(allQuakes, pageable);
    }
}
