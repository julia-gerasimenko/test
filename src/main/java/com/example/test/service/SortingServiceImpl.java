package com.example.test.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SortingServiceImpl implements SortingService {

    @Override
    public Map<String, Integer> sortByLetters(String letters) {
        if (letters == null || letters.isBlank()) {
            throw new RuntimeException("Cannot use an empty String " +
                    "or null value for the method sortByLetters.");
        }

        List<String> lettersByOne = new ArrayList<>(Arrays.asList(letters.toLowerCase()
                .replaceAll("\\s", "").split("")));

        Map<String, Integer> groupedLetters = new HashMap<>() {
            @Override
            public String toString() {
                Iterator<Entry<String, Integer>> i = entrySet().iterator();
                if (!i.hasNext())
                    return "";

                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, Integer> entry : entrySet()) {
                    Entry<String, Integer> e = i.next();
                    String key = e.getKey();
                    Integer value = e.getValue();
                    sb.append("\"").append(key).append("\"");
                    sb.append(':');
                    sb.append(value);
                    if (i.hasNext()) {
                        sb.append(", ");
                    }
                }
                return sb.toString();
            }
        };

        for (String s : lettersByOne) {
            if (groupedLetters.containsKey(s)) {
                int counter = groupedLetters.get(s) + 1;
                groupedLetters.put(s, counter);
            } else {
                groupedLetters.put(s, 1);
            }
        }

        Map<String, Integer> sortedLetters = new LinkedHashMap<>();
        groupedLetters.entrySet()
                .stream().sorted(Map.Entry.comparingByValue())
                .forEach((i) -> sortedLetters.put(i.getKey(), i.getValue()));

        log.info("Letters are sorted successfully from {} to {}", letters, groupedLetters);
        return sortedLetters;
    }
}
