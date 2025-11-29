package ngrams;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super(ts.subMap(startYear, true, endYear, true));
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        return new ArrayList<>(keySet());
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        return new ArrayList<>(values());
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     *
     * 返回此 TimeSeries 与给定 TimeSeries 按年份的逐年相加结果。换言之，对于每个年份，
     * 将此 TimeSeries 的数据与给定 TimeSeries 中对应年份的数据相加。应当返回一个新的
     * TimeSeries（不修改当前对象）。
     *
     * 如果两个 TimeSeries 都不包含任何年份，则返回一个空的 TimeSeries。
     * 如果某个年份只存在于其中一个 TimeSeries，则返回的 TimeSeries 在该年份应保存存在
     * 的那个 TimeSeries 的值。
     */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries t = (TimeSeries) this.clone();
        for (Integer i : ts.keySet()) {
            if (t.containsKey(i)) {
                t.replace(i, t.get(i) + ts.get(i));
            } else {
                t.put(i, ts.get(i));
            }
        }
        return t;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     *
     * 返回一个新的 TimeSeries，其中每个年份的值等于当前 TimeSeries 在该年份的值
     * 除以参数 TS 在同一年份的值。（不修改当前 TimeSeries）
     *
     * 如果 TS 缺少当前 TimeSeries 中存在的某一年份，则抛出 IllegalArgumentException。
     * 如果 TS 包含当前 TimeSeries 中不存在的年份，则忽略该年份。
     */
    public TimeSeries dividedBy(TimeSeries ts) throws IllegalArgumentException{
        TimeSeries result = new TimeSeries();
        for (Integer year : this.keySet()) {
            if (!ts.containsKey(year)) {
                throw new IllegalArgumentException("Missing year: " + year);
            }
            result.put(year, this.get(year) / ts.get(year));
        }
        return result;
    }
}
