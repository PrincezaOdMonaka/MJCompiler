// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:10


package rs.ac.bg.etf.pp1.ast;

public class ElseStmt extends ElseStatement {

    private OneOrBlockStatement OneOrBlockStatement;

    public ElseStmt (OneOrBlockStatement OneOrBlockStatement) {
        this.OneOrBlockStatement=OneOrBlockStatement;
        if(OneOrBlockStatement!=null) OneOrBlockStatement.setParent(this);
    }

    public OneOrBlockStatement getOneOrBlockStatement() {
        return OneOrBlockStatement;
    }

    public void setOneOrBlockStatement(OneOrBlockStatement OneOrBlockStatement) {
        this.OneOrBlockStatement=OneOrBlockStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OneOrBlockStatement!=null) OneOrBlockStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OneOrBlockStatement!=null) OneOrBlockStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OneOrBlockStatement!=null) OneOrBlockStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ElseStmt(\n");

        if(OneOrBlockStatement!=null)
            buffer.append(OneOrBlockStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ElseStmt]");
        return buffer.toString();
    }
}
