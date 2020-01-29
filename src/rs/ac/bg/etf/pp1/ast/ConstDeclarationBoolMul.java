// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:10


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclarationBoolMul extends ConstDeclBoolMul {

    private ConstDeclBoolMul ConstDeclBoolMul;
    private ConstDeclBool ConstDeclBool;

    public ConstDeclarationBoolMul (ConstDeclBoolMul ConstDeclBoolMul, ConstDeclBool ConstDeclBool) {
        this.ConstDeclBoolMul=ConstDeclBoolMul;
        if(ConstDeclBoolMul!=null) ConstDeclBoolMul.setParent(this);
        this.ConstDeclBool=ConstDeclBool;
        if(ConstDeclBool!=null) ConstDeclBool.setParent(this);
    }

    public ConstDeclBoolMul getConstDeclBoolMul() {
        return ConstDeclBoolMul;
    }

    public void setConstDeclBoolMul(ConstDeclBoolMul ConstDeclBoolMul) {
        this.ConstDeclBoolMul=ConstDeclBoolMul;
    }

    public ConstDeclBool getConstDeclBool() {
        return ConstDeclBool;
    }

    public void setConstDeclBool(ConstDeclBool ConstDeclBool) {
        this.ConstDeclBool=ConstDeclBool;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclBoolMul!=null) ConstDeclBoolMul.accept(visitor);
        if(ConstDeclBool!=null) ConstDeclBool.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclBoolMul!=null) ConstDeclBoolMul.traverseTopDown(visitor);
        if(ConstDeclBool!=null) ConstDeclBool.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclBoolMul!=null) ConstDeclBoolMul.traverseBottomUp(visitor);
        if(ConstDeclBool!=null) ConstDeclBool.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclarationBoolMul(\n");

        if(ConstDeclBoolMul!=null)
            buffer.append(ConstDeclBoolMul.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclBool!=null)
            buffer.append(ConstDeclBool.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclarationBoolMul]");
        return buffer.toString();
    }
}
