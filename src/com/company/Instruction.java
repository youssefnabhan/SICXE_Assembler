package com.company;

public class Instruction extends Statement {

    private int format;
    private int opcode;

    public Instruction( String mnemonic,int format,int opcode) {
        super(mnemonic);
        this.format = format;
        this.opcode = opcode;


    }

    public void setFormat(int value) {
        if(value >= 1 && value <= 4)
            this.format = value;
    }

    @Override
    public int getLength() {
        return format;
    }
}
