// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:10


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclrBool extends ConstDecl {

    private Type Type;
    private ConstDeclBool ConstDeclBool;
    private ConstDeclBoolMul ConstDeclBoolMul;

    public ConstDeclrBool (Type Type, ConstDeclBool ConstDeclBool, ConstDeclBoolMul ConstDeclBoolMul) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ConstDeclBool=ConstDeclBool;
        if(ConstDeclBool!=null) ConstDeclBool.setParent(this);
        this.ConstDeclBoolMul=ConstDeclBoolMul;
        if(ConstDeclBoolMul!=null) ConstDeclBoolMul.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ConstDeclBool getConstDeclBool() {
        return ConstDeclBool;
    }

    public void setConstDeclBool(ConstDeclBool ConstDeclBool) {
        this.ConstDeclBool=ConstDeclBool;
    }

    public ConstDeclBoolMul getConstDeclBoolMul() {
        return ConstDeclBoolMul;
    }

    public void setConstDeclBoolMul(ConstDeclBoolMul ConstDeclBoolMul) {
        this.ConstDeclBoolMul=ConstDeclBoolMul;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ConstDeclBool!=null) ConstDeclBool.accept(visitor);
        if(ConstDeclBoolMul!=null) ConstDeclBoolMul.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ConstDeclBool!=null) ConstDeclBool.traverseTopDown(visitor);
        if(ConstDeclBoolMul!=null) ConstDeclBoolMul.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ConstDeclBool!=null) ConstDeclBool.traverseBottomUp(visitor);
        if(ConstDeclBoolMul!=null) ConstDeclBoolMul.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclrBool(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclBool!=null)
            buffer.append(ConstDeclBool.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclBoolMul!=null)
            buffer.append(ConstDeclBoolMul.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclrBool]");
        return buffer.toString();
    }
}
