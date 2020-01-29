// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:10


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclrNum extends ConstDecl {

    private Type Type;
    private ConstDeclNum ConstDeclNum;
    private ConstDeclNumMul ConstDeclNumMul;

    public ConstDeclrNum (Type Type, ConstDeclNum ConstDeclNum, ConstDeclNumMul ConstDeclNumMul) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ConstDeclNum=ConstDeclNum;
        if(ConstDeclNum!=null) ConstDeclNum.setParent(this);
        this.ConstDeclNumMul=ConstDeclNumMul;
        if(ConstDeclNumMul!=null) ConstDeclNumMul.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ConstDeclNum getConstDeclNum() {
        return ConstDeclNum;
    }

    public void setConstDeclNum(ConstDeclNum ConstDeclNum) {
        this.ConstDeclNum=ConstDeclNum;
    }

    public ConstDeclNumMul getConstDeclNumMul() {
        return ConstDeclNumMul;
    }

    public void setConstDeclNumMul(ConstDeclNumMul ConstDeclNumMul) {
        this.ConstDeclNumMul=ConstDeclNumMul;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ConstDeclNum!=null) ConstDeclNum.accept(visitor);
        if(ConstDeclNumMul!=null) ConstDeclNumMul.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ConstDeclNum!=null) ConstDeclNum.traverseTopDown(visitor);
        if(ConstDeclNumMul!=null) ConstDeclNumMul.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ConstDeclNum!=null) ConstDeclNum.traverseBottomUp(visitor);
        if(ConstDeclNumMul!=null) ConstDeclNumMul.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclrNum(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclNum!=null)
            buffer.append(ConstDeclNum.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclNumMul!=null)
            buffer.append(ConstDeclNumMul.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclrNum]");
        return buffer.toString();
    }
}
