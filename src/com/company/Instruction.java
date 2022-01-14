package com.company;

import java.util.Objects;

public class Instruction extends Statement {

    private int format;
    public int opcode;
    private int length;

    public Instruction( String mnemonic,int format,int opcode) {
        super(mnemonic);
        this.format = format;
        this.opcode = opcode;


    }

    public void setLength(int value) {
        if(value >= 1 && value <= 4)
            this.length = value;
    }
    public void setFormat(int value) {
        if(value >= 1 && value <= 6)
            this.format = value;
    }
    public int getFormat(){
        return format;
    }
    @Override
    public int getLength() {
        return length;
    }

    private String toBinaryString(int number,int size,boolean start){
        String str = Integer.toBinaryString(number);
        if(str.length()<size){
            int numberOfZeros = size -str.length();
            for(int i =0; i< numberOfZeros ; i++){
                if(start)
                    str = "0" + str ;
                else
                    str = str + "0";
            }
        }
        return str;
    }

    public String getObjCode(){
        String code;
        String op;
        int pc;
        int base;
        char adressingType;
        int target;
        String ni;
        String bp;
        String X;

        switch (format){
            case 1:
                op =  Integer.toBinaryString(opcode);
                code = op;
                break;
            case 2:
                op = Integer.toBinaryString(opcode);
                String r1 = toBinaryString( Register.get(getFirstOperand().charAt(0)),4,true);
                String r2;
                if(Objects.equals(getMnemonic(), "CLEAR") || Objects.equals(getMnemonic(), "TIXR")){
                    r2 = "0000";
                }else{
                    r2 = toBinaryString( Register.get(getSecondOperand().charAt(0)),4,true);

                }

                code = op + r1 + r2;
                break;
            case 3:
                boolean isNumber =false;
                boolean emptyOp = false;
                pc = location + getLength();
                base = Assembler.getBase();
                if(Objects.equals(getMnemonic(),"RSUB")) {
                    adressingType = '1';
                    emptyOp = true;
                }
                else
                    adressingType = getFirstOperand().charAt(0);
                if(emptyOp){
                    target=0;
                }
                else if(Character.isAlphabetic(adressingType)){
                    target = Assembler.symbolTable.get(getFirstOperand());
                }
                else{

                    String key = getFirstOperand().substring(1);
                    if(Character.isAlphabetic(key.charAt(0)))
                        target = Assembler.symbolTable.get(key);
                    else {
                        target = Integer.parseInt(key);
                        isNumber = true;
                    }
                }

                op = toBinaryString(opcode,6,false);
                op = op.substring(0,6);

                ni = switch (adressingType){
                    case '#' -> "10";
                    case '@' -> "01";
                    default -> "11";
                };
                X = switch (getSecondOperand()){
                    case "X" -> "1";
                    default -> "0";
                };


                int disp;

                if(isNumber){
                    disp = target;
                    bp ="00";
                }
                else if(target - pc <= 2047 || target - pc >= -2048) {
                    bp = "01";
                    disp = target - pc;
                }
                else if(target - base <= 4095) {
                    bp = "10";
                    disp = target - base;
                }
                else
                    throw new Error("Unreachable Address using Format 3");
                String dispString = toBinaryString(disp,12,true);

                if(dispString.length()>12){ //negative displacement 32 bits binary returned
                    dispString =dispString.substring(20); //removes excess 1's
                }
                code = op + ni + X + bp + "0" + dispString;

                break;
            case 4:

                adressingType = getFirstOperand().charAt(0);
                if(Character.isAlphabetic(adressingType)){
                    target = Assembler.symbolTable.get(getFirstOperand());
                }
                else{

                    String key = getFirstOperand().substring(1);
                    if(Character.isAlphabetic(key.charAt(0)))
                        target = Assembler.symbolTable.get(key);
                    else {
                        target = Integer.parseInt(key);
                    }
                }

                String targetBinary = toBinaryString(target,20,true);

                op = Integer.toBinaryString(opcode);
                if(op.length()<6){
                    int numberOfZeros = 6 -op.length();
                    for(int i =0; i< numberOfZeros ; i++){
                        op = op + "0";
                    }
                }
                op = op.substring(0,6);
                ni = switch (adressingType){
                    case '#' -> "10";
                    case '@' -> "01";
                    default -> "11";
                };
                X = switch (getSecondOperand()){
                    case "X" -> "1";
                    default -> "0";
                };

                code = op + ni + X + "001" + targetBinary;

                break;
            case 5:
                code = "/";
                //System.out.println(Integer.toBinaryString(opcode));
                break;
            case 6:
                code = "/";
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + format);
        }
        return code;
    }
}

