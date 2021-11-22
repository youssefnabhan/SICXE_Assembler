package com.company;

public class Directive extends Statement{
    Boolean hasLength;
    public Directive( String mnemonic, boolean hasLength) {
        super(mnemonic);
        this.hasLength = hasLength;
    }

    @Override
    public int getLength() {
        // calculate size if exists from operand
        if (hasLength) {
            switch(this.getMnemonic()){
                case "RESW":
                    return Integer.parseInt(getOperand())*3;
                case "RESB":
                    return Integer.parseInt(getOperand());
                case "BYTE":
                    return calculateSize(getOperand());
                default://'WORD'
                    return 3;
            }
        }
        else
            return 0;
    }

    private int calculateSize(String operand){
        if(operand.charAt(0) == 'C') // C'EOF' 3 ASCII CHARS
            return operand.length()-3;
        else    //X'3F' HEXA CONSTANT
            return operand.length()/2;
    }
}
