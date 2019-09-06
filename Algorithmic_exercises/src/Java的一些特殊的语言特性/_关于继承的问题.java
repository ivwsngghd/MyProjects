package Java的一些特殊的语言特性;


public class _关于继承的问题 {
    public static void main(String[] args) {
        Animal animal = new Dog(2);
        System.out.println(((Dog) animal).id);
        System.out.println(animal.id);
    }
}

class Animal{
    public int id;

    Animal(){

    }
}

class Dog extends  Animal{
    public int id;

    Dog(int x){
        id = x;
    }

    public int getId() {
        return id;
    }
}