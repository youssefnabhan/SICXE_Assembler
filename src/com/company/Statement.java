package com.company;

public abstract class Statement {

    private String label;
    private String mnemonic;
    private String operand;
    private String operand2;
    private String objCode;

    public Statement(String mnemonic) {
        this.mnemonic = mnemonic;
        this.label = null;
        this.operand = null;
        this.operand2 = null;
    }


    public static Statement getInstance(String label, String mnemonic, String operand, String operand2) {
        Statement s = null;
        if(InstructionSet.isInstruction(mnemonic)){
            s = InstructionSet.get(mnemonic);
        }
        else if (DirectiveTable.isDirective(mnemonic)){
            s = DirectiveTable.get(mnemonic);
        }
        else{
            throw new Error("mnemonic Unknown!");
        }
        s.label = label;
        s.operand = operand;
        s.operand2 =operand2;

        return s;
    }

    public abstract int getLength();

    public String getLabel() {
        return label;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public String getOperand() {
        return operand;
    }
}
