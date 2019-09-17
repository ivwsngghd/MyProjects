package Java的一些特殊的语言特性.MyBinarySearchTree;

import java.util.Comparator;

public class BinarySearchTree<AnyType> {
    private BinaryNode<AnyType> root;
    private Comparator<? super AnyType> cmp;

    public BinarySearchTree() {
        this(null);
    }

    //生成一个根为null的二叉查找树
    public BinarySearchTree(Comparator<? super AnyType> c) {
        root = null;
        cmp = c;

    }


    public void insert(AnyType x) {
        root = insert(x, root);
    }

    /**
     * @param x
     * @param t
     * @return
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {

        //递归查找的出口
        if (t == null) return new BinaryNode<>(x, null, null);

        int compareResult = myCompare(x, t.element);

        if (compareResult < 0) t.left = insert(x, t.left);
        else if (compareResult > 0) t.right = insert(x, t.right);
        else t.duplicate(); //相同的时候，频率加一；
        return t;

    }

    /**
     * 如果该二叉树构造时初始化了比较器，则使用比较器
     * 没有比较器则使用自带的比较方法
     *
     * @param lhs
     * @param rhs
     * @return
     */
    private int myCompare(AnyType lhs, AnyType rhs) {
        if (cmp != null) {
            return cmp.compare(lhs, rhs);
        } else {
            return ((Comparable) lhs).compareTo(rhs);
        }
    }

    /**
     * 通过与当前结点进行比较
     * 然后通过结果，小于当前结点则左递归
     * 否则右递归查找
     *
     * @param target
     * @param currentNode
     * @return
     */
    public boolean contains(AnyType target, BinaryNode<AnyType> currentNode) {
        if (currentNode == null) return false;  //没有找到的出口
        int compareResult = myCompare(target, currentNode.element);

        if (compareResult < 0) return contains(target, currentNode.left);
        else if (compareResult > 0) return contains(target, currentNode.right);
        else return true;

    }

    public void printTree() {
        if (root == null) System.out.println("Empty Tree!");
        else printTree(root);
    }

    private void printTree(BinaryNode<AnyType> typeBinaryNode) {
        if (typeBinaryNode != null) {
            printTree(typeBinaryNode.left);
            System.out.println(typeBinaryNode.element + "  出现频率：" + typeBinaryNode.frequency);
            printTree(typeBinaryNode.right);
        }
    }

    public static class BinaryNode<AnyType> {
        AnyType element;    //数据域
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;
        long frequency = 1; //默认存在数据才生成结点

        //constructors

        //构造空结点
        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        //构造一个结点(包括左儿子和右儿子)
        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        private void duplicate() {
            frequency++;
        }

    }

}

class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bTree = new BinarySearchTree<>();
        for (int i = 0; i < 100; i++) {
            bTree.insert(i);
        }
        bTree.insert(99);
        bTree.insert(99);
        bTree.insert(99);
        bTree.insert(99);
        bTree.printTree();
    }
}