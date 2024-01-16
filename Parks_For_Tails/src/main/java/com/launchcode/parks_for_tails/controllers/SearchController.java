package com.launchcode.parks_for_tails.controllers;

import com.launchcode.parks_for_tails.models.Search;
import com.launchcode.parks_for_tails.data.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {
    @Autowired
    private SearchRepository searchRepository;

    //    @GetMapping("/search")
//    public String search(@RequestParam("keyword") String keyword, Model model){
//        // perform the search using the parklistservice
//        List<ParksList> searchResult = parksListService.searchParksList(keyword);
//        // Add the search results to the model
//        model.addAttribute("searchResults",searchresults);
//        return "search";
//    }
//
//
// }
    @GetMapping("/search")
    public List<Search> getAllSearch(){
        return searchRepository.findAll();
    }
}
