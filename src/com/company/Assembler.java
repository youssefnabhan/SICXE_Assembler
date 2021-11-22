package com.company;

import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Assembler {
    private SymbolTable symbolTable;

    public Assembler(){
        this.symbolTable = new SymbolTable();

    }
    public Statement parser(String line){
        String[] lineInput = line.split(" ");
        String label = lineInput[0];
        String mnemonic = lineInput[1];
        String operand = lineInput[2].split(",")[0];
        String operand2 = "";
        if(operand.contains(","))
            operand2 = lineInput[2].split(",")[1];

        return Statement.getInstance(label,mnemonic,operand,operand2);
    }


    public SymbolTable passOne(String filepath){
        try{
            Scanner scanner = new Scanner(new File(filepath));
            int locationCounter = 0;

            while(scanner.hasNextLine()){
                Statement statement = parser(scanner.nextLine());

                symbolTable.update(statement,locationCounter);
                locationCounter+= statement.getLength();
            }
            return symbolTable;


        }catch(Exception e){
            e.printStackTrace();
        }
        return symbolTable;

    }
}
