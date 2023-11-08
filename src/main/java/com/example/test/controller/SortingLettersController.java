package com.example.test.controller;


import com.example.test.service.SortingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sort/string")
@RequiredArgsConstructor
public class SortingLettersController {

    private final SortingService sortingService;

    @GetMapping("/{letters}")
    public String sortIncomingLetters(@PathVariable String letters) {
        return sortingService.sortByLetters(letters);
    }
}