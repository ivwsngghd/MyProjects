package leetcode;

public class _七大排序算法的实现 {
    /**
     * 冒泡排序
     * - 关于 AnyType extends Comparable<? super AnyType> 的含义为:
     * - 比较对象(泛型) 实现了 Comparable 接口
     * - 而Comparable接口里的泛型(即比较对象) 必须是它本身以及它的父类,(比较的必须是双方均有的属性)
     * - 即 练习的算法题.ChiefTechnology 类可以与他的父类Employee 比较 年龄，工资 等共有属性，
     * - 即可以比较类似这样的定义
     * - class 练习的算法题.ChiefTechnology extends 练习的算法题.Employee implements Comparable<练习的算法题.Employee>
     * - O(N^2)
     */
    public static <AnyType extends Comparable<? super AnyType>> void bubbleSort(AnyType[] a) {
        boolean adFinish;   //用于判断冒泡排序是否提前完成了
        AnyType tmp;

        for (int i = 0; i < a.length; i++) {
            adFinish = true;
            for (int j = 0; j < a.length - 1; j++) {
                if (a[j].compareTo(a[j + 1]) > 0) {     //升序排列
                    tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    adFinish = false;   //发生了交换
                }
            }
            if (adFinish) break;
        }


    }


    /**
     * 插入排序
     * 核心思想：
     * - 从左第二个位置开始遍历 标记为p
     * - 如果p比左侧小，则与左侧交换位置，直至大于左侧，或者等于0
     * <p>
     * 把元素分为已排序的和未排序的,
     * 每次从未排序的元素取出第一个，
     * 与已排序的元素从尾到头逐一比较，
     * 找到插入点，将之后的元素都往后移一位，腾出位置给该元素
     */
    public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a) {   //具有comparable接口的对象 而且不能是子类，上下界统一
        int j;
        for (int p = 1; p < a.length; p++) {
            AnyType tmp = a[p];
            for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }

    }

    /**
     * 选择排序
     */

    public static <AnyType extends Comparable<? super AnyType>> void selectionSort(AnyType[] a) {
        AnyType temp;
        int mark;
        for (int i = 0; i < a.length - 1; i++) {
            mark = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[mark]) < 0) {
                    mark = j;
                }
            }

            //交换
            temp = a[i];
            a[i] = a[mark];
            a[mark] = temp;

        }
    }

    /**
     * 计数排序
     * 需要大量的内存空间
     * 通过标记所有出现过的数字来进行排序
     */
    public static <AnyType extends Comparable<? super AnyType>> void countSort(AnyType[] a) {

    }

    private static <AnyType extends Comparable<? super AnyType>> AnyType minUtil(AnyType[] a) {
        int mark = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[mark]) < 0) {
                mark = i;
            }
        }
        return a[mark];
    }


    /**
     * 希尔排序（增量排序）
     *
     * @param a 对象数组
     *          可能有更好的增量来实现这个算法
     *          核心思想：
     *          - 假设间隙gap为数组长度的1/2，每次循环的均以这个gap来比较，比如a[0] 和 a[0+gap] 比较，小者排前
     *          - 不断缩小gap,直到gap为1时候，是普通的一趟冒泡排序
     */
    public static <AnyType extends Comparable<? super AnyType>> void shellSort(AnyType[] a) {
        //最糟糕的时间复杂度是O(N^2)
        for (int gap = a.length / 2; gap > 0; gap /= 2) //间隙gap必须大于0 不断缩小间隔，直至gap为1并停止
//        int j;
            for (int i = gap; i < a.length; i++) {      //根据gap决定开始的位置并遍历至数组末端
                AnyType tmp = a[i];
                if (tmp.compareTo(a[i - gap]) < 0) {  //如果比前gap个位的数值小，则交换位置
                    a[i] = a[i - gap];
                    a[i - gap] = tmp;
                }


                //源码写法：
                /*
                for (j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap)    //当前位置j如果小于前gap个位置
                    a[j] = a[j - gap];
                a[j] = tmp;
                */
            }
    }


    /**
     * 堆排序(优先队列)
     * <p>
     * 核心思想：
     * - 先利用对象建造一个堆
     * - 再用deleteMax后者deleteMin来获取当前堆中最小或者最大的值，并存放在数组中
     * - 通过下滤或上滤，重复进行，直到堆为空
     * - 关键在于堆的建造buildHeap 从根部开始，插入新的叶子，判断大小，大小不符合就进行上滤操作
     */

    public static <AnyType extends Comparable<? super AnyType>> void heapSort(AnyType[] a) {
        for (int i = a.length / 2 - 1; i >= 0; i--) { //上滤第一步直接到父节点a.length / 2 - 1
            percDown(a, i, a.length);
        }//buildHeap

        for (int i = a.length - 1; i > 0; i--) {
            swapReferences(a, 0, i);
            percDown(a, 0, i); //(数组,从0开始上滤,)
        }//对数组进行排序：不断取顶端元素，然后上滤
    }

    /**
     * 下滤
     */
    private static <AnyType extends Comparable<? super AnyType>> void percDown(AnyType[] a, int i, int n) {
        int child;
        AnyType tmp;
        //通过上滤尝试寻找可插入点
        for (tmp = a[i]; leftChild(i) < n; i = child) { //一直循环，直至左孩子的坐标小于最大长度 n
            child = leftChild(i);   //标记左孩子
            if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0) child++;    //这里判断的是如果左孩子比右孩子小，就指向右孩子
            if (tmp.compareTo(a[child]) < 0) a[i] = a[child];   //如果当前的根节点小于孩子节点(二者较大的那个)，
            else break;
        }
        a[i] = tmp;
    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    } //此处开始的位置是1

    /**
     * 归并排序
     * 一般用于把两个有序集合进行合并
     * 核心思想：
     * - 双指针依次比较插入
     */


    public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a) {
        AnyType[] tmpArray = (AnyType[]) new Comparable[a.length];

        mergeSort(a, tmpArray, 0, a.length - 1);

    }

    private static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a, AnyType[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    /**
     * @param a         用于比较的项目
     * @param tmpArray  用于存放比较结果
     * @param leftPos   最左子树
     * @param rightPos  mark
     * @param rightEnd  最右子树
     * @param <AnyType>
     */
    private static <AnyType extends Comparable<? super AnyType>> void merge(AnyType[] a, AnyType[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        //Main loop 归并排序：
        while (leftPos <= leftEnd && rightPos <= rightEnd)
            if (a[leftPos].compareTo(a[rightPos]) <= 0)
                tmpArray[tmpPos++] = a[leftPos++];
            else
                tmpArray[tmpPos++] = a[rightPos++];

        while (leftPos <= leftEnd)
            tmpArray[tmpPos++] = a[leftPos++];

        while (rightPos <= rightEnd)
            tmpArray[tmpPos++] = a[rightPos++];

        //把结果tmpArray 结果复制到 目的数组a里
        for (int i = 0; i < numElements; i++) {
            a[rightEnd] = tmpArray[rightEnd];
            rightEnd--;
        }

    }

    /**
     * 快排主例程
     *
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a) {
        if (a == null || a.length == 0 || a.length == 1) return;
        quickSort(a, 0, a.length - 1);
    }

    /**
     * 三数中值分割法，用于选取更好的枢纽做的预处理
     */
    private static <AnyType extends Comparable<? super AnyType>> AnyType median3(AnyType[] a, int left, int right) {
        int center = (left + right) / 2;           //选取数组中间的数值

        if (a[right].compareTo(a[left]) < 0) swapReferences(a, right, left);
        if (a[center].compareTo(a[left]) < 0) swapReferences(a, center, left);
        else if (a[right].compareTo(a[center]) < 0) swapReferences(a, right, center);

        swapReferences(a, left, center);
        return a[left];
    }

    /**
     * 快速排序 core
     * CUTOFF 数值为10
     * 核心思想:
     * 1) 左右指针以分割策略获得的枢纽为参照，左右两边往中间移动，期间以左小右大(和枢纽比较)做交换
     * 2) 当左右指针相交，就把二者较大，即i，和数组末尾right-1(枢纽)交换
     * <p>
     * 枢纽，这里默认选取第一个
     */
    private static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a, int left, int right) {
        //开始分割：
        if (left > right) return;    //递归出口
//        AnyType base = median3(a, left, right);    //base作为枢纽，而且此时a[left]<=a[center](base)<=a[right]
        AnyType base = a[left];    //base作为枢纽，而且此时a[left]<=a[center](base)<=a[right]
        int i = left, j = right;
        while (i != j) {
            //先选大
            while (a[j].compareTo(base) >= 0 && i < j) j--; // >= 0 之所以允许相同是为了去重，也因此该算法不稳定
            //后选小
            while (a[i].compareTo(base) <= 0 && i < j) i++; // 最后结果是i一定会和j重合(不会交叉)，而此时这个位置，正是枢纽应该存储的位置；
            if (i < j) swapReferences(a, i, j);
        }

        //此时i和j已经重合(枢纽应该存放的位置)，枢纽更新，而此时i位置的值必定小于等于枢纽值(因为先筛大的原因，先从右开始往左移动的原因，反之则会是必定大于等于枢纽值)
        a[left] = a[i];//这个值必定小于等于枢纽(右移中...)
        System.out.println(a[i].compareTo(base));
        a[i] = base;    // i的这个位置是枢纽，以下对其两边进行余下的快排

        quickSort(a, left, i - 1);
        quickSort(a, i + 1, right);
    }

    private static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a, int left, int right) {
        int length = right - left + 1; //length 不是下标，因此要+1
        AnyType temp;
        for (int i = left + 1; i < length; i++) {
            //插入排序
            temp = a[i];
            int j = i;
            for (; j > 0 && temp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = temp;
        }
    }

    /**
     * 该方法用于交换AnyType数组里面的left、right下标对应的元素
     *
     * @param a
     * @param left
     * @param right
     * @param <AnyType>
     */
    final private static <AnyType extends Comparable<? super AnyType>> void swapReferences(AnyType a[], int left, int right) {
        AnyType temp;
        temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }


    public static void main(String[] args) {
//        练习的算法题.Employee employees[] = new 练习的算法题.Employee[]{new 练习的算法题.Employee(19), new 练习的算法题.Employee(18), new 练习的算法题.ChiefTechnology(13), new 练习的算法题.Employee(12), new 练习的算法题.ChiefTechnology(30), new 练习的算法题.Employee(22), new 练习的算法题.ChiefTechnology(14)};
//        练习的算法题.Employee employees[] = new 练习的算法题.Employee[]{new 练习的算法题.Employee(19), new 练习的算法题.Employee(18), new 练习的算法题.ChiefTechnology(13), new 练习的算法题.Employee(12), new 练习的算法题.ChiefTechnology(30), new 练习的算法题.Employee(22), new 练习的算法题.ChiefTechnology(14), new 练习的算法题.Employee(19), new 练习的算法题.Employee(18), new 练习的算法题.ChiefTechnology(13), new 练习的算法题.Employee(12), new 练习的算法题.ChiefTechnology(30), new 练习的算法题.Employee(22), new 练习的算法题.ChiefTechnology(14), new 练习的算法题.Employee(19), new 练习的算法题.Employee(18), new 练习的算法题.ChiefTechnology(13), new 练习的算法题.Employee(12), new 练习的算法题.ChiefTechnology(30), new 练习的算法题.Employee(22), new 练习的算法题.ChiefTechnology(14)};
        Employee employees[] = new Employee[]{new Employee(1), new Employee(3), new ChiefTechnology(2), new Employee(4), new ChiefTechnology(7), new Employee(5), new ChiefTechnology(6), new Employee(8), new Employee(10), new ChiefTechnology(9), new Employee(11), new ChiefTechnology(12), new Employee(13), new ChiefTechnology(14), new Employee(16), new Employee(15), new ChiefTechnology(17), new Employee(18), new ChiefTechnology(20), new Employee(19), new ChiefTechnology(21)};

        for (int i = 0; i < employees.length; i++) {
            System.out.println(employees[i]);
        }

        System.out.println();
//        countSort(employees);
//        shellSort(employees);
//        bubbleSort(employees);
//        insertionSort(employees);
//        selectionSort(employees);
//        heapSort(employees);
//        mergeSort(employees);
        quickSort(employees);


        System.out.println("排序后:");
        for (int i = 0; i < employees.length; i++) {
            System.out.println(employees[i]);
        }

    }


}

class Employee implements Comparable<Employee> {
    private Integer age;
    private Integer salary;
    private String name;

    @Override
    public int compareTo(Employee o) {
        return age.compareTo(o.getAge());   //升序
    }

    public Employee() {

    }

    public Employee(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "雇员年龄为 : " + age;

    }
}

class ChiefTechnology extends Employee implements Comparable<Employee> {
    private int priority;

    public ChiefTechnology() {
        super();
    }

    public ChiefTechnology(int age) {
        super(age);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void printName() {
        System.out.println(this.getName());
    }

    @Override
    public int compareTo(Employee o) {
        return super.compareTo(o);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

