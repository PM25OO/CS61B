package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.HashMap;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 * <p>
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 * <p>
 * 一个对象，用于提供对 Google NGrams 数据集（或其子集）进行查询的各种实用方法。
 * <p>
 * NGramMap 存储来自“单词文件”和“计数文件”的相关数据。它并不是严格意义上的映射，
 * 但它提供了一些额外的功能。
 */
public class NGramMap {

    private final In WORDSFILE;
    private final In COUNTFILE;
    private final HashMap<String, TimeSeries> nGramMap = new HashMap<>();
    private final TimeSeries countsSeries = new TimeSeries();

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     * <p>
     * 使用 WORDSFILENAME 和 COUNTSFILENAME 构造一个 NGramMap。
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        WORDSFILE = new In(wordsFilename);
        COUNTFILE = new In(countsFilename);
        handleFile();
    }
    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     * <p>
     * 提供指定 WORD 在 STARTYEAR 到 ENDYEAR（包含这两个端点之间所有年份）的历史数据。
     * 返回的 TimeSeries 应该是一个副本，而不是指向此 NGramMap 内部 TimeSeries 的引用。
     * 换句话说，对此方法返回对象所做的任何修改不应影响到 NGramMap 本身。这种做法也称为
     * “防御性拷贝”。如果该单词不在数据文件中，则返回一个空的 TimeSeries。
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        if (!nGramMap.containsKey(word)) return new TimeSeries();
        return new TimeSeries(nGramMap.get(word), startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     * <p>
     * 提供指定 WORD 的完整历史数据。返回的 TimeSeries 应该是一个副本，而不是指向此
     * NGramMap 内部 TimeSeries 的引用。换句话说，对此方法返回对象所做的任何修改不应
     * 影响到 NGramMap 本身。这种做法也称为“防御性拷贝”。如果该单词不在数据文件中，
     * 则返回一个空的 TimeSeries。
     */
    public TimeSeries countHistory(String word) {
        TimeSeries t = new TimeSeries();
        if (!nGramMap.containsKey(word)) return new TimeSeries();
        TimeSeries originTS = nGramMap.get(word);
        for (Integer year : originTS.keySet()) {
            t.put(year, originTS.get(year));
        }
        return t;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     * <p>
     * 返回一个关于所有卷中每年记录的单词总数的 TimeSeries 的防御性拷贝。
     */
    public TimeSeries totalCountHistory() {
        TimeSeries t = new TimeSeries();
        for (Integer year : countsSeries.keySet()) {
            t.put(year, countsSeries.get(year));
        }
        return t;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     * <p>
     * 提供一个 TimeSeries，包含指定 WORD 在 STARTYEAR 到 ENDYEAR（包含两端）之间每年的
     * 相对频率。如果该单词不在数据文件中，则返回一个空的 TimeSeries。
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries t = new TimeSeries();
        if (!nGramMap.containsKey(word)) return t;
        TimeSeries originTS = countHistory(word, startYear, endYear);
        for (Integer year : originTS.keySet()) {
            t.put(year, originTS.get(year) / countsSeries.get(year));
        }
        return t;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     * <p>
     * 提供一个 TimeSeries，包含指定 WORD 在每一年相对于该年所有被记录单词的相对频率。
     * 如果该单词不在数据文件中，则返回一个空的 TimeSeries。
     */
    public TimeSeries weightHistory(String word) {
        TimeSeries t = new TimeSeries();
        if (!nGramMap.containsKey(word)) return t;
        TimeSeries originTS = nGramMap.get(word);
        for (Integer year : originTS.keySet()) {
            t.put(year, originTS.get(year) / countsSeries.get(year));
        }
        return t;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     * <p>
     * 提供一个 TimeSeries，表示集合 WORDS 中所有单词在 STARTYEAR 到 ENDYEAR（包含两端）
     * 之间每年的相对频率之和。如果某个单词在这个时间范围内不存在，则忽略它，而不是抛出
     * 异常。
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries t = new TimeSeries();
        for (String word : words) {
            if (!nGramMap.containsKey(word)) {
                continue;
            }
            TimeSeries tempTS = weightHistory(word, startYear, endYear);
            t = t.plus(tempTS);
        }
        return t;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     * <p>
     * 返回一个 TimeSeries，表示集合 WORDS 中所有单词在每一年中的相对频率之和。如果某个
     * 单词在对应年份的时间范围内不存在，则忽略它，而不是抛出异常。
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries t = new TimeSeries();
        for (String word : words) {
            if (!nGramMap.containsKey(word)) {
                continue;
            }
            TimeSeries tempTS = weightHistory(word);
            t = t.plus(tempTS);
        }
        return t;
    }

    private void handleFile() {
        String currentWord = "";
        while (WORDSFILE.hasNextLine()) {
            String[] nextLineWords = WORDSFILE.readLine().split("\t");
            String thisWord = nextLineWords[0];
            Integer year = Integer.parseInt(nextLineWords[1]);
            Double times = Double.parseDouble(nextLineWords[2]);
            if (!currentWord.equals(thisWord)) {
                nGramMap.put(thisWord, new TimeSeries());
                currentWord = thisWord;
            }
            nGramMap.get(thisWord).put(year, times);
        }
        while (COUNTFILE.hasNextLine()) {
            String[] nextLineWords = COUNTFILE.readLine().split(",");
            Integer year = Integer.parseInt(nextLineWords[0]);
            Double times = Double.parseDouble(nextLineWords[1]);
            countsSeries.put(year, times);
        }
    }
}
