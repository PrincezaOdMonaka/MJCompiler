// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:10


package rs.ac.bg.etf.pp1.ast;

public class ForStatement extends Statement {

    private ForStmt ForStmt;

    public ForStatement (ForStmt ForStmt) {
        this.ForStmt=ForStmt;
        if(ForStmt!=null) ForStmt.setParent(this);
    }

    public ForStmt getForStmt() {
        return ForStmt;
    }

    public void setForStmt(ForStmt ForStmt) {
        this.ForStmt=ForStmt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForStmt!=null) ForStmt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForStmt!=null) ForStmt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForStmt!=null) ForStmt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStatement(\n");

        if(ForStmt!=null)
            buffer.append(ForStmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForStatement]");
        return buffer.toString();
    }
}
