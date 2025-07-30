package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    /**
     * 测试默认构造函数是否正确创建空的 ArrayDeque
     */
    @Test
    public void testConstructor() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        assertTrue("新创建的 deque 应该为空", deque.isEmpty());
        assertEquals("新创建的 deque 大小应该为 0", 0, deque.size());
    }

    /**
     * 测试 addFirst 方法：在双端队列前端添加元素
     */
    @Test
    public void testAddFirst() {
        ArrayDeque<String> deque = new ArrayDeque<>();

        // 添加单个元素
        deque.addFirst("first");
        assertFalse("添加元素后 deque 不应该为空", deque.isEmpty());
        assertEquals("添加一个元素后大小应该为 1", 1, deque.size());
        assertEquals("第一个元素应该是 'first'", "first", deque.get(0));

        // 添加多个元素
        deque.addFirst("second");
        deque.addFirst("third");
        assertEquals("添加三个元素后大小应该为 3", 3, deque.size());
        assertEquals("第一个元素应该是 'third'", "third", deque.get(0));
        assertEquals("第二个元素应该是 'second'", "second", deque.get(1));
        assertEquals("第三个元素应该是 'first'", "first", deque.get(2));
    }

    /**
     * 测试 addLast 方法：在双端队列后端添加元素
     */
    @Test
    public void testAddLast() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // 添加单个元素
        deque.addLast(1);
        assertEquals("添加一个元素后大小应该为 1", 1, deque.size());
        assertEquals("第一个元素应该是 1", (Integer) 1, deque.get(0));

        // 添加多个元素
        deque.addLast(2);
        deque.addLast(3);
        assertEquals("添加三个元素后大小应该为 3", 3, deque.size());
        assertEquals("第一个元素应该是 1", (Integer) 1, deque.get(0));
        assertEquals("第二个元素应该是 2", (Integer) 2, deque.get(1));
        assertEquals("第三个元素应该是 3", (Integer) 3, deque.get(2));
    }

    /**
     * 测试混合使用 addFirst 和 addLast 方法
     */
    @Test
    public void testMixedAdd() {
        ArrayDeque<String> deque = new ArrayDeque<>();

        deque.addLast("middle");
        deque.addFirst("first");
        deque.addLast("last");

        assertEquals("混合添加后大小应该为 3", 3, deque.size());
        assertEquals("第一个元素应该是 'first'", "first", deque.get(0));
        assertEquals("第二个元素应该是 'middle'", "middle", deque.get(1));
        assertEquals("第三个元素应该是 'last'", "last", deque.get(2));
    }

    /**
     * 测试 isEmpty 方法：检查双端队列是否为空
     */
    @Test
    public void testIsEmpty() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // 空队列测试
        assertTrue("新创建的 deque 应该为空", deque.isEmpty());

        // 添加元素后测试
        deque.addFirst(1);
        assertFalse("添加元素后 deque 不应该为空", deque.isEmpty());

        // 移除元素后测试
        deque.removeFirst();
        assertTrue("移除所有元素后 deque 应该为空", deque.isEmpty());
    }

    /**
     * 测试 size 方法：获取双端队列的大小
     */
    @Test
    public void testSize() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        assertEquals("空 deque 大小应该为 0", 0, deque.size());

        // 逐步添加元素并检查大小
        for (int i = 1; i <= 5; i++) {
            deque.addLast(i);
            assertEquals("添加 " + i + " 个元素后大小应该为 " + i, i, deque.size());
        }

        // 逐步移除元素并检查大小
        for (int i = 4; i >= 0; i--) {
            deque.removeLast();
            assertEquals("移除元素后大小应该为 " + i, i, deque.size());
        }
    }

    /**
     * 测试 removeFirst 方法：移除并返回双端队列前端的元素
     */
    @Test
    public void testRemoveFirst() {
        ArrayDeque<String> deque = new ArrayDeque<>();

        // 添加测试数据
        deque.addLast("first");
        deque.addLast("second");
        deque.addLast("third");

        // 测试移除第一个元素
        String removed = deque.removeFirst();
        assertEquals("移除的元素应该是 'first'", "first", removed);
        assertEquals("移除后大小应该为 2", 2, deque.size());
        assertEquals("新的第一个元素应该是 'second'", "second", deque.get(0));

        // 继续移除
        assertEquals("移除的元素应该是 'second'", "second", deque.removeFirst());
        assertEquals("移除的元素应该是 'third'", "third", deque.removeFirst());
        assertTrue("移除所有元素后应该为空", deque.isEmpty());
    }

    /**
     * 测试 removeLast 方法：移除并返回双端队列后端的元素
     */
    @Test
    public void testRemoveLast() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // 添加测试数据
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        // 测试移除最后一个元素
        Integer removed = deque.removeLast();
        assertEquals("移除的元素应该是 3", (Integer) 3, removed);
        assertEquals("移除后大小应该为 2", 2, deque.size());
        assertEquals("最后一个元素应该是 2", (Integer) 2, deque.get(1));

        // 继续移除
        assertEquals("移除的元素应该是 2", (Integer) 2, deque.removeLast());
        assertEquals("移除的元素应该是 1", (Integer) 1, deque.removeLast());
        assertTrue("移除所有元素后应该为空", deque.isEmpty());
    }

    /**
     * 测试 get 方法：根据索引获取元素
     */
    @Test
    public void testGet() {
        ArrayDeque<String> deque = new ArrayDeque<>();

        // 添加测试数据
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");

        // 测试有效索引
        assertEquals("索引 0 的元素应该是 'a'", "a", deque.get(0));
        assertEquals("索引 1 的元素应该是 'b'", "b", deque.get(1));
        assertEquals("索引 2 的元素应该是 'c'", "c", deque.get(2));

        // 测试无效索引
        assertNull("负索引应该返回 null", deque.get(-1));
        assertNull("超出范围的索引应该返回 null", deque.get(3));
        assertNull("远超出范围的索引应该返回 null", deque.get(100));
    }

    /**
     * 测试数组自动扩容机制：当元素数量达到数组容量时自动扩容
     */
    @Test
    public void testArrayResize() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // 添加超过初始容量的元素（初始容量为 8）
        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }

        assertEquals("添加 20 个元素后大小应该为 20", 20, deque.size());

        // 验证所有元素都正确存储
        for (int i = 0; i < 20; i++) {
            assertEquals("索引 " + i + " 的元素应该是 " + i, (Integer) i, deque.get(i));
        }
    }

    /**
     * 测试数组自动缩容机制：当使用率低于阈值时自动缩容
     */
    @Test
    public void testArrayShrink() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // 先添加大量元素触发扩容
        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }

        // 然后移除大部分元素触发缩容
        for (int i = 0; i < 18; i++) {
            deque.removeLast();
        }

        assertEquals("移除后大小应该为 2", 2, deque.size());
        assertEquals("剩余的第一个元素应该是 0", (Integer) 0, deque.get(0));
        assertEquals("剩余的第二个元素应该是 1", (Integer) 1, deque.get(1));
    }

    /**
     * 测试 printDeque 方法：打印双端队列的所有元素
     * 注意：这个测试主要验证方法不会抛出异常，实际输出需要手动验证
     */
    @Test
    public void testPrintDeque() {
        ArrayDeque<String> deque = new ArrayDeque<>();

        // 测试空队列打印
        deque.printDeque(); // 应该只打印一个换行符

        // 测试非空队列打印
        deque.addLast("hello");
        deque.addLast("world");
        deque.printDeque(); // 应该打印 "hello world"
    }

    /**
     * 综合测试：测试各种操作的组合使用
     */
    @Test
    public void testComprehensive() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // 混合添加和移除操作
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(0);
        deque.addLast(3);

        assertEquals("混合操作后大小应该为 4", 4, deque.size());
        assertEquals("第一个元素应该是 0", (Integer) 0, deque.get(0));
        assertEquals("最后一个元素应该是 3", (Integer) 3, deque.get(3));

        // 移除操作
        assertEquals("removeFirst 应该返回 0", (Integer) 0, deque.removeFirst());
        assertEquals("removeLast 应该返回 3", (Integer) 3, deque.removeLast());

        assertEquals("移除后大小应该为 2", 2, deque.size());
        assertEquals("剩余第一个元素应该是 1", (Integer) 1, deque.get(0));
        assertEquals("剩余第二个元素应该是 2", (Integer) 2, deque.get(1));
    }

    /**
     * 边界条件测试：测试空队列的移除操作等边界情况
     */
    @Test
    public void testEdgeCases() {
        ArrayDeque<String> deque = new ArrayDeque<>();

        // 测试在空队列上添加单个元素后立即移除
        deque.addFirst("single");
        assertEquals("添加单个元素后移除应该返回该元素", "single", deque.removeFirst());
        assertTrue("移除后应该为空", deque.isEmpty());

        // 测试 addLast 后 removeFirst
        deque.addLast("last");
        assertEquals("addLast 后 removeFirst 应该返回该元素", "last", deque.removeFirst());

        // 测试 addFirst 后 removeLast
        deque.addFirst("first");
        assertEquals("addFirst 后 removeLast 应该返回该元素", "first", deque.removeLast());
    }
}
