package com.util;

import java.util.ArrayList;
import java.util.List;

public class ListOneFieldUtil {

    public static <T> List<T> getFiledsFromList(List<?> list,GetFieldHander hander){
        List<T> result = new ArrayList<T>();
        if(list != null)for (Object object: list){
            T t = (T) hander.getField(object);
            result.add(t);
        }
        return result;
    }

    public static <T> void setFiledsInList(List<T> list, SetFieldHander hander){
        if(list != null)for (T object: list){
            hander.setField(object);
        }
    }
}
