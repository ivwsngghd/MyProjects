package _唯品会面试;

import java.util.*;


/**
 * 主要思路为，实现一个优先队列，里面根据Key_Value的使用频率use_frequency 来进行优先级排序
 * Key_Value 是自己封装的一个键值对，里面增加了 use_frequency 变量 ，每次进行一次get()方法，就加一，并尝试更新至优先队列里
 * LFUCache有一个HashMap，用于存放数据；
 * 当前如果优先队列中没有对应的数据，则LFUCache不会返回值，只会令对应的值增加使用次数，直到更新直优先队列里；
 */
public class Main {
    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);

        lfuCache.add("key2", new Integer(2));
        lfuCache.add("key3", new Integer(3));
        lfuCache.add("key4", new Integer(4));
        System.out.println(lfuCache.getValue("key3"));
        System.out.println(lfuCache.getValue("key4"));
        System.out.println(lfuCache.getValue("key2"));
        lfuCache.add("key5", new Integer(5));
        lfuCache.add("key6", new Integer(6));
        lfuCache.add("key7", new Integer(7));
        System.out.println(lfuCache.getValue("key5"));
        System.out.println(lfuCache.getValue("key6"));
        System.out.println(lfuCache.getValue("key7"));

        System.out.println(lfuCache.getValue("key5"));
        System.out.println(lfuCache.getValue("key6"));
        System.out.println(lfuCache.getValue("key7"));

        System.out.println(lfuCache.getValue("key5"));
        System.out.println(lfuCache.getValue("key6"));
        System.out.println(lfuCache.getValue("key7"));

        System.out.println(lfuCache.getValue("key5"));
        System.out.println(lfuCache.getValue("key6"));
        System.out.println(lfuCache.getValue("key7"));

        System.out.println(lfuCache.getValue("key3"));
        System.out.println(lfuCache.getValue("key4"));
        System.out.println(lfuCache.getValue("key2"));

        System.out.println(lfuCache.getValue("key3"));
        System.out.println(lfuCache.getValue("key4"));
        System.out.println(lfuCache.getValue("key2"));

        System.out.println(lfuCache.getValue("key3"));
        System.out.println(lfuCache.getValue("key4"));
        System.out.println(lfuCache.getValue("key2"));

        System.out.println(lfuCache.getValue("key3"));
        System.out.println(lfuCache.getValue("key4"));
        System.out.println(lfuCache.getValue("key2"));
        System.out.println(lfuCache.getValue("key3"));
        System.out.println(lfuCache.getValue("key4"));
        System.out.println(lfuCache.getValue("key2"));
        System.out.println(lfuCache.getValue("key3"));
        System.out.println(lfuCache.getValue("key4"));
        System.out.println(lfuCache.getValue("key2"));

        for (int i = 0; i < 15; i++) {
            System.out.println(lfuCache.getValue("key6"));
        }

    }
}

class LFUCache<Key, Value> {
    private int k;  //记录可使用的缓存数量
    PriorityQueue<Key_Value> cacheQueue;    //优先队列，用于记录缓存
    Map<Key, Key_Value> hashMap;                //用于存放数据

    /**
     * 构造对应的HashMap,和优先队列；
     *
     * @param k 缓存大小
     */
    public LFUCache(int k) {
        this.k = k;
        cacheQueue = new PriorityQueue<Key_Value>((Comparator<Key_Value>) (o1, o2) -> o1.use_frequence - o2.use_frequence);
        hashMap = new HashMap();
    }

    public void add(Key key, Value value) {
        hashMap.put(key, new Key_Value(key, value));
        System.out.println("queueSize:" + cacheQueue.size());
        if (cacheQueue.size() < k) cacheQueue.add(new Key_Value(key, value));        //小于允许缓存数量直接加入
        else {
            Key_Value key_value = hashMap.get(key);
            //如果使用频率大于优先队列使用频率最低的那个，就替换掉
            if (key_value.use_frequence > cacheQueue.peek().use_frequence) {
                cacheQueue.poll();
                cacheQueue.add(key_value);
            }

        }

    }

    public Value getValue(Key key) {
        Iterator<Key_Value> key_valueIterator = cacheQueue.iterator();
        while (key_valueIterator.hasNext()) {
            Key_Value keyValue = key_valueIterator.next();
            if (keyValue.key.equals(key)) {
                keyValue.use_frequence++;               //每获取一次都增加一次使用频率,并且更新队列
//                cacheQueue.remove(hashMap.get(key));    //删除旧值
                cacheQueue.remove(keyValue);    //删除旧值
                cacheQueue.add(keyValue);               //更新,优先队列的优先顺序基于删除和增加；
                hashMap.put(key,keyValue);
                return (Value) keyValue.value;
            }
        }

        //如果没有找到，则尝试更新直优先队列
        Key_Value key_value = hashMap.get(key);
        key_value.use_frequence++;
        if (key_value.use_frequence > cacheQueue.peek().use_frequence) {
            cacheQueue.remove(key_value);
            cacheQueue.add(key_value);
        }


        return null;
    }

    /**
     * 封装键值对，记录对应的使用频率used_frequence;
     *
     * @param <Key>
     * @param <Value>
     */
    private static class Key_Value<Key, Value> {
        Key key;
        Value value;
        int use_frequence;

        Key_Value(Key key, Value value) {
            this.key = key;
            this.value = value;
            use_frequence = 0;
        }

    }

    //优先队列通过堆来实现；

}

/*
* PriorityQueue队列是基于堆排序的不断更新排序的，没错，它是不断更新排序的。
* 但是前提是要插入(删除)数据，如果仅仅是修改已经稳定队列的值或内容，而不进行插入或者删除，
* 那么，这个顺序是不会变的。
* */


/*
* 目前问题存在，先加进去的常用的次数太多，导致新加进来的数据几乎没有任何使用空间；use_frequency数，无法超过老缓存；
*
* */