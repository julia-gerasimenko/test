package com.example.test.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomLinkedHashMap {

    public Map<String, Integer> sortedLetters = new LinkedHashMap<>() {
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
}
