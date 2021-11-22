package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class Main {

    public static void main(String[] args) throws IOException {
        Assembler assembler1 = new Assembler();
        DirectiveTable.load();
        InstructionSet.load();
        SymbolTable symbTable = assembler1.passOne("src/com/company/in.txt");
        FileWriter writer = new FileWriter("src/com/company/symbTable.txt");
        writer.write(symbTable.toString());
        writer.close();
    }
}
