package com.imooc.interview.clone;

import java.io.*;

public class Dancer implements Cloneable,Serializable{
    private String name;

    private Dancer partner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dancer getPartner() {
        return partner;
    }

    public void setPartner(Dancer partner) {
        this.partner = partner;
    }

    public Dancer deepClone() throws IOException, ClassNotFoundException {
        //序列化，将内存中的对象序列化为字节数组
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        //反序列化，将字节数组转回到对象，同时完成深复制的任务
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (Dancer)ois.readObject();
    }


    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Dancer d1 = new Dancer();
        d1.setName("刘明");
        Dancer d2 = new Dancer();
        d2.setName("李敏霞");
        d1.setPartner(d2);
        System.out.println("Partner:" + d2.hashCode());
        //浅复制
        Dancer shallow = (Dancer)d1.clone();
        System.out.println("浅复制:" + shallow.getPartner().hashCode());
        //深复制
        Dancer deep = (Dancer)d1.deepClone();
        System.out.println("深复制:" + deep.getPartner().hashCode());
    }

}
