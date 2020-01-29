// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:10


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclAnyNum extends ConstDeclAny {

    private ConstDeclNum ConstDeclNum;

    public ConstDeclAnyNum (ConstDeclNum ConstDeclNum) {
        this.ConstDeclNum=ConstDeclNum;
        if(ConstDeclNum!=null) ConstDeclNum.setParent(this);
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
        if(ConstDeclNum!=null) ConstDeclNum.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclNum!=null) ConstDeclNum.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclNum!=null) ConstDeclNum.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclAnyNum(\n");

        if(ConstDeclNum!=null)
            buffer.append(ConstDeclNum.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclAnyNum]");
        return buffer.toString();
    }
}
