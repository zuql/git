package com.zuql.io;

import java.io.File;

public class demo01 {
    public static void main(String[] args) {
        String path;

        //path = "D:/home/java/eclipse/eclipse.exe";//存在的文件路径
        //path = "D:\\home\\java\\eclipse";//存在的目录路径
        path = "e:\\sd";//不存在的路径

        File f = new File(path);
        System.out.println(f.exists());//是否存在
        System.out.println(f.isFile());//是否是文件
        System.out.println(f.isDirectory());//是否是文件夹
        System.out.println(f.getName());//文件名
        System.out.println(f.getParent());//父文件夹
        System.out.println(f.length());//文件大小，文件夹无效
        System.out.println(f.lastModified());//最后修改时间

    }

}
