package com.company;

import java.util.Hashtable;

public class SymbolTable {
    private Hashtable<String,Integer> table = new Hashtable<String,Integer>();

    public void update(Statement statement,int locationCounter){
        if(statement.getLabel() != ""){

            String label = statement.getLabel();
            if(table.containsKey(label)){
                table.replace(label,locationCounter);
            }
            else
                table.put(label,locationCounter);
        }

    }
    public Integer get(String key){
        return table.get(key);
    }
    public void put(String key,Integer val){
        table.put(key,val);
    }
    public String toString(){
        return table.toString();
    }
    
    public void printTableHexa() {
        for (String key:table.keySet()) {
            System.out.println(key + " " + Integer.toHexString(table.get(key)));
        }
    }

}
