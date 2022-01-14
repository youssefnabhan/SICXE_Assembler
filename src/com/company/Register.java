package com.company;

import java.util.Hashtable;

public class Register {
    static private Hashtable<Character,Integer> registerTable = new Hashtable<Character,Integer>();

    public static void load(){
        registerTable.put('A',0);
        registerTable.put('X',1);
        registerTable.put('L',2);
        registerTable.put('B',3);
        registerTable.put('S',4);
        registerTable.put('T',5);
        registerTable.put('F',6);

    }

    public static int get(Character c) {
        return registerTable.get(c);
    }

}
