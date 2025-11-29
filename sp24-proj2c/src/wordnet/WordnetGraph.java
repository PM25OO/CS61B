package wordnet;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
            // 创建单词的索引
            List<String> words = new ArrayList<>(Arrays.asList(nextLineWords[1].split(" ")));
            wordNetGraph.addWord(number, words);
        }
        while (HYPONYMS.hasNextLine()) {
            String[] nexLineNodes = HYPONYMS.readLine().split(",");
            int number = Integer.parseInt(nexLineNodes[0]);
            // 对所有标号生成节点
            for (String nexLineNode : nexLineNodes) wordNetGraph.addNode(Integer.parseInt(nexLineNode));
            // 构建图的邻接矩阵实现
            for (int i = 1; i < nexLineNodes.length; i ++) wordNetGraph.addEdge(number, Integer.parseInt(nexLineNodes[i]));
        }
    }

    /**
    搜索给定单词的下义词集合
     **/
    public Set<String> hyponymList(String word) {
        return wordNetGraph.searchHyponym(word);
    }
}
