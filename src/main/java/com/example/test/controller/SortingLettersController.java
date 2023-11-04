package com.example.test.controller;


import com.example.test.service.SortingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/sort/string")
@RequiredArgsConstructor
public class SortingLettersController {
    @Autowired
    SortingService sortingService;

    @GetMapping ("/{letters}")
    public Map<String, Integer> sortIncomingLetters(@PathVariable(value = "letters") String letters) {
        return sortingService.sortByLetters(letters);
    }
}
