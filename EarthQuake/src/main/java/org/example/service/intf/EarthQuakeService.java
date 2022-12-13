package org.example.service.intf;

import org.example.DAO.QuakeEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface EarthQuakeService {
    ArrayList<QuakeEntry> getAllQuakes();

    ArrayList<QuakeEntry> getAllBigQuakes(double magMin);

    ArrayList<QuakeEntry> getQuakesCloseToMe(double latitude, double longitude, double distance);

    ArrayList<QuakeEntry> getQuakesCloseToMeInACountry(double latitude, double longitude, double distance, String country);

    ArrayList<QuakeEntry> getQuakesByDepth(double minDepth, double maxDepth);

    ArrayList<QuakeEntry> getQuakesByDepthAndMagnitude(double minDepth, double maxDepth, double minMagnitude, double maxMagnitude);

    ArrayList<QuakeEntry> getQuakesByDepthMagnitudeAndPhrase(double minDepth, double maxDepth, double minMagnitude, double maxMagnitude,
                                                             String phrase, String phasePattern);

    ArrayList<QuakeEntry> getQuakesByPhrase(String phasePattern, String phrase);

    ArrayList<QuakeEntry> getNClosestQuakesFromMe(double latitude, double longitude, int howMany);

    ArrayList<QuakeEntry> getNLargest(int howMany);

    ArrayList<QuakeEntry> getQuakesCloseToMeWithMagnitudeAndPhrase(double latitude, double longitude, double distance, double minMagnitude,
                                                                   double maxMagnitude, String phrasePattern, String phrase);

    Page<QuakeEntry> getQuakesPagedAndSorted(String sortingAlgo, String sortingParam, boolean isAsc, Pageable pageable);

    Page<QuakeEntry> getQuakesPagedAndSortedWithComparator(String comparator, boolean asc, Pageable pageable);
}
