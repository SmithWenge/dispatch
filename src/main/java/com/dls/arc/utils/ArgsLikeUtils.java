package com.dls.arc.utils;

public class ArgsLikeUtils {
    /**
     *  模糊查询参数前后加 %
     */
    public static String likeArguments(String argsLike) {
        StringBuffer likeArgs = new StringBuffer("%").append(argsLike.trim()).append("%");
        return likeArgs.toString();
    }
}
