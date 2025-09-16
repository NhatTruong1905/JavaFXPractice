/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.utils;

import com.ndnt.services.BaseServices;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class FlyweightFactory {

    private static final Map<String, List> catchedData = new HashMap<>();

    public static <E> List<E> getDate(BaseServices s, String key) throws Exception {
        if (catchedData.containsKey(key) == true) {
            return catchedData.get(key);
        } else {
            List ls = s.list();
            catchedData.put(key, ls);
            return ls;
        }
    }
}
