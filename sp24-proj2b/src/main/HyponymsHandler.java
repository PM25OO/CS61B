package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class HyponymsHandler extends NgordnetQueryHandler {

    private final WordnetGraph wordnetGraph;

    public HyponymsHandler(WordnetGraph wordnetGraph) {
        this.wordnetGraph = wordnetGraph;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        Set<String> retainSet = wordnetGraph.hyponymList(words.get(0));
        String response = "";

        for (String word : words) {
            Set<String> set = wordnetGraph.hyponymList(word);
            retainSet.retainAll(set);
        }

        return response + retainSet.toString();
    }
}
