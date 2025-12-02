package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import wordnet.WordnetGraph;

import java.util.*;
import java.util.stream.Collectors;

public class HyponymsHandler extends NgordnetQueryHandler {

    private final WordnetGraph wdg;
    private final NGramMap ngm;

    public HyponymsHandler(WordnetGraph wdg, NGramMap ngm) {
        this.wdg = wdg;
        this.ngm = ngm;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();

        Set<String> retainSet = wdg.hyponymList(words.get(0));
        Map<Double, List<String>> popularList = new TreeMap<>();
        String response = "";

        for (String word : words) {
            Set<String> set = wdg.hyponymList(word);
            retainSet.retainAll(set);
        }

        for (String word : retainSet) {
            TimeSeries ts = ngm.countHistory(word, startYear, endYear);
            double count = ts.values().stream().mapToDouble(Double::doubleValue).sum();
            popularList.computeIfAbsent(count, s -> new ArrayList<String>()).add(word);
        }

        List<String> result = popularList.entrySet().stream()
                .sorted(Map.Entry.<Double, List<String>>comparingByKey().reversed())
                .flatMap(entry -> entry.getValue().stream())
                .limit(k)
                .toList();


        return response + new TreeSet<String>(result);
    }
}
