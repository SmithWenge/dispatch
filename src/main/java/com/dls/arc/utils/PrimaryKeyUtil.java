package com.dls.arc.utils;

import java.util.UUID;

public class PrimaryKeyUtil {
    /**
     * 使用UUID算法生成主键
     *
     * @return UUID生成的字符串主键
     */
    public static String uuidPrimaryKey() {
        return UUID.randomUUID().toString();
    }
}
