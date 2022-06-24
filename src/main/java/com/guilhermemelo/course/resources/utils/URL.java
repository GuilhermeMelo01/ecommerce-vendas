package com.guilhermemelo.course.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static String decodeParam(String s){
        return URLDecoder.decode(s, StandardCharsets.UTF_8);
    }

    public static List<Integer> decodeIntList(String s){
         String [] vet = s.split(",");
         List<Integer> list = new ArrayList<>();
        for (int i = 0; i < vet.length; i++) {
            list.add(Integer.parseInt(vet[i]));
        }
        return list;

//        return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
    }
}
