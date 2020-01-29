// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:10


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclAnyChar extends ConstDeclAny {

    private ConstDeclChar ConstDeclChar;

    public ConstDeclAnyChar (ConstDeclChar ConstDeclChar) {
        this.ConstDeclChar=ConstDeclChar;
        if(ConstDeclChar!=null) ConstDeclChar.setParent(this);
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
        if(ConstDeclChar!=null) ConstDeclChar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclChar!=null) ConstDeclChar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclChar!=null) ConstDeclChar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclAnyChar(\n");

        if(ConstDeclChar!=null)
            buffer.append(ConstDeclChar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclAnyChar]");
        return buffer.toString();
    }
}
