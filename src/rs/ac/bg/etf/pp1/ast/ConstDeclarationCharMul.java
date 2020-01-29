// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:10


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclarationCharMul extends ConstDeclCharMul {

    private ConstDeclCharMul ConstDeclCharMul;
    private ConstDeclChar ConstDeclChar;

    public ConstDeclarationCharMul (ConstDeclCharMul ConstDeclCharMul, ConstDeclChar ConstDeclChar) {
        this.ConstDeclCharMul=ConstDeclCharMul;
        if(ConstDeclCharMul!=null) ConstDeclCharMul.setParent(this);
        this.ConstDeclChar=ConstDeclChar;
        if(ConstDeclChar!=null) ConstDeclChar.setParent(this);
    }

    public ConstDeclCharMul getConstDeclCharMul() {
        return ConstDeclCharMul;
    }

    public void setConstDeclCharMul(ConstDeclCharMul ConstDeclCharMul) {
        this.ConstDeclCharMul=ConstDeclCharMul;
    }

    public ConstDeclChar getConstDeclChar() {
        return ConstDeclChar;
    }

    public void setConstDeclChar(ConstDeclChar ConstDeclChar) {
        this.ConstDeclChar=ConstDeclChar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclCharMul!=null) ConstDeclCharMul.accept(visitor);
        if(ConstDeclChar!=null) ConstDeclChar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclCharMul!=null) ConstDeclCharMul.traverseTopDown(visitor);
        if(ConstDeclChar!=null) ConstDeclChar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclCharMul!=null) ConstDeclCharMul.traverseBottomUp(visitor);
        if(ConstDeclChar!=null) ConstDeclChar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclarationCharMul(\n");

        if(ConstDeclCharMul!=null)
            buffer.append(ConstDeclCharMul.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclChar!=null)
            buffer.append(ConstDeclChar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclarationCharMul]");
        return buffer.toString();
    }
}
