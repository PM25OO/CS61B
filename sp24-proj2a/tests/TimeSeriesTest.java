import ngrams.TimeSeries;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TimeSeriesTest {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1991, 1992, 1994, 1995));

        assertThat(totalPopulation.years()).isEqualTo(expectedYears);

        List<Double> expectedTotal = new ArrayList<>
                (Arrays.asList(0.0, 100.0, 600.0, 500.0));

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedTotal.get(i));
        }
    }

    @Test
    public void testEmptyBasic() {
        TimeSeries catPopulation = new TimeSeries();
        TimeSeries dogPopulation = new TimeSeries();

        assertThat(catPopulation.years()).isEmpty();
        assertThat(catPopulation.data()).isEmpty();

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);

        assertThat(totalPopulation.years()).isEmpty();
        assertThat(totalPopulation.data()).isEmpty();
    }

    @Test
    public void testYearsDataOrderConsistency() {
        TimeSeries ts = new TimeSeries();
        ts.put(2001, 1.0);
        ts.put(1999, 2.0);
        ts.put(2000, 3.0);
        // TreeMap 应按年份排序
        assertThat(ts.years()).isEqualTo(Arrays.asList(1999, 2000, 2001));
        assertThat(ts.data()).isEqualTo(Arrays.asList(2.0, 3.0, 1.0));
    }

    @Test
    public void testCopyConstructorRange() {
        TimeSeries ts = new TimeSeries();
        ts.put(1998, 1.0);
        ts.put(1999, 2.0);
        ts.put(2000, 3.0);
        ts.put(2001, 4.0);
        TimeSeries sub = new TimeSeries(ts, 1999, 2000);
        assertThat(sub.years()).isEqualTo(Arrays.asList(1999, 2000));
        assertThat(sub.data()).isEqualTo(Arrays.asList(2.0, 3.0));
    }

    @Test
    public void testPlusDisjoint() {
        TimeSeries a = new TimeSeries();
        a.put(2000, 10.0);
        TimeSeries b = new TimeSeries();
        b.put(2001, 20.0);
        TimeSeries c = a.plus(b);
        assertThat(c.years()).isEqualTo(Arrays.asList(2000, 2001));
        assertThat(c.data()).isEqualTo(Arrays.asList(10.0, 20.0));
        // 原对象不变
        assertThat(a.years()).isEqualTo(Arrays.asList(2000));
        assertThat(b.years()).isEqualTo(Arrays.asList(2001));
    }

    @Test
    public void testPlusOverlap() {
        TimeSeries a = new TimeSeries();
        a.put(2000, 1.0);
        a.put(2001, 2.0);
        TimeSeries b = new TimeSeries();
        b.put(2001, 5.0);
        b.put(2002, 7.0);
        TimeSeries c = a.plus(b);
        assertThat(c.years()).isEqualTo(Arrays.asList(2000, 2001, 2002));
        assertThat(c.data()).isEqualTo(Arrays.asList(1.0, 7.0, 7.0));
    }

    @Test
    public void testPlusImmutability() {
        TimeSeries a = new TimeSeries();
        a.put(2000, 3.0);
        TimeSeries b = new TimeSeries();
        b.put(2000, 4.0);
        TimeSeries c = a.plus(b);
        assertThat(a.get(2000)).isEqualTo(3.0);
        assertThat(b.get(2000)).isEqualTo(4.0);
        assertThat(c.get(2000)).isEqualTo(7.0);
    }

    @Test
    public void testDividedByBasic() {
        TimeSeries a = new TimeSeries();
        a.put(2000, 10.0);
        a.put(2001, 20.0);
        TimeSeries b = new TimeSeries();
        b.put(2000, 2.0);
        b.put(2001, 4.0);
        b.put(2002, 100.0); // 多余年份应被忽略
        TimeSeries c = a.dividedBy(b);
        assertThat(c.years()).isEqualTo(Arrays.asList(2000, 2001));
        assertThat(c.data()).isEqualTo(Arrays.asList(5.0, 5.0));
    }

    @Test
    public void testDividedByIgnoreExtraYear() {
        TimeSeries a = new TimeSeries();
        a.put(1999, 9.0);
        TimeSeries b = new TimeSeries();
        b.put(1999, 3.0);
        b.put(2000, 100.0); // 不在 a 中，忽略
        TimeSeries c = a.dividedBy(b);
        assertThat(c.years()).isEqualTo(Arrays.asList(1999));
        assertThat(c.data()).isEqualTo(Arrays.asList(3.0));
    }

    @Test
    public void testDividedByMissingYearThrows() {
        TimeSeries a = new TimeSeries();
        a.put(2000, 5.0);
        TimeSeries b = new TimeSeries(); // 缺少 2000
        assertThrows(IllegalArgumentException.class, () -> a.dividedBy(b));
    }

    @Test
    public void testDividedByImmutability() {
        TimeSeries a = new TimeSeries();
        a.put(2000, 8.0);
        TimeSeries b = new TimeSeries();
        b.put(2000, 2.0);
        TimeSeries c = a.dividedBy(b);
        assertThat(a.get(2000)).isEqualTo(8.0);
        assertThat(b.get(2000)).isEqualTo(2.0);
        assertThat(c.get(2000)).isEqualTo(4.0);
    }

    @Test
    public void testEmptyDividedByThrows() {
        TimeSeries a = new TimeSeries();
        a.put(2000, 1.0);
        TimeSeries b = new TimeSeries(); // 空且缺年份
        assertThrows(IllegalArgumentException.class, () -> a.dividedBy(b));
    }
}
