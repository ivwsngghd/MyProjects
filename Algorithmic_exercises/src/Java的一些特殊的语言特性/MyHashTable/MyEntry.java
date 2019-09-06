package Java的一些特殊的语言特性.MyHashTable;

/**
 * MyHashTable里的基本元素
 */
public class MyEntry {
    private int key;
    private int value;
    private MyEntry nextMyEntry;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public MyEntry getNextMyEntry() {
        return nextMyEntry;
    }

    public void setNextMyEntry(MyEntry nextMyEntry) {
        this.nextMyEntry = nextMyEntry;
    }

    public MyEntry(int key, int value, MyEntry nextMyEntry) {
        this.key = key;
        this.value = value;
        this.nextMyEntry = nextMyEntry;
    }
}
