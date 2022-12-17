package org.example.controller.SortingAlgorithms.ImplementingSelectionSort.PracticeQuiz;

import org.example.DAO.QuakeEntry;
import org.example.service.intf.EarthQuakeService;
import org.example.util.intf.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/SA/ISS/PQ/Sample2/")
public class SA_ISS_PQ_QuakeControllerSample2 {

    @Qualifier("SA_ISS_PQ_Dec6_Sample2")
    @Autowired
    private EarthQuakeService earthQuakeService;

    @Autowired
    private Paging paging;

    @GetMapping("")
    String home() {
        return "Hello Folks Welcome to Sorting and Algorithms Implementing Selection Sort Practice Quiz Sample 2 Answers";
    }

    @GetMapping("All")
    ArrayList<QuakeEntry> getAllQuakes() {
        return earthQuakeService.getAllQuakes();
    }

    @GetMapping("PagedSorted")
    Page<QuakeEntry> getAllQuakesSorted(@RequestParam("sortingAlgo") String sortingAlgo, @RequestParam("sortingParam") String sortingParam,
                                        @RequestParam("sort") String direction, @RequestParam("passes") int passes, Pageable pageable) {
        return earthQuakeService.getQuakesPagedAndSorted(sortingAlgo, sortingParam, direction.equals("ASC"), passes, pageable);
    }

    @GetMapping("PagedSorted/Comparator")
    Page<QuakeEntry> getAllQuakesSortedByComparator(@RequestParam("comparator") String comparator, @RequestParam("sort") String direction, Pageable pageable) {
        return earthQuakeService.getQuakesPagedAndSortedWithComparator(comparator,direction.equals("ASC"), pageable);
    }

    @GetMapping("AllPage")
    Page<QuakeEntry> getAllQuakesInPages(Pageable pageable) {
        return paging.getDataInPages(earthQuakeService.getAllQuakes(), pageable);
    }

    @GetMapping("Bigger/{minMagnitude}")
    ArrayList<QuakeEntry> getAllBigQuakes(@PathVariable("minMagnitude") double minMagnitude) {
        return earthQuakeService.getAllBigQuakes(minMagnitude);
    }

    @GetMapping("closeToMe/{distance}")
    ArrayList<QuakeEntry> getQuakesCloseToMe(@PathVariable("distance") double distance, @RequestParam("latitude") double latitude,
                                             @RequestParam("longitude") double longitude) {
        return earthQuakeService.getQuakesCloseToMe(latitude, longitude, distance);
    }

    @GetMapping("closeToMe/Country/{distance}")
    ArrayList<QuakeEntry> getQuakesCloseToMeInCountry(@PathVariable("distance") double distance, @RequestParam("latitude") double latitude,
                                             @RequestParam("longitude") double longitude, @RequestParam("country") String country) {
        return earthQuakeService.getQuakesCloseToMeInACountry(latitude, longitude, distance, country);
    }

    @GetMapping("closeToMe/Magnitude/Phrase/{phasePattern}/{distance}")
    ArrayList<QuakeEntry> getQuakesCloseToMeWithMagnitudeAndPhrase(@PathVariable("distance") double distance, @RequestParam("latitude") double latitude,
                                             @RequestParam("longitude") double longitude,@RequestParam("minMagnitude") double minMagnitude,
                                                                   @RequestParam("maxMagnitude") double maxMagnitude, @PathVariable("phasePattern") String phasePattern,
                                                                   String phrase) {
        return earthQuakeService.getQuakesCloseToMeWithMagnitudeAndPhrase(latitude, longitude, distance, minMagnitude,
                maxMagnitude, phasePattern, phrase);
    }

    @GetMapping(value = "depth")
    ArrayList<QuakeEntry> getQuakesOfDepth( @RequestParam("minDepth") double minDepth, @RequestParam("maxDepth") double maxDepth) {
        return earthQuakeService.getQuakesByDepth(minDepth, maxDepth);
    }

    @GetMapping(value = "depthMagnitude")
    ArrayList<QuakeEntry> getQuakesOfDepthAndMagnitude( @RequestParam("minDepth") double minDepth, @RequestParam("maxDepth") double maxDepth,
                                                        @RequestParam("minMagnitude") double minMagnitude, @RequestParam("maxMagnitude") double maxMagnitude) {
        return earthQuakeService.getQuakesByDepthAndMagnitude(minDepth, maxDepth, minMagnitude, maxMagnitude);
    }

    @GetMapping(value = "depthMagnitude/Phrase/{phasePattern}")
    ArrayList<QuakeEntry> getQuakesByDepthMagnitudeAndPhrase( @RequestParam("minDepth") double minDepth, @RequestParam("maxDepth") double maxDepth,
                                                        @RequestParam("minMagnitude") double minMagnitude, @RequestParam("maxMagnitude") double maxMagnitude,
                                                        @PathVariable("phasePattern") String phasePattern, @RequestParam("phrase") String phrase) {
        return earthQuakeService.getQuakesByDepthMagnitudeAndPhrase(minDepth, maxDepth, minMagnitude, maxMagnitude, phrase, phasePattern);
    }


    @GetMapping("phrase/{phasePattern}")
    ArrayList<QuakeEntry> getQuakesOfPhrase( @PathVariable("phasePattern") String phasePattern, @RequestParam("phrase") String phrase){
        return earthQuakeService.getQuakesByPhrase(phasePattern, phrase);
    }

    @GetMapping("NClosestToMe/{howMany}")
    ArrayList<QuakeEntry> getNClosestQuakes( @PathVariable("howMany") int howMany,@RequestParam("latitude") double latitude,
                                             @RequestParam("longitude") double longitude){
        return earthQuakeService.getNClosestQuakesFromMe(latitude, longitude, howMany);
    }

    @GetMapping("largest")
    ArrayList<QuakeEntry> getNClosestQuakes(){
        return earthQuakeService.getNLargest(1);
    }

    @GetMapping("NLargest/{howMany}")
    ArrayList<QuakeEntry> getNClosestQuakes( @PathVariable("howMany") int howMany){
        return earthQuakeService.getNLargest(howMany);
    }
}
