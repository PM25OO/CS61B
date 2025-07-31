package gh2;

import deque.ArrayDeque;
import deque.Deque;

//Note: This file will not compile until you complete the Deque implementations
//注意：在你完成Deque实现之前，这个文件不会编译
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    /**
     * 常量。不要更改。如果你好奇的话，关键字final
     * 意味着这些值在运行时不能被改变。我们将在周五的讲座中讨论这个
     * 和其他主题。
     */
    private static final int SR = 44100;      // Sampling Rate // 采样率
    private static final double DECAY = .996; // energy decay factor // 能量衰减因子

    /* Buffer for storing sound data. */
    /* 存储声音数据的缓冲区。 */
    private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    /* 创建给定频率的吉他弦。 */
    public GuitarString(double frequency) {
        int buffer_size = (int) Math.round(SR / frequency);
        buffer = new ArrayDeque<>();
        while (buffer.size() < buffer_size) buffer.addLast(0.0);
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    /* 通过用白噪声替换缓冲区来拨动吉他弦。 */
    public void pluck() {
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
        //
        //       确保你的随机数彼此不同。这并不意味着你需要检查
        //       数字是否彼此不同。这意味着你应该反复调用
        //       Math.random() - 0.5 为每个数组索引生成新的随机数。
        int size = buffer.size();
        for (int i = 0; i < size; i++) {
            buffer.removeFirst();
        }
        for (int i = 0; i < size; i++) {
            buffer.addLast(Math.random() - 0.5);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    /* 通过执行Karplus-Strong算法的一次迭代来
     * 推进模拟一个时间步长。
     */
    public void tic() {
        double newDouble = (buffer.get(0) + buffer.get(1)) / 2 * DECAY;
        buffer.removeFirst();
        buffer.addLast(newDouble);
    }

    /* Return the double at the front of the buffer. */
    /* 返回缓冲区前端的double值。 */
    public double sample() {
        return buffer.get(0);
    }
}
