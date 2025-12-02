package main;

import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import wordnet.WordnetGraph;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymsHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {

        WordnetGraph graph = new WordnetGraph(synsetFile, hyponymFile);
        NGramMap ngm = new NGramMap(wordFile, countFile);

        return new HyponymsHandler(graph, ngm);
    }
}
