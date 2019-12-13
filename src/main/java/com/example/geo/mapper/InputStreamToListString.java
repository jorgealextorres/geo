package com.example.geo.mapper;

import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputStreamToListString {
    public static List<String> getListString(InputStream is){
        List<String> list = new ArrayList<>();

        Scanner sc = new Scanner(is);

        while(sc.hasNext()){
            list.add(sc.nextLine());
        }

        if(CollectionUtils.isEmpty(list)){
            list = null;
        }

        return list;
    }
}
