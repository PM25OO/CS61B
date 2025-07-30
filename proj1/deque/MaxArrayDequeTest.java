package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayDequeTest {

    /**
     * 测试默认构造函数创建的空队列
     */
    @Test
    public void testDefaultConstructor() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        assertTrue("新创建的队列应该为空", mad.isEmpty());
        assertEquals("新创建的队列大小应该为0", 0, mad.size());
    }

    /**
     * 测试带Comparator参数的构造函数创建的空队列
     */
    @Test
    public void testComparatorConstructor() {
        Comparator<Integer> intComparator = Integer::compareTo;
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(intComparator);
        assertTrue("新创建的队列应该为空", mad.isEmpty());
        assertEquals("新创建的队列大小应该为0", 0, mad.size());
    }

    /**
     * 测试addFirst方法添加单个元素
     */
    @Test
    public void testAddFirstSingleElement() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        mad.addFirst(5);
        assertFalse("添加元素后队列不应该为空", mad.isEmpty());
        assertEquals("添加一个元素后大小应该为1", 1, mad.size());
        assertEquals("第一个元素应该是5", (Integer) 5, mad.get(0));
    }

    /**
     * 测试addLast方法添加单个元素
     */
    @Test
    public void testAddLastSingleElement() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        mad.addLast(10);
        assertFalse("添加元素后队列不应该为空", mad.isEmpty());
        assertEquals("添加一个元素后大小应该为1", 1, mad.size());
        assertEquals("第一个元素应该是10", (Integer) 10, mad.get(0));
    }

    /**
     * 测试addFirst方法添加多个元素
     */
    @Test
    public void testAddFirstMultipleElements() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>();
        mad.addFirst("third");
        mad.addFirst("second");
        mad.addFirst("first");

        assertEquals("添加三个元素后大小应该为3", 3, mad.size());
        assertEquals("第一个元素应该是'first'", "first", mad.get(0));
        assertEquals("第二个元素应该是'second'", "second", mad.get(1));
        assertEquals("第三个元素应该是'third'", "third", mad.get(2));
    }

    /**
     * 测试addLast方法添加多个元素
     */
    @Test
    public void testAddLastMultipleElements() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>();
        mad.addLast("first");
        mad.addLast("second");
        mad.addLast("third");

        assertEquals("添加三个元素后大小应该为3", 3, mad.size());
        assertEquals("第一个元素应该是'first'", "first", mad.get(0));
        assertEquals("第二个元素应该是'second'", "second", mad.get(1));
        assertEquals("第三个元素应该是'third'", "third", mad.get(2));
    }

    /**
     * 测试混合使用addFirst和addLast方法
     */
    @Test
    public void testMixedAddFirstAndAddLast() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        mad.addLast(2);
        mad.addFirst(1);
        mad.addLast(3);
        mad.addFirst(0);

        assertEquals("添加四个元素后大小应该为4", 4, mad.size());
        assertEquals("第一个元素应该是0", (Integer) 0, mad.get(0));
        assertEquals("第二个元素应该是1", (Integer) 1, mad.get(1));
        assertEquals("第三个元素应该是2", (Integer) 2, mad.get(2));
        assertEquals("第四个元素应该是3", (Integer) 3, mad.get(3));
    }

    /**
     * 测试removeFirst方法从空队列删除元素
     */
    @Test
    public void testRemoveFirstFromEmpty() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        assertNull("从空队列删除第一个元素应该返回null", mad.removeFirst());
        assertTrue("队列应该仍然为空", mad.isEmpty());
    }

    /**
     * 测试removeLast方法从空队列删除元素
     */
    @Test
    public void testRemoveLastFromEmpty() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        assertNull("从空队列删除最后一个元素应该返回null", mad.removeLast());
        assertTrue("队列应该仍然为空", mad.isEmpty());
    }

    /**
     * 测试removeFirst方法删除单个元素
     */
    @Test
    public void testRemoveFirstSingleElement() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        mad.addFirst(42);
        assertEquals("删除的元素应该是42", (Integer) 42, mad.removeFirst());
        assertTrue("删除唯一元素后队列应该为空", mad.isEmpty());
        assertEquals("删除唯一元素后大小应该为0", 0, mad.size());
    }

    /**
     * 测试removeLast方法删除单个元素
     */
    @Test
    public void testRemoveLastSingleElement() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        mad.addLast(42);
        assertEquals("删除的元素应该是42", (Integer) 42, mad.removeLast());
        assertTrue("删除唯一元素后队列应该为空", mad.isEmpty());
        assertEquals("删除唯一元素后大小应该为0", 0, mad.size());
    }

    /**
     * 测试removeFirst方法删除多个元素
     */
    @Test
    public void testRemoveFirstMultipleElements() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        mad.addLast(1);
        mad.addLast(2);
        mad.addLast(3);

        assertEquals("删除的第一个元素应该是1", (Integer) 1, mad.removeFirst());
        assertEquals("删除后大小应该为2", 2, mad.size());
        assertEquals("删除的第二个元素应该是2", (Integer) 2, mad.removeFirst());
        assertEquals("删除后大小应该为1", 1, mad.size());
        assertEquals("删除的第三个元素应该是3", (Integer) 3, mad.removeFirst());
        assertTrue("删除所有元素后队列应该为空", mad.isEmpty());
    }

    /**
     * 测试removeLast方法删除多个元素
     */
    @Test
    public void testRemoveLastMultipleElements() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        mad.addLast(1);
        mad.addLast(2);
        mad.addLast(3);

        assertEquals("删除的最后一个元素应该是3", (Integer) 3, mad.removeLast());
        assertEquals("删除后大小应该为2", 2, mad.size());
        assertEquals("删除的倒数第二个元素应该是2", (Integer) 2, mad.removeLast());
        assertEquals("删除后大小应该为1", 1, mad.size());
        assertEquals("删除的第一个元素应该是1", (Integer) 1, mad.removeLast());
        assertTrue("删除所有元素后队列应该为空", mad.isEmpty());
    }

    /**
     * 测试get方法获取有效索引的元素
     */
    @Test
    public void testGetValidIndex() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>();
        mad.addLast("a");
        mad.addLast("b");
        mad.addLast("c");

        assertEquals("索引0的元素应该是'a'", "a", mad.get(0));
        assertEquals("索引1的元素应该是'b'", "b", mad.get(1));
        assertEquals("索引2的元素应该是'c'", "c", mad.get(2));
    }

    /**
     * 测试get方法获取无效索引的元素
     */
    @Test
    public void testGetInvalidIndex() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        mad.addLast(1);
        mad.addLast(2);

        assertNull("负索引应该返回null", mad.get(-1));
        assertNull("超出范围的索引应该返回null", mad.get(2));
        assertNull("远超出范围的索引应该返回null", mad.get(10));
    }

    /**
     * 测试数组扩容功能
     */
    @Test
    public void testArrayResizing() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();

        // 添加超过初始容量(8)的元素来触发扩容
        for (int i = 0; i < 20; i++) {
            mad.addLast(i);
        }

        assertEquals("添加20个元素后大小应该为20", 20, mad.size());
        for (int i = 0; i < 20; i++) {
            assertEquals("元素应该按顺序存储", (Integer) i, mad.get(i));
        }
    }

    /**
     * 测试数组缩容功能
     */
    @Test
    public void testArrayShrinking() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();

        // 先添加足够多的元素触发扩容
        for (int i = 0; i < 20; i++) {
            mad.addLast(i);
        }

        // 删除大部分元素触发缩容
        for (int i = 0; i < 18; i++) {
            mad.removeLast();
        }

        assertEquals("删除后应该还有2个元素", 2, mad.size());
        assertEquals("第一个元素应该是0", (Integer) 0, mad.get(0));
        assertEquals("第二个元素应该是1", (Integer) 1, mad.get(1));
    }

    /**
     * 测试max()方法在空队列上的行为
     */
    @Test
    public void testMaxOnEmptyDeque() {
        Comparator<Integer> intComparator = Integer::compareTo;
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(intComparator);
        assertNull("空队列的max()应该返回null", mad.max());
    }

    /**
     * 测试max()方法在单个元素队列上的行为
     */
    @Test
    public void testMaxOnSingleElement() {
        Comparator<Integer> intComparator = Integer::compareTo;
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(intComparator);
        mad.addFirst(42);
        assertEquals("单个元素队列的max()应该返回该元素", (Integer) 42, mad.max());
    }

    /**
     * 测试max()方法在多个元素队列上的行为
     */
    @Test
    public void testMaxOnMultipleElements() {
        Comparator<Integer> intComparator = Integer::compareTo;
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(intComparator);
        mad.addLast(3);
        mad.addLast(1);
        mad.addLast(4);
        mad.addLast(1);
        mad.addLast(5);
        mad.addLast(9);
        mad.addLast(2);

        assertEquals("多个元素队列的max()应该返回最大值", (Integer) 9, mad.max());
    }

    /**
     * 测试带Comparator参数的max()方法在空队列上的行为
     */
    @Test
    public void testMaxWithComparatorOnEmptyDeque() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        Comparator<Integer> intComparator = Integer::compareTo;
        assertNull("空队列的max(comparator)应该返回null", mad.max(intComparator));
    }

    /**
     * 测试带Comparator参数的max()方法在单个元素队列上的行为
     */
    @Test
    public void testMaxWithComparatorOnSingleElement() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        Comparator<Integer> intComparator = Integer::compareTo;
        mad.addFirst(42);
        assertEquals("单个元素队列的max(comparator)应该返回该元素", (Integer) 42, mad.max(intComparator));
    }

    /**
     * 测试带Comparator参数的max()方法在多个元素队列上的行为
     */
    @Test
    public void testMaxWithComparatorOnMultipleElements() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        Comparator<Integer> intComparator = Integer::compareTo;
        mad.addLast(3);
        mad.addLast(1);
        mad.addLast(4);
        mad.addLast(1);
        mad.addLast(5);
        mad.addLast(9);
        mad.addLast(2);

        assertEquals("多个元素队列的max(comparator)应该返回最大值", (Integer) 9, mad.max(intComparator));
    }

    /**
     * 测试使用自定义Comparator的max()方法
     */
    @Test
    public void testMaxWithCustomComparator() {
        // 创建一个反向比较器（最小值变成最大值）
        Comparator<Integer> reverseComparator = (a, b) -> b.compareTo(a);
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(reverseComparator);
        mad.addLast(3);
        mad.addLast(1);
        mad.addLast(4);
        mad.addLast(5);

        assertEquals("使用反向比较器时max()应该返回最小值", (Integer) 1, mad.max());
    }

    /**
     * 测试使用字符串比较器的max()方法
     */
    @Test
    public void testMaxWithStringComparator() {
        Comparator<String> stringComparator = String::compareTo;
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(stringComparator);
        mad.addLast("apple");
        mad.addLast("banana");
        mad.addLast("cherry");
        mad.addLast("date");

        assertEquals("字符串队列的max()应该返回字典序最大的字符串", "date", mad.max());
    }

    /**
     * 测试printDeque方法（虽然无法直接验证输出，但可以确保不抛出异常）
     */
    @Test
    public void testPrintDeque() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        mad.addLast(1);
        mad.addLast(2);
        mad.addLast(3);

        // 这个测试主要确保printDeque不会抛出异常
        try {
            mad.printDeque();
        } catch (Exception e) {
            fail("printDeque方法不应该抛出异常");
        }
    }

    /**
     * 测试空队列的printDeque方法
     */
    @Test
    public void testPrintEmptyDeque() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();

        try {
            mad.printDeque();
        } catch (Exception e) {
            fail("空队列的printDeque方法不应该抛出异常");
        }
    }

    /**
     * 综合测试：测试完整的添加、删除、查找最大值的工作流程
     */
    @Test
    public void testCompleteWorkflow() {
        Comparator<Integer> intComparator = Integer::compareTo;
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(intComparator);

        // 添加一些元素
        mad.addFirst(5);
        mad.addLast(10);
        mad.addFirst(3);
        mad.addLast(7);

        assertEquals("队列大小应该为4", 4, mad.size());
        assertEquals("最大值应该是10", (Integer) 10, mad.max());

        // 删除一些元素
        assertEquals("删除第一个元素应该是3", (Integer) 3, mad.removeFirst());
        assertEquals("删除最后一个元素应该是7", (Integer) 7, mad.removeLast());

        assertEquals("队列大小应该为2", 2, mad.size());
        assertEquals("最大值应该是10", (Integer) 10, mad.max());

        // 继续删除
        mad.removeFirst(); // 删除5
        assertEquals("队列大小应该为1", 1, mad.size());
        assertEquals("最大值应该是10", (Integer) 10, mad.max());

        mad.removeLast(); // 删除10
        assertTrue("队列应该为空", mad.isEmpty());
        assertNull("空队列的最大值应该为null", mad.max());
    }
}
