package utils;

import java.util.Random;

/**
 * A library of static methods to generate pseudo-random numbers from
 * different distributions (bernoulli, uniform, gaussian, discrete,
 * and exponential). Also includes methods for shuffling an array and
 * other randomness related stuff you might want to do. Feel free to
 * modify this file.
 * <p>
 * Adapted from https://introcs.cs.princeton.edu/java/22library/StdRandom.java.html
 */
/*
 * 一个静态方法的库，用于从不同的分布（伯努利、均匀、高斯、离散和指数）
 * 生成伪随机数。还包括用于打乱数组和其他您可能需要的随机性相关操作的
 * 方法。您可以自由修改此文件。
 * <p>
 * 改编自 https://introcs.cs.princeton.edu/java/22library/StdRandom.java.html
 */
public class RandomUtils {

    /**
     * Returns a random real number uniformly in [0, 1).
     * 返回 [0, 1) 范围内均匀分布的随机实数。
     *
     * @return a random real number uniformly in [0, 1)
     * [0, 1) 范围内均匀分布的随机实数
     */
    public static double uniform(Random random) {
        return random.nextDouble();
    }

    /**
     * Returns a random integer uniformly in [0, n).
     * 返回 [0, n) 范围内均匀分布的随机整数。
     *
     * @param n number of possible integers
     * 可能的整数数量
     * @return a random integer uniformly between 0 (inclusive) and {@code n} (exclusive)
     * 0（包含）和 {@code n}（不包含）之间均匀分布的随机整数
     * @throws IllegalArgumentException if {@code n <= 0}
     * 如果 {@code n <= 0} 则抛出此异常
     */
    public static int uniform(Random random, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("argument must be positive: " + n);
        }
        return random.nextInt(n);
    }


    /**
     * Returns a random long integer uniformly in [0, n).
     * 返回 [0, n) 范围内均匀分布的随机长整数。
     *
     * @param n number of possible {@code long} integers
     * 可能的 {@code long} 整数数量
     * @return a random long integer uniformly between 0 (inclusive) and {@code n} (exclusive)
     * 0（包含）和 {@code n}（不包含）之间均匀分布的随机长整数
     * @throws IllegalArgumentException if {@code n <= 0}
     * 如果 {@code n <= 0} 则抛出此异常
     */
    public static long uniform(Random random, long n) {
        if (n <= 0L) {
            throw new IllegalArgumentException("argument must be positive: " + n);
        }

        // https://docs.oracle.com/javase/8/docs/api/java/util/Random.html#longs-long-long-long-
        long r = random.nextLong();
        long m = n - 1;

        // power of two
        // 二的幂
        if ((n & m) == 0L) {
            return r & m;
        }

        // reject over-represented candidates
        // 拒绝过度代表的候选项
        long u = r >>> 1;
        while (u + m - (r = u % n) < 0L) {
            u = random.nextLong() >>> 1;
        }
        return r;
    }

    ///////////////////////////////////////////////////////////////////////////
    //  STATIC METHODS BELOW RELY ON JAVA.UTIL.RANDOM ONLY INDIRECTLY VIA
    //  THE STATIC METHODS ABOVE.
    ///////////////////////////////////////////////////////////////////////////
    /*
     * 下面的静态方法仅通过上面的静态方法间接依赖于 JAVA.UTIL.RANDOM。
     */


    /**
     * Returns a random integer uniformly in [a, b).
     * 返回 [a, b) 范围内均匀分布的随机整数。
     *
     * @param a the left endpoint
     * 左端点（包含）
     * @param b the right endpoint
     * 右端点（不包含）
     * @return a random integer uniformly in [a, b)
     * [a, b) 范围内均匀分布的随机整数
     * @throws IllegalArgumentException if {@code b <= a}
     * 如果 {@code b <= a} 则抛出此异常
     * @throws IllegalArgumentException if {@code b - a >= Integer.MAX_VALUE}
     * 如果 {@code b - a >= Integer.MAX_VALUE} 则抛出此异常
     */
    public static int uniform(Random random, int a, int b) {
        if ((b <= a) || ((long) b - a >= Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("invalid range: [" + a + ", " + b + ")");
        }
        return a + uniform(random, b - a);
    }

    /**
     * Returns a random real number uniformly in [a, b).
     * 返回 [a, b) 范围内均匀分布的随机实数。
     *
     * @param a the left endpoint
     * 左端点（包含）
     * @param b the right endpoint
     * 右端点（不包含）
     * @return a random real number uniformly in [a, b)
     * [a, b) 范围内均匀分布的随机实数
     * @throws IllegalArgumentException unless {@code a < b}
     * 除非 {@code a < b}，否则抛出此异常
     */
    public static double uniform(Random random, double a, double b) {
        if (!(a < b)) {
            throw new IllegalArgumentException("invalid range: [" + a + ", " + b + ")");
        }
        return a + uniform(random) * (b - a);
    }

    /**
     * Returns a random boolean from a Bernoulli distribution with success
     * probability <em>p</em>.
     * 返回来自成功概率为 <em>p</em> 的伯努利分布的随机布尔值。
     *
     * @param p the probability of returning {@code true}
     * 返回 {@code true} 的概率
     * @return {@code true} with probability {@code p} and
     * {@code false} with probability {@code p}
     * 以概率 {@code p} 返回 {@code true}，以概率 {@code 1 - p} 返回 {@code false}
     * @throws IllegalArgumentException unless {@code 0} &le; {@code p} &le; {@code 1.0}
     * 除非 {@code 0} &le; {@code p} &le; {@code 1.0}，否则抛出此异常
     */
    public static boolean bernoulli(Random random, double p) {
        if (!(p >= 0.0 && p <= 1.0)) {
            throw new IllegalArgumentException("probability p must be between 0.0 and 1.0: " + p);
        }
        return uniform(random) < p;
    }

    /**
     * Returns a random boolean from a Bernoulli distribution with success
     * probability 1/2.
     * 返回来自成功概率为 1/2 的伯努利分布的随机布尔值。
     *
     * @return {@code true} with probability 1/2 and
     * {@code false} with probability 1/2
     * 以概率 1/2 返回 {@code true}，以概率 1/2 返回 {@code false}
     */
    public static boolean bernoulli(Random random) {
        return bernoulli(random, 0.5);
    }

    /**
     * Returns a random real number from a standard Gaussian distribution.
     * 返回来自标准高斯分布（正态分布）的随机实数。
     *
     * @return a random real number from a standard Gaussian distribution
     * (mean 0 and standard deviation 1).
     * 来自标准高斯分布（均值 0，标准差 1）的随机实数。
     */
    public static double gaussian(Random random) {
        // use the polar form of the Box-Muller transform
        // 使用 Box-Muller 变换的极坐标形式
        double r, x, y;
        do {
            x = uniform(random, -1.0, 1.0);
            y = uniform(random, -1.0, 1.0);
            r = x * x + y * y;
        } while (r >= 1 || r == 0);
        return x * Math.sqrt(-2 * Math.log(r) / r);

        // Remark:  y * Math.sqrt(-2 * Math.log(r) / r)
        // is an independent random gaussian
        // 备注：y * Math.sqrt(-2 * Math.log(r) / r) 是一个独立的高斯随机数
    }

    /**
     * Returns a random real number from a Gaussian distribution with mean &mu;
     * and standard deviation &sigma;.
     * 返回来自均值为 $\mu$，标准差为 $\sigma$ 的高斯分布的随机实数。
     *
     * @param mu    the mean
     * 均值 ($\mu$)
     * @param sigma the standard deviation
     * 标准差 ($\sigma$)
     * @return a real number distributed according to the Gaussian distribution
     * with mean {@code mu} and standard deviation {@code sigma}
     * 根据均值为 {@code mu}、标准差为 {@code sigma} 的高斯分布的实数
     */
    public static double gaussian(Random random, double mu, double sigma) {
        return mu + sigma * gaussian(random);
    }

    /**
     * Returns a random integer from a geometric distribution with success
     * probability <em>p</em>.
     * 返回来自成功概率为 <em>p</em> 的几何分布的随机整数。
     *
     * @param p the parameter of the geometric distribution
     * 几何分布的参数
     * @return a random integer from a geometric distribution with success
     * probability {@code p}; or {@code Integer.MAX_VALUE} if
     * {@code p} is (nearly) equal to {@code 1.0}.
     * 来自成功概率为 {@code p} 的几何分布的随机整数；如果 {@code p}
     * （几乎）等于 {@code 1.0}，则返回 {@code Integer.MAX_VALUE}。
     * @throws IllegalArgumentException unless {@code p >= 0.0} and {@code p <= 1.0}
     * 除非 {@code p >= 0.0} 且 {@code p <= 1.0}，否则抛出此异常
     */
    public static int geometric(Random random, double p) {
        if (!(p >= 0.0 && p <= 1.0)) {
            throw new IllegalArgumentException("probability p must be between 0.0 and 1.0: " + p);
        }
        // using algorithm given by Knuth
        // 使用 Knuth 给出的算法
        return (int) Math.ceil(Math.log(uniform(random)) / Math.log(1.0 - p));
    }

    /**
     * Returns a random integer from a Poisson distribution with mean &lambda;.
     * 返回来自均值为 $\lambda$ 的泊松分布的随机整数。
     *
     * @param lambda the mean of the Poisson distribution
     * 泊松分布的均值 ($\lambda$)
     * @return a random integer from a Poisson distribution with mean {@code lambda}
     * 来自均值为 {@code lambda} 的泊松分布的随机整数
     * @throws IllegalArgumentException unless {@code lambda > 0.0} and not infinite
     * 除非 {@code lambda > 0.0} 且不为无穷大，否则抛出此异常
     */
    public static int poisson(Random random, double lambda) {
        if (!(lambda > 0.0)) {
            throw new IllegalArgumentException("lambda must be positive: " + lambda);
        }
        if (Double.isInfinite(lambda)) {
            throw new IllegalArgumentException("lambda must not be infinite: " + lambda);
        }
        // using algorithm given by Knuth
        // 使用 Knuth 给出的算法
        // see http://en.wikipedia.org/wiki/Poisson_distribution
        // 参阅 http://en.wikipedia.org/wiki/Poisson_distribution
        int k = 0;
        double p = 1.0;
        double expLambda = Math.exp(-lambda);
        do {
            k++;
            p *= uniform(random);
        } while (p >= expLambda);
        return k - 1;
    }

    /**
     * Returns a random real number from the standard Pareto distribution.
     * 返回来自标准帕累托分布的随机实数。
     *
     * @return a random real number from the standard Pareto distribution
     * 来自标准帕累托分布的随机实数
     */
    public static double pareto(Random random) {
        return pareto(random, 1.0);
    }

    /**
     * Returns a random real number from a Pareto distribution with
     * shape parameter &alpha;.
     * 返回来自形状参数为 $\alpha$ 的帕累托分布的随机实数。
     *
     * @param alpha shape parameter
     * 形状参数 ($\alpha$)
     * @return a random real number from a Pareto distribution with shape
     * parameter {@code alpha}
     * 来自形状参数为 {@code alpha} 的帕累托分布的随机实数
     * @throws IllegalArgumentException unless {@code alpha > 0.0}
     * 除非 {@code alpha > 0.0}，否则抛出此异常
     */
    public static double pareto(Random random, double alpha) {
        if (!(alpha > 0.0)) {
            throw new IllegalArgumentException("alpha must be positive: " + alpha);
        }
        return Math.pow(1 - uniform(random), -1.0 / alpha) - 1.0;
    }

    /**
     * Returns a random real number from the Cauchy distribution.
     * 返回来自柯西分布的随机实数。
     *
     * @return a random real number from the Cauchy distribution.
     * 来自柯西分布的随机实数。
     */
    public static double cauchy(Random random) {
        return Math.tan(Math.PI * (uniform(random) - 0.5));
    }

    /**
     * Returns a random integer from the specified discrete distribution.
     * 返回来自指定离散分布的随机整数。
     *
     * @param probabilities the probability of occurrence of each integer
     * 每个整数发生的概率
     * @return a random integer from a discrete distribution:
     * {@code i} with probability {@code probabilities[i]}
     * 来自离散分布的随机整数：以概率 {@code probabilities[i]} 返回 {@code i}
     * @throws IllegalArgumentException if {@code probabilities} is {@code null}
     * 如果 {@code probabilities} 为 {@code null} 则抛出此异常
     * @throws IllegalArgumentException if sum of array entries is not (very nearly) equal to 1.0
     * 如果数组元素的总和不（非常接近地）等于 1.0 则抛出此异常
     * @throws IllegalArgumentException unless {@code probabilities[i] >= 0.0} for each index i
     * 除非每个索引 i 的 {@code probabilities[i] >= 0.0}，否则抛出此异常
     */
    public static int discrete(Random random, double[] probabilities) {
        if (probabilities == null) {
            throw new IllegalArgumentException("argument array is null");
        }
        double eps = 1E-14;
        double sum = 0.0;
        for (int i = 0; i < probabilities.length; i++) {
            if (!(probabilities[i] >= 0.0)) {
                throw new IllegalArgumentException("array entry " + i + " must be nonnegative: "
                                                   + probabilities[i]);
            }
            sum += probabilities[i];
        }
        if (sum > 1.0 + eps || sum < 1.0 - eps) {
            throw new IllegalArgumentException("sum of array entries does not approximately "
                                               + "equal 1.0: " + sum);
        }

        // the for loop may not return a value when both r is (nearly) 1.0 and when the
        // cumulative sum is less than 1.0 (as a result of floating-point roundoff error)
        // 当 r （几乎）为 1.0 且累积和小于 1.0 时（由于浮点舍入误差），for 循环可能不会返回值
        while (true) {
            double r = uniform(random);
            sum = 0.0;
            for (int i = 0; i < probabilities.length; i++) {
                sum = sum + probabilities[i];
                if (sum > r) {
                    return i;
                }
            }
        }
    }

    /**
     * Returns a random integer from the specified discrete distribution.
     * 返回来自指定离散分布的随机整数。
     *
     * @param frequencies the frequency of occurrence of each integer
     * 每个整数的出现频率
     * @return a random integer from a discrete distribution:
     * i with probability proportional to frequencies[i]
     * 来自离散分布的随机整数：以与 frequencies[i] 成比例的概率返回 i
     * @throws IllegalArgumentException if frequencies is null
     * 如果 frequencies 为 null 则抛出此异常
     * @throws IllegalArgumentException if all array entries are 0
     * 如果所有数组元素都为 0 则抛出此异常
     * @throws IllegalArgumentException if frequencies[i] is negative for any index i
     * 如果任何索引 i 的 frequencies[i] 为负数则抛出此异常
     * @throws IllegalArgumentException if sum of frequencies exceeds Integer.MAX_VALUE (2^31 - 1)
     * 如果频率的总和超过 Integer.MAX_VALUE (2^31 - 1) 则抛出此异常
     */
    public static int discrete(Random random, int[] frequencies) {
        if (frequencies == null) {
            throw new IllegalArgumentException("argument array is null");
        }
        long sum = 0;
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] < 0) {
                throw new IllegalArgumentException("array entry " + i + " must be nonnegative: "
                                                   + frequencies[i]);
            }
            sum += frequencies[i];
        }
        if (sum == 0) {
            throw new IllegalArgumentException("at least one array entry must be positive");
        }
        if (sum >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException("sum of frequencies overflows an int");
        }

        // pick index i with probabilitity proportional to frequency
        // 选择一个索引 i，其概率与频率成比例
        double r = uniform(random, (int) sum);
        sum = 0;
        for (int i = 0; i < frequencies.length; i++) {
            sum += frequencies[i];
            if (sum > r) {
                return i;
            }
        }

        // can't reach here
        // 不可能到达这里
        assert false;
        return -1;
    }

    /**
     * Returns a random real number from an exponential distribution
     * with rate &lambda;.
     * 返回来自速率为 $\lambda$ 的指数分布的随机实数。
     *
     * @param lambda the rate of the exponential distribution
     * 指数分布的速率 ($\lambda$)
     * @return a random real number from an exponential distribution with
     * rate {@code lambda}
     * 来自速率为 {@code lambda} 的指数分布的随机实数
     * @throws IllegalArgumentException unless {@code lambda > 0.0}
     * 除非 {@code lambda > 0.0}，否则抛出此异常
     */
    public static double exp(Random random, double lambda) {
        if (!(lambda > 0.0)) {
            throw new IllegalArgumentException("lambda must be positive: " + lambda);
        }
        return -Math.log(1 - uniform(random)) / lambda;
    }

    /**
     * Rearranges the elements of the specified array in uniformly random order.
     * 以均匀随机的顺序重新排列指定数组的元素（打乱）。
     *
     * @param a the array to shuffle
     * 要打乱的数组
     * @throws IllegalArgumentException if {@code a} is {@code null}
     * 如果 {@code a} 为 {@code null} 则抛出此异常
     */
    public static void shuffle(Random random, Object[] a) {
        validateNotNull(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(random, n - i);     // between i and n-1
            // 在 i 和 n-1 之间
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /**
     * Rearranges the elements of the specified array in uniformly random order.
     * 以均匀随机的顺序重新排列指定数组的元素（打乱）。
     *
     * @param a the array to shuffle
     * 要打乱的数组
     * @throws IllegalArgumentException if {@code a} is {@code null}
     * 如果 {@code a} 为 {@code null} 则抛出此异常
     */
    public static void shuffle(Random random, double[] a) {
        validateNotNull(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(random, n - i);     // between i and n-1
            // 在 i 和 n-1 之间
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /**
     * Rearranges the elements of the specified array in uniformly random order.
     * 以均匀随机的顺序重新排列指定数组的元素（打乱）。
     *
     * @param a the array to shuffle
     * 要打乱的数组
     * @throws IllegalArgumentException if {@code a} is {@code null}
     * 如果 {@code a} 为 {@code null} 则抛出此异常
     */
    public static void shuffle(Random random, int[] a) {
        validateNotNull(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(random, n - i);     // between i and n-1
            // 在 i 和 n-1 之间
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /**
     * Rearranges the elements of the specified array in uniformly random order.
     * 以均匀随机的顺序重新排列指定数组的元素（打乱）。
     *
     * @param a the array to shuffle
     * 要打乱的数组
     * @throws IllegalArgumentException if {@code a} is {@code null}
     * 如果 {@code a} 为 {@code null} 则抛出此异常
     */
    public static void shuffle(Random random, char[] a) {
        validateNotNull(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(random, n - i);     // between i and n-1
            // 在 i 和 n-1 之间
            char temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /**
     * Rearranges the elements of the specified subarray in uniformly random order.
     * 以均匀随机的顺序重新排列指定子数组的元素（打乱）。
     *
     * @param a  the array to shuffle
     * 要打乱的数组
     * @param lo the left endpoint (inclusive)
     * 左端点（包含）
     * @param hi the right endpoint (exclusive)
     * 右端点（不包含）
     * @throws IllegalArgumentException if {@code a} is {@code null}
     * 如果 {@code a} 为 {@code null} 则抛出此异常
     * @throws IllegalArgumentException unless {@code (0 <= lo) && (lo < hi) && (hi <= a.length)}
     * 除非 {@code (0 <= lo) && (lo < hi) && (hi <= a.length)}，否则抛出此异常
     */
    public static void shuffle(Random random, Object[] a, int lo, int hi) {
        validateNotNull(a);
        validateSubarrayIndices(lo, hi, a.length);

        for (int i = lo; i < hi; i++) {
            int r = i + uniform(random, hi - i);     // between i and hi-1
            // 在 i 和 hi-1 之间
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /**
     * Rearranges the elements of the specified subarray in uniformly random order.
     * 以均匀随机的顺序重新排列指定子数组的元素（打乱）。
     *
     * @param a  the array to shuffle
     * 要打乱的数组
     * @param lo the left endpoint (inclusive)
     * 左端点（包含）
     * @param hi the right endpoint (exclusive)
     * 右端点（不包含）
     * @throws IllegalArgumentException if {@code a} is {@code null}
     * 如果 {@code a} 为 {@code null} 则抛出此异常
     * @throws IllegalArgumentException unless {@code (0 <= lo) && (lo < hi) && (hi <= a.length)}
     * 除非 {@code (0 <= lo) && (lo < hi) && (hi <= a.length)}，否则抛出此异常
     */
    public static void shuffle(Random random, double[] a, int lo, int hi) {
        validateNotNull(a);
        validateSubarrayIndices(lo, hi, a.length);

        for (int i = lo; i < hi; i++) {
            int r = i + uniform(random, hi - i);     // between i and hi-1
            // 在 i 和 hi-1 之间
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /**
     * Rearranges the elements of the specified subarray in uniformly random order.
     * 以均匀随机的顺序重新排列指定子数组的元素（打乱）。
     *
     * @param a  the array to shuffle
     * 要打乱的数组
     * @param lo the left endpoint (inclusive)
     * 左端点（包含）
     * @param hi the right endpoint (exclusive)
     * 右端点（不包含）
     * @throws IllegalArgumentException if {@code a} is {@code null}
     * 如果 {@code a} 为 {@code null} 则抛出此异常
     * @throws IllegalArgumentException unless {@code (0 <= lo) && (lo < hi) && (hi <= a.length)}
     * 除非 {@code (0 <= lo) && (lo < hi) && (hi <= a.length)}，否则抛出此异常
     */
    public static void shuffle(Random random, int[] a, int lo, int hi) {
        validateNotNull(a);
        validateSubarrayIndices(lo, hi, a.length);

        for (int i = lo; i < hi; i++) {
            int r = i + uniform(random, hi - i);     // between i and hi-1
            // 在 i 和 hi-1 之间
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /**
     * Returns a uniformly random permutation of <em>n</em> elements.
     * 返回 <em>n</em> 个元素的均匀随机排列。
     *
     * @param n number of elements
     * 元素数量
     * @return an array of length {@code n} that is a uniformly random permutation
     * of {@code 0}, {@code 1}, ..., {@code n-1}
     * 一个长度为 {@code n} 的数组，是 {@code 0}, {@code 1}, ..., {@code n-1}
     * 的均匀随机排列
     * @throws IllegalArgumentException if {@code n} is negative
     * 如果 {@code n} 为负数则抛出此异常
     */
    public static int[] permutation(Random random, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("argument is negative");
        }
        int[] perm = new int[n];
        for (int i = 0; i < n; i++) {
            perm[i] = i;
        }
        shuffle(random, perm);
        return perm;
    }

    /**
     * Returns a uniformly random permutation of <em>k</em> of <em>n</em> elements.
     * 返回 <em>n</em> 个元素中 **选取** <em>k</em> 个的均匀随机排列。
     *
     * @param n number of elements
     * 元素总数
     * @param k number of elements to select
     * 要选取的元素数量
     * @return an array of length {@code k} that is a uniformly random permutation
     * of {@code k} of the elements from {@code 0}, {@code 1}, ..., {@code n-1}
     * 一个长度为 {@code k} 的数组，是 {@code 0}, {@code 1}, ..., {@code n-1}
     * 中选取的 {@code k} 个元素的均匀随机排列
     * @throws IllegalArgumentException if {@code n} is negative
     * 如果 {@code n} 为负数则抛出此异常
     * @throws IllegalArgumentException unless {@code 0 <= k <= n}
     * 除非 {@code 0 <= k <= n}，否则抛出此异常
     */
    public static int[] permutation(Random random, int n, int k) {
        if (n < 0) {
            throw new IllegalArgumentException("argument is negative");
        }
        if (k < 0 || k > n) {
            throw new IllegalArgumentException("k must be between 0 and n");
        }
        int[] perm = new int[k];
        for (int i = 0; i < k; i++) {
            int r = uniform(random, i + 1);    // between 0 and i
            // 在 0 和 i 之间
            perm[i] = perm[r];
            perm[r] = i;
        }
        for (int i = k; i < n; i++) {
            int r = uniform(random, i + 1);    // between 0 and i
            // 在 0 和 i 之间
            if (r < k) {
                perm[r] = i;
            }
        }
        return perm;
    }

    // throw an IllegalArgumentException if x is null
    // (x can be of type Object[], double[], int[], ...)
    // 如果 x 为 null 则抛出 IllegalArgumentException
    // (x 可以是 Object[], double[], int[], ... 类型)
    private static void validateNotNull(Object x) {
        if (x == null) {
            throw new IllegalArgumentException("argument is null");
        }
    }

    // throw an exception unless 0 <= lo <= hi <= length
    // 除非 0 <= lo <= hi <= length，否则抛出异常
    private static void validateSubarrayIndices(int lo, int hi, int length) {
        if (lo < 0 || hi > length || lo > hi) {
            throw new IllegalArgumentException("subarray indices out of bounds: [" + lo + ", "
                                               + hi + ")");
        }
    }
}