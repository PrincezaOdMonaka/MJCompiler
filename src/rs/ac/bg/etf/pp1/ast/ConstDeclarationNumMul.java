// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:10


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclarationNumMul extends ConstDeclNumMul {

    private ConstDeclNumMul ConstDeclNumMul;
    private ConstDeclNum ConstDeclNum;

    public ConstDeclarationNumMul (ConstDeclNumMul ConstDeclNumMul, ConstDeclNum ConstDeclNum) {
        this.ConstDeclNumMul=ConstDeclNumMul;
        if(ConstDeclNumMul!=null) ConstDeclNumMul.setParent(this);
        this.ConstDeclNum=ConstDeclNum;
        if(ConstDeclNum!=null) ConstDeclNum.setParent(this);
    }

    public ConstDeclNumMul getConstDeclNumMul() {
        return ConstDeclNumMul;
    }

    public void setConstDeclNumMul(ConstDeclNumMul ConstDeclNumMul) {
        this.ConstDeclNumMul=ConstDeclNumMul;
    }

    public ConstDeclNum getConstDeclNum() {
        return ConstDeclNum;
    }

    public void setConstDeclNum(ConstDeclNum ConstDeclNum) {
        this.ConstDeclNum=ConstDeclNum;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclNumMul!=null) ConstDeclNumMul.accept(visitor);
        if(ConstDeclNum!=null) ConstDeclNum.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclNumMul!=null) ConstDeclNumMul.traverseTopDown(visitor);
        if(ConstDeclNum!=null) ConstDeclNum.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclNumMul!=null) ConstDeclNumMul.traverseBottomUp(visitor);
        if(ConstDeclNum!=null) ConstDeclNum.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclarationNumMul(\n");

        if(ConstDeclNumMul!=null)
            buffer.append(ConstDeclNumMul.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclNum!=null)
            buffer.append(ConstDeclNum.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclarationNumMul]");
        return buffer.toString();
    }
}
