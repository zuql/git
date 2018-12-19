package com.zuql.util;


import sun.misc.BASE64Decoder;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 集合工具类
 * @auth xiaolong
 * @date 2016-12-19 11:33:27
 */
public class CollectionUtils {


    protected final static Logger log = Logger.getLogger(new Object() {
       public String getClassName() {
            String clazzName = this.getClass().getName();
           return clazzName.substring(0, clazzName.lastIndexOf('$'));
        }
    }.getClassName());
    //todo:
    //private static final Logger LOGGER = LoggerFactory.getLogger(CollectionUtils.class);


    /**
     * 耗时测试
     * @param message
     * @param c
     */
    public static void timeConsumingTest(String message, Consumer<Object> c){
        //log.info("====================》"+message+":start");
        long start=System.currentTimeMillis();
        c.accept(new Object());
       long end=System.currentTimeMillis();
        if(end-start>0){
            log.info("====================》"+message+":end,耗时:"+(end-start));
        }

    }

}
