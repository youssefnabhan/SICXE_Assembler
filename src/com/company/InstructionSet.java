package com.company;

import java.util.Hashtable;

public final class InstructionSet {
    //Fields*********************************************************
    private static Hashtable<String,Instruction> set = new Hashtable<String,Instruction>();

    //Constructor*********************************************************
    private InstructionSet(){} //Singleton


    //*************methods*******************


    public static boolean isInstruction(String mnemonic){
        if(mnemonic.charAt(0) == '+'){
            mnemonic = mnemonic.split("\\+")[1];
        }
        return set.containsKey(mnemonic);
    }


    public static Instruction get(String mnemonic){
        if(mnemonic.charAt(0) == '+'){
            mnemonic = mnemonic.split("\\+")[1];
            Instruction instruction = set.get(mnemonic);
            instruction.setFormat(4);
            return instruction;
        }

        return set.get(mnemonic);
    }


    public static void load(){
        set.put("ADD",new Instruction("ADD",3,0x18));
        set.put("AND",new Instruction("AND",3,0x40));
        set.put("ADDR",new Instruction("ADDR",2,0x90));
        set.put("FIX",new Instruction("FIX",1,0xC4));
        set.put("ADDF",new Instruction("ADDF",3,0x58));
        set.put("CLEAR",new Instruction("CLEAR",2,0xB4));
        set.put("COMP",new Instruction("COMP",3,0x28));
        set.put("COMPF",new Instruction("COMPF",3,0x88));
        set.put("COMPR",new Instruction("COMPR",2,0xA0));
        set.put("DIV",new Instruction("DIV",3,0x24));
        set.put("DIVF",new Instruction("DIVF",3,0x64));
        set.put("DIVR",new Instruction("DIVR",2,0x9C));
        set.put("FLOAT",new Instruction("FLOAT",1,0xC0));
        set.put("HIO",new Instruction("HIO",1,0xF4));
        set.put("J",new Instruction("J",3,0x3C));
        set.put("JEQ",new Instruction("JEQ",3,0x30));
        set.put("JGT",new Instruction("JGT",3,0x34));
        set.put("JLT",new Instruction("JLT",3,0x38));
        set.put("JSUB",new Instruction("JSUB",3,0x48));
        set.put("LDA",new Instruction("LDA",3,0x00));
        set.put("LDB",new Instruction("LDB",3,0x68));
        set.put("LDCH",new Instruction("LDCH",3,0x50));
        set.put("LDF",new Instruction("LDF",3,0x70));
        set.put("LDL",new Instruction("LDL",3,0x08));
        set.put("LDS",new Instruction("LDS",3,0x6C));
        set.put("LDT",new Instruction("LDT",3,0x74));
        set.put("LDX",new Instruction("LDX",3,0x04));
        set.put("LPS",new Instruction("LPS",3,0xD0));
        set.put("MUL",new Instruction("MUL",3,0x20));
        set.put("MULF",new Instruction("MULF",3,0x60));
        set.put("MULR",new Instruction("MULR",2,0x98));
        set.put("NORM",new Instruction("NORM",1,0xC8));
        set.put("OR",new Instruction("OR",3,0x44));
        set.put("RD",new Instruction("RD",3,0xD8));
        set.put("RMO",new Instruction("RMO",2,0xAC));
        set.put("RSUB",new Instruction("RSUB",3,0x4C));
        set.put("SHIFTL",new Instruction("SHIFTL",2,0xA4));
        set.put("SHIFTR",new Instruction("SHIFTR",2,0xA8));
        set.put("SIO",new Instruction("SIO",1,0xF0));
        set.put("SSK",new Instruction("SSK",3,0xEC));
        set.put("STA",new Instruction("STA",3,0x0C));
        set.put("STB",new Instruction("STB",3,0x78));
        set.put("STCH",new Instruction("STCH",3,0x54));
        set.put("STF",new Instruction("STF",3,0x80));
        set.put("STI",new Instruction("STI",3,0xD4));
        set.put("STL",new Instruction("STL",3,0x14));
        set.put("STS",new Instruction("STS",3,0x7C));
        set.put("STSW",new Instruction("STSW",3,0xE8));
        set.put("STT",new Instruction("STT",3,0x84));
        set.put("STX",new Instruction("STX",3,0x10));
        set.put("SUB",new Instruction("SUB",3,0x1C));
        set.put("SUBF",new Instruction("SUBF",3,0x5C));
        set.put("SUBR",new Instruction("SUBR",2,0x94));
        set.put("SVC",new Instruction("SVC",2,0xB0));
        set.put("TD",new Instruction("TD",3,0xE0));
        set.put("TIO",new Instruction("TIO",1,0xF8));
        set.put("TIX",new Instruction("TIX",3,0x2C));
        set.put("TIXR",new Instruction("TIXR",2,0xB8));
        set.put("WD",new Instruction("WD",3,0xDC));




    }
}
