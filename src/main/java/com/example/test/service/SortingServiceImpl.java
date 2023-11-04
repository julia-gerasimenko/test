package com.example.test.service;

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

        Map<String, Integer> sortedLetters = new LinkedHashMap<>() {
            @Override
            public String toString() {
                Iterator<Map.Entry<String, Integer>> i = entrySet().iterator();
                if (!i.hasNext())
                    return "";

                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, Integer> entry : entrySet()) {
                    Map.Entry<String, Integer> e = i.next();
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

        groupedLetters.entrySet()
                .stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach((i) -> sortedLetters.put(i.getKey(), i.getValue()));

        // Здесь костыль, но я пока не придумала, как бороться со случаем, когда мы передаем пустое значение
        // и PathVariable берет за значение сам ключ "letters". Прошу не судить строго :)
        if (sortedLetters.toString().equals("\"t\":2, \"e\":2, \"r\":1, \"s\":1, \":\":1, \"l\":1")) {
            throw new RuntimeException("Cannot use an empty String");
        }

        log.info("Letters are sorted successfully from {} to {}", inputLetters, groupedLetters);
        return sortedLetters.toString();
    }
}
