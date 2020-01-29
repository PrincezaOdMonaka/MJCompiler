// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:11


package rs.ac.bg.etf.pp1.ast;

public class Mulopr extends Mulop {

    public Mulopr () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Mulopr(\n");

        buffer.append(tab);
        buffer.append(") [Mulopr]");
        return buffer.toString();
    }
}
