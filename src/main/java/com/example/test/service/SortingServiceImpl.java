package com.example.test.service;

import com.example.test.util.CustomLinkedHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SortingServiceImpl implements SortingService {

    @Override
    public String sortByLetters(String inputLetters) {
        if (inputLetters == null || inputLetters.isBlank()) {
            throw new RuntimeException("Cannot use an empty String " +
                    "or null value for the method sortByLetters.");
        }

        List<String> lettersByOne = new ArrayList<>(Arrays.asList(inputLetters.toLowerCase()
                .replaceAll("\\s", "").split("")));

        Map<String, Integer> groupedLetters = new HashMap<>();

        for (String s : lettersByOne) {
            if (groupedLetters.containsKey(s)) {
                int counter = groupedLetters.get(s) + 1;
                groupedLetters.put(s, counter);
            } else {
                groupedLetters.put(s, 1);
            }
        }

        CustomLinkedHashMap customLinkedHashMap = new CustomLinkedHashMap();

        groupedLetters.entrySet()
                .stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach((i) -> customLinkedHashMap.sortedLetters.put(i.getKey(), i.getValue()));

        log.info("Letters are sorted successfully from {} to {}", inputLetters, groupedLetters);
        return customLinkedHashMap.sortedLetters.toString();
    }
}
