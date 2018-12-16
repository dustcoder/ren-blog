package com.ren.blog.util;/*
 *@Author:WuRen
 *@Description:
 *@date: 19:24 2018/12/16
 */

import java.util.ArrayList;
import java.util.List;

public class ConvertUtil {

    public static Integer[] stringArrayToIntegerArray(String[] stringArray){
        List<Integer> LString = new ArrayList<Integer>();
        Integer[] result = null;

        for (int i = 0; i < stringArray.length; i++) {
            LString.add(new Integer(stringArray[i]));
        }

        int size = LString.size();
        result = (Integer[]) LString.toArray(new Integer[size]);
        return result;
    }
}
