package main;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordnetGraph {

    private final In SYNSETS;
    private final In HYPONYMS;
    private final MyGraph wordNetGraph;

    public WordnetGraph(String synsets, String hyponyms) {
        wordNetGraph = new MyGraph();
        SYNSETS = new In(synsets);
        HYPONYMS = new In(hyponyms);
        handleFiles();
    }

    private void handleFiles() {
        while (SYNSETS.hasNextLine()) {
            String[] nextLineWords = SYNSETS.readLine().split(",");
            int number = Integer.parseInt(nextLineWords[0]);
            List<String> words = new ArrayList<>(Arrays.asList(nextLineWords[1].split(" ")));
            wordNetGraph.addWord(number, words);
        }
        while (HYPONYMS.hasNextLine()) {
            String[] nexLineNodes = HYPONYMS.readLine().split(",");
            int number = Integer.parseInt(nexLineNodes[0]);
            for (int i = 1; i < nexLineNodes.length; i ++) wordNetGraph.addEdge(number, Integer.parseInt(nexLineNodes[i]));
        }
    }
}
