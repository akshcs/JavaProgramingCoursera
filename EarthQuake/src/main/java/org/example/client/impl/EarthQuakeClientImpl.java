package org.example.client.impl;


import org.example.DAO.Location;
import org.example.DAO.QuakeEntry;
import org.example.client.intf.EarthQuakeClient;
import org.example.service.impl.EarthQuakeServiceImpl;
import org.example.service.intf.EarthQuakeParser;
import org.example.util.impl.MatchAllFilters;
import org.example.util.impl.comparator.*;
import org.example.util.impl.depth.MaxDepthFilter;
import org.example.util.impl.depth.MinDepthFilter;
import org.example.util.impl.distance.MaxDistanceFilter;
import org.example.util.impl.distance.MinDistanceFilter;
import org.example.util.impl.magnitude.MaxMagnitudeFilter;
import org.example.util.impl.magnitude.MinMagnitudeFilter;
import org.example.util.impl.title.TitleContainsPhraseFilter;
import org.example.util.impl.title.TitleEndAtPhraseFilter;
import org.example.util.impl.title.TitleStartFromPhraseFilter;
import org.example.util.intf.Filter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class EarthQuakeClientImpl implements EarthQuakeClient {

    @Autowired
    private EarthQuakeParser earthQuakeParser;

    public abstract String getDataSource();

    @Override
    public ArrayList<QuakeEntry> getAllQuakes(){
        ArrayList<QuakeEntry> quakeList  = earthQuakeParser.read(getDataSource());
        System.out.println("# quakes = "+quakeList.size());
        return quakeList;
    }

    @Override
    public ArrayList<QuakeEntry> bigQuakes(double magMin) {
        ArrayList<QuakeEntry> filteredQuakeEntries = filter(getAllQuakes(), new MinMagnitudeFilter(magMin));
        System.out.println("# Quakes with magnitude greater than "+ magMin +  " = "+ filteredQuakeEntries.size());
        return filteredQuakeEntries;
    }

    @Override
    public void createCSV(){
        dumpCSV(getAllQuakes());
    }

    @Override
    public ArrayList<QuakeEntry> closeToMe(Location city, double distance) {
        ArrayList<QuakeEntry> filteredQuakeEntries = filter(getAllQuakes(), new MaxDistanceFilter(distance, city));
        System.out.println("# Quakes with distance less than "+ distance + " from latitude(" + city.getLatitude() +  "), "+ "longitude(" + city.getLongitude() + ") = " + filteredQuakeEntries.size());
        return filteredQuakeEntries;
    }

    @Override
    public ArrayList<QuakeEntry> closeToMeInACountry(Location city, double distance, String country) {
        ArrayList<QuakeEntry> filteredQuakeEntries = filter(getAllQuakes(), new MatchAllFilters(getMaxDistanceFilter_InACountry(distance, city, country)));
        System.out.println("# Quakes with distance less than "+ distance + " from latitude(" + city.getLatitude() +  "), "+ "longitude(" + city.getLongitude() + ") In " + country + " = " + filteredQuakeEntries.size());
        return filteredQuakeEntries;
    }

    @Override
    public ArrayList<QuakeEntry> quakesOfDepth(double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> filteredQuakeEntries = filter(getAllQuakes(), new MatchAllFilters(getDepthMinMaxFilter(minDepth, maxDepth)));
        System.out.println("# Quakes of Depth between "+ minDepth + " - " + maxDepth +  " = "+ filteredQuakeEntries.size());
        return filteredQuakeEntries;
    }

    @Override
    public ArrayList<QuakeEntry> quakesOfDepthAndMagnitude(double minDepth, double maxDepth, double minMagnitude, double maxMagnitude) {
        ArrayList<QuakeEntry> filteredQuakeEntries = filter(getAllQuakes(), new MatchAllFilters(
                getMagnitudeMinMaxFilter_DepthMinMaxFilter(minMagnitude, maxMagnitude, minDepth, maxDepth)));
        System.out.println("# Quakes of Depth between ("+ minDepth + " - " + maxDepth +  ") and Magnitude between ("+ minMagnitude + " - " + maxMagnitude + ") = " + filteredQuakeEntries.size());
        return filteredQuakeEntries;
    }

    @Override
    public ArrayList<QuakeEntry> quakesOfDepthMagnitudeAndPhrase(double minDepth, double maxDepth, double minMagnitude, double maxMagnitude,
                                                                 String phrase, EarthQuakeServiceImpl.PhrasePattern pattern) {
        ArrayList<QuakeEntry> filteredQuakeEntries = filter(getAllQuakes(), new MatchAllFilters(
                getMagnitudeMinMaxFilter_DepthMinMaxFilter_FilterFromPattern(minMagnitude, maxMagnitude, minDepth, maxDepth, phrase, pattern)));
        System.out.println("# Quakes of Depth between ("+ minDepth + " - " + maxDepth +  ") , Magnitude between ("+ minMagnitude + " - " + maxMagnitude + ") And By Phase " + pattern + " - " + phrase +  " = "+ filteredQuakeEntries.size());
        return filteredQuakeEntries;
    }


    @Override
    public ArrayList<QuakeEntry> quakesOfPhrase(String phrase, EarthQuakeServiceImpl.PhrasePattern pattern) {
        ArrayList<QuakeEntry> quakesOfPhrase = filter(getAllQuakes(), getFilterFromPattern(phrase, pattern));
        System.out.println("# Quakes By Phase "+ pattern + " - " + phrase +  " = "+ quakesOfPhrase.size());
        return quakesOfPhrase;
    }

    @Override
    public ArrayList<QuakeEntry> findClosestQuakes(Location city, int howMany){
        ArrayList<QuakeEntry> closestQuakes = getNClosest(getAllQuakes(), city, howMany);
        System.out.println("number found: " + closestQuakes.size());
        return closestQuakes;
    }

    @Override
    public ArrayList<QuakeEntry> findLargestQuakes(int howMany) {
        ArrayList<QuakeEntry> largestQuakes = getNLargest(getAllQuakes(), howMany);
        System.out.println("number found: " + largestQuakes.size());
        return largestQuakes;
    }

    @Override
    public ArrayList<QuakeEntry> getQuakesCloseToMeWithMagnitudeAndPhrase(double distance, Location city, double minMagnitude,
                                                                          double maxMagnitude, String phrase, EarthQuakeServiceImpl.PhrasePattern pattern) {
        ArrayList<QuakeEntry> filteredQuakeEntries = filter(getAllQuakes(), new MatchAllFilters(
                getMagnitudeMinMaxFilter_MaxDistanceFilter_FilterFromPattern(distance, city, minMagnitude, maxMagnitude, phrase, pattern)));
        System.out.println("# Quakes At Max Distance of " +  distance + "meters from location ("+ city.getLatitude() + " , " + city.getLongitude() +
                ") with Magnitude between ("+ minMagnitude + " - " + maxMagnitude + ") And By Phase " + pattern + " - " + phrase +  " = "+
                filteredQuakeEntries.size());
        return filteredQuakeEntries;
    }

    @Override
    public int sort(ArrayList<QuakeEntry> allQuakes, String sortingAlgo, String sortingParam, boolean isAsc, int passes) {
        if(sortingAlgo.equals("SelectionSort")){
            return sortBySelectionSort(allQuakes, sortingParam, isAsc, passes);
        } else if(sortingAlgo.equals("BubbleSort")){
            return sortByBubbleSort(allQuakes, sortingParam, isAsc, passes);
        }
        return 0;
    }

    @Override
    public void sortWithComparator(ArrayList<QuakeEntry> allQuakes, String comparator, boolean asc) {
        Comparator comp = null;
        switch (comparator) {
            case "MagnitudeComparator":
                comp = new MagnitudeComparator();
                break;
            case "DepthComparator":
                comp = new DepthComparator();
                break;
            case "TitleComparator":
                comp = new TitleComparator();
                break;
            case "TitleAndDepthComparator":
                comp = new TitleAndDepthComparator();
                break;
            case "TitleLastAndMagnitudeComparator":
                comp = new TitleLastAndMagnitudeComparator();
                break;
            case "MagnitudeAndDepthComparator":
                comp = new MagnitudeAndDepthComparator();
                break;
        }
        allQuakes.sort(comp);
    }

    @Override
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeEntries, Filter filter){
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for(QuakeEntry quakeEntry : quakeEntries){
            if(filter.satisfies(quakeEntry)){
                answer.add(quakeEntry);
            }
        }
        return answer;
    }

    private int sortBySelectionSort(ArrayList<QuakeEntry> quakeEntries, String sortingParam, boolean isAsc, int passes){
        int numberOfSwaps = 0;
        int numberOfPass = 0;
        for(int i=0;i<quakeEntries.size();i++){
            int index = findQuakeIndex(quakeEntries, i, isAsc, sortingParam);
            if(index!=i) {
                numberOfSwaps++;
                numberOfPass = i+1;
                swapQuakeEntries(quakeEntries, i, index);
            }
            if(passes==numberOfPass){
                return numberOfPass;
            }
        }
        return numberOfPass;
    }

    private void swapQuakeEntries(ArrayList<QuakeEntry> quakeEntries, int index1, int index2){
        QuakeEntry index1Entry = quakeEntries.get(index1);
        QuakeEntry index2Entry = quakeEntries.get(index2);
        quakeEntries.set(index1,index2Entry);
        quakeEntries.set(index2, index1Entry);
    }

    private int sortByBubbleSort(ArrayList<QuakeEntry> quakeEntries, String sortingParam, boolean isAsc, int passes){
        int numberOfPass = 0;
        for(int i=0;i<quakeEntries.size();i++){
            boolean passNeeded = false;
            for(int j=0;j<(quakeEntries.size()-i-1);j++){
                if(isSwapNeeded(quakeEntries, j, j+1, isAsc, sortingParam)){
                    passNeeded = true;
                    swapQuakeEntries(quakeEntries, j, j+1);
                }
            }
            if(passNeeded){
                numberOfPass=i+1;
            }
        }
        return numberOfPass;
    }

    private boolean isSwapNeeded(ArrayList<QuakeEntry> quakeEntries, int currIndex, int nextIndex, boolean isAsc, String sortingParam){
        return (currIndex==getIndexAfterCompare(quakeEntries, currIndex, nextIndex, isAsc, sortingParam));
    }

    @Override
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }
    }
    @Override
    public ArrayList<Filter> getDepthMinMaxFilter(double minDepth, double maxDepth){
        ArrayList<Filter> filters = new ArrayList<>();
        filters.add(new MinDepthFilter(minDepth));
        filters.add(new MaxDepthFilter(maxDepth));
        return filters;
    }

    private ArrayList<Filter> getDistanceMinMaxFilter(double minDistance, double maxDistance, Location city){
        ArrayList<Filter> filters = new ArrayList<>();
        filters.add(new MinDistanceFilter(minDistance, city));
        filters.add(new MaxDistanceFilter(maxDistance, city));
        return filters;
    }
    @Override
    public ArrayList<Filter> getMagnitudeMinMaxFilter(double minMagnitude, double maxMagnitude){
        ArrayList<Filter> filters = new ArrayList<>();
        filters.add(new MinMagnitudeFilter(minMagnitude));
        filters.add(new MaxMagnitudeFilter(maxMagnitude));
        return filters;
    }

    private ArrayList<Filter> getMagnitudeMinMaxFilter_DepthMinMaxFilter(double minMagnitude, double maxMagnitude,
                                                                         double minDepth, double maxDepth){
        ArrayList<Filter> filters = getMagnitudeMinMaxFilter(minMagnitude, maxMagnitude);
        filters.addAll(getDepthMinMaxFilter(minDepth, maxDepth));
        return filters;
    }

    private ArrayList<Filter> getMagnitudeMinMaxFilter_DepthMinMaxFilter_FilterFromPattern(double minMagnitude, double maxMagnitude,
                                                                                           double minDepth, double maxDepth, String phrase,
                                                                                           EarthQuakeServiceImpl.PhrasePattern pattern){
        ArrayList<Filter> filters = getMagnitudeMinMaxFilter_DepthMinMaxFilter(minMagnitude, maxMagnitude, minDepth, maxDepth);
        filters.add(getFilterFromPattern(phrase, pattern));
        return filters;
    }

    private ArrayList<Filter> getMagnitudeMinMaxFilter_MaxDistanceFilter_FilterFromPattern(double maxDistance, Location city,double minMagnitude,
                                                                                           double maxMagnitude, String phrase,
                                                                                           EarthQuakeServiceImpl.PhrasePattern pattern){
        ArrayList<Filter> filters = getMagnitudeMinMaxFilter(minMagnitude, maxMagnitude);
        filters.add(getFilterFromPattern(phrase, pattern));
        filters.add(new MaxDistanceFilter(maxDistance, city));
        return filters;
    }

    private ArrayList<Filter> getMaxDistanceFilter_InACountry(double maxDistance, Location city, String country){
        ArrayList<Filter> filters = new ArrayList<>();
        filters.add(new MaxDistanceFilter(maxDistance, city));
        filters.add(new TitleEndAtPhraseFilter(country));
        return filters;
    }

    private Filter getFilterFromPattern(String phrase, EarthQuakeServiceImpl.PhrasePattern pattern) {
        switch (pattern){
            case START:
                return new TitleStartFromPhraseFilter(phrase);
            case END:
                return new TitleEndAtPhraseFilter(phrase);
            case ANY:
                return new TitleContainsPhraseFilter(phrase);
            default:
                return null;
        }
    }

    private ArrayList<QuakeEntry> getNClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<>();
        while(howMany>0 && copy.size()>0){
            int index = findClosestQuakeIndex(copy, current);
            ret.add(copy.get(index));
            copy.remove(index);
            howMany--;
        }
        return ret;
    }

    private ArrayList<QuakeEntry> getNLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<>();
        while(howMany>0 && copy.size()>0){
            int index = findLargestQuakeIndex(copy);
            ret.add(copy.get(index));
            copy.remove(index);
            howMany--;
        }
        return ret;
    }

    private int findLargestQuakeIndex(ArrayList<QuakeEntry> quakeData) {
        int index = 0;
        for(int k=1; k < quakeData.size(); k++){
            if (quakeData.get(k).getMagnitude() > quakeData.get(index).getMagnitude()){
                index = k;
            }
        }
        return index;
    }

    private int findQuakeIndex(ArrayList<QuakeEntry> quakeData, int startingIndex, boolean isAsc, String param) {
        int index = startingIndex;
        for(int k=startingIndex+1; k < quakeData.size(); k++){
            index = getIndexAfterCompare(quakeData, k, index, isAsc, param);
        }
        return index;
    }

    private int getIndexAfterCompare(ArrayList<QuakeEntry> quakeData, int currentIndex, int index, boolean isAsc,  String param){
        if(compareQuakes(quakeData, currentIndex, index, isAsc, param)){
            index = currentIndex;
        }
        return index;
    }

    private boolean compareQuakes(ArrayList<QuakeEntry> quakeData, int currentIndex, int index, boolean isAsc, String param){
        ArrayList<Double> valuesForComparison = valuesForComparison(quakeData, currentIndex, index, param);
        if(isAsc) {
            return (valuesForComparison.get(0) < valuesForComparison.get(1));
        } else {
            return (valuesForComparison.get(0) > valuesForComparison.get(1));
        }
    }

    private ArrayList<Double> valuesForComparison(ArrayList<QuakeEntry> quakeData, int currentIndex, int index, String param){
        ArrayList<Double> valuesForComparison = new ArrayList<>();
        if(param.equals("magnitude")){
            valuesForComparison.add(quakeData.get(currentIndex).getMagnitude());
            valuesForComparison.add(quakeData.get(index).getMagnitude());
        } else {
            valuesForComparison.add(quakeData.get(currentIndex).getDepth());
            valuesForComparison.add(quakeData.get(index).getDepth());
        }
        return valuesForComparison;
    }

    private int findClosestQuakeIndex(ArrayList<QuakeEntry> quakeData, Location current){
        int minIndex = 0;
        for(int k=1; k < quakeData.size(); k++){
            if (quakeData.get(k).getLocation().distanceTo(current) < quakeData.get(minIndex).getLocation().distanceTo(current)){
                minIndex = k;
            }
        }
        return minIndex;
    }

}
