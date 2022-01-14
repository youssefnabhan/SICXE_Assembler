package com.company;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Assembler {
    public static ArrayList<Statement> codeList = new ArrayList<Statement>();
    public static SymbolTable symbolTable;
    public static int locationCounter = 0;
    public static String base;
    public static int getBase (){
        return Assembler.symbolTable.get(base);
    }
    public Assembler(){
        this.symbolTable = new SymbolTable();

    }
    public Statement parser(String line){
        String[] lineInput = line.split(" ");
        String label = lineInput[0];
        String mnemonic = lineInput[1];
        String operand;
        String operand2;
        operand2 = "";
        if(Objects.equals(mnemonic, "RSUB") ||Objects.equals(mnemonic, "FIRST") ) {
            operand = "";
        }
        else{
            operand = lineInput[2].split(",")[0];
            if(lineInput[2].contains(","))
            operand2 = lineInput[2].split(",")[1];

        }



        return Statement.getInstance(label,mnemonic,operand,operand2,locationCounter);
    }

    public void pass2 (){
       // System.out.println(Integer.parseInt("10010011000100000001000000110110",2));
       for(Statement s : codeList){
           if(!(Objects.equals(s.getObjCode(), ""))){
               String binaryCode = s.getObjCode();
               long code = Long.parseLong(binaryCode,2);
               String hexaCode = Long.toHexString(code).toUpperCase();
               System.out.println(binaryCode);
               if(s instanceof Instruction){
                   System.out.println(((Instruction) s).opcode);
               }
               System.out.println(s.getMnemonic() + " " + s.getFirstOperand());

           }
       }
    }
    public SymbolTable passOne(String filepath){
        try{
            Scanner scanner = new Scanner(new File(filepath));

            FileWriter writer = new FileWriter("src/com/company/out.txt");
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                Statement statement = parser(line);
                codeList.add(statement);
                symbolTable.update(statement,locationCounter);
                writer.write(Integer.toHexString(locationCounter) + " " + line + "\n");
                locationCounter+= statement.getLength();
            }
            writer.close();
            return symbolTable;


        }catch(Exception e){
            e.printStackTrace();
        }
        return symbolTable;

    }

}
