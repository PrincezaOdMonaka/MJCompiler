// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:10


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclrChar extends ConstDecl {

    private Type Type;
    private ConstDeclChar ConstDeclChar;
    private ConstDeclCharMul ConstDeclCharMul;

    public ConstDeclrChar (Type Type, ConstDeclChar ConstDeclChar, ConstDeclCharMul ConstDeclCharMul) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ConstDeclChar=ConstDeclChar;
        if(ConstDeclChar!=null) ConstDeclChar.setParent(this);
        this.ConstDeclCharMul=ConstDeclCharMul;
        if(ConstDeclCharMul!=null) ConstDeclCharMul.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ConstDeclChar getConstDeclChar() {
        return ConstDeclChar;
    }

    public void setConstDeclChar(ConstDeclChar ConstDeclChar) {
        this.ConstDeclChar=ConstDeclChar;
    }

    public ConstDeclCharMul getConstDeclCharMul() {
        return ConstDeclCharMul;
    }

    public void setConstDeclCharMul(ConstDeclCharMul ConstDeclCharMul) {
        this.ConstDeclCharMul=ConstDeclCharMul;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ConstDeclChar!=null) ConstDeclChar.accept(visitor);
        if(ConstDeclCharMul!=null) ConstDeclCharMul.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ConstDeclChar!=null) ConstDeclChar.traverseTopDown(visitor);
        if(ConstDeclCharMul!=null) ConstDeclCharMul.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ConstDeclChar!=null) ConstDeclChar.traverseBottomUp(visitor);
        if(ConstDeclCharMul!=null) ConstDeclCharMul.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclrChar(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclChar!=null)
            buffer.append(ConstDeclChar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclCharMul!=null)
            buffer.append(ConstDeclCharMul.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclrChar]");
        return buffer.toString();
    }
}
