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
public class EarthQuakeServiceImpl implements EarthQuakeService {
    public enum PhrasePattern {
        START,
        END,
        ANY,
    }
    @Autowired
    private EarthQuakeClient earthQuakeClient;

    @Autowired
    private Paging paging;

    @Override
    public ArrayList<QuakeEntry> getAllQuakes() {
        return earthQuakeClient.getAllQuakes();
    }

    @Override
    public ArrayList<QuakeEntry> getAllBigQuakes(double magMin) {
        ArrayList<QuakeEntry> quakeEntries = earthQuakeClient.getAllQuakes();
        Filter minMagnitudeFilter = new MinMagnitudeFilter(magMin);
        ArrayList<QuakeEntry> filteredQuakeEntries = earthQuakeClient.filter(quakeEntries, minMagnitudeFilter);
        System.out.println("# Quakes with magnitude greater than "+ magMin +  " = "+ filteredQuakeEntries.size());
        return filteredQuakeEntries;
    }

    @Override
    public ArrayList<QuakeEntry> getQuakesCloseToMe(double latitude, double longitude, double distance) {
        Location city = new Location(latitude, longitude);
        return earthQuakeClient.closeToMe(city, distance);
    }

    @Override
    public ArrayList<QuakeEntry> getQuakesCloseToMeInACountry(double latitude, double longitude, double distance, String country) {
        Location city = new Location(latitude, longitude);
        return earthQuakeClient.closeToMeInACountry(city, distance, country);
    }

    @Override
    public ArrayList<QuakeEntry> getQuakesByDepth(double minDepth, double maxDepth) {
        return earthQuakeClient.quakesOfDepth(minDepth, maxDepth);
    }

    @Override
    public ArrayList<QuakeEntry> getQuakesByDepthAndMagnitude(double minDepth, double maxDepth, double minMagnitude, double maxMagnitude) {
        return earthQuakeClient.quakesOfDepthAndMagnitude(minDepth, maxDepth, minMagnitude, maxMagnitude);
    }

    @Override
    public ArrayList<QuakeEntry> getQuakesByDepthMagnitudeAndPhrase(double minDepth, double maxDepth, double minMagnitude, double maxMagnitude,
                                                                    String phrase, String phasePattern) {
        try {
            PhrasePattern pattern = PhrasePattern.valueOf(phasePattern);
            return earthQuakeClient.quakesOfDepthMagnitudeAndPhrase(minDepth, maxDepth, minMagnitude, maxMagnitude, phrase, pattern);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Please Use a Valid Phase Pattern Only (START,END and ANY) are allowed");
        }
    }

    @Override
    public ArrayList<QuakeEntry> getQuakesByPhrase(String phasePattern, String phrase) {
        try {
            PhrasePattern pattern = PhrasePattern.valueOf(phasePattern);
            return earthQuakeClient.quakesOfPhrase(phrase, pattern);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Please Use a Valid Phase Pattern Only (START,END and ANY) are allowed");
        }
    }

    @Override
    public ArrayList<QuakeEntry> getNClosestQuakesFromMe(double latitude, double longitude, int howMany) {
        Location city = new Location(latitude, longitude);
        return earthQuakeClient.findClosestQuakes(city, howMany);
    }

    @Override
    public ArrayList<QuakeEntry> getNLargest(int howMany) {
        return earthQuakeClient.findLargestQuakes(howMany);
    }

    @Override
    public ArrayList<QuakeEntry> getQuakesCloseToMeWithMagnitudeAndPhrase(double latitude, double longitude, double distance,
                                                                          double minMagnitude, double maxMagnitude, String phasePattern, String phrase) {
        try {
            PhrasePattern pattern = PhrasePattern.valueOf(phasePattern);
            Location city = new Location(latitude, longitude);
            return earthQuakeClient.getQuakesCloseToMeWithMagnitudeAndPhrase(distance, city, minMagnitude, maxMagnitude, phrase, pattern);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Please Use a Valid Phase Pattern Only (START,END and ANY) are allowed");
        }
    }

    @Override
    public Page<QuakeEntry> getQuakesPagedAndSorted(String sortingAlgo, String sortingParam, boolean isAsc, Pageable pageable){
        ArrayList<QuakeEntry> allQuakes = getAllQuakes();
        int numberOfSwaps = earthQuakeClient.sort(allQuakes, sortingAlgo, sortingParam, isAsc);
        System.out.println("Number of Swaps  = " + numberOfSwaps);
        return paging.getDataInPages(allQuakes, pageable);
    }

    @Override
    public Page<QuakeEntry> getQuakesPagedAndSortedWithComparator(String comparator, boolean asc, Pageable pageable) {
        ArrayList<QuakeEntry> allQuakes = getAllQuakes();
        earthQuakeClient.sortWithComparator(allQuakes, comparator, asc);
        return paging.getDataInPages(allQuakes, pageable);
    }
}
