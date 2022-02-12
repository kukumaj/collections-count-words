package com.epam.rd.autotasks;


import java.util.*;

public class Words {

    public String countWords(List<String> lines) {
        Map<String, Integer> map = new HashMap<>();


        for (String line : lines) {

            String[] arr = line.toLowerCase().replaceAll("[^0-9a-zA-Z\u0430-\u044F\u0410-\u042F\u0100-\u017F\u0180-\u024F\u0080-\u00FF]", " ").trim().split("\\s");


            for (String word : arr) {
                if (!map.containsKey(word) && word.length() >= 4) {
                    map.put(word, 0);
                }
                if (word.length() >= 4) {
                    map.put(word, map.get(word) + 1);
                }
            }
        }


        List<WordsStats> list = new ArrayList<>(map.size());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            list.add(new WordsStats(entry.getKey(), entry.getValue()));
        }

        list.sort(new Comparator<>() {
            @Override
            public int compare(WordsStats o1, WordsStats o2) {
                if (o1.getOccurrences() > o2.getOccurrences()) {
                    return -1;
                } else if (o1.getOccurrences() < o2.getOccurrences()) {
                    return 1;
                } else {
                    return o1.getWord().compareTo(o2.getWord());
                }
            }
        });


        StringBuilder result = new StringBuilder();

        for (WordsStats wordsStats : list) {
            if (wordsStats.getOccurrences() >= 10) {
                result.append(wordsStats.getWord()).append(" - ").append(wordsStats.getOccurrences()).append("\n");
            }
        }

        return result.toString().trim();
    }

    private static class WordsStats {
        private final String word;
        private final int occurrences;

        public WordsStats(String word, int occurrences) {
            this.word = word;
            this.occurrences = occurrences;
        }

        public String getWord() {
            return word;
        }

        public int getOccurrences() {
            return occurrences;
        }
    }
}
