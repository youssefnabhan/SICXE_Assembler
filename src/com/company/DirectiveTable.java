package com.company;

import java.util.Hashtable;

public final class DirectiveTable {
    private static Hashtable<String,Directive> table = new Hashtable<String,Directive>();

    private DirectiveTable(){}


    public static boolean isDirective(String mnemonic){
        return table.containsKey(mnemonic);
    }
    public static Directive get(String mnemonic){
        return table.get(mnemonic);
    }

    public static void load(){
        table.put("BYTE", new Directive("BYTE",true));
        table.put("RESB", new Directive("RESB",true));
        table.put("WORD", new Directive("WORD",true));
        table.put("RESW", new Directive("RESW",true));
        table.put("START", new Directive("START",false));
        table.put("BASE", new Directive("BASE",false));
        table.put("NOBASE", new Directive("NOBASE",false));
        table.put("END", new Directive("END",false));
        table.put("LTORG", new Directive("LTORG",false));
        table.put("EXTREF", new Directive("EXTREF",false));
        table.put("EXTDEF", new Directive("EXTDEF",false));
        table.put("ORG", new Directive("ORG",false));
        table.put("EQU", new Directive("EQU",false));
        table.put("CSECT", new Directive("CSECT",false));
    }
}
