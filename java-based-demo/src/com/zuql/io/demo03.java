package com.zuql.io;

import java.io.*;
import java.util.Scanner;

public class demo03 {
    public static void main(String[] args) throws Exception {
        /*
         * f3(GBK)
         *    a中
         *    61 d6 d0
         *
         * f4(UTF-8)
         *    a中
         *    61 e4 b8 ad
         */

        f("e:/abc/f3", "GBK", "abc中文喆镕");
        f("e:/abc/f4", "UTF-8", "abc中文喆镕");
    }

    private static void f(String path,String encoding,String s) throws Exception {
        // OSW--FOS--path
        // encoding
        OutputStreamWriter out =new OutputStreamWriter(new FileOutputStream(path), encoding);

        out.write(s);

        out.close();
    }

}
class Test2 {
    public static void main(String[] args) throws Exception {
        f("e:/abc/f3", "GBK");
        f("e:/abc/f4", "UTF-8");
    }

    private static void f(
            String path, String encoding) throws Exception {
        /*
         * ISR--FIS--path
         * encoding
         */
        InputStreamReader in =
                new InputStreamReader(
                        new FileInputStream(path), encoding);

        int c;
        while((c = in.read()) != -1) {
            System.out.print((char)c);
        }
        System.out.println("\n\n");

        in.close();
    }
}
    class Test3 {
        public static void main(String[] args) throws Exception {
                /*
                 * Unicode编码
                 * 20902个中文范围 \u4e00 到 \u9fa5
                 *
                 * f5(GBK)
                 * f6(UTF-8)
                 */
                f("e:/abc/f5", "GBK");
                f("e:/abc/f6", "UTF-8");
        }

        private static void f(String path, String encoding) throws Exception {
                OutputStreamWriter out =new OutputStreamWriter(new FileOutputStream(path), encoding);
                int count=0;
                for(char c='\u4e00'; c<='\u9fa5'; c++) {
                    out.write(c);
                    count++;
                    if(count==30) {//每30个，加换行
                        out.write('\n');
                        count=0;
                    }
                }

                out.close();
        }
    }
class Test4 {
    public static void main(String[] args) {
        /*
         * 原文件：
         * 目标文件：
         * 原文件编码：
         * 目标文件编码：
         */
        System.out.println("原文件：");
        String s1 = new Scanner(System.in).nextLine();
        File from = new File(s1);
        if(! from.isFile()) {
            System.out.println("不是文件");
            return;
        }
        System.out.println("目标文件：");
        String s2 = new Scanner(System.in).nextLine();
        File to = new File(s2);
        if(to.isDirectory()) {
            System.out.println("输入文件，不能是文件夹");
            return;
        }
        System.out.println("原文件编码：");
        String fromCharset = new Scanner(System.in).nextLine();
        System.out.println("目标文件编码：");
        String toCharset = new Scanner(System.in).nextLine();
        try {
            copy(from, to, fromCharset, toCharset);

        } catch (Exception e) {
            System.out.println("失败");
            e.printStackTrace();
        }
    }

    private static void copy(File from,File to,String fromCharset,String toCharset) throws Exception {
        /*
         * BR--ISR--FIS--from
         * PW--OSW--FOS--to
         */
        BufferedReader in =new BufferedReader(new InputStreamReader(new FileInputStream(from), fromCharset));

        PrintWriter out =new PrintWriter(new OutputStreamWriter(new FileOutputStream(to), toCharset));

        String line;
        while((line = in.readLine()) != null) {
            out.println(line);
        }

        out.close();
        in.close();

    }
}
