package com.company;

import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

public class Directive extends Statement{
    Boolean hasLength;
    Boolean hasOperation =false;
    public Directive( String mnemonic, boolean hasLength) {
        super(mnemonic);
        this.hasLength = hasLength;
    }
    public Directive( String mnemonic, boolean hasLength, boolean hasOperation) {
        super(mnemonic);
        this.hasLength = hasLength;
        this.hasOperation = hasOperation;
    }

    @Override
    public int getLength() {
        // calculate size if exists from operand
        if (hasLength) {
            switch(this.getMnemonic()){
                case "RESW":
                    return Integer.parseInt(getFirstOperand())*3;
                case "RESB":
                    return Integer.parseInt(getFirstOperand());
                case "BYTE":
                    return calculateSize(getFirstOperand());
                default://'WORD'
                    return 3;
            }
        }
        else
            return 0;
    }

    public void doOperation() {
        if(hasOperation){
            if(getMnemonic() == "BASE")
                Assembler.base = getFirstOperand();
        }
    }

    @Override
    public String getObjCode() {
        if(getMnemonic() == "WORD" || getMnemonic() == "BYTE"){

            String operand  = getFirstOperand();
            char type = operand.charAt(0);
            StringBuilder data  = new StringBuilder(operand.substring(2, operand.length() - 1)); //Clipping '' and C or X
            if(type == 'X'){
                int decimalVal = Integer.parseInt(data.toString(),16);
                String binaryString = Integer.toBinaryString(decimalVal);

                if(binaryString.length() < 8){
                    int numberOfZeros = 8-binaryString.length();
                    for(int i =0;i<numberOfZeros;i++){
                        binaryString = "0" + binaryString;
                    }
                }
                data = new StringBuilder(binaryString);
            }

            else if(type == 'C'){
                byte[] ascii = data.toString().getBytes(StandardCharsets.US_ASCII);
                StringBuilder code = new StringBuilder();
                for (byte b : ascii) {
                    String charBinaryStr= Integer.toBinaryString(b);
                    if(charBinaryStr.length() < 8){
                        int numberOfZeros = 8-charBinaryStr.length();
                        for(int i =0;i<numberOfZeros;i++){
                            charBinaryStr = "0" + charBinaryStr;
                        }
                    }
                    code.append(charBinaryStr);


                }
                data = code;
            }
            if(getMnemonic() == "WORD"){
                data = new StringBuilder(data.substring(0, Integer.min(5, data.length() - 1)));
                if(data.length()<6){
                    int numberOfZeros = 6-data.length();
                    for(int i =0;i<numberOfZeros;i++){
                        data.insert(0, "0");
                    }
                }
            }
            return data.toString();

        }

        else
            return "";
    }




    private int calculateSize(String operand){
        if(operand.charAt(0) == 'C') // C'EOF' 3 ASCII CHARS
            return operand.length()-3;
        else    //X'3F' HEXA CONSTANT
            return operand.length()-3/2;
    }
}
