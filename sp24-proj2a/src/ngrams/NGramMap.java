package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 *
 * 一个对象，用于提供对 Google NGrams 数据集（或其子集）进行查询的各种实用方法。
 *
 * NGramMap 存储来自“单词文件”和“计数文件”的相关数据。它并不是严格意义上的映射，
 * 但它提供了一些额外的功能。
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    // TODO：添加所有必要的静态变量或实例变量。
    private final In WORDSFILE;
    private final In COUNTSFILE;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     *
     * 使用 WORDSFILENAME 和 COUNTSFILENAME 构造一个 NGramMap。
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        WORDSFILE = new In(wordsFilename);
        COUNTSFILE = new In(countsFilename);
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        // TODO：实现该构造函数。可参考规范中的“NGramMap 提示”部分获取帮助。
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     *
     * 提供指定 WORD 在 STARTYEAR 到 ENDYEAR（包含这两个端点之间所有年份）的历史数据。
     * 返回的 TimeSeries 应该是一个副本，而不是指向此 NGramMap 内部 TimeSeries 的引用。
     * 换句话说，对此方法返回对象所做的任何修改不应影响到 NGramMap 本身。这种做法也称为
     * “防御性拷贝”。如果该单词不在数据文件中，则返回一个空的 TimeSeries。
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        // TODO：实现该方法。
        return null;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     *
     * 提供指定 WORD 的完整历史数据。返回的 TimeSeries 应该是一个副本，而不是指向此
     * NGramMap 内部 TimeSeries 的引用。换句话说，对此方法返回对象所做的任何修改不应
     * 影响到 NGramMap 本身。这种做法也称为“防御性拷贝”。如果该单词不在数据文件中，
     * 则返回一个空的 TimeSeries。
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        // TODO：实现该方法。
        return null;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     *
     * 返回一个关于所有卷中每年记录的单词总数的 TimeSeries 的防御性拷贝。
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        // TODO：实现该方法。
        return null;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     *
     * 提供一个 TimeSeries，包含指定 WORD 在 STARTYEAR 到 ENDYEAR（包含两端）之间每年的
     * 相对频率。如果该单词不在数据文件中，则返回一个空的 TimeSeries。
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        // TODO：实现该方法。
        return null;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     *
     * 提供一个 TimeSeries，包含指定 WORD 在每一年相对于该年所有被记录单词的相对频率。
     * 如果该单词不在数据文件中，则返回一个空的 TimeSeries。
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        // TODO：实现该方法。
        return null;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     *
     * 提供一个 TimeSeries，表示集合 WORDS 中所有单词在 STARTYEAR 到 ENDYEAR（包含两端）
     * 之间每年的相对频率之和。如果某个单词在这个时间范围内不存在，则忽略它，而不是抛出
     * 异常。
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        // TODO：实现该方法。
        return null;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     *
     * 返回一个 TimeSeries，表示集合 WORDS 中所有单词在每一年中的相对频率之和。如果某个
     * 单词在对应年份的时间范围内不存在，则忽略它，而不是抛出异常。
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        // TODO：实现该方法。
        return null;
    }

    // TODO: Add any private helper methods.
    // TODO：添加所有需要的私有辅助方法。

    // TODO: Remove all TODO comments before submitting.
    // TODO：提交前删除所有 TODO 注释。
}
