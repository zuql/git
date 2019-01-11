package com.zuql.io.serialization;

import java.io.*;

public class Test1 {
    public static void main(String[] args) throws Exception {
        Student s = new Student(9527, "唐伯虎", "男", 19);

        /*
         * 把学生对象s，序列化输出保存到 d:/abc/f2
         *
         * OOS--FOS--f2
         */
        File file1=new File("e:/abc");
        file1.delete();
        file1.mkdirs();
        File file=new File("e:/abc/f2");

        file.createNewFile();
        ObjectOutputStream out =new ObjectOutputStream(new FileOutputStream(file));

        out.writeObject(s);

        out.close();
    }

}

class Test2 {
    public static void main(String[] args) throws Exception {
        /*
         * OIS--FIS--f2
         */
        ObjectInputStream in =new ObjectInputStream(new FileInputStream("e:/abc/f2"));

        Student s = (Student) in.readObject();
        System.out.println(s);

        in.close();


    }
}
