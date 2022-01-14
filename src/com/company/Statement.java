package com.company;

public abstract class Statement implements Cloneable {

    private String label;
    private String mnemonic;
    private String operand;
    private String operand2;
    private String objCode;
    public int location;

    public Statement(String mnemonic) {
        this.mnemonic = mnemonic;

    }

    public static Statement getInstance(String label, String mnemonic, String operand, String operand2,int location) {
        Statement s = null;

        try{

            if(InstructionSet.isInstruction(mnemonic)){
                s = InstructionSet.get(mnemonic);

                s = (Statement) s.clone();

                //s.objCode = s.getObjCode();
            }
            else if (DirectiveTable.isDirective(mnemonic)){
                s = DirectiveTable.get(mnemonic);

                s = (Statement) s.clone();

            }
            else{
                throw new Error("mnemonic Unknown!");
            }
            s.label = label;
            s.operand = operand;
            s.operand2 =operand2;
            s.location = location;
            if(s instanceof Directive){
                ((Directive) s).doOperation();
            }

            return s;
        }catch(Exception e){
            e.printStackTrace();
        }
        return s;
    }

    public abstract int getLength();

    public abstract String getObjCode();

    public String getLabel() {
        return label;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public String getFirstOperand() {
        return operand;
    }
    public String getSecondOperand(){ return operand2;}
}
