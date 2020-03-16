package Java的一些特殊的语言特性.MyHashTable;


/**
 * 对key进行Hash是为了寻找坐标，
 */
public class MyHashTable {
    /**
     * 散列表的初始化默认长度；
     * 设置小一点，可以清楚的看到扩容
     * 在实际使用中其实可以初始化传参来进行长度的设定，毕竟后面再扩容的话对性能有影响
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 4;

    /**
     * 扩容因子
     */
    private static final float LOAD_FACTOR = 0.75F;

    /**
     * 散列表数组
     */
    private MyEntry[] myEntryTables = new MyEntry[DEFAULT_INITIAL_CAPACITY];
    private int size = 0;   //散列表元素的个数
    private int use = 0;    //散列表使用的地址的个数

    /**
     * 哈希方法，根据这个方法可以确定元素位置
     * @param key
     * @return
     */
    private int myHash(int key) {
        return key % myEntryTables.length;
    }


    /**
     * 元素的添加/修改
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        int index = myHash(key);
        //初始化链表头 head
        if (myEntryTables[index] == null) {
            myEntryTables[index] = new MyEntry(-1, -1, null); //?? TODO
        }

        MyEntry entry = myEntryTables[index];
        if (entry.getNextMyEntry() == null) {
            //不存在值，向链表添加，有可能扩容，要用
            entry.setNextMyEntry(new MyEntry(key, value, null));
            size++;
            use++;
            //不存在值，说明是个未用过的地址，需要判断是否需要扩容；
            if (use >= myEntryTables.length * LOAD_FACTOR) {
                resize();
            }
        } else {
            for (entry = entry.getNextMyEntry(); entry.getNextMyEntry() != null; entry = entry.getNextMyEntry()) {
                if (entry.getKey() == key) {
                    entry.setValue(value);
                    return;
                }
            }
        }
        //非空，且，不存在相同的key，直接向链表头(也可以尾部)中添加元素；
        MyEntry temp = myEntryTables[index].getNextMyEntry();
        MyEntry newMyEntry = new MyEntry(key, value, temp);
        myEntryTables[index].setNextMyEntry(newMyEntry);
        size++;

    }

    public void remove(int key) {
        int index = myHash(key);
        MyEntry e = myEntryTables[index];
        MyEntry pre = myEntryTables[index];
        if (e != null && e.getNextMyEntry() != null) {
            for (e = e.getNextMyEntry(); e != null; pre = e, pre = e.getNextMyEntry()) {
                int k = e.getKey();
                if (k == key) {
                    pre.setNextMyEntry(e.getNextMyEntry());
                    size--;
                    return;
                }
            }
        }
    }

    public int get(int key) {
        int index = myHash(key);
        MyEntry e = myEntryTables[index];
        if (e != null && e.getNextMyEntry() != null) {
            for (e = e.getNextMyEntry(); e != null; e = e.getNextMyEntry()) {
                if (e.getKey() == key) return e.getValue();
            }
        }
        return -1;
    }

    public int getSize() {
        return size;
    }

    public static int getDefaultInitialCapacity() {
        return DEFAULT_INITIAL_CAPACITY;
    }

    public static float getLoadFactor() {
        return LOAD_FACTOR;
    }

    public int getLength() {
        return myEntryTables.length;
    }

    public int getUse() {
        return use;
    }

    private void resize() {
        int newLength = myEntryTables.length * 2;
        MyEntry[] oldTable = myEntryTables;
        myEntryTables = new MyEntry[newLength];
        use = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null && oldTable[i].getNextMyEntry() != null) {
                MyEntry e = oldTable[i];
                while (null != e.getNextMyEntry()) {
                    MyEntry next = e.getNextMyEntry();
                    //重新计算哈希值，放入新的地址
                    int index = myHash(next.getKey());
                    if (myEntryTables[index] == null) {
                        use++;
                        myEntryTables[index] = new MyEntry(-1, -1, null);
                    }
                    MyEntry temp = myEntryTables[index].getNextMyEntry();
                    MyEntry newEntry = new MyEntry(next.getKey(), next.getValue(), temp);
                    myEntryTables[index].setNextMyEntry(newEntry);

                    e = e.getNextMyEntry();

                }
            }
        }

    }

    public static void main(String[] args) {
        MyHashTable myHashTable = new MyHashTable();
        myHashTable.put(1, 10);
        myHashTable.put(2, 20);
        myHashTable.put(5, 50);
        System.out.println(myHashTable.getLength());
        myHashTable.put(3, 30);
        System.out.println(myHashTable.getLength());
        myHashTable.put(6, 60); //使用了5个地址
        myHashTable.put(7, 70); //使用了6个地址，为8的0.75倍，又需要扩容；
        System.out.println(myHashTable.getLength());

        System.out.println(myHashTable.get(1));
        System.out.println(myHashTable.get(3));
        System.out.println(myHashTable.get(5));
        System.out.println(myHashTable.get(6));




    }

}
