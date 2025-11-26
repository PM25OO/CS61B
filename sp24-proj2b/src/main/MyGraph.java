package main;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MyGraph {

    private final TreeMap<Integer, String> wordList;
    private final TreeMap<Integer, ArrayList<Integer>> graph;

    public MyGraph() {
        wordList = new TreeMap<>();
        graph = new TreeMap<>();
    }

    public void addWord(int k, String v) {
        wordList.put(k, v);
    }

    public void addEdge(int a, List<Integer> b) {
        if (!graph.containsKey(a)) graph.put(a, new ArrayList<>());
        graph.get(a).addAll(b);
    }

}
