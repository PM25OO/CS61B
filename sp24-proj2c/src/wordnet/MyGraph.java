package wordnet;

import java.util.*;

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

    public void addNode(int a) {
        graph.computeIfAbsent(a, k -> new AdjList());
    }

    public void addEdge(int a, int b) {
        graph.computeIfAbsent(a, k -> new AdjList()).getNeighbors().add(b);
    }

    public TreeSet<String> searchHyponym(String word) {
        List<Integer> index = new ArrayList<>();
        for (Integer i : wordList.keySet()) {
            if (wordList.get(i).getWords().contains(word)) index.add(i);
        }
        return searchHelper(index);
    }

    private TreeSet<String> searchHelper(List<Integer> list) {
        TreeSet<String> stringSet = new TreeSet<>();
        if (!list.isEmpty()) {
            for (Integer i : list) {
                stringSet.addAll(wordList.get(i).getWords());
                stringSet.addAll(searchHelper(graph.get(i).getNeighbors()));
            }
        }
        return stringSet;
    }
}
