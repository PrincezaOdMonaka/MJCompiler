// generated with ast extension for cup
// version 0.8
// 29/0/2020 16:43:10


package rs.ac.bg.etf.pp1.ast;

public class AbsClassMethodList extends AbsClassMethodDeclList {

    private AnyMethodDeclList AnyMethodDeclList;

    public AbsClassMethodList (AnyMethodDeclList AnyMethodDeclList) {
        this.AnyMethodDeclList=AnyMethodDeclList;
        if(AnyMethodDeclList!=null) AnyMethodDeclList.setParent(this);
    }

    public AnyMethodDeclList getAnyMethodDeclList() {
        return AnyMethodDeclList;
    }

    public void setAnyMethodDeclList(AnyMethodDeclList AnyMethodDeclList) {
        this.AnyMethodDeclList=AnyMethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AnyMethodDeclList!=null) AnyMethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AnyMethodDeclList!=null) AnyMethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AnyMethodDeclList!=null) AnyMethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AbsClassMethodList(\n");

        if(AnyMethodDeclList!=null)
            buffer.append(AnyMethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AbsClassMethodList]");
        return buffer.toString();
    }
}
