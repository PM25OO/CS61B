package main;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MyGraph {

    static class AdjList {
        private final List<Integer> neighbors = new ArrayList<>();
        public List<Integer> getNeighbors() {
            return neighbors;
        }
    }

    static class WordList {
        private final List<String> words = new ArrayList<>();
        public List<String> getWords() {
            return words;
        }
    }

    private final TreeMap<Integer, WordList> wordList;
    private final TreeMap<Integer, AdjList> graph;

    public MyGraph() {
        wordList = new TreeMap<>();
        graph = new TreeMap<>();
    }

    public void addWord(int k, List<String> v) {
        wordList.computeIfAbsent(k, f -> new WordList()).getWords().addAll(v);
    }

    public void addEdge(int a, List<Integer> b) {
        graph.computeIfAbsent(a, k -> new AdjList()).getNeighbors().addAll(b);
    }

    public void addEdge(int a, int b) {
        graph.computeIfAbsent(a, k -> new AdjList()).getNeighbors().add(b);
    }
}
